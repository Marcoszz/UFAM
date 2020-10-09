#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>


typedef struct lista_vizinhos_t{

    int id;
    struct lista_vizinhos_t *prox;

}lista_vizinhos_t;

typedef struct No{
    
    int id;
    double pos_x;
    double pos_y;
    lista_vizinhos_t **lista;

}No;

typedef No *grafo_t;

void grafo_imprimir(int tam, grafo_t grafo);
void grafo_atualizar_vizinhos(int tam, double raio_comunicacao, grafo_t grafo);
void lista_vizinhos_imprimir(lista_vizinhos_t *lista);
bool lista_vizinhos_adicionar(int vizinho, lista_vizinhos_t **lista);
grafo_t grafo_criar(int tam);