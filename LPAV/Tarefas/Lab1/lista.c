#include "lista.h"

bool lista_eventos_adicionar_inicio(evento_t *evento, lista_eventos_t **lista){
    
    lista_eventos_t *item_novo = (lista_eventos_t*) malloc(sizeof(lista_eventos_t));

    if(!item_novo){
        return false;
    }else{

        item_novo->evento = evento;
        item_novo->prox = *lista;
        *lista = item_novo;
        return true;
    }

}

bool lista_eventos_adicionar_fim(evento_t *evento, lista_eventos_t **lista){
    
    lista_eventos_t *item_novo = (lista_eventos_t*) malloc(sizeof(lista_eventos_t));
    lista_eventos_t *auxiliar = (lista_eventos_t*) malloc(sizeof(lista_eventos_t));

    auxiliar = *lista;


    if(!item_novo){
        return false;
    }else{

        if(!auxiliar){

            item_novo->evento = evento;
            item_novo->prox = NULL;
            *lista = item_novo;
            return true;

        }else{

            while(auxiliar->prox){
            auxiliar = auxiliar->prox;
            }
        
            item_novo->evento = evento;
            item_novo->prox = NULL;
            auxiliar->prox = item_novo;
            return true;


        }
    }

}

bool lista_eventos_adicionar_ordenado(evento_t *evento, lista_eventos_t **lista){

    lista_eventos_t *item_novo = (lista_eventos_t*) malloc(sizeof(lista_eventos_t));
    lista_eventos_t *auxiliar = (lista_eventos_t*) malloc(sizeof(lista_eventos_t));

    auxiliar = *lista;

    if(!auxiliar){

            item_novo->evento = evento;
            item_novo->prox = NULL;
            *lista = item_novo;
            return true;

    }else{

        if(evento->temp < auxiliar->evento->temp){

            item_novo->evento = evento;
            item_novo->prox = *lista;
            *lista = item_novo;
            return true;

        }else{

            while(auxiliar->prox != NULL && auxiliar->prox->evento->temp < evento->temp) auxiliar = auxiliar->prox;
            
            item_novo->evento = evento;
            item_novo->prox = auxiliar->prox;
            auxiliar->prox = item_novo;
            
            return true;
            
        }

    }

}

void lista_eventos_listar(lista_eventos_t *lista){

    int qtd = 0;
    lista_eventos_t *auxiliar = (lista_eventos_t *) malloc(sizeof(lista_eventos_t));
    
    auxiliar = lista;

    while(auxiliar){

        qtd++;
        printf("Temp: %3.6f, Alvo: %d, Tipo: %d\n",auxiliar->evento->temp,auxiliar->evento->alvo,auxiliar->evento->tipo);
        auxiliar = auxiliar->prox;
        
    }

    printf("Quantidade de eventos: %d\n",qtd);

}