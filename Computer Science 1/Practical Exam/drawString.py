"""
Moisés Lora Pérez
"""

from turtle import *

def init():
    reset()
    setup(600,600)
    setworldcoordinates(-300,-300,300,300)

def drawC():
    circle(30)

def drawP():
    forward(30)
    left(72)
    forward(30)
    left(72)
    forward(30)
    left(72)
    forward(30)
    left(72)
    forward(30)
    left(72)

def drawF():
    forward(30)

def drawB():
    back(30)

def drawL():
    left(30)

def drawR():
    right(30)

def drawU():
    up()

def drawD():
    down()

def drawH():
    up()
    goto(0,0)

def drawRec():
    line = 0

    if s == 'C':
        drawC()
        line +=1
    elif s == 'P':
        drawP()
        line +=5
    elif s == 'F':
        drawF()
        line +=1
    elif s == 'B':
        drawB()
        line +=1
    elif s == 'L':
        drawL()
    elif s == 'R':
        drawR()
    elif s == 'U':
        drawU()
    elif s == 'D':
        drawD()
    elif s == 'H':
        drawH()
    return line


letters = ['C','P','F','B','L','R','U','D','H']
s = str(input("String to Parse:"))
drawRec()
input()