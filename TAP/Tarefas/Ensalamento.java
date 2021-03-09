import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ensalamento{

    ArrayList<Sala> salas = new ArrayList<Sala>();
    ArrayList<Turma> turmas = new ArrayList<Turma>();
    ArrayList<TurmaEmSala> ensalamento = new ArrayList<TurmaEmSala>();


    public Ensalamento(){

    }

    void addSala(Sala sala){
        this.salas.add(sala);

    }


    void addTurma(Turma turma){
        this.turmas.add(turma);

    }

    Sala getSala(Turma turma){
        for(int i=0;i<ensalamento.size();i++){
            if(this.ensalamento.get(i).turma == turma) return ensalamento.get(i).sala;
        }
        return null;
    }

    boolean salaDisponivel(Sala sala, int horario){
        for(int i=0;i<this.ensalamento.size();i++){
            if(this.ensalamento.get(i).sala == sala){
                for(int j=0;j<this.ensalamento.get(i).turma.horarios.size();j++){
                    if(horario == this.ensalamento.get(i).turma.horarios.get(j)) return false;
                }
            }
        }

        return true;
    }

    boolean salaDisponivel(Sala sala, ArrayList<Integer> horarios){

        for(int i=0;i<horarios.size();i++){
            if(!salaDisponivel(sala, horarios.get(i))) return false;
        }
        return true;

    }

    boolean alocar(Turma turma, Sala sala){
        if(turma.acessivel){
            if(sala.acessivel){
                if(turma.numAlunos <= sala.capacidade){
                    if(salaDisponivel(sala, turma.horarios)){
                        TurmaEmSala nova = new TurmaEmSala();
                        nova.turma = turma;
                        nova.sala = sala;
                        ensalamento.add(nova);
                        return true;
                    }
                    return false;
                }
                return false;
    
            }
            return false;

        }else{
            if(turma.numAlunos <= sala.capacidade){
                if(salaDisponivel(sala, turma.horarios)){
                    TurmaEmSala nova = new TurmaEmSala();
                    nova.turma = turma;
                    nova.sala = sala;
                    ensalamento.add(nova);
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    void alocarTodas(){
        for(int i=0; i<turmas.size();i++){
            for(int j=0;j<salas.size();j++){

                if(alocar(turmas.get(i),salas.get(j))) break;

            }
        }

    }

    int getTotalTurmasAlocadas(){

        int cont=0;

        for(int i=0;i<ensalamento.size();i++){
            if(ensalamento.get(i).sala != null) cont++;

        }
        return cont;
    }

    int getTotalEspacoLivre(){

        int acumulador = 0;

        for(int i=0;i<ensalamento.size();i++){
            if(ensalamento.get(i).turma != null){
                acumulador += ensalamento.get(i).sala.capacidade - ensalamento.get(i).turma.numAlunos;
            }
        }
        return acumulador;
    }

    String relatorioResumoEnsalamento(){
        return "Total de Salas: "+salas.size()+"\n"+"Total de Turmas: "+turmas.size()+"\n"+"Turmas Alocadas: "+getTotalTurmasAlocadas()+"\nEspaços Livres: "+getTotalEspacoLivre()+"\n";
    }

    String relatorioTurmasPorSala(){
        String palavra = relatorioResumoEnsalamento();
        Iterator<Sala> iter = salas.iterator();
        Sala s;
        int i=0;
        while(iter.hasNext()){
            s = iter.next();
            palavra += "\n--- "+s.getDescricao()+" ---\n\n";
            for(i=0; i<ensalamento.size(); i++){
                if (ensalamento.get(i).sala == s) {
                    palavra += ensalamento.get(i).turma.getDescricao();
                }
            }
        }
        return palavra;
    }

    String relatorioSalasPorTurma(){
        String palavra = relatorioResumoEnsalamento();
        Iterator<Turma> iter = turmas.iterator();
        Sala s = new Sala();
        Turma t;
        while(iter.hasNext()){
            t = iter.next();
            s = getSala(t);
            if (s != null) {
                palavra += "\n\n" + t.getDescricao() + "\n"+"Sala: " + getSala(t).getDescricao();
            } else {
                palavra += "\n\n" + t.getDescricao() + "\n"+"Sala: SEM SALA";
            }
        }
        return palavra;
    }

    public static void main(String[] args){

        Ensalamento e1 = new Ensalamento();
        Sala s1 = new Sala(2, 102, 80, true);
        e1.addSala(s1);
        Turma t1 = new Turma("Organização de Computadores", "Andrew S. Tanenbaum", 70, true);
        t1.addHorario(7);
        t1.addHorario(21);
        t1.addHorario(35);
        e1.addTurma(t1);
        Sala s2 = new Sala(2, 202, 100, false);
        e1.addSala(s2);
        e1.alocar(t1, s2);
        Sala s3 = new Sala(2, 301, 50, true);
        e1.addSala(s3);
        for(int i=0;i<e1.salas.size();i++){
            System.out.println(e1.salas.get(i).getDescricao());
        }
        e1.alocar(t1, s3);
        e1.alocar(t1, s1);
        System.out.println(e1.relatorioTurmasPorSala());

    }


}