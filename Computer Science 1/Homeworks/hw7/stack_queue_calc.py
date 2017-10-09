"""
file: stack_queue_calc.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
description: This program calculates the result of an equation using both stacks and queues.".
"""
from re import *
from rit_lib import *
from myNode import *
from myStack import *
from myQueue import *

def parseNumbers(equation):
    """
    This function parses the equation and then returns a stack and queue.
    :param equation: inputed equation.
    :return: a stack and a queue.
    """
    queue = createQueue()
    stack = None
    parts = equation.split(' ')
    for part in parts:
        enqueue(queue, part)
        stack = push(stack, part)
    return stack, queue

def calculate(operandOne, operandTwo, operation):
    """
    This function does the corresponding operation depending of what string(sign) does the variable operation matches.
    :param operandOne: Stores the value of one operand
    :param operandTwo: Stores the value of another operand
    :param operation: Stores the value of the operation to be performed.
    :return: the corresponding result is returned
    """
    if operation == '+':
        return operandOne + operandTwo
    elif operation == '-':
        return operandOne - operandTwo
    elif operation == '*':
        return operandOne * operandTwo
    elif operation == '/':
        return operandOne // operandTwo

def calculateStack(stack):
    """
    This calculates the result of the equation using stacks.
    :param stack: parsed equation
    :return: the result of the equation.
    """
    while size(stack) > 1:
        operandOne = int(stack.data)
        stack = pop(stack)
        operation = stack.data
        stack = pop(stack)
        operandTwo = int(stack.data)
        stack = pop(stack)
        stack = push(stack, calculate(operandOne, operandTwo, operation))
    return stack.data

def calculateQueue(queue):
    """
    This calculates the result of the equation using queues.
    :param queue: the parsed equation.
    :return: the result of the equation.
    """
    temp = int(front(queue))
    dequeue(queue)
    while not emptyQueue(queue):
        operation = front(queue)
        dequeue(queue)
        operandTwo = int(front(queue))
        dequeue(queue)
        temp = calculate(temp, operandTwo, operation)
    return temp

def mysplit(equation):
    """
    This splits the inputed equation, which is then used for error checking.
    :param equation: Inputed equation
    :return: split list
    """
    return split("([+-/*])", equation.replace(" ", ""))

def main():
    """
    This function prompts the user for an equation, then parses the input, and proceeds to properly calculate the result
    using both queues and stacks (separately). It also states if their results match each other, then it'll print if
    they're equal or not. This function also contains the error checking part of the assignment. It uses the splitted
    equation from the mySplit function and then is separated into two different lists containing the digits and one
    containing the operators. Then each list is checked, one if it contain digits and the other if it contains the
    correct operators. Lastly, the function also checks if the equation divides by a zero."
    :return:
    """
    eq = input("Input an equation: ")
    splitList = (mysplit(eq))
    operandsList = []
    #This loop takes in the split list and adds to a list without operators
    for operand in splitList:
        if operand == '+' or operand == '-' or operand == '*' or operand == '/':
            continue
        operandsList.append(operand)
    operatorsList = []
    #This loop takes in the split list and adds to a list without digits
    for operator in splitList:
        if operator.isdigit() is True:
            continue
        operatorsList.append(operator)
    #variable to check if the operator is allowed
    operatorChecker = False
    for sign in operatorsList:
        if sign == '+' or sign == '-' or sign == '/' or sign == '*':
            operatorChecker = True
        else:
            operatorChecker = False
    operandsDigits = ''.join(operandsList)
    #this checks if the operands are digits
    operandsChecker = str.isdigit(operandsDigits)
    #check if equation contains division with 0
    if '/ 0' in eq:
        zeroChecker = False
    else:
        zeroChecker = True

    #if conditions for the
    if operandsChecker is False or operatorChecker is False or zeroChecker is False:
        print("Invalid Input")
    else:
        stack, queue = parseNumbers(eq)
        stackAnswer = calculateStack(stack)
        queueAnswer = calculateQueue(queue)
        print("Queue total:", queueAnswer)
        print("Stack total:", stackAnswer)
        if queueAnswer == stackAnswer:
            print("They do match!")
        else:
            print("They do not match!")

if __name__ == '__main__':
    main()