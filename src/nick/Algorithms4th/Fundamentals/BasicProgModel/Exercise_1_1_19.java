package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

import java.util.Date;

public class Exercise_1_1_19 {

    private static int recursionCount = 0;

    /**
     * 使用递归计算斐波那契数的方法，原始版本。
     * 当 N 过大时，因递归深度过深而导致效率极低，一个小时内大约只能计算到 F(55) 左右
     */
    private static long F(int N) {
        recursionCount++;
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N-1) + F(N-2);
    }

    private static void testF() {
        // 迭代计算
        long start = new Date().getTime();
        for (int N = 0; N < 100; N++) {
            recursionCount = 0;
            // 输出
            String s = "F(" + N + ") = " + F(N);
            StdOut.printf("已运行 %d 毫秒。%s. 进行了 %d 次递归\n",
                    (new Date().getTime() - start), s, recursionCount);
        }
    }

    /**
     * 需要外部提供存储对象的 F(N)，速度最快（？），只需约 34 毫秒
     */
    private static long FDependent(int N, long[] fns) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        // 检索存储对象，如果已有 F(N) 值，则直接返回不必再重新递归计算。
        if (fns[N] != 0) return fns[N];
        // 若该 F(N) 值还未被计算过，则进行递归计算
        return FDependent(N-1, fns) + FDependent(N-2, fns);
    }

    private static void testFDependent() {
        // 创建用来存放计算结果的数组对象
        long[] fns = new long[100];
        // 迭代计算
        long start = new Date().getTime();
        for (int N = 0; N < 100; N++) {
            // 计算 F(N) 并保存在数组对象中
            fns[N] = FDependent(N, fns);
            // 输出
            String s = N + " " + fns[N];
            StdOut.println("已运行 " + (new Date().getTime() - start) + " 毫秒。" + s);
        }
    }

    /**
     * 无需外部存储对象的 F(N)，和需要外部存储的 F(N) 速度差不多
     * 斐波那契数的计算类似于沿一棵二叉树进行计算，期间较小的 N 会被计算多次。
     * 如                 F(4)
     *                ↙       ↘
     *           F(3)              F(2)
     *         ↙    ↘           ↙    ↘
     *      F(2)     F(1)     F(1)     F(0)
     *    ↙    ↘
     * F(1)     F(0)
     * 除 F(1) F(0) 无需计算外，F(2) 被计算了两次。当 N 较大时，这种重复计算会造成极大的浪费，导致性能极低。
     * 使用带内部存储的 F(N) 实现，则对于每一个 F(N)，只有二叉树最左边的节点会使用递归进行计算，
     * 所有其他节点的值都可以通过检索内部存储对象得到
     */
    private static long FOptimized(int N) {
        // 创建用来存放计算结果的数组
        long[] fns = new long[N+1];
        // 计算
        return FOptimized(N, fns);
    }

    private static long FOptimized(int N, long[] fns) {
        recursionCount++;
        if (N == 0) return 0;
        if (N == 1) return 1;
        if (fns[N] != 0) return fns[N];
        fns[N] = FOptimized(N-1, fns) + FOptimized(N-2, fns);
        return fns[N];
    }

    private static void testFOptimized() {
        // 开始计算
        long start = new Date().getTime();
        for (int N = 0; N < 100; N++) {
            recursionCount = 0;
            String s = N + " " + FOptimized(N);
            StdOut.printf("已运行 %d 毫秒。%s. 进行了 %d 次递归\n",
                    (new Date().getTime() - start), s, recursionCount);
        }
    }

    public static void main(String[] args) {
        testF();
    }

}
