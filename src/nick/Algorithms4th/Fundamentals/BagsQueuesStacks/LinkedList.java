package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item> {

    // 链表结构的基本组件：结点
    private class Node {

        Item item;
        Node next;

    }
    private Node first;
    private Node last;
    private int N = 0;

    /**
     * 向队列的尾部添加一个新元素
     * @param item 添加的新元素
     */
    public void add(Item item) {
        // 向队尾添加一个新结点
        Node oldLast = last;
        last = new Node();
        last.item = item;
        // 当队列为空时，最后一个元素就是第一个元素，此时需要更新 first 的引用
        if (isEmpty()) first = last;
        // 当队列不为空时，需要将旧的尾结点指向新的尾结点
        else oldLast.next = last;
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

    /**
     * 此方法用于删除链表中的首结点
     * @return 被删除的元素
     */
    private Item deleteFirst() {
        if (size() == 1) return deleteTheOnlyOneLeft();
        Item item = first.item;
        // 将 first 指向第 2 个结点
        first = first.next;
        N--;
        return item;
    }

    /**
     * 删除单向链表的尾结点，时间复杂度为 O(n)
     * @return 被删除的元素
     */
    public Item deleteLast() {
        if (size() == 0) throw new RuntimeException("Can't delete from an empty list.");
        /**
         * 两种情况:
         * 1) 链表中只剩一个结点;
         * 2) 链表中的结点数 >= 2
         */
        // 1) 当链表中只剩一个结点时，最后一个结点就是首结点，只需将 first 和 last 都置空即可
        if (size() == 1) return deleteTheOnlyOneLeft();
        // 2) 链表结点数大于 1 时
        // 获取所要删除的元素
        Item item = last.item;
        // 寻找尾结点的前一个结点
        Node current = first;
        while (current.next.next != null) current = current.next;
        // 删除指向尾结点的引用
        current.next = null;
        // 更新 last 的引用
        last = current;
        N--;
        return item;
    }

    /**
     * 此方法用于当链表中只有一个结点时，删除最后一个结点
     * @return 被删除的元素
     */
    private Item deleteTheOnlyOneLeft() {
        Item item = last.item;
        first = null;
        last = null;
        N--;
        return item;
    }

    /**
     * 删除单向链表的第 k 个结点，时间复杂度为 O(n)
     * @return 被删除的元素
     */
    public Item delete(int k) {
        if (k < 1 || k > size()) throw new RuntimeException("Out of index.");
        /**
         * 四种情况：
         * 1) 链表中只剩一个结点
         * 2) 链表中的结点数 > 1，且所要删除的是 首结点
         * 3) 链表中的结点数 > 1，且所要删除的是 尾结点
         * 4) 链表中的结点数 > 2，所要删除的是 中间结点
         */
        // 1) 链表中只剩一个结点时
        if (size() == 1) return deleteTheOnlyOneLeft();
        // 2) 链表中的结点数 > 1，且所要删除的是 首结点
        if (k == 1) return deleteFirst();
        // 3) 链表中的结点数 > 1，且所要删除的是 尾结点
        if (k == size()) return deleteLast();
        // 4) 链表中的结点数 > 2，所要删除的是 中间结点
        // 寻找第 k-1 个结点
        Node current = first;
        for (int i = 0; i < k - 2; i++) {
            current = current.next;
        }
        return removeAfter(current);
    }

    /**
     * 遍历链表，查找某个元素值
     * @param key 所要查找的值
     * @return true or false
     */
    public boolean find(Item key) {
        if (size() == 0) return false;
        Node current = first;
        while (true) {
            if (current.item.equals(key)) return true;
            if (current.next == null) return false;
            current = current.next;
        }
    }

    /**
     * 删除指定结点后面的一个结点
     * @param x 指定的结点
     * @return 被删除的结点
     */
    private Item removeAfter(Node x) {
        /**
         * 四种情况：
         * 1) x 为空
         * 2) x 为尾结点
         * 3) x 为尾结点前的一个结点，即所要删除的是尾结点
         * 4) 其他情况
         */
        // 1) x 为空 or 2) x 为尾结点。此时无需进行操作
        if (x == null || x.next == null) return null;
        // 3) x 为尾结点前的一个结点，即所要删除的是尾结点
        if (x.next == last) return deleteLast();
        // 4) 其他情况
        Item item = x.next.item;
        x.next = x.next.next;
        N--;
        return item;
    }

    /**
     * 在指定结点后插入一个新结点
     * @param x 被插入的结点
     * @param n 新结点
     */
    private void insertAfter(Node x, Node n) {
        /**
         * 三种情况：
         * 1) x 或 n 为空
         * 2) x 为 尾结点
         * 3) x 不是尾结点
         */
        // 1) x 或 n 为空
        if (x == null || n == null) return;
        // 2) x 为 尾结点
        if (x == last) {
            x.next = n;
            last = n;
        }
        // 3) x 不是尾结点
        else {
            n.next = x.next;
            x.next = n;
        }
        N++;
    }

    /**
     * 遍历链表，删除所有 item 域和 key 相同的结点
     * @param key 所要删除的值
     * @return 删除的结点数量
     */
    public int remove(Item key) {
        /**
         * 四种情况：
         * 1) 链表为空
         * 2) first 需要被删除
         * 3) last 需要被删除
         * 4) 其他结点需要被删除
         */
        int n = 0;
        // 1) 链表为空时
        if (size() == 0) return n;
        // 2) 判断 first 是否需要被删除
        /**
         * 若 first 结点被删除了，原来的第 2 个结点就成了新的 first 结点
         * 因此还需要继续判断新的 first 和 key 是否相同，直到新的 first 和 key 不同为止
         */
        while (first.item.equals(key)) {
            deleteFirst();
            n++;
        }
        // 3) last 需要被删除 or 4) 其他结点需要被删除
        // 此时 current 的 item 域和 key 已经确定不相同了
        //
        // 流程：
        // ┌─────────────────────────────> ( current 后面有结点吗？) (N) → 结束
        // │
        // │                                              ↓ (Y)
        // │
        // ├── 将 current 指向下一个结点 ← (N) ( current 后面的结点和 key 相同吗？)
        // │
        // │                                              ↓ (Y)
        // │
        // └─────────────────────────────── 删除 current 后面的结点
        //
        Node current = first;
        while (current.next != null) {
            if (current.next.item.equals(key)) {
                removeAfter(current);
                n++;
            } else {
                current = current.next;
            }
        }
        return n;
    }

    public int maxInt() {
        int max = 0;
        if (size() == 0) return max;
        // 手动遍历
        Node current = first;
        while (current != null) {
            // 比较当前结点和最大值的大小
            if ((Integer) current.item > max) max = (Integer) current.item;
            // 指向下一个结点
            current = current.next;
        }
        return max;
    }

    public int maxIntRecursive() {
        if (size() == 0) return 0;
        return maxIntRecursive(first);
    }

    private int maxIntRecursive(Node first) {
        if (first == null) throw new RuntimeException("List is empty.");
        // 最简单的情况
        if (first.next == null) return (Integer) first.item;
        // 寻找一个链表的最大值，可以分解为比较 链表首结点的值 和 链表剩余部分的最大值
        int rightMax = maxIntRecursive(first.next);
        return Math.max((Integer) first.item, rightMax);
    }

    public void reverse() {
        last = first;
        first = reverse(first);
    }

    /**
     * 接受一个链表的首结点，将链表反转
     * @param first 旧链表首结点
     * @return 反转后的新链表的首结点
     */
    private Node reverse(Node first) {
        if (first == null || first.next == null) return first;
        // 保存对第二个结点后的链表的引用
        Node temp = first.next;
        // 将首结点变为尾结点
        first.next = null;
        Node current;
        do {
            // 取出当前需要操作的下一个结点
            current = temp;
            // temp 右移
            temp = temp.next;
            // 将 current 添加到新链表的首部
            current.next = first;
            // 更新新链表的首部位置
            first = current;
        } while (temp != null);
        return first;
    }

    public void reverseRec() {
        last = first;
        first = reverseRec(first);
    }

    /**
     * 反转链表的递归版本
     * @param first 旧链表首结点
     * @return 反转后的新链表的首结点
     */
    private Node reverseRec(Node first) {
        /**
         * 反转一条链表，可以抽象为这样一个过程：
         * 将链表从第二个结点开始的部分反转，再将首结点链接到其尾部
         * 由此就变为了一个递归问题
         */
        // 递归的最简单的情况
        if (first == null || first.next == null) return first;
        // 其余情况
        // 将首结点和链表剩余的部分分离开
        Node oldSecond = first.next;
        first.next = null;
        // 反转子链表
        Node newFirst = reverseRec(oldSecond);
        // 将原来的首结点链接到新的子链表的尾部，此时子链表原来的首结点即是新的尾结点
        oldSecond.next = first;
        return newFirst;
    }

    private static void addElements(LinkedList<String> list, int k) {
        for (int i = 0; i < k; i++) {
            StdOut.println("add " + i + " to the tail of the list");
            list.add(i + "");
        }
    }

    private static void testDeleteLast() {
        LinkedList<String> list = new LinkedList<>();
        int k = 8;
        addElements(list, k);
        for (int i = 0; i < k; i++) {
            StdOut.println("delete " + list.deleteLast() + " from the tail of the list");
        }
    }

    private static void testDelete() {
        LinkedList<String> list = new LinkedList<>();
        int k = 8;
        addElements(list, k);
        StdOut.println("delete the last element " + list.delete(k) + " from the list");
        StdOut.println("delete the #3 element " + list.delete(3) + " from the list");
        StdOut.println("delete the first element " + list.delete(1) + " from the list");
        for (int i = 0; i < k - 3; i++) {
            StdOut.println("delete the first element " + list.delete(1) + " from the list");
        }
    }

    private static void testFind() {
        LinkedList<String> list = new LinkedList<>();
        int k = 8;
        addElements(list, k);
        StdOut.println("2 in this list? : " + list.find(2 + ""));
        StdOut.println("10 in this list? : " + list.find(10 + ""));
    }

    private static void testRemove() {
        LinkedList<String> list = new LinkedList<>();
        String key = "key";
        for (int i = 0; i < 3; i++) {
             list.add(key);
        }
        list.add("1");
        list.add(key);
        list.add("2");
        for (int i = 0; i < 3; i++) {
            list.add(key);
        }
        StdOut.println("remove " + list.remove(key) + " key from list.");
    }

    private static void testMaxInt() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        StdOut.println("max value of list is " + list.maxInt());
    }

    private static void testMaxIntRecursive() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        StdOut.println("max value of list is " + list.maxIntRecursive());
    }

    private static void testReverse() {
        LinkedList<String> list = new LinkedList<>();
        list.reverse();
        for (String s:
                list) {
            StdOut.println(s);
        }
        addElements(list, 1);
        list.reverse();
        for (String s:
                list) {
            StdOut.println("iterate the reverse of the list: " + s);
        }
        list = new LinkedList<>();
        addElements(list, 2);
        list.reverse();
        for (String s:
             list) {
            StdOut.println("iterate the reverse of the list: " + s);
        }
        list = new LinkedList<>();
        addElements(list, 8);
        for (String s:
                list) {
            StdOut.println("normal iterate the list: " + s);
        }
        list.reverse();
        for (String s:
                list) {
            StdOut.println("iterate the reverse of the list: " + s);
        }
    }

    private static void testReverseRec() {
        LinkedList<String> list = new LinkedList<>();
        addElements(list, 8);
        for (String s:
                list) {
            StdOut.println("normal iterate the list: " + s);
        }
        list.reverseRec();
        for (String s:
                list) {
            StdOut.println("iterate the reverse of the list: " + s);
        }
    }

    public static void main(String[] args) {
        StdOut.println("test deleteLast() :");
        testDeleteLast();

        StdOut.println();
        StdOut.println("test delete() :");
        testDelete();

        StdOut.println();
        StdOut.println("test find() :");
        testFind();

        StdOut.println();
        StdOut.println("test remove() :");
        testRemove();

        StdOut.println();
        StdOut.println("test maxInt() :");
        testMaxInt();

        StdOut.println();
        StdOut.println("test maxIntRecursive() :");
        testMaxIntRecursive();

        StdOut.println();
        StdOut.println("test reverse() :");
        testReverse();

        StdOut.println();
        StdOut.println("test reverseRec() :");
        testReverseRec();
    }

}
