#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>

struct png_chunk_hdr{
    int length;
    char chunk_type[4];
}__attribute__ ((packed));

struct chunk_ihdr{
    int width;
    int height;
    char bit_depth;
    char colour_type;
    char compression_method;
    char filter_method;
    char interlace_method;
}__attribute__ ((packed));

int main(int argc, char **argv){

    struct png_chunk_hdr *aux_chunk = (struct png_chunk_hdr *) malloc(sizeof(struct png_chunk_hdr));
    struct chunk_ihdr *aux_ihdr = (struct chunk_ihdr *) malloc(sizeof(struct chunk_ihdr));
    
    FILE *png_chunk = fopen(argv[1],"rb");
    fseek(png_chunk,8,SEEK_CUR);
    
    int arquivo = 0;

    while(!feof(png_chunk)){
        arquivo++;
        printf("Lendo chunk [%d]:\n",arquivo);
        fread(aux_chunk,sizeof(struct png_chunk_hdr),1,png_chunk);
        printf("    --> Tamanho: %d\n",  ntohl(aux_chunk->length));
        printf("    --> Tipo: %.4s\n",  aux_chunk->chunk_type);
        if(strcmp(aux_chunk->chunk_type,"IHDR") == 0){
            fread(aux_ihdr,sizeof(struct chunk_ihdr),1,png_chunk);
            printf("        --> Largura: %d\n",  ntohl(aux_ihdr->width));
            printf("        --> Altura: %d\n",  ntohl(aux_ihdr->height));
            fseek(png_chunk,4,SEEK_CUR);
        }else if(strcmp(aux_chunk->chunk_type,"IEND") == 0) break;
        else fseek(png_chunk,ntohl(aux_chunk->length)+4,SEEK_CUR); 
    }

    free(aux_ihdr);
    free(aux_chunk);
    fclose(png_chunk);
}