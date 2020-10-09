import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 

public class DiaSemana{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[][] tab = new int[10000][7];
        Vector<Integer> dias = new Vector<Integer>();
        int n=0,cont=0,total=0,maior=0,ind=0;

        while(n != -1){
            n = sc.nextInt();
            if(n == -1) break;
            tab[cont][0] = n;

            for(int i=1;i<=6;i++){
                n = sc.nextInt();
                tab[cont][i] = n;
            }
            cont+=1;
        }

        for(int i=0;i<7;i++){
            for(int j=0;j<cont;j++){
                total += tab[j][i];
            }
            if(total > maior){
                maior = total;
                ind = dias.size();
                dias.add(i+1);

            }else if(total == maior){
                maior = total;
                dias.add(i+1);

            }
            total = 0; 
            
        }


        for(int i=0;i<dias.size();i++){
            if(i >= ind) System.out.printf("%d\n",dias.get(i));
        }



    }
}