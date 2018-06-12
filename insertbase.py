
arquivo = open('BalanceteReceitaOrinal.txt', 'r')

conteudo = arquivo.readlines()

insert  = "INSERT INTO `receitas` (`id`, `receita`, `fr`, `descricao`, `orcada`, `atualizada`, `arrecada_mes`, `arrecada_exercicio`) VALUES (NULL"

for linha in conteudo:
	
	dado = linha.split(',')
	 insert = insert+dado[0]+
		","+dado[1]+
		","+dado[2]+
		","+dado[3]+"."+dado[4]+
		","+dado[5]+"."+dado[6]+
		","+dado[7]+"."+dado[8]+
		","+dado[9]+"."+dado[10]+");"
	

	print(dado)
	
arquivo.close()

