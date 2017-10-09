"""
file: spirograph.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
description: This program draws a spirograph iteratively given a p which equates to the value of the offset of the pen
point in the moving circle".
"""
from math import *
from turtle import *

def drawSpirograph(p,t,r,R):
    """
    In this function, the turtle is assigned to a position (x,y) which coordinates correspond to the values of x and y
    obtained with the formula denoted below.
    :param p: The offset of the pen point in the moving circle
    :param t: The iteration between 2*PI and 0, in intervals of -0.01
    :param r: The radius of the moving circle
    :param R: The radius of the fixed circle
    """
    #The formulas below determine the equation of the curve
    x= (R-r)*cos(t) - (r+p)*cos((R-r)/r*t)
    y= (R-r)*sin(t) - (r+p)*sin((R-r)/r*t)
    #Moves the turtle to the position of x and y at the current point of iteration.
    goto(x,y)

def main():
    """
    In this function the values of R,r and t are assigned, while p is inputed by the user. The function then proceeds
    to iterate the values of t at intervals of -0.01 until they are less than 0 while at the same times drawing the
    curves that form the spirograph.
    Pre-Condition: The drawing starts at the turtle's initial position.
    Post-Condition: The turtle then draws curves until it reaches back to it's initial position.
    """
    p = int(input("Enter an integer value for p:"))
    if 10 <= p <= 100:
        R = 100
        r=4
        t =2*pi
        up()
        drawSpirograph(p,t,r,R)
        down()
        while t>0:
            drawSpirograph(p,t, r, R)
            t-=0.01

    else:
        print("Incorrect value for p!")

#this executes the program
main()
#this asks the user for termination
input("Hit enter to close...")
