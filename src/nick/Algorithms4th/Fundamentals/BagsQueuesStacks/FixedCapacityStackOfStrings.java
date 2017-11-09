package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings {

    private String[] stack;
    private int capacity;
    private int pointer = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        if (capacity < 1) throw new RuntimeException("Illegal ResizingArrayStack capacity: " + capacity);
        stack = new String[capacity];
        this.capacity = capacity;
    }

    public void push(String s) {
        if (pointer == capacity) throw new RuntimeException("ResizingArrayStack overflow.");
        stack[pointer++] = s;
    }

    public String pop() {
        if (isEmpty()) throw new RuntimeException("ResizingArrayStack is Empty.");
        return stack[--pointer];
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public int size() {
        return pointer;
    }

    public boolean isFull() {
        return pointer == capacity;
    }

    public static void main(String[] args) {
        // 生成栈
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
        In stdIn = new In("data/tinyW.txt");
        while (!stdIn.isEmpty()) {
            if (stack.isFull()) break;
            stack.push(stdIn.readString());
            StdOut.printf("The size of stack: %d. Is full? : %s\n", stack.size(), stack.isFull());
        }
    }

}
