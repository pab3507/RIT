from math import *

def main():
    grades = list()

    datafile = open('data.txt')

    for line in datafile:
        elements = line.split()
        print(elements)

        name = elements[0]
        grade1 = elements[1]
        grade2 = elements[2]
        grade3 = elements[3]

        grades = grades + [int(grade1)]
        grades += [int(grade2)]
        grades.append(int(grade3))

    print(grades)

    sum = 0
    for loopCounter in range(0, len(grades)):
        sum += grades[loopCounter]

    for grade in grades:
        sum+= grade

    avg = sum/len(grades)
    print(avg)

def swap(lst,i,j):
    temp = lst[i]
    lst[i] = lst[j]
    lst[j] = temp

    #lst[i], lst[j] = lst[j], lst[i]

def insert(lst,mark):
    index = mark
    while index > -1 and lst[index] > lst[index+1]:
        swap(lst, index, index +1)
        index -= 1

#a list is pass by reference, so a list can be used outside a function

def insertion_sort(lst):
    for mark in range(len(lst)-1):
        insert(lst, mark)

m=[35,8,0,-7,22, 200, 0, 141, 18,22,50,42,pi]

"""
Time Complextity for insertion sorts

N is moving mark, N-1 swaps

Best Case O(N)

Worst Case O(N(N-1)) => O(N^2-N) => O(N^2)


"""

