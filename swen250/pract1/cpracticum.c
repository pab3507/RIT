// C (no pointers) Practicum
// SWEN-250
// Larry Kiser November 1, 2015

#include <stdlib.h>
#include <stdio.h>
#include "cpracticum.h"
#include "unit_tests.h"

// Determine whether or not the passed string is in sorted descending order.
// For the purpose of this exam sort order is determined by simply
// comparing the numeric values of the characters in the string.
// You can assume that only printable characters are included in the string.
// If you have a string with a '9' (0x39), a ':' (0x40), and a 'A' (0x41)
// it must be "A:9" to be considered to be in sorted descending order.
// ALSO -- successive characters that are the same character are considered to
// be sorted (e.g. "AAAAA" is sorted).
//
// Hint -- create a loop that goes to the end of the string that
// compares adjacent characters. You can exit the loop as soon as you
// see one pair of characters out of order. Be sure to not compare the '\0'
// at the end of the string!
//
// Return 0 if any character is not in descending order.
// Return 1 if all characters in the entire string are in descending order.
//
// NOTE -- you must handle an empty string -- return 1 if empty.
// NOTE 2 -- a string with just one character is sorted -- return a 1.
//
// You are NOT allowed to use any library functions to do this.
// You must loop through the array and return the correct result.
int string_is_reverse_sorted( char mystring[] )
{
	int result ;
	int i = 0;

	for(i;i<sizeof(mystring); i++){
		if (mystring[i] > mystring[i+1]){ // check for descending order
			result = 0;}
		else if (mystring[i] < mystring[i+1]){ // check for ascending order
			result = 1;}
		else if (mystring[i] ==  '\0'){ //check for empty string
			result = 1;}
		else if (i == 1){ // check for only one character
			result = 0;}
	}
	return result ;
}

// This function is implemented incorrectly. You need to correct it.
// It is supposed to total up the integers in the array for the number of entries in the
// second parameter.
// It returns that sum.
int fix_bad_code( int numbers[], int entry_count )
{		
	int i = 0;
	int total = 0 ;
	int result = 0;
	
	for (i; i < entry_count ; i++ ) {  // changed initialization
		total += numbers[ i ] ;	 //adds total
		result += total; // adds total to result
}
	return result;
}



// Returns the mydata struct that is typedef'ed in cpracticum.h
// Find the lowest upper case letter in the passed string and put that the struct.
// that your return.
// Count the number of upper case letters in the passed string (A through Z) and put that in the struct.
// NOTE -- you must handle an empty string. If you have an empty string set
//         set the lowest_upper_case_letter to '\0' and the upper_case_count to 0.
// NOTE -- you cannot use any library functions in this code.
// For your convenience I have made a copy of that struct definition below:
//
// Hint -- you may want to initialize a char variable to a value higher than 'Z' to
// help with finding the highest letter.
//
//typedef struct
//{
//	char lowest_upper_case_letter ;	// This would be A if A is anywhere in the string.
//							// If there are NO upper case letters (A through Z) then this must be a 0 or '\0'.
//							// Yes, this is the same as the End of String marker ('\0').
//	int upper_case_count ;	// The number of upper case letters in the string.
//} mydata ;

mydata get_string_info( char mystring[] )
{
	mydata data ;
	int upperlimit = 123;
	int minimum;
	int i = 0;
	int j = 65;
        for (i; mystring[i] < upperlimit; i++){
		if (mystring[i] >= 65 && mystring[i] <= 90){ //ascii code for uppercase letter 65-90
			for (j; mystring[j]<90; j++){
				if (mystring[j] < minimum){ //accounting for minimum value.
					minimum = mystring[j];
					lowest_upper_case_letter = minimum;
					data.lowest_upper_case_letter;
					}
				}
		}
		if (mystring[i] >= 65 && mystring[i] <= 90){
			upper_case_count++;
			data.lowest_upper_case_count; //assigning values to struct
		}	
		if (mystring[i] == '\0'){
			lowest_upper_case_letter = '\0';
			upper_case_count = 0;
			data.lowest_upper_case_letter;
			data.upper_case_letter;
		}	
}
	return data ;
}

// Only runs the unit tests.
int main( int argc, char *argv[] ) {

	// Execute unit tests
	return test() ;
}
