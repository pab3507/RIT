// unit_tests.c
// Larry L. Kiser Oct. 22, 2015
// Copyright 2015 All Rights Reserved

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <unistd.h>

#include "linked.h"
#include "unit_tests.h"

// used by stdout switching functions
static int stdout_file_descriptor = 0 ;
static fpos_t stdout_position ;

// Redirect printf's to the specified text file (redirect stdout).
static void switch_stdout_to_file( char *filename )
{
	fflush( stdout ) ;
	fgetpos( stdout, &stdout_position ) ;
	stdout_file_descriptor = dup( fileno( stdout ) ) ;
	freopen( filename, "w", stdout ) ;
}

// Restore normal printf behavior (restore stdout).
// MUST be called only after a prior call to switch_stdout_to_file!
static void restore_stdout( void )
{
	if ( stdout_file_descriptor )
	{
		fflush( stdout ) ;
		dup2( stdout_file_descriptor, fileno( stdout ) ) ;
		close( stdout_file_descriptor ) ;
		clearerr( stdout ) ;
		fsetpos( stdout, &stdout_position ) ;
	}
}

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
	
	// bogus code that does nothing except avoid compiler warning on these two unused functions.
	#pragma GCC diagnostic ignored "-Waddress"
	if ( switch_stdout_to_file && restore_stdout ) ;
	#pragma GCC diagnostic warning "-Waddress"
	
	// Test 1 -- check that list length is 0
	assert( length() == 0,
		"Length of list must initially be zero" )
		? passcount++ : failcount++ ;
	
	
	printf( "\nSummary of unit tests:\n%d tests passed\n%d tests failed\n\n", passcount, failcount ) ;
	
	return failcount ;
}