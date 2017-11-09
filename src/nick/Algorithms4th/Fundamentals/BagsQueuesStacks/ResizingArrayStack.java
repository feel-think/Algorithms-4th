package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] stack = (Item[]) new Object[1];
    private int pointer = 0;

    public void push(Item i) {
        if (pointer == stack.length) resize(2*stack.length);
        stack[pointer++] = i;
    }

    public Item pop() {
        if (isEmpty()) throw new RuntimeException("ResizingArrayStack is Empty.");
        if (pointer == stack.length/4) resize(stack.length/2);
        Item i = stack[--pointer];
        stack[pointer+1] = null; // 避免对象游离
        return i;
    }

    private void resize(int newCapacity) {
        Item[] temp = (Item[]) new Object[newCapacity];
        for (int i = 0; i < stack.length; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public int size() {
        return pointer;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private int iteratorPointer = pointer;

            @Override
            public boolean hasNext() {
                return iteratorPointer != 0;
            }

            @Override
            public Item next() {
                return stack[--iteratorPointer];
            }
        };
    }

    public static void main(String[] args) {
        // 生成栈
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
        In stdIn = new In("data/tinyW.txt");
        while (!stdIn.isEmpty()) {
            stack.push(stdIn.readString());
        }
        // 遍历
        for (String s:
                stack) {
            StdOut.println(s);
        }
    }

}

