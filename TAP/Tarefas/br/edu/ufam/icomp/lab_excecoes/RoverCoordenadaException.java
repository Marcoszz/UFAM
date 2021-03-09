package br.edu.ufam.icomp.lab_excecoes;

import br.edu.ufam.icomp.lab_excecoes.RoverException;

public class RoverCoordenadaException extends RoverException{
    String exe;

    public RoverCoordenadaException(){
        this("Exceção geral de coordenada do rover");
    }

    public RoverCoordenadaException(String exe){
        super(exe);
    }
    
}
