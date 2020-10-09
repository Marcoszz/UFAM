import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 

public class RaizDois{

    static double n=0;

    public static double raiz(double x){
        if(x == 1.0) return (double)1.0/3f;
        else{
            double prt = 1.0/(2+raiz(x-1));
            if(x!=n) System.out.printf("%.14f\n",prt+1);
            return prt;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] loc = new int[1000];
        Vector<Integer> dias = new Vector<Integer>();
        int[][] tab = {{0,2,11,6,15,11,1},{2,0,7,12,4,2,15},{11,7,0,11,8,3,13},{6,12,11,0,10,2,1},{15,4,8,10,0,5,13},{11,2,3,2,5,0,14},{1,15,13,1,13,14}};

        n = sc.nextInt();

        if(n != 1.0) System.out.printf("%.14f\n",4.0/3.0);
        System.out.printf("%.14f\n",1.0+raiz(n));


    }
}