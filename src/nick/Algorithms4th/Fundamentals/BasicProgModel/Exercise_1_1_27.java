package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

import java.util.Date;

public class Exercise_1_1_27 {

    private static long recursionCount = 0;

    /**
     * 计算二项分布的实现，by Nick
     * @param N 实验次数
     * @param k 事件发生次数
     * @param p 单次事件发生概率
     * @return N 次实验中事件发生 k 次的概率
     */
    private static double myBinomial(int N, int k, double p) {
        /**
         * PNk = CNk * p^k * (1-p)^(N-k)
         *   = (N! / ((N-k)! * k!)) * p^k * (1-p)^(N-k)
         *   = ((N-k+1) / k) * (p / (1-p)) * PN(k-1)
         */
        // 异常情况，p 不属于 [0, 1]
        if (p < 0 || p > 1) {
            StdOut.printf("Error! 0 <= p <=1 must be satisfied. p = %d\n", p);
            return 0.0;
        }
        // 异常情况，N 或 k 存在非法值
        if (N < 0 || k < 0) {
            StdOut.printf("Error! N >= 0 and k >= 0 must be satisfied. N = %d, k = %d\n", N, k);
            return 0.0;
        }
        // 最简单的情况
        if (k == 0) return Math.pow(1-p, N);
        // 特殊情况
        if (N == 0 || k == 0) return 1.0;
        return ((N-k+1) / k) * (p / (1-p)) * myBinomial(N,k-1, p);
    }

    /**
     * 书本上的实现
     * 1 分钟内可计算至 P(N=100, k=5, p=0.500000) = 0.000000。进行了 2551031743 (25亿) 次递归，耗时 12 秒
     * 优化版：  计算至 P(N=100, k=5, p=0.500000) = 0.000000。进行了       1265       次递归，耗时 47 毫秒
     */
    private static double binomial(int N, int k, double p) {
        /**
         *                                                             b(5, 3)
         *                             b(4, 3)                                                         b(4, 2)
         *             b(3, 3)                         b(3, 2)                         b(3, 2)                         b(3, 1)
         *     b(2, 3)         b(2, 2)         b(2, 2)         b(2, 1)         b(2, 2)         b(2, 1)         b(2, 1)         b(2, 0)
         * b(1, 3) b(1, 2) b(1, 2) b(1, 1) b(1, 2) b(1, 1) b(1, 1) b(1, 0) b(1, 2) b(1, 1) b(1, 1) b(1, 0) b(1, 1) b(1, 0) b(1, 0) b(1, -1)
         * ...................
         */
        // 递归次数 +1
        recursionCount++;
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0)   return 0.0;
        return (1.0 - p)*binomial(N-1, k, p) + p*binomial(N-1, k-1, p);
    }

    private static void testBinomial() {
        long start = new Date().getTime();
        double p = 0.5;
        int N = 100;
        for (int k = 0; k <= N/2; k++) {
            recursionCount = 0;
            double P = binomial(N, k, p);
            StdOut.printf("已运行 %d 毫秒。P(N=%d, k=%d, p=%f) = %f. binomial 进行了 %d 次递归\n",
                    (new Date().getTime() - start), N, k, p, P, recursionCount);
        }
    }

    /**
     * 使用数组保存计算结果的实现方式
     * 1 分钟内可计算至 P(N=100, k=30, p=0.500000) = 0.000023。进行了 4294971633 (42亿) 次递归，耗时 44 秒
     * 计算至 P(N=100, k=5, p=0.500000) = 0.000000。进行了 1265 次递归，耗时 47 毫秒
     */
    private static double binomialOptimized(int N, int k, double p) {
        // 创建用于保存计算结果的数组
        double[][] Ps = new double[N+1][k+1];
        return binomialOptimized(N, k, p, Ps);
    }

    private static double binomialOptimized(int N, int k, double p, double[][] Ps) {
        recursionCount++;
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0)   return 0.0;
        if (Ps[N][k] != 0.0) return Ps[N][k];
        Ps[N][k] = (1.0 - p)*binomialOptimized(N-1, k, p, Ps) + p*binomialOptimized(N-1, k-1, p, Ps);
        return Ps[N][k];
    }

    private static void testBinomialOptimized() {
        long start = new Date().getTime();
        double p = 0.5;
        int N = 100;
        for (int k = 0; k <= N/2; k++) {
            recursionCount = 0;
            double P = binomialOptimized(N, k, p);
            StdOut.printf("已运行 %d 毫秒。P(N=%d, k=%d, p=%f) = %f. binomialOptimized 进行了 %d 次递归\n",
                    (new Date().getTime() - start), N, k, p, P, recursionCount);
        }
    }

    public static void main(String[] args) {
        testBinomial();
//        testBinomialOptimized();
    }
}
