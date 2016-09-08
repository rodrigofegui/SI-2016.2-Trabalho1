/**
 * Aplicação principal do controlador de estoque
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	07/09/2016
 */
public class ControladorEstoque {
	public static void main(String[] args) {
		Estoque estoque = new Estoque("Referências/ListaItensBase.txt", "Referências/HistóricoBase.txt");
	}
}
