from slList import *

def readFile( filename ):
    """
    This function creates a linked list then it opens a file and appends the contents of the file to the linked list.
    :param filename: Inputed file
    :return: the appended linked list is returned.
    """

    myList = createList()
    file = open(filename)
    for currentLine in file:
        myList.append(int(currentLine))
    return myList


def main():
    """
    In the main function, the user inputs a filename, then the file gets converted to a linked list, gets converted
    to a string(with the toString() function) and that is then printed. Then the linked list is sorted with the
    linkSort function and finally printed(again as a string).
    """
    filename = input("Input filename: ")
    linkedList = readFile(filename)
    printList = linkedList.toString()
    print(printList)
    linkSort(linkedList)
    sortedList = linkedList.toString()
    print(sortedList)

main()