package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Exercise_1_2_15 {

    public static int[] readInts(String name) {
        In in = new In(name);
        String s = in.readAll();
        String[] strings = s.split(" ");
        int[] ints = new int[strings.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }

    public static void main(String[] args) {
        StdOut.println(Arrays.toString(readInts("1 2 3 4")));
    }

}
