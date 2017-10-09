__author__ = 'Moisés Lora'

from rit_lib import *
from train import *
from Location import *

class MapCursor( struct ):
    _slots = ((NoneType, 'Location'), 'currentLocation')

    def hasNext(self):
        """
        This function checks if there is a next
        """
        return self.currentLocation is not None

    def moveNext(self):
        """
        This moves to the next location on the map.
        """
        if not self.hasNext():
            raise RuntimeError("Nothing left")
        location = self.currentLocation
        self.currentLocation = self.currentLocation.next
        return location
