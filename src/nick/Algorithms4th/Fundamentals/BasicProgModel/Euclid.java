package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

public class Euclid {

    public static int gcd(int p, int q) {
        StdOut.println("p: " + p + ", q: " + q);
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        // 从命令行获取整数 p 和 q
        int p = Integer.parseInt(args[0]);
        int q = Integer.parseInt(args[1]);
        StdOut.println(gcd(p, q));
        StdOut.println();
    }

}
