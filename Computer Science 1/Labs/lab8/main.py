from rit_lib import *
from train import *
from trainCar import *
from Location import *
from map import *
from mapCursor import *

def processCommand(myTrain, myMap, args):
    """
    This function processes the commands inputed.
    :param myTrain: the train created
    :param myMap: the map created

    """
    args = None
    if args[0] == 'file':
        file = open(args[1])
        for currentLine in file:
            processCommand(myTrain,myMap, currentLine.split(" "))
        file.close()
    if args[0] == 'addStop':
        myMap.addStop(args[1], int(args[2]))
    if args[0] == 'help':
        print("    help - this help")
        print("    quit - quit the simulator")
        print("    start <time> - start moving the train for time (in minutes)")
        print("    addCar <content> - adds car to the train")
        print("    addHome <name> - adds home location to the map with the given name")
        print("    addStop <name> <distanceFromPrevious> - adds stop to route")
        print("    setEngine <type><speed> - sets the engine for the train, with the given type and the speed.")
        print("    file <file> - reads the commands from a file")
        print("    printMap - prints the map in the form: (home - distance to location 1 - location1, etc")
    if args[0] == 'printMap':
        mapCursor = myMap.getCursor()
        while mapCursor.hasNext():
            data = mapCursor.moveNext()
            if data.DistanceToNext != 0:
                print(data.Name + ' ---' + str(data.DistanceToNext) + '--> ', end='')
            else:
                print(data.Name)
    if args[0] == 'setEngine':
        myTrain.setEngine(args[1], int(args[2]))
    if args[0] == 'start':
        if Train.EngineType != "" and Map.startLocation is not None:
            distance = Train.EngineSpeed * int(args[1]) / 60
            print("Train will travel:", distance, "miles")
            myTrain.currentLocation = myMap.getCursor()
            while distance > 0 and myTrain.currentLocation.hasNext():
                stoppedAt = myTrain.currentLocation.moveNext()
                if myTrain.car is not None:
                    print("Train stopped in " + stoppedAt.Name + " to drop off " + myTrain.car.content)
                    myTrain.car = myTrain.car.NextCar
                else:
                    print("Train stopped in " + stoppedAt.Name + " but had no cargo to drop off")
            distance -= stoppedAt.distancetoNext
    if args[0] == 'addCar':
        myTrain.addCar(args[1])
    if args[0] == 'addHome':
        myMap.addStop(args[1], 0)
    if args[0] == 'quit':
        exit()


def convertFile(filename):
    """
    This converts the textfile to a list
    :param filename: textfile used
    :return: a list is returned
    """
    file = open(filename)
    List = []
    for currentLine in file:
        List.append((currentLine.strip()))
    return List


def main():
    """
    This function executes the program.
    """
    textFile = input("Enter file: ")
    file = convertFile(textFile)
    myTrain = Train("",0,None,None)
    myMap = Map(None)
    for args in file:
        args.split(' ')
        if args[0] == 'quit':
            break
        else:
            processCommand(myTrain,myMap, args)

if __name__ == "__main__":
    main()

