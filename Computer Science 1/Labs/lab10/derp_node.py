"""
141 Tree Lab - Derp the Interpreter

These are the definitions of the node classes that
are used by the interpreter. It is meant to be imported by the main program
and the derp_tree file.

Author: Sean Strout (sps@cs.rit.edu)\
Author: Scott C Johnson (scj@cs.rit.edu)

----------------------------------------------------------------------------
file: derp_node.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
"""

from rit_lib import *

##############################################################################
# structure definitions for parse tree
##############################################################################

class MultiplyNode(struct):
    """Represents a multiply operator, *"""
    
    _slots = ((object, 'left'), (object, 'right'))

    def getInfixString(self):
        """
        Return a string representing this node in an infix notation.
        :return: string in infix notation with its corresponding operator.
        """
        return '(' + self.left.getInfixString() + '*' + self.right.getInfixString() + ')'

    def addLeftSide(self, side):
        """
        Sets the left side of the operand
        """
        self.left = side

    def addRightSide(self, side):
        """
        Sets the right side of the operand
        """
        self.right = side

    def calculateValue(self, symbolTable):
        """
        Calculates the value of this node using the
        left and right hand sides.
        :return: The multiplication of the calculated values of both the left and hand right sides.
        """
        return self.left.calculateValue(symbolTable) * self.right.calculateValue(symbolTable)
    
class DivideNode(struct):
    """Represents an integer divide operator, //"""

    _slots = ((object, 'left'), (object, 'right'))

    def getInfixString(self):
        """
        Return a string representing this node in an infix notation.
        :return: string in infix notation with its corresponding operator.
        """
        return '(' + self.left.getInfixString() + '//' + self.right.getInfixString() + ')'

    def addLeftSide(self, side):
        """
        Sets the left side of the operand
        """
        self.left = side

    def addRightSide(self, side):
        """
        Sets the right side of the operand
        """
        self.right = side

    def calculateValue(self, symbolTable):
        """
        Calculates the value of this node using the
        left and right hand sides.
        :return: The division of the calculated values of both the left and hand right sides.
        """
        return self.left.calculateValue(symbolTable) // self.right.calculateValue(symbolTable)
    
class AddNode(struct):
    """Represents an addition operator, +"""

    _slots = ((object, 'left'), (object, 'right'))

    def getInfixString(self):
        """
        Returns a string representing this node in an infix notation.
        :return: string in infix notation with its corresponding operator.
        """
        return '(' + self.left.getInfixString() + '+' + self.right.getInfixString() + ')'

    def addLeftSide(self, side):
        """
        Sets the left side of the operand
        """
        self.left = side

    def addRightSide(self, side):
        """
        Sets the right side of the operand
        """
        self.right = side

    def calculateValue(self, symbolTable):
        """
        Calculates the value of this node using the left and right hand sides.
        :return: The addition of the calculated values of both the left and hand right sides.
        """
        return self.left.calculateValue(symbolTable) + self.right.calculateValue(symbolTable)
    
class SubtractNode(struct):
    """Represents a subtraction operator, -"""

    _slots = ((object, 'left'), (object, 'right'))

    def getInfixString(self):
        """
        Returns a string representing this node in an infix notation.
        :return: string in infix notation with its corresponding operator.
        """
        return '(' + self.left.getInfixString() + '-' + self.right.getInfixString() + ')'

    def addLeftSide(self, side):
        """
        Sets the left side of the operand
        """
        self.left = side

    def addRightSide(self, side):
        """
        Sets the right side of the operand
        """
        self.right = side

    def calculateValue(self, symbolTable):
        """
        Calculates the value of this node using the left and right hand sides.
        :return: The subtraction of the calculated values of both the left and hand right sides.
        """
        return self.left.calculateValue(symbolTable) - self.right.calculateValue(symbolTable)
    
class LiteralNode(struct):
    """Represents an operand node"""
    
    _slots = ((int, 'val'))

    def getInfixString(self):
        """
        Returns a string representing this node in an infix notation.
        :return:  operand node in infix notation.
        """

        return str(self.val)

    def calculateValue(self, symbolTable):
        """
        Returns the value of this node using the left and right hand sides.
        :return: the value of the calculation
        """
        return self.val
class VariableNode(struct):
    """Represents a variable node"""
    
    _slots = ((str, 'name'))


    def getInfixString(self):
        """
        Returns a string representing this node in an infix notation.
        :return: the node of the class with is already a string.
        """
        return self.name

    def calculateValue(self, symbolTable):
        """
        Calculates the value of this node using the left and right hand sides.
        :return: the value that the symbol takes at index of the node.
        """
        return symbolTable[self.name]
