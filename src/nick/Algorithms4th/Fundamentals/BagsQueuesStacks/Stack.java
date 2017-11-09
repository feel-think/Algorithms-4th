package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    // 链表结构的基本组件：结点
    private class Node {
        Item item;
        Node next;
    }

    // 用于保存数据的链表
    private Node first;
    // 保存栈的元素数量
    private int N = 0;

    // 记录被操作的次数的变量
    private int operations = 0;

    public Stack() {}

    public Stack(Stack<Item> that) {
        if (that.size() == 0) return;
        // 复制首结点
        this.first = new Node();
        first.item = that.first.item;
        Node thatCurrent = that.first;
        Node thisCurrent = this.first;
        // 复制剩余结点
        while (thatCurrent.next != null) {
            // 创建下一个结点
            thisCurrent.next = new Node();
            // 指针后移
            thatCurrent = thatCurrent.next;
            thisCurrent = thisCurrent.next;
            // 复制元素
            thisCurrent.item = thatCurrent.item;
        }
        // 设置新栈的大小
        this.N = that.N;
    }

    public void push(Item i) {
        // 将旧链表的引用保存起来，新创建一个结点，并将链表首部指向这个结点，最后将新的首部元素和旧的链表链接起来
        Node oldFirst = first;
        first = new Node();
        first.item = i;
        first.next = oldFirst;
        // 链表元素数 +1
        N++;
        operations++;
    }

    public Item pop() {
        // 取出首部元素的值，将首部指向下一个结点
        Item i = first.item;
        first = first.next;
        // 链表元素数 -1
        N--;
        operations++;
        return i;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Item peek() {
        return first.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node pointer = first;

        /**
         * 迭代开始前，保存 Stack 已被操作的次数，在外界每次调用时检查 Stack 对象有没有发生新的操作
         * 通过一个记录了 Stack 被操作的次数的变量实现
         */
        private int stackOps = operations;

        @Override
        public boolean hasNext() {
            if (operations != stackOps) throw new ConcurrentModificationException("The stack has been changed");
            return pointer != null;
        }

        @Override
        public Item next() {
            if (operations != stackOps) throw new ConcurrentModificationException("The stack has been changed");
            Item item = pointer.item;
            pointer = pointer.next;
            return item;
        }
    }

    private static void testCopy() {
        Stack<String> s = new Stack<>();
        for (int i = 0; i < 8; i++) {
            s.push(i + "");
        }
        Stack<String> t = new Stack<>(s);
        s.pop();
        StdOut.println("The elements of Stack s: ");
        for (String i:
                s) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        StdOut.println("The elements of Stack t: ");
        for (String i:
                t) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }

    private static void testFailFast() {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < 8; i++) {
            stack.push(i + "");
        }
        int i = 0;
        for (String s:
                stack) {
            StdOut.print(s + " ");
            i++;
//             if (i == 5) stack.push("in");
        }
        stack.push("out");
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        In in = new In("data/number_stack.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (s.equals("-") && !stack.isEmpty()) StdOut.print(stack.pop() + " ");
            else stack.push(s);
        }
        StdOut.println();

//        testCopy();

        testFailFast();
    }

}
