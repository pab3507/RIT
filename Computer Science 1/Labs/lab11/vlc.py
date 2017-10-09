"""
file: vlc.py
author: mal3941@g.rit.edu Moises Lora Perez
class: CSCI 141-03
"""
from rit_lib import *
from array_heap import *
from math import *

class SymbolObject( struct ):
    """
    Represents a the symbol object.
    :slot name (str): The name of the symbol.
    :slot frequency (int): The symbol's frequency.
    :slot codeword (str): The codeword corresponding to the symbol.
    """
    _slots = ((str, 'name'), (int, 'frequency'), (str, 'codeword'))

class Node( struct ):
    """
    Represents a node made of symbols.
    :slot cum (int): The cumulative frequency of the symbols on the node..
    :slot symbols (lst): The list of symbols contained in the node.
    """
    _slots = ((int, 'cum'),(list, 'symbols'))

def createSymbolObject(symbol,frequency):
    """
    This function takes in a symbol and frequency and returns a SymbolObject with an empty frequency.
    """
    return SymbolObject(symbol, frequency, "")

def createNode(cumfrequency, symbols):
    """
    This function takes in a cumulative frequency and symbol object list and returns a new Node.
    """
    return Node(cumfrequency, symbols)

def createHistogram(filename):
    """
    This function wil create a histogram that will contain each symbol and the count of each symbol in the file.
    :param filename: inputed filename
    :return: Returns a dictionary (called hist) containing the count.
    """
    file = open(filename)
    hist = {}
    for line in file:
        lst = line.strip()
        for character in lst:
            if character in hist:
                hist[character] += 1
            else:
                hist[character] = 1
    file.close()
    return hist

def convertToHeap(hist):
    """
    This function takes in the histogram, creates and empty Heap and for each symbol it will create a Symbol Object, and for each SymbolObject
    it will create a Node and then add it to the heap.
    :param hist: The histogram from the createHistogram function
    :return: returns the Heap with the added Nodes.
    """
    maxSize = len(hist)
    Heap = createEmptyHeap(maxSize,compareFunc)
    for symbol in hist:
        symbolObject = [createSymbolObject(symbol,hist[symbol])]
        Node = createNode(hist[symbol], symbolObject)
        Heap.add(Node)
    return Heap
def compareFunc(n1,n2):
    """
    Compares if Node 1 is smaller than Node 2.
    :param n1: Node 1
    :param n2: Node 2
    :return: True or False (Boolean)
    """
    return n1.cum < n2.cum

def combine(n1,n2):
    """
    This function creates a combined Node with the corresponding values from Node 1 and Node 2.
    :param n1: inputted Node 1
    :param n2: inputted Node 2
    :return: The combined Node
    """
    combinedNode = None
    cumulutaiveFreq = n1.cum + n2.cum
    symbols = []
    for symbol in n1.symbols:
        symbol.codeword = '0' + symbol.codeword
        symbols.append(symbol)
    for symbol in n2.symbols:
        symbol.codeword = '1' + symbol.codeword
        symbols.append(symbol)
    CombinedNode = createNode(cumulutaiveFreq, symbols)
    return CombinedNode

def createVLC(heap):
    """
    This creates the codeword for the symbol.
    :param heap: this takes the created heap from the convertToHeap function.
    """
    while heap.size > 1:
        Node1 = heap.remove()
        Node2 = heap.remove()
        CombinedNode = combine(Node1,Node2)
        heap.add(CombinedNode)
    return heap.remove()
def main():
    """
    This function takes in a filename, creates the histogram, then the heap and finally the codeword for each symbol and creates a correct output.
    """
    filename = input("Input Filename:")
    hist = createHistogram(filename)
    heap = convertToHeap(hist)
    vlc = createVLC(heap)
    print("Variable Length Code Output")
    print("------------------------------------")
    topeq = 0
    bottomeq = 0

    for symbol in vlc.symbols:
        print('Symbol: %2s  ' % symbol.name, end='')
        print('Codeword: %8s  ' %symbol.codeword, end = '')
        print('Frequency: %5d' % symbol.frequency)
        topeq += len(symbol.codeword) * symbol.frequency
        bottomeq += symbol.frequency

    eqres = topeq / bottomeq
    logres = ceil(log2(len(hist)))
    print("Average VLC codeword length: ", eqres, "bits per symbol")
    print("Average Fixed length codeword length:", logres, "bits per symbol")

if __name__ == '__main__':
    main()