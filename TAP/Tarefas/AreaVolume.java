import java.util.Scanner;

public class AreaVolume{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double r, pi = Math.PI;

        r = sc.nextDouble();

        System.out.printf("Um circulo com raio de %.2f centimetros tem uma area de %.2f centimetros quadrados.\n",r,pi*r*r);
        System.out.printf("Uma esfera com raio de %.2f centimetros tem um volume de %.2f centimetros cubicos.\n",r,(4.0/3f)*pi*Math.pow(r,3));



       
    }
}