package nick.Algorithms4th.Fundamentals.AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import nick.Algorithms4th.myTools.Counter;

public class TwoSumFaster {

    public static int bruteForceTwoSum(int[] a) {
        int N = a.length;
        int count = 0;
        int operations = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < a.length; j++) {
                operations++;
                if (a[i] + a[j] == 0) count++;
            }
        }
        return operations;
    }

    public static void testBruteForceTwoSum() {
        StdOut.printf("%-16s %10s %10s\n", "N", "operations", "op/N");
        for (int i = 1; i < 9; i *= 2) {
            In file = new In("data/1.4/"+i+"Kints.txt");
            int[] a = file.readAllInts();
            int operations = bruteForceTwoSum(a);
            // 规模，操作次数
            StdOut.printf("%-16d %10d %10.2f\n", i*1000, operations, operations/(i*1000.0));
        }
    }

    /**
     * 计算有序数组中和为 0 的整数对的数量，线性级别，假设数组中的元素不重复
     * @param a 有序数组
     * @return 和为 0 的整数对的数量
     */
    public static int twoSum(int[] a) {
        // 动态调整的范围来进行二分查找
        int hi = a.length - 1;
        int count = 0;
        Counter counter = new Counter("operations");
        for (int i = 0; i <= hi; i++) {
            // 在 > i+1 的范围里查找，因为 <= i 的是重复的组合
            int index = search(-a[i], a, i+1, hi, counter);
            if (index != -1) {
                // 如果找到了，则下一次查找的右边界应为 index-1
                // 因为 a[i+1] > a[i]，所以 -a[i+1] < -a[i]，即 -a[i+1] 不会在 [hi, a.length] 内出现
                count++;
                hi = index - 1;
            }
        }
        return counter.tally();
    }

    private static int search(int key, int[] a, int lo, int hi, Counter counter) {
        int mid = 0;
        while (lo <= hi) {
            counter.increment();
            mid = (lo + hi) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else                   return mid;
        }
        return -1;
    }

    private static void testTwoSum() {
        StdOut.printf("%-16s %10s %10s\n", "N", "operations", "op/N");
        for (int i = 1; i < 9; i *= 2) {
            In file = new In("data/1.4/"+i+"Kints.txt");
            int[] a = file.readAllInts();
            int operations = twoSum(a);
            // 规模，操作次数，op/N
            StdOut.printf("%-16d %10d %10.2f\n", i*1000, operations, operations/(i*1000.0));
        }
    }

    public static void main(String[] args) {
        testBruteForceTwoSum();
        testTwoSum();
    }

}
