package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_1_18 {



    /**
     * 两数相乘的神算法
     */
    private static int mystery1(int a, int b) {
        StdOut.println("a = " + a + " , b = " + b);
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery1(a+a, b/2);
        return mystery1(a+a, b/2) + a;
    }

    /**
     * 计算 a 的 b 次幂的神算法
     */
    private static int mystery2(int a, int b) {
        StdOut.println("a = " + a + " , b = " + b);
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery2(a*a, b/2);
        return mystery2(a*a, b/2) * a;
    }

    public static void main(String[] args) {
        StdOut.println(mystery1(2, 25)); // out: 50
        StdOut.println(mystery2(3, 11)); // out: 177147
    }

}
