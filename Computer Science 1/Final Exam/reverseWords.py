from myStack import *
def read_file(filename):
    """
    This function opens the filename and returns the list of puzzles.
    :param filename: textfile inputed
    :return: puzzleList
    """
    file = open(filename)
    puzzleList = []
    for currentLine in file:
        puzzleList += currentLine.split()
    return puzzleList

def reverseWords(lst):
    wordStack = None
    for word in lst:
        wordStack = push(wordStack, word)
    while not emptyStack(wordStack):
        x = top(wordStack)
        wordStack = pop(wordStack)
        print(x,end=' ')

def main():
    txtFile = input("Input Filename:")
    list = read_file(txtFile)
    print(list)
    reverseWords(list)

main()