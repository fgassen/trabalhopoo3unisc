
arquivo = open('BalanceteReceitaOrinal.txt', 'r')

conteudo = arquivo.readlines()

arquivo.close()

for linha in conteudo:
	dado = linha.split(',')
	print(dado)
	
	

