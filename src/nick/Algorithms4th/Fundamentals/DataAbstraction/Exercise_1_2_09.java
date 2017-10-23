package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import nick.Algorithms4th.myTools.Counter;

import java.util.Arrays;

public class Exercise_1_2_09 {

    public static int rank(int key, int[] a, Counter counter) {
        int lo = 0;
        int hi = a.length - 1;
        return rank(key, a, lo, hi, counter);
    }

    private static int rank(int key, int[] a, int lo, int hi, Counter counter) {
        if (lo > hi) return -1;
        counter.increment();
        int mid = (lo + hi) / 2;
        if      (key == a[mid]) return mid;
        else if (key > a[mid])  return rank(key, a, mid+1, hi, counter);
        else                    return rank(key, a, lo, mid - 1, counter);
    }

    public static void main(String[] args) {
        int[] whitelist = new In(args[0]).readAllInts();
        Arrays.sort(whitelist);
        In stdIn = new In("data/tinyT.txt");
        while (!stdIn.isEmpty()) {
            int key = stdIn.readInt();
            Counter counter = new Counter("finds");
            rank(key, whitelist, counter);
            /**
             * 采用二分查找，递归深度每加深一层，查找的范围缩小一半，即范围除以 2
             * 所以最多需要 (int) log2(N) 次查找即可确定 key 在不在一个数组中
             */
            StdOut.printf("查找键 %d，共进行了 %s\n", key, counter);
        }
    }

}
