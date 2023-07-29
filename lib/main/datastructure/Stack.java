package lib.main.datastructure;

import java.net.StandardProtocolFamily;
import java.util.Iterator;
import java.util.NoSuchElementException;

import lib.api.*;
import lib.std.StdOut;
import lib.std.StdRandom;

public class Stack<Item> implements AbstractStack<Item> {

    private Node head;
    private int N;

    public Stack() {
        this.head = null;
        this.N = 0;
    }

    private class Node {
        Item item;
        Node next;

        public Node (Item item) {
            this.item = item;
            this.next = null;
        }
    }

    /**
     * Adds the item to this stack.
     *
     * @param  item the item to add
     */
    @Override
    public void push(Item item) {
        Node old = head;
        head = new Node(item);
        head.next = old;
        old = null;
        N++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    @Override
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Node ret = head;
        head = ret.next;
        N--;
        return ret.item;
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return head.item;
    }


    @Override
    public boolean isEmpty() {
        return head == null;    
    }


    @Override
    public int size() {
        return N;
    }

    
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();    
    }
    
    /**
     * 从栈底部迭代出每一个元素
     */
    private class ReverseArrayIterator implements Iterator<Item> {
        // 支持后进先出的迭代
        private int i = N;
        private Node next = head;
        public boolean hasNext() { return i > 0; }
        public Item next() {
            var ret = next.item;
            next = next.next;
            i--;
            return ret;
        }
        public void remove() { }
    }

    public static void main(String[] args) {
        // 单元测试 1
        /* 
        Stack<String> s = new Stack<String>();
        while (!StdIn.isEmpty()) { 
            String item = StdIn.readString(); 
            if (!item.equals("-")) {
                s.push(item);
                StdOut.print("Stack -> ");
                for (String p : s) {
                    StdOut.printf("%s ", p);
                }
                StdOut.println();
            } 
            else if (!s.isEmpty()) StdOut.print(s.pop() + " \n"); 
        }
        StdOut.println("(" + s.size() + " left on stack)");
        */

        // 单元测试 2
        var maillist = new Stack<String>();

        // StdOut.println(maillist.pop());
        for (int i = 0; i < 15; i++) {
            maillist.push("Arg: " + StdRandom.uniformInt(1000));
        }

        StdOut.println("Size: " + maillist.size());
        
        StdOut.println(maillist.pop());
        StdOut.println(maillist.pop());
        StdOut.println("____________");

        for (String i : maillist) {
            StdOut.println(i);
        }
        StdOut.println("____________");

        int size = maillist.size();
        for (int i = 0; i < size; i++) StdOut.println(maillist.pop());
        StdOut.println("____________");
        
        StdOut.println("Size: " + maillist.size());
    }
}
