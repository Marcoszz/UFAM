import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 

public class DataExtenso{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Vector <String> x = new Vector<String>();
        String data = new String();
        String dia,mes,ano;
        String meses[] = new String[]{"janeiro","fevereiro","março","abril","maio","junho","julho","agosto","setembro","outubro","novembro","dezembro"};
        data = sc.nextLine();
        dia = data.substring(0,2);
        mes = data.substring(2,4);
        ano = data.substring(4,8);


        

        

        System.out.printf("%s de %s de %s\n",dia,meses[Integer.parseInt(mes)-1],ano);

    }
}