import java.util.Scanner;

public class NumeroNarcisista{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s;
        s = sc.nextLine();
        int total = 0, aux;

        for(int i = 0; i<s.length();i++){
            total += Math.pow(s.charAt(i)-'0',s.length());

        }

        aux = Integer.parseInt(s.trim());


        if(aux == total){
            System.out.println("SIM");

        }else{
            System.out.println("NAO");

        }
       
    }
}