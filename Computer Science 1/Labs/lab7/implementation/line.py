"""
file: line.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
"""

from myNode import *
from myQueue import *
from myStack import *
from person import *

#this creates a queue
queue = createQueue()

def addPerson(person):
    """
    This adds a person to the queue. It checks if the queue is empty if it's it adds to the queue otherwise it's going
    to add a person to the queue in order depending of the number of skips the person has.
    :param person: person to be entered to the queue.
    """
    addChecker = False
    if emptyQueue(queue) is True:
        enqueue(queue, person)
    else:
        for i in range(queue.size):
            top = front(queue)
            if top.skips < person.skips and addChecker is False:
                enqueue(queue, person)
                addChecker = True
            dequeue(queue)
            enqueue(queue,top)
        if not addChecker:
            enqueue(queue, person)

def lineTest():
    """
    Tests the functionality of the file.
    """
    Professor = createPerson('Jeremy', 'Professor', 3)
    TA = createPerson('Nathan', 'TA', 2)
    Tutor = createPerson('Connor', 'Tutor', 1)
    Student = createPerson('Moises', 'Student', 0)
    addPerson(TA)
    addPerson(Professor)
    addPerson(Student)
    addPerson(Tutor)
    testChecker = False
    if front(queue).title == Professor.title:
        testChecker = True
    else:
        testChecker = False
    dequeue(queue)
    if front(queue).title == TA.title:
        testChecker = True
    else:
        testChecker = False
    dequeue(queue)
    if front(queue).title == Tutor.title:
        testChecker = True
    else:
        testChecker = False
    dequeue(queue)
    if front(queue).title == Student.title:
        testChecker = True
    else:
        testChecker = False

    if testChecker == True:
        print("File works properly")
    else:
        print("File works improperly")

    print(queue)

if __name__ == '__main__':
    lineTest()

