import java.util.Scanner;

public class FolhaPagamento{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double h;
        int q;
        h = sc.nextDouble();
        q = sc.nextInt();
        System.out.printf("Salario bruto: R$%.2f\n",h*q);
        System.out.printf("IR: R$%.2f\n",(h*q*11)/100f);
        System.out.printf("INSS: R$%.2f\n",(h*q*8)/100f);
        System.out.printf("Total de descontos: R$%.2f\n",(h*q*11)/100f+(h*q*8)/100f);
        System.out.printf("Salario liquido: R$%.2f\n",h*q-((h*q*11)/100f+(h*q*8)/100f));
       
    }
}