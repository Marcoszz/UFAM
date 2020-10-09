import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 

public class PorcentagemAcerto{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Vector <Integer> x = new Vector<Integer>();
        Vector <Integer> y = new Vector<Integer>();
        int n=0,m=0,cont=0,total=0;

        while(n != -1){
            n = sc.nextInt();
            if(n == -1) break;
            cont+=1;
            x.add(n);
        }
        while(m != -1){
            m = sc.nextInt();
            if(m==-1) break;
            y.add(m);
        }

        for(int i=0;i<cont;i++){
            m = x.get(i);
            n = y.get(i);
            if(m == n) total+=1;
        }

        System.out.printf("%.2f\n",(double)total/(double)cont*100f);

    }
}