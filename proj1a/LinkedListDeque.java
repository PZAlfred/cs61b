public class LinkedListDeque<T> {

    public class TNode {
        public TNode prev;
        public T item;
        public TNode next;

        private TNode(TNode n1, T i, TNode n2) {
            prev = n1;
            item = i;
            next = n2;
        }
    }

    private int size;
    private TNode sentinel;

    /**
     * Instantiate the empty doubly-linked deque.
     */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new TNode(sentinel, item, sentinel);
            sentinel.prev = sentinel.next;
            size += 1;
        } else {
            TNode nextNode = sentinel.next;
            TNode newNode = new TNode(sentinel, item, sentinel.next);
            sentinel.next = newNode;
            nextNode.prev = newNode;
            size += 1;
        }
    }

    /**
     * Adds an item of type T to the end of the deque.
     */
    public void addLast(T item) {
        if (size == 0) {
            sentinel.next = new TNode(sentinel, item, sentinel);
            sentinel.prev = sentinel.next;
            size += 1;
        } else {
            TNode prevNode = sentinel.prev;
            TNode newNode = new TNode(sentinel.prev, item, sentinel);
            sentinel.prev = newNode;
            prevNode.next = newNode;
            size += 1;
        }
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

}
