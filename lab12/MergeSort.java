import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        int originalSize = items.size();
        Queue<Queue<Item>> singleItemQueue = new Queue<Queue<Item>>();
        for(int i = 0; i < originalSize; i++) {
            Queue singleItem = new Queue<Item>();
            singleItem.enqueue(items.dequeue());
            singleItemQueue.enqueue(singleItem);
        }
        return singleItemQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> sortedQueuesMerged = new Queue<Item>();
        while(q1.size() > 0 || q2.size() > 0) {
            Item smallest = getMin(q1,q2);
            sortedQueuesMerged.enqueue(smallest);
        }
        while(q1.size() > 0) {
            sortedQueuesMerged.enqueue(q1.dequeue());
        }
        while(q2.size() > 0) {
                sortedQueuesMerged.enqueue(q2.dequeue());
            }
        return sortedQueuesMerged;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        Queue<Queue<Item>> singleItemQueue= makeSingleItemQueues(items);

        while(singleItemQueue.size() > 1) {
            Queue<Item> q1 = singleItemQueue.dequeue();
            Queue<Item> q2 = singleItemQueue.dequeue();
            Queue<Item> mergeSorted = mergeSortedQueues(q1,q2);
            singleItemQueue.enqueue(mergeSorted);
        }
        return singleItemQueue.dequeue();
    }

    public static void main(String args[]) {
        Queue<String> originalQueue = new Queue();
        originalQueue.enqueue("Anna");
        originalQueue.enqueue("Natalia");
        originalQueue.enqueue("Dan");
        originalQueue.enqueue("Zeke");
        originalQueue.enqueue("Ryan");
        originalQueue.enqueue("Ted");
        originalQueue.enqueue("Jenny");
        System.out.println(originalQueue);

        Queue<String> sortedQueue = MergeSort.mergeSort(originalQueue);
        System.out.println(originalQueue);
        System.out.println(sortedQueue);
    }
}
