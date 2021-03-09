#define BESTFIT 0
#define FIRSTFIT 1
#define NEXTFIT 2
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <list> 
#include <iterator> 
#include <iostream>

//TAMANHO MÁXIMO DE ALOCACAO: 65535 (maior unsigned short)
typedef struct FreeSpaceNo { 
    int len;
    char* addr;
    
}FreeSpaceNo;


using namespace std;


class meualoc{


	public:
		char *memoria; //char* pois eh byte a byte
		int tamanhoMem;
		int politica;
		int numeroMagico;
		list<FreeSpaceNo>::iterator checkPoint;
		list<FreeSpaceNo> espacosLivres;
		//tamanhoMemoria vai definir o tamanho da memória que o alocador vai utilizar
		//politicaMem define como escolher o bloco de onde saira a memória
		meualoc(int tamanhoMemoria,int politicaMem);

		//Pega um pedaco da variavel memoria e aloca, retornando o ponteiro para o comeco dessa regiao e atualizando a lista de livres.
		char* alocaFirstFit(unsigned short int tamanho);

		char* alocaBestFit(unsigned short int tamanho);

		char* alocaNextFit(unsigned short int tamanho);
		
		char* aloca(unsigned short int tamanho);

		//Verifica se a posicao dada pelo ponteiro esta alocada
		char* verifica(char* ponteiro,int posicao=0);
		
		//Libera o espaco ocupado na posicao, de forma analoga ao free. Ou seja, liberar toda a memoria alocada para este ponteiro na funcao aloca.
		int libera(char* ponteiro);

		//Imprime o numero de elementos na lista de vazios, o maior e a media de tamanhos dos blocos vazios
		void imprimeDados();

		//libera a memoria completamente como se fechasse o programa.

		~meualoc();


};
