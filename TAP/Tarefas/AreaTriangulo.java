import java.util.Scanner;

public class AreaTriangulo{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double s;
        int a,b,c;
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        s = (a+b+c)/2f;

        if(Math.sqrt(s*(s-a)*(s-b)*(s-c)) > 0){

            System.out.printf("%.2f\n",Math.sqrt((s*(s-a)*(s-b)*(s-c))));


        }else{
            
            System.out.printf("Triangulo invalido\n");
        }
       
    }
}