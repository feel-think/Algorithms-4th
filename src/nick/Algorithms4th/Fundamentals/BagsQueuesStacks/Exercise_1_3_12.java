package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_3_12 {

    public static Stack<String> copy(Stack<String> stack) {
        Stack<String> tempStack = new Stack<>();
        for (String s:
             stack) {
            tempStack.push(s);
        }
        Stack<String> newStack = new Stack<>();
        for (String s:
                tempStack) {
            newStack.push(s);
        }
        return newStack;
    }

    public static void main(String[] args) {
        String[] strings = "1 2 3 4 5 6 7 8 9".split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < strings.length; i++) {
            stack.push(strings[i]);
        }
        Stack<String> newStack = copy(stack);
        StdOut.println("Old Stack:");
        for (String s:
             stack) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("New Stack:");
        for (String s:
                newStack) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }

}
