#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>


int main(int argc, char **argv){
    
    FILE *mpeg = fopen(argv[1],"rb");
    FILE *prim = fopen("video_parte_1.mpg","wb");
    int tam = atoi(argv[2]), ind = 2, ind_buffer=0;
    char *buffer = (char *) malloc(sizeof(char)*1024*1024*tam);
    unsigned char start[4];
    char *string = (char *) malloc(sizeof(char)*50);
    int tam_arq = 0;
    printf("Criando arquivo video_parte_1.mpg ..\n");

    while(1){
        fread(start,sizeof(char),4,mpeg);
        if(!(start[0] == 0x00 && start[1] == 0x00 && start[2] == 0x01 && start[3] == 0xB3) && !(feof(mpeg))){
            
            buffer[ind_buffer] = start[0];
            ind_buffer++;
            fseek(mpeg,-sizeof(char)*3,SEEK_CUR);
            continue; 
        }

        if(tam_arq+ind_buffer > tam*1024*1024){
            fclose(prim);
            sprintf(string,"video_parte_%d.mpg",ind);
            printf("Criando arquivo %s ..\n", string);
            ind++;
            prim = fopen(string,"wb");
            fwrite(buffer, sizeof(char), ind_buffer, prim);
            tam_arq = ind_buffer;
            ind_buffer = 0;
        }else{
            fwrite(buffer, sizeof(char), ind_buffer, prim);
            tam_arq += ind_buffer;
            ind_buffer = 0;
        }

        if(feof(mpeg)){
            fclose(prim);
            break;
        }else{
            memcpy(buffer,start,4);
            ind_buffer += 4;
        }
    }

    free(buffer);
    fclose(mpeg);

}