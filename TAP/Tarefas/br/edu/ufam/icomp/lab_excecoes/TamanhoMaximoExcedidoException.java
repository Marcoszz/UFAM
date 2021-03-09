package br.edu.ufam.icomp.lab_excecoes;

import br.edu.ufam.icomp.lab_excecoes.RoverCaminhoException;
import br.edu.ufam.icomp.lab_excecoes.RoverCoordenadaException;
import br.edu.ufam.icomp.lab_excecoes.RoverException;

public class TamanhoMaximoExcedidoException extends RoverCaminhoException{
    String exe;

    public TamanhoMaximoExcedidoException(){
        this("Quantidade máxima de coordenadas excedida");
    }

    public TamanhoMaximoExcedidoException(String exe){
        super(exe);
    }
    
}
