public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    /**
     * Resize the array.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int oldIndex;
        int newIndex;
        for (int i = 0; i < size; i++) {
            oldIndex = i + nextFirst + 1;
            if (oldIndex >= items.length) {
                oldIndex -= items.length;
            }
            newIndex = i;
            a[newIndex] = items[oldIndex];
        }
        items = a;
        if (capacity > size) {
            // For making larger
            nextFirst = items.length - 1;
            nextLast = size;
        } else {
            // For making smaller
            nextFirst = items.length - 1;
            nextLast = 0;
        }
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
        if (nextLast == items.length - 1 && size < items.length) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
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
        T value;
        if (nextFirst == items.length - 1) {
            value = items[0];
            items[0] = null;
        } else {
            value = items[nextFirst + 1];
            items[nextFirst + 1] = null;
        }
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        if (size <= items.length / 2 && items.length > 8) {
            resize(items.length / 2);
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
        T value;
        if (nextLast == 0) {
            value = items[items.length - 1];
            items[items.length - 1] = null;
        } else {
            value = items[nextLast - 1];
            items[nextLast - 1] = null;
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        if (size <= items.length / 2 && items.length > 8) {
            resize(items.length / 2);
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
    // ArrayDeque<Integer> A = new ArrayDeque<>();
    // A.addLast(0);
    // A.addLast(1);
    // A.addLast(2);
    // A.removeLast();
    // A.removeFirst();
    // A.removeLast();
    // A.addLast(6);
    // A.removeLast();
    // A.addFirst(8);
    // A.removeFirst();
    // A.addFirst(10);
    // A.addLast(11);
    // A.get(1);
    // A.addLast(13);
    // A.addLast(14);
    // A.addLast(15);
    // A.addFirst(16);
    // A.get(2);
    // A.addLast(18);
    // A.addLast(19);
    // A.addLast(20);
    // A.addLast(21);
    // A.removeFirst();
    // A.removeLast();
    // A.addLast(22);
    // int t = A.removeFirst();
    // int b = A.get(0);
    // }

}
