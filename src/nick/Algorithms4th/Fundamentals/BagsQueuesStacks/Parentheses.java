package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Parentheses {

    private static boolean isComplete(String filename) {
        Stack<Character> stack = new Stack<>();
        In in = new In(filename);
        while (!in.isEmpty()) {
            char s = in.readChar();
            switch (s) {
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                default:
                    stack.push(s);
                    break;
            }
        }
        if (!stack.isEmpty()) return false;
        return true;
    }

    public static void main(String[] args) {
        StdOut.println(isComplete("data/parentheses.txt") ? "true" : "false");
    }

}
