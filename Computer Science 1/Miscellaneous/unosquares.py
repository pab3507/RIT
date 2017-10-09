from turtle import *

def drawSquares(depth,length):
    if depth <= 0:
        pass
    else:
        forward(length)
        left(90)

        forward(length)
        left(90)
        drawSquares(depth-1,length/3)

        forward(length)
        left(90)

        forward(length)
        left(90)

maxSize = 100
depth = int(input("Enter a depth: "))
drawSquares(depth,maxSize)
input("Hit Enter to close.")