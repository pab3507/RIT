/*
 * Implementation of the read_data module.
 *
 * See read_data.h for a description of the read_data function's
 * specification.
 *
 * Note that the parameter declarations in this module must be
 * compatible with those in the read_data.h header file.
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "read_data.h"

void read_data(char *C, int *I, double *D) {
	
	char buffer[26];	// 25 characters for number + 1 null terminator
	*C = getchar();		// Get the first character
	getchar();		// Skip the first dollar sign

	int j;
	for(j = 0; j < 2; j++){	// We are getting 2 numbers
		int i;
		for(i = 0; i < sizeof(buffer)-1; i++) {
			char current = getchar();
			if(current == '$')
				break;
			else
				buffer[i] = current;
		}
		buffer[i] = '\0';
		if(j == 0)
			*I = atoi(buffer);
		else
			*D = atof(buffer);
	}
/*
	int j1,j2,j3; 
	for(j1;input!='$';j1++){
		char input = getchar();
		C += input;}
		for(j2;input!='$';j2++){
                I += input;
			for(j3;input!='$';j3++){
				D += input;}
		
}
**/
	//printf("%p\n",	 (void *) &C);
	return ;
}

