package br.edu.ufam.icomp.lab_excecoes;
import br.edu.ufam.icomp.lab_excecoes.Coordenada;
import br.edu.ufam.icomp.lab_excecoes.CoordenadaForaDosLimitesException;
import br.edu.ufam.icomp.lab_excecoes.CoordenadaNegativaException;
import br.edu.ufam.icomp.lab_excecoes.DigitoInvalidoException;
import br.edu.ufam.icomp.lab_excecoes.TamanhoMaximoExcedidoException;
import br.edu.ufam.icomp.lab_excecoes.DistanciaEntrePontosExcedidaException;
import java.lang.Math;
import java.util.ArrayList;


public class Caminho extends Object{
    private Coordenada[] caminho;
    private int tamanho;

    public Caminho(int maxTam){
        this.tamanho = maxTam;
        caminho = new Coordenada[maxTam];

    }

    public int tamanho(){
        int cont = 0;
        for(int i=0;i<tamanho;i++){
            if(caminho[i] != null) cont++;
        }
        return cont;
    }

    public void addCoordenada(Coordenada coordenada) throws TamanhoMaximoExcedidoException,DistanciaEntrePontosExcedidaException{
        int i;
        if(this.tamanho()+1 > tamanho) throw new TamanhoMaximoExcedidoException();

        for(i=0;i<tamanho;i++){
            if(caminho[i] == null){
                break;
            }
        }

        if(i-1 == -1){

            caminho[i] = coordenada;

        }else if(caminho[i-1].distancia(coordenada) > 15) throw new DistanciaEntrePontosExcedidaException();

        else caminho[i] = coordenada;
        
    }

    public void reset(){
        this.tamanho = 0;
        for(int i=0;i<tamanho;i++){
            caminho[i] = null;
        }
        

    }

    public String toString(){
        String palavra = "Dados do caminho:\n  - Quantidade de pontos: "+tamanho()+"\n  - Pontos:\n";
        for(int i=0;i<caminho.length;i++){
            if(caminho[i] != null) palavra +="   -> "+caminho[i].toString()+"\n";
        }
        return palavra;
    }

}
