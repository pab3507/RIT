"""
A Heap implemented by 
mapping a tree onto an array (Python list) of the same size.
file: array_heap.py
language: python3

new language feature: passing (and storing) functions as arguments.
"""

import copy
from rit_lib import *

# Utility functions to map tree relations to array indices ###

def parent(i):
    """
       Return index of parent of node at index i.
    """
    return (i - 1)//2  

def lChild(i):
    """
       Return index of left child of node at index i.
    """
    return 2*i + 1

def rChild(i):
    """
       Return index of right child of node at index i.
    """
    return 2*i + 2

############################################################################
def createEmptyHeap(maxSize, compareFunc):
    """
       createEmptyHeap : NatNum * Function -> Heap
       Create an empty heap with capacity maxSize
       and comparison function compareFunc.
       Return initialized heap.
    """

    heap = Heap([None for _ in range(maxSize)], 0, compareFunc)
    return heap

class Heap(struct):
    """
       A heap inside an array that may be bigger than the
       heapified section of said array
       SLOTS:
           array: the Python list object used to store the heap
           size: the number of array elements currently in the
                 heap. (size-1) is the index of the last element.
           compareFunc: A function to compare values in the heap.
                  For example, if compareFunc performs less-than,
                  then the heap will be a min-heap.
    """
    _slots = ((list, 'array'), (int, 'size'), (object, 'compareFunc'))



    def displayHeap(self, startIndex=0, indent=""):
        """
           displayHeap : Heap * NatNum * String -> NoneType
           Display the heap as a tree with each child value indented
           from its parent value. Traverse the tree in preorder.
        """
        if startIndex < self.size:
            print(indent + str(self.array[startIndex]))
            self.displayHeap(lChild(startIndex), indent + '    ')
            self.displayHeap(rChild(startIndex), indent + '    ')

    def siftUp(self, startIndex):
        """
           siftUp : Heap * NatNum -> NoneType
           Move the value at startIndex up to its proper spot in
           the given heap. Assume that the value does not have
           to move down.
        """
        i = startIndex
        a = self.array
        while i > 0 and not self.compareFunc(a[parent(i)], a[i]):
            (a[parent(i)], a[i]) = (a[i], a[parent(i)])     # swap
            i = parent(i)

    def _first_of_3(self, index):
        """
        _first_of_3 : Heap * NatNum -> NatNum
        _first_of_3 is a private, utility function.
           Look at the values at:
           - index
           - the left child position of index, if in the heap
           - the right child position of index, if in the heap
           and return the index of the value that should come
           first, according to heap.compareFunc().
        """
        lt = lChild(index)
        rt = rChild(index)
        thisVal = self.array[index]
        if rt < self.size:        # If there are both left and right children
            lVal = self.array[lt]
            rVal = self.array[rt]
            if self.compareFunc(lVal, thisVal)    \
            or self.compareFunc(rVal, thisVal):
                if self.compareFunc(lVal, rVal):
                    return lt # The left child goes first
                else:
                    return rt # The right child goes first
            else:
                    return index # This one goes first
        elif lt < self.size: # If there is only a left child
            lVal = self.array[lt]
            if self.compareFunc(lVal, thisVal):
                return lt # The left child goes first
            else:
                return index # This one goes first
        else: # There are no children
            return index

    def siftDown(self, startIndex):
        """
           siftDown : Heap * NatNum -> NoneType
           Move the value at startIndex down to its proper spot in
           the given heap. Assume that the value does not have
           to move up.
        """
        curIndex = startIndex
        a = self.array
        swapIndex = self._first_of_3( curIndex)
        while (swapIndex != curIndex):
            (a[swapIndex], a[curIndex]) = (a[curIndex], a[swapIndex]) # swap
            curIndex = swapIndex
            swapIndex = self._first_of_3(curIndex)

    def add(self, newValue):
        """
           add : Heap * Comparable -> NoneType
           add inserts the element at the correct position in the heap.
        """
        if self.size == len(self.array):
            self.array = self.array + ([None] * len(self.array))
        self.array[self.size] = newValue
        self.siftUp(self.size)
        self.size = self.size + 1

    def remove(self):
        """
           remove : Heap -> Comparable
           remove removes and returns the root element in the heap.
        """
        res = self.array[0]
        self.size = self.size - 1
        self.array[0] = self.array[self.size]
        self.array[self.size] = None
        self.siftDown(0)
        return res

    def updateValue(self, index, newValue):
        """
           Fix the heap after changing the value in one of its nodes.
        """
        oldValue = self.array[index]
        self.array[index] = newValue
        if self.compareFunc(newValue, oldValue):
            self.siftUp(index)
        else:
            self.siftDown(index)

    def peek(self):
        """
           peek : Heap -> Comparable
           peek returns a deep copy of the current root/top of the heap
        """
        res = copy.deepcopy(self.array[0])
        return res
############################################################################

def less(a, b):
    """
       less : Comparable * Comparable -> Boolean
       This ordering function returns True if the first value is smaller.
    """
    return a <= b

def greater(a, b):
    """
       greater : Comparable * Comparable -> Boolean
       This ordering function returns True if the first value is larger.
    """
    return a >= b




def testHeap( testData ):
    """
    testHeap : TupleOfComparable -> NoneType
    Create a min heap, fill it with the test data, and display it.
    """
    print( "testHeap(", testData, "):" )

    heap = createEmptyHeap(len(testData), less)

    for i in range(len(testData)):
        heap.add( testData[i])
        if i % 2 == 0: print( i, "-th iteration's root:", heap.peek( ) )

    print("Heap size is now", heap.size)
    heap.displayHeap()
    print()

    # Perform some heap modifications. Tests updateValue().
    for (index, value) in ((1, 100), (4, -1)):
        print("Change value at position", index, "to", value)
        heap.updateValue(index, value)
        heap.displayHeap()
    print( "current root:", heap.peek( ) )

if __name__ == '__main__':

    testData = (1, 3, 5, 7, 9, 10, 8, 6, 4, 2, 0)     # Test data
    testHeap( testData )

