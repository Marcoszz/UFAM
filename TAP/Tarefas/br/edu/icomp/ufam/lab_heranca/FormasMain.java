import br.edu.icomp.ufam.lab_heranca.*;

import java.lang.Math;


public class FormasMain{
    public static void main(String[] args) {

        FormaGeometrica[] oi = new FormaGeometrica[3];
        Circulo one = new Circulo(99, 69, 3.2);
        Retangulo two = new Retangulo(99, 69, 2.0, 3.0);
        Quadrado three = new Quadrado(99, 69, 5.0);

        oi[0] = one;
        oi[1] = two;
        oi[2] = three;

        for(int i=0;i<oi.length;i++){
            oi[i].toString();
        }

    }
}