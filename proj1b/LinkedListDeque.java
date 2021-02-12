public class LinkedListDeque<T> implements Deque<T> {
    private final ItemNode<T> sentinel;
    private int size;

    private static class ItemNode<T> {
        private ItemNode<T> prev; //arrow to the previous element
        private T item; //the item stored in the list
        private ItemNode<T> next; //arrow to the next element

        public ItemNode(T x) {
            prev = null;
            next = null;
            item = x;
        }
    }

    public LinkedListDeque() {
        sentinel = new ItemNode<>(null);
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        ItemNode<T> firstItem = new ItemNode<>(item);
        if (size == 0) {
            sentinel.next = firstItem;
            firstItem.prev = sentinel;
            sentinel.prev = firstItem;
            firstItem.next = sentinel;
        }
        ItemNode<T> secondItem = sentinel.next;
        firstItem.next = secondItem;
        sentinel.next = firstItem;
        secondItem.prev = firstItem;
        firstItem.prev = sentinel;
        size = size + 1;
    }

    @Override
    public void addLast(T item) {
        ItemNode<T> lastItem = new ItemNode<>(item);
        if (size == 0)
        {
            sentinel.next = lastItem;
            lastItem.prev = sentinel;
            lastItem.next = sentinel;
            sentinel.prev = lastItem;
        }
        ItemNode<T> second_to_last = sentinel.prev;
        second_to_last.next = lastItem;
        lastItem.prev = second_to_last;
        lastItem.next = sentinel;
        sentinel.prev = lastItem;
        size = size + 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque() {
        ItemNode<T> pointer = new ItemNode<>(sentinel.next.item);
        while (pointer != null) {
            String print_object = pointer.item.toString();
            System.out.println(print_object);
            pointer = pointer.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removed = sentinel.next.item;
        if (size == 1) {
            sentinel.next = null;
            sentinel.prev = null;
            size = 0;
            return removed;
        }
        ItemNode<T> toRemove = sentinel.next;
        ItemNode<T> newFirst = toRemove.next;
        sentinel.next = newFirst;
        newFirst.prev = sentinel;
        toRemove.next = null;
        toRemove.prev = null;
        size = size - 1;
        return removed;
    }

    @Override
    public T removeLast()
    {
        if (size == 0) {
            return null;
        }
        T removed = sentinel.prev.item;
        if (size == 1) {
            sentinel.next = null;
            sentinel.prev = null;
            size = 0;
            return removed;
        }
        ItemNode<T> toRemove = sentinel.prev;
        ItemNode<T> newLast = toRemove.prev;
        sentinel.prev = newLast;
        newLast.next = sentinel;
        toRemove.next = null;
        toRemove.prev = null;
        size = size - 1;
        return removed;
    }

    @Override
    public T get(int index)
    {
        ItemNode<T> pointer = sentinel.next;
        while (index > 0) {
            pointer = pointer.next;
            index = index - 1;
        }
        return pointer.item;
    }

    private T getRecursive(int index, ItemNode pointer) {
        if (index == 0)
            return (T)pointer.item;
        return getRecursive(index - 1, pointer.next);
    }
    public T getRecursive(int index) {
        if (index == 0)
            return sentinel.next.item;
        return getRecursive(index - 1, sentinel.next.next);
    }
}

