package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_1_07 {

    private static void test1() {
        double t = 9.0;
        while (Math.abs(t - 9.0/t) > .001)
            t = (9.0/t + t) / 2.0;
        StdOut.printf("%.5f\n", t);
    }

    private static void test2() {
        int sum = 0;
        for (int i = 0; (Math.pow(2, i)) < 1000; i++)
            for (int j = 0; j < 1000; j++)
                sum++;
        StdOut.println(sum);
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

}
