package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Exercise_1_1_15 {

    /**
     * 统计整型数组中数字的频率
     * @param a 需要进行统计的数组
     * @param M 数组中元素的取值范围
     * @return 统计结果数组
     */
    private static int[] histogram(int[] a, int M) {
        StdOut.println("a: " + Arrays.toString(a));
        int[] his = new int[M];
        for (int num : a) {
            his[num] += 1;
        }
        StdOut.println("his: " + Arrays.toString(his));
        return his;
    }

    public static void main(String[] args) {
        int N = 30;
        int range = 10;
        int[] a = new int[N];
        // 随机初始化数组
        for (int i = 0; i < N; i++) {
            a[i] = (int) (StdRandom.uniform() * range);
        }
        histogram(a, range);
    }

}
