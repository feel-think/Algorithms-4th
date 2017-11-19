package nick.Algorithms4th.Fundamentals.UnionFind;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import nick.Algorithms4th.myTools.Stopwatch;

public class MyUF {

    /**
     * 触点数组，其中 a[i] 为第 i 个触点所在的连通分量的标识
     */
    private int[] a;
    /**
     * 连通分量数
     */
    private int N;

    public MyUF(int N) {
        // 初始化触点数组，其中 a[i] 代表第 i 个触点所在的连通分量的标识。
        // 初始状态有 N 个连通分量
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
         * 获取触点 p, q 的连通分量标识 CCOfP, CCOfQ，遍历所有触点，将所有 连通分量标识 为 CCOfQ 的改为 CCOfP
         */
        // 此处进行了两次数组访问
        int CCOfP = a[p], CCOfQ = a[q];
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // if 判断执行 n 次，true 代码块执行次数 ∈ (1, n-1)
            if (a[i] == CCOfQ) a[i] = CCOfP;
        }
        N--;
        // 所以 union() 方法对数组的访问次数  ∈ (n+3, 2n+1)
    }

    /**
     * 查找该对象所在连通分量的标识
     * @param p
     * @return
     */
    public int find(int p) {
        return a[p];
    }

    /**
     * 判断两个对象是否在一个连通分量里
     * @param p 对象 p
     * @param q 对象 q
     * @return 是否连通
     */
    public boolean connected(int p, int q) {
        return a[p] == a[q];
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
        MyUF uf = new MyUF(N);
        while (!stdIn.isEmpty()) {
            int p = stdIn.readInt(), q = stdIn.readInt();
            // connected() 方法执行 M 次，connected() 方法对数组的访问次数为 2 次
            if (uf.connected(p, q)) continue;
            StdOut.println(p + " " + q);
            // 在 N 个触点的网络中，初始状态有 N 个分量，每次连接使分量数减 1
            // 因此 union() 方法最多执行 N-1 次，union() 方法对数组的访问次数 ∈ (N+3, 2N+1)
            uf.union(p, q);
        }
        StdOut.printf("共有 %d 个连通分量\n", uf.count());
        StdOut.printf("共耗时 %f 秒\n", timer.elapsedTime());
        // 综上，MyUF 的增长数量级约为 N^2
    }

    public static void main(String[] args) {
        mainTest();
    }

}
