import math

from random import *
from turtle import *

def areaEquilateralTriangle(len):
    a = len
    b = a/2
    h = ((math.sqrt(3)*(a))/2)
    print(2*((b*h)/2))

areaEquilateralTriangle(5)

def boundingBox():
    forward(400)
    left(90)
    forward(400)
    left(90)
    forward(400)
    left(90)
    forward(400)
    left(90)

def triangle(depth,len):
    forward(len)
    left(120)
    forward(len)
    left(120)
    forward(len)
    left(120)

def MAX_FIGURES():
    return 500

def MAX_DISTANCE():
    return 30

def MAX_ANGLE():
    return 30

def drawFiguresIter(depth,len):
    sum = 0
    while True:
        if depth <= 0:
            break
        else:
            triangle(depth,len)
            up()
            forward(10)
            left()
            down()
            depth-=1
            len-=1
            sum = sum + len
    return sum

def


def drawFiguresRec(depth,len):
    if depth <= 0:
        pass
    else:
        boundingBox()

        triangle(len)
        up()
        forward(10)
        left()
        down()
        areaEquilateralTriangle(len)
        return areaEquilateralTriangle

def main():
    #I convert the input into a integer using the following method.
    depth = int(input("Enter the depth: "))
    len = int(input("Enter the length: "))
    #This call the input
    drawFiguresRec(depth, len)
    #this asks the user for termination
    input("Press enter to terminate")
    drawFiguresIter(depth,len)
    input("Press enter to terminate")


#this executes the program
main()
