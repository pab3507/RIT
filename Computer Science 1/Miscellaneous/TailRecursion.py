from turtle import *

def initialize():
        left(90)
def drawSpiral(spiral):

    if spiral <= 0:
        pass
    else:
       fd(spiral*10)
       rt(90)
       drawSpiral(spiral-1)

def main():
    initialize()
    drawSpiral(20)
    input()

main()