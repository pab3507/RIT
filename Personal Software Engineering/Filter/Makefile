HEADERS = filter.h test_filter.h 

default: test

test: filter.o test_filter.o
	gcc -g -o test filter.o test_filter.o
	
filter.o: filter.c $(HEADERS)
	gcc -Wall -g -c filter.c
	
test_filter.o: test_filter.c $(HEADERS)
	gcc -g -c test_filter.c

clean:
	-rm -f filter.o
	-rm -f test_filter.o
	-rm -f test