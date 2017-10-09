"""
141 Tree Lab - Derp the Interpreter

Derp is a simple interpreter that parses and evaluates prefix expressions
containing basic arithmetic operators (*,//,-,+).  It performs arithmetic with
integer only operands that are either literals or variables (read from a
symbol table).  It dumps the symbol table, produces the expression infix with
parentheses to denote order of operation, and evaluates/produces the result of
the expression.

Author: Sean Strout (sps@cs.rit.edu)
Author: Scott C. Johnson (scj@cs.rit.edu)
--------------------------------------------------
file: derp.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
"""

from derp_tree import *

def main():
    """main: None -> None
    The main program prompts for the symbol table file, and a prefix
    expression.  It produces the infix expression, and the integer result of
    evaluating the expression"""

    print("Hello Herp, welcome to Derp v1.0 :)")

    inFile = input("Herp, enter symbol table file: ")

    # STUDENT: CONSTRUCT AND DISPLAY THE SYMBOL TABLE HERE, helper functions are allowed
    filename = open(inFile)
    dict = {}
    for line in filename:
        lst = line.strip().split()
        dict[lst[0]] = int(lst[1])

    print("Herp, enter prefix expressions, e.g.: + 10 20 (RETURN to quit)...")

    # input loop prompts for prefix expressions and produces infix version
    # along with its evaluation
    while True:
        prefixExp = input("derp> ")
        if prefixExp == "":
            break

        # STUDENT: create an empty DerpTree
        tr = createDerpTree()
        # STUDENT: create the tree from the prefix expression
        tr.createTreeFromPrefix(prefixExp)
        # STUDENT: get the infix expression from the tree
        infix = tr.getInfixString()
        # STUDENT: MODIFY THE print STATEMENT TO INCLUDE RESULT.
        print("Derping the infix expression:" + infix)

        # STUDENT: EVALUTE THE PARSE TREE BY CALLING evaluate tree AND SAVING THE
        # INTEGER RESULT.

        # STUDENT: MODIFY THE print STATEMENT TO INCLUDE RESULT.
        print("Derping the evaluation:" + str(tr.evaluateTree(dict)))
        print(dict)

    print("Goodbye Herp :(")

if __name__ == "__main__":
    main()

