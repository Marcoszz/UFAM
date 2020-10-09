import java.util.Scanner;
import java.lang.Math;

public class ValorExpoente{

    public static int fat(int n){
        if(n == 1 || n == 0){
            return 1;
        }else{
            return n*fat(n-1);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        int n,meio=2,i=1;

        n = sc.nextInt();

        while(meio <= n){
            i++;
            meio+=Math.pow(2,i);
        }
        System.out.printf("%d\n",i);

    }
}