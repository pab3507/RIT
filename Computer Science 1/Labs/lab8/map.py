__author__ = 'Moisés Lora'

from rit_lib import *
from train import *
from Location import *

class Map( struct ):
    _slots = (((NoneType, Location), "startLocation"))

    def addStop(self, name, distanceToNext):
        """
        This adds a stop for the train route.
        """
        if self.startLocation is None:
            self.startLocation = Location(name, None, 0)
        else:
            currStop = self.startLocation
            while currStop.next is not None:
                currStop = currStop.next
            currStop.distanceToNext = distanceToNext
            currStop.next = Location(name, None, 0)
