import java.util.ArrayList;

public class SessaoJedi{

    String nome;
    TreinadorJedi treinador;
    ArrayList<IniciadoJedi> iniciados = new ArrayList<IniciadoJedi>();

    public SessaoJedi(){

    }

    public SessaoJedi(String nome, TreinadorJedi treinador){
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
    
}