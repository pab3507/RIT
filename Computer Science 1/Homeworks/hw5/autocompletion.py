"""
file: autocompletion.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
description: This program given a prefix uses binary or linear search to match up to a word in a list.
"""

def binary_search(data, prefix, start, end ):
    """
    Perform a binary search for a target value between start and end indices.
    Parameters:
        data - a list of sorted data
        target - the target value to search for
        start - the starting index in data that is part of this search
        end - the ending index in data that is part of this search
    Returns:
        index of target in data, if present; otherwise None.
    """
    if start > end:
        return None

    mid_index = (start + end) // 2
    mid_value = data[mid_index]

    if mid_value[:len(prefix)] == prefix:
        return data[mid_index]
    elif prefix < mid_value:
        return binary_search(data, prefix, start, mid_index-1)
    else:
        return binary_search(data, prefix, mid_index+1, end)

def get_index( data, prefix ):
    """
    get_index : List(Orderable) Orderable -> NatNum or NoneType
    get_index returns the index of target in data or None if not target found.
    Parameters:
        data - a list of sorted data
        target - the target value to search for
    Returns:
        The index of the target element in data, if it is present,
        otherwise None.
    """

    # search for the target across all elements in data
    return binary_search(data, prefix, 0, len(data) - 1 )

def linear_search(data,prefix):
    """
    This searches at values one by one
    :param data: list used
    :param prefix: prefix looked for
    :return:
    """
    for currentline in range(0, len(data)):
        currentElement = data[currentline]
        if str(currentElement[0:len(prefix)]) == str(prefix):
            return data[currentline]

def convertList(filename):
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

def main():
    textFile = str(input("Enter the filename:"))
    list = []
    list = (convertList(textFile))
    print(list)
    search = input('What kind of search would you like to use [binary / linear]:')

    if str(search) == 'binary':
        while input != "":
            psearchb = str(input('Please enter a prefix to search for:'))
            print(get_index(list, psearchb))

    elif str(search) == 'linear':
        checkerl = True
        while checkerl == True:
            if input == "":
                checkerl = False
            psearchl = str(input('Please enter a prefix to search for:'))
            print(linear_search(list, psearchl))

#this executes the program
main()