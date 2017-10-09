"""
file: mentoring_center.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
"""

from club import *
from line import *
from person import *

def convertFile(filename):
    """
    This converts the textfile to a list
    :param filename: textfile used
    :return: a list is returned
    """
    file = open(filename)
    wordList = []
    for currentLine in file:
        wordList.append((currentLine.strip()))
    return wordList

def main():
    """
    This executes the program
    :return:
    """
    textfile = str(input("Enter the filename:"))
    lst = (convertFile(textfile))
    print(lst)
    stack = None
    processCommands(lst, stack, queue)

if __name__ == '__main__':
    main()

