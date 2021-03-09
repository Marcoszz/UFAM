package br.edu.ufam.icomp.lab_excecoes;

import br.edu.ufam.icomp.lab_excecoes.RoverCoordenadaException;
import br.edu.ufam.icomp.lab_excecoes.RoverException;

public class CoordenadaNegativaException extends RoverCoordenadaException{
    String exe;

    public CoordenadaNegativaException(){
        this("Coordenada com valor negativo");
    }

    public CoordenadaNegativaException(String exe){
        super(exe);
    }
    
}
