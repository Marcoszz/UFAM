import java.util.Scanner;

public class Desconto{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double t1;

        t1 =  sc.nextDouble();

        if(t1 >= 200.00){

            System.out.printf("%.2f\n",(t1*95f)/100f);

        }else{
            System.out.printf("%f\n",t1);
        }

       
    }
}