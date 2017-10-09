from slNode import *

class Cursor(struct):
    _slots = ((Node, NoneType ), 'node')

    def hasNext(self):
        return self.node != None

    def moveNext(self):
        if self.hasNext():
            retVal = self.node.data
            self.node = self.node.next
            return retVal
        else:
            raise IndexError("move next at end of list")