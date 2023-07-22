package lib.dstr;

import lib.std.StdIn;
import lib.std.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
import lib.world.Person;

public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] itemStack;
    private int size;

    // create an empty stack with given capacity
    public FixedCapacityStack(int capacity) {
        itemStack = (Item[]) new Object[capacity];   // no generic array creation
        size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == itemStack.length;
    }

    /**
     * 
     * @return the runtime size of stack
     */
    public int size() {
        return this.size;
    }

    /**
     * 
     * @param capacity new length of the stack
     */
    public void resize(int capacity) {
        if (capacity < this.size) {
            System.out.printf("Error: Method FixedCapacityStack.resize() refuse to resize %d(original) to %d(updated).\n", this.size, capacity);
        } else {
            var temp = (Item[]) new Object[capacity];
            for (int i = 0; i < this.size; i++) {
                temp[i] = itemStack[i];
            }
            itemStack = temp;
        }
    }

    public void push(Item item) {
        if (this.isFull()) this.resize(size * 2);
        itemStack[size++] = item;
    }

    public Item pop() {
        if (this.size < itemStack.length / 4) this.resize(size * 2);
        var temp = itemStack[--size];
        itemStack[size] = null;
        return temp;
    }

    public int getCapacity() {
        return itemStack.length;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // an array iterator, in reverse order
    public class ReverseArrayIterator implements Iterator<Item> {
        private int i = size-1;

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return itemStack[i--];
        }
    }


    public static void main(String[] args) {
        var s = new FixedCapacityStack<Person>(5);
        StdOut.println(s.size());
        s.push(new Person("Alice", 12, Person.FEMALE));
        s.push(new Person("Bob", 44, Person.MALE));
        s.push(new Person("Jack Hackness", 22, Person.MALE));
        s.push(new Person("Ramsay Auora", 19, Person.MALE));
        StdOut.println(s.size());
        StdOut.println(s.pop());
        s.push(new Person("Cern Clone", 29, Person.FEMALE));
        StdOut.println(s.size());
        s.push(new Person("Kevlein", 27, Person.MALE));
        StdOut.println(s.size());
        s.push(new Person("Molly Spyroll", 8, Person.FEMALE));
        StdOut.println(s.getCapacity());
        
        StdOut.println(s.pop());
        StdOut.println(s.pop());
        StdOut.println(s.pop());
        StdOut.println(s.pop());
        StdOut.println(s.pop());
        StdOut.println(s.pop());
        
        StdOut.println(s.getCapacity());
        StdOut.println(s.size());


        
    }
}