import java.util.Scanner;

public class PontoReta{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float x,y;
        x = sc.nextFloat();
        y = sc.nextFloat();
        if(2*x+y==3){
            System.out.printf("Ponto (%.1f, %.1f) pertence a reta 2x + y = 3.\n",x,y);
        }else{
            System.out.printf("Ponto (%.1f, %.1f) nao pertence a reta 2x + y = 3.\n",x,y);
        }

        
    }
}