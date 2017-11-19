package nick.Algorithms4th.Fundamentals.AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import nick.Algorithms4th.myTools.Stopwatch;

import java.util.Arrays;

public class Exercise_1_4_8 {

    /**
     * 暴力查找一个数组中相等的整数对的数量
     * @param a 给定的数组
     * @return 相等的整数对的数量
     */
    private static int bruteForceTwoEqual(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (a[i] == a[j]) count++;
            }
        }
        return count;
    }

    /**
     * 对指定的问题规模 N 进行实验，返回算法运行的时间
     * @param N 问题规模
     * @return 算法运行的时间
     */
    private static double bruteForceTimeTrial(int N) {
        // 产生一个长度为 N 的随机整型数组
        int[] a = getRandomArray(N);
        // 进行 n 次实验，取平均时间
        int n = 1;
        double time = 0;
        for (int i = 0; i < n; i++) {
            Stopwatch timer = new Stopwatch();
            bruteForceTwoEqual(a);
            time += timer.elapsedTime();
        }
        return time/n;
    }

    private static int[] getRandomArray(int N) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-100000, 100000);
        return a;
    }

    private static void bruteForceDoublingRatioTest() {
        // 先对 N = 125 进行实验，作为第一个参考结果
        int N = 125;
        double prev = bruteForceTimeTrial(N);
        double time;
        // 然后循环进行倍率实验，输出每次实验花费的时间，以及和上一次实验时间开销之比
        StdOut.printf("%-16s %10s %5s\n", "N", "time(s)", "ratio");
        while (true) {
            N *= 2;
            time = bruteForceTimeTrial(N);
            // 规模 N , 花费的时间 , 与上一次时间之比
            StdOut.printf("%-16d %10.2f %5.1f\n", N, time, time/prev);
            prev = time;
        }
    }

    /**
     * 查找一个有序数组中相等的整数对的数量
     * @param a 给定的有序数组
     * @return 相等的整数对的数量
     */
    private static int twoEqual(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            // 对 a 中的第 i 个元素，查找和其相等的索引值最大的元素
//            StdOut.printf("在数组 %s\n中查找第 %d 个元素 %d\n", Arrays.toString(a), i, a[i]);
            int rightMostEqual = findRightmost(a[i], a, 0, a.length - 1);
            if (rightMostEqual > i) {
//                StdOut.printf("找到了 %d 个相等的元素\n", rightMostEqual - i);
                count += rightMostEqual - i;
            }
        }
        return count;
    }

    /**
     * 使用二分查找，在一个有序数组的指定区间中查找值为 key，且索引最大的元素的索引
     * @param key 要查找的元素
     * @param a 有序数组
     * @param lo 区间左边界
     * @param hi 区间右边界
     * @return 值为 key 且索引最大的元素的索引
     */
    private static int findRightmost(int key, int[] a, int lo, int hi) {
        /**
         * 1.用二分法查找第一个出现的 key
         * 2.若找到了一个 key，还需要判断该元素右边还有没有值和 key 相等的元素。
         *   继续在最近的 [mid+1, hi] 范围中查找索引最大的 key
         * 3.递归终止条件为在某一个区间中找不到 key，或区间范围无效。
         */
        // 1.用二分法查找第一个出现的 key
        int mid = 0;
        boolean found = false;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else                 { found = true; break; }
        }
//        StdOut.printf("一轮查找结束：lo = %d, mid = %d, hi = %d\n", lo, mid, hi);
        // 2.若找到了一个 key，还需要判断该元素右边还有没有值和 key 相等的元素。
        //   继续在的 [mid+1, hi] 范围中查找索引最大的 key
        if (found) {
            int largerIndex = findRightmost(key, a, mid + 1, hi);
            return largerIndex != -1 ? largerIndex : mid;
        }
        return -1;
    }

    private static double timeTrial(int N) {
        // 产生一个长度为 N 的随机整型数组
        int[] a = getRandomArray(N);
        Stopwatch timer = new Stopwatch();
        // 排序数组
        Arrays.sort(a);
        // 进行 n 次实验，取平均时间
        int n = 1;
        double time = 0;
        for (int i = 0; i < n; i++) {
            twoEqual(a);
            time += timer.elapsedTime();
        }
        return time/n;
    }

    private static void testTwoEqual() {
        int[] a = new int[]{1,1,1,1,2,2,3,4,4,5};
        StdOut.printf("\n数组 %s\n中共有 %d 个相等的整数对\n", Arrays.toString(a), twoEqual(a));
    }

    private static void doublingRatioTest() {
        // 先对 N = 125 进行实验，作为第一个参考结果
        int N = 125;
        double prev = timeTrial(N);
        double time;
        // 然后循环进行倍率实验，输出每次实验花费的时间，以及和上一次实验时间开销之比
        StdOut.printf("%-16s %10s %5s\n", "N", "time(s)", "ratio");
        while (true) {
            N *= 2;
            time = timeTrial(N);
            // 规模 N , 花费的时间 , 与上一次时间之比
            StdOut.printf("%-16d %10.2f %5.1f\n", N, time, time/prev);
            prev = time;
        }
    }
    
    public static void main(String[] args) {
//        bruteForceDoublingRatioTest();
//        testTwoEqual();
        doublingRatioTest();
    }

}
