package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class RingQueue<Item> {

    // 链表结构的基本组件：结点
    private class Node {
        Item item;
        Node next;
    }

    private Node last;
    private int N = 0;

    public void enqueue(Item item) {
        // 向队尾添加一个新结点
        Node oldLast = last;
        last = new Node();
        last.item = item;
        // 当队列为空时，需要初始化环形结构
        if (isEmpty()) {
            last.next = last;
        }
        // 队列不为空时，需要维护环形结构
        else {
            last.next = oldLast.next;
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("This Queue is empty.");
        /**
         * 两种情况：
         * 1) 链表中只剩一个结点时
         * 2) 链表中的结点数 > 1 时
         */
        // 从队首取出一个结点
        Item item = last.next.item;
        // 1) 链表中只剩一个结点时，释放对该结点的所有引用即可
        if (size() == 1) {
            last.next = null; // 避免游离
            last = null;
        }
        // 2) 链表中的结点数 > 1 时
        else {
            last.next = last.next.next;
        }
        N--;
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

        private Node pointer = last.next;
        private int k = 1; // 指示当前迭代至第几个结点了

        @Override
        public boolean hasNext() {
            return k <= N;
        }

        @Override
        public Item next() {
            Item item = pointer.item;
            pointer = pointer.next;
            k++;
            return item;
        }
    }

    public static void main(String[] args) {
        RingQueue<String> queue = new RingQueue<>();
        In in = new In("data/tobe.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (s.equals("-") && !queue.isEmpty()) StdOut.println(queue.dequeue());
            else queue.enqueue(s);
        }
    }

}
