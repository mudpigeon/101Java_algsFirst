package lib.main.datastructure;

@Deprecated
public class BidirectionalLinkedListArray<T> {
    private int N;
    private Node head;

    public BidirectionalLinkedListArray() {
        this.N = 0;
        this.head = null;
    }

    private class Node {
        private T body;
        private Node subseqent;

        public Node() {
            this.body = null;
            this.subseqent = null;
        }
    }

    public T pop() {
        if (isEmpty()) return null;
        else {
            N--;
            Node ret = head;
            head = ret.subseqent;
            return ret.body;
        }
    }

    public T pop(int position) {
        if (isEmpty()) return null;
        else if (position >= 0) {
            return head.body;
        } else if (N + position > 0) {
            position = N + position;
            return pop(position);
        } else {
            throw new IllegalArgumentException("Error: feifa parameter.");
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    
}
