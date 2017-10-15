package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Date;

public class MergeSort {

    /**
     * 通过拆分数组再合并进行的快速排序算法，需要不断创建额外的数组，比较浪费空间，第一版
     * @param a 待排序的数组
     * @return 已排序的数组
     */
    public static int[] spaceWastedSort(int[] a) {
        // 当数组长度为 1 时，为递归的最简单情况，直接返回原数组即可
        if (a.length == 1) return a;
        // 数组长度大于 1 时，先将数组拆分，分别对两个子数组排序，再将排序后的两个子数组合并
        int mid = a.length / 2;
        int[] left = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);
        return spaceWastedMerge(spaceWastedSort(left), spaceWastedSort(right));
    }

    /**
     * 将两个有序的数组合并为一个新的数组
     * @param a 有序数组 a
     * @param b 有序数组 b
     * @return a 和 b 合并后的新的有序数组
     */
    public static int[] spaceWastedMerge(int[] a, int[] b) {
        /**
         * 以弹出堆栈顶部元素的形式，分别从两个子数组中取出首部元素，将较小的元素放入新数组中。
         * 直至两个子数组中的元素都已检查完毕，此时合并完成且新数组也是有序的
         */
        int[] merge = new int[a.length + b.length];
        int aPointer = 0, bPointer = 0, mergePointer = 0;
        // 只要两个数组中还有未检查的元素，就继续进行合并操作
        while (aPointer < a.length || bPointer < b.length) {
            // left 数组已检查完时，直接将 b 数组剩余的元素全部拷贝到 merge 中
            if (aPointer == a.length) {
                while (bPointer < b.length)
                    merge[mergePointer++] = b[bPointer++];
            }
            // b 数组已检查完时，直接将 left 数组剩余的元素全部拷贝到 merge 中
            else if (bPointer == b.length) {
                while (aPointer < a.length)
                    merge[mergePointer++] = a[aPointer++];
            }
            // 否则将两个数组中较小的元素拷贝到 merge 中
            else {
                merge[mergePointer++] = (a[aPointer] <= b[bPointer]) ?
                        a[aPointer++] : b[bPointer++];
            }
        }
        return merge;
    }

    public static void spaceFreeSort(int[] a) {
        spaceFreeSort(a, 0, a.length-1);
    }

    public static void spaceFreeSort(int[] a, int lo, int hi) {
        /**
         * 尽量减少新数组的创建，通过两个边界值指定方法所处理的数组区间
         * 将数组 a 中索引 lo 至 hi 部分的元素进行排序
         */
        // 当指定的区间中仅包含 1 个元素时，为递归的最简单情况，直接返回即可
        if (lo == hi) return;
        // 当指定的区间包含的元素数量大于 1 时，将该区间拆分为两个子区间，
        // 分别对两个子区间内的元素进行排序，排序完成后再将两个已经有序的子区间整理成一个大的有序区间
        int mid = (lo + hi) / 2;
        spaceFreeSort(a, lo, mid);
        spaceFreeSort(a, mid + 1, hi);
        spaceFreeMerge(a, lo, mid, hi);
    }

    public static void spaceFreeMerge(int[] a, int lo, int mid, int hi) {
        /**
         * 直接 in-place 地进行合并无法实现，仍使用中间数组的形式进行缓存
         */
        int[] merge = new int[hi - lo + 1];
        int leftPointer = lo, rightPointer = mid + 1, mergePointer = 0;
        // 只要两个区间中还有未检查的元素，就继续进行合并操作
        while (leftPointer <= mid || rightPointer <= hi) {
            // left 区间已检查完时，直接将 right 区间剩余的元素全部拷贝到 merge 中
            if (leftPointer > mid) {
                while (rightPointer <= hi)
                    merge[mergePointer++] = a[rightPointer++];
            }
            // right 区间已检查完时，直接将 left 区间剩余的元素全部拷贝到 merge 中
            else if (rightPointer > hi) {
                while (leftPointer <= mid)
                    merge[mergePointer++] = a[leftPointer++];
            }
            // 否则将两个数组中较小的元素拷贝到 merge 中
            else {
                merge[mergePointer++] = (a[leftPointer] <= a[rightPointer]) ?
                        a[leftPointer++] : a[rightPointer++];
            }
        }
        // 将已排序好的元素序列复制回原数组中
        for (int i = 0; i < merge.length; i++) {
            a[lo + i] = merge[i];
        }
    }

    public static void main(String[] args) {
        // 获取白名单数组
        int[] whitelist = new In(args[0]).readAllInts();
        int[] whitelistCopy = Arrays.copyOf(whitelist, whitelist.length);
        // 测试第一版的排序算法的性能
        long start = new Date().getTime();
        spaceWastedSort(whitelist);
        StdOut.printf("测试第一版归并排序的性能，对 100 万个元素进行排序，耗时 %d ms.\n",
                (new Date().getTime() - start));
        // 测试第二版的排序算法性能
        start = new Date().getTime();
        spaceFreeSort(whitelistCopy);
        StdOut.printf("测试第二版归并排序的性能，对 100 万个元素进行排序，耗时 %d ms.\n",
                (new Date().getTime() - start));
    }

}
