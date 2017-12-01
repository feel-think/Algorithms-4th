package nick.Algorithms4th.Fundamentals.UnionFind;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import nick.Algorithms4th.myTools.Stopwatch;

public class WeightedQuickUnion {

    /** 触点数组，其中 id[i] 为第 i 个触点所在的连通分量的标识 */
    private int[] id;
    /** 保存分量高度的数组 */
    private int[] size;
    /** 连通分量数 */
    private int N;

    public WeightedQuickUnion(int N) {
        /**
         * 初始化触点数组和高度数组
         */
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
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
         * 将触点数较少的一个树链接到触点数较多的树上，同时更新树的大小
         * 把盲目地链接改为有选择地链接，减小了树的高度，从而减少了 find 的操作次数，提高了整个算法的效率
         * size 为 N 的树的最大高度为 log(N)
         * 因此，find 方法的数组访问次数从 (1, N) 减小为 (1, log(N))
         * 整个算法最坏情况下的增长数量级也降为 M*log(N)
         */
        int pRoot = find(p), qRoot = find(q);
        if (size[pRoot] < size[qRoot]) {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
        N--;
    }

    /**
     * 查找该对象所在连通分量的标识
     * @param p
     * @return
     */
    public int find(int p) {
        int index = p;
        while (id[index] != index) index = id[index];
        // 压缩路径
        if (id[p] != index) {
            int current = p, next;
            do {
                next = id[current]; // 保存指向路径上下一个节点的链接
                id[current] = index; // 压缩当前节点的路径
                current = next; // 转移到下一个节点
            } while (id[current] != index);
        }
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
        // 遍历所有触点，获取所有树中的最大高度
        int N = id.length;
        for (int i = 0; i < N; i++) {
            // 对触点 i，计算其深度
            int index = i;
            int depth = 0;
            while (id[index] != index) { // 判断
                index = id[index]; // 改变指向
                depth++; // 累加深度
            }
            if (depth > max) max = depth;
        }
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
        MyWeightedQuickUnion uf = new MyWeightedQuickUnion(N);
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
        // 综上，MyWeightedQuickUnion 的最坏增长数量级为 M*log(N)
    }

    public static double[] timeTrial(int[] scale) {
        int N = scale[0], M = scale[1];
        Stopwatch timer = new Stopwatch();
        MyWeightedQuickUnion uf = new MyWeightedQuickUnion(N);
        for (int i = 0; i < M; i++) {
            // 使用随机生成的连接
            int p = StdRandom.uniform(0, N), q = StdRandom.uniform(0, N);
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        }
        double time = timer.elapsedTime();
        return new double[]{time, uf.count(), uf.maxHeight(), uf.averageDepth()};
    }

    public static void main(String[] args) {

    }

}
