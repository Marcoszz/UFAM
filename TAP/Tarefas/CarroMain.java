class Placa{
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
        return "Placa: placa="+placa+", tipo="+getTipoString()+", estacionamentoLivre="+temEstacionamentoLivre()+".";
    }
    
}

class Motor{
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
        return "Motor: tipo="+getTipoString()+", capacidade="+capacidade+"L, potencia="+potencia+"CV.";
    }
    
}

class Proprietario{
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
        return "Proprietario: nome="+nome+", cnh="+cnh+", anoNascimento="+anoNascimento+".";
    }
    
}


class Carro{
    String marca, modelo;
    Proprietario proprietario;
    Placa placa;
    Motor motor;


    public Carro(String marca, String modelo, Proprietario proprietario, Placa placa, Motor motor){
        this.marca = marca;
        this.modelo = modelo;
        this.proprietario = proprietario;
        this.placa = placa;
        this.motor = motor;

        
        

    }

    public Carro(){
        
    }

    public String getDescricao(){
        return "Carro "+marca+"/"+modelo+". "+proprietario.getDescricao()+placa.getDescricao()+motor.getDescricao();
    }
    
}

public class CarroMain{
    public static void main(String[] args) {

        Carro carrinho = new Carro();
        carrinho.proprietario = new Proprietario();
        carrinho.placa = new Placa();
        carrinho.motor = new Motor();
        carrinho.marca = "Polly Pocket";
        carrinho.modelo = "111";
        carrinho.proprietario.anoNascimento = 1969;
        carrinho.proprietario.cnh = 1291281;
        carrinho.proprietario.nome = "Valentina Very Latina";
        carrinho.placa.placa = "MPCL-2000";
        carrinho.placa.tipo =  3;
        carrinho.motor.capacidade = 69.999;
        carrinho.motor.potencia = 20;
        carrinho.motor.tipo = 3;

        carrinho.getDescricao();




       
    }
}