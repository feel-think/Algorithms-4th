package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_2_07 {

    private static String mystery(String s) {
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N/2);
        String b = s.substring(N/2, N);
        return mystery(b) + mystery(a);
    }

    public static void main(String[] args) {
        StdOut.println(mystery("12345678")); // 反序，输出为 "87654321"
    }

}
