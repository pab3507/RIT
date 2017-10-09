from rit_lib import *

class Student(struct):
    """
    Represents the attributes of the student.
    :slot name (str): the name
    :slot furnitureList (list): List of furniture
    :slot Student (NoneType): represents of one of the students in the room.
    next necessary for it to become a linked list.
    """
    _slots = ((str, 'name'), (list, 'furnitureList'), ((NoneType,'Student'), 'next'))


    def addFurniture(self, furniture):
        """
        Takes in a furniture and adds it to a list.
        :param furniture:
        :return:
        """
        self.furnitureList += furniture



