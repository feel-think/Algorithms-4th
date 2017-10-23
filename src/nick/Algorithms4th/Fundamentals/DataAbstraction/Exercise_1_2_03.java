package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise_1_2_03 {

    private static Interval1D getInterval1D(double l, double r) {
        double a = StdRandom.uniform(l, r);
        double b = StdRandom.uniform(l, r);
        double min = a <= b ? a : b;
        double max = a <= b ? b : a;
        return new Interval1D(min, max);
    }

    private static boolean contain(Interval2D a, Interval2D b) {
        /**
         * 无法通过 Interval2D 现有的 API 判断两个 Interval2D 对象是否存在包含关系
         * 只能通过 Interval2D 的 toString() 方法获取其范围，然后手动判断两个对象是否存在包含关系
         */
        return false;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);
        // 生成 N 个随机 2D 间隔，其边界随机分布在 min 到 max 之间
        Interval2D[] intervals = new Interval2D[N];
        for (int i = 0; i < N; i++) {
            intervals[i] = new Interval2D(getInterval1D(min, max), getInterval1D(min, max));
            intervals[i].draw();
        }
        // 计算相交间隔对和包含间隔对的数量
        int intersects = 0;
        int contains = 0;
        for (int i = 0; i < intervals.length - 1; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i].intersects(intervals[j])) intersects++;
                if (contain(intervals[i], intervals[j])) contains++;
            }
        }
        StdOut.printf("%d 个随机 2D 间隔对中有 %d 个相交的间隔对，%d 个存在包含关系的间隔对", N, intersects, contains);
    }

}
