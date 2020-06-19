import java.util.Hashtable;
import java.util.TreeMap; 
import java.util.Scanner;

public class MyClass {
  
    public static void main (String args[]){

    int i=1, j=1, sup=10000000, iterHash = 1, iterRB = 1, pause;
    long tmpRB,tmpHash;
    Scanner input = new Scanner(System.in);
              
    Hashtable<Integer,Integer> tabelaHash = new Hashtable<Integer,Integer>();
    TreeMap<Integer, Integer> arvoreRB = new TreeMap<Integer, Integer>(); 
    
    for (j=1000000; j>=1; j/=10) {

        System.out.printf("\n Inserção na Tabela Hash em Java\n");

        tmpHash = System.currentTimeMillis();
        for (i=0; i<sup/j; i++) {
            tabelaHash.put(i, i*j);
        }
        tmpHash = System.currentTimeMillis() - tmpHash;

        System.out.println("\n " + (iterHash++ + " Iteração | Tempo para inserção de: " + (sup/j) + ": " + (tmpHash / 1000.0) + "s"));
        System.out.println("\n Parada para analise de memória, digite um número para continuar:");
        pause = input.nextInt();
        tabelaHash.clear();

        System.out.printf("\n Inserção na RB em Java\n");

        tmpRB = System.currentTimeMillis();
        for (i=0; i<sup/j; i++) {
            arvoreRB.put(i, i*j);
        }
        tmpRB = System.currentTimeMillis() - tmpRB;

        System.out.println("\n " + (iterRB++ + " Iteração | Tempo para inserção de: " + (sup/j) + ": " + (tmpRB / 1000.0) + "s"));
        System.out.printf("\n Parada para analise de memória, digite um número para continuar: ");
        pause = input.nextInt();
        arvoreRB.clear();

        System.out.printf("\n-----------------------\n");
    }
              
  }
    
}
