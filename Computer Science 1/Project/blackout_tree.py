from rit_lib import *
from bst import *
from btNode import *
from blackout import *

def makeTree(equation, value):
    """
    This function is a helper function for the actual make_tree function that just takes in a string.
    This function sets variables for both left and right childrens, which are assigned the value that a recursive call
    with it's equation([1:]) and value from a parameter. Then a new variable is created and assigned a binary tree node
    created with the value obtained from the leftChildren and Right Children, and with the value.
    :param equation: A string
    :return: the created binary tree node
    """
    if equation == '':
        leftChildren = None
        rightChildren = None
    else:
        leftChildren = makeTree(equation[1:], value)
        rightChildren = makeTree(equation[1:],value+(equation[0]))
    tr = BinaryTreeNode(leftChildren, value, rightChildren)
    return tr

def make_tree(s):
    """
    This function makes the binary tree from the string s, s is a puzzle string, it's parameter is obtained from the
    make tree helper function.
    :param s: puzzle
    :return: a Tree
    """
    tree = BinaryTree(makeTree(s, ''))
    return tree

def solve_tree(bt, number):
    """
    This function will solve the given binary tree puzzle using the solveTree Helper Function. It obtains the size from
    the length of the last right leaf node from the node that contains the equation.
    :param bt: the binary tree.
    :param number: number of blacked out squares.
    """
    node = bt.root
    while node.hasRightChild():
        node = node.right
    size = len(node.value)
    solveTree(bt.root, number, size)

def solveTree(btNode, number, size):
    """
    This function will solve the given binary tree puzzle, bt, with the given number of blacked out square, number.
    It will find all leaf nodes and call evaluate(s) from blackout.py on any leaf node value that has the requested
    blacked out squares. It will print any solutions it finds.
    :param btNode: The Binary Tree Node.
    :param number: Number of blacked out squares.
    :param size: Length of the equation.
    :return: Solution
    """
    if btNode.isLeaf():
        if len(btNode.value) + number == size:
            if evaluate(btNode.value):
                print(btNode.value)
                return
    else:
        left = solveTree(btNode.left, number,size)
        if left is None:
            right = solveTree(btNode.right, number, size)
            return right
        else:
            return left
def test_make_tree():
    s='63-2-9=18*2*2+2'
    tree = make_tree(s)
    if tree is not None:
        print("Successful Test")
    else:
        print("Failed Test")

def read_file(filename):
    """
    This function opens the filename and returns the list of puzzles.
    :param filename: textfile inputed
    :return: puzzleList
    """
    file = open(filename)
    puzzleList = []
    for currentLine in file:
        puzzleList.append((currentLine.strip()))
    return puzzleList

def main():
    """
    This function prompts the user for the file containing the puzzles and the number of blacked out squares to look
    for. It prints the puzzle and calls make tree to create the tree. Then solves tree to print the valid solutions
    for the tree.
    """
    textFile = input("Input the name of your file containing the puzzles: ")
    squares = int(input('Number of squares to blackout: '))
    puzzlesFile = read_file(textFile)
    for puzzle in puzzlesFile:
        print("Puzzle is:", puzzle)
        s = puzzle
        tree = make_tree(s)
        print("Solution is:")
        solve_tree(tree, squares)
#test_make_tree()
if __name__ == "__main__":
    main()
