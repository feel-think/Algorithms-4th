package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise_1_1_11 {

    public static void main(String[] args) {
        if (args.length < 2) {
            StdOut.println("请输入二维布尔数组的行数和列数");
            return;
        }
        int M = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);
        boolean[][] a = new boolean[M][N];
        // 随机初始化 boolean 数组
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = (StdRandom.uniform() >= 0.5) ? true : false;
            }
        }
        String columnNames = "  ";
        for (int i = 1; i <= N; i++)
            columnNames += " " + i;
        // 打印列号
        StdOut.println(columnNames);
        for (int i = 0; i < M; i++) {
            // 打印行号
            StdOut.print(" " + (i + 1));
            for (int j = 0; j < N; j++) {
                // 打印元素
                StdOut.print(" " + (a[i][j] ? "*" : " "));
            }
            StdOut.println();
        }
    }

}
