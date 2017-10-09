"""
file: student_placer.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
"""

from building import *
from student import *
from room import *
from rooms import *
from floors import *


def readStudents(filename):
    """
    This function opens the filename and returns the list.
    :param filename: textfile inputed
    :return: nameList
    """
    file = open(filename)
    nameList = []
    for currentLine in file:
        nameList.append((currentLine.strip().split()))
    return nameList

def placeStudents(list):
    """
    This function places the students in their corresponding buildings, floor and rooms. It uses the hash functions
    in order to determine where the students should be placed according to the value of the corresponding hash.
    :param list: inputed list
    """
    buildings = createBuilding()

    for line in list:
        name, furniture = line.split()
        floors = buildings.get(name)
        rooms = floors.get(name)
        room = rooms.get(name)
        if room.AddtoRoom(name, furniture):
            print("student", name, "already present in", buildings.hash_function(name),"floor", floors.hash_function(name)
                  , "in room", rooms.hash_function(name), ". Added furniture", furniture)
            # They were already in the room and their furniture was added
        else:
            print('Added student', name, 'with', furniture, 'to building', buildings.hash_function(name), "floor",
            floors.hash_function(name), "in room", rooms.hash_function(name))

def main():
    """
    The main function reads in a file, converts it to a list and then proceeds to place the students.
    """
    textfile = input("input filename: ")
    list = readStudents(textfile)
    placeStudents(list)

if __name__ == "__main__":
    main()
