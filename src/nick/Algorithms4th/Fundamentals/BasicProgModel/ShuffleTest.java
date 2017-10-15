package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ShuffleTest {

    private static int[] initializeArray(int[] a) {
        // 初始化数组
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        return a;
    }

    private static void terribleShuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniform(n-i);     // between i and n-1
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void main(String[] args) {
        // 所需打乱的数组的长度
        int M = Integer.parseInt(args[0]);
        // 打乱的次数
        int N = Integer.parseInt(args[1]);
        int[] a = new int[M];
        // 打乱 N 次，记录打乱后元素的分布情况，其中 stats[i][j] 为 i 在打乱后落到位置 j 的次数
        int[][] stats = new int[M][M];
        for (int i = 0; i < N; i++) {
            // 初始化
            initializeArray(a);
            // 乱序
            terribleShuffle(a);
            // 统计
            for (int j = 0; j < a.length; j++) {
                // stats[i][j] 为 i 在打乱后落到位置 j 的次数
                stats[a[j]][j]++;
            }
        }
        Arrays.deepToString(stats);
    }
}
