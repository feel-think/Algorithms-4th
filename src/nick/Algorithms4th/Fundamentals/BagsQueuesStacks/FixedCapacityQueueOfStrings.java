package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityQueueOfStrings {

    private String[] queue;
    private int first = 0;
    private int last = 0;
    private int N = 0;

    public FixedCapacityQueueOfStrings(int capacity) {
        queue = new String[capacity];
    }

    public void enqueue(String s) {
        // 队列已满时抛出异常
        if (size() == queue.length) throw new RuntimeException("Can't push into a full Queue.");
        // 向队尾插入数据，last 增 1
        queue[last++] = s;
        // 队列长度增 1
        N++;
        // 当指针超出数组范围后，重新将其指向数组首部
        if (last == queue.length) last = 0;
    }

    public String dequeue() {
        // 队列为空时抛出异常
        if (isEmpty()) throw new RuntimeException("Can't pop from an empty Queue.");
        // 从队首取出一条记录，first 增 1
        String item = queue[first];
        queue[first++] = null; // 避免对象游离
        // 队列长度减 1
        N--;
        // 当指针超出数组范围后，重新将其指向数组首部
        if (first == queue.length) first = 0;
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        String[] strings = "0 1 2 3 4 5 6 7 8 9 10 11".split(" ");
        FixedCapacityQueueOfStrings queue = new FixedCapacityQueueOfStrings(10);
        for (int i = 0; i < strings.length; i++) {
            queue.enqueue(strings[i]);
            if ("36".indexOf(strings[i]) != -1) StdOut.println(queue.dequeue());
        }
    }

}
