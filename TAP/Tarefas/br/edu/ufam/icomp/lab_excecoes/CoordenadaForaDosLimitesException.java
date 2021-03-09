package br.edu.ufam.icomp.lab_excecoes;

import br.edu.ufam.icomp.lab_excecoes.RoverCoordenadaException;
import br.edu.ufam.icomp.lab_excecoes.RoverException;

public class CoordenadaForaDosLimitesException extends RoverCoordenadaException{
    String exe;

    public CoordenadaForaDosLimitesException(){
        this("Coordenada com valores fora dos limites");
    }

    public CoordenadaForaDosLimitesException(String exe){
        super(exe);
    }
    
}
