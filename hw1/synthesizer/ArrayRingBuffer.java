package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if(fillCount == capacity) {
            throw new RuntimeException("Ring bugger overflow");
        }
        if(fillCount == 0) {
            first = last;
            rb[first] = x;
        }
        else {
            if(last == rb.length - 1) {
                last = 0;
            }
            else {
                last = last + 1;
            }
            rb[last] = x;
        }
        fillCount = fillCount + 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if(fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T toReturn = rb[first];
        rb[first] = null;
        if(first == rb.length-1) {
            first = 0;
        }
        else {
            first = first + 1;
        }
        fillCount = fillCount - 1;
        return toReturn;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if(fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int bufferPos;

        public ArrayRingBufferIterator() {
            bufferPos = 0;
        }

        public boolean hasNext() {
            return bufferPos < fillCount;
        }

        public T next() {
            T returnItem = rb[bufferPos];
            bufferPos = bufferPos + 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

}
