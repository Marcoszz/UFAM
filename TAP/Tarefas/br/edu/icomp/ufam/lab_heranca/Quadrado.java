package br.edu.icomp.ufam.lab_heranca;

import java.lang.Math;

public class Quadrado extends Retangulo{
    

    public Quadrado(int posX, int posY, double lado){
        super(posX,posY,lado,lado);

    }

    public String toString(){
        return "Quadrado na "+getPosString()+" com lado de "+largura+"cm (área="+getArea()+"cm2, perímetro="+getPerimetro()+"cm)";
    }


}
