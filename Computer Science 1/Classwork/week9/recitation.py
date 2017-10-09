from slList import *
from slNode import *
from rit_lib import *

class SlList( struct ):

    _slots = (((Node, NoneType), 'head'), (int, 'size'))

    lst = createList()

    lst.append("ahoy")
    lst.append("booty")
    lst.append("landlubber")
    lst.append("swashbucklers")
    lst.append("grog")
    lst.append("dubloon")
    print("size: ", lst.size)
    print("get 1:", lst.get(1))
    print("get 4:", lst.get(4))
    print("index dubloon:", lst.index("dubloon"))

