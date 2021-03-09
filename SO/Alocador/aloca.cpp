#include "aloca.h"


meualoc:: meualoc(int tamanhoMemoria, int politicaMem){
    tamanhoMem = tamanhoMemoria;
    memoria = (char*) malloc(sizeof(char)*tamanhoMem);
    politica = politicaMem;
    FreeSpaceNo nolivre;
    nolivre.len = tamanhoMemoria;
    nolivre.addr = memoria;
    espacosLivres.push_front(nolivre);
    checkPoint = espacosLivres.begin();
    numeroMagico = 18741;
}



char* meualoc:: alocaFirstFit(unsigned short int tamanho){
    char* ender=NULL;
    list<FreeSpaceNo>::iterator it;
    for(it = espacosLivres.begin(); it != espacosLivres.end(); ++it) {
        
        if(it->len>tamanho+4){
            ender = it->addr;
            it->addr += tamanho+4;
            it->len -= tamanho+4;

            *(ender) = (tamanho >> 8);
            *(ender+1) = (tamanho & 0xff);
            *(ender+2) = ((numeroMagico >> 8) & 0xff);
            *(ender+3) = (numeroMagico & 0xff);

            

            return ender+4;

        }else if(it->len == tamanho+4){

            ender = it->addr;
            // if(it->addr == checkPoint->addr) checkPoint =  ++it;
            espacosLivres.erase(it);
            

            *(ender) = (tamanho >> 8);
            *(ender+1) = (tamanho & 0xff);
            *(ender+2) = ((numeroMagico >> 8) & 0xff);
            *(ender+3) = (numeroMagico & 0xff);
    
            return ender+4;
        }
        
    }
    
    return NULL;
}


char* meualoc:: alocaNextFit(unsigned short int tamanho){
    char* ender;

    list<FreeSpaceNo>::iterator it = espacosLivres.begin();
    list<FreeSpaceNo>::iterator itC;

    it = checkPoint;

    if(it->len>tamanho+4){
        ender = it->addr;
        it->addr += tamanho+4;
        it->len -= tamanho+4;

        *ender = (tamanho >> 8) & 0xff;
        *(ender+1) = tamanho & 0xff;
        *(ender+2) = (numeroMagico >> 8) & 0xff;
        *(ender+3) = numeroMagico & 0xff;

        
        checkPoint =  it;
        return ender+4;

    }else if(it->len == tamanho+4){
        ender = it->addr;
        it->addr += tamanho+4;
        it->len -= tamanho+4;
        if(it->addr == checkPoint->addr) checkPoint =  ++it;
        espacosLivres.erase(it);
        

        *ender = (tamanho >> 8) & 0xff;
        *(ender+1) = tamanho & 0xff;
        *(ender+2) = (numeroMagico >> 8) & 0xff;
        *(ender+3) = numeroMagico & 0xff;

        
        return ender+4;

    }

    ++it;

    while(it != checkPoint){
        if(it == espacosLivres.end()){
            it = espacosLivres.begin();
        }
        
        if(it->len>tamanho+4){
            ender = it->addr;
            it->addr += tamanho+4;
            it->len -= tamanho+4;

            *ender = (tamanho >> 8) & 0xff;
            *(ender+1) = tamanho & 0xff;
            *(ender+2) = (numeroMagico >> 8) & 0xff;
            *(ender+3) = numeroMagico & 0xff;

            
            checkPoint =  it;
            return ender+4;

        }else if(it->len == tamanho+4){
            ender = it->addr;
            it->addr += tamanho+4;
            it->len -= tamanho+4;
            if(it->addr == checkPoint->addr) checkPoint =  ++it;
            espacosLivres.erase(it);
            

            *ender = (tamanho >> 8) & 0xff;
            *(ender+1) = tamanho & 0xff;
            *(ender+2) = (numeroMagico >> 8) & 0xff;
            *(ender+3) = numeroMagico & 0xff;

            
            return ender+4;

        }
        
        ++it;
    }

    return NULL;

}   




char* meualoc:: alocaBestFit(unsigned short int tamanho){
    int i,n;
    char *ender=NULL;
    int menor = 10000010;
    list<FreeSpaceNo>::iterator it;
    list<FreeSpaceNo>::iterator it_aux;
    
    for(it = espacosLivres.begin(),i=0; it != espacosLivres.end(); ++it,i++){
        if(it->len>=tamanho+4 && it->len < menor){
            menor = it->len;
            it_aux = it;
        }
    }

    if(it_aux->len > tamanho+4){
        ender = it_aux->addr;
        it_aux->addr += tamanho+4;
        it_aux->len -= tamanho+4;
        
        *ender = (tamanho >> 8) & 0xff;
        *(ender+1) = tamanho & 0xff;
        *(ender+2) = (numeroMagico >> 8) & 0xff;
        *(ender+3) = numeroMagico & 0xff;

        
        return ender+4;


    }else if(it_aux->len == tamanho+4){
        ender = it_aux->addr;
        it_aux->addr += tamanho+4;
        it_aux->len -= tamanho+4;
        if(checkPoint->addr == it_aux->addr) checkPoint =  ++it;
        espacosLivres.erase(it_aux);
        
        *ender = (tamanho >> 8) & 0xff;
        *(ender+1) = tamanho & 0xff;
        *(ender+2) = (numeroMagico >> 8) & 0xff;
        *(ender+3) = numeroMagico & 0xff;

        
        return ender+4;

    }

    return NULL;
}

// bool meualoc:: limiteMem(unsigned short int tamanho){
//     int tam = espacosLivres.size();
//     int maior = 0, media = 0;
    
//     list<FreeSpaceNo>::iterator it;
//     // printf("================================================\n");
//     // printf("Lista de espaços vazios: \n");
//     for(it = espacosLivres.begin(); it != espacosLivres.end(); ++it){
//         //printf("\t - Endereço: %d  Tamanho: %d  \n", it->addr, it->len);
        
//         if(it->len>maior){
//             maior = it->len;
//         }
//     }
//     if(maior-4<=tamanho) return true;
    
//     return false;
    
// }

char* meualoc:: aloca(unsigned short int tamanho){

    char* retorno = NULL;

    if(tamanho <= 0){
        printf("\nNão tenho memória NÃO TENHO\n");
        return retorno;
    }

    switch (politica){
    case 0:
        retorno = alocaBestFit(tamanho);
        break;

    case 1:
        retorno = alocaFirstFit(tamanho);

        break;
    
    case 2:
        retorno = alocaNextFit(tamanho);
        break;
    
    default:
        break;
    }

    return retorno;

}

char* meualoc:: verifica(char* ponteiro, int posicao){
    
    unsigned short int num = 0;
    
    if (ponteiro == NULL || ponteiro < memoria || ponteiro + posicao > memoria + tamanhoMem) {
        return NULL;
    }
    
    num = (*(ponteiro-2) << 8) | (*(ponteiro-1) & 0xff);
    
    if(num == numeroMagico){

        return ponteiro+posicao;
        
    }

    return NULL;
}

bool compare(FreeSpaceNo a, FreeSpaceNo b){
    return a.addr < b.addr;
}

int meualoc:: libera(char* ponteiro){
    
    unsigned short int tam=0;
    int i;
    list<FreeSpaceNo>::iterator it,it2,aux;
    
    if(!verifica(ponteiro, 0)){
        return 0;
    }

    tam = (*(ponteiro-4) << 8) | (*(ponteiro-3) & 0xff);
    

    FreeSpaceNo nolivre;
    nolivre.len = ((int) tam)+4;
    nolivre.addr = ponteiro-4;

    *(ponteiro-4) = 0;
    *(ponteiro-3) = 0;
    *(ponteiro-2) = 0;
    *(ponteiro-1) = 0; 

    if(espacosLivres.empty()){
        espacosLivres.push_front(nolivre);
        return 1;
    }

    if(nolivre.addr < espacosLivres.begin()->addr){
        espacosLivres.push_front(nolivre);
        it = espacosLivres.begin();
        it2 = ++it;
        --it;

        if(it2->addr == (it->addr + it->len)){
            it->len += it2->len;
            if(it2->addr == checkPoint->addr) checkPoint = it;
            espacosLivres.erase(it2);
            
        }
        return 1;
    }

    for(it = espacosLivres.begin(); it != espacosLivres.end(); ++it){
        if(it->addr >  nolivre.addr) break;
    }
    
    espacosLivres.insert(it,nolivre);
    it2 = --it; /* Posteior == it */
    ++it;
    aux = it2; /*No Livre*/
    --it2; /*Anterior*/

    if(aux->addr == (it2->len+it2->addr)){
        if(it->addr == (aux->addr+aux->len)){
            
            aux->addr = it2->addr;
            aux->len += it2->len+it->len;
            if(it2->addr == checkPoint->addr||it->addr == checkPoint->addr) checkPoint = aux;
            espacosLivres.erase(it);
            espacosLivres.erase(it2);

            return 1;

        }else{

            aux->addr = it2->addr;
            aux->len += it2->len;
            if(it2->addr == checkPoint->addr) checkPoint = aux;
            espacosLivres.erase(it2);
            return 1;

        }
    }

    if(it->addr == (aux->addr+aux->len)){
        aux->len += it->len;
        if(it->addr == checkPoint->addr) checkPoint = aux;
        espacosLivres.erase(it);
        return 1;
    }
    
    return 1;

}


// void meualoc:: coalesce(){
//     if(espacosLivres.size()==1) return ;
//     int i;
//     list<FreeSpaceNo>::iterator it, it2;
//     espacosLivres.sort(compare);
//     it = espacosLivres.begin();
    
    
//     for(i = 0; it!= espacosLivres.end(); ++it, i++){
//         //printf("começou: %lu\n", espacosLivres.size());
//         // it2 = espacosLivres.begin();
//         // advance(it2,i+1);
//         // printf("Endereço: %d  Tamanho: %d  \nProx Endereço: %d Proximo tamanho: %d\n", it->addr, it->len, it2->addr, it2->len);
        
//         if(++it==espacosLivres.end()) break;
//             it--;
//             it2 = it;
//             it2++;
            
//         if(it2->addr == (it->len+it->addr)){
//             //printf("tchauuuuuuu\n");
//             it->len = it2->len+it->len;
//             if(it2->addr==checkPoint->addr) checkPoint = it;
//             espacosLivres.erase(it2);
//             coalesce();

//         }else if(it2->addr < (it->len+it->addr)){
//             if(it2->addr==checkPoint->addr) checkPoint = it;
//             espacosLivres.erase(it2);

//         }
//     }
//     return ;

// }

void meualoc:: imprimeDados(){
    int tam = espacosLivres.size();
    int maior = 0, media = 0;
    
    list<FreeSpaceNo>::iterator it;
    printf("================================================\n");
    printf("Lista de espaços vazios: \n");
    for(it = espacosLivres.begin(); it != espacosLivres.end(); ++it){
        //printf("\t - Endereço: %d  Tamanho: %d  \n", it->addr, it->len);
        
        if(it->len>maior){
            maior = it->len;
        }
        
        media += it->len;
    }
    printf("\n");
    if(tam >= 1) printf("Número de elementos: %d\nMaior espaço livre: %d\nMedia dos espaços livres: %f, total de espaços livres: %d\n",tam,maior,(float) (media/tam), media);
    else printf("Número de elementos: %d\nMaior espaço livre: %d\nMedia dos espaços livres: %f\n",tam,maior,0.0);
    printf("================================================\n");
}

meualoc :: ~meualoc(){
    free(memoria);
    espacosLivres.clear();
}