package lib.main.processor;

import lib.main.datastructure.*;

public class StackWithQueue {

    public static <Item> void reverseQueue(Queue<Item> q) {
        var s = new Stack<Item>();
        while (!q.isEmpty()) s.push(q.dequeue());
        while (!s.isEmpty()) q.enqueue(s.pop());
    }

    public static void main(String[] args) {
        var stringQueue = new Queue<String>();

        stringQueue.enqueue("Hello");
        stringQueue.enqueue("one");
        stringQueue.enqueue("two");
        stringQueue.enqueue("three");

        for (String s : stringQueue) {
            System.out.println(s);
        }

        reverseQueue(stringQueue);

        for (String s : stringQueue) {
            System.out.println(s);
        }
    }
}
