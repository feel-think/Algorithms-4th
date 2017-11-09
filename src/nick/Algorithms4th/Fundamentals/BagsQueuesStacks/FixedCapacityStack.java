package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

public class FixedCapacityStack<Item> {

    private Item[] stack;
    private int pointer = 0;

    public FixedCapacityStack(int capacity) {
        stack = (Item[]) new Object[capacity];
    }

    public void push(Item i) {
        if (pointer == stack.length) throw new RuntimeException("ResizingArrayStack overflow.");
        stack[pointer++] = i;
    }

    public Item pop() {
        if (isEmpty()) throw new RuntimeException("ResizingArrayStack is Empty.");
        Item i = stack[--pointer];
        stack[pointer+1] = null; // 避免对象游离
        return i;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public int size() {
        return pointer;
    }

}
