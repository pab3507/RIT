// C pointers light Practice Practicum
// SWEN-250
// Larry Kiser October 30, 2015

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "cpractice2.h"
#include "unit_tests.h"

// return a pointer to the character that is at the passed position.
// return a null if the requested position is past the end of the string.
char *get_pointer_at_position( char *pstring, int position )
{
	// Your code here
	return pstring ;	// Fix this -- it is incorrect but allows the program to compile and run.
}

// Convert array of integer x values into an array of y values using y = mx + b where m and b are constants
// passed to the function. The x values will be replaced by the y values (the x values are overwritten).
// The constants can be positive, negative or zero.
// Return the sum of the y values.
// Return 0 if the passed in px pointer is NULL.
int convert_and_sum( int *px, int m, int b, int number_of_x_values )
{
	// your code here
	return 0 ;		// Fix this -- it is incorrect but allows the program to compile and run.
}

// Determine if two pointers point to the same array of numbers
// return 1 if they do, return 0 otherwise.
// return 0 if either pointer is NULL.
int same_array( int *pfirst, int *psecond )
{
	// your code here
	return 0 ;	// Fix this -- it is incorrect but allows the program to compile and run.
}

// The first time this is called return 1.
// The second time this is called return 0.
// Continue this pattern returning 1, then 0, then 1 and so on.
int bool_flip_flop()
{
	// your code here
	return 0 ;	// Fix this -- it is incorrect but allows the program to compile and run.
}

// This function is implemented incorrectly. You need to correct it.
// When fixed it changes the last character in the passed string to 'Z'.
// It returns returns 1 on success.
// It returns 0 on if the passed string is NULL or an empty string.
int fix_bad_code( char *pstring )
{
	if ( *pstring || ! pstring )
		return 0 ; 
	while ( *pstring = '\0' )
		;
	pstring = 'Z' ;
	return 1 ;
}
