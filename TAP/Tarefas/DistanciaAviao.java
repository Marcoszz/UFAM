import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 

public class DistanciaAviao{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] loc = new int[1000];
        Vector<Integer> dias = new Vector<Integer>();
        int[][] tab = {{0,2,11,6,15,11,1},{2,0,7,12,4,2,15},{11,7,0,11,8,3,13},{6,12,11,0,10,2,1},{15,4,8,10,0,5,13},{11,2,3,2,5,0,14},{1,15,13,1,13,14}};

        int n=0,cont=0,total=0,ind=0,ind2;

        while(n != -1){
            n = sc.nextInt();
            if(n == -1) break;
            loc[cont] = n;
            cont+=1;
        }

        for(int i=0;i<cont-1;i++){
            ind = loc[i]%10-1;
            ind2 = loc[i+1]%10-1;
            total += tab[ind][ind2];
            
            
        }

        System.out.printf("%d\n",total);


    }
}