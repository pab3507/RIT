__author__ = 'Moisés Lora'

from rit_lib import *
from train import *
from Location import *

class Location( struct ):
    _slots = ((str, 'Name'), ((NoneType, 'Location'), 'nextLocation'), (int, 'distanceToNext'))
