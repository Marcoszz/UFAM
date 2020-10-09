import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*; 

public class OperacoesInteiros{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Vector <Integer> x = new Vector<Integer>();
        Vector <Integer> y = new Vector<Integer>();
        int n=0,m=0,cont=0,total=0,pares=0,impares=0,maior=-1,menor=10000;
        while(m != -1){
            while(n != -1){
                n = sc.nextInt();
                if(n == -1) break;
                x.add(n);
            }
            for(int i=0;i<x.size();i++){
                total += x.get(i);
                if(x.get(i)%2==0){
                    pares+=1;
                }else{
                    impares+=1;
                }
                if(x.get(i) > maior) maior = x.get(i);
                if(x.get(i) < menor) menor = x.get(i);
            }
            System.out.printf("%d\n%d\n%d\n%d\n%.2f\n%d\n%d\n",x.size(),pares,impares,total,(double)total/(double)x.size(),maior,menor);
            pares = impares = total = maior = 0;
            menor = 100000;
            x.clear();
            m = sc.nextInt();
            if(m == -1) break;
            n=0;
            x.add(m);

        }

    }
}