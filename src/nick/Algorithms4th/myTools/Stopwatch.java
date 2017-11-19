package nick.Algorithms4th.myTools;

import edu.princeton.cs.algs4.StdOut;

public class Stopwatch {

    private long startTime = System.currentTimeMillis();

    public double elapsedTime() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
    }

    public static void main(String[] args) {
        Stopwatch watch = new Stopwatch();
        StdOut.println(watch.elapsedTime());
        try {
            Thread.sleep(1000*3);
            StdOut.println(watch.elapsedTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
