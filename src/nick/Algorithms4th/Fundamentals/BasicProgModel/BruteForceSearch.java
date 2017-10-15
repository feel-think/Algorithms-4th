package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Date;

public class BruteForceSearch {

    public static int rank(int key, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (key == a[i]) return i;
        return -1;
    }

    /**
     * 在 100 万大小的数组中查找 100 万次，共需 270 秒
     */
    private static void speedTest(String[] args) {
        // 获取白名单数组
        int[] whitelist = new In(args[0]).readAllInts();
        // 记录开始时间
        long start = new Date().getTime();
        int counter = 0;
        // 从标准输入读入 key，进行查找
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1) {
//                StdOut.println(key + "\n");
            }
            counter++;
            if (counter % 1000 == 0)
                StdOut.printf("have running %d ms. have find %d elements\n",
                        (new Date().getTime() - start), counter);
        }
    }

    public static void main(String[] args) {
        speedTest(args);
    }

}
