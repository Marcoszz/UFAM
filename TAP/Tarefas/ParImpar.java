import java.util.Scanner;

public class ParImpar{
    public static void main(String[] args) {
        int vetor[] = new int[100];
        int x = 0;
        int ind = 0;
        Scanner sc = new Scanner(System.in);

        while(x != -1){
            x = sc.nextInt();
            vetor[ind] = x;
            ind++;
        }

        for(int i = 0; i<ind-1;i++){
            if(vetor[i] % 2 == 0){
                System.out.println("PAR");
            }else{
                System.out.println("IMPAR");
            }

        }
        
     }
}