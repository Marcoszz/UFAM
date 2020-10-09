#include "grafo.h"

bool lista_vizinhos_adicionar(int vizinho, lista_vizinhos_t **lista){
    lista_vizinhos_t *item_novo = (lista_vizinhos_t*) malloc(sizeof(lista_vizinhos_t));

    if(!item_novo){
        return false;
    }else{

        item_novo->id = vizinho;
        item_novo->prox = *lista;
        *lista = item_novo;
        return true;
    }

}

void lista_vizinhos_imprimir(lista_vizinhos_t *lista){

    lista_vizinhos_t *auxiliar = (lista_vizinhos_t *) malloc(sizeof(lista_vizinhos_t));
    
    auxiliar = lista;

    if(auxiliar){

        while(auxiliar){
            printf("%d ",auxiliar->id);
            auxiliar = auxiliar->prox;
        }

    }

}

grafo_t grafo_criar(int tam){

    grafo_t grafo = (grafo_t) malloc(sizeof(No)*tam);
    
    for(int i=0;i<tam;i++){

        grafo[i].lista = (lista_vizinhos_t **) malloc(sizeof(lista_vizinhos_t**));
        *(grafo[i].lista) = NULL;

    }

    return grafo;
}

void grafo_atualizar_vizinhos(int tam, double raio_comunicacao, grafo_t grafo){
    for(int i=0;i<tam;i++){
        for(int j=0;j<tam;j++){
            if(i != j){
                if(sqrt(pow(grafo[i].pos_x - grafo[j].pos_x,2) + pow(grafo[i].pos_y - grafo[j].pos_y,2)) < raio_comunicacao){
                    lista_vizinhos_adicionar(j,grafo[i].lista);
                }

            }         

        }
    }
}

void grafo_imprimir(int tam, grafo_t grafo){
    for(int i=0;i<tam;i++){
        printf("NÓ %d: ",i);
        lista_vizinhos_imprimir(*(grafo[i].lista));
        printf("\n");
    }
}