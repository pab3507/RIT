from rit_lib import *

def swap(lst,i,j):
    temp = lst[i]
    lst[i] = lst[j]
    lst[j] = temp

def insert(lst,mark):
    index = mark
    while index > -1 and lst[index] < lst[index+1]:
        swap(lst, index, index +1)
        index -= 1

def isSpaceFree(bin, row, col, block):
    if block + row > len (bin):
        return False
    if block + col > len(bin):
        return False
    for r in range (row,block+row):
        for c in range(col, block+col):
            if bin[r,c] != 0:
                return False
    return True

def insertionSort(lst):

    for mark in range(len(lst)-1):
        insert(lst, mark)
    return lst

def convertFile(filename):
    result = []
    with open(filename, 'r', encoding='utf-8') as file:
        line = file.readline()
        for c in line:
            if c != ' ':
                result.append(int(c))
    return result

class bin( struct ):
    _slots = ((list, 'grid'), (int, 'row',), (int, 'col'))

def binStructure(bin,row,col,block):
        bin = []
        bin.append([])
        for col in range():
            pass

def main():
    textFile = str(input("Enter the filename:"))
    lst = (convertFile(textFile))
    print(insertionSort(lst))


main()
