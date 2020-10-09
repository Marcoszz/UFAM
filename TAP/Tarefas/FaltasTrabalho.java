import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 

public class FaltasTrabalho{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int dias[] = new int[]{0,0,0,0,0,0};
        int n = 0, cont=0;

        while(n != -1){
            n = sc.nextInt();
            if(n == -1) break;
            cont+=1;
            dias[n-2]+=1;
        }

        for(int i=0;i<6;i++){
            System.out.printf("%.1f ",(double)dias[i]/cont*100f);
        }
        System.out.printf("\n");



    }
}
