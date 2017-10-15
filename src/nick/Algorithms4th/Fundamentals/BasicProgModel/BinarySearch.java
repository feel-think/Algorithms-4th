package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Date;

public class BinarySearch {

    /**
     * 在所给的数组中查找指定 key 的索引值，若 key 不存在，则返回 -1
     */
    public static int rank(int key, int[] a, boolean display) {
        if (display) StdOut.println("search " + key);
        // 选取中位数与要查找的元素进行比较，并根据比较的结果决定是继续在左子集还是右子集中查找，或是直接返回
        int lo = 0;
        int hi = a.length - 1;
        // 第一步在整个数组中查找
        return rank(key, a, lo, hi, 0, display);
    }

    private static int rank(int key, int[] a, int lo, int hi, int depth, boolean display) {
        // 计算缩进
        String indent = "";
        for (int i = 0; i < depth; i++)
            indent += "  ";
        // 输出参数 lo 和 hi
        if (display) StdOut.printf("%slo: %d, hi: %d.", indent, lo, hi);
        // 当 key 不在 a 中时，最后会出现 lo < hi 的情况
        if (lo > hi) {
            if (display) StdOut.println();
            return -1;
        }
        // 计算中位数的索引
        int mid = (lo + hi) / 2;
        // 输出 a[mid]
        if (display) StdOut.printf(" a[%d] = %d\n", mid, a[mid]);
        if      (key == a[mid]) return mid;
        else if (key > a[mid])  return rank(key, a, mid+1, hi, depth + 1, display);
        else                    return rank(key, a, lo, mid - 1, depth + 1, display);
    }

    /**
     * 删除有序数组中重复的元素
     * @param a 有序数组
     * @return 去重后的有序数组
     */
    public static int[] unique(int[] a) {
        /**
         * 有几种思路：
         * 1.遍历数组，记录重复元素的索引；再次遍历，将旧数组中的值复制到新数组中，跳过重复值。
         *   问题：用于记录重复元素索引的数组，不知道该创建为多大，保险起见只能设为 length-1，可能会造成较大的空间浪费（空间复杂度）
         * 2.遍历数组，遇到重复元素时，将该元素后面的部分整体向前移一位；然后将旧数组中的值复制到新数组中，忽略尾部的 k 个元素
         *   问题：初次遍历期间，将数组元素整体左移所需的操作可能会很多，造成较高的时间复杂度
         */

        /**
         * 从第二个元素开始遍历数组，将该元素与前一个元素比较，遇到重复元素时，删除该元素，并将后面的所有元素整体左移一位。
         * 遍历完成后，创建一个新的数组，将旧数组中的前 N-k 个元素拷贝至新数组中
         */
        // 用于保存重复元素个数的变量
        int counter = 0;
        // 遍历截止处
        int end = a.length;
        // 遍历数组 a
        for (int i = 1; i < end; i++) {
            if (a[i-1] == a[i]) {
                counter++;
                /**
                 * 左移数组时，会导致数组尾部多出一次重复的情况，从而导致 counter 多出一次计数。
                 * 动态修正数组结尾的索引值，可以解决这个问题
                 */
                end--;
                // 左移右边的元素
                for (int j = i; j < a.length-1; j++) {
                    a[j] = a[j+1];
                }
            }
        }
        // 新数组
        int[] b = new int[a.length-counter];
        // 复制元素
        for (int i = 0; i < b.length; i++)
            b[i] = a[i];
        return b;
    }

    private static void mainTest(String[] args) {
        // 白名单数组通过文件获取，文件名作为程序参数从命令行传入
        // 获取白名单数组
        int[] whitelist = new In(args[0]).readAllInts();
        // 对白名单数组进行排序
        Arrays.sort(whitelist);
        // 对白名单进行去重
//        StdOut.println(Arrays.toString(whitelist));
//        whitelist = unique(whitelist);
//        StdOut.println(Arrays.toString(whitelist));
        // 从标准输入读入 key，进行查找
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            // 如果第二个命令行参数为 "+"，则打印出不在白名单上的值
            if (args[1].equals("+")) {
                if (rank(key, whitelist, false) == -1)
                    StdOut.println(key + "\n");
            }
            // 如果第二个命令行参数为 "-"，则打印出在白名单上的值
            else if (args[1].equals("-")) {
                if (rank(key, whitelist, false) != -1)
                    StdOut.println(key + "\n");
            }
        }
    }

    /**
     * 在 100 万大小的数组中查找 100 万次，共需 7 秒
     * 与此对比，使用暴力搜索，需 270 秒
     */
    private static void speedTest(String[] args) {
        // 获取白名单数组
        int[] whitelist = new In(args[0]).readAllInts();
        // 记录开始时间
        long start = new Date().getTime();
        // 对白名单数组进行排序
        Arrays.sort(whitelist);
        int counter = 0;
        // 从标准输入读入 key，进行查找
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist, false) == -1) {
//                StdOut.println(key + "\n");
            }
            counter++;
            if (counter % 1000 == 0)
                StdOut.printf("have running %d ms. have find %d elements\n",
                        (new Date().getTime() - start), counter);
        }
    }

    /**
     * 查找数组中小于该 key 的元素数量
     */
    public static int countOfLess(int key, int[] a) {
        // 首先找到该元素
        int index = rank(key, a, true);
        // 然后向左搜索，直至找到小于 key 的元素
        while (a[index-1] == a[index])
            index--;
        // countOfEqual = (index-1)(小于 key 的最大元素的索引) + 1 = index
        return index;
    }

    /**
     * 查找数组等于该 key 的元素数量
     */
    public static int countOfEqual(int key, int[] a) {
        // 找到该元素
        int index = rank(key, a, true);
        // 向左搜索，找到左边界
        int leftIndex = index;
        while (a[leftIndex-1] == a[leftIndex])
            leftIndex--;
        // 向右搜索，找到右边界
        int rightIndex = index;
        while (a[rightIndex] == a[rightIndex+1])
            rightIndex++;
        // countOfEqual = rightIndex - leftIndex + 1
        return rightIndex - leftIndex + 1;
    }

    private static void countTest(String[] args) {
        // 白名单数组通过文件获取，文件名作为程序参数从命令行传入
        // 获取白名单数组
        int[] whitelist = new In(args[0]).readAllInts();
        // 对白名单数组进行排序
        Arrays.sort(whitelist);
        StdOut.println(Arrays.toString(whitelist));
        // 查找小于 key 的元素数量
        StdOut.printf("the number of elements which less than 48: %d\n", countOfLess(48, whitelist));
        // 查找等于 key 的元素数量
        StdOut.printf("the number of elements which equal to 48: %d\n", countOfEqual(48, whitelist));
    }

    /**
     * 命令： java -cp .;C:\Programming\Libraries\algs4.jar BinarySearch nick/algorithm/test/tinyW.txt + < nick/algorithm/test/tinyT.txt
     */
    public static void main(String[] args) {
        speedTest(args);
    }

}
