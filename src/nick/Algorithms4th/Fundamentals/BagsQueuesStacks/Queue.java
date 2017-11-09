package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    // 链表结构的基本组件：结点
    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private Node last;
    private int N = 0;

    public Queue() {}

    public Queue(Queue<Item> q) {
        for (Item item:
             q) {
            enqueue(item);
        }
    }

    public void enqueue(Item item) {
        // 向队尾添加一个新结点
        Node oldLast = last;
        last = new Node();
        last.item = item;
        // 当队列为空时，最后一个元素就是第一个元素，此时需要更新 first 的引用
        // 且此时 oldLast 为 null，因而也就不需要链接 oldLast 和 last 了
        if (isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("This Queue is empty.");
        // 从队首取出一个结点，然后更新 first 的引用
        Item item = first.item;
        first = first.next;
        N--;
        // 当队列中最后一个元素也被取出后，应该同时更新 last 的引用，将其置为 null
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node pointer = first;

        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        @Override
        public Item next() {
            Item item = pointer.item;
            pointer = pointer.next;
            return item;
        }
    }

    private static void testCopy() {
        Queue<String> q = new Queue<>();
        for (int i = 0; i < 8; i++) {
            q.enqueue(i + "");
        }
        Queue<String> r = new Queue<>(q);
        q.dequeue();
        StdOut.println("The elements of Queue q: ");
        for (String s:
                q) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("The elements of Queue r: ");
        for (String s:
                r) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        In in = new In("data/tobe.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (s.equals("-") && !queue.isEmpty()) StdOut.println(queue.dequeue());
            else queue.enqueue(s);
        }
        StdOut.println();
//        testCopy();
    }

}
