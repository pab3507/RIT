
def main():
    dict = {}
    for line in open("woodchuck.txt"):
        lst = line.strip().split()
        for word in lst:
            if word in dict:
                dict[word] += 1
            else:
                dict[word] = 1

    sortlist = list(dict.keys())
    sortlist.sort()

    for word in sortlist:
        print( word, dict[word])


if __name__ == "__main__":
    main()