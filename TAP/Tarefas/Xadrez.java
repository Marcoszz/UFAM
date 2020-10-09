import java.util.Scanner;

public class Xadrez{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int tam = sc.nextInt();
        
        for(int i = 0; i<tam; i++){
            if(i%2 != 0) System.out.print(" ");

            for(int j = 0; j<tam; j++){
                System.out.print("* ");
            }
            System.out.print("\n");
        }

    }
}