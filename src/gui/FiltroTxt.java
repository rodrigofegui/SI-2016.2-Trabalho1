/*	Pacote ao qual pertence */
package gui;

/*	Importando APIs necessárias */
import java.io.File;
import javax.swing.filechooser.FileFilter;

/*	Definição da Classe em si */
public class FiltroTxt extends FileFilter{
	/*	Criação do filtro de arquivos */
	public boolean accept(File f) {
		/*	Sendo um diretório, é aceito */
        if (f.isDirectory()) {
            return true;
        }
        
        /*	Verificação da extensão do arquivo */
        /*	Conseguindo a extensão do arquivo */
        String extensao = getExtensao(f);
        /*	Existindo 'extensao' e sendo válida, é aceita, caso contrário é rejeitada */
        if (extensao != null) {
            if (extensao.equals("txt")){
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /*	Como será visto no Selecionador de Arquivos */
    public String getDescription() {
        return "Arquivos de texto (*.txt)";
    }
    
    /*	Extraindo a extensão do arquivo */
    public String getExtensao (File arq){
    	String ext = null;
        String nomearq = arq.getName();
        
        /*	Encontra o último '.' */
        int pos = nomearq.lastIndexOf('.');

        /*	Sendo uma posição válida, extrai-se o resto do nome do arquivo,
         *	ou seja a extensão */
        if ((pos > 0) &&  (pos < nomearq.length() - 1)) {
            ext = nomearq.substring(pos + 1).toLowerCase();
        }
        
        return ext;
    }
}
