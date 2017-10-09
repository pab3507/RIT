"""
file: arrows.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
description: This program draws colored recursive triangles both iteratively and recursively. It receives some random
parameters such as the length, angle, and distance, but also has fixed value like the numbers of triangles that should
be drawn. The figures drawn should always start inside the bounding box and never outside it.".
"""
from math import *
from random import *
from turtle import *

def areaEquilateralTriangle(length):
    """
    This function calculates the area of any given equilateral triangle.
    :param length: This paramater is calculated randomly on the recursive and iterative functions
    :return: The area calculation is returned
    pre-conditions: The turtle is not manipulated on this function.
    post-conditions: The turtle is not manipulated on this function.
    """
    a = length
    b = a/2
    h = ((sqrt(3)*(a))/2)
    return 2*((b*h)/2)

def BOUNDING_BOX():
    """
    This function returns the Bounding Box's Area at 200.
    :return: Area is 200.
    pre-conditions: The turtle is not manipulated on this function.
    post-conditions: The turtle is not manipulated on this function.
    """
    return 200

def MAX_FIGURES():
    """
    This function defines the maximum number of triangles drawn.
    pre-conditions: The turtle is not manipulated on this function.
    post-conditions: The turtle is not manipulated on this function.
    :return: Maximum figures drawn is 500
    """
    return 500

def MAX_DISTANCE():
    """
    This function defines the maximum size of the triangles drawn.
    pre-conditions: The turtle is not manipulated on this function.
    post-conditions: The turtle is not manipulated on this function.
    :return: Maximum distance is 30
    """
    return 30

def MAX_ANGLE():
    """
    This function defines the maximum size of the triangles drawn.
    pre-conditions: The turtle is not manipulated on this function.
    post-conditions: The turtle is not manipulated on this function.
    :return: Maximum angle is 30
    """
    return 30

def MAX_SIZE():
    """
    This function defines the maximum length of the triangles drawn.
    pre-conditions: The turtle is not manipulated on this function.
    post-conditions: The turtle is not manipulated on this function.
    :return: Maximum length is 30
    """
    return 30

def boundingBox():
    """
    This function draws a bounding box (square) and then centers at the middle of the canvas
    :param length: This paramater is calculated randomly on the recursive and iterative functions
    :return: The area calculation is returned
    pre-conditions: The turtle starts at the -200(x-axis)position of the bounding box on the bottom left facing east.
    post-conditions: The turtle ends on the middle of the bounding box facing east.
    """
    up()
    backward(BOUNDING_BOX())
    left(90)
    backward(BOUNDING_BOX())
    right(90)
    down()
    forward(400)
    left(90)
    forward(400)
    left(90)
    forward(400)
    left(90)
    forward(400)
    left(90)
    up()
    left(90)
    forward(BOUNDING_BOX())
    right(90)
    forward(BOUNDING_BOX())
    down()

def triangle(length):
    """
    This function correctly draws a colored triangle with a random length and random color fill.
    pre-conditions: The first triangle drawn starts at the middle of the bounding box facing east.
    post-conditions: The triangle then ends at the same point it started and continues at the position which the
    recursive or iterative function randomly generates.
    """
    color(random(), random(), random())
    begin_fill()
    forward(length)
    left(120)
    forward(length)
    left(120)
    forward(length)
    left(120)
    end_fill()

def drawFiguresRec(depth):
    """
    This function draws the figures recursively, makes sure the figures fit within the bounding box and calculates
    the sum of the triangles areas.
    pre-conditions: The first triangle drawn starts at the middle of the bounding box facing east.
    post-conditions: The triangle then ends at the same point it started and continues to draw more at an angle, length,
    and distance which the recursive function randomly generates until the depth reaches a value lower than zero.
    :param depth: The depth is inputed by the user which defines the number of triangles that will be drawn.
    :return: The sum of the triangles areas.
    """
    if depth <= 0:
        return 0
    else:
        x, y = position()
        length = randint(1, MAX_SIZE())
        angle = randint(-MAX_ANGLE(), MAX_ANGLE())
        dist = random() * MAX_DISTANCE()
        up()
        left(angle)

        if y + dist > BOUNDING_BOX():
            setheading(270)
        elif y - dist < -BOUNDING_BOX():
            setheading(90)
        elif x+dist > BOUNDING_BOX():
            setheading(180)
        elif x-dist < -BOUNDING_BOX():
            setheading(0)

        forward(dist)
        triangle(length)
        down()
        return areaEquilateralTriangle(length) + drawFiguresRec(depth-1)

def drawFiguresIter(depth):
    """
    This function draws the figures iteratively, makes sure the figures fit within the bounding box and calculates
    the sum of the triangles areas.
    pre-conditions: The first triangle drawn starts at the middle of the bounding box facing east.
    post-conditions: The triangle then ends at the same point it started and continues at angle, length, and distance
    which the iterative function randomly generates until the depth reaches a value lower than zero.
    :param depth: The depth is inputed by the user which defines the number of triangles that will be drawn.
    :return: The sum of the triangles areas.
    """
    sum = 0
    while True:
        if depth <= 0:
            break
        else:
            x, y = position()
            length = randint(1, MAX_SIZE())
            dist = random() * MAX_DISTANCE()
            left(randint(-MAX_ANGLE(), MAX_ANGLE()))
            if y + dist > BOUNDING_BOX():
                setheading(270)
            elif y - dist < -BOUNDING_BOX():
                setheading(90)
            elif x+dist > BOUNDING_BOX():
                setheading(180)
            elif x-dist < -BOUNDING_BOX():
                setheading(0)
            up()
            forward(dist)
            down()
            depth-=1
            length-=1
            triangle(length)
            up()
            sum += areaEquilateralTriangle(length)
    return sum

def main():
    """
    This function contains an if statement that evaluates the value of the input (depth) and evaluates if it's within
    the range comprehended, and if not an error message is displayed. Then bounding box is called and then recursive
    function is drawn and value of the sum of the areas is returned. Then canvas is reseted and the same is done for
    the iterative function and the program then terminated.
    pre-conditions: The turtle starts at the -200(x-axis)position of the bounding box on the bottom left facing east.
    post-conditions: The turtle then starts drawing figures from the middle of the bounding box facing east, then
    proceeds to draw figures by calling the recursive function. Then after completion canvas is reseted and then the
    same happens for the iterative function.
    """
    #I convert the input into a integer using the following method.
    depth = int(input("Enter the number of figures to be drawn: "))
    #This makes sure the value inputed is within the range of (0,500)
    if 0 <= depth <= MAX_FIGURES():
        #This draws the Bounding box
        boundingBox()
        #This calls the recursive function and also assigns the value of its return to a variable called sum.
        sum = drawFiguresRec(depth)
        #This prints the sum.
        print("The sum of the areas is", sum)
        #this asks the user for continuation
        input("Press enter to continue")
        #this resets the canvas
        reset()
        #This draws the bounding box again
        boundingBox()
        #This calls the iterative function and also assigns the value of its return to a variable called sum.
        sum = drawFiguresIter(depth)
        #This prints the sum.
        print("The sum of the areas is", sum)
        #this asks the user for termination
        input("Press enter to terminate")
    else:
        #Error message for input out of range.
        print("The number of triangles should be within the range of 0 to 500. Invalid Input!")

#this executes the program
main()
