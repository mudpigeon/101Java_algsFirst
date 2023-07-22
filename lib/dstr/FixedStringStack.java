package lib.dstr;

import lib.std.StdIn;
import lib.std.StdOut;

/**
 * FixedStringStack 是一个固定长度的栈（这似乎不太能被称之为栈），并且它也只能接受 String 类型
 */ 
public class FixedStringStack {
    private String[] content;
    private int size;

    public FixedStringStack(int capacity) {
        content = new String[capacity];
        size = 0;
    }

    public boolean isFull() {
        return size == content.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(String word) {
        if (this.isFull()) {
            System.out.println("Error: stack is full.");
        } else {
            content[size] = word;
            size++;
        }
    }

    public String pop() {
        if (this.isEmpty()) {
            System.out.println("Error: stack is empty.");
            return null;
        } else {
            size--;
            return content[size];
        }
    }

    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        var s = new FixedStringStack(100);
        System.out.printf("Enter >> ");
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
