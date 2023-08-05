package lib.vary.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Steque<Item> implements Iterable<Item> {

    private int N;
    private Node head;
    private Node tail;

    private class Node {
        private Item body;
        private Node next;

        public Node(Item item) {
            this.body = item;
            this.next = null;
        }
    }

    public Steque() {
        this.N = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * 向队尾添加元素
     * @param item
     */
    public void enqueue(Item item) {
        if (N == 0) push(item);
        else {
            var enqueueItem = new Node(item);
            tail.next = enqueueItem;
            tail = enqueueItem;
            N++;
        }
    }

    /**
     * 向栈顶添加元素
     * @param item
     */
    public void push(Item item) {
        var pushingItem = new Node(item);
        pushingItem.next = head;
        head = pushingItem;
        if (N == 0) tail = pushingItem;
        N++;
    }

    /**
     * 从栈顶弹出元素
     * @return
     */
    public Item pop() {
        if(isEmpty()) throw new NoSuchElementException("Error: steque is empty.");
        var popItem = head;
        head = popItem.next;
        N--;
        return popItem.body;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Item peek() {
        return head.body;
    }

    public Item tail() {
        return tail.body;
    }

    @Override
    public String toString() {
        var buffer = new StringBuilder();
        buffer.append("[");
        for (Item element : this) {
            buffer.append(element + ", ");
        }
        buffer.append("]");
        return buffer.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<Item> {

        private int i = N;
        private Node popHead = head;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            var ret = popHead;
            popHead = popHead.next;
            i--;
            return ret.body;
        }
        
    }

    public static void main(String[] args) {
        Steque<String> st = new Steque<String>();
        System.out.println("Size: " + st.size());
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) st.push("Odd: " + i);
            else st.enqueue("Even: " + i);
        }
        System.out.println("Size: " + st.size());
        for (String wn : st) {
            System.out.println(wn);
        }
        System.out.println(st);

        // -------------------Test 2-----------------------

        Steque<Integer> stack = new Steque<>();

        // 测试栈是否为空
        System.out.println("Is stack empty? " + stack.isEmpty());

        // 添加元素到栈顶
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // 打印栈的元素
        System.out.println("Stack elements: " + stack);

        // 弹出栈顶元素
        try {
            System.out.println("Popped element: " + stack.pop());
        } catch (NoSuchElementException e) {
            System.out.println("Stack is empty, cannot pop.");
        }

        // 查看栈顶元素
        try {
            System.out.println("Top element: " + stack.peek());
        } catch (NoSuchElementException e) {
            System.out.println("Stack is empty, no top element.");
        }

        // 查看栈中元素个数
        System.out.println("Size of stack: " + stack.size());

        // 测试栈是否为空
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
