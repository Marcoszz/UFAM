import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 

public class HorasTrabalho{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n=0,total=0;

        while(n != -1){
            n = sc.nextInt();
            if(n == -1) break;
            total+=n;

            for(int i=1;i<=6;i++){
                n = sc.nextInt();
                total += n;
            }
            System.out.printf("%d\n",total);
            total = 0;
        }

    }
}