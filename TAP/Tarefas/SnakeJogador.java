import java.awt.Point;
import java.util.*;
import java.lang.Math;

/**
 * Classe de exemplo para a implementação de um Jogador para o Jogo Snake.
 * Nesta implementação, a próxima direção da cobra é escolhida aleatoriamente
 * entre as direções possíveis (que não geram colisões).
 * <p>
 * Use esta classe como base inicial para a sua solução do jogo. Basicamente
 * você precisará reimplementar o método {@code getDirecao}.
 * 
 * @author Horácio
 */

public class SnakeJogador {
    private Snake jogo;
    private int tiksemcomida;
    
    /**
     * Cria um novo jogador para o jogo passado como parâmetro.
     * @param jogo jogo snake.
     */
    public SnakeJogador(Snake jogo) {
        this.jogo = jogo;
        this.tiksemcomida = 0;
    }

    public boolean temparede(Point comida, Point atual, char dir){
        int x,y;

        if(dir == 'C'){
            for(x = atual.x,y = atual.y-1;comida.y<y;y--){
                if(jogo.isCelulaLivre(x, y) == false) return false; 
            }
        }
        else if(dir == 'D'){
            for(x = atual.x+1,y = atual.y;comida.x>x;x++){
                if(jogo.isCelulaLivre(x, y) == false) return false; 
            }
        }
        else if(dir == 'B'){
            for(x = atual.x,y = atual.y+1;comida.y>y;y++){
                if(jogo.isCelulaLivre(x, y) == false) return false; 
            }
        }
        else if(dir == 'E'){
            for(x = atual.x-1,y = atual.y;comida.x<x;x--){
                if(jogo.isCelulaLivre(x, y) == false) return false; 
            }
        }
        return true;
    }

    public boolean temcobra(Point atual){

        if (atual.x < 0 || atual.x >= jogo.getLargura() || atual.y < 0 || atual.y >= jogo.getAltura()) return false;
        return !(jogo.isCelulaLivre(atual.x, atual.y));

    }

    /**
     * Executado pelo método {@link Snake#inicia()} a cada 'tick' do jogo para
     * perguntar qual a próxima direção da cobra ('C'ima, 'D'ireita, 'B'aixo,
     * 'E'squerda ou 'N'enhum).
     * 
     * @return a próxima direção da cobra.
     */
    public char getDirecao() {
        /*
         * IMPLEMENTE AQUI A SUA SOLUÇÃO PARA O JOGO
         */
        
        ArrayList<Character> possiveisDirecoes = new ArrayList<Character>(4);
        Point cabeca = jogo.getSegmentos().getFirst();
        int posX, posY;
        char dir;
        
        // Adiciona as possíveis direções na lista

        posX = jogo.getComida().x;
        posY = jogo.getComida().y;
        dir = jogo.getDirecaoAtual();

        if(cabeca.y == posY){
            if(posX < cabeca.x && jogo.isCelulaLivre(cabeca.x-1, cabeca.y) && temparede(jogo.getComida(), cabeca, 'E')){
                tiksemcomida = 0;
                possiveisDirecoes.add('E');
            } 
            else if(posX > cabeca.x && jogo.isCelulaLivre(cabeca.x+1, cabeca.y) && temparede(jogo.getComida(), cabeca, 'D')){
                possiveisDirecoes.add('D');
                tiksemcomida = 0;
                
            }else{
                if(dir == 'C'){
                    if(temcobra(new Point(cabeca.x,cabeca.y-1))){
                        if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                    }else{
                        if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                    }

                }else if (dir == 'D'){
                    if(temcobra(new Point(cabeca.x+1,cabeca.y))){
                        if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                    }else{
                        if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                    }

                }else if (dir == 'B'){
                    if(temcobra(new Point(cabeca.x,cabeca.y+1))){
                        if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                    }else{
                        if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                    }

                }else if (dir == 'E'){
                    if(temcobra(new Point(cabeca.x-1,cabeca.y))){
                        if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                    }else{
                        if(jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                    }
                }
            }

        }else if(cabeca.x == posX){
            if(posY < cabeca.y && jogo.isCelulaLivre(cabeca.x, cabeca.y-1) && temparede(jogo.getComida(), cabeca, 'C')){
                possiveisDirecoes.add('C');
                tiksemcomida = 0;
            }else if(posY > cabeca.y && jogo.isCelulaLivre(cabeca.x, cabeca.y+1) && temparede(jogo.getComida(), cabeca, 'B')){
                possiveisDirecoes.add('B');
                tiksemcomida = 0;
            }else{
                if(dir == 'C'){
                    if(temcobra(new Point(cabeca.x,cabeca.y-1))){
                        if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                    }else{
                        if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                    }

                }else if (dir == 'D'){
                    if(temcobra(new Point(cabeca.x+1,cabeca.y))){
                        if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                    }else{
                        if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                    }

                }else if (dir == 'B'){
                    if(temcobra(new Point(cabeca.x,cabeca.y+1))){
                        if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                    }else{
                        if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                    }

                }else if (dir == 'E'){
                    if(temcobra(new Point(cabeca.x-1,cabeca.y))){
                        if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                    }else{
                        if(jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                        else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                        else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                    }
                }  
            }
        }else{
            if(dir == 'C'){
                if(temcobra(new Point(cabeca.x,cabeca.y-1))){
                    if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                    else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                    else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                }else{
                    if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                    else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                    else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                }

            }else if (dir == 'D'){
                if(temcobra(new Point(cabeca.x+1,cabeca.y))){
                    if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                    else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                }else{
                    if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                    else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                }

            }else if (dir == 'B'){
                if(temcobra(new Point(cabeca.x,cabeca.y+1))){
                    if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                    else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                    else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                }else{
                    if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                    else if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                    else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                }

            }else if (dir == 'E'){
                if(temcobra(new Point(cabeca.x-1,cabeca.y))){
                    if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                    else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                }else{
                    if(jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C');
                    else if(jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D');
                    else if(jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B');
                }
            }
        }
        // Não existe mais nenhuma direção disponível
        if (possiveisDirecoes.size() == 0) return 'N';
        
        
        // int posicao = new Random().nextInt(possiveisDirecoes.size());
        // tiksemcomida++;

        // if(tiksemcomida > 200){
        //     tiksemcomida = 0;
        //     return possiveisDirecoes.get(posicao);
        // } 

        return possiveisDirecoes.get(0);
    }
    
}