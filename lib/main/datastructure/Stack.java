package lib.main.datastructure;

import java.util.Iterator;
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

    @Override
    public void push(Item item) {
        if (head != null) {
            Node old = head;
            head = new Node(item);
            head.next = old;
            N++;
        } else {
            head = new Node(item);
            N++;
        }
    }

    @Override
    public Item pop() {
        if (this.isEmpty()) return null;
        else {
            Node ret = head;
            head = ret.next;
            N--;
            return ret.item;
        }
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
    
    private class ReverseArrayIterator implements Iterator<Item> {
        // 支持后进先出的迭代
        private int i = N;
        public boolean hasNext() { return i > 0; }
        public Item next() {
            var ret = pop();
            i--;
            return ret;
        }
        public void remove() { }
    }

    public static void main(String[] args) {
        // var maillist = new Stack<String>();
        // while (!StdIn.isEmpty()) {
        //     String item = StdIn.readString();
        //     if (!item.equals("-")) maillist.push(item);
        //     else if (!maillist.isEmpty()) StdOut.print(maillist.pop() + " ");
        // }
        // StdOut.println("(" + maillist.size() + " left on stack)");

        var maillist = new Stack<String>();

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
    }
}
