COMPILADOR = javac
EXECUTADOR = java
DOCUMENTADOR = javadoc
DIR_BASE = /home/rodrigo/Dropbox/UnB/UnB\ 2016.2/Sistemas\ de\ Informação/Trabalhos/Trabalho1/
DIR_FONTES = src/
DIR_BINARIO = bin/
DIR_DOCUMENTACAO = Javadoc/
DIR_EXTERN_JAR = /home/rodrigo/Dropbox/UnB/UnB\ 2016.2/Sistemas\ de\ Infomação/Trabalhos/
TAGS_DOCUMENTACAO = -author -version
TAGS_MEMORIA = -Xms1024M -Xmx1624M
ARQ_PRINCIPAL = ControladorEstoque

compilar:
	clear
	$(COMPILADOR) -d $(DIR_BINARIO) -cp $(DIR_BINARIO):. -sourcepath $(DIR_FONTES):. $(DIR_FONTES)*.java -Xlint
	
executar: clear_bin compilar
	$(EXECUTADOR) $(TAGS_MEMORIA) -cp $(DIR_BINARIO):. $(ARQ_PRINCIPAL)

documentar: clear_doc compilar 
	$(DOCUMENTADOR) -d $(DIR_DOCUMENTACAO) -sourcepath $(DIR_FONTES):. $(DIR_FONTES)*.java -docencoding UTF-8 -charset UTF-8 $(TAGS_DOCUMENTACAO)
	clear

clear_all: clear_bin clear_doc
	clear

clear_bin:
	rm -rf $(DIR_BINARIO)
	mkdir $(DIR_BINARIO)
	clear

clear_doc:
	rm -rf $(DIR_DOCUMENTACAO)
	mkdir $(DIR_DOCUMENTACAO)
	clear

