package br.edu.ufam.icomp.lab_excecoes;

public class RoverException extends Exception{
    String exe;

    public RoverException(){
        this("Exceção geral do rover");
    }

    public RoverException(String exe){
        super(exe);
    }
}