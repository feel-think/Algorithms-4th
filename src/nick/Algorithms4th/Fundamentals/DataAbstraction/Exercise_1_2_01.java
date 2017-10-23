package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise_1_2_01 {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Point2D[] points = new Point2D[N];
        StdDraw.setPenRadius(0.005);
        // 生成 N 个随机点
        for (int i = 0; i < N; i++) {
            points[i] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
            points[i].draw();
        }
        // 计算最近距离
        double min = 1;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = points[i].distanceTo(points[j]);
                if (distance < min) min = distance;
            }
        }
        StdOut.printf("%d 个随机点中两点最小距离为 %f\n", N, min);
    }

}
