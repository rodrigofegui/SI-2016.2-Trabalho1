/*	Pacote ao qual pertence */
package gui;

/*	Importando APIs necessárias */
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Aplicação responsável pela construção dos arquivos válidos a serem
 * considerados no seletor de arquivos
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	13/09/2016
 */
public class FiltroTxt extends FileFilter{

	/**
	 * Verificação se é um arquivo no formato aceitável
	 * @param arq Arquivo a ser analisado
	 * @return true, se for um arquivo aceitável;
	 * false, caso contrário
	 */
	public boolean accept(File arq) {
        if (arq.isDirectory()) {
            return true;
        }
        
        String extensao = getExtensao(arq);
        if (extensao != null) {
            if (extensao.equals("txt")){
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * Descrição dos formatos aceitos
     * @return Descrição construída
     */
    public String getDescription() {
        return "Arquivos de texto (*.txt)";
    }
    
    /**
     * Valor atribuído à extensão do arquivo
     * @param arq Arquivo a ser extraido a extensão
     * @return Extensão detectada, ou "" caso contrário
     */
    public String getExtensao (File arq){
    	String ext = "";
        String nomearq = arq.getName();
        
        int pos = nomearq.lastIndexOf('.');

        if ((pos > 0) &&  (pos < nomearq.length() - 1)) {
            ext = nomearq.substring(pos + 1).toLowerCase();
        }
        
        return ext;
    }
}
