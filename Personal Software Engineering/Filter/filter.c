/*
 * Implementation of functions that filter values in strings.
 *****
 * YOU MAY NOT USE ANY FUNCTIONS FROM <string.h> OTHER THAN
 * strcpy() and strlen()
 *****
 */

#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#include "filter.h"

#define NUL ('\0')

/*
 * YOU MAY FIND THIS FUNCTION USEFUL.
 * Return true if and only if character <c> is in string <s>.
 */
static bool includes(char c, char *s) {
	while( *s && c != *s ) {
		s++ ;
	}
	return c == *s ;
}

/*
 * YOU MAY ALSO FIND THIS FUNCTION USEFUL.
 * Return true if and only if string <pre> is a prefix of string <s>.
 */
static bool prefix(char *s, char *pre) {
	while( *pre && *s == *pre ) {
		s++ ;
		pre++ ;
	}
	return *pre == NUL ;
}

/*
 * Copy <string> to <result> while removing all occurrences of <ch>.
 */
void filter_ch_index(char string[], char result[], char ch) {
	int i = 0;//string iterator
	int j = 0;//result iterator
	while(string[i] != NUL){
		if(string[i] != ch){
			result[j] = string[i];
			j++;
		}
		i++;
	}
	result[j] = NUL ;
}

/*
 * Return a pointer to a string that is a copy of <string> with
 * all occurrences of <ch> removed. The returned string must occupy
 * the least possible dynamically allocated space.
 *****
 * YOU MAY *NOT* USE INTEGERS OR ARRAY INDEXING.
 *****
 */
char *filter_ch_ptr(char *string, char ch) {
	char *p_copy;
	p_copy = malloc(sizeof(*string)+1); //+1 accounting for null character
	char *p = p_copy;
	while(*string != NUL){
		//Only non-ch characters are copied.
		if(*string != ch){ 
			*p = *string;
			p++;                       
                }
		string++;
		
	}
	
	*p = NUL;
	p = malloc(sizeof(*p_copy)); //New memory gets allocated		
	strcpy(p, p_copy) ; // copy 
	free(p_copy); // freeing memory
	return p;
}

/*
 * Copy <string> to <result> while removing all occurrences of
 * any characters in <remove>.
 */
void filter_any_index(char string[], char result[], char remove[]) {
		int i = 0;//string iterator
        int j = 0;//result iterator
        while(string[i] != '\0'){
		int c = 0; //count
		int copy = 0;
		int end = 1; 
            while(remove[c] != '\0' && end == 1){
				if(string[i] == remove[c]){
					copy = 1;
					end = 0;
				}
				c++;
            }
		//If the  character of string was not found in remove we continue to save 
		if(copy == 0){
            result[j] = string[i];
            j++;
		}
            i++;
        }
        result[j] = NUL ; 
}

/*
 * Return a pointer to a copy of <string> after removing all
 * occurrrences of any characters in <remove>.
 * The returned string must occupy the least possible dynamically
 * allocated space.
 *****
 * YOU MAY *NOT* USE INTEGERS OR ARRAY INDEXING.
 *****
 */
char *filter_any_ptr(char *string, char* remove) {
	
        char *p_copy;
        p_copy = malloc(sizeof(*string)+1); //+1 accounting for null character
        char *p = p_copy;
        while(*string != NUL){
		//Iterating trought remove and just copying if the caracter of string was not found in remove.
		int copy = 0;//1 for false, 0 for true
		int end = 1;//1 for false, 0 for true
		char *copy_remove = remove;	//loops through remove and copies if the character wasn't found in the remove.
		while(*copy_remove != NUL && end == 1){
			if(*string == *copy_remove){
				copy = 1;
				end = 0;
			}
			copy_remove++;
		}
                if(copy == 0){
                        *p = *string;
                        p++;
                }
                string++;
        }
		
        *p = NUL;
        p = malloc(sizeof(*p_copy));//memory reallocation
        strcpy(p, p_copy) ;//copying string
        free(p_copy);//freeing memory
        return p;

}

/*
 * Return a pointer to a copy of <string> after removing all
 * occurrrences of the substring <substr>.
 * The returned string must occupy the least possible dynamically
 * allocated space.
 *****
 * YOU MAY *NOT* USE ARRAY INDEXING.
 *****
 */
char *filter_substr(char *string, char* substr) {
	
        char *p_copy;
        p_copy = malloc(strlen(string)+1);
        char *p = p_copy;
        while(*string != NUL){
		if(prefix(string,substr)){
			int c = 0;//Counter
			//looping while the conter is less than the length of the substring.
			while(c < strlen(substr)){
				c++;
				string++;
			}
			
		}else{
			//Since in this case it's not a prefix, we copy the character
			*p = *string;
            p++;
			string++;
			}
        }
        *p = NUL;
        p = malloc(strlen(p_copy)+1);//memory reallocation
        //Copy string and free space
        strcpy(p, p_copy) ;//copying string
        free(p_copy);//freeing memory
        return p;
}
