// C (no pointers) Practicum
// SWEN-250
// Larry Kiser November 1, 2015

// cpracticum struct typedef
typedef struct
{
	char lowest_upper_case_letter ;	// This would be A if A is anywhere in the string.
							// If there are NO upper case letters (A through Z) then this must be a 0.
							// Yes, this is the same as the End of String marker ('\0').
	int upper_case_count ;	// The number of upper case letters in the string.
} mydata ;

// cpracticum functions
int string_is_reverse_sorted( char mystring[] ) ;
int fix_bad_code( int numbers[], int entry_count ) ;
mydata get_string_info( char mystring[] ) ;
