"""
file: squares.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
description: This program draws colored recursive squares".
"""

import math
from turtle import *

def drawOneSquare(length,depth):
    """
    This function correctly draws a square using an inputed length.
    """
    forward(length)
    left(90)
    forward(length)
    left(90)
    forward(length)
    left(90)
    forward(length)
    left(90)

def fillColor(depth):
    """
    This function changes the turtle's color depending on the depth inputed and determines if the depth is odd or even.
    If the remainder of the depth is 0, the color will be green, otherwise it will be blue.
    """
    if depth % 2 == 0:
        color("green")
    else:
        color("blue")

def drawSquares(depth, length):
    """
    This function uses recursion to draw squares depending on the depth and the length inputed and also uses the
    function FillColor to change the turtle's color depending if the depth inputed is odd or even.
    """
    #gives the turtle a color
    fillColor(depth)
    #base case
    if depth == 0:
        pass
    else:
        #draws initial square
        drawOneSquare(length,depth)
        #the pen is brought up and down repetitively so the colors don't overlap
        up()
        #the turtle goes back to initial position (left bottom corner facing east)
        forward(length)
        left(90)
        forward(length/2)
        left(45)
        #the turtle is then brought to the right middle of the square facing the northwest
        down()
        drawSquares(depth-1,length/(2*math.sqrt(2)))
        #the turtle goes back to position before the first recursion (right middle of the square)
        up()
        right(45)
        forward(length/2)
        left(90)
        forward(length)
        left(90)
        forward(length/2)
        left(45)
        #the turtle is then brought to the left middle of the square facing the northeast
        down()
        drawSquares(depth-1,length/(2*math.sqrt(2)))
        #the turtle goes back to position before the second recursion (left middle of the square)
        up()
        right(45)
        forward(length/2)
        left(90)
        #the turtle goes back to initial position (left bottom corner facing east)
        down()
        #this changes the color recursively depending if the depth is odd or even.
        fillColor(depth+1)

def main():
    #I convert the input into a integer using the following method.
    depth = int(input("Enter the depth: "))
    length = int(input("Enter the length: "))
    #This call the input
    drawSquares(depth, length)
        #this asks the user for termination
    input("Press enter to terminate")

#this executes the program
main()

