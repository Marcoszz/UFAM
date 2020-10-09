import java.util.Scanner;
import java.lang.Math;

public class AproximacaoSeno{

    public static double fat(double n){
        if(n == 1 || n == 0){
            return 1;
        }else{
            return n*fat(n-1);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double sen,x,meio=3;
        int n;

        x = sc.nextDouble();
        n = sc.nextInt();
        x = Math.toRadians(x);
        sen = x;

        for(int i = 1; i<=n; i++){
            if(i == 1);

            else if(i%2 != 0){

                sen += Math.pow(x, meio)/fat(meio);
                meio += 2;

            }else{
                sen -= Math.pow(x, meio)/fat(meio);
                meio += 2;

            }
            System.out.printf("%.10f\n",sen);
        }

    }
}