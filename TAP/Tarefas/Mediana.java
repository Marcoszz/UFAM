import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 

public class Mediana{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Vector <Integer> x = new Vector<Integer>();
        int cont=0,n=0,m=0;

        while(n != -1){
            n = sc.nextInt();
            if(n == -1) break;
            cont+=1;
            x.add(n);
        }

        if(cont%2==0){
            // System.out.printf("%d %d %d %d %f\n",x.get(cont/2-1),x.get(cont/2),cont/2-1,cont/2,(x.get(cont/2-1)+x.get(cont/2))/2f);
            System.out.printf("%.1f\n",(x.get(cont/2-1)+x.get(cont/2))/2f);

        }else{
            System.out.printf("%.1f\n",(double)x.get(cont/2));
        }


    }
}