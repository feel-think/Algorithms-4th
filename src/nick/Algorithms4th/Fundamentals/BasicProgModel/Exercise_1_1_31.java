package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise_1_1_31 {

    /**
     * 在单位圆上画出间距相等的 N 个点，然后将每对点按照概率 p 用灰线连接
     * @param N 点的数量
     * @param p 概率
     */
    private static void randomConnect(int N, double p) {
        StdDraw.setXscale(-1.1, 1.1);
        StdDraw.setYscale(-1.1, 1.1);
        // 画圆
        double xOfCenter = 0.0;
        double yOfCenter = 0.0;
        double radius = 1.0;
        StdDraw.circle(xOfCenter, yOfCenter, radius);
        // 等分，画点
        double radian = (360.0 / N) * (Math.PI / 180);
        StdDraw.setPenRadius(0.025);
        StdDraw.setPenColor(StdDraw.BLUE);
        double[][] points = new double[N][2];
        for (int i = 0; i < N; i++) {
            // 计算每个点的坐标，绘制，并记录下来
            double x = xOfCenter + Math.cos(i * radian);
            double y = yOfCenter + Math.sin(i * radian);
            StdDraw.point(x, y);
            // 存储点的坐标
            points[i][0] = x;
            points[i][1] = y;
        }
        // 随机连接
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.GRAY);
        for (int i = 0; i < points.length - 1; i++)
            for (int j = i + 1; j < points.length; j++)
                if (StdRandom.bernoulli(p))
                    StdDraw.line(points[i][0], points[i][1], points[j][0], points[j][1]);
    }

    public static void main(String[] args) {
        randomConnect(10, 0.5);
    }

}
