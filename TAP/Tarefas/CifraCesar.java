import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 


public class CifraCesar {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char letra;
        int cv = sc.nextInt();
        String ms = sc.nextLine();
        ms = ms.substring(1);
        ms = ms.toUpperCase();

        while (cv >= 26) {
            cv = cv - 26;
        }
        

        for (int i = 0; i < ms.length(); i++) { 
            if (ms.charAt(i) >= 65 && ms.charAt(i) <= 90) {
                if (ms.charAt(i) + cv > 90) {
                    letra = (char) (ms.charAt(i) + cv);
                    letra = (char) (letra - 90);
                    System.out.print((char) (letra+64));
                } else {
                    System.out.print((char) (ms.charAt(i) + cv));
                }
            }else{
                System.out.printf("%c",ms.charAt(i));
            }
        }
        System.out.printf("\n");
    }
}