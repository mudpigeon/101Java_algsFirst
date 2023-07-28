package lib.main.datastructure;

import java.util.Iterator;
import lib.api.AbstractQueue;

public class Queue<Item> implements AbstractQueue<Item> {

    private Node head;
    private Node tail;
    private int N;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.N = 0;
    }

    private class Node {
        private Item body;
        private Node next;
        private Node prev;

        public Node(Item item2) {
            body = item2;
            next = null;
            prev = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.N == 0;
    }

    @Override
    public int size() {
        return N;
    }
    

    @Override
    public void enQueue(Item item) {
        var temp = new Node(item);
        head.prev = temp;
        temp.next = head;
        head = temp;
        N++;

        if (N == 1) {
            tail = head;
        }
    }

    @Override
    public Item deQueue() {
        if (this.isEmpty()) return null;
        else {
            Item ret;
            re
            N--;
            return ret;
        }
    }

    

    @Override
    public Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    
}
