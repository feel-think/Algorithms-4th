package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayQueueOfStrings {

    private String[] array = new String[1];
    private int first = 0;
    private int last = 0;
    private int N = 0;

    public void enqueue(String s) {
        // 队列已满时将存储元素的数组扩大一倍
        if (size() == array.length) resize(2*array.length);
        // 向队尾插入数据，last 增 1
        array[last++] = s;
        // 队列长度增 1
        N++;
        // 当指针超出数组范围后，重新将其指向数组首部
        if (last == array.length) last = 0;
    }

    public String dequeue() {
        // 队列为空时抛出异常
        if (isEmpty()) throw new RuntimeException("Can't pop from an empty Queue.");
        // 队列长度不足数组的四分之一时，将数组长度缩小一半
        if (N == array.length/4) resize(array.length/2);
        // 从队首取出一条记录，first 增 1
        String item = array[first];
        array[first++] = null; // 避免对象游离
        // 队列长度减 1
        N--;
        // 当指针超出数组范围后，重新将其指向数组首部
        if (first == array.length) first = 0;
        return item;
    }

    private void resize(int newCapacity) {
        String[] newArray = new String[newCapacity];
        // 将队列中的元素按队列顺序从旧数组复制到新数组，从新数组的 0 索引处开始存放
        for (int i = 0, pointer = first; i < N; i++, pointer++) {
            // 跟踪遍历位置的指针越界时，将其重置为 0
            if (pointer == array.length) pointer = 0;
            // 复制队列元素引用
            newArray[i] = array[pointer];
        }
        array = newArray;
        // 重新设置队列在新数组中的首尾位置
        first = 0;
        last = N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
        for (int i = 0; i < 16; i++) {
            queue.enqueue(String.valueOf(i));
            if ("36".indexOf(String.valueOf(i)) != -1) StdOut.println(queue.dequeue());
        }
    }

}
