package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_1_14 {

    private static int lg(int N) {
        // 求 n，使 2^n <= N 且 2^(n+1) > N
        int n = 0;
        int N_guess = 1;
        for ( ; ; N_guess *= 2, n += 1) {
            if ((N_guess <= N) && (N_guess * 2 > N))
                break;
        }
        return n;
    }

    public static void main(String[] args) {
        StdOut.printf("Expected: 1, got: %d.\n", lg(3));
    }

}
