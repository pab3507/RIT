/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public interface PriorityQueue<T extends Prioritizable> {

    /**
     * Is there anything in the queue
     *
     * @return queue is empty.
     */
    boolean isEmpty();

    /**
     * Add an item to the queue (at the appropriate location)
     *
     * @param toInsert Item to be added
     */
    void insert(T toInsert);

    /**
     * Removes and returns the item at the front of the queue.
     *
     * @return Removed element
     */
    T dequeue();
}
