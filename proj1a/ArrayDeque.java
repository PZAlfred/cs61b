public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    /**
     * Resize the array.
     */
    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, capacity);
        items = a;
    }

    /**
     * Instantiate the empty array deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
            nextLast = size;
            nextFirst = items.length - 1;
        }
        items[nextFirst] = item;
        size += 1;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    /**
     * Adds an item of type T to the end of the deque.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
            nextLast = size;
            nextFirst = items.length - 1;
        }
        items[nextLast] = item;
        size += 1;
        nextLast += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (size == 0) {
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

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        int printFirst = nextFirst;
        while (printFirst != nextLast) {
            System.out.print(items[printFirst] + " ");
            if (printFirst == items.length - 1) {
                printFirst = 0;
            } else {
                printFirst += 1;
            }
        }
    }

}
