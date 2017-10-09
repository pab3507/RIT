"""
file: club.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
"""
from person import *
from line import *
from myQueue import *
from myStack import *

def moveStack(stack1, stack2):
    """
    This assigns a variable the top of the stack, then removes the top element of the stack2 and returns it's value and
    then pushes the value of the variable to the second stack.
    :param stack1: a stack
    :param stack2: a stack
    :return:
    """
    while not emptyStack(stack2):
        stack2val = top(stack2)
        stack2 = pop(stack2)
        stack1 = push(stack1,stack2val)
    return stack1

def processCommands(lst,stack, queue):
    """
    This processes whatever is inputed in the list. So if it's a name it does the correct number of skips, and does the
    action corresponding to the commands.
    :param lst: inputed list of command
    :param stack: stack to be used
    :param queue: queue to be used
    :return: a queue
    """
    for command in lst:
        if command == 'PARTY':
            if not emptyQueue(queue):
                push(stack, front(queue))
                dequeue(queue)
            else:
                print("Empty Line")

        elif command == 'BOUNCE':
            if not emptyQueue(queue):
                temp = None
                while True:
                    if stack == emptyStack(stack):
                        stack = moveStack(stack,temp)
                        person = top(stack)
                    elif person.skips == 0:
                        stack = pop(stack)
                        stack = moveStack(stack, temp)
                        return stack
                    else:
                        person.skips =- 1
                        push(temp, person)
                        push(stack, person)
            else:
                print("Empty Line")
        else:
            name, title = command.split(' ')
            person = None
            if title == 'Professor':
                person = createPerson(name, title, 3)
            elif title == 'TA':
                person = createPerson(name, title, 2)
            elif title == 'Tutor':
                person = createPerson(name, title, 1)
            elif title == 'Student':
                person = createPerson(name, title, 0)
            else:
                continue
            addPerson(person)
        print(queue)

def clubTest():
    """
    Tests the functionality of the file.
    """
    Professor = "Jeremy Professor"
    TA = 'Nathan TA'
    Tutor = 'Connor Tutor'
    Student = 'Moises Student'
    lst = [Professor, TA, Tutor, Student]
    print(lst)
    stack = None
    processCommands(lst, stack, queue)
    testChecker = False
    if front(queue).skips == 3:
        testChecker = True
    else:
        testChecker = False
    dequeue(queue)
    if front(queue).skips == 2:
        testChecker = True
    else:
        testChecker = False
    dequeue(queue)
    if front(queue).skips == 1:
        testChecker = True
    else:
        testChecker = False
    dequeue(queue)
    if front(queue).skips == 0:
        testChecker = True
    else:
        testChecker = False

    if testChecker == True:
        print("File works properly")
    else:
        print("File works improperly")

    print(stack)
if __name__ == '__main__':
    clubTest()