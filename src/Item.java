/**
 * Classes necessárias
 */

import java.util.LinkedList;

/**
 * Classe responsável pela implementação física de um item
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	07/09/2016
 */
public class Item {
	/**
	 * Código único de identificacao
	 * Identificador inteligível
	 * Historico das quantidade em estoque, incluindo a atual
	 */
	private int codigo;
	private String descricao;
	private LinkedList<Integer> historico;
	
	/**
	 * Construção de um Item por maneira default
	 */
	public Item (){
		this.codigo = -1;
		this.descricao = "";
		this.historico = new LinkedList<Integer>();
	}
	
	/**
	 * Construção de um Item conhecendo seu código e 
	 * sua descrição
	 * @param codigo Identificação única
	 * @param descricao Identificação inteligível
	 */
	public Item (int codigo, String descricao){
		this();
		
		setCodigo(codigo);
		setDescricao(descricao);
	}
	
	
	/**
	 * Incremento de um valor ao histórico
	 * @param demanda Quantidade a ser adicionada ao histórico
	 */
	public void addHistorico (Integer demanda){
		this.historico.add(demanda);
	}
	
	/**
	 * Adição do valor atual ao histórico do Item
	 * @param atual Quantidade Atual
	 */
	public void addQntAtual (Integer atual){
		this.historico.addFirst(atual);
	}
	
	
	/**
	 * Valor atribuído ao codigo do Item
	 * @return Código do Item
	 */
	public int getCodigo (){
		return this.codigo;
	}
	
	/**
	 * Atribui valor ao código do Item
	 * @param codigo Valor a ser atribuído
	 */
	public void setCodigo (int codigo){
		this.codigo = codigo;
	}

	/**
	 * Valor atribuido à descrição do Item
	 * @return Descrição do Item
	 */
	public String getDescricao (){
		return this.descricao;
	}
	
	/**
	 * Atribui valor à descrição do Item
	 * @param descricao Descrição a ser atribuída
	 */
	public void setDescricao (String descricao){
		this.descricao = descricao;
	}
	
	/**
	 * Valores registrados no histórico do Item
	 * @return Histórico do Item
	 */
	public LinkedList<Integer> getHistorico (){
		return this.historico;
	}
	
	/**
	 * Atribui um histórico ao Item
	 * @param historico Histórico a ser atribuído
	 */
	public void setHistorico (LinkedList<Integer> historico){
		this.historico = historico;
	}

	/**
	 * Interpretação inteligível do Item
	 * @return Interpretação
	 */
	public String toString (){
		return "O código '" + getCodigo() + "' está atribuído ao produto: " + getDescricao() + "; tendo como histórico: " + getHistorico().toString() + "\n";
	}
	
	/**
	 * Comparação básica entre dois itens
	 * @param objetoDois Item a ser comparado
	 * @return Valor da comparação. 0 se forem iguais, senão retorna a subtração dos códigos.
	 */

	public int compareTo (Item objetoDois){
		if (this.equals(objetoDois))
			return 0;
		else
			return (this.codigo - objetoDois.getCodigo());
	}
}
