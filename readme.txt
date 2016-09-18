Responsável:
	Rodrigo Ferreira Guimarães

Matrícula:
	14/0170740

Linguagem Utilizada:
	Java

Plataforma necessária:
	Terminal com MV java instalada, ou seja: javac, java e javadoc

Descritivo:
	Módulo de Sistema de Informação Gerencial (SIG) referente ao cálculo
de estoque mínimo de um Sistema de Controle de Estoqeu (SCE).

Descritivo do módulo:
	Sendo desenvolvido em Java, houve o desenvolvimento de dois pacotes:
	- controle: que controla o núcleo da implementação do módulo desejável;
	- gui: que controla a interface gráfica desenvolvida;
Em cada pacote, há as seguintes classes:
	- controle.ControladorEstoque: aplicação principal do controlador de estoque;
	- controle.Estoque: responsável pela implementação lógica de um estoque;
	- controle.Item: responsável pela implementação lógica de um item;
	- gui.Comandos: responsável pelo gerenciamento da comunicação entre
a interface gráfica e o núcleo da aplicação;
	- gui.FiltroTxt: responsável pela construção dos arquivos válidos a serem
considerados no seletor de arquivos;
	- gui.FormatacaoPadrao: responsável pela formatação básica da interface gráfica;
	- gui.InterfaceGrafica: responsável pelo gerenciamento da interface gráfica;

Descritivo dos diretórios básicos:
	Há os seguintes diretórios:
	- ArquivosInicialização: responsável por armagenar os arquivos
interpretados como "listagem" e "histórico";
	- Javadoc: responsável por armazenar a documentação da aplicação;
	- Relatórios: responsável por armazenar os relatórios gerados. Os mesmos serão
nomeados como: relatorio_<data>_<hora>.txt, de modo a evitar a sobreposição de arquivos;
	- src: responsável por armazenar os arquivos-fonte da aplicação desenvolvida;
	- bin: reponsável por armazenar os arquivos compilados e executáveis.
Além dos diretórios, há o arquivo:
	- makefile: responsável pela abstração dos comandos essenciais à aplicação.

Descritivo do arquivo makefile:
	É preciso alterar de antemão o valor de DIR_BASE, pois este é o diretório atual que
contém todas as pastas necessárias; depende de cada computador e usuário.
	Executando o comando:
	@make
	Haverá uma simples explicação sobre cada regra possível.

Instruções sobre a interface:
	1. Escolha do arquivo com a listagem dos itens que serão carregados
no estoque; com o botão "Abrir" no campo da "Listagem";
		1.1. É permitida a edição dos atributos dos itens listados, sendo necessário salvar
a edição; com o botão "Salvar".
	2. Escolha do arquivo com o histórico dos itens;
		2.1. É permitida a visualização do histórico dos itens; com o botão "Histórico".
	3. Será habilitada a opção de calcular o estoque, mínimo ou máximo, daqueles que estiverem
com a respectiva opção marcada na tabela, caso contrário será atribuído 0 no campo correspondente
no relatório;
		3.1. É possível marcar ou desmarcar todos os itens para o cálculo dos estoques;
		3.2. É possível controlar o leadTime (LT), o período de uso do estoque (PP) e o fator de segurança (FS), indiretamente através do nível de segurança (NS), para todos os itens, sendo controlado pela marcação ou não da caixa "Aplicar a todos";
		3.3. É possível restaurar todos os itens à configuração inicial: LT = 2, PP = 1 e NS = 95; com o botão "Restaurar itens".
	4. Será habilitada a opção de geração do relatório, sendo salvo no .txt já descrito; com o botão "Gerar relatório".

