import java.util.ArrayList;

public class Turma{

    int numAlunos;
    String nome, professor;
    boolean acessivel;
    ArrayList<Integer> horarios = new ArrayList<Integer>();

    public Turma(){

    }

    public Turma(String nome, String professor, int numAlunos, boolean acessivel){
        this.nome = nome;
        this.professor = professor;
        this.numAlunos = numAlunos;
        this.acessivel = acessivel;
        
    }

    void addHorario(int horario){
        this.horarios.add(horario);
    }

    String getHorariosString(){

        String hor = "";
        int hora = 8;

        for(int i=0;i<this.horarios.size();i++){

            for(int j=1;j<=35;j++){

                if(hora > 20) hora = 8;

                if(j == this.horarios.get(i)){
                    if(j >= 1 && j<=7) hor+="segunda "+hora+"hs, ";
                    else if(j>=8 && j<= 14) hor+="terça "+hora+"hs, ";
                    else if(j>=15 && j<=21) hor+="quarta "+hora+"hs, ";
                    else if(j>=22 && j<=28) hor+="quinta "+hora+"hs, ";
                    else hor+="sexta "+hora+"hs, "; 
                }

                hora+=2;

            }
        }

        return hor.substring(0, hor.length()-2);
    }

    String getDescricao(){
        if(acessivel) return "Turma: "+nome+"\nProfessor: "+professor+"\nNúmero de Alunos: "+numAlunos+"\nHorário: "+this.getHorariosString()+"\nAcessível: sim\n";
        else return "Turma: "+nome+"\nProfessor: "+professor+"\nNúmero de Alunos: "+numAlunos+"\nHorário: "+this.getHorariosString()+"\nAcessível: não\n";
    }

}