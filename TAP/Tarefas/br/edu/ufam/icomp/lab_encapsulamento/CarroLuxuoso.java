package br.edu.ufam.icomp.lab_encapsulamento;

import br.edu.ufam.icomp.lab_encapsulamento.Localizavel;
import java.util.*;

public class CarroLuxuoso extends Carro implements Localizavel{

    public CarroLuxuoso(String placa){
        super(placa);
        this.placa = placa;

    }

    public Posicao getPosicao(){
        Random r = new Random();
        Posicao nova = new Posicao(0,0,0);
        nova.setLatitude(-3.160000 + (-2.960000 + 3.160000)*r.nextDouble());
        nova.setLongitude(-60.120000 + (-59.820000 + 60.120000)*r.nextDouble());
        nova.setAltitude(15.0 + (100.0-15.0)*r.nextDouble());
        return nova;

    }

    public double getErroLocalizacao(){
        return 15.0;
    }


    
}
