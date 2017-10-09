"""
file: typography.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
description: This program draws the text message ABRACADABRA CS I".
"""

from turtle import *

def draw_a():
    """ This function draws the letter A."""
    down()
    left(90)
    forward(25)
    right(90)
    forward(25)
    right(90)
    forward(12.5)
    right(90)
    forward(25)
    up()
    back(25)
    left(90)
    down()
    forward(12.5)
    left(90)
    up()
    #makes up the space between the letters
    forward(12.5)

def draw_b():
    """ This function draws the letter B."""
    down()
    forward(25)
    circle(6.25,180)
    forward(25)
    left(180)
    forward(25)
    circle(6.25,180)
    forward(25)
    left(90)
    forward(25)
    left(90)
    up()
    #makes up the space between the letters
    forward(43)

def draw_c():
    """ This function draws the letter C."""
    down()
    forward(25)
    back(25)
    left(90)
    forward(25)
    right(90)
    forward(25)
    up()
    right(90)
    forward(25)
    left(90)
    #makes up the space between the letters
    forward(12.5)

def draw_d():
    """ This function draws the letter D."""
    down()
    left(90)
    forward(25)
    right(90)
    forward(25)
    right(45)
    forward(8.75)
    right(45)
    forward(12.5)
    right(45)
    forward(8.75)
    right(45)
    forward(25)
    back(25)
    right(180)
    up()
    #makes up the space between the letters
    forward(20)

def draw_r():
    """ This function draws the letter R."""
    down()
    left(90)
    forward(25)
    right(90)
    forward(25)
    right(90)
    forward(12.5)
    right(90)
    forward(25)
    left(150)
    forward(25)
    left(30)
    up()
    #makes up the space between the letters
    forward(16)

def draw_s():
    """ This function draws the letter S."""
    down()
    forward(25)
    left(90)
    forward(12.5)
    left(90)
    forward(25)
    right(90)
    forward(12.5)
    right(90)
    forward(25)
    up()
    right(90)
    forward(25)
    left(90)
    up()
    #makes up the space between the letters
    forward(12.5)



def draw_I():
    """ This function draws number one in roman numerals"""
    down()
    forward(25)
    back(12.5)
    left(90)
    forward(25)
    left(90)
    forward(12.5)
    back(25)
    left(90)
    up()
    forward(25)
    left(90)
    forward(15)


def draw_message():
    """ This function draws all the letters that the message ABRACADABRA CS I contains."""
    #draw_s()
    draw_a()
    draw_b()
    draw_r()
    draw_a()
    draw_c()
    draw_a()
    draw_d()
    draw_a()
    draw_b()
    draw_r()
    draw_a()
    #makes up the space between the two words
    forward(25)
    draw_c()
    draw_s()
    #makes up the space between the words and the number
    forward(25)
    draw_I()



#this pauses the program until the user presses the enter key for the program to start.
input("Press enter to run...")
up()
#the pen is picked up and then moved backwards so the letters fit into the canvas.
back(300)
#this calls the function and draws the text message ABRACADABRA CS I".
draw_message()
#this pauses the program until the user presses the enter key for the program to be terminated.
input("Press enter to terminate...")