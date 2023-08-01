package lib.main.datastructure;

import java.util.Iterator;

import lib.api.AbstractPackage;

public class LinkedListPackage<Item> implements AbstractPackage<Item> {

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayFormIterator();
    }

    private class ReverseArrayFormIterator implements Iterator<Item> {
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
        }

        @Override
        public Item next() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'next'");
        }
        
    }

    @Override
    public void add(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }
    
}
