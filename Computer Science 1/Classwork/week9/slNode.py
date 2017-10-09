"""
File: slNode.py
Purpose: rit_lib struct based single linked node for CS1 LECTURE.
Author: Sean Strout <sps@cs.rit.edu>
Contributor: ben k steele <bks@cs.rit.edu>
Contributor: Jeremy S. Brown <jsb@cs.rit.edu>
Language: Python 3
Description:  A representation of a single linked node intended
for use as the building block for a single-linked list.
"""

from rit_lib import *

###########################################################
# NODE CLASS DEFINITIONS
###########################################################


class Node( struct ):
    """
       Slots of a linked node structure
       data: an object reference allows any kind of element
       next: either a Node reference or None
    """
    # syntax is rit_object version 2.5b
    _slots = (( object, 'data'), ((NoneType, 'Node'), 'next' ))


    ###########################################################
    # CURSOR FUNCTIONS
    ###########################################################



def test_node():
    """
    test_node tests the {NoneType, Node} type constructions
    """
    node1 = Node( 'first', None )
    print( node1.data == 'first' and node1.next == None )
    node2 = Node( 'one-two', Node( 'two', None ) )
    print( node2.data == 'one-two' )
    print( isinstance( node2.next, Node ) )
    print( node2.next.data == 'two' )
    print( node2.next.next == None )


if __name__ == "__main__":
    test_node()

