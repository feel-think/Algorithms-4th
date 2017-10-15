package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_1_16 {

    private static String exR1(int n) {
        // "" + 3 + "" + 1 + "" + 1 + 3 + 6 + "" + 1 + "" + 1 + 4 + "" + 2 + "" + 2 + 4 + 6 = "311361142246"
        if (n <= 0) return "";
        return exR1(n-3) + n + exR1(n-2) + n;
    }

    public static void main(String[] args) {
        StdOut.println(exR1(6));
    }

}
