// unit_tests.c
// Larry Kiser Oct. 25, 2016

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <unistd.h>

#include "cpracticum2.h"
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
	double first_doubles[] = { 1.1, 2.2, 3.9, 0.5, 0.2, 0.0 } ;
	double second_doubles[] = { 5.2, 3.3, 10.7, 0.8,0.0 } ;
	int first_array[] = { 1, 2, 2 } ;
	int second_array[] = { 8, 9, 10 } ;
	int test1[] = { 55, 66, 88, 2 } ;
	int test2[] = { 44, 77, 11, 17 } ;
	
	int result = 0 ;
	double answer = 0.0 ;
	
	printf( "\nTests for is_pointer_in_array.......................\n" ) ;
	// Test 1
	result = is_pointer_in_array( first_doubles, first_doubles, 0.0 ) ;
	assert( result == 1,
		"Points to the first position in the array so expected a 1 but got a %d",  result )
		? passcount++ : failcount++ ;
	// Test 2
	result = is_pointer_in_array( first_doubles, &first_doubles[4], 0.0 ) ;
	assert( result == 1,
		"Points to the last position in the array (before the 0.0) so expected a 1 but got a %d",  result )
		? passcount++ : failcount++ ;
	// Test 3
	result = is_pointer_in_array( first_doubles, &second_doubles[2], 0.0 ) ;
	assert( result == 0,
		"Points to a position in a different array so expected a 0 but got a %d",  result )
		? passcount++ : failcount++ ;

	printf( "\nTests for my_random()...............................\n" ) ;
	// Test 4
	assert( ( answer = my_random() ) == 0.92,
		"First random number must be 0.92 but got a %f",  answer )
		? passcount++ : failcount++ ;
	// Test 5
	assert( ( answer = my_random() ) == 0.33,
		"Second random number must be 0.33 but got a %f",  answer )
		? passcount++ : failcount++ ;
	// Test 6
	my_random() ;	// skip next four numbers
	my_random() ;
	my_random() ;
	my_random() ;
	assert( ( answer = my_random() ) == 0.72,
		"Last random number must be 0.72 but got a %f",  answer )
		? passcount++ : failcount++ ;
	// Test 7
	assert( ( answer = my_random() ) == 0.92,
		"Must now be back at the first random number of 0.92 but got a %f",  answer )
		? passcount++ : failcount++ ;

	printf( "\nTests for sum_of_array_multiplication..........................\n" ) ;
	// Test 8
	result = sum_of_array_multiplication( first_array, second_array, sizeof( first_array ) / sizeof( int ) ) ;
	assert( result == 46,
		"Sum of the updated first array should be 46 but got %d",  result )
		? passcount++ : failcount++ ;
	// Test 9
	result = sum_of_array_multiplication( first_array, second_array, 0 ) ;
	assert( result == 0,
		"Expected 0 because number of entries is 0 but got %d",  result )
		? passcount++ : failcount++ ;
	// Test 10
	result = sum_of_array_multiplication( first_array, NULL, sizeof( first_array ) / sizeof( int ) ) ;
	assert( result == 0,
		"Expected 0 because second array is a null pointer but got %d",  result )
		? passcount++ : failcount++ ;	
	// Test 11
	result = sum_of_array_multiplication( NULL, second_array, sizeof( first_array ) / sizeof( int ) ) ;
	assert( result == 0,
		"Expected 0 because first array is a null pointer but got %d",  result )
		? passcount++ : failcount++ ;
	// Test 12
	result = sum_of_array_multiplication( test1, test2, sizeof( test1 ) / sizeof( int ) ) ;
	assert( result == 8504,
		"Sum of the updated first array should be 8504 but got %d",  result )
		? passcount++ : failcount++ ;
		
	printf( "\nTests for fix_bad_code..............................\n" ) ;
	// Test 13
	assert( ( result = fix_bad_code( "1234" ) ) == 0,
		"Expected 0 because there are no lower case letters but got %d",  result )
		? passcount++ : failcount++ ;
	// Test 14
	assert( ( result = fix_bad_code( "9876a" ) ) == 1,
		"Expected 1 because there is one lower case letter but got %d",  result )
		? passcount++ : failcount++ ;
	// Test 15
	assert( ( result = fix_bad_code( "" ) ) == 0,
		"Expected 0 because it is an empty string but got %d",  result )
		? passcount++ : failcount++ ;
	// Test 16
	assert( ( result = fix_bad_code( NULL ) ) == 0,
		"Expected 0 because the string pointer is a null pointer but got %d",  result )
		? passcount++ : failcount++ ;
		
	printf( "\nSummary of unit tests:\n%d tests passed\n%d tests failed\n\n", passcount, failcount ) ;
	
	return failcount ;
}
