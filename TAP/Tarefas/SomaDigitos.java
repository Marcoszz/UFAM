import java.util.Scanner;

public class SomaDigitos{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s;
        s = sc.nextLine();
        int total = 0;

        for(int i = 0; i<s.length();i++){
            total += s.charAt(i)-'0';

        }

    System.out.printf("%d\n",total);
       
    }
}