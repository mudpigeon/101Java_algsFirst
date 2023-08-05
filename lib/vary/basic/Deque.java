package lib.vary.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deque 是一个双端队列
 * @{应用}
 * 双端队列（栈队列）的应用场景：
 * 实现队列和栈：双端队列可以同时实现队列和栈的功能。在某些问题中，可能需要同时使用这两种数据结构，这时候可以使用双端队列来减少代码的复杂性。
 * 滑动窗口问题：在一些算法问题中，需要维护一个窗口，窗口在数据流中滑动，双端队列可以用于高效地解决这类问题。
 * 广度优先搜索（BFS）：在广度优先搜索的过程中，需要维护待访问节点的队列。双端队列可以用于这种情况。
 * 最大/最小滑动窗口：给定一个数组和一个窗口大小，需要在数组中找到每个窗口的最大或最小值。双端队列可以帮助在线性时间内解决这类问题。
 * 调度问题：在任务调度问题中，可能需要在任务队列的两端执行任务，双端队列可以很好地实现这个功能。
 */
public class Deque<Item> implements Iterable<Item> {

    private int N;
    private Node left;
    private Node right;

    private class Node {
        private Item body;
        private Node leftside;
        private Node rightside;


        public Node(Item item) {
            this.body = item;
            this.leftside = null;
            this.rightside = null;
        }
    }

    public Deque() {
        this.N = 0;
        this.left = null;
        this.right = null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void pushLeft(Item item) {
        var bullet = new Node(item);
        if (N == 0) {
            N++;
            left = bullet;
            right = bullet;
        } else {
            bullet.rightside = left;
            left.leftside = bullet;
            left = bullet;
            N++;
        }
    }

    public void pushRight(Item item) {
        var bullet = new Node(item);
        if (N == 0) {
            N++;
            left = bullet;
            right = bullet;
        } else {
            bullet.leftside = right;
            right.rightside = bullet;
            right = bullet;
            N++;
        }
    }

    public Item popLeft() {
        if (isEmpty()) throw new NoSuchElementException("Error: Queue is empty.");
        if (N == 1) {
            var bullet = left;
            left = null;
            right = null;
            N--;
            return bullet.body;
        } else {
            var bullet = left;
            left.rightside.leftside = null;
            left = left.rightside;
            N--;
            return bullet.body;
        }
    }

    public Item popRight() {
        if (isEmpty()) throw new NoSuchElementException("Error: Queue is empty.");
        if (N == 1) {
            var bullet = right;
            left = null;
            right = null;
            N--;
            return bullet.body;
        } else {
            var bullet = right;
            right.leftside.rightside = null;
            right = right.leftside;
            N--;
            return bullet.body;
        }
    }

    @Override
    public String toString() {
        var buffer = new StringBuilder();
        buffer.append("[");
        for (Item s : this) {
            buffer.append(s + ", ");
        }
        buffer.append("]");

        return buffer.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new LeftToRightIterator();
    }

    private class LeftToRightIterator implements Iterator<Item> {

        private int i = N;
        private Node first = left;
        @Override
        public boolean hasNext() {
            return i > 0;
        }


        @Override
        public Item next() {
            var bullet = first;
            first = first.rightside;
            i--;
            return bullet.body;
        }
    }

    public static void main(String[] args) {
        var fuck = new Deque<String>();
        System.out.println("Deque is empty: " + fuck.isEmpty());

        System.out.println("__________Pushing Now__________");
        for (int i = 0; i < 30; i++) {
            if (i % 2 == 0) fuck.pushLeft("Even: " + i);
            else            fuck.pushRight("Odd: " + i);
        }
        System.out.println(fuck);

        System.out.println("___________Poped Now___________");
        while(!fuck.isEmpty()) System.out.println(fuck.popLeft());
    }
}
