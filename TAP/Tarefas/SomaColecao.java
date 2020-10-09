import java.util.Scanner;

public class SomaColecao{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a=0,b=0;


        while(a != -1){
            b+=a;
            a = sc.nextInt();
        }

        System.out.printf("%d\n",b);
       
    }
}