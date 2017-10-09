from rit_lib import *
from student import *

class Room(struct):
    """
    Represents the attributes of the room
    """
    _slots = (((NoneType, Student), 'occupant'), (int, 'size'))

    def AddtoRoom(self, name, furniture=None):

        if self.size > 3:
            print('Let the riots begin')
            exit()
        if self.occupant is None:
            self.occupant = Student(name, [], None)
            self.size += 1
            return False
        value = self.Search(name)
        alreadyPresent = False
        if value.name == name:
            value.addFurniture(furniture)
            alreadyPresent = True
        else:
            value.next = Student(name, [], None)
            self.size += 1
            if furniture is not None:
                value.next.addFurniture(furniture)
        return alreadyPresent

    def Search(self, name):
        currOcupant = self.occupant
        while currOcupant.next is not None and currOcupant.name != name:
            currOcupant = currOcupant.next
        return currOcupant

class Cursor(struct):
    _slots = ((Student, NoneType), 'node')
    def hasNext(self):
        return self.node != None

    def moveNext(self):
        if self.hasNext():
            retVal = self.node.data
            self.node = self.node.next
            return retVal
        else:
            raise IndexError("move next at end of list")