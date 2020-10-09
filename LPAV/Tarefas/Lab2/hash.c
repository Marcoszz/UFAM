#include "hash.h"


int tabela_hash_pessoas_funcao(pessoa_t *pessoa, int tam){

    return pessoa->cpf%tam;

}

bool lista_pessoas_adicionar_fim(pessoa_t *pessoa, lista_pessoas_t **lista){
    
    lista_pessoas_t *item_novo = (lista_pessoas_t*) malloc(sizeof(lista_pessoas_t));
    lista_pessoas_t *auxiliar = (lista_pessoas_t*) malloc(sizeof(lista_pessoas_t));

    auxiliar = *lista;


    if(!item_novo){
        return false;
    }else{

        if(!auxiliar){

            item_novo->pessoa = pessoa;
            item_novo->prox = NULL;
            *lista = item_novo;
            return true;

        }else{

            while(auxiliar->prox){
            auxiliar = auxiliar->prox;
            }
        
            item_novo->pessoa = pessoa;
            item_novo->prox = NULL;
            auxiliar->prox = item_novo;
            return true;


        }
    }

}

bool lista_pessoas_adicionar_inicio(pessoa_t *pessoa, lista_pessoas_t **lista){
    
    lista_pessoas_t *item_novo = (lista_pessoas_t*) malloc(sizeof(lista_pessoas_t));

    if(!item_novo){
        return false;
    }else{

        item_novo->pessoa = pessoa;
        item_novo->prox = *lista;
        *lista = item_novo;
        return true;
    }

}

void lista_pessoas_listar(lista_pessoas_t *lista){

    lista_pessoas_t *auxiliar = (lista_pessoas_t *) malloc(sizeof(lista_pessoas_t));
    
    auxiliar = lista;

    while(auxiliar){

        printf("- Nome: %s, CPF: %lld, Idade: %d\n",auxiliar->pessoa->nome,auxiliar->pessoa->cpf,auxiliar->pessoa->idade);
        auxiliar = auxiliar->prox;
        
    }

}

tabela_hash_t * tabela_hash_pessoas_criar(int tam){

    tabela_hash_t *tabela_hash = (tabela_hash_t*) malloc(sizeof(tabela_hash_t)*tam);

    for(int i=0;i<tam;i++){
        tabela_hash[i] = (lista_pessoas_t **) malloc(sizeof(lista_pessoas_t **));
        *(tabela_hash[i]) = NULL;
    }

    return tabela_hash;

}

bool tabela_hash_pessoas_adicionar(pessoa_t *pessoa, tabela_hash_t *tabela_hash,int tam){
    
    int indice = tabela_hash_pessoas_funcao(pessoa,tam);

    if (lista_pessoas_adicionar_inicio(pessoa,tabela_hash[indice])){
        return true;
    }else{
        return false;
    }

}

void tabela_hash_pessoas_listar(tabela_hash_t *tabela_hash,int tam){

    for(int i=0;i<tam;i++){
        if(*(tabela_hash[i])){

            printf("POSIÇÃO %d DA TABELA HASH:\n",i);
            lista_pessoas_listar(*(tabela_hash[i]));

        }
    }
}

