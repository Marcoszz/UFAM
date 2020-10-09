import java.util.Scanner;

public class ArteAscii{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int tam = sc.nextInt();
        
        for(int i = tam; i>0;i--){
            for(int j = 0; j<i; j++){
                System.out.print('*');
            }
            System.out.print("\n");
        }

        for(int i = 1; i<=tam;i++){
            for(int j = 0; j<i; j++){
                System.out.print('*');
            }
            System.out.print("\n");
        }

    }
}