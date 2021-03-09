#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>


int main(int argc, char **argv){

    int qtd,ch;
    char *abc = (char *) malloc(sizeof(char)*strlen(argv[2]));
    strcpy(abc,argv[2]);

    sscanf(argv[1],"%d",&qtd);

    for(int i=1;i<=qtd;i++){
        
        int possibilidades = (int) pow((double) strlen(abc), (double) i);
        // printf("%d\n",possibilidades);

        for(int j=0;j<possibilidades;j++){
            
            char palavra[200] = "";
            int vez = j;
            // printf("%s palavra",palavra);

            for(int x=0;x<i;x++){
                // printf("%d\n",i);

                ch = vez % strlen(abc);
                // printf("%c\n",abc[ch]);
                strncat(palavra,&abc[ch],1);
                // printf("%s",palavra);
                vez /= strlen(abc);

            }
            
            printf("%s\n",palavra); 
        }
    }

}