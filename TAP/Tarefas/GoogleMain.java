public class GoogleMain {
    public static void main(String[] args){
        ListaInvertida listona = new ListaInvertida();
        listona.insere("valentina", "verylatina.txt");
        listona.busca("valetina");
        System.out.println(listona.toString());

    }
    
}
