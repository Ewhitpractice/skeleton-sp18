public class LinkedListDeque<T>
{
    private final ItemNode<T> sentinel;
    private int size;

    private static class ItemNode<T>
    {
        public ItemNode<T> prev; //arrow to the previous element
        public T item; //the item stored in the list
        public ItemNode<T> next; //arrow to the next element

        public ItemNode(T x)
        {
            prev = null;
            next = null;
            item = x;
        }
    }

    public LinkedListDeque()
    {
        sentinel = new ItemNode<>(null);
        size = 0;
    }

    public void addFirst(T item)
    {
        ItemNode<T> first_item = new ItemNode<>(item);

        if(size == 0)
        {
            sentinel.next = first_item;
            first_item.prev = sentinel;
            sentinel.prev = first_item;
            first_item.next = sentinel;
        }
        ItemNode<T> second_item = sentinel.next;
        first_item.next = second_item;
        sentinel.next = first_item;
        second_item.prev = first_item;
        first_item.prev = sentinel;
        size = size + 1;
    }

    public void addLast(T item)
    {
        ItemNode<T> last_item = new ItemNode<>(item);
        if(size == 0)
        {
            sentinel.next = last_item;
            last_item.prev = sentinel;
            last_item.next = sentinel;
            sentinel.prev = last_item;
        }
        ItemNode<T> second_to_last = sentinel.prev;
        second_to_last.next = last_item;
        last_item.prev = second_to_last;
        last_item.next = sentinel;
        sentinel.prev = last_item;
        size = size + 1;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public void printDeque()
    {
        ItemNode<T> pointer = new ItemNode<>(sentinel.next.item);
        while(pointer != null)
        {
            String print_object = pointer.item.toString();
            System.out.println(print_object);
            pointer = pointer.next;
        }
    }

    public T removeFirst()
    {
        T removed = sentinel.next.item;
        ItemNode<T> to_remove = sentinel.next;
        if(size == 0)
        {
            return null;
        }
        if(size == 1)
        {
            sentinel.next = null;
            sentinel.prev = null;
            size = 0;
            return removed;
        }
        ItemNode<T> new_first = to_remove.next;
        sentinel.next = new_first;
        new_first.prev = sentinel;
        to_remove.next = null;
        to_remove.prev = null;
        size = size - 1;
        return removed;
    }

    public T removeLast()
    {
        T removed = sentinel.next.item;
        ItemNode<T> to_remove = sentinel.next;
        if(size == 0)
        {
            return null;
        }
        if(size == 1)
        {
            sentinel.next = null;
            sentinel.prev = null;
            size = 0;
            return removed;
        }
        ItemNode<T> new_last = to_remove.prev;
        sentinel.prev = new_last;
        new_last.next = sentinel;
        to_remove.next = null;
        to_remove.prev = null;
        size = size-1;
        return removed;
    }

    public T get(int index)
    {
        ItemNode<T> pointer = new ItemNode<>(sentinel.next.item);
        while(index > 0)
        {
            pointer = pointer.next;
            index = index - 1;
        }
        return pointer.item;
    }

    private T getRecursive(int index, ItemNode<T> current_node)
    {
        if(index == 0) {
            return sentinel.next.item;
        }
        return getRecursive(index-1,current_node.next);
    }
}
