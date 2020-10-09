import java.util.Scanner;

public class NumeroNeperiano{

    public static int fat(int n){
        if(n == 1 || n == 0){
            return 1;
        }else{
            return n*fat(n-1);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double e = 0;
        int n;

        n = sc.nextInt();

        for(int i = 0; i<n; i++){

            e += 1.0/(double)fat(i);
        }

        System.out.printf("%.6f\n",e);

    }
}