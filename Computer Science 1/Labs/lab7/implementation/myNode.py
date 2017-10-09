"""
Nodes
file: myNode.py
author: Sean Strout
"""

from rit_lib import *


class Node(struct):
    _slots = ( (object, 'data'), ((NoneType, 'Node'), 'next') )
    
