import java.util.Scanner;

public class TanqueCombustivel{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double t1,h;
        int op;

        t1 =  sc.nextDouble();
        h = sc.nextDouble();
        op = sc.nextInt();

        if(op == 1){

            System.out.printf("%.4f\n",Math.PI/3f*Math.pow(h,2)*(3*t1-h));
            
        }else{

            System.out.printf("%.4f\n",(4.0/3f)*Math.PI*Math.pow(t1,3)-(Math.PI/3f*Math.pow(h,2)*(3*t1-h)));
        }

       
    }
}