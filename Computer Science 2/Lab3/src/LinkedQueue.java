/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class LinkedQueue<T extends Prioritizable> implements PriorityQueue<T>{

    private double size; //variable for size of the linked queue
    private Node<T> front; // node variable for the front

    public LinkedQueue() {
        this.size = 0;
        this.front = null;
    }

    @Override
    public T dequeue() {
        /**
         * Dequeue method checks for an empty queue and then returns the front of the queue. Also reduces size.
         */
        if(this.isEmpty()){
            System.out.println("Empty Queue");
        }
        T newQueue = front.getData();
        front = front.getNext();
        size -=1;
        return newQueue;
    }

    @Override
    public void insert(T toInsert) {
        /**
         * Method creates a new Node and then checks for an empty queue, if true then the Front of the Queue is equal to
         * the Node and the size gets incremented, or if the Node's priority is bigger than the front priority, the next
         * Node is set to front and front is equaled to Node and size incremented, or else the Node's previus and next
         * values change as well.
         *
         */
        Node<T> Node = new Node<T>(toInsert, null);
        if (isEmpty()) {
            front = Node;
            size++;
        }
        else if (Node.getData().getPriority() > front.getData().getPriority()) {
            Node.setNext(front);
            front = Node;
            size++;
        }
        else {
            Node<T> previous = front;
            Node<T> next = front.getNext();
            while (next != null && Node.getData().getPriority() <= previous.getData().getPriority()) {
                if (Node.getData().getPriority() > next.getData().getPriority()) {
                    previous.setNext(Node);
                    Node.setNext(next);

                } else {
                    previous = next;
                    next = next.getNext();
                }

            }
        }
    }

    @Override
    public boolean isEmpty() {
        /**
         * Returns a boolean whether the size of the Queue is equal to 0.
         */
        return size == 0;

    }

}