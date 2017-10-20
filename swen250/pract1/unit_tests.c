// unit_tests.c
// Larry Kiser November 1, 2015

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <unistd.h>

#include "cpracticum.h"
#include "unit_tests.h"


// Simple boolean assert function for unit testing
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

// do the unit tests
int test()
{
	int passcount = 0 ;
	int failcount = 0 ;
	int result ;
	int numbers1[] = { 5, 10, 15, 20, 25 } ;
	mydata data ;
	
	// Test 1 -- 
	assert( ( result = string_is_reverse_sorted( "" ) ) == 1,
		"Empty string must report 1 for reverse sorted. Got %d", result )
		? passcount++ : failcount++ ;
		
	// Test 2 -- 
	assert( ( result = string_is_reverse_sorted( "x" ) ) == 1,
		"Single character string must report 1 for reverse sorted. Got %d", result )
		? passcount++ : failcount++ ;
		
	// Test 3 -- 
	assert( ( result = string_is_reverse_sorted( "fedca" ) ) == 1,
		"Expected reverse sorted for string fedca. Got %d", result )
		? passcount++ : failcount++ ;
		
	// Test 4 -- 
	assert( ( result = string_is_reverse_sorted( "mhgfee" ) ) == 1,
		"Expected reverse sorted for string mhgfee. Got %d", result )
		? passcount++ : failcount++ ;
		
	// Test 5 -- 
	assert( ( result = string_is_reverse_sorted( "mhgfeef" ) ) == 0,
		"Expected unsorted for string mhgfeef. Got %d", result )
		? passcount++ : failcount++ ;
		
	// Test 6 -- 
	assert( ( result = string_is_reverse_sorted( "cbc" ) ) == 0,
		"Expected unsorted for string cbc. Got %d", result )
		? passcount++ : failcount++ ;
		
	// Test 7 -- 
	assert( ( result = fix_bad_code( numbers1, 2 ) ) == 15,
		"Expected 15 for sum of first two. Got %d", result )
		? passcount++ : failcount++ ;
		
	// Test 8 -- 
	assert( ( result = fix_bad_code( numbers1, 5 ) ) == 75,
		"Expected 75 for sum of first five. Got %d", result )
		? passcount++ : failcount++ ;
		
	// Test 9 -- 
	data = get_string_info( "" ) ;
	assert( data.lowest_upper_case_letter == '\0',
		"Expected 0 for lowest upper case letter for an empty string but got 0x%x", (int)data.lowest_upper_case_letter )
		? passcount++ : failcount++ ;
		
	// Test 10 -- 
	assert( data.upper_case_count == 0,
		"Expected 0 for upper case count for an empty string but got %d", data.upper_case_count )
		? passcount++ : failcount++ ;
	
	// Test 11 --
	data = get_string_info( "ZXY55T" ) ;
	assert( data.lowest_upper_case_letter == 'T',
		"Expected T for lowest upper case letter but got %c", data.lowest_upper_case_letter )
		? passcount++ : failcount++ ;
		
	// Test 12 --
	assert( data.upper_case_count == 4,
		"Expected 4 for upper case count but got %d", data.upper_case_count )
		? passcount++ : failcount++ ;
		
	// Test 13 --
	data = get_string_info( "AABB----" ) ;
	assert( data.lowest_upper_case_letter == 'A',
		"Expected A for lowest upper case letter but got %c", data.lowest_upper_case_letter )
		? passcount++ : failcount++ ;
		
	// Test 14 --
	assert( data.upper_case_count == 4,
		"Expected 4 for upper case count but got %d", data.upper_case_count )
		? passcount++ : failcount++ ;
		
	printf( "\nSummary of unit tests:\n%d tests passed\n%d tests failed\n\n", passcount, failcount ) ;
	
	return failcount ;
}