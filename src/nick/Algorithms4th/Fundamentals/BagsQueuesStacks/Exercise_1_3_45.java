package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_3_45 {

    public static boolean isValid(String s) {
        String[] strings = s.split(" ");
        // 使用一个变量模拟栈中元素的数量，每次出栈操作后检查该变量是否 < 0
        int N = 0;
        for (String string:
             strings) {
            if (string.equals("-")) {
                N--;
                if (N < 0) return false;
            } else {
                N++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "0 1 2 - 3 4 5 - 6 - 7 - - 8 - 9 - - - -";
        StdOut.printf("string [ %s ]\n    is %sa valid Stack operation sequence.\n", s, isValid(s) ? "" : "not ");
        s = "0 1 2 3 - - - - - 4";
        StdOut.printf("string [ %s ]\n    is %sa valid Stack operation sequence.\n", s, isValid(s) ? "" : "not ");
    }

}
