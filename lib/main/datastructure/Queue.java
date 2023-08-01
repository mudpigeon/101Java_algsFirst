package lib.main.datastructure;

import java.util.Iterator;
import lib.api.AbstractQueue;
import lib.other.Date;
import lib.std.StdOut;
import lib.std.StdRandom;

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

        public Node(Item item2) {
            body = item2;
            next = null;
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
    public void enqueue(Item item) {
        if(tail != null) {
            var hockNode = new Node(item);
            var oldNode = this.tail;
            oldNode.next = hockNode;
            this.tail = hockNode;
        } else {
            var hockNode = new Node(item);
            tail = hockNode;
            head = hockNode;
        }
        N++;
    }

    @Override
    public Item dequeue() {
        if (head == null) {
            return null;
        } else if (head == tail) {
            N--;
            Node ret = head;
            head = null;
            tail = null;
            return ret.body;
        } else {
            N--;
            Node ret = head;
            head = ret.next;
            return ret.body;
        }
    }

    public Item head() {
        return head.body;
    }
    
    public Item tail() {
        return tail.body;
    }

    @Override
    public Iterator<Item> iterator() {
        return new HeadToTailIterator();
    }

    private class HeadToTailIterator implements Iterator<Item> {

        private int i = N;
        private Node headNode = head;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            var traversePointer = headNode;
            headNode = traversePointer.next;
            i--;
            return traversePointer.body;
        }
        
    }

    /**
     * 驱动测试
     * @param args
     * 
     * @Part1测试
     */
    public static void main(String[] args) {
        // 入队和出队测试
        Queue<String> queue = new Queue<>();
        StdOut.println("Queue is empty: " + queue.isEmpty());
        StdOut.println("Starting enqueue process:");
        queue.enqueue("Alice");
        queue.enqueue("Bob");
        queue.enqueue("Charlie");
        queue.enqueue("Ethan");
        queue.enqueue("Olivia");
        queue.enqueue("Liam");
        queue.enqueue("Sophia");

        StdOut.println("Starting dequeue process:");
        for(int i = 0; i < 7; i++) {
            StdOut.println((i+1) + " -> " + queue.dequeue());
        }
        StdOut.println("Queue has been cleared: " + queue.isEmpty());
        StdOut.println("_____________Enq and deq Test Finished_____________");

        // 异常测试
        StdOut.println(queue.dequeue());
        StdOut.println("________________Exception Test Finished________________");

        // 迭代测试
        var dateQueue = new Queue<Date>();
        int year;
        int month;
        int day;
        for (int i = 0; i < 20; i++) {
            year = StdRandom.uniformInt(1990, 2030);
            //StdOut.print(year + " ");
            month = StdRandom.uniformInt(1, 12);
            //StdOut.print(month + " ");
            day = StdRandom.uniformInt(1, 25);
            //StdOut.print(day + " ");
            var hockNode = new Date(month, day, year);
            dateQueue.enqueue(hockNode);
        }
        int i = 0;
        for (Date d : dateQueue) {
            StdOut.println(++i + " --> " + d.toString());
        }

        StdOut.println("Head --=> " + dateQueue.head());
        StdOut.println("Tail --=> " + dateQueue.tail());

        for (i = 0; i < 21; i ++) {
            StdOut.println((i+1) + " -> " + dateQueue.dequeue());
        }

    }
    
}
