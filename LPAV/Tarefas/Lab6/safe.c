#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct zip_file_hdr{
    int signature;
    short version;
    short flags;
    short compression;
    short mod_time;
    short mod_date;
    int crc;
    int compressed_size;
    int uncompressed_size;
    short name_length;
    short extra_field_length;
}__attribute__ ((packed));

int main(int argc, char **argv){

    FILE *zip_file = fopen(argv[1],"rb");
    struct zip_file_hdr *aux = (struct zip_file_hdr *) malloc(sizeof(struct zip_file_hdr));
    int arquivo = 0;

    while(!feof(zip_file)){
        fread(aux,sizeof(struct zip_file_hdr),1,zip_file);
        arquivo++;
        if(aux->signature == 67324752){
            char *nome = (char *) malloc(sizeof(char)*aux->name_length+1);
            fread(nome,sizeof(char)*aux->name_length,1,zip_file);
            nome[aux->name_length] = '\0';
            printf("\nArquivo [%d]\n",arquivo);
            printf("--> Nome do arquivo: %s\n",nome);
            printf("--> Tamanho compactado: %d\n",aux->compressed_size);
            printf("--> Tamanho descompactado: %d\n",aux->uncompressed_size);
            free(nome);
            fseek(zip_file, aux->extra_field_length + aux->compressed_size, SEEK_CUR);
            printf("\n");
        }else break;
    }
    free(aux);
    fclose(zip_file);
}