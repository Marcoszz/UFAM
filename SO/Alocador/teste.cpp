#include "aloca.h"
#include <string.h>

int main(int argc, char **argv){

    int tamanhoMemoria, politica, numOp, i, id, tamanhoMem, maior, med, tam, erros;
    char op;
    list<FreeSpaceNo>::iterator it;
    FILE *fp = fopen("graficos.txt", "w+");

    for(int j = 1 ; j <=3 ; j++){

        float media[100000];
        int espaco[100000];
        char *vetor[100000];
        erros = 0;

        FILE *f = fopen(argv[j], "r");
        if (f==NULL) exit(1);

        fscanf(f, "%d %d", &tamanhoMemoria, &politica);
        fscanf(f, "%d", &numOp);

        meualoc aloc = meualoc(tamanhoMemoria, politica);
        
        for(i = 0; i < numOp; i++){
            tam = aloc.espacosLivres.size();
            maior=med=0;
            // printf("\n%d\n", i+3);
            fscanf(f, "%s %d %d", &op, &id, &tamanhoMem);
            if(op == 'A'){
                vetor[id] = aloc.aloca(tamanhoMem);
                if(vetor[id] == NULL) erros++;
            } else {
                aloc.libera(vetor[id]);
            }

            for(it = aloc.espacosLivres.begin(); it != aloc.espacosLivres.end(); ++it){
                med += it->len;
            }

            if(tam >= 1){
                media[i] = (float) med/tam;
                espaco[i] = med;
            }
            else{
                media[i] = 0.0;
                espaco[i] = med;

            }
            

            fprintf(fp,"%d %d %f\n",tam,espaco[i],media[i]);
        }

        fprintf(fp,"\n");
        
        printf("%d\n",erros);

        fclose(f);

    }

    fclose(fp);
}