#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

typedef struct __myarg_t {
    int a;
    int b;
} myarg_t;

int fib(int n){                                         
     if (n == 1) return 1;                   
     else{
        if (n == 2) return 1;                 
        else return fib(n - 1) + fib(n - 2);

    }                                
}  

void *mythread(void *arg) {
    myarg_t *m = (myarg_t *) arg;
    printf("%d %d\n", m->a, m->b);
    return NULL;
}

int main(int argc, char *argv[]) {
    pthread_t p[4];
    int rc,i,vez=3,a=1,b=1;

    myarg_t args[4];
    for(int i=0;i<4;i++){
        args[i].a = a;
        args[i].b = b;
        rc = pthread_create(&p[i], NULL, mythread, &args[i]);
        a = fib(vez);
        b = fib(vez+1);
        vez+=2;
        sleep(1);

    }

}