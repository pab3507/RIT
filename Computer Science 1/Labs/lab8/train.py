__author__ = 'Moisés Lora'

from rit_lib import *
from trainCar import *
from map import *
from mapCursor import *

class Train( struct ):
    _slots = ((str, 'EngineType'), (int, 'EngineSpeed'), ((TrainCar, NoneType), 'car'), ((MapCursor, None), "currentLocation"))

    def addCar(self, content):
        """
        This function adds a car to the train.
        """
        if self.car is None:
            self.car = TrainCar(content, None)
        else:
            curCar = self.car
            while curCar.next is not None:
                curCar = curCar.next
            curCar.next = TrainCar(content, None)

    def setEngine(self, type, engineSpeed):
        """
        This function sets the engine type and speed for the train.
        """
        self.EngineType = type
        self.EngineSpeed = int(engineSpeed)
