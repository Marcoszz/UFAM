import java.util.Scanner;

public class SomaColecoes{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a=0,b=0,c=0;

        while(c != -1){
            c = sc.nextInt();
            b+=c;
            a=0;
            if(c == -1) break;
            while(a != -1){
                b+=a;
                a = sc.nextInt();
            }
    
            System.out.printf("%d\n",b);
            b = 0;
           
        }

    }
}