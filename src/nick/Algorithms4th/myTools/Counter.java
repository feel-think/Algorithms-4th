package nick.Algorithms4th.myTools;

import edu.princeton.cs.algs4.StdOut;

public class Counter {

    private String name;
    private int count = 0;

    public Counter(String id) {
        name = id;
    }

    public void increment() {
        count++;
    }

    public int tally() {
        return count;
    }

    public String toString() {
        return count + " " + name;
    }

    public static void main(String[] args) {
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");

        heads.increment();
        heads.increment();
        tails.increment();

        StdOut.println(heads.tally() + " heads");
        StdOut.println(heads);
        StdOut.println(tails);
    }

}
