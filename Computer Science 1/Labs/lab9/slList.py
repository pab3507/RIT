"""
File: slList.py
Purpose: rit_object-based single-linked list for CS141 LECTURE.
Author: ben k steele <bks@cs.rit.edu>
Author: sean strout <sps@cs.rit.edu>
Language: Python 3
Description: Implementation of a single-linked list data structure.
"""
from slCursor import *

###########################################################
# LINKED LIST CLASS DEFINITION                                
###########################################################

class SlList( struct ):
    """
    SlList class encapsulates a node based linked list.
    'head' slot refers to a Node instance.
    'size' slot holds the number of nodes in the list.
    """
    _slots = ( ((Node, NoneType), 'head'), (int, 'size' ))


    def getCursor(self):
        return Cursor(self.head)


    ###########################################################
    # LINKED LIST FUNCTIONS
    ###########################################################

    def clear( self ):
        """
           Make a list empty.
           Parameters:
               lst ( SlList ) - the linked list
           Returns:
               None
        """

        self.head = None
        self.size = 0


    def toString(self):
        """
        Converts our linked list into a string form that is similar to Python's
        printed list.

        Parameters:
            lst (SlList) - The linked list
        Returns:
            A string representation of the list (e.g. '[1,2,3]')
        """

        result = '['
        curr = self.head
        while not curr == None :
            if curr.next == None :
                result +=  str(curr.data)
            else:
                result += str(curr.data) + ', '
            curr = curr.next
        result += ']'

        return result


    def append( self, value ):
        """
           Add a node containing the value to the end of the list.

           Parameters:
               lst ( SlList ) - The linked list
               value ( any type ) - The data to append to the end of the list
           Returns:
               None
        """

        if self.head == None :
            self.head = Node( value, None )
        else:
            curr = self.head
            while curr.next != None :
                curr = curr.next
            curr.next = Node( value, None )
        self.size += 1

    def insertAt( self, index, value ):
        """
           Insert a new element before the index.

           Parameters:
               lst ( SlList ) - The list to insert value into
               index ( int ) - The 0-based index to insert before
               value ( any type ) - The data to be inserted into the list
           Preconditions:
               0 <= index <= lst.size, raises IndexError exception
           Returns:
               None
        """

        if index < 0 or index > self.size:
            raise IndexError( str( index ) + ' is out of range.' )

        if index == 0:
            self.head = Node( value, self.head )
        else:
            prev = self.head
            while index > 1:
                prev = prev.next
                index -= 1
            prev.next = Node( value, prev.next )

        self.size += 1

    def get( self, index ):
        """
           Returns the element that is at index in the list.

           Parameters:
               lst ( SlList ) - The list to insert value into
               index ( int ) - The 0-based index to get
           Preconditions:
               0 <= index < lst.size, raises IndexError exception
           Returns:
               value at the index
        """

        if index < 0 or index >= self.size:
            raise IndexError( str( index ) + ' is out of range.' )

        curr = self.head
        while index > 0:
            curr = curr.next
            index -= 1
        return curr.data

    def set( self, index, value ):
        """
           Sets the element that is at index in the list to the value.

           Parameters:
               lst ( SlList ) - The list to insert value into
               index ( int ) - The 0-based index to set
               value ( any type )
           Preconditions:
               0 <= index < lst.size, raises IndexError exception
           Returns:
               None
        """

        if index < 0 or index >= self.size:
            raise IndexError( str( index ) + ' is out of range.' )

        curr = self.head
        while index > 0:
            curr = curr.next
            index -= 1
        curr.data = value

    def pop( self, index ):
        """
           pop removes and returns the element at index.

           Parameters:
               lst ( SlList ) - The list from which to remove
               index ( int ) - The 0-based index to remove
           Preconditions:
               0 <= index < lst.size, raises IndexError exception
           Returns:
               The value ( any type ) being popped
        """

        if index < 0 or index >= self.size:
            raise IndexError( str( index ) + ' is out of range.' )

        if index == 0:
            value = self.head.data
            self.head = self.head.next
        else:
            prev = self.head
            while index > 1:
                prev = prev.next
                index -= 1
            value = prev.next.data
            prev.next = prev.next.next

        self.size -=1
        return value

    def index( self, value ):
        """
           Returns the index of the first occurrence of a value in the list

           Parameters:
               lst ( SlList ) - The list to insert value into
               value ( any type ) - The data being searched for
           Preconditions:
               value exists in list, otherwise raises ValueError exception
           Returns:
               The 0-based index of value
        """

        pos = 0
        curr = self.head
        while not curr == None :
            if curr.data == value:
                return pos

            pos += 1
            curr = curr.next

        raise ValueError( str( value ) + " is not present in the list" )



###########################################################
# LINKED LIST CLASS CONSTRUCTOR                                
###########################################################

def createList():
    """
       Create and return an instance
       of an empty node-based, single-linked list.
       Parameters:
           None
       Returns: 
           An empty list
    """
    return SlList( None, 0 )

#FUNCTIONS NEEDED FOR THE HOMEWORK BELOW

def swap(node1, node2):
    """
    This function swaps the data pertaining to two separate nodes.
    """
    node1.data, node2.data = node2.data, node1.data

def findMinFrom(node):
    """
    This function finds the node with the minimum value in the linked list and then returns it.
    """
    minNode = node
    while node is not None:
        if minNode.data > node.data:
            minNode = node
        node = node.next
    return minNode

def linkSort(lst):
    """
    This function sorts the linked list with the selection sort algorithm implemented to be used with linked lists.
    This function is then used to sort a linked list inputed by the user.
    """
    currentNode = lst.head
    while currentNode is not None:
        minNode = findMinFrom(currentNode)
        swap(minNode,currentNode)
        currentNode = currentNode.next
