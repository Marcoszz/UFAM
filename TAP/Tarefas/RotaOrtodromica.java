import java.util.Scanner;

public class RotaOrtodromica{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double t1,t2,g1,g2;

        t1 =  sc.nextDouble();
        g1 =  sc.nextDouble();
        t2 =  sc.nextDouble();
        g2 =  sc.nextDouble();

        System.out.printf("A distancia entre os pontos (%f, %f) e (%f, %f) e de %.2f km\n",t1,g1,t2,g2,
        6371*Math.acos(Math.sin(Math.toRadians(t1))*Math.sin(Math.toRadians(t2))+Math.cos(Math.toRadians(t1))*Math.cos(Math.toRadians(t2))*Math.cos(Math.toRadians(g1)-Math.toRadians(g2))));


       
    }
}