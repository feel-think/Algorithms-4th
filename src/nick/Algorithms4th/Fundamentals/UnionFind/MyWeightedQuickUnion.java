package nick.Algorithms4th.Fundamentals.UnionFind;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import nick.Algorithms4th.myTools.Stopwatch;

public class MyWeightedQU {

    /** 触点数组，其中 id[i] 为第 i 个触点所在的连通分量的标识 */
    private int[] id;
    /** 保存分量高度的数组 */
    private int[] height;
    /** 连通分量数 */
    private int N;

    public MyWeightedQU(int N) {
        /**
         * 初始化触点数组和高度数组
         */
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        height = new int[N];
        this.N = N;
    }

    /**
     * 将两个对象连接起来
     * @param p 对象 p
     * @param q 对象 q
     */
    public void union(int p, int q) {
        /**
         * 将高度较小的一个分量链接到高度较大的分量上，同时更新高度信息
         * 通过减少树的高度，减少了 find 的操作次数，从而提高了整个算法的效率
         * 高度为 2 的树最少需要两个触点，高度为 3 的树最少需要两个高度为 2 的树
         * 以此类推，树的最大高度为 log(N)
         * 因此，find 方法的数组访问次数从 (1, N) 减小为 (1, log(N))
         * 整个算法最坏情况下的增长数量级也降为 M*log(N)
         */
        int pRoot = find(p), qRoot = find(q);
        if (height[pRoot] < height[qRoot]) {
            id[pRoot] = qRoot;
            height[pRoot] = 0;
        } else {
            id[qRoot] = pRoot;
            // 当两棵树高度相同时，将主树的高度加 1
            if (height[pRoot] == height[qRoot])
                height[pRoot]++;
            height[qRoot] = 0;
        }
        N--;
    }

    /**
     * 查找该对象所在连通分量的标识
     * @param p
     * @return
     */
    public int find(int p) {
        // 从触点 p 开始，沿网络寻找根触点，根触点的定义是 id[index] = index，即其指向自己
        int index = p;
        int pointer;
        while (true) {
            pointer = id[index]; // <- 该赋值语句执行的次数 ∈ (1, N)
            // 判断索引为 index 的触点是否是根触点
            if (pointer == index) break;
            // 不是根触点时，指向触点网络中的下一个节点
            index = pointer;
        }
        // 综上，find() 方法对数组的访问次数 ∈ (1, N)
        return index;
    }

    public int slowFind(int p) {
        // 从触点 p 开始，沿网络寻找根触点，根触点的定义是 id[index] = index，即其指向自己
        int index = p;
        while (id[index] != index) { // <- 判断执行的次数比下面的赋值多一次
            // 指向触点网络中的下一个节点
            index = id[index]; // <- 该赋值语句执行的次数 ∈ (0, N-1)
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

    public int maxHeight() {
        int max = 0;
        int N = height.length;
        for (int i = 0; i < N; i++)
            if (height[i] > max) max = height[i];
        return max;
    }

    public double averageDepth() {
        // 遍历所有触点，计算平均深度
        int sum = 0;
        int N = id.length;
        for (int i = 0; i < N; i++) {
            // 对触点 i，计算其深度，并累加起来
            int index = i;
            while (id[index] != index) { // 判断
                index = id[index]; // 改变指向
                sum++; // 累加深度
            }
        }
        return (float) sum / N;
    }

    private static void mainTest() {
        /**
         * 算法分析：
         * 设触点数为 N 个，连接数为 M 个，成本模型为：对数组的访问次数
         */
        In stdIn = new In("data/1.5/largeUF.txt");
        int N = stdIn.readInt();
        Stopwatch timer = new Stopwatch();
        MyWeightedQU uf = new MyWeightedQU(N);
        while (!stdIn.isEmpty()) {
            int p = stdIn.readInt(), q = stdIn.readInt();
            // connected() 方法执行 M 次，connected() 方法对数组的访问次数为 2*find，find ∈ (1, log(N))
            if (uf.connected(p, q)) continue;
            StdOut.println(p + " " + q);
            // union() 方法最多执行 N-1 次，union() 方法对数组的访问次数为 2*find+1
            uf.union(p, q);
        }
        StdOut.printf("共有 %d 个连通分量\n", uf.count());
        StdOut.printf("共耗时 %f 秒\n", timer.elapsedTime());
        // 综上，MyWeightedQU 的最坏增长数量级为 M*log(N)
    }

    private static double[] timeTrial(int N, int M) {
        Stopwatch timer = new Stopwatch();
        MyWeightedQU uf = new MyWeightedQU(N);
        for (int i = 0; i < M; i++) {
            // 使用随机生成的连接
            int p = StdRandom.uniform(0, N), q = StdRandom.uniform(0, N);
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        }
        double time = timer.elapsedTime();
        return new double[]{time, uf.count(), uf.maxHeight(), uf.averageDepth()};
    }

    public static void doublingRatio() {
        // 对不同的触点数 N 和 连接数 M，进行实验，记录时间，计算时间比
        // 选取 M = 2 * N
        int N = 125, r = 2;
        // 计算第一个参考时间
        double[] result = timeTrial(N, r*N);
        double prev = result[0];
        double time;
        int count;
        StdOut.printf("%-16s %10s %10s %5s %5s %6s\n", "N", "time(s)", "count", "ratio", "maxHeight", "avgDepth");
        while (true) {
            N *= 2;
            result = timeTrial(N, r*N);
            time = result[0];
            count = (int) result[1];
            // 规模 N , 花费的时间 , 分量个数，与上一次时间之比
            StdOut.printf("%-16d %10.2f %10d %5.1f %5d %6.1f\n", N, time, count, time/prev, (int) result[2], result[3]);
            prev = time;
        }
    }

    public static void main(String[] args) {
        doublingRatio();
    }

}
