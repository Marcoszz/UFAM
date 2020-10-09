public class Placa{
    String placa;
    int tipo;

    public Placa(String placa, int tipo){

        this.placa = placa;
        this.tipo = tipo;
        

    }

    public Placa(){
        
    }

    public String getTipoString(){
        switch(this.tipo){
            case 1: return "Normal";
            case 2: return "Servico";
            case 3: return "Oficial";
            case 4: return "Auto Escola";
            case 5: return "Prototipo";
            case 6: return "Colecionador";
            default: return "Outros";
        }
    }

    public boolean temEstacionamentoLivre(){

        if(this.tipo == 2 || this.tipo == 3) return true;
        else return false;

    }

    public String getDescricao(){
        return "Placa: placa="+placa+", tipo="+getTipoString()+", estacionamentoLivre="+temEstacionamentoLivre()+".\n";
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