"""
file: person.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
"""

from rit_lib import *
from myNode import *
from myQueue import *
from mentoring_center import *
from myStack import *

class Person( struct ):
    """
    This class represents the properties of the person
    :slot name (str): this string represents the name of the person
    :slot title (str): this string represents the title of the person
    :slot skips (int): this represents the number of skips the person has
    """
    _slots = ((str, 'name'),(str,'title'), (int,'skips'))

def createPerson(name, title, skips):
    """
    A person is created with the paramaters corresponding to the class.
    """
    return Person(name, title, skips)

def personTest():
    """
    Tests the functionality of the file.
    """
    Professor = createPerson('Jeremy', 'Professor', 3)
    TA = createPerson('Nathan', 'TA', 2)
    Tutor = createPerson('Connor', 'Tutor', 1)
    Student = createPerson('Moises', 'Student', 0)
    test1 = isinstance(Professor, Person)
    test2 = isinstance(TA, Person)
    test3 = isinstance(Tutor, Person)
    test4 = isinstance(Student, Person)
    if test1 is True and test2 is True and test3 is True and test4 is True:
        print("The file is working properly")
    else:
        print("The file is working improperly")

if __name__ == '__main__':
    personTest()
