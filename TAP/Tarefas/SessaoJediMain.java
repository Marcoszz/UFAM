import java.util.ArrayList;

public class SessaoJediMain{

    String nome;
    TreinadorJedi treinador;
    ArrayList<IniciadoJedi> iniciados = new ArrayList<IniciadoJedi>();

    public SessaoJediMain(String nome, TreinadorJedi treinador){
        this.nome = nome;
        this.treinador = treinador;
    }

    void addIniciado(IniciadoJedi iniciado){
        for(int i=0;i<iniciados.size();i++){
            if((iniciados.get(i)).nome == iniciado.nome) return;
        }

        iniciados.add(iniciado);
        return;

    }

    IniciadoJedi getIniciado(String nome){
        for(int i=0;i<iniciados.size();i++){
            if(iniciados.get(i).nome == nome) return iniciados.get(i);
        }

        return null;
    }

    double getMediaAnoNascimento(){
        double media = 0;
        for(int i=0;i<iniciados.size();i++){
            media += iniciados.get(i).anoNascimento;
        }
        return media/iniciados.size();

    }

    String getDescricao(){
        String titulo = "--> SESSÃO "+nome+" (Treinador: "+treinador.getDescricao()+")\n";
        String aux = new String();
        System.out.printf("-->%s",titulo);
        for(int i=0;i<iniciados.size();i++){
            aux = "- Iniciado "+(i+1)+":"+iniciados.get(i).getDescricao()+"\n";
            titulo+=aux;
        }

      return titulo;
    }

    public static void main(String[] args) {
        SessaoJedi one, two;
        IniciadoJedi um, dois;
        TreinadorJedi one1,two2;

        um = new IniciadoJedi();
        dois = new IniciadoJedi();
        one1 = new TreinadorJedi();
        two2 = new TreinadorJedi();
        one = new SessaoJedi();
        two = new SessaoJedi();

        um.anoNascimento = 2;
        dois.anoNascimento = 2;
        um.especie = "Dragon";
        dois.especie = "Dragon";
        um.nome = "Dragon";
        um.nome = "Dragon";


        one.nome = "Cock Destroyer";
        two.nome = "Cock Fucker";
        one.treinador.nome = "Valetina";
        two.treinador.nome = "Very Latina";
        one.treinador.titulacao = "Gay";
        two.treinador.titulacao = "Minj";
        one.iniciados.add(um);
        two.iniciados.add(dois);

        one.getDescricao();
        two.getDescricao();
    }
    
    
    
}