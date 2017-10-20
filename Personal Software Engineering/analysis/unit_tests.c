// unit_tests.c
// Larry L. Kiser Oct. 22, 2015

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <unistd.h>

#include "analysis.h"
#include "unit_tests.h"

// Simple boolean assert function for unit testing
// DO NOT MODIFY THIS FUNCTION
int assert( int test_result, char error_format[], ... ) {
	va_list arguments ;
	static int test_number = 1 ;
	int result = 1 ;	// return 1 for test passed or 0 if failed
	
	if ( ! test_result ) {
		va_start( arguments, error_format ) ;
		printf( "Test # %d failed: ", test_number ) ;
		vprintf( error_format, arguments ) ;
		printf( "\n" ) ;
		result = 0 ;
	}
	test_number++ ;
	return result ;
}

//////////////////////////////////////////////////////////////////////////
// Begin unit tests //////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

// Write your unit tests below. I have provided some simple tests to get started.
// Add, rearrange, delete, modify as you see fit to get a good set of tests for
// both boundry conditions and normal operation.
int test()
{
	int passcount = 0 ;
	int failcount = 0 ;
	int result = 0 ;		// general purpose work variable for functions that return integers
	struct word_entry one_entry ;	// general purpose return struct for unit tests
	
	// Test 1 Verify that read_file handles a NULL file name pointer.
	result = read_file( NULL ) ;
	assert( result == 0,
		"A bad or NULL file name must return 0. Got %d", result )
		? passcount++ : failcount++ ;
		
	// Test 2 read a real file
	result = read_file( "ActivityJournal.txt" ) ;
	assert( result == 1,
		"Failure reading ActivityJournal.txt. Got result code %d", result )
		? passcount++ : failcount++ ;
	
	// Test 3 free the list and confirm that the list is now empty
	free_list() ;
	one_entry = get_first_word() ;
	assert( one_entry.word_count == 0,
		"Expected the get 0 word_count since the list is now empty. Got word_count %d", one_entry.word_count )
		? passcount++ : failcount++ ;
	
	printf( "\nSummary of unit tests:\n%d tests passed\n%d tests failed\n\n", passcount, failcount ) ;
	
	return failcount ;
}