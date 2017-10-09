"""
file: five_point_star.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
description: This program draws a five point star consisting of five equilateral triangles.
"""
from turtle import *
        
def draw_triangle(length):
    """ This function creates an equilateral triangle, it's angles are a 120
    which are the exterior angles of an equilateral triangle.(360/3 = 120)."""
    down()
    forward(100)
    left(120)
    forward(100)
    left(120)
    forward(100)
    left(120)
    forward(100)
    up()

def draw_five_point_star(length):
    """ This function creates 5 equilateral triangle, an rotates each one to the right at an
    angle of 72 which are the exterior angles of a regular pentagon (360/5 = 72)."""
    for i in range(0,5):
        draw_triangle(length)
        right(72)

#this pauses the program until the user presses the enter key for the program to start.
length = input("Input the length:")
#this calls the function and creates the complete five point star
draw_five_point_star(length)
#draw_five_point_star()
#this pauses the program until the user presses the enter key for the program to be terminated.
input("Press enter to terminate...")
