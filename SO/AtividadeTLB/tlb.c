#define _GNU_SOURCE
#include <stdio.h>
#include <time.h>
#include <sched.h>
#include <stdlib.h>
#define PAGESIZE 4096


double tempo(int num, int iter){

    int *v = (int *) malloc((PAGESIZE*num)),i,j;
    clock_t inicio,fim;
    int salto = PAGESIZE/sizeof(int);
    double tempo_sec=0;

    inicio = clock();
    for(int j=0;j<iter;j++){
        for(i=0;i<num*salto;i+=salto){
            v[i] += 1;
        }
    }
    fim = clock();
    tempo_sec += (fim-inicio);
    
    tempo_sec = (double) (tempo_sec/(iter*num))/CLOCKS_PER_SEC;
    free(v);
    return tempo_sec;

}

FILE* script(int paginas){
    FILE *fp = fopen("test.txt","w+");

    for(int i=1;i<=paginas;i++){
        double temp = tempo(i,10000);
        fprintf(fp,"%d %.20f\n",i,temp);
    }

    return fp;
}

int main(){
    cpu_set_t mask;
    CPU_ZERO(&mask);
    CPU_SET(0, &mask);
    sched_setaffinity(0, sizeof(cpu_set_t), &mask);
    FILE *fp = script(100);
}