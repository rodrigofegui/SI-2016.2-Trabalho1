###################
# Programas
###################
# Responsável por compilar a aplicação desenvolvida em Java
COMPILADOR = javac
# Responsável por executar a aplicação desenvolvida em Java
EXECUTADOR = java
# Responsável por documentar a aplicação desenvolvida em Java
DOCUMENTADOR = javadoc

###################
# Diretórios 
###################
# Atual, que contém todas as pastas necessárias
DIR_BASE = /home/rodrigo/Dropbox/UnB/UnB\ 2016.2/Sistemas\ de\ Informação/Trabalhos/Trabalho1/
# Arquivos-fonte
DIR_FONTES = src/
# Arquivos executáveis
DIR_BINARIO = bin/
# Arquivos da documentação
DIR_DOCUMENTACAO = Javadoc/

###################
# Tags importantes
###################
TAGS_DOCUMENTACAO = -author -version
TAGS_MEMORIA = -Xms1024M -Xmx1624M

###################
# Arquivo com o método "main"
###################
ARQ_PRINCIPAL = controle.ControladorEstoque


###################
# Regras
###################
help:
	@echo "Uso: make [opção]"
	@echo "Opções:"
	@echo "	compilar		Compilar todos os arquivos-fontes."
	@echo "	executar		Executa a aplicação, na versão mais recente"
	@echo "				da mesma: limpando o diretório 'bin' e recompilando."
	@echo "	documentar		Gera a documentação, atualizada, de todos os"
	@echo "				arquivos-fonte: limpando o diretório 'Javadoc' e"
	@echo "				recompilando."
	@echo "	clear_all		Limpeza dos diretório 'bin' e 'Javadoc'."
	@echo "	clear_bin		Limpeza do diretório 'bin'."
	@echo "	clear_doc		Limpeza do diretório 'Javadoc'."
	@echo "	help			Imprime esta mensagem e sai."

compilar:
	@clear
	@echo "Compilando arquivos em '$(DIR_FONTES)'"
	@$(COMPILADOR) -d $(DIR_BINARIO) -cp $(DIR_BINARIO):. -sourcepath $(DIR_FONTES):. $(DIR_FONTES)*/*.java -Xlint
	@echo "Compilação concluída\n"
	
	
executar: clear_bin compilar
	@echo "A executar '$(ARQ_PRINCIPAL)'"
	@$(EXECUTADOR) $(TAGS_MEMORIA) -cp $(DIR_BINARIO):. $(ARQ_PRINCIPAL)
	@echo "Aplicação finalizada"

documentar: clear_doc compilar
	@echo "Gerando a documentação"
	@$(DOCUMENTADOR) -d $(DIR_DOCUMENTACAO) -sourcepath $(DIR_FONTES):. $(DIR_FONTES)*/*.java -docencoding UTF-8 -charset UTF-8 $(TAGS_DOCUMENTACAO)
	@echo "\nDocumentação salva em '$(DIR_DOCUMENTACAO)'"

clear_all: clear_bin clear_doc
	@clear

clear_bin:
	@echo "Excluindo todos os arquivos de '$(DIR_BINARIO)'"
	@rm -rf $(DIR_BINARIO)
	@mkdir $(DIR_BINARIO)
	@echo "Arquivos excluídos, pasta '$(DIR_BINARIO)' vazia"

clear_doc:
	@echo "Excluindo todos os arquivos de '$(DIR_DOCUMENTACAO)'"
	@rm -rf $(DIR_DOCUMENTACAO)
	@mkdir $(DIR_DOCUMENTACAO)
	@echo "Arquivos excluídos, pasta '$(DIR_DOCUMENTACAO)' vazia"
