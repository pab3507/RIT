
from rit_lib import *

class Pet(struct):
    _slots = ((str,'name'),(int,'age'),(str,'species'))

    def birthday(self):
        self.age += 1
        print("Happy Birthday,", self.name,"!")


def createPet(name, spec):
    return Pet(name,0,spec)

p = createPet("Chip","dog")
print(p)
p.birthday()
print(p)

print(p.name)