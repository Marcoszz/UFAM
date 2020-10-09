public class IniciadoJedi{

    String nome,especie;
    int anoNascimento;

    public IniciadoJedi(){

        this("","",0);

    }

    public IniciadoJedi(String nome, String especie, int anoNascimento){
        this.nome = nome;
        this.especie = especie;
        this.anoNascimento = anoNascimento;
    }

    String getAnoNascimento(){
        if(this.anoNascimento < 0){
            return -anoNascimento+" ABY";
        }else{
            return anoNascimento+" DBY";
        }
    }

    String getDescricao(){
        return nome+" (especie="+especie+", nascimento="+getAnoNascimento()+")";
    }
    
}