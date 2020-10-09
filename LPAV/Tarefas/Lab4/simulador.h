#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>
#include "lista.h"


typedef struct lista_vizinhos_t{

    int id;
    struct lista_vizinhos_t *prox;

}lista_vizinhos_t;

typedef struct No{
    
    int id;
    double pos_x;
    double pos_y;
    bool pacote_enviado;
    lista_vizinhos_t **lista;

}No;

typedef No *grafo_t;

bool lista_vizinhos_adicionar_ordenado(evento_t *evento, lista_eventos_t **lista);
bool lista_vizinhos_adicionar(int vizinho, lista_vizinhos_t **lista);
grafo_t grafo_criar(int tam);
void grafo_atualizar_vizinhos(int tam, double raio_comunicacao, grafo_t grafo);
void simulacao_iniciar(grafo_t grafo);