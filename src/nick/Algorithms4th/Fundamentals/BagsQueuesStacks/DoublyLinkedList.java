package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import java.util.Iterator;

public class DoublyLinkedList<Item> {

    // 双向结点
    private class DoubleNode {
        Item item;
        DoubleNode previous;
        DoubleNode next;
    }
    private DoubleNode first;
    private DoubleNode last;
    private int N = 0;

    public void insertAtHead(Item item) {
        /**
         * 两种情况：
         * 1) 链表为空
         * 2) 链表不为空
         */
        // 1) 链表为空
        if (size() == 0) {
            insertTheFirstOne(item);
            return;
        }
        // 2) 链表不为空
        else {
            // 创建新结点，将 旧的首结点 和 新的首结点 双向关联
            first.previous = new DoubleNode(); // first -> newFirst
            first.previous.next = first; // newFirst -> first
            // 链表首结点位置前移
            first = first.previous;
        }
        N++;
    }

    public void insertAtTail(Item item) {
        /**
         * 两种情况：
         * 1) 链表为空
         * 2) 链表不为空
         */
        // 1) 链表为空
        if (size() == 0) {
            insertTheFirstOne(item);
            return;
        }
        // 2) 链表不为空
        else {
            // 创建新结点，将 旧的尾结点 和 新的尾结点 双向关联
            last.next = new DoubleNode(); // last -> newLast
            last.next.previous = last; // newLast -> last
            // 链表尾结点位置后移
            last = last.next;
        }
        N++;
    }

    /**
     * 链表为空时插入第一个元素的方法
     * @param item
     */
    private void insertTheFirstOne(Item item) {
        if (size() != 0) throw new RuntimeException("List is not empty.");
        first = new DoubleNode();
        first.item = item;
        last = first;
        N++;
    }

    public Item deleteFromHead() {
        /**
         * 三种情况
         * 1) 链表为空
         * 2) 链表只有一个结点
         * 3) 链表结点数 > 1
         */
        // 1) 链表为空
        if (size() == 0) throw new RuntimeException("Can't delete from an empty list.");
        // 2) 链表只有一个结点
        if (size() == 1) return deleteTheLastOne();
        // 3) 链表结点数 > 1
        Item item = first.item;
        // first 后移
        first = first.next;
        // 解除双向关联
        first.previous.next = null;
        first.previous = null;
        N--;
        return item;
    }

    public Item deleteFromTail() {
        /**
         * 三种情况
         * 1) 链表为空
         * 2) 链表只有一个结点
         * 3) 链表结点数 > 1
         */
        // 1) 链表为空
        if (size() == 0) throw new RuntimeException("Can't delete from an empty list.");
        // 2) 链表只有一个结点
        if (size() == 1) return deleteTheLastOne();
        // 3) 链表结点数 > 1
        Item item = last.item;
        // last 前移
        last = last.previous;
        // 解除双向关联
        last.next.previous = null;
        last.next = null;
        N--;
        return item;
    }

    private Item deleteTheLastOne() {
        if (size() != 1) throw new RuntimeException("The number of elements in this list is not 1.");
        Item item = first.item;
        first = null;
        last = null;
        N--;
        return item;
    }

    public void insertBefore(DoubleNode x, Item item) {
        /**
         * 三种情况：
         * 1) x 为空
         * 2) x 为首结点
         * 3) x 为其他结点
         */
        // 1) x 为空
        if (x == null) throw new NullPointerException("The node which new item will be insert before is null.");
        // 2) x 为首结点
        if (x == first) {
            insertAtHead(item);
            return;
        }
        // 3) x 为其他结点
        DoubleNode newNode = new DoubleNode();
        // 赋值
        newNode.item = item;
        // 将新结点和前后结点双向关联
        newNode.next = x;
        newNode.previous = x.previous;
        x.previous.next = newNode;
        x.previous = newNode;
        N++;
    }

    public void insertAfter(DoubleNode x, Item item) {
        /**
         * 三种情况：
         * 1) x 为空
         * 2) x 为尾结点
         * 3) x 为其他结点
         */
        // 1) x 为空
        if (x == null) throw new NullPointerException("The node which new item will be insert after is null.");
        // 2) x 为尾结点
        if (x == last) {
            insertAtTail(item);
            return;
        }
        // 3) x 为其他结点
        DoubleNode newNode = new DoubleNode();
        // 赋值
        newNode.item = item;
        // 将新结点和前后结点双向关联
        newNode.previous = x;
        newNode.next = x.next;
        x.next.previous = newNode;
        x.next = newNode;
        N++;
    }

    public Item delete(int k) {
        /**
         * 五种情况：
         * 1) 索引越界
         * 2) 链表中只有一个结点
         * 3) 链表中结点数 > 1，x 为首结点
         * 4) 链表中结点数 > 1，x 为尾结点
         * 5) 链表中结点数 > 2，x 为中间结点
         */
        // 1) 索引越界
        if (k < 1 || k > size()) throw new RuntimeException("Index out of range.");
        // 2) 链表中只有一个结点
        if (size() == 1) return deleteTheLastOne();
        // 3) 链表中结点数 > 1，x 为首结点
        if (k == 1) return deleteFromHead();
        // 4) 链表中结点数 > 1，x 为尾结点
        if (k == size()) return deleteFromTail();
        // 5) 链表中结点数 > 2，x 为中间结点
        // 判断从表首还是表尾进行查找
        DoubleNode current;
        if (k < size()/2) {
            // 正向查找该结点，后移 k - 1 次
            current = first;
            for (int i = 0; i < k - 1; i++) {
                current = current.next;
            }
        } else {
            // 反向查找该结点，前移 N - k 次
            current = last;
            for (int i = 0; i < N - k; i++) {
                current = current.previous;
            }
        }
        return delete(current);
    }

    public Item delete(DoubleNode x) {
        /**
         * 五种情况：
         * 1) x 为空
         * 2) 链表中只有一个结点
         * 3) 链表中结点数 > 1，x 为首结点
         * 4) 链表中结点数 > 1，x 为尾结点
         * 5) 链表中结点数 > 2，x 为中间结点
         */
        // 1) x 为空
        if (x == null) throw new NullPointerException("The node which will be delete is null.");
        // 2) 链表中只有一个结点
        if (size() == 1) return deleteTheLastOne();
        // 3) 链表中结点数 > 1，x 为首结点
        if (x == first) return deleteFromHead();
        // 4) 链表中结点数 > 1，x 为尾结点
        if (x == last) return deleteFromTail();
        // 5) 链表中结点数 > 2，x 为中间结点
        // 将 x 前后的结点 关联起来
        x.previous.next = x.next;
        x.next.previous = x.previous;
        // 解除 x 对其前后结点的引用
        x.previous = null;
        x.next = null;
        N--;
        return x.item;
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


        private DoubleNode pointer = first;

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

    public static void main(String[] args) {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.insertAtHead("1");
        list.insertAtHead("2");
        list.deleteFromHead();
        list.deleteFromHead();
        list.insertAtTail("1");
        list.insertAtTail("2");
        list.deleteFromTail();
        list.deleteFromTail();
        list.insertAtHead("1");
        list.insertAtHead("2");
        list.delete(2);
    }

}
