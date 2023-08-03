package lib.main.datastructure;

public class LinkedList<Item> {
    
    private Node head;
    private Node tail;
    private int N;

    private class Node {
        private Item body;
        private Node next;
    }

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.N = 0;
    }

    public void push() {

    }

    public Item pop() {
        return null;
    }

    public Item pop(int position) {
        return null;
    }

    public void insert(int position) {
        
    }

    public int size() {
        return N;
    }
}
