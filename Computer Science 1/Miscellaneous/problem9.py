from slList import *

def count(cursor,element):
    if cursor.hasNext():
        data = cursor.node.data
        cursor.moveNext()
        if data == element:
            return 1 + count(cursor,element)
        else:
            return count(cursor,element)
    else:
        return 0


def countAcc(cursor,element,occurences):
    if not cursor.hasNext():
        return occurences

    elif cursor.node.data == element:
        occurences += 1

    cursor.moveNext()
    return countAcc(cursor,element,occurences)


def countItt(cursor,element):
    occurences = 0
    while cursor.hasNext():
        if cursor.node.data == element:
            occurences += 1
        cursor.moveNext()
    return occurences


def main():
    lst = createList()

    # populate list
    for i in range(10):
        lst.append(i)

        if i % 2 == 0:
            lst.append(i)

    print(lst.toString())


    print(count(lst.getCursor(),5))
    print(count(lst.getCursor(),4),"\n")

    print(countAcc(lst.getCursor(),5,0))
    print(countAcc(lst.getCursor(),4,0),"\n")

    print(countItt(lst.getCursor(),5))
    print(countItt(lst.getCursor(),4),"\n")

if __name__ == "__main__":
    main()
