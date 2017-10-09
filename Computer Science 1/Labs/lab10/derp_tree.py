"""
file: multiSort.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03

Description: Derp Tree class. Used to construct a Derp Tree from a prefix string, convert it to a infix string, and
solve for the value.
"""
from rit_lib import *
from derp_node import *

############################################################
# Class definiton                                          #
############################################################

class DerpTree( struct ):
    """ A non-empty BinaryTree has a root BSTNode.
        A empty BinaryTree has a root of None
    """
    _slots = ((NoneType, MultiplyNode,
               AddNode, SubtractNode,
               DivideNode, LiteralNode,
               VariableNode), 'root')

    def getInfixString(self):
        """
        Prints the tree in a infix form
        :return: the root in infix format
        """
        return self.root.getInfixString()

    def createTreeFromPrefix(self, prefix):
        """
        Creates a tree from the postfix list entered.
        It equates the result that the parse function gives to the root of the tree.
        """
        result = parse(prefix.split())
        self.root = result

    def evaluateTree(self, symTable):
        """
        Takes in a symbol table and calculates the value of root.
        """
        return self.root.calculateValue(symTable)


def createDerpTree( ):
    """
    Creates an empty tree
    :return: Empty Tree
    """
    return DerpTree( None )

def parse(tokens):
    """
    This functions takes a list of tokens for the prefix expression and returns the parsed tree.
    :param tokens: a list of strings
    :return: parsed tree
    """
    storedVal = tokens.pop(0)
    if storedVal == '+':
        leftAdd = parse(tokens)
        rightAdd = parse(tokens)
        return AddNode(leftAdd,rightAdd)
    if storedVal == '-':
        leftSub = parse(tokens)
        rightSub = parse(tokens)
        return SubtractNode(leftSub,rightSub)
    if storedVal == '*':
        leftMult = parse(tokens)
        rightMult = parse(tokens)
        return MultiplyNode(leftMult,rightMult)
    if storedVal == '//':
        leftDiv = parse(tokens)
        rightDiv = parse(tokens)
        return DivideNode(leftDiv,rightDiv)
    if storedVal.isdigit():
        return LiteralNode(int(storedVal))
    if storedVal.isidentifier():
        return VariableNode(storedVal)