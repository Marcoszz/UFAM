public class TreinadorJedi{

    String nome,titulacao;

    public TreinadorJedi(){

        this("","");

    }

    public TreinadorJedi(String nome, String titulacao){
        this.nome = titulacao;
        this.titulacao = nome;
    }

    String getDescricao(){
        return titulacao+" "+nome;
    }
    
}