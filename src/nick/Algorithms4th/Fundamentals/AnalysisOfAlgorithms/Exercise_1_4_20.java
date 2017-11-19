package nick.Algorithms4th.Fundamentals.AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_4_20 {

    /**
     * 在一个所有元素都不相等的双调数组中查找指定值
     * @param key 指定元素
     * @param a 双调数组
     * @return 元素索引
     */
    private static int search(int key, int[] a) {
        /**
         * 两步：
         * 1.查找双调数组中最大的元素，将数组分为左右两个部分
         * 2.在两个部分分别应用二分搜索
         */
        // 1.查找双调数组中最大的元素
        int max = findMax(a);
        int result = searchLeft(key, a, max);
        if (result != -1) return result;
        result = searchRight(key, a, max + 1);
        if (result != -1) return result;
        return -1;
    }

    private static int findMax(int[] a) {
        /**
         * 三种情况：
         * 1) a[mid-1] < a[mid] < a[mid+1]
         * 2) a[mid-1] > a[mid] > a[mid+1]
         * 3) a[mid-1] < a[mid] > a[mid+1]
         */
        int max = a.length - 1, left = 0, right = max, mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            // 范围缩小至左边
            if      (a[mid-1] > a[mid]) right = mid - 1;
            // 范围缩小至右边
            else if (a[mid] < a[mid+1]) left = mid + 1;
            // 找到了最大值
            else                        return mid;
        }
        return -1;
    }

    private static int searchLeft(int key, int[] a, int hi) {
        int lo = 0, mid;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if      (a[mid] < key) lo = mid + 1;
            else if (key < a[mid]) hi = mid - 1;
            else                   return mid;
        }
        return -1;
    }

    private static int searchRight(int key, int[] a, int lo) {
        int hi = a.length - 1, mid;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if      (key > a[mid]) hi = mid - 1;
            else if (a[mid] > key) lo = mid + 1;
            else                   return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,3,5,7,9,11,6,4,2};
        StdOut.println(findMax(a));
        StdOut.println(search(5, a));
        StdOut.println(search(12, a));
    }

}
