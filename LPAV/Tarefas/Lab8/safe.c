#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>


int main(int argc, char **argv){
    
    FILE *mpeg = fopen(argv[1],"rb");

    unsigned char start[3], tipo, picture[2];
    unsigned char byte1;
    unsigned char byte2;
    unsigned char byte3;
    unsigned char byte4;
    unsigned char numero;
    unsigned int largura=0;
    unsigned int altura=0;
    unsigned int frame_rate=0;

    while(!feof(mpeg)){
        fread(&start,sizeof(char),3,mpeg);
        if(start[0] == 0x00 && start[1] == 0x00 && start[2] == 0x01){
            fread(&tipo,sizeof(char),1,mpeg);
            if(tipo == 0xB3){
                byte1 = fgetc(mpeg);
                byte2 = fgetc(mpeg);
                byte3 = fgetc(mpeg);
                byte4 = fgetc(mpeg);
                largura = byte1 * 16 + (byte2 >> 4);
                altura = (byte2 & 0x0f) * 256 + byte3;
                frame_rate = byte4 & 0x0f;

                switch (frame_rate){
                    case 1:
                        printf("--> Código: %.2x -- Sequence -- Largura = %d, Altura = %d -- Frame rate = 23.976\n",tipo,largura,altura);
                        break;
                    case 2:
                        printf("--> Código: %.2x -- Sequence -- Largura = %d, Altura = %d -- Frame rate = 24.000fps\n",tipo,largura,altura);
                        break;
                    case 3:
                        printf("--> Código: %.2x -- Sequence -- Largura = %d, Altura = %d -- Frame rate = 25.000fps\n",tipo,largura,altura);
                        break;
                    case 4:
                        printf("--> Código: %.2x -- Sequence -- Largura = %d, Altura = %d -- Frame rate = 29.970fps\n",tipo,largura,altura);
                        break;
                    case 5:
                        printf("--> Código: %.2x -- Sequence -- Largura = %d, Altura = %d -- Frame rate = 30.000fps\n",tipo,largura,altura);
                        break;
                    case 6:
                        printf("--> Código: %.2x -- Sequence -- Largura = %d, Altura = %d -- Frame rate = 50.000fps\n",tipo,largura,altura);
                        break;
                    case 7:
                        printf("--> Código: %.2x -- Sequence -- Largura = %d, Altura = %d -- Frame rate = 59.940fps\n",tipo,largura,altura);
                        break;
                    case 8:
                        printf("--> Código: %.2x -- Sequence -- Largura = %d, Altura = %d -- Frame rate = 60.000fps\n",tipo,largura,altura);
                        break;
                    default:
                        break;
                }

        

            }else if(tipo == 0){
                fread(&picture,sizeof(char),2,mpeg);
                numero = picture[1] << 2;
                numero = (unsigned char) numero;
                numero = numero >> 5;
                switch (numero){
                    case 1:
                        printf("--> Código: %.2x -- Picture -- Tipo = I\n",tipo);
                        break;
                    case 2:
                        printf("--> Código: %.2x -- Picture -- Tipo = P\n",tipo);
                        break;
                    case 3:
                        printf("--> Código: %.2x -- Picture -- Tipo = B\n",tipo);
                        break;
                    default:
                        break;
                }

            }else{
                if(tipo == 0xBA) printf("--> Código: %.2x -- Pack\n",tipo);
                else if(tipo == 0xBB) printf("--> Código: %.2x -- System\n",tipo);
                else if(tipo == 0xB8) printf("--> Código: %.2x -- Group of Pictures\n",tipo);
                else if(tipo >= 0x01 && tipo <= 0xAF) printf("--> Código: %.2x -- Slice\n",tipo);
                else if(tipo >= 0xC0 && tipo <= 0xDF) printf("--> Código: %.2x -- Packet Video\n",tipo);
                else if(tipo >= 0xE0 && tipo <= 0xEF) printf("--> Código: %.2x -- Packet Audio\n",tipo);
                else printf("--> Código: %.2x -- Tipo de stream não implementado\n",tipo);
            }

        }else fseek(mpeg,-sizeof(char)*2,SEEK_CUR);

    }

}