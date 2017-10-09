"""
file: olympics_simpl.py
language: python3
author: mal3941@g.rit.edu Moisés Lora Pérez
class: CSCI 141-03
description: This program counts the number of gold medals in a given year and also counts the number of
medals that a certain athlete has been awarded.".
"""

def goldMedalinYear(year, filename):
    """
    This function counts how many gold medals there were in a specific year of the olympics.
    It takes the following parameters and returns:

    pre-conditions: year is a string
    postconditions: File is unmodified, the amount of gold medals won in that year is returned

    :param year: The year that will be used to count the amount of gold medals.
    :param filename: The filename that will be used
    :return: The amount of gold medals won in that year
    """

    gcounter = 1 #This variable serves as a counter and it ranges from 0 to 5, which accounts to the line numbers.
    goldMedals = 0 #This variable counts the amount of goldMedals that have been won.
    isCorrectYear = False #This variable checks if the year inputed is the same as the line being read.

    with open(filename, 'r', encoding='utf-8') as file:
        for line in file:
            line = line.strip()
            if gcounter == 3:
                if line == str(year):
                    isCorrectYear = True
            if gcounter == 4:
                if isCorrectYear is True and line == "1":
                    goldMedals += 1
                isCorrectYear = False
            if gcounter == 5:
                gcounter = 0

            gcounter += 1

    return goldMedals

def countByName(lastName, firstName, filename):
    """
    This function counts all gold, silver and bronze medals for a given athlete of the olympics.
    It takes the following parameters and returns:

    pre-conditions: firstName and lastName are strings
    postconditions: file is unmodified, the amount of medals won is returned

    :param lastName: The last name of the athlete inputed.
    :param firstName: The first name of the athlete inputed.
    :param filename: The filename that will be used
    :return: The amount of gold, silver and bronze medals won by that athlete.
    """

    nameCounter = 1 #This variable serves as a counter and it ranges from 0 to 5, which accounts to the line numbers.
    isCorrectName = False #This variable evaluates whether the names compare to the names on the text.
    gmedals = 0 #Counts the amount of gold medals
    smedals = 0 #Counts the amount of silver medals
    bmedals = 0 #Counts the amount of bronze medals

    with open(filename, 'r', encoding='utf-8') as file:
        for line in file:
            line = line.strip().upper()
            if nameCounter == 1:
                if line == lastName.upper():
                    isCorrectName = True
                else:
                    isCorrectName = False
            if nameCounter == 2 and isCorrectName is True:
                if line == firstName.upper():
                    isCorrectName = True
                else:
                    isCorrectName = False
            if nameCounter == 4:
                if isCorrectName is True and line == '1':
                    gmedals += 1
                else:
                    pass
                if isCorrectName is True and line == '2':
                    smedals += 1
                else:
                    pass
                if isCorrectName is True and line == '3':
                    bmedals += 1

            if nameCounter == 5:
                nameCounter = 0
                isCorrectName = False

            nameCounter += 1

    return gmedals, smedals, bmedals

"""
if filename wants to be asked to the user

filenameInput = str(input("Enter the filename to be looked at: "))
filename = filenameInput
"""

#name of filename to be looked at is below.
filename = 'athletes.txt'

#user is asked for year to look at
yearInput = str(input("Enter year to count its winners:"))

goldMedalsWon = goldMedalinYear(yearInput, filename)
#values returned are printed
print("In year " + str(yearInput) + " there were " + str(goldMedalsWon) + " gold medalists in total." )

print("Let’s look up the total medals won by an athlete (1896-2008)!")

#last name of athlete is asked
lastNameInput = str(input("Last name:"))
#first name of athlete is asked
firstNameInput = str(input("First name:"))

#Since values of the function countByName are returned in a tuple, there values by order are assigned to a variable
medalCounts = countByName(lastNameInput,firstNameInput,filename)
gold = medalCounts[0]
silver = medalCounts[1]
bronze = medalCounts[2]
"""
Values returned are printed in order (strings printed are uppercased so that the letters are uniform.
ie: PhElpS would be printed as PHELPS instead
"""
print(str.upper(firstNameInput) + " " + str.upper(lastNameInput) + " won " + str(medalCounts[0]) + " gold, "
+ str(medalCounts[1]) + " silver and " + str(medalCounts[2]) + " bronze medals.")

