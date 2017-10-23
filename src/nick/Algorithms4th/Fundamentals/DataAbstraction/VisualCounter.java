package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.StdRandom;

public class VisualCounter {

    private int count;
    private int maxCount;
    private int N;
    private int maxN;
    private Draw draw = new Draw();

    /**
     * 可视化计数器，每次计数器变化后会显示计数器的值
     * @param N 可进行的最大操作次数
     * @param max 计数器的最大绝对值
     */
    public VisualCounter(int N, int max) {
        maxN = N;
        maxCount = max;
        draw.setXscale(0, N);
        draw.setYscale(-max, max);
        draw.setPenRadius(0.005);
    }

    public void increment() {
        if ( N <= maxN && count <= maxCount && count >= -maxCount) {
            count++;
            N++;
            draw();
        }
    }

    public void decrease() {
        if ( N <= maxN && count <= maxCount && count >= -maxCount) {
            count--;
            N++;
            draw();
        }
    }

    private void draw() {
        draw.point(N, count);
    }

    public int tally() {
        return count;
    }

    public String toString() {
        return count + "";
    }

    public static void main(String[] args) {
        int N = 100;
        int max = 100;
        VisualCounter visualCounter = new VisualCounter(N, max);
        for (int i = 0; i < N; i++) {
            if (StdRandom.uniform() > 0.5) visualCounter.increment();
            else                           visualCounter.decrease();
        }
    }

}
