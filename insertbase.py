
arquivo = open('BalanceteReceitaOrinal.txt', 'r')

conteudo = arquivo.readlines()

insert  = "INSERT INTO `receitas` (`id`, `receita`, `fr`, `descricao`, `orcada`, `atualizada`, `arrecada_mes`, `arrecada_exercicio`) VALUES (NULL"

,, , , , , , );


for linha in conteudo:
	
	dado = linha.split(',')
	 insert = insert+
	

	print(dado)
	
arquivo.close()

