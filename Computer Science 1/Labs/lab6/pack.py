"""
file: pack.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
"""

from rit_lib import *

class Bin( struct ):
    """
    This class represents the properties of the bin
    :slot grid (list): this list represents the grid for the bin
    :slot freespace (int): represents the space available in the bin
    :slot unpacked (list): represents the unpacked blocks
    """
    _slots = ((list, 'grid'), (int, 'freespace'), (list, 'unpacked'))

def createBin(size):
    """
    This function creates an initial bin with all empty spaces(0).
    :param size: This is the size of the bin's dimensions.
    :return: returns a created empty bin depending on the size.
    """
    bin = []
    for row in range(size):
        bin.append([])
        for col in range(size):
            bin[row].append(0)
    return Bin(bin, size*size, [])

def isSpaceFree(bin, row, col, block):
    """
    This function checks whether a free space is available on the bin.
    :param bin: the complete bin
    :param row: a row inside the grid of the bin
    :param col: a column inside the grid of the bin
    :param block: variable for block to be packed
    :return: A boolean is returned depending if there's a free space or not
    """
    if row + block > len(bin.grid) or col + block > len(bin.grid):
        return False
    for r in range(row,block+row):
        for c in range(col, block+col):
            if bin.grid[r][c] != 0:
                return False
    return True

def packBin(bin,blocks):
    """
    This function packs the bin according to the dimensions of the blocks.
    It also uses the function isSpaceFree to check whether a free space is available, if there is
    then the space is filled, otherwise the space is unpacked.
    :param bin: complete bin
    :param blocks: variable for blocks to be packed
    """
    for b in blocks:
        added = False
        for r in range(len(bin.grid)):
            for c in range(len(bin.grid)):
                if isSpaceFree(bin,r,c, b) and not added:
                    added = True
                    bin.freespace -= b*b
                    for r2 in range(r,r+b):
                        for c2 in range(c,c+b):
                            bin.grid[r2][c2] = b
        if not added:
            bin.unpacked.append(b)

def convertFile(filename):
    """
    This function converts a text file into a sorted list.
    :param filename: the file to be converted
    :return: the sorted list
    """
    result = []
    with open(filename, 'r', encoding='utf-8') as file:
        line = file.readline()
        for c in line:
            if c != ' ':
                result.append(int(c))
    result.sort()
    result.reverse()
    return result

def main():
    """
    This function asks the user for inputs and also outputs the correct results
    the packed bin, number of free spaces, and the list of unpacked blocks.
    """
    binsize = int(input("Enter square bin dimension: "))
    textFile = input("Block file: ")
    blocklist = (convertFile(textFile))
    binCreated = createBin(binsize)
    packBin(binCreated,blocklist)
    for row in range(binsize):
        for col in range(binsize):
            print(binCreated.grid[row][col], end=" ")
        print()
    print("Free Spaces: " + str(binCreated.freespace))
    print("Unpacked blocks: " + str(binCreated.unpacked))

main()
