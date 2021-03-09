#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <GL/glut.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <time.h>

int largura;
int altura;
int quantidade;

int *lista;

int ccw(int xA,int yA,int xB,int yB,int xC,int yC){
    if((yC-yA)*(xB-xA) > (yB-yA)*(xC-xA)) return 1;
    return 0;
}

int intersect(int xA,int yA,int xB,int yB,int xC,int yC,int xD,int yD){
    int m1 = ccw(xA,yA,xC,yC,xD,yD);
    int m2 = ccw(xB,yB,xC,yC,xD,yD);
    int m3 = ccw(xA,yA,xB,yB,xC,yC);
    int m4 = ccw(xA,yA,xB,yB,xD,yD);

    if(m1 != m2 && m3 != m4) return 1;
    return 0;
}

void display(void) {
    int x1,y1,x2,y2,atual,j,i,flag; 

    glClear(GL_COLOR_BUFFER_BIT);

    for(i = 0; i<quantidade;i++){
        if(i == 0){
            x1 = rand() % (largura + 1 - 0) + 0;
            x2 = rand() % (largura + 1 - 0) + 0;
            y1 = rand() % (altura + 1 - 0) + 0;
            y2 = rand() % (altura + 1 - 0) + 0;
            lista[0] = x1;
            lista[1] = y1;
            lista[2] = x2;
            lista[3] = y2;
            

            glColor3f(1.0, 0, 1.0); // Seta a cor do seg. (Red, Green, Blue, entre 0.0 e 1.0)
            glBegin(GL_LINES);// Indica que um segmento será iniciado
            glVertex2f(x1, y1);// Seta a posição inicial do segmento (inteiros)
            glVertex2f(x2, y2);// Seta a posição final do segmento (inteiros)
            
            glEnd();// Finaliza a criação do segmento
            
            glFlush();

        }else{

            atual = i;
            

            while(1){

                flag = 0;
                x1 = rand() % (largura + 1 - 0) + 0;
                x2 = rand() % (largura + 1 - 0) + 0;
                y1 = rand() % (altura + 1 - 0) + 0;
                y2 = rand() % (altura + 1 - 0) + 0;

                for(j=0; j<atual*4;j+=4){
                    if(intersect(x1,y1,x2,y2,lista[j],lista[j+1],lista[j+2],lista[j+3]) == 1){
                        flag = 1;
                        break;
                    }
                }

                if(flag == 1){
                    continue;
                }else{
                    lista[j] = x1;
                    lista[j+1] = y1;
                    lista[j+2] = x2;
                    lista[j+3] = y2;
                    break;
                }


            }

            glColor3f(1.0, 0.0, 1.0); // Seta a cor do seg. (Red, Green, Blue, entre 0.0 e 1.0)
            glBegin(GL_LINES);// Indica que um segmento será iniciado
            glVertex2f(x1, y1);// Seta a posição inicial do segmento (inteiros)
            glVertex2f(x2, y2);// Seta a posição final do segmento (inteiros)
            
            glEnd();// Finaliza a criação do segmento
            
            glFlush();

        }



    }
}

int main(int argc, char **argv){
    largura = atoi(argv[1]);
    altura = atoi(argv[2]);
    quantidade = atoi(argv[3]);
    lista = (int *) malloc(sizeof(int)*quantidade*4);

    srand(time(NULL));

    glutInit(&argc, argv);
    glutInitDisplayMode ( GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);

    // Cria uma janela de tamanho “largura” x “altura”
    glutInitWindowSize(largura, altura);
    glutCreateWindow ("Segmentos Aleatorios");
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(0.0, largura, 0.0, altura, -1.0, 1.0);

    // Seta a cor do fundo da janelagl
    glClearColor(1.0, 1.0, 1.0, 1.0);

    // Seta a função “display” como sendo a função que irá pintar a janela (infinitamente)
    glutDisplayFunc(display);
    glutMainLoop();


}