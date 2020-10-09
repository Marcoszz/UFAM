import java.util.Scanner;
import java.lang.Math;

public class AngryBirds{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v0,a;
        double d,x;
        v0 = sc.nextInt();
        a = sc.nextInt();
        d = sc.nextFloat();
        x = Math.abs((d - (Math.pow(v0,2)*Math.sin(2*Math.toRadians(a)))/9.8f));

        if(x >= 0 && x <= 0.1) System.out.println('1');
        else System.out.println('0');
        

        
    }
}