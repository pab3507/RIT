HEADERS = analysis.h unit_tests.h

default: test

test: main.o analysis.o unit_tests.o
	gcc -g -o test main.o analysis.o unit_tests.o
	
analysis.o: analysis.c $(HEADERS)
	gcc -Wall -g -c analysis.c

main.o: main.c $(HEADERS)
	gcc -g -c main.c
	
unit_tests.o: unit_tests.c $(HEADERS)
	gcc -g -c unit_tests.c

clean:
	-rm -f analysis.o
	-rm -f main.o
	-rm -f unit_tests.o
	-rm -f test
	
