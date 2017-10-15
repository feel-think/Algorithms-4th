package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise_1_1_13 {

    private static void printArray(int[][] a) {
        int M = a.length;
        int N = a[0].length;
        int width = (""+a[0][0]).length() + 2;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                StdOut.printf("%"+width+"d",a[i][j]);
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            StdOut.println("请输入二维数组的行数和列数");
            return;
        }
        int M = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);
        int[][] a = new int[M][N];
        // 随机初始化数组
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = (int) (StdRandom.uniform() * 10);
            }
        }
        printArray(a);
        StdOut.println();
        // 将二维数组进行转置
        int[][] b = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                b[i][j] = a[j][i];
            }
        }
        printArray(b);
    }

}
