package lib.main.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

import lib.api.AbstractQueue;
import lib.other.Date;
import lib.std.StdOut;
import lib.std.StdRandom;

public class ResizingCircleArrayQueue<Item> implements AbstractQueue<Item> {

    private Item[] contents;
    private int N;
    private int front;
    private int rear;
    private int queueLength;
    private final int minQueueLength;

    // default constructor
    public ResizingCircleArrayQueue() {
        this.contents = (Item[]) new Object[1];
        this.N = 0;
        this.front = 0;
        this.rear = 0;
        this.queueLength = 1;
        this.minQueueLength = 1;
    }

    // configuration constructor
    public ResizingCircleArrayQueue(int capacity) {
        this.contents = (Item[]) new Object[capacity];
        this.N = 0;
        this.front = 0;
        this.rear = 0;
        this.queueLength = capacity;
        this.minQueueLength = capacity;
    }

    @Override
    public void enqueue(Item item) {
        contents[front % queueLength] = item;
        front++;
        N++;
        if (N == 0)
            rear++;
        if (isFull()) sizeIncrease();
    }

    @Override
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Error: Queue is empty");
        else {
            var ret = contents[rear % queueLength]; // 获取退出元素
            contents[rear % queueLength] = null; // 防止对象游离
            if (rear != front)
                rear++; // 尾部增长
            N--; // 长度缩减
            if (N < queueLength / 4 && N > minQueueLength)
                sizeDecrease(); // 大小调整
            return ret;
        }
    }

    private void sizeIncrease() {
        var newContents = (Item[]) new Object[2 * queueLength];
        for (int i = 0; i < queueLength; i++) {
            newContents[i] = contents[(rear + i) % queueLength];
        }
        contents = newContents;
        queueLength *= 2;
        front = front - rear;
        rear = 0;
    }

    private void sizeDecrease() {
        var newContents = (Item[]) new Object[queueLength / 2];
        for (int i = 0; i < N; i++) {
            newContents[i] = contents[(rear + i) % queueLength];
        }
        contents = newContents;
        queueLength /= 2;
        front = front - rear;
        rear = 0;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    private boolean isFull() {
        return N == queueLength;
    }

    // /**
    // * 判断循环数组是否越界
    // * @return
    // */
    // private boolean isOverflow() {
    // return front - rear > queueLength;
    // }

    /**
     * 返回队列中当前元素的个数
     */
    @Override
    public int size() {
        return N;
    }

    /**
     * 返回当前队列的最大容量
     * 
     * @return
     */
    public int capacity() {
        return queueLength;
    }

    @Override
    public Iterator<Item> iterator() {
        return new HeadToTailIterator();
    }

    private class HeadToTailIterator implements Iterator<Item> {

        private int i = N;
        private int bottom = rear;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            var ret = contents[bottom % queueLength];
            bottom++;
            i--;
            return ret;
        }

    }

    /**
     * 驱动测试
     * 
     * @param args
     * 
     * @Part1测试
     */
    public static void main(String[] args) {

        //combineEnqueueAndDequeueTest();
        iteratorTest();
    }

    /**
     * 测试队列初始化功能是否正常、入队是否能够自动增长
     */
    public static void enqueueTest() {
        System.out.println("\n>>> Default Initialization Test <<<");
        var rcaq = new ResizingCircleArrayQueue<String>();
        System.out.println("Queue size: " + rcaq.size());
        System.out.println("Queue capa: " + rcaq.capacity());
        System.out.println("_____________________________");

        System.out.println("\n\n>>> Enqueue Test <<<");
        rcaq.enqueue("Hello world. I am the beginner of Java programing.");
        System.out.printf("Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());
        rcaq.enqueue("String #2");
        System.out.printf("Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());
        rcaq.enqueue("String #3");
        rcaq.enqueue("String #4");
        rcaq.enqueue("String #5");
        rcaq.enqueue("String #6");
        rcaq.enqueue("String #7");
        System.out.printf("Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());

        System.out.println("\n\n>>> General Initialization Test <<<");
        rcaq = null;
        rcaq = new ResizingCircleArrayQueue<String>(10);
        System.out.printf("Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());
        rcaq.enqueue("String #1");
        rcaq.enqueue("String #2");
        rcaq.enqueue("String #3");
        rcaq.enqueue("String #4");
        rcaq.enqueue("String #5");
        rcaq.enqueue("String #6");
        rcaq.enqueue("String #7");
        rcaq.enqueue("String #8");
        System.out.printf(">> #8 | Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());
        rcaq.enqueue("String #9");
        System.out.printf(">> #9 | Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());
        rcaq.enqueue("String #10");
        System.out.printf(">> #10| Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());
        rcaq.enqueue("String #11");
        rcaq.enqueue("String #12");
        rcaq.enqueue("String #13");
        rcaq.enqueue("String #14");
        System.out.printf(">> #14| Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());

    }

    /**
     * 测试出队列功能
     */
    public static void deququeTest() {

        // 基本出队测试
        System.out.println("\n\n >>> Dequeue Test <<<");
        var rcaq = new ResizingCircleArrayQueue<String>();
        System.out.println("Queue size: " + rcaq.size());
        System.out.println("Queue capa: " + rcaq.capacity());
        System.out.println("_____________________________");

        System.out.println("\nEnqueued 6 element.");
        rcaq.enqueue("String #1");
        rcaq.enqueue("String #2");
        rcaq.enqueue("String #3");
        rcaq.enqueue("String #4");
        rcaq.enqueue("String #5");
        rcaq.enqueue("String #6");
        System.out.printf("Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());

        System.out.println("Dequeue #1:" + rcaq.dequeue());
        System.out.println("Dequeue #2:" + rcaq.dequeue());
        System.out.println("Dequeue #3:" + rcaq.dequeue());
        System.out.println("Dequeue #4:" + rcaq.dequeue());
        System.out.println("Dequeue #5:" + rcaq.dequeue());
        System.out.println("Dequeue #6:" + rcaq.dequeue());
        System.out.printf("Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());
    
        // 在随机位置开始的入队和出队测试
        for (int i = 1; i < 31; i++) {
            System.out.println(i);
            rcaq.enqueue("Number: " + i);
        }
        System.out.printf("Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());
        for (int i = 0; i < 30; i++) {
            System.out.printf("Dequeue #%d: %s\n", i+1, rcaq.dequeue());
            System.out.printf("Queue size: %d and capacity: %d.\n", rcaq.size(), rcaq.capacity());
        }
        System.out.println("_______________\n\n");

    }

    /**
     * 测试随机的出队和入队混合操作
     */
    public static void combineEnqueueAndDequeueTest() {
        var rcaq = new ResizingCircleArrayQueue<String>();
        int insert = 0;
        int withdraw = 0;
        int cycle = 0;
        int add;
        while (cycle < 100) {
            add = StdRandom.uniformInt(16);
            insert += add;
            for (int i = 0; i < add; i++) {
                rcaq.enqueue("String: #" + i);
            }
            System.out.printf("Enqueue cycle: %d >> Queue size: %d and capacity: %d.\n", cycle, rcaq.size(), rcaq.capacity());

            withdraw = StdRandom.uniformInt(insert);
            insert -= withdraw;
            for (int i = 0; i < withdraw; i++) {
                rcaq.dequeue();
            }
            System.out.printf("Dequeue Cycle: %d >> Queue size: %d and capacity: %d.\n", cycle, rcaq.size(), rcaq.capacity());
            cycle++;
        }

        System.out.println("!!! Success!");


        
    }

    /**
     * 测试迭代器功能
     */
    public static void iteratorTest() {
        var rcaq = new ResizingCircleArrayQueue<String>();
        int insert = 0;
        int withdraw = 0;
        int cycle = 0;
        int add;
        while (cycle < 10) {
            add = StdRandom.uniformInt(16);
            insert += add;
            for (int i = 0; i < add; i++) {
                rcaq.enqueue("arg=" + i);
            }
            System.out.printf("Enqueue cycle: %d >> Queue size: %d and capacity: %d.\n", cycle, rcaq.size(), rcaq.capacity());

            withdraw = StdRandom.uniformInt(insert);
            insert -= withdraw;
            for (int i = 0; i < withdraw; i++) {
                rcaq.dequeue();
            }
            System.out.printf("Dequeue Cycle: %d >> Queue size: %d and capacity: %d.\n", cycle, rcaq.size(), rcaq.capacity());
            cycle++;

            for (String s : rcaq) {
                System.out.print(s + " -> ");
            }
            System.out.println();
        }

        System.out.println("!!! Success!");
    }
}
