package nick.Algorithms4th.Fundamentals.UnionFind;

import edu.princeton.cs.algs4.StdOut;

public class DoublingRatio extends nick.Algorithms4th.test.DoublingRatio{

    @Override
    protected int[] getScale(int N) {
        int r = 2;
        return new int[]{N, r*N};
    }

    @Override
    protected void printColumns() {
        StdOut.printf("%-16s %10s %10s %5s %9s %8s\n", "N", "time(s)", "count", "ratio", "maxHeight", "avgDepth");
    }

    @Override
    protected void printResult(int N, double[] result) {
        double time = result[0];
        int count = (int) result[1];
        int maxHeight = (int) result[2];
        double avgDepth = result[3];
        // 规模 N，花费的时间，分量个数，与上一次时间之比，最大高度，平均深度
        StdOut.printf("%-16d %10.2f %10d %5.1f %9d %8.2f\n", N, time, count, time/prev, maxHeight, avgDepth);
        prev = time;
    }

    public static void main(String[] args) {
        DoublingRatio test = new DoublingRatio();
        test.start(args[0]);
    }

}
