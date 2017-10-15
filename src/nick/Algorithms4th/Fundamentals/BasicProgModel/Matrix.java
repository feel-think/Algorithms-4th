package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Matrix {

    /**
     * 向量点乘
     * @param x
     * @param y
     * @return x dot y
     */
    public static double dot(double[] x, double[] y ) {
        double product = 0;
        for (int i = 0; i < x.length; i++) {
            product += x[i] * y[i];
        }
        return product;
    }

    /**
     * 矩阵点乘
     * @param x
     * @param y
     * @return x dot y
     */
    public static double[][] mult(double[][] x, double[][] y) {
        int M = x.length;
        int N = y[0].length;
        double[][] result = new double[M][N];
        // 计算结果矩阵中的第 i 行
        for (int i = 0; i < M; i++) {
            // 计算结果矩阵第 i 行中的第 j 列
            for (int j = 0; j < N; j++) {
                // 计算结果矩阵中第 i 行、第 j 列的元素的值
                for (int k = 0; k < x[i].length; k++) {
                    // 累加
                    result[i][j] += x[i][k] * y[k][j];
                }
            }
        }
        return result;
    }

    /**
     * 矩阵转置
     * @param a
     * @return a T
     */
    public static double[][] transpose(double[][] a) {
        int M = a.length;
        int N = a[0].length;
        double[][] result = new double[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[i][j] = a[j][i];
            }
        }
        return result;
    }

    /**
     * 矩阵和向量的点乘
     * @param a
     * @param x
     * @return a dot x
     */
    public static double[] mult(double[][] a, double[] x) {
        int M = a.length;
        double[] result = new double[M];
        // 计算结果矩阵中的第 i 个元素
        for (int i = 0; i < M; i++) {
            // 累加
            for (int j = 0; j < a[i].length; j++) {
                result[i] += a[i][j] * x[j];
            }
        }
        return result;
    }

    /**
     * 向量和矩阵的点乘
     * @param y
     * @param a
     * @return y dot a
     */
    public static double[] mult(double[] y, double[][] a) {
        int M = a[0].length;
        double[] result = new double[M];
        // 计算结果矩阵中的第 i 个元素
        for (int i = 0; i < M; i++) {
            // 累加，y 的整行与 a 的第 i 列相乘
            for (int j = 0; j < a[i].length; j++) {
                result[i] += y[j] * a[j][i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        double[] x = {1, 2};
        double[] y = {3, 4};
        double[][] a = {
                {1, 2},
                {3, 4}
        };
        double[][] b = {
                {5, 6},
                {7, 8}
        };
        // 测试向量点乘
        StdOut.printf("测试向量点乘，正确结果为 11，输出结果为：%f\n", dot(x, y));
        // 测试矩阵点乘
        StdOut.printf("测试矩阵点乘，正确结果为 [[19, 22], [43, 50]]，输出结果为：%s\n",
                Arrays.deepToString(mult(a, b)));
        // 测试矩阵转置
        StdOut.printf("测试矩阵转置，正确结果为 [[1, 3], [2, 4]]，输出结果为：%s\n",
                Arrays.deepToString(transpose(a)));
        // 测试矩阵和向量的点乘
        StdOut.printf("测试矩阵和向量的点乘，正确结果为 [5, 11]，输出结果为：%s\n",
                Arrays.toString(mult(a, x)));
        // 测试向量和矩阵的点乘
        StdOut.printf("测试向量和矩阵的点乘，正确结果为 [7, 10]，输出结果为：%s\n",
                Arrays.toString(mult(x, a)));
    }

}
