package nick.Algorithms4th.Fundamentals.AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import nick.Algorithms4th.myTools.Stopwatch;

public class ThreeSum {

    public static int count(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        In file = new In(args[0]);
        int[] a = file.readAllInts();
        Stopwatch watch = new Stopwatch();
        StdOut.printf("%s 中有 %d 组和为 0 的三元组\n", args[0], count(a));
        StdOut.printf("运行耗时约 %f 秒\n", watch.elapsedTime());
    }

}
