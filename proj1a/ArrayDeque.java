public class ArrayDeque<T> {
    private T[] items;
    private int size = 0;
    private int nextFirst = 4;
    private int nextLast = 5;
    private int REFACTOR = 2;

    public ArrayDeque()
    {
        items = (T[]) new Object[8];
    }

    private void resize(int capacity) {
        T[] items2 = (T[]) new Object[capacity];
        System.arraycopy(items2, 0, items, 0, size);
        items = items2;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }

        if (nextLast == items.length) {
            nextLast = 0;
        }
        items[nextLast] = item;
        nextLast++;
        size++;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        }
        items[nextFirst] = item;
        nextFirst--;
        size++;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public int size() {

        return size;
    }

    public T removeFirst() {
        int remove_index;
        if (size == 0) {
            return null;
        }
        if(nextFirst == items.length-1) {
            remove_index = 0;
        }
        else
        {
            remove_index = nextFirst + 1;
        }
        T removed = items[remove_index];
        items[remove_index] = null;
        nextFirst = remove_index;
        size = size - 1;
        return removed;
    }

    public T removeLast() {
        int remove_index;
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            remove_index = items.length-1;
        }
        else
        {
            remove_index = nextLast - 1;
        }
        T removed = items[remove_index];
        items[remove_index] = null;
        nextLast = remove_index;
        size = size - 1;
        return removed;
    }

    public T get(int index)
    {
        T[] firstToLast = (T[]) new Object[size];
        int FTL_index = 0;
        int items_index = nextFirst + 1;
        while (items_index != nextLast) {
            if (items_index == items.length)
            {
                items_index = 0;
            }
            firstToLast[FTL_index] = items[items_index];
            FTL_index++;
            items_index++;
        }
        return firstToLast[index];
    }

    public void printDeque() {
        T[] firstToLast = (T[]) new Object[size];
        int FTL_index = 0;
        int items_index = nextFirst + 1;
        while (items_index != nextLast) {
            if (items_index == items.length)
            {
                items_index = 0;
            }
            firstToLast[FTL_index] = items[items_index];
            FTL_index++;
            items_index++;
        }

        for(int i = 0; i < firstToLast.length; i++) {
            System.out.print(firstToLast[i]);
        }
    }
}

