import java.util.Scanner;

public class VolumeCombustivel{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double tanque,nivel,calota,esfera,cilindro,raio;

        tanque =  sc.nextDouble();
        nivel = sc.nextDouble();
        raio = sc.nextDouble();

        calota = Math.PI/3f*Math.pow(nivel,2)*(3*raio-nivel);
        esfera = (4.0/3f)*Math.PI*Math.pow(raio,3);
        cilindro = Math.PI*Math.pow(raio,2)*nivel;

        if(tanque < 0 || nivel < 0 || raio <0) System.out.println("-1.000");
        else if(tanque-nivel <= raio){
            if(tanque == nivel) System.out.printf("%.3f",4*Math.PI/3f*Math.pow(raio,3)+Math.PI*Math.pow(raio,2));
            else  if(tanque-nivel < raio) System.out.printf("%.3f",4*Math.PI/3f*Math.pow(raio,3)/2+Math.PI*Math.pow(raio,2)*(nivel-(tanque-nivel)-raio)+Math.PI/3f*Math.pow((tanque-nivel),2)*(3*raio-(tanque-nivel)));
            else System.out.printf("%.3f",4*Math.PI/3f*Math.pow(raio,3)/2+Math.PI*Math.pow(raio,2)*(nivel-raio));
        }else{
            if(nivel == raio) System.out.printf("%.3f\n",4*Math.PI/3f*Math.pow(raio,3)/2);
            else if(nivel < raio) System.out.printf("%.3f\n",Math.PI/3f*Math.pow(nivel,2)*(3*raio-nivel));
            else System.out.printf("%.3f\n",4*Math.PI/3f*Math.pow(raio,3)/2+Math.PI*Math.pow(raio,2)*(nivel-raio));
            
        }
       
    }
}