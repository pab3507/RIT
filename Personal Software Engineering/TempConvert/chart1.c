#include <stdlib.h>
#include <stdio.h>
int main( ) {
	int f=0;
	int c=0;
	printf("Fahrenheit-Celsius\n");
	for(f;f<=300;f=f+20) {
		c = (f-32)* 5/9;
		printf("    %d\t     %d\n",f,c);

	}
	return 0;
}
