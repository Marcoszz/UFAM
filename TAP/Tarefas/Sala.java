public class Sala{

    int bloco, sala, capacidade;
    boolean acessivel;

    public Sala(){

    }

    public Sala(int bloco, int sala, int capacidade, boolean acessivel){
        this.bloco = bloco;
        this.sala = sala;
        this.capacidade = capacidade;
        this.acessivel = acessivel;
    }

    String getDescricao(){
        if(acessivel) return "\n\n--- Bloco "+bloco+", Sala "+sala+" ("+capacidade+" lugares, acessível) ---";
        else return "\n\n--- Bloco "+bloco+", Sala "+sala+" ("+capacidade+" lugares, não acessível) ---";
    }

}
