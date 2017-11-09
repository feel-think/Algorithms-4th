package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_3_7 {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        In in = new In("data/number_stack.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (s.equals("-") && !stack.isEmpty()) StdOut.print("-" + stack.pop() + " ");
            else {
                stack.push(s);
                StdOut.print("+" + stack.peek() + " ");
            }
        }
        StdOut.println();
    }

}
