def troco(troco, moedas):
    qtd = 0
    while troco > 0:
        for i in moedas:
            if i <= troco:
                qtd+=1
                troco-=i
    return qtd

moedas = [100,50,25,10,5,1]
print(troco(137, moedas))