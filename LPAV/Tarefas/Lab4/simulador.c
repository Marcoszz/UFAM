#include "simulador.h"

bool lista_vizinhos_adicionar_ordenado(evento_t *evento, lista_eventos_t **lista){

    lista_eventos_t *item_novo = (lista_eventos_t*) malloc(sizeof(lista_eventos_t));
    lista_eventos_t *auxiliar = (lista_eventos_t*) malloc(sizeof(lista_eventos_t));

    auxiliar = *lista;

    if(!auxiliar){

            item_novo->evento = evento;
            item_novo->prox = NULL;
            *lista = item_novo;
            return true;

    }else{

        if(evento->temp <= auxiliar->evento->temp){

            item_novo->evento = evento;
            item_novo->prox = *lista;
            *lista = item_novo;
            return true;

        }else{

            while(auxiliar->prox != NULL && auxiliar->prox->evento->temp <= evento->temp) auxiliar = auxiliar->prox;
            
            item_novo->evento = evento;
            item_novo->prox = auxiliar->prox;
            auxiliar->prox = item_novo;
            
            return true;
            
        }

    }

}

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

grafo_t grafo_criar(int tam){

    grafo_t grafo = (grafo_t) malloc(sizeof(No)*tam);
    
    for(int i=0;i<tam;i++){

        grafo[i].lista = (lista_vizinhos_t **) malloc(sizeof(lista_vizinhos_t**));
        *(grafo[i].lista) = NULL;
        grafo[i].pacote_enviado = false;

        

    }

    return grafo;
}

void grafo_atualizar_vizinhos(int tam, double raio_comunicacao, grafo_t grafo){
    for(int i=0;i<tam;i++){
        for(int j=0;j<tam;j++){
            if(i != j){
                if(sqrt(pow(grafo[i].pos_x - grafo[j].pos_x,2) + pow(grafo[i].pos_y - grafo[j].pos_y,2)) <= raio_comunicacao){
                    lista_vizinhos_adicionar(j,grafo[i].lista);
                }

            }         

        }
    }
}

void simulacao_iniciar(grafo_t grafo){

    evento_t *evento = (evento_t *) malloc(sizeof(evento_t));
    lista_eventos_t *auxiliar = (lista_eventos_t*) malloc(sizeof(lista_eventos_t));
    lista_eventos_t **lista = (lista_eventos_t**) malloc(sizeof(lista_eventos_t**));
    lista_vizinhos_t *aux_list;


    evento->alvo=0;
    evento->temp=100.0;
    evento->tipo=1;
    *lista = NULL;

    lista_eventos_adicionar_ordenado(evento,lista);

    while(*lista){

        auxiliar = *lista;
        printf("[%3.6f] Nó %d recebeu pacote.\n",auxiliar->evento->temp/100.0,auxiliar->evento->alvo);
        aux_list = *(grafo[auxiliar->evento->alvo].lista);
        int id = auxiliar->evento->alvo;

        if(grafo[auxiliar->evento->alvo].pacote_enviado == false){

            while(aux_list){

                printf("\t--> Repassando pacote para o nó %d ...\n",aux_list->id);
                evento = (evento_t*) malloc(sizeof(evento_t));
                evento->alvo=aux_list->id;
                evento->temp=auxiliar->evento->temp + (10 + (aux_list->id * 1));
                evento->tipo=1;

                lista_eventos_adicionar_ordenado(evento,lista);

                aux_list = aux_list->prox;

            }

            grafo[id].pacote_enviado = true;

        }

        
        (*lista) = auxiliar->prox;
        free(auxiliar);     
        
    }
}