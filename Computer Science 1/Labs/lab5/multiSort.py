"""
file: multiSort.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
"""
def swap(lst,i,j):
    """
    This swap function swaps the index of i to the one in j.
    :param lst: Inputed list
    :param i: index
    :param j: other index
    :return: swapped list
    """
    """
    temp = lst[i]
    lst[i] = lst[j]
    lst[j] = temp
    """
    #lst[i], lst[j] = lst[j], lst[i]

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
    This function sorts the list in a descending matter
    :param lst: inputed list
    :return: list sorted in descending order
    """
    for mark in range(len(lst)-1):
        insert(lst, mark)
    return lst

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

def perkSort(lst):
    """
    This function swaps the elements whenever the left element is larger.
    :param lst: inputed list
    :return: the list sorted
    """
    swapMade = True
    while swapMade:
        swapMade=False
        for i in range(0, len(lst)-1):
            if lst[i] > lst[i+1]:
                swap(lst, i, i+1)
                swapMade = True
    return lst

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

def professorFurySorted(lst):
    """
    Returns the sorting.
    :param lst: inputed list
    :return: sorted list
    """

    return professorFurySort(lst, 0, len(lst)-1)

def professorFurySort(lst, start, end):
    """
    This function sorts recursively if the first item is larger than the last one then swaps them if its greater
    than two, it'll sort the first 2/3 of the list , then last and again the first and then return the list.
    :param lst:
    :param start:
    :param end:
    :return:
    """
    if end is None:
        end = len(lst)-1
    if lst[start] > lst[end]:
        swap(lst,start,end)

    if end-start > 1:
        lst = professorFurySort(lst, start, (2*end//3))
        lst = professorFurySort(lst, start + (end//3)+1, end)
        lst = professorFurySort(lst, start, (2*end//3))

    return lst

def convertFile(filename):
    """
    This converst the textfile to a list
    :param filename: textfile used
    :return: a list is returned
    """
    file = open(filename)
    wordList = []
    for currentLine in file:
        wordList.append((currentLine.strip()))
    return wordList

def testing(lst,expected):
    """
    This tests the list with the expected one.
    :param lst: inputed list
    """
    emptylist = []
    expected = []
    testSort(lst,expected)
    singleElementlist = [1]
    expected = [1]
    testSort(lst,expected)
    reverseSorteda = [5,4,3,2,1]
    expected = [1,2,3,4,5]
    testSort(lst,expected)
    reverseSortedb = [10,9,8,7,6]
    expected = [6.7,8,9,10]
    testSort(lst,expected)
    sorteda = [1,2,3,4,5]
    expected = [1,2,3,4,5]
    testSort(lst,expected)
    sortedb = [6,7,8,9,10]
    expected = [6,7,8,9,10]
    testSort(lst,expected)
    randoma = [45,60,79,92]
    expected = [45,60,79,92]
    testSort(lst,expected)

def testSort(lst, expected):
    """
    This functions tests the sorting algorithms with the expected lists.
    :param lst: test list
    :param expected: expected list
    :return: boolean value
    """
    if insertionSort(lst) == expected:
        return True
    else:
        return False

    if perkSort(lst) == expected:
        return True
    else:
        return False
    if selectionSort(lst) == expected:
        return True
    else:
        return False
    if professorFuryHelp(lst) == expected:
        return True
    else:
        return False


def main():

    #this executes the function

    textFile = str(input("Enter the filename:"))
    lst = [55, 100, 92, 66, 35, 54, 26, 7, 70]
    print(selectionSort(lst))

main()

