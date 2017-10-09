from myStack import *

def operationPrecedence(op1,op2):
    if op1 == op2:
        return True
    firstOrder = ['*','/']
    secondOrder = ['+','-']
    if op1 in firstOrder and (op2 in firstOrder or op2 in secondOrder) :
        return True
    if op1 in secondOrder and op2 in secondOrder:
        return True
    return False

def operationPrecedenceTest():
    plus = '+'
    plus2 = '+'
    sub = '-'
    mult = '*'
    div = '/'
    firstOrder = ['*', '/']
    secondOrder = ['+', '-']
    expected = True
    if operationPrecedence(plus,plus2) == expected:
        print("Succesful")
    else:
        print("Fail")
    if mult in firstOrder and (div in firstOrder or div in secondOrder):
        print("Succesful")
    else:
        print("Fail")
    if mult in firstOrder and (sub in firstOrder or sub in secondOrder):
        print("Succesful")
    else:
        print("Fail")
    if plus in secondOrder and sub in secondOrder:
        print("Succesful")
    else:
        print("Fail")

def operationEval(num1,num2,operator):
    if operator == '+':
        return num1 + num2
    elif operator == '-':
        return num1 - num2
    elif operator == '/':
        return num1 / num2
    elif operator == '*':
        return num1 * num2
    else:
        return None

def split(s):
    list = []
    if s == "":
        return list
    list.append(s[0])
    for x in s[1:]:
        if x.isdigit() and list[-1].isdigit():
            list[-1]+= x
        else:
            list.append(x)
    return list

def compute(s):
    operators = ('+', '-', '/', '*')
    numStack = None # Number Stack
    opStack = None # Operator Stack
    for x in split(s):
        if x.isdigit():
            numStack = push(numStack, int(x))
            continue
        elif x in operators:
            if emptyStack(opStack):
                opStack = push(opStack, x)
            else:
                while not emptyStack(opStack) and operationPrecedence(top(opStack),x) and not emptyStack(numStack):
                    topNum1 = top(numStack)
                    numStack = pop(numStack)
                    if emptyStack(numStack):
                        return None
                    topNum2 = top(numStack)
                    numStack = pop(numStack)
                    topOp = top(opStack)
                    opStack = pop(opStack)
                    result = operationEval(topNum2,topNum1,topOp)
                    numStack = push(numStack,result)
                opStack = push(opStack,x)
        elif x == '(':
            if emptyStack(opStack):
                return None
            opStack = push(opStack, x)
        elif x == ')':
            if emptyStack(opStack):
                return None
            while not emptyStack(opStack) and top(opStack) is not '(':
                topNum1 = top(numStack)
                if emptyStack(numStack):
                    return None
                numStack = pop(numStack)
                if emptyStack(numStack):
                    return None
                topNum2 = top(numStack)
                numStack = pop(numStack)
                topOp = top(opStack)
                opStack = pop(opStack)
                result = operationEval(topNum2,topNum1,topOp)
                numStack = push(numStack, result)
            if emptyStack(opStack):
                return None
            opStack = pop(opStack)
    while (not emptyStack(numStack)) and (not emptyStack(opStack)):
        topNum1 = top(numStack)
        numStack = pop(numStack)
        if emptyStack(numStack):
            return None
        topNum2 = top(numStack)
        numStack = pop(numStack)
        topOp = top(opStack)
        opStack = pop(opStack)
        result = operationEval(topNum2, topNum1, topOp)
        numStack = push(numStack, result)
    if emptyStack(numStack):
        return None
    result = top(numStack)
    numStack = pop(numStack)
    return int(result)

def computeTest():
    s1 = '3*3'
    s2 = '6*(10-3)'
    s3 = '5+(21/3)'
    expected = 9
    expected2 = 42
    expected3 = 12
    expected4 = 15

    if compute(s1) == expected:
        print("Succesful Test")
    else:
        print("Failed test")
    if compute(s2) == expected2:
        print("Succesful Test")
    else:
        print("Failed test")
    if compute(s3) == expected3:
        print("Succesful Test")
    else:
        print("Failed test")
    if (compute(s1) or compute(s2) or compute(s3)) == expected4:
        print("Succesful Test")
    else:
        print("Failed test")

def evaluate(s):
    if '=' in s:
        eq = s.split('=')
        leftSide = eq[0]
        rightSide = eq[1]
        resLeft = compute(leftSide)
        resRight = compute(rightSide)
        if resLeft is None or resRight is None:
            return False
        return resLeft == resRight
    else:
        return False

def evaluateTest():
    s1 = '4+5*3=20-1'
    s2 = '6*(5-3)=10+2'
    s3 = '10+5/1=20-5*1'
    expected = True
    expected2 = False
    if evaluate(s1) == expected:
        print("Succesful Test")
    else:
        print("Failed Test")
    if evaluate(s2) == expected:
        print("Succesful Test")
    else:
        print("Failed Test")
    if evaluate(s3) == expected:
        print("Succesful Test")
    else:
        print("Failed Test")
    if (evaluate(s1) or evaluate(s2) or evaluate(s3)) == expected2:
        print("Succesful Test")
    else:
        print("Failed Test")

def solve(s):
    for i in range(0,len(s)-1):
        for j in range(i+1, len(s)-1):
            res = s[:i] + s[i+1:j] + s[j+1:]
            if evaluate(res):
                return res
def solveTest():
    s1 = '466-8*51=96/16'
    s2 = '38*21+63=3*7*3-10'
    s3 = '6*(15-3)=100-7*2*2*2'
    expected = '46-8*5=96/16'
    expected2 = '38*1+63=37*3-10'
    expected3 = '6*(15-3)=100-7*2*2'
    expected4 = '5-2=10-3'
    if solve(s1) == expected:
        print("Successful Test")
    else:
        print("Failed Test")
    if solve(s2) == expected2:
        print("Successful Test")
    else:
        print("Failed Test")
    if solve(s3) == expected3:
        print("Successful Test")
    else:
        print("Failed Test")
    if (solve(s1) or solve(s2) or solve(s3)) == expected4:
        print("Successful Test")
    else:
        print("Failed Test")

def read_file(filename):
    """
    This function opens the filename and returns the list of puzzles.
    :param filename: textfile inputed
    :return: puzzleList
    """
    file = open(filename)
    puzzleList = []
    for currentLine in file:
        puzzleList.append((currentLine.strip()))
    return puzzleList

def main():
    textFile = input("Input the name of your file containing the puzzles: ")
    puzzlesFile = read_file(textFile)
    for puzzle in puzzlesFile:
        print("Puzzle is:", puzzle)
        s = puzzle
        print("Solution is:", solve(s))

def TestFunctions():
    computeTest()
    evaluateTest()
    solveTest()

#TestFunctions()
if __name__ == "__main__":
    main()
