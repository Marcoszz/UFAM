package br.edu.ufam.icomp.lab_excecoes;

// import java.lang.Math;
// import java.util.ArrayList;


class RoverMain {
    public static Caminho doido = new Caminho(1);
    public static void main(String[] args) {
        try{
            Coordenada valentina = new Coordeanda (6,6,6);
        }catch(RoverException xis){
            doido.rest();
            System.out.println(xis);
        }
    }

}
