public class ArrayDeque<T>
{
    private T[] items;
    int size;
    int nextFirst = 4;
    int nextLast = 5;
    int REFACTOR = 2;

    public ArrayDeque()
    {
        items = (T[]) new Object[8];
    }

    private void resize(int capacity)
    {
        T[] items2 = (T[]) new Object[capacity];
        System.arraycopy(items2,0,items,0,size);
        items = items2;
    }

    public void addLast(T item)
    {
        if(size == items.length)
        {
            resize(size*REFACTOR);
        }

        if(nextLast == items.length)
        {
            nextLast = 0;
        }
        items[nextLast] = item;
        nextLast++;
        size++;
    }

    public void addFirst(T item)
    {
        if(size == items.length)
        {
            resize(size*REFACTOR);
        }

        items[nextFirst] = item;
        if(nextFirst == 0)
        {
            nextFirst = items.length-1;
        }
        nextFirst--;
        size++;
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
        T[] arrayToPrint = (T[]) new Object[size];
        int array_to_print_index = 0;
        int current_index = nextFirst+1;
        while(current_index != nextLast-1)
        {
            if(current_index == items.length)
            {
                current_index = 0;
            }
            arrayToPrint[array_to_print_index] = items[current_index];
            array_to_print_index++;
            current_index++;
        }

        for(int i=0;i<size;i++)
        {
            System.out.print(arrayToPrint[i]);
        }
    }

    public T removeFirst()
    {
        int remove_index;
        if(size == 0)
        {
            return null;
        }

        if(nextFirst + 1 == items.length-1)
        {
            remove_index = 0;
            nextLast = remove_index;
        }
        remove_index = nextFirst + 1;
        T removed = items[remove_index];
        items[remove_index] = null;
        nextFirst = remove_index;
        return removed;
    }

    public T removeLast()
    {
        int remove_index;
        if(size == 0)
        {
            return null;
        }
        if(nextLast == 0)
        {
            remove_index = items.length-1;
            nextLast = remove_index;
        }
        remove_index = nextLast - 1;
        T removed = items[remove_index];
        items[remove_index] = null;
        nextFirst = remove_index;
        return removed;
    }

    public T get(int index)
    {
        T[] firstToLast = (T[]) new Object[size];
        int FTL_index = 0;
        int items_index = nextFirst+1;
        while(items_index != nextLast-1)
        {
            if(items_index == items.length)
            {
                items_index = 0;
            }
            firstToLast[FTL_index] = items[items_index];
            FTL_index++;
            items_index++;
        }
        return firstToLast[index];
    }


