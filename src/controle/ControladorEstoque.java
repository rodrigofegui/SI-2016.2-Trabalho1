package controle;

import gui.InterfaceGrafica;

/**
 * Aplicação principal do controlador de estoque
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	07/09/2016
 */
public class ControladorEstoque {
	public static Estoque estoque;
	
	public static void main(String[] args) {
		estoque = new Estoque();
		//Estoque estoque = new Estoque("Referências/ListaItensBase.txt", "Referências/HistóricoBase.txt");
		
		//estoque.escreverRelatorio();
		new InterfaceGrafica ();
	}
}
