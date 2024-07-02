public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);

    // public static void main(String[] args) {
    //     Deque<Integer> D = new LinkedListDeque<>();
    //     D.addFirst(1);
    //     D.addFirst(5);
    //     D.addFirst(3);
    //     D.printDeque();
    // }
}
