import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 
import java.util.Arrays;
import java.util.List;

public class OperacoesString{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String data = new String();
        Character aux;
        data = sc.nextLine();
        Character vog[] = new Character[]{'a','e','i','o','u','A','E','I','O','U'};
        int cont=0;
        

        System.out.printf("%d\n%s\n%s\n%s\n%s\n",data.length(),data.charAt(0),
        data.charAt(data.length()-1),data.toUpperCase(),data.toLowerCase());

        for(int i=0;i<data.length();i++){
            if(data.charAt(i) == 'a') System.out.printf("e");
            else{
                System.out.printf("%c",data.charAt(i));
            }
        }
        System.out.printf("\n");
        for(int i=0;i<data.length();i++){
            if(i%2 == 0) System.out.printf("%c",data.charAt(i));
        }
        System.out.printf("\n");
        for(int i=0;i<data.length();i++){
            aux = data.charAt(i);
            for(int j=0;j<10;j++){
                if(aux == vog[j]) cont++;
            } 
        }
        System.out.printf("%d\n",cont);
    
        



    }

}