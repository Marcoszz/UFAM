package br.edu.ufam.icomp.lab_excecoes;
import br.edu.ufam.icomp.lab_excecoes.CoordenadaForaDosLimitesException;
import br.edu.ufam.icomp.lab_excecoes.CoordenadaNegativaException;
import br.edu.ufam.icomp.lab_excecoes.DigitoInvalidoException;
import java.lang.Math;


public class Coordenada extends Object{
    private int posX,posY,digito;

    public Coordenada(int posX, int posY, int digito) throws CoordenadaNegativaException,CoordenadaForaDosLimitesException,DigitoInvalidoException{

        if(posX < 0 || posY < 0) throw new CoordenadaNegativaException();
        if(posX > 30000 || posY > 30000 || posX < 0 || posY < 0 ) throw new CoordenadaForaDosLimitesException();
        if((posX+posY)%10 != digito) throw new DigitoInvalidoException();

        this.posX = posX;
        this.posY = posY;
        this.digito = digito;

    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }

    public double distancia(Coordenada cordenada){
        return Math.sqrt(Math.pow(this.getPosX()-cordenada.getPosX(), 2)+Math.pow(this.getPosY()-cordenada.getPosY(), 2));
    }

    public String toString(){
        return ""+getPosX()+", "+getPosY();
    }

}
