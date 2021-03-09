package br.edu.ufam.icomp.lab_excecoes;

import br.edu.ufam.icomp.lab_excecoes.RoverCaminhoException;
import br.edu.ufam.icomp.lab_excecoes.RoverCoordenadaException;
import br.edu.ufam.icomp.lab_excecoes.RoverException;

public class DistanciaEntrePontosExcedidaException extends RoverCaminhoException{
    String exe;

    public DistanciaEntrePontosExcedidaException(){
        this("Distância máxima entre duas coordenadas vizinhas excedida");
    }

    public DistanciaEntrePontosExcedidaException(String exe){
        super(exe);
    }
    
}
