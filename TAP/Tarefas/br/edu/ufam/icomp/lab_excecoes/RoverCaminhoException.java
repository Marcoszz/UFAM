package br.edu.ufam.icomp.lab_excecoes;

import br.edu.ufam.icomp.lab_excecoes.RoverException;

public class RoverCaminhoException extends RoverException{
    String exe;

    public RoverCaminhoException(){
        this("Exceção geral de caminho do rover");
    }

    public RoverCaminhoException(String exe){
        super(exe);
    }
    
}
