package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

public class Buffer {

    private Stack<Character> leftBuffer = new Stack<>();
    private Stack<Character> rightBuffer = new Stack<>();

    public void insert(char c) {
        leftBuffer.push(c);
    }

    public char delete() {
        return leftBuffer.pop();
    }

    public void left(int k) {
        for (int i = 0; i < k; i++) {
            if (!leftBuffer.isEmpty()) rightBuffer.push(leftBuffer.pop());
        }
    }

    public void right(int k) {
        for (int i = 0; i < k; i++) {
            if (!rightBuffer.isEmpty()) leftBuffer.push(rightBuffer.pop());
        }
    }

    public int size() {
        return leftBuffer.size() + rightBuffer.size();
    }

    public String toString() {
        // 复制右栈
        Stack<Character> buffer = new Stack<>(rightBuffer);
        // 迭代遍历左栈，将所有字符都压入右栈中
        for (Character c:
             leftBuffer) {
            buffer.push(c);
        }
        // 迭代遍历复制的 buffer 生成字符串
        StringBuilder s = new StringBuilder();
        for (Character c:
             buffer) {
            s.append(c);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Buffer editor = new Buffer();
        for (int i = 0; i < 5; i++) {
            editor.insert(String.valueOf(i).charAt(0));
        }
        editor.left(2);
        editor.delete();
        editor.right(1);
        StdOut.println(editor);
    }

}
