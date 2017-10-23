package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_2_04 {

    public static void main(String[] args) {
        String string1 = "hello";
        String string2 = string1;
        string1 = "world";
        StdOut.println(string1); // out: world
        StdOut.println(string2); // out: hello
    }

}
