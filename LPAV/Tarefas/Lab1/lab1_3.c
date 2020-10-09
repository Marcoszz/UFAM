#include "lista.h"


int main(int argc, char **argv){
    lista_eventos_t **lista = (lista_eventos_t**) malloc(sizeof(lista_eventos_t**));

    *lista = NULL;

    double temp;
    int alvo,tipo;


    FILE * fp = fopen(argv[1], "r+");

    while(!feof(fp)){

        evento_t *evento = (evento_t*) malloc(sizeof(evento_t));
        fscanf(fp,"%lf\t%d\t%d\n",&temp,&alvo,&tipo);

        evento->temp = temp;
        evento->alvo = alvo;
        evento->tipo = tipo;

        lista_eventos_adicionar_ordenado(evento,lista);

        
    }

    lista_eventos_listar(*lista);
    
}