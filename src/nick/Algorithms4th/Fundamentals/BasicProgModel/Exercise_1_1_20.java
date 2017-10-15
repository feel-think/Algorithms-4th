package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_1_20 {

    /**
     * 计算 ln(N!) 的递归实现
     */
    private static double lnOfFactorialOf(int N) {
        return Math.log(N) * lnOfFactorialOf(N-1);
    }

    public static void main(String[] args) {
        StdOut.println(lnOfFactorialOf(4));
    }

}
