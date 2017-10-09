

from copy import deepcopy
from sys import argv

# DO NOT CHANGE ANYTHING BETWEEN HERE AND THE END MARKER

class SkyscraperConfig:
    """
    A class that represents a skyscraper configuration.
        DIM - square board DIMension (int)
        lookNS - north to south looking values, left to right
            (list of DIM int's)
        lookEW - east to west looking values, top to bottom
            (list of DIM int's)
        lookSN - south to north looking values, left to right
            (list of DIM int's)
        lookWE - west to east looking values, top to bottom
            (list of DIM int's)
        board - square grid of values
            (list of list of int's, size DIM*DIM)
        curr - the first unpopulated cell from top to bottom, left to right
            (r,c)
        prev - the cell prior to curr (r,c)
    """
    __slots__ = ('DIM', 'lookNS', 'lookEW', 'lookSN', 'lookWE', 'board',
        'curr', 'prev')

    """The empty cell value"""
    EMPTY = 0   # can be referenced anywhere as: SkyscraperConfig.EMPTY

    def __init__(self, dim, lookNS, lookEW, lookSN, lookWE, board):
        """
        Constructor.
        """

        self.DIM = dim
        self.lookNS = lookNS
        self.lookEW = lookEW
        self.lookSN = lookSN
        self.lookWE = lookWE
        self.board = board

        # find first unpopulated cell
        self.prev = None
        row, col = (0,0)
        while True:
            if row == self.DIM and col == 0 or self.board[row][col] == \
                SkyscraperConfig.EMPTY:
                    break
            else:
                col += 1
                if col >= self.DIM:
                    row, col = (row+1, 0)
        self.curr = (row, col)

    def __str__(self):
        """
        Return a string representation of the config.
        """

        # top row
        result = '  '
        for val in self.lookNS:
            result += str(val) + ' '
        result += '\n  ' + '-' * (self.DIM*2-1) + '\n'

        # board rows
        for row in range(self.DIM):
            result += str(self.lookWE[row]) + '|'
            for col in range(self.DIM):
                if self.board[row][col] == SkyscraperConfig.EMPTY:
                    result += '.'
                else:
                    result += str(str(self.board[row][col]))
                if col != self.DIM-1: result += ' '
            result += '|' + str(self.lookEW[row]) + '\n'

        # bottom row
        result += '  ' + '-' * (self.DIM*2-1) + '\n'
        result += '  '
        for val in self.lookSN:
            result += str(val) + ' '
        result += '\n'

        return result

#END OF DO NOT CHANGE SECTION


def readBoard(filename):
    """
    Read the board file.  It is organized as follows:
        DIM     # square DIMension of board (1-9)
        lookNS   # DIM values (1-DIM) left to right
        lookEW   # DIM values (1-DIM) top to bottom
        lookSN   # DIM values (1-DIM) left to right
        lookWE   # DIM values (1-DIM) top to bottom
        row 1 values    # 0 for empty, (1-DIM) otherwise
        row 2 values    # 0 for empty, (1-DIM) otherwise
        ...

        filename: The file name (string)
    Returns: A config (SkyscraperConfig) containing the board info from file
    """
    f = open(filename)
    DIM = int(f.readline().strip())
    lookNS = [int(val) for val in f.readline().split()]
    lookEW = [int(val) for val in f.readline().split()]
    lookSN = [int(val) for val in f.readline().split()]
    lookWE = [int(val) for val in f.readline().split()]
    board = list()
    for _ in range(DIM):
        line = [int(val) for val in f.readline().split()]
        board.append(line)
    f.close()
    return SkyscraperConfig(DIM, lookNS, lookEW, lookSN, lookWE, board)

def isGoal(config):
    """
    Checks whether a config is a goal or not
        config: The config (SkyscraperConfig)
    Returns: True if config is a goal, False otherwise
    """

    return config.curr >= (config.DIM, 0)

def getSuccessors(config):
    """
    Get the successors of config
        config: The config (SkyscraperConfig)
    Returns: A list of successor configs (list of SkyscraperConfig)
    """

    successors = []
    # if there is a number in the cell, generate only one successor
    # config which skips the occupied cell value
    if config.board[config.curr[0]][config.curr[1]] != SkyscraperConfig.EMPTY:
        successor = deepcopy(config)
        successor.prev = successor.curr
        row, col = successor.curr
        col += 1
        if col == config.DIM:
            row, col = (row+1, 0)
        successor.curr = (row, col)
        successors.append(successor)
    # otherwise generate all digits in new cell as successors
    else:
        for val in range(1, config.DIM+1):
            successor = deepcopy(config)
            row, col = successor.curr
            successor.board[row][col] = val
            # move curr to next cell
            successor.prev = successor.curr
            col += 1
            if col == config.DIM:
                row, col = (row+1, 0)
            successor.curr = (row, col)
            successors.append(successor)
    return successors

def isValid(config):
    """
    Checks the config to see if it is valid
        config: The config (SkyscraperConfig)
    Returns: True if the config is valid, False otherwise
    """

    row, col = config.prev

    # check row and column for duplicates
    legalVals = {val for val in range(1,config.DIM+1)}
    for r in range(config.DIM):
        if config.board[r][col] == SkyscraperConfig.EMPTY: continue
        elif config.board[r][col] not in legalVals: return False
        else: legalVals.discard(config.board[r][col]) #Discard

    legalVals = {val for val in range(1,config.DIM+1)}
    for c in range(config.DIM):
        if config.board[row][c] == SkyscraperConfig.EMPTY: continue
        elif config.board[row][c] not in legalVals: return False
        else: legalVals.discard(config.board[row][c])

    # check N
    canSee, lastSaw = 0, SkyscraperConfig.EMPTY
    for r in range(row+1):
        if config.board[r][col] >= lastSaw:
            canSee, lastSaw = canSee+1, config.board[r][col]
    if canSee > config.lookNS[col] or row == config.DIM-1 and canSee != config.lookNS[col]:
            return False

    # check S
    if row == config.DIM-1:
        canSee, lastSaw = 0, SkyscraperConfig.EMPTY
        for r in range(row,-1,-1):
            if config.board[r][col] >= lastSaw:
                canSee, lastSaw = canSee+1, config.board[r][col]
        if canSee != config.lookSN[col]:
            return False

    # check W
    canSee, lastSaw = 0, SkyscraperConfig.EMPTY
    for c in range(col+1):
        if config.board[row][c] >= lastSaw:
            canSee, lastSaw = canSee+1, config.board[row][c]
    if canSee > config.lookWE[row] or col == config.DIM-1 and canSee != \
        config.lookWE[row]:
            return False

    # check E
    if col == config.DIM-1:
        canSee, lastSaw = 0, SkyscraperConfig.EMPTY
        for c in range(col,-1,-1):
            if config.board[row][c] >= lastSaw:
                canSee, lastSaw = canSee+1, config.board[row][c]
        if canSee != config.lookEW[row]:
            return False

    return True

def solve(config, debug):
    """
    Generic backtracking solver.
        config: the current config (SkyscraperConfig)
        debug: print debug output? (Bool)
    Returns:  A config (SkyscraperConfig), if valid, None otherwise
    """

    if isGoal(config):
        return config
    else:
        if debug: print('Current:\n' + str(config))
        for successor in getSuccessors(config):
            if isValid(successor):
                if debug: print('Valid Successor:\n' + str(successor))
                solution = solve(successor, debug)
                if solution != None:
                    return solution

def main():
    """
    The main program.
        Usage: python3 skyscraper.py [filename] [debug]
            [filename]: The name of the board file
            [debug]: True or False for debug output
    """

    # if no command line arguments specified, prompt for the filename
    # and set debug output to False
    if len(argv) == 1:
        filename = input('Enter board file: ')
        debug = eval(input("Debug output (True or False): "))
    # otherwise, use the command line arguments
    elif len(argv) == 3:
        filename = argv[1]
        debug = eval(argv[2])
    # incorrect number of command line arguments
    else:
        print("Usage: python3 skyscraper.py [filename] [debug]")
        print("  [filename]: The name of the board file")
        print("  [debug]: True or False for debug output")
        return -1

    # read and display the initial board
    initConfig = readBoard(filename)
    print('Initial Config:\n' + str(initConfig))

    # solve the puzzle
    solution = solve(initConfig, debug)

    # display the solution, if one exists
    if solution != None:
        print('Solution:\n' + str(solution))
    else:
        print('No solution.')

if __name__ == '__main__':
    main()