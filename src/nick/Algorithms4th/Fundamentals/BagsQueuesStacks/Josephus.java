package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

public class Josephus {

    public static void main(String[] args) {
        /**
         * Josephus 问题需要一个 Queue 来保存所有尚且存活的人的编号
         * 从队列首部取出一个人，计数，判断这个人是否需要被杀死，如果需要，就把他的编号打印出来
         * 如果不需要，就让他重新进入队列的尾部。然后不断重复这个过程，直至队列为空
         * 在这里，圈的形态主要靠出列再入列这个步骤来实现
         */
        int N = Integer.parseInt(args[0]), M = Integer.parseInt(args[1]);
        // 构造 N 个人的队列
        Queue<Integer> peoples = new Queue<>();
        for (int i = 0; i < N; i++) {
            peoples.enqueue(i);
        }
        // 循环杀人：取人，计数 -> 判断 -> 杀人 or 入列
        for (int i = 1; !peoples.isEmpty(); i++) {
            int people = peoples.dequeue();
            if (i % M == 0) StdOut.print(people + " ");
            else peoples.enqueue(people);
        }
        StdOut.println();
    }

}
