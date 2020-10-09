import java.util.Scanner;

public class Fahrenheit{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a;
        a = sc.nextInt();


        System.out.printf("%.1f\n",(9*a)/5f+32);        
    }
}