from rit_lib import *
from slList import *
from rooms import *
from floors import *
from room import *


class BuildingTable(struct):
    """
           The HashTable data structure contains a collection of values
       where each value is located by a hashable key.
       No two values may have the same key, but more than one
       key may have the same value.
       table is the list holding the hash table
       size is the number of elements in occupying the hashtable.
    """
    _slots = ((list, 'table'), (int, 'size'))

    def HashTableToStr(self):
        """
        HashTableToStr: HashTable -> String
        """
        result = ""
        for i in range(len(self.table)):
            e = self.table[i]
            if not e == None:
                result += str(i) + ": "
                result += e.EntryToStr() + "\n"
        return result


    def hash_function(self, name):
        """
        Modified hash function for the floors.
        """
        hashval = 0
        for letter in name:
            hashval += (ord(letter) - ord('a'))
        hallnum = hashval % len(self.table)
        # hashcode = 0
        # hashcode = len(val) % n
        return hallnum

    def keys(self):
        """
        keys: HashTable(K, V) -> List(K)
        Return a list of keys in the given hashTable.
        """
        result = []
        for entry in self.table:
            if entry != None:
                result.append(entry.key)
        return result

    def has(self, key):
        """
        has: HashTable(K, V) K -> Boolean
        Return True iff hTable has an entry with the given key.
        """
        index = self.hash_function(key)
        startIndex = index  # We must make sure we don't go in circles.
        while self.table[ index ] != None and self.table[ index ].key != key:
            index = (index + 1) % len(self.table)
            if index == startIndex:
                return False
        return self.table[ index ] != None

    def put(self, key, value):
        """
        put: HashTable(K, V) K V -> Boolean

        Using the given hash table, set the given key to the
        given value. If the key already exists, the given value
        will replace the previous one already in the table.
        If the table is full, an Exception is raised.
        """

        index = self.hash_function(key)
        startIndex = index  # We must make sure we don't go in circles.
        while self.table[ index ] != None and self.table[ index ].key != key:
            index = (index + 1) % len(self.table)
            if index == startIndex:
                raise Exception("Hash table is full.")
        if self.table[ index ] == None:
            self.table[ index ] = Entry(key, value)
            self.size += 1
        else:
            self.table[ index ].value = value
        return True

    def get( self, key):
        """
        get: HashTable(K, V) K -> V

        Return the value associated with the given key in
        the given hash table.

        Precondition: self.has(key)
        """
        index = self.hash_function(key)

        return self.table[ index ].value

def createBuilding():
    """
    Function creates the building structure.
    :return:
    """
    buildings = createBuildingHashTable(5)
    for i in range(5):
        floors = createFloorsTable(4)
        for j in range(4):
            rooms = createRoomsHashTable(5)
            for k in range(5):
                rooms.table[k] = Entry(k, Room(None, 0))
            floors.table[j] = Entry(j, rooms)
        buildings.table[i] = Entry(i, floors)
    return buildings

def printBuildings(buildings):
    for i in range(5):
        print("Building" + str(i))
        floors = buildings.table[i]
        for k in range(4):
            print("\tFloor" + str(k))
            rooms = floors.value.table[k]
            for j in range(5):
                print("\t\tRoom" + str(j))
                room = rooms.value.table[j].value
                myRoomCursor = room.students.getCursor()
                while myRoomCursor.hasNext():
                    student = myRoomCursor.moveNext()
                    print("\t\t\t", end = " ")
                    print(student)


def createBuildingHashTable(capacity=100):
    """
    createHashTable: NatNum? -> HashTable
    """
    if capacity < 2:
        capacity = 2
    aHashTable = BuildingTable([None for _ in range(capacity)], 0)
    return aHashTable

class Entry(struct):
    """
       A class used to hold key/value pairs.
    """

    _slots = ((object, "key"), (object, "value"))


    def EntryToStr( self ):
        """
        EntryToStr: Entry -> String
        return the string representation of the entry.
        """
        return "(" + str(self.key) + ", " + str(self.value) + ")"
