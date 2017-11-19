package nick.Algorithms4th.Fundamentals.AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import nick.Algorithms4th.myTools.Stopwatch;

import java.util.Arrays;

public class TwoSumFast {

    private static int twoSum(int[] a) {
        int N = a.length, count = 0;
        for (int i = 0; i < N; i++) {
            if (search(-a[i], a) > i) count++;
        }
        return count;
    }

    private static int search(int key, int[] a) {
        int lo = 0, mid = 0, hi = a.length - 1;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if      (a[mid] < key) lo = mid + 1;
            else if (key < a[mid]) hi = mid - 1;
            else                   return mid;
        }
        return -1;
    }

    public static void main(String[] args) {

    }

}
