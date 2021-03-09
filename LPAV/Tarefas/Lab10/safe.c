#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>

typedef struct {   
     uint8_t  daddr[6];  // Endereco MAC de destino    
     uint8_t  saddr[6];  // Endereco MAC de origem (source)    
     uint16_t protocol;  // Protocolo da camada superior (IP!)
} ethernet_hdr_t;

typedef struct {    
    uint8_t  hdr_len:4, // Tamanho do Cabeçalho,              
             version:4; // Versão do IP    
    uint8_t  tos;       // Tipo de serviço    
    uint16_t tot_len;   // Tamanho total do IP    
    uint16_t id;        // Id do pacote    
    uint16_t frag_off;  // Fragmentado?    
    uint8_t  ttl;       // Tempo de vida    
    uint8_t  protocol;  // Protocolo da camada superior (TCP!)    
    uint16_t check;     // Checksum    
    uint8_t saddr[4];   // Endereço IP de origem    
    uint8_t daddr[4];   // Endereço IP de destino
} ip_hdr_t;

typedef struct {    
    uint16_t sport;       // Porta TCP de origem    
    uint16_t dport;       // Porta TCP de destino    
    uint32_t seq;         // Sequência    
    uint32_t ack_seq;     // Acknowledgement    
    uint8_t  reservado:4, // Não usado             
             hdr_len:4;   // Tamanho do cabecalho    
    uint8_t  flags;       // Flags do TCP    
    uint16_t window;      // Tamanho da janela    
    uint16_t check;       // Checksum    
    uint16_t urg_ptr;     // Urgente
} tcp_hdr_t;

int main(int argc, char **argv){
    
    FILE *http = fopen(argv[1],"rb");

    ethernet_hdr_t *ethe = (ethernet_hdr_t *) malloc(sizeof(ethernet_hdr_t));
    ip_hdr_t *ip = (ip_hdr_t *) malloc(sizeof(ip_hdr_t));
    tcp_hdr_t *tcp = (tcp_hdr_t *) malloc(sizeof(tcp_hdr_t));
    fread(ethe,sizeof(ethernet_hdr_t),1,http);
    fread(ip,sizeof(ip_hdr_t),1,http);
    fseek(http,ip->hdr_len*4-sizeof(ip_hdr_t),SEEK_CUR);
    fread(tcp,sizeof(tcp_hdr_t),1,http);
    fseek(http,tcp->hdr_len*4-sizeof(tcp_hdr_t),SEEK_CUR);
    char c;

    printf("Lendo Ethernet ..\n");
    printf("  --> MAC de Origem: %.2x:%.2x:%.2x:%.2x:%.2x:%.2x\n",ethe->saddr[0],ethe->saddr[1],ethe->saddr[2],ethe->saddr[3],ethe->saddr[4],ethe->saddr[5]);
    printf("  --> MAC de Destino: %.2x:%.2x:%.2x:%.2x:%.2x:%.2x\n",ethe->daddr[0],ethe->daddr[1],ethe->daddr[2],ethe->daddr[3],ethe->daddr[4],ethe->daddr[5]);
    printf("Lendo IP ..\n");
    printf("  --> Versão do IP: %d\n",ip->version);
    printf("  --> Tamanho do cabeçalho: %d bytes\n",ip->hdr_len*4);
    printf("  --> Tamanho do pacote: %d bytes\n",ntohs(ip->tot_len));
    printf("  --> Endereço IP de Origem: %d.%d.%d.%d\n",ip->saddr[0],ip->saddr[1],ip->saddr[2],ip->saddr[3]);
    printf("  --> Endereço IP de Destino: %d.%d.%d.%d\n",ip->daddr[0],ip->daddr[1],ip->daddr[2],ip->daddr[3]);
    printf("Lendo TCP ..\n");
    printf("  --> Porta de Origem: %d\n",ntohs(tcp->sport));
    printf("  --> Porta de Destino: %d\n",ntohs(tcp->dport));
    printf("  --> Tamanho do cabeçalho: %d bytes\n",tcp->hdr_len*4);
    printf("Lendo Dados (HTTP) ..\n");
    int tam_dados = ntohs(ip->tot_len) - (ip->hdr_len*4) - (tcp->hdr_len*4);
    printf("  --> Tamanho dos dados: %d bytes\n",tam_dados);
    printf("  --> Dados: \n");
    while(1){
        c = fgetc(http);
        if(feof(http)) break;
        printf("%c",c);       
    }

    fclose(http);
}