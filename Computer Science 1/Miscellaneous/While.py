from turtle import *

def initialize():
        left(90)

def drawSpiral(spiral):
    while spiral > 0:
        forward(spiral*10)
        right(90)
        spiral = spiral -1

def main():
    initialize()
    drawSpiral(10)
    input()

main()