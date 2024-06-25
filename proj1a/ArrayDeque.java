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
        for (int i = 0; i < size; i++) {
            int oldIndex = i + nextFirst + 1;
            if (oldIndex >= items.length) {
                oldIndex = oldIndex - items.length;
            }
            int newIndex;
            if (oldIndex < nextLast) {
                newIndex = oldIndex;
            } else if (capacity > items.length) {
                newIndex = oldIndex + items.length;
            } else {
                newIndex = oldIndex - items.length / 2;
            }
            a[newIndex] = items[oldIndex];
        }
        if (capacity > items.length) {
            nextFirst = nextFirst + items.length;
        } else {
            nextFirst = nextFirst - items.length / 2;
        }
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
        if (size <= items.length / 2 && size > 8) {
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
        if (size <= items.length / 2 && size > 8) {
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
    //     ArrayDeque<Integer> A = new ArrayDeque<>();
    //     A.addFirst(0);
    //     A.addFirst(1);
    //     A.addFirst(2);
    //     A.addFirst(3);
    //     A.get(0);
    //     A.removeFirst();
    //     A.addLast(6);
    //     A.removeFirst();
    //     A.get(0);
    //     A.removeLast();
    //     A.get(1);
    //     A.removeFirst();
    //     A.removeFirst();
    //     A.addLast(13);
    //     int t = A.removeFirst();
    //     int b = A.get(0);
    // }

}
