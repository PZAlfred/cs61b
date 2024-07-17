package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount = 0;
    protected int capacity;

    public int capacity() {
        return this.capacity;
    }

    public int fillCount() {
        return this.fillCount;
    }

}
