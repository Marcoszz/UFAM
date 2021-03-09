import java.util.*; 

public class ListaInvertida{

    
    private Hashtable<String, LinkedList<String>> tabela = new Hashtable<String, LinkedList<String>>();

    public ListaInvertida(){

    }

    public boolean insere(String palavra, String documento){

        
        if(tabela != null){
            
            LinkedList<String> list = tabela.get(palavra);

            if(list != null){
                if(list.contains(documento)) return false;
                else{
                    list.add(documento);
                    return true;
                }
            }else{
                LinkedList<String> novo = new LinkedList<String>();
                novo.add(documento);
                tabela.put(palavra, novo);
                return true;
            } 
        }else{
            LinkedList<String> novo = new LinkedList<String>();
            novo.add(documento);
            tabela.put(palavra, novo);
            return true;
        }
    }

    public LinkedList<String> busca(String palavra){
        if(tabela == null) return null;
        else return tabela.get(palavra);
    }

    public String toString(){
        if(tabela == null) return "{}";
        else return tabela.toString();
    }

}