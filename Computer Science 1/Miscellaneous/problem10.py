
from slList import *

lst = createList()

# populate list
for i in range(10):
    lst.append(i)


def findNthElement(lst, n):

    # set cursor to head
    cursor = lst.getCursor()

    # number of iterations needed
    m = lst.size - n

    while m > 0:
        cursor.moveNext()
        m -= 1

    return cursor.node.data

print(lst)
print(findNthElement(lst,2))

