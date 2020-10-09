import java.util.Scanner;

public class AproximacaoPi{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double pi = 3;
        int n;
        double meio = 3;

        n = sc.nextInt();

        for(int i = 1; i<=n; i++){
            if(i == 1);

            else if(i%2 != 0){

                pi -= 4.0/((meio-1)*meio*(meio+1));
                meio += 2;

            }else{
                pi += 4.0/((meio-1)*meio*(meio+1));
                meio += 2;

            }
            System.out.printf("%.6f\n",pi);
        }

    }
}