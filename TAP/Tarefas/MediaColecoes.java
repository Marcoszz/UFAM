import java.util.Scanner;

public class MediaColecoes{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double a=0,b=0,c=0,cont=1;

        while(c != -1){
            c = sc.nextDouble();
            b+=c;
            a=0;
            if(c == -1) break;
            while(a != -1){
                b+=a;
                a = sc.nextDouble();
                cont+=1;
            }

    
            System.out.printf("%.2f\n",b/(cont-1));
            b = 0;
            cont = 1;
           
        }

    }
}