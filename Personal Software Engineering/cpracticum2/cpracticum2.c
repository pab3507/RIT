// C pointers light Practice Practicum
// SWEN-250
// Larry Kiser October 30, 2015

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "cpracticum2.h"
#include "unit_tests.h"

// Determine if the p_unknown pointer points to one of the numbers in the array of doubles.
// This is about comparing the addresses contained in two pointers. It is not about comparing the value pointed to
// by those two pointers.
// So -- be sure to compare the actual pointers for equality.
// You need to use a loop that advances a pointer through the array. In that loop you
// also need to check for a match with the last_double.
// If you reach the last_double without seeing a match on the pointer addresses then return 0.
int is_pointer_in_array( double *p_double_array, double *p_unknown, double last_double )
{
	/* I changed my implementation to use the actual pointers and not assigning them to variables,
	but otherwise I had similar logic going on. */
	while(*p_double_array != last_double){  //looking at pointers now
		if(p_double_array==p_unknown) //comparing the current position with the unknown.
        		return 1;
       	
		p_double_array++; //looping through the array
    }
    	return 0;
}

// The first time this is called return the first double in the mylist array.
// The second time this is called return the second double in the mylist array.
// On subsequent calls return the next entry in mylist.
// HOWEVER, after you return the last item in the array the next call must return
// the first element in the array.
double my_random()
{
	double mylist[] = { 0.92, 0.33, 0.98, 0.45, 0.07, 0.51, 0.72 } ; // DO NOT change this array!
	static double random = 0; //static variable accounting for the random
	int size=  (int)( sizeof(mylist) / sizeof(mylist[0]) ); //returns the size of the myList
	static int i = 0; // index
  	if(random != mylist[size -1]){ //keeps looking through the list
		random = mylist[i]; //assigns the current position of the list to the random variable
		i++;
	}else{
		i=0; //resetting index
		random = mylist[i]; //assigns the current position of the list to the random variable
	}
	return random;
}

//The first parameter points to an array of integers.
// The second parameter points to second array of integers.
// Multiply the first number in the first array by the first number in the second array and add this to the sum value.
// Next multiply the second number in the first array by the second number in the second array and add this to the sum value.
// Repeat for all numbers in these arrays.
// Return the sum value.
// The third parameter is the number of entries in the array.
// You can assume that both arrays have the same number of entries and that this parameter is a valid value if it is > 0.
// Return 0 if either pointer is NULL.
// Return 0 if the number of entries is <= 0.
int sum_of_array_multiplication( int *pfirst, int *psecond, int number_of_entries )
{
        int i = 0;//index
	int result = 0;
	if(pfirst ==NULL  || psecond ==NULL  || number_of_entries<=0){//now looking at the actual arrays not their pointers.
		return result;
	}
        while (i < number_of_entries){
		result +=  pfirst[i] * psecond[i]; //simplified this.
		//Move to next chracter of the string
        	i++; //incrementing the index
        }
	return result;
}

// This function is implemented incorrectly. You need to correct it.
// When fixed it returns 1 if there are any lower case letters ('a' through 'z') anywhere in the passed string.
// If there are no lower case letters it returns 0.
// It also returns 0 if the passed string pointer is NULL or if the string points to an empty string.
// You can re-write this code completely if you prefer.
int fix_bad_code( char *pstring )
{
	int result = 0 ;	// is this a good choice for initialization?
	
	// Fix this next line so it correctly returns 0 if the passed pointer is NULL or
	// if the pointer points to an empty string.
	if ( ( pstring == NULL ) || *pstring == '\n'  ) //now looking if the array is the Null not the Null Character.
		return result ;
	
	// does this loop work correctly?
	while ( *pstring != '\0') //checking if the pointer is null
		if ( islower( *pstring++) )	// islower returns true if the passed character is a lower case letter
			result = 1 ;
  return result ;
}
