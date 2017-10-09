"""
file: sort_compare.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03

description: This program compares the different time runs of some of the sorting algorithms that we have learned
so far. The program creates a random list and then proceeds to time the time it takes to sort that list with the
different sorting algorithms.
"""

from time import *
from random import *
from array_heap import *

def swap(lst,i,j):
    """
    This swap function swaps the index of i to the one in j.
    :param lst: Inputed list
    :param i: index
    :param j: other index
    :return: swapped list
    """
    temp = lst[i]
    lst[i] = lst[j]
    lst[j] = temp

def minFrom(lst, mark):
    """
    This function returns the minimum current index of the list
    :param lst: inputed list
    :param mark: current index
    :return: the minimum current index of the list
    """
    currIndex = mark
    for i in range(mark, len(lst)):
        if lst[currIndex] > lst[i]:
            currIndex = i
    return currIndex

def compareFunc(num1,num2):
    """
    This function returns a boolean whether num1 is smaller than num2.
    :param num1:
    :param num2:
    :return:
    """
    return num1 < num2

#-------------------- Insertion Sort --------------------

def insert(lst,mark):
    """
    This function decreases the value of the index
    :param lst: Inputed list
    :param mark: index
    """
    index = mark
    while index > -1 and lst[index] > lst[index+1]:
        swap(lst, index, index +1)
        index -= 1

def insertionSort(lst):
    """
    This function sorts the list in a ascending matter

    :param lst: inputed list
    :return: list sorted in descending order
    """
    for mark in range(len(lst)-1):
        insert(lst, mark)
    return lst

# -------------------- Selection Sort --------------------

def selectionSort(lst):
    """
    This function returns the sequence of values that can be cut into two sub-sequences such that the first
    sub-sequence is in order and all the elements are less than or equal to the elements of the second sub-sequence.
    :param lst: inputed list
    :return: the sorted list
    """
    for i in range(0, len(lst)-1):
        swap(lst, i, minFrom(lst,i))
    return lst

# ---------------------- Heap Sort ----------------------

def heapSort(lst):
    """
    This function creates an empty heap then goes through the list and adds each item to the heap. Then while the size
    of the heap is greater than 0, it appends to a new list the values which the removal function from array heap
    returns.
    :param lst: inputed list
    :return: the sorted list
    """
    arrayHeap = createEmptyHeap(len(lst),compareFunc)
    newList = []
    for item in lst:
        arrayHeap.add(item)
    while arrayHeap.size > 0:
        newList.append(arrayHeap.remove())
    return newList

# ---------------------- Merge Sort ----------------------

def mergeSort(L):
    """
    mergeSort: List( A ) -> List( A )
    where A is totally ordered
    """
    if L == []:
        return []
    elif len(L) == 1:
        return L
    else:
        ( half1, half2 ) = split(L)
        return merge(mergeSort(half1), mergeSort(half2))


def split(L):
    """
    split: List( A ) -> Tuple( List( A ), List( A ) )
    """
    evens = []
    odds = []
    isEvenIndex = True
    for e in L:
        if isEvenIndex:
            evens.append(e)
        else:
            odds.append(e)
        isEvenIndex = not isEvenIndex
    return ( evens, odds )


def merge(sorted1, sorted2):
    """
    merge: List( A ) * List( A ) -> List( A )
      precondition: sorted1 and sorted2 are lists whose elements are in order
    """
    result = []
    index1 = 0
    index2 = 0
    while index1 < len(sorted1) and index2 < len(sorted2):
        if sorted1[index1] <= sorted2[index2]:
            result.append(sorted1[index1])
            index1 = index1 + 1
        else:
            result.append(sorted2[index2])
            index2 = index2 + 1
    if index1 < len(sorted1):
        result.extend(sorted1[index1:])
    elif index2 < len(sorted2):
        result.extend(sorted2[index2:])
    return result

# ---------------------- Quick Sort ----------------------

def quickSort( L ):
    """
    quickSort: List( A ) -> List( A )
    where A is 'totally ordered'
    """
    if L == []:
        return []
    else:
        pivot = L[0]
        ( less, same, more ) = partition( pivot, L )
        return quickSort( less ) + same + quickSort( more )

def partition( pivot, L ):
    """
    partition: A * List( A ) -> Tuple( List( A ), List( A ), List( A ) )
    where A is totally ordered
    """
    ( less, same, more ) = ( [], [], [] )
    for e in L:
        if e < pivot:
            less.append( e )
        elif e > pivot:
            more.append( e )
        else:
            same.append( e )
    return ( less, same, more )

# ---------------------- Random List ----------------------

def createRandomList(min,max,size):
    randomList = []
    for i in range(size):
        random = randint(min,max)
        randomList.append(random)
    return randomList

# ---------------------- Time Sort ----------------------

def timeSort(sortFunction,lst):
    listToRun = lst[:]
    listToCompare = lst[:]
    listToCompare.sort()
    start = time()
    listToRun = sortFunction(listToRun)
    end = time()
    if listToRun == listToCompare:
        return end-start
    return -1

# -------------------- Main Function --------------------

def main():
    """
    This function executes the program. It asks the user for input on a minimum and maximum values for the random list,
    plus a size for the corresponding list. Then it proceeds to print the time that it takes to sort the list with each
    sorting algorithm.
    """
    print("----------------- Random List Constants ------------------ ")
    min = int(input("What is the minimum possible value of an item in the list: "))
    max = int(input("What is the maximum possible value of an item in the list: "))
    size = int(input("What is the size of the list: "))
    print("---------------------- Random List ----------------------- ")
    lst = createRandomList(min, max, size)
    print(lst)
    print("----------- Sorting Algorithm's Time -------------")
    print("Insertion Sort Time:", timeSort(insertionSort,lst), "seconds")
    print("Selection Sort Time:", timeSort(selectionSort, lst), "seconds")
    print("Heap Sort Time:", timeSort(heapSort, lst), "seconds")
    print("Merge Sort Time:", timeSort(mergeSort, lst), "seconds")
    print("Quick Sort Time:", timeSort(quickSort, lst), "seconds")

main()

