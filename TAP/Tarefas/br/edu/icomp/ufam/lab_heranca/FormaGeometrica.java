package br.edu.icomp.ufam.lab_heranca;

public abstract class FormaGeometrica extends Object{
    public int posX;
    public int posY;

    public FormaGeometrica(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public String getPosString(){
        return "posição ("+posX+", "+posY+")";
    }

    public abstract double getArea();

    public abstract double getPerimetro();


}
