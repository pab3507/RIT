"""
file: hashtable.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
description: This is a modified version of the file provided in our lecture, which now implements rehashing."""

from rit_lib import *

class HashTable(struct):
    """
           The HashTable data structure contains a collection of values
       where each value is located by a hashable key.
       No two values may have the same key, but more than one
       key may have the same value.
       table is the list holding the hash table
       size is the number of elements in occupying the hashtable

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


    def hash_function(self, val):
        """
        hash_function: K NatNum -> NatNum
        Compute a hash of the val string that is in [0 ... n).
        """
        hashcode = hash(val) % len(self.table)
        # hashcode = 0
        # hashcode = len(val) % n
        return hashcode

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

        #rehash if the number of items stored in the hashtable exceeds 0.75 of the capacity.
        if (len(self.table) * 0.75) <= self.size:
            #calls the method resizeHashTable( ) if the condition is satisfied.
            self.resizeHashTable()
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
        startIndex = index  # We must make sure we don't go in circles.
        while self.table[ index ] != None and self.table[ index ].key != key:
            index = (index + 1) % len(self.table)
            if index == startIndex:
                raise Exception("Hash table does not contain key.")
        if self.table[ index ] == None:
            raise Exception("Hash table does not contain key:", key)
        else:
            return self.table[ index ].value

    def resizeHashTable(self):
        """
        This function allocates a lager Python list that is twice the size of the current list and populate it
        with the data that is currently stored in the hash table for rehashing purposes.
        """
        #variable for the temporal new hashtable that takes in double the size of the current list.
        doubleHashTable = [None for _ in range(2*len(self.table))]
        #this for loops through the keys in the hashtable.
        for key in self.keys():
            value = self.get(key)
            #modified code that does esentially the same as the put function
            index = hash(key) % len(doubleHashTable)
            startIndex = index  # We must make sure we don't go in circles.
            while doubleHashTable[index] != None and doubleHashTable[index].key != key:
                index = (index + 1) % len(doubleHashTable)
                if index == startIndex:
                    raise Exception("Hash table is full.")
            if doubleHashTable[index] == None:
                doubleHashTable[index] = Entry(key, value)
            else:
                doubleHashTable[index].value = value
        #setting the value of the temporal double hash table to class' hashtable.
        self.table = doubleHashTable
        return True

def createHashTable(capacity=100):
    """
    createHashTable: NatNum? -> HashTable
    """
    if capacity < 2:
        capacity = 2
    aHashTable = HashTable([None for _ in range(capacity)], 0)
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
