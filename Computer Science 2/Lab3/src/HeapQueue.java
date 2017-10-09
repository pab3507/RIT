import java.util.ArrayList;
/**
 * Name: Moisés Lora Pérez
 * Email: mal3941@rit.edu
 * Class: CSCI-142 Professor Strout
 * Language: Java 8
 */
public class HeapQueue<T extends Prioritizable> implements PriorityQueue<T> {

    /**
     * The storage for the heap data
     */
    private ArrayList<T> theHeap;

    /**
     * Construct an empty heap.
     */
    public HeapQueue() {
        theHeap = new ArrayList<>();
    }

    @Override
    public T dequeue() {
        if (theHeap.isEmpty())
            return null;
        // keep the top of the heap to return later
        T toReturn = theHeap.get(0);
        // if that wasn't the last one, take the end of the heap to the
        // root and bubble it down.
        if (theHeap.size() > 1) {
            theHeap.set(0, theHeap.remove(theHeap.size() - 1));
            int maxc, whereamI = 0;
            while ((maxc = maxChild(whereamI)) != -1) {
                // bubbling down
                T temp = theHeap.get(whereamI);
                theHeap.set(whereamI, theHeap.get(maxc));
                theHeap.set(maxc, temp);
                whereamI = maxc;
            }
        } else {
            // simply remove the root and be happy!
            theHeap.remove(0);
        }
        return toReturn;
    }

    /**
     * Computes the child with max priority - but also counting the parent,
     * so that if both children are smaller than the parent, -1 is returned.
     * Also returns -1 if there are no children of the given index.
     *
     * @param index
     * @return index of child with largest priority, or -1
     */
    private int maxChild(int index) {
        double maxPrio = theHeap.get(index).getPriority();
        int maxc = -1;
        // children of index are 2*index+1, 2*index+2
        // if first child exists, is it higher than me?
        if (2 * index + 1 < theHeap.size()) {
            if (theHeap.get(2 * index + 1).getPriority() > maxPrio) {
                maxPrio = theHeap.get(2 * index + 1).getPriority();
                maxc = 2 * index + 1;
            }
            // how about a second child?
            if (2 * index + 2 < theHeap.size()) {
                if (theHeap.get(2 * index + 2).getPriority() > maxPrio) {
                    maxc = 2 * index + 2;
                }
            }
        }
        return maxc;
    }

    @Override
    public void insert(T toInsert) {
        // put at the end and bubble up!
        theHeap.add(toInsert);
        int whereamI = theHeap.size() - 1;
        int parentLoc = whereamI / 2;
        // bubble if I am not root and am higher priority than my parent.
        while ((whereamI > 0) &&
                (theHeap.get(whereamI).getPriority() >
                        theHeap.get(parentLoc).getPriority())) {
            T temp = theHeap.get(whereamI);
            theHeap.set(whereamI, theHeap.get(parentLoc));
            theHeap.set(parentLoc, temp);
            whereamI = parentLoc;
            parentLoc = whereamI / 2;
        }
    }

    @Override
    public boolean isEmpty() {
        return theHeap.isEmpty();
    }

}
