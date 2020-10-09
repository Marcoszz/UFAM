import java.util.Scanner;

public class TipoTriangulo{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double a,b,c;

        a =  sc.nextDouble();
        b = sc.nextDouble();
        c = sc.nextDouble();

        if((Math.abs(b-c) < a && a < b + c) && (Math.abs(a-c) < b && b < a+c) && (Math.abs(a-b) < c && c < a + b)){
            if(a < 0 || b < 0 || c < 0){
                System.out.println("invalido");
            }
            else if(a == b && b == c){
                System.out.println("equilatero");
            }else if(a != b && b != c){
                System.out.println("escaleno");
            }else{
                System.out.println("isosceles");
            }
        }else{

            System.out.println("invalido");

        }
       
    }
}