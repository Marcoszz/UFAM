#include "simulador.h"


int main(int argc, char **argv){

    int nos,id;
    double raio,pos_x,pos_y;
    grafo_t grafo;

    FILE *fp = fopen(argv[1], "r+");

    fscanf(fp,"%d\t%lf\n",&nos,&raio);

    grafo = grafo_criar(nos);

    for(int i=0;i<nos;i++){
        fscanf(fp,"%d\t%lf\t%lf\n",&grafo[i].id,&grafo[i].pos_x,&grafo[i].pos_y);

    }

    grafo_atualizar_vizinhos(nos,raio,grafo);
    
    simulacao_iniciar(grafo);


}