package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Exercise_1_1_30 {

    public static void main(String[] args) {
        int N = 10;
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = (Euclid.gcd(i, j) == 1) ? true : false;
            }
        }
        StdOut.println(Arrays.deepToString(a));
    }

}
