package nick.Algorithms4th.Fundamentals.UnionFind;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import nick.Algorithms4th.myTools.Stopwatch;

public class MyQuickUF {

    /**
     * 触点数组，其中 a[i] 为第 i 个触点所在的连通分量的标识
     */
    private int[] a;
    /**
     * 连通分量数
     */
    private int N;

    public MyQuickUF(int N) {
        /**
         * 初始化触点数组，其中 a[i] 有多种含义，当 a[i] = i 时，a[i] 代表触点 i 所在的连通分量的标识
         * 当 a[i] != i 时，a[i] 代表与触点 i 相连的触点 j 的索引
         * 初始状态有 N 个连通分量
         */
        a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = i;
        }
        this.N = N;
    }

    /**
     * 将两个对象连接起来
     * @param p 对象 p
     * @param q 对象 q
     */
    public void union(int p, int q) {
        /**
         * API 向外暴露的语义是将两个对象连接起来，但在内部，应该是将两个对象所在的连通分量合并起来
         * a[i] 中存放的是与触点 i 相连的触点的索引，在这种存储结构下，连接两个分量只需找到两个分量的根触点
         * 然后将其中一个根触点指向另一个根触点即可
         */
        a[find(q)] = find(p);
        N--;
    }

    /**
     * 查找该对象所在连通分量的标识
     * @param p
     * @return
     */
    public int find(int p) {
        // 从触点 p 开始，沿网络寻找根触点，根触点的定义是 a[index] = index，即其指向自己
        int index = p;
        while (a[index] != index) { // <- 判断执行的次数比下面的赋值多一次
            // 指向触点网络中的下一个节点
            index = a[index]; // <- 该赋值语句执行的次数 ∈ (0, N-1)
        }
        // 综上，find() 方法对数组的访问次数 ∈ (1, 2N-1)
        return index;
    }

    /**
     * 判断两个对象是否在一个连通分量里
     * @param p 对象 p
     * @param q 对象 q
     * @return 是否连通
     */
    public boolean connected(int p, int q) {
        return find(q) == find(p);
    }

    /**
     * 连通分量的个数
     * @return 连通分量的个数
     */
    public int count() {
        return N;
    }

    private static void myTest() {
        StdOut.print("请输入网络的大小：");
        int N = StdIn.readInt();
        MyUF uf = new MyUF(N);
        for (int i = 0; ; i++) {
            StdOut.print("请输入一对触点：");
            int p = StdIn.readInt(), q = StdIn.readInt();
            if (uf.connected(p, q)) {
                StdOut.printf("%d 和 %d 是连通的。\n", p, q);
            } else {
                StdOut.printf("%d 和 %d 未连通，已建立连接。\n", p, q);
                if (i < 10) uf.union(p, q);
            }
            StdOut.printf("现有连通分量数：%d\n", uf.count());
        }
    }

    private static void mainTest() {
        /**
         * 算法分析：
         * 设触点数为 N 个，连接数为 M 个，成本模型为：对数组的访问次数
         */
        In stdIn = new In("data/1.5/largeUF.txt");
        int N = stdIn.readInt();
        Stopwatch timer = new Stopwatch();
        MyQuickUF uf = new MyQuickUF(N);
        while (!stdIn.isEmpty()) {
            int p = stdIn.readInt(), q = stdIn.readInt();
            // connected() 方法执行 M 次，connected() 方法对数组的访问次数为 2*find，find ∈ (1, 2N-1)
            if (uf.connected(p, q)) continue;
            StdOut.println(p + " " + q);
            // union() 方法最多执行 N-1 次，union() 方法对数组的访问次数为 2*find+1
            uf.union(p, q);
        }
        StdOut.printf("共有 %d 个连通分量\n", uf.count());
        StdOut.printf("共耗时 %f 秒\n", timer.elapsedTime());
        // 综上，MyQuickUF 的增长数量级为 M * find
    }

    private static void doublingRatio() {

    }

    public static void main(String[] args) {
    }

}
