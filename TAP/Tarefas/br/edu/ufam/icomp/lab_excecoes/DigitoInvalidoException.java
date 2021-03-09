package br.edu.ufam.icomp.lab_excecoes;

import br.edu.ufam.icomp.lab_excecoes.RoverCoordenadaException;
import br.edu.ufam.icomp.lab_excecoes.RoverException;

public class DigitoInvalidoException extends RoverCoordenadaException{
    String exe;

    public DigitoInvalidoException(){
        this("Digito da coordenada inválido");
    }

    public DigitoInvalidoException(String exe){
        super(exe);
    }
    
}
