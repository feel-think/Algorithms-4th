package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.*;

public class Exercise_1_2_02 {

    /**
     * 产生 N 对随机数，介于 l 和 h 之间
     * @param N 随机数的个数
     * @param l 随机数范围的左边界
     * @param h 随机数范围的右边界
     */
    private static void generateRandomNumbers(int N, double l, double h) {
        Out file = new Out("randomDoublePairs.txt");
        for (int i = 0; i < N; i++) {
            file.println(StdRandom.uniform(l, h) + " " + StdRandom.uniform(l, h));
        }
        file.close();
    }

    public static void main(String[] args) {
//        generateRandomNumbers(10, 0, 1);
        int N = Integer.parseInt(args[0]);
        // 读取标准输入，创建 N 个间隔
        // 使用 Intellij 调试时无法重定向输入流，所以使用文件来模拟
        In stdIn = new In("data/randomDoublePairs.txt");
        Interval1D[] intervals = new Interval1D[N];
        for (int i = 0; i < N; i++) {
            double a = stdIn.readDouble();
            double b = stdIn.readDouble();
            double min = a <= b ? a : b;
            double max = a <= b ? b : a;
            intervals[i] = new Interval1D(min, max);
        }
        // 遍历查找相交的间隔
        for (int i = 0; i < intervals.length - 1; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i].intersects(intervals[j]))
                    StdOut.printf("%s 与 %s 相交\n", intervals[i], intervals[j]);
            }
        }
    }

}
