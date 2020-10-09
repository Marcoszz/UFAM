public class Motor{
    double capacidade;
    int tipo,potencia;

    public Motor(int tipo, double capacidade, int potencia){

        this.capacidade = capacidade;
        this.tipo = tipo;
        this.potencia = potencia;
        

    }

    public Motor(){
        
    }

    public String getTipoString(){
        switch(this.tipo){
            case 1: return "Gasolina";
            case 2: return "Alcool";
            case 3: return "Flex";
            case 4: return "Diesel";
            case 5: return "Eletrico";
            default: return "Outros";
        }
    }

    public String getDescricao(){
        return "Motor: tipo="+getTipoString()+", capacidade="+capacidade+"L, potencia="+potencia+"CV.\n";
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