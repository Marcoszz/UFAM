public class Proprietario{
    String nome;
    int cnh;
    int anoNascimento;

    public Proprietario(String nome, int cnh,int anoNascimento){

        this.nome = nome;
        this.cnh = cnh;
        this.anoNascimento = anoNascimento;
        

    }

    public Proprietario(){
        
    }


    public int getIdade(int anoReferencia){
        return anoReferencia-anoNascimento;
    }

    public String getDescricao(){
        return "Proprietario: nome="+nome+", cnh="+cnh+", anoNascimento="+anoNascimento+".\n";
    }
    
}

// public class CarroMain{
//     public static void main(String[] args) {

//         Proprietario prop = new Proprietario();
//         prop.nome = "Emmett L. Brown";
//         prop.cnh = 98008173;
//         prop.anoNascimento = 1920;
//         prop.getDescricao();


       
//     }
// }