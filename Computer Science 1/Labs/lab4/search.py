"""
file: search.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
description: This program does several processes regarding strings.
"""

def strHead(s):
    """
    A function that returns a string head.
    :param s: A string
    :return: Returns the character at position 0 of the string(first letter).
    """
    return s[0]
def strTail(s):
    """
    A function that returns a string tail.
    :param s: A string
    :return: Returns the characters from position 1 and on. (second to last letters)
    """
    return s[1:]

def searchChar(char, s):
    """
    This function compares a character with a string and determines that if they're equal its return will be True
    otherwise it will return false.
    :param char: Character to be matched to the string
    :param s: String to be matched to.
    :return: Returns True or False depending if the character matches the string or not.
    """
    if s == "":
        return False
    elif char == "":
        return True
    else:
        return char == strHead(s) or searchChar(char, strTail(s))

def matchString(pre, s):
    """
    This functions checks whether the prefix of a string matches up with another string.
    :param pre: The prefix of the string
    :param s: A string
    :return: Returns True or False depending if the prefix matches up or not.
    """
    if pre == "":
        return True
    elif s == "":
        return False
    else:
        return strHead(pre) == strHead(s) and matchString(strTail(pre),strTail(s))

def searchString(searchfor, searched):
    """
    This functions searches if there's matching sets of characters in the strings compared.
    :param searchfor: This is the string that will be compared.
    :param searched: This is the string that will be compared to.
    :return: Returns True or False depending if the strings match with each other.
    """
    if searchfor == "":
        return True
    elif searched == "":
        return False
    else:
        return matchString(searchfor, searched) or searchString(searchfor, searched[1:])

def matchPat(pattern, string):
    if pattern == "":
        return True
    elif string == "":
        return False
    elif strHead(pattern) == '*':
        return matchPat(pattern, strTail(string)) or matchPat(strTail(pattern), string)
    else:
        return strHead(pattern) == strHead(string) and matchPat(pattern[1:], string[1:])

def searchPat(pattern, string):
    """
    This functions searches for a pattern in a certain string.
    :param pattern: The pattern that will be searched.
    :param string: The string that is going to be searched.
    :return: Returns True or False depending if pattern matches up to the string.
    """
    if pattern == "":
        return True
    elif string == "":
        return False
    else:
        return matchPat(pattern, string) or searchPat(pattern, strTail(string))

#A true and false test case is provided for every function.

print(searchChar('b', 'abc'))
print(searchChar('d', 'abc'))
print(matchString('ab', 'abc'))
print(matchString('bc', 'abc'))
print(searchString('ab', 'abc'))
print(searchString('ac', 'abc'))
print(matchPat('a*t*r', 'anteaters'))
print(matchPat('a*t*r', 'artist'))
print(searchPat('a*t*r', 'The artist worked'))
print(searchPat('a*t*r', 'The artist was painted'))