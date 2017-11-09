package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

    // 链表结构的基本组件：结点
    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private int N = 0;

    public void add(Item i) {
        // 将旧链表的引用保存起来，新创建一个结点，并将链表首部指向这个结点，最后将新的首部元素和旧的链表链接起来
        Node oldFirst = first;
        Node first = new Node();
        first.item = i;
        first.next = oldFirst;
        // 链表元素数 +1
        N++;
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

}
