#include "hash.h"


int main(int argc, char **argv){
    tabela_hash_t *Hash;
    char nome[50];
    long long int cpf;
    int idade, arg;

    sscanf(argv[1],"%d",&arg);

    Hash = tabela_hash_pessoas_criar(arg);

    FILE * fp = fopen(argv[2], "r+");

    while(!feof(fp)){

        pessoa_t *pessoa = (pessoa_t*) malloc(sizeof(pessoa_t));
        fscanf(fp,"%50[^\t]\t%lld\t%d\n",nome,&cpf,&idade);

        strcpy(pessoa->nome,nome);
        pessoa->cpf = cpf;
        pessoa->idade = idade;

        tabela_hash_pessoas_adicionar(pessoa,Hash,arg);

        
    }

    tabela_hash_pessoas_listar(Hash,arg);
    
}