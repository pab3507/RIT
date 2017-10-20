
/* linked - linked list functions
*/

#include <stdio.h>
#include <stdlib.h>
#include "linked.h"
	
static struct node* head = NULL;

/************************************************************
 length - return length of a list
 ************************************************************/
int length()
{
	struct node* temp = head;
	int length = 0;
	while(temp!= NULL){
		length++;
		temp = temp->next;
	}
	return length;
}


/************************************************************
 push - add new node at beginning of list
 ************************************************************/
void push(int data)
{
  struct node *temp = malloc(sizeof(struct node)); //creating node structure
	if(length()==0){ //checking for length
		temp->next=NULL;
	}else{
		temp->next=head;

	}
	temp->data=data; //pushing node
	head=temp;
}

/************************************************************
 pop - delete node at beginning of non-empty list and return its data
 ************************************************************/
int pop()
{
  int data;
	if(length()>0){ //checking for length
		data=head->data;
		struct node *temp = malloc(sizeof(head->next));  //creating node structure
		temp=head->next;
		free(head); //freeing head
		head=temp;
	}else{
		data=-1;
	}
	return 	data;
	
}

/************************************************************
 appendNode - add new node at end of list
 ************************************************************/
void appendNode(int data)
{
  struct node *temp = head;
	if(length()>0){ //checking for length
        	while(temp->next!=NULL){
	                //traversing through the linked list
                	temp=temp->next;
        	}
		struct node *node_to_append = malloc(sizeof(struct node)); //creating node structure
		node_to_append->next=NULL;
		node_to_append->data=data;
		temp->next=node_to_append;
		
	}else{
		push(data);
	}
}

void appendNodeHelper(int data, struct node* pointer)
{
  struct node *temp= pointer; //creating node and assigning value
  //checking for the size 
  struct node *temp_counter= pointer;
  int c=0;
  while(temp_counter!=NULL){
          //traversing through the linked list
          temp_counter=temp_counter->next;
          c++; //increasing count
  }
  
  if(c==0){
  temp->data=data;
  temp->next=NULL;
  }
  else{
                while(temp->next!=NULL){
                        //traversing through the linked list
                        temp=temp->next;
                }
                struct node *node_to_append = malloc(sizeof(struct node)); //node to be appended created
                node_to_append->next=NULL;
                node_to_append->data=data;
                temp->next=node_to_append;
       }
}



/************************************************************
 copyList - return new copy of list
 ************************************************************/
struct node* copyList()
{
  struct node *temp= head;
	struct node *result = malloc(sizeof(struct node));
	result->data=temp->data;
	result->next=NULL;
	temp=temp->next;
  while(temp!=NULL){
  //traversing through the linked list
  int data=temp->data;
  appendNodeHelper(data, result);
	temp=temp->next; //keep moving to next
  }	
		return result;
}

/************************************************************
 freeList - release all memory you allocated for the linked list.
 NOTE -- add a unit test that calls this function and observes
 that you returned 1 for success on freeing all memory.
 The instructor will verify that you correctly freed all
 allocated memory.
 ************************************************************/
int freeList() {
	struct node *curr= head;
        while(curr!=NULL){
          //traversing through the linked list
        	struct node *temp= malloc(sizeof(struct node));
		temp=curr;
		curr=curr->next;

		free(temp);
        }

	head=NULL;
	if(length()==0){
		return 1;
	}
 	return 0;
}

