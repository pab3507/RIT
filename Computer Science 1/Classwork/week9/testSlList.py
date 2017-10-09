#!/usr/local/bin/python3

"""
File: testList.py
Author: Jeremy Brown (jsb@cs.rit.edu)
Language: Python 3
Description:  A test module for the linked list data structure, SlList.
"""
from slList import *

def main():
    """
    main asks user to choose between
    tests using iterative and recursive linked list modules.
    """

    myList = createList()
    myList.append("a")
    myList.append("b")
    print(myList)

    myCursor = myList.getCursor()
    while myCursor.hasNext():
        print(myCursor.moveNext())


    
# main is the tester.
if __name__ == "__main__":
    main()
