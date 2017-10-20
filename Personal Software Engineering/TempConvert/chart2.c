#include <stdlib.h>
#include <stdio.h>
int main( ) {
        float f=0;
        float c=0;
        printf("Fahrenheit-Celsius\n ");
        for(f;f<=300;f=f+20) {
                c = (f-32)* 5/9;
                printf("    %.0f\t    %5.1f\n",f,c);

        }
        return 0;
}
