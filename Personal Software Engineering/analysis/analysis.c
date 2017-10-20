// Document Analysis project functions

#include <stdlib.h>
#include <stdio.h>

#include <string.h>
#include "analysis.h"
#include "unit_tests.h"

// Define your linked list here. It should be prefixed with static so it is only accessible
// from inside this module.
// Also, you will need to maintain the current location in the doubly linked list to support the next and previous functions.
struct node *p_head = NULL;

struct node *cursor; //cursor that tracks current position
//node structure for doubly linked list
	struct node
	{
		struct word_entry entry;
		struct node *p_next;
		struct node *p_prev;
	}

// returns 0 if the file cannot be read or if the file does not contain any words as defined for this project.
// returns 0 if the pointer to the file name is NULL.
// returns 1 if the file wsas found, opened successfully, and successfully read into the doubly linked list.
int read_file( char *file_name )
{
	//if statement returns 0 if file does not contain any words.
	if(file_name[0] == "\0")
		return 0;

	FILE* f = fopen(file_name);
	if(f == NULL)
		return 0;
	char word_buffer[50];
	while(fscanf(f, "%s",word_buffer)>1){
		addNode(word_buffer);
	}


	// fill this in. You should create a doubly linked list and add entries to that list in this function.
	// This will be a significant effort. You should create your own functions to implement parts of this logic.
	// For the bonus you will need to add some significant logic to this function. DO NOT try the bonus until all else works.
	return 1 ;		// temporary code so it compiles and runs.
}
/*
void add(char word_buffer[]){
	int i = 0;
	int ch = 0;
	while((ch==getchar())!=EOF){
		if((ch >='A' && ch<= 'Z') || (ch >= 'a' && ch<= 'z')){
			word_buffer[i++] = ch;}
		else{
			word_buffer[i] = "\0";
			i = 0;
			printf("%s", word_buffer);
			addNode(word_buffer);
	}


}
*/

void addNode(char word_buffer[]){
	//a temporary node is created and assigned the value of the head so it doesn't change.
	struct node* current = p_head;
	//traversing through the doubly linked list.
	while(current != NULL) {
		//if increments word count if unique word equals the word buffer
		if(strcmp(current->unique_word, word_buffer) == 0) {
			current->word_count++;
			return;
		} else if(current->p_next == NULL)
			break;
		current = current->p_next;
	}
	//wordbuffer is added to the linked list node structure.
	struct node* newNode;
	newNode = (struct node*) malloc(sizeof(struct node));
	newNode->entry.word_count = 1;
	strcpy(current->entry.unique_word, word_buffer);
	newNode->p_prev = current;
	newNode->p_next = NULL;

	if(p_head == NULL) {
		p_head = newNode;
		cursor = p_head;
	}

}

// This walks through the linked list and frees every node in the linked list.
// After freeing all nodes in the list be sure to set your head and tail
// pointers to NULL to indicate that the list is now empty.
void free_list()
{
	struct node *temp; //temporary node
	//traversing through the linked list.
	while(p_head!=NULL){
		temp = p_head;
		p_head = p_head->p_next; //keeps the loop traversing.
		free(temp);//frees the current node
	}
}

// Returns 0 in the word_count field if no first word (empty list).
// Otherwise, returns a struct with the first unique word and its number of occurrences in the text file.
struct word_entry get_first_word()
{
	struct node* curr = p_head;
	if(curr == NULL) { //checks if the head is not null
		struct word_entry empty_entry; //struct created for an empty entry
		empty_entry.word_count = 0; //initializing to 0
 		return empty_entry; //returns struct
	} else
		return curr->entry; //returns struct with number of occurrences and the unique word.
}

// Returns 0 in the word_count field if no next word (previously reached end of list or it is an empty list).
// Otherwise, returns a struct with the next unique word and its number of occurrences in the text file.
struct word_entry get_next_word()
{
	struct word_entry empty;
	empty.word_count = 0;
	if(cursor == NULL) {
		return empty;
	} else {
		cursor = cursor->p_next;
		if(cursor == NULL)
			return empty;
		return cursor->entry;
	}
}

// Returns 0 in the word_count field if no previous word (was already at beginning of the list or it is an empty list).
// Otherwise, returns a struct with the previous unique word and its number of occurrences in the text file.
struct word_entry get_prev_word()
{
	struct word_entry empty; //creating struct
        empty.word_count = 0; //initializing to 0
        if(cursor == NULL) { //struct returned if the cursor is null.
                return empty;
        } else {
                cursor = cursor->p_prev; //goes to the previous node
                if(cursor == NULL)
                        return empty; //returns struct
                return cursor->entry; //returns struct with number of occurrences and unique word.
        }
}

// Returns 0 in the word_count field if it is an empty list).
// Otherwise, returns a struct with the last unique word and its number of occurrences in the text file.
struct word_entry get_last_word()
{
	struct word_entry empty; //creating struct
        empty.word_count = 0; //initializing word count to 0
        if(cursor == NULL) { 
                return empty; //returns struct if cursor is
        } else {
                while(cursor->p_next!=NULL){
			cursor = cursor->p_next;
		}
	return cursor->entry;
        }
}

// Returns 0 if no sentences include an empty list and if there are no periods in the file.
// Otherwise returns number of sentences.
int get_sentence_count(char word_buffer[])
{
	int sentence_iterator = 0;
	int i = 0;
	for(i;word_buffer[i]!='\0';i++){
		if (word_buffer[i] == '.'){
			sentence_iterator++;
		}
			return sentence_iterator;
		else{
			return 0;
		}
}
	return 0;
}

// returns 0 if word not found in the list.
// Searches the list for the word and returns its number of occurrences.
int get_unique_word_count(char *word_to_find, struct node *newNode)
{
	int count = 0;
	while (cursor!= NULL){
	        if(strcmp(cursor->entry, word_to_find) == 0)
			count++;
		cursor = cursor->p_next;	
    }
        return count;
}

// Returns a NULL pointer if word_to_find is not found in the list.
// Searches the list for word_to_find and returns the word that most commonly occurs after it.
// If word_to_find is ALWAYS the last word in the sentence it returns a pointer to an empty string.
char *get_most_common_word_after_this_word( char *word_to_find )
{
	return (char *)NULL ;	// temporary code that indicates that the word is not found.
}

// Writes the sorted unique word list to a csv file.
// Receives one parameter which is the name of the file to be created.
// Returns 1 on a successful write of the file.
// Returns 0 on any failure.
// Test for a NULL or empty string in the file_name. Return 0 for failure if NULL or empty.
// Be sure to test for failure to open the file, failure to write to the file, and failure to close.
// You must have a header row exactly like this (without the quotes): "word,count"
// You must have one row for each unique word and its count (e.g. without quotes "student,5").
// It must be in sorted order and must be the complete list.
int write_unique_word_list_to_csv_file( char *file_name )
{
	return 0 ;		// temporary code so it compiles and links.
}
