#include <time.h>
#include <unordered_map>
#include <iostream>
#include <stdlib.h>
#include <map>
#define MAX 100000000 

using namespace std; 

int main() { 
    unordered_map<int, int> tabelaHash;
    map<int, int> arvorePV;
    clock_t tmpHash, tmpPV;
    int sup = MAX, iterHash = 1, iterRB=1, pause;


    for (int j=10000000; j>=1; j/=10) {

        tmpHash = clock();
        for (int i=0; i<sup/j; i++) {
            tabelaHash[i] = j*i; 
        }
        tmpHash = clock() - tmpHash;

        cout << "\n Inserção na Tabela Hash em C++\n";
        cout << "\n " << iterHash++ << ") Iteração | Tempo para inserção de " << sup/j << " chaves: " << ((double) tmpHash)/CLOCKS_PER_SEC << "s\n";
        cout << "\n Parada para analise de memória, digite um número para continuar:";
        cin >> pause;
        tabelaHash.clear();

        tmpPV = clock();
        for (int i=0; i<sup/j; i++) {
            arvorePV[i] = j*i; 
        }
        tmpPV = clock() - tmpPV;

        cout << "\n Inserção na RB em C++\n";
        cout << "\n " << iterRB++ << ") Iteração | Tempo para inserção de " << sup/j << " chaves: " << ((double) tmpPV)/CLOCKS_PER_SEC << "s\n";
        cout << "\n Parada para analise de memória, digite um número para continuar:";
        cin >> pause;
        arvorePV.clear();

        cout << "\n-----------------------\n"; 

    }
    
    return 0; 
}
