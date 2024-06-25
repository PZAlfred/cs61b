public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    /**
     * Resize the array.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int frontLength = nextLast;
        int backLength = items.length - frontLength;
        System.arraycopy(items, 0, a, 0, frontLength);
        System.arraycopy(items, nextFirst + 1, a, nextFirst + 1 + items.length, backLength);
        nextFirst = nextFirst + items.length;
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
        }
        items[nextLast] = item;
        size += 1;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
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

    /**
     * Removes and returns the item at the front of the deque. If no such item
     * exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T value = items[nextFirst];
        // items[nextFirst] = null;
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        return value;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T value = items[nextLast];
        // items[nextLast] = null;
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        return value;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (size == 0 || index > size - 1) {
            return null;
        }
        int finalIndex = index + nextFirst + 1;
        finalIndex = finalIndex % items.length;
        return items[finalIndex];
    }

    // public static void main(String[] args) {
    //     ArrayDeque<Integer> A = new ArrayDeque<>();
    //     int N = 9;
    //     for (int i=0; i<N; i++) {
    //         A.addFirst(i);
    //     }
    //     A.removeFirst();
    //     A.removeLast();
    //     A.removeLast();
    //     int b = A.get(0);
    // }

}
