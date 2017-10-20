#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>

#define FALSE (0)
#define TRUE  (1)

int main() {
	int tot_chars = 0 ;	
	int tot_lines = 0 ;	
	int tot_words = 0 ;	
	int character;
	int prev_character;
	while((character=getchar())!=EOF){

		tot_chars++;
		
		if((isspace(character)) && !isspace(prev_character)) {
                        tot_words++;
                }

		if (character=='\n'){
			tot_lines++;
                }
		
		prev_character=character;
	}

	printf("%3d%4d%4d\n",tot_lines,tot_words,tot_chars);

	return 0 ;
}

