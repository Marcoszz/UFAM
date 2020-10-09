import java.util.Scanner;

public class Media{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        float a,b,c,media;
        a = sc.nextFloat();
        b = sc.nextFloat();
        c = sc.nextFloat();
        media = a+b+c;


        System.out.printf("%.2f\n",media/3.0f);        
    }
}