#include <stdio.h>
#include <unistd.h>
#include <assert.h>
#include <pthread.h>
#include "mythreads.h"
#include <stdlib.h>


int max;
int loops;
int *buffer1,*buffer2;
int target;
int consu1 = 0;
int consu2 = 0;

int consome1  = 0;
int produz1 = 0;
int consome2  = 0;
int produz2 = 0;

int countBuf1 = 0;
int countBuf2 = 0;
pthread_cond_t freeBuf1, freeBuf2, fillBuf1, fillBuf2;
pthread_mutex_t mutex1, mutex2;

#define MAX (10)

int consumidores = 1;
int produtores = 1;
int nambos = 1;

void produz(int valor,int buf) {
   if(buf==1){
      buffer1[produz1] = valor;
      produz1 = (produz1+1) % max;
      countBuf1++;

   }else{
      buffer2[produz2] = valor;
      produz2 = (produz2+1) % max;
      countBuf2++;
   }
}

int consome(int buf) {
   int tmp; 
   if(buf==1){
      tmp = buffer1[consome1];
      consome1 = (consome1+1) %max;
      countBuf1--;
   }else{
      tmp = buffer2[consome2];
      consome2 = (consome2+1) %max;
      countBuf2--;
   }

   return tmp;
}

void *produtor(void *arg) {
   int i;
   for(int i = 0;i<loops;i++){
      Pthread_mutex_lock(&mutex1);
      while(countBuf1 == max) Pthread_cond_wait(&freeBuf1, &mutex1);
      produz(i,1);
      Pthread_cond_signal(&fillBuf1);
      printf("produtor %lld produziu %d\n", (long long int) arg, i);
      Pthread_mutex_unlock(&mutex1);
   }
   printf("Produtor %lld finalizado\n",(long long int) arg);
   return NULL;
}

void *consumidor(void *arg) {
   
   int tmp = 0;
   int i;
   while(1) {
      Pthread_mutex_lock(&mutex2);

      if(consu2 == target){
         for(int i=0;i<consu2;i++){
            Pthread_cond_signal(&fillBuf2);
         }
         countBuf2 = -1;

         
      }

      while(countBuf2 == 0) pthread_cond_wait(&fillBuf2, &mutex2);

      if(consu2 == target){
         for(int i=0;i<consu2;i++){
            Pthread_mutex_unlock(&mutex2);
         }

         break;
      }

      tmp = consome(2);
      consu2++;
      Pthread_cond_signal(&freeBuf2);
      printf("Consumidor %lld consumiu %d\n", (long long int) arg, tmp);
      Pthread_mutex_unlock(&mutex2);
   }
   return NULL;
}

void *ambos(void *arg) {
   int tmp = 0;
   int i;
   while(1) {
      Pthread_mutex_lock(&mutex1);
      
      if(consu1 == target){
         for(int i=0;i<consu1;i++){
            Pthread_cond_signal(&fillBuf1);
         }
         countBuf1 = -1;

   
      }

      while(countBuf1 == 0) Pthread_cond_wait(&fillBuf1, &mutex1);

      if(consu1 == target){
         for(int i=0;i<consu1;i++){
            Pthread_mutex_unlock(&mutex1);
         }
   
         break;
      }

      tmp = consome(1);
      Pthread_mutex_unlock(&mutex1);
      Pthread_mutex_lock(&mutex2);

      consu1++;
      Pthread_cond_signal(&freeBuf1);
      printf("Ambos %lld consumiu %d\n", (long long int) arg, tmp);
      while(countBuf2 == max) Pthread_cond_wait(&freeBuf2, &mutex2);
      produz(tmp,2);
      Pthread_cond_signal(&fillBuf2);
      printf("Ambos %lld produziu %d\n", (long long int) arg, tmp);
      Pthread_mutex_unlock(&mutex2);
   }
   return NULL;
}

int main(int argc, char *argv[]) {
   if (argc != 6) {
      fprintf(stderr, "uso: %s <tambuffer> <loops> <produtores> <consumidores> <ambos>\n", argv[0]);
      exit(1);
   }
   max = atoi(argv[1]);
   loops = atoi(argv[2]);
   produtores = atoi(argv[3]);
   consumidores = atoi(argv[4]);
   nambos = atoi(argv[5]);

   target = produtores*loops;

   assert(consumidores <= MAX);

   buffer1 = (int *) malloc(max * sizeof(int));
   buffer2 = (int *) malloc(max * sizeof(int));
   int i;
   for (i = 0; i < max; i++) {
      buffer1[i] = 0;
      buffer2[i] = 0;
   }


   pthread_t pid[MAX], cid[MAX],aid[MAX];
   for (i = 0; i < produtores; i++) {
      pthread_create(&pid[i], NULL, produtor, (void *) (long long int) i);
   }
   for (i = 0; i < nambos; i++) {
      pthread_create(&aid[i], NULL, ambos, (void *) (long long int) i);
   }
   for (i = 0; i < consumidores; i++) {
      pthread_create(&cid[i], NULL, consumidor, (void *) (long long int) i); 
   }
   for (i = 0; i < consumidores; i++) {
      pthread_join(cid[i], NULL); 
   }
   for (i = 0; i < produtores; i++) {
      pthread_join(pid[i], NULL); 
   }
   for (i = 0; i < nambos; i++) {
      pthread_join(aid[i], NULL);
   }
}