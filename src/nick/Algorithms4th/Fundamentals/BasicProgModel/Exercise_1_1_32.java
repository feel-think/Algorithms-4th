package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise_1_1_32 {

    /**
     * 将区间 (l, r) 分为 N 段，从标准输入中读入一系列 double 值，统计这些 double 值在 (l, r) 中的分布，并绘制直方图
     * @param N 划分的段数
     * @param l 区间左边界
     * @param r 区间右边界
     */
    private static void histogram(int N, double l, double r) {
        // 统计分布
        double[] nums = StdIn.readAllDoubles();
        int[] his = new int[N];
        double interval = (r - l) / N;
        for (double num : nums) {
            // 计算该值在区间 (l, r) 中位于第几段
            int index = (int) ((num - l) / interval);
            his[index]++;
        }
        StdOut.println(Arrays.toString(his));
        // 绘制直方图
        StdDraw.setXscale(l - (r - l)/10, r + (r - l)/10);
        StdDraw.setYscale(0, StdStats.max(his));
        for (int i = 0; i < his.length; i++) {
            // 计算第 i 个长条的中心坐标
            double x = l + interval * i + interval / 4;
            double y = his[i] / 2.0;
            double halfWidth = interval / 4;
            double halfHeight = his[i] / 2.0;
            StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
        }
    }

    /**
     * 产生 N 个随机数，介于 l 和 h 之间
     * @param N 随机数的个数
     * @param l 随机数范围的左边界
     * @param h 随机数范围的右边界
     */
    private static void generateRandomNumbers(int N, double l, double h) {
        Out file = new Out("randomDoubles.txt");
        for (int i = 0; i < N; i++) {
            file.println(StdRandom.uniform(l, h));
        }
        file.close();
    }

    public static void main(String[] args) {
        histogram(10, 2, 10);
    }

}
