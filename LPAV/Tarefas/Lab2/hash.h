#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct pessoa_t{

    char nome[50];
    long long int cpf;
    int idade;


}pessoa_t;

typedef struct lista_pessoas_t{

    struct pessoa_t *pessoa;
    struct lista_pessoas_t *prox;

}lista_pessoas_t;

typedef lista_pessoas_t **tabela_hash_t;

int tabela_hash_pessoas_funcao(pessoa_t *pessoa, int tam);
bool lista_pessoas_adicionar_fim(pessoa_t *pessoa, lista_pessoas_t **lista);
bool lista_pessoas_adicionar_inicio(pessoa_t *pessoa, lista_pessoas_t **lista);
bool tabela_hash_pessoas_adicionar(pessoa_t *pessoa, tabela_hash_t *tabela_hash,int tam);
void lista_pessoas_listar(lista_pessoas_t *lista);
void tabela_hash_pessoas_listar(tabela_hash_t *tabela_hash,int tam);
tabela_hash_t * tabela_hash_pessoas_criar(int tam);
