public class LinkedListDeque<T> {

  public class TNode {
    public TNode prev;
    public T item;
    public TNode next;

    public TNode(TNode n1, T i, TNode n2) {
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

  /**
   * Prints the items in the deque from first to last, separated by a space.
   */
  public void printDeque() {
    TNode ptrNode = sentinel.next;
    while (ptrNode != sentinel) {
      System.out.println(ptrNode.item);
      ptrNode = ptrNode.next;
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
    TNode temp = sentinel.next;
    T value = temp.item;
    sentinel.next = temp.next;
    temp.next.prev = sentinel;
    temp.next = null;
    temp.prev = null;
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
    TNode temp = sentinel.prev;
    T value = temp.item;
    sentinel.prev = temp.prev;
    temp.prev.next = sentinel;
    temp.next = null;
    temp.prev = null;
    return value;
  }

  /**
   * Gets the item at the given index, where 0 is the front, 1 is the next item,
   * and so forth. If no such item exists, returns null. Must not alter the deque!
   */
  public T get(int index) {
    if (size == 0) {
      return null;
    }
    int k = 0;
    TNode kNode = sentinel.next;
    while (k != index) {
      k += 1;
      kNode = kNode.next;
    }
    return kNode.item;
  }

  public T getRecursive(int index) {
    if (size == 0) {
      return null;
    }
    return nextNode(sentinel.next, index);
  }

  public T nextNode(TNode n, int i) {
    if (i == 0) {
      return n.item;
    } else {
      return nextNode(n.next, i - 1);
    }
  }

}
