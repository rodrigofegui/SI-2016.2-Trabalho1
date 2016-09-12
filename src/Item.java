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
	 * Controle da inserção de novas demandas ao histórico
	 * Média da demanda
	 * Desvio padrão da demanda
	 * Estoque mínimo
	 * Estoque máximo
	 * Fator de segurança para o controle de estoque
	 * Tempo de reabastecimento
	 * Período de uso do estoque
	 */
	private int codigo;
	private String descricao;
	private LinkedList<Integer> historico;
	
	private boolean demandaNova;
	private int demandaMedia;
	private int demandaDesvPad;
	
	private int estoqueMin;
	private int estoqueMax;
	
	private float fatorSeguranca;
	private int leadTime;
	private int periodo;
	
	private boolean habilitadoEstoqueMin;
	private boolean habilitadoEstoqueMax;
	
	/**
	 * Construção de um Item por maneira default
	 */
	public Item (){
		this.codigo = -1;
		this.descricao = "";
		
		this.historico = new LinkedList<Integer>();
		
		this.demandaNova = true;
		this.demandaMedia = 0;
		this.demandaDesvPad = 0;
		
		this.estoqueMin = 0;
		this.estoqueMax = 0;
		
		restaurar();
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
	 * Construção de um Item conhecendo seu código,
	 * sua descrição e sua quantidade atual
	 * @param codigo Identificação única
	 * @param descricao Identificação inteligível
	 * @param qntAtual Quantidade atual em estoque
	 */
	public Item (int codigo, String descricao, int qntAtual){
		this(codigo, descricao);
		
		addQntAtual(qntAtual);
	}
	
	
	
	/**
	 * Incremento de um valor ao histórico
	 * @param demanda Quantidade a ser adicionada ao histórico
	 */
	public void addHistorico (Integer demanda){
		this.historico.add(demanda);
		this.demandaNova = true;
	}
	
	/**
	 * Adição do valor atual ao histórico do Item
	 * @param atual Quantidade Atual
	 */
	public void addQntAtual (Integer atual){
		this.historico.addFirst(atual);
		this.demandaNova = true;
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
	 * Valor atribuído à quantidade existente atualmente
	 * no estoque
	 * @return Quantidade existente em estoque
	 */
	public int getQntExistente (){
		return historico.getFirst();
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
	 * Valor atribuído à média da demanda
	 * @return Média da Demanda
	 */
	public int getDemandaMedia() {
		if (demandaNova)
			demandaMedia = calculaDemandaMedia();
		
		return demandaMedia;
	}

	/**
	 * Valor atribuído ao desvio padrão da demanda
	 * @return Desvio padrão da demanda
	 */
	public int getDemandaDesvPad() {
		if (demandaNova)
			demandaDesvPad = calculaDemandaDesvPad();
		
		return demandaDesvPad;
	}
	
	/**
	 * Valor atribuído ao nível de estoque mínimo
	 * @return Nível de estoque mínimo
	 */
	public int getEstoqueMin (){
		if (demandaNova || habilitadoEstoqueMin)
			estoqueMin = calculaEstoqueMin ();
		
		return estoqueMin;
	}

	/**
	 * Valor atribuído ao nível de estoque máximo
	 * @return Nível de estoque máximo
	 */
	public int getEstoqueMax (){
		if (demandaNova || habilitadoEstoqueMax)
			estoqueMax = calculaEstoqueMax ();
		
		return estoqueMax;
	}
	
	/**
	 * Valor atribuído ao fator de segurança para o Item
	 * @return Fator de segurança
	 */
	public float getFatorSeguranca() {
		return fatorSeguranca;
	}

	/**
	 * Atribui valor ao fator de segurança para o Item,
	 * conforme a porcentagem
	 * @param nivelSeguranca Porcentagem de seguranca,
	 * entre 0 e 99.99%, a ser atribuida
	 */
	public void setFatorSeguranca (float nivelSeguranca) {
		if (nivelSeguranca <= 60f)
			this.fatorSeguranca = 0.2540f;
		
		else if (nivelSeguranca <= 70f)
			this.fatorSeguranca = 0.5250f;
		
		else if (nivelSeguranca <= 80f)
			this.fatorSeguranca = 0.8420f;
		
		else if (nivelSeguranca <= 85f)
			this.fatorSeguranca = 1.0370f;
		
		else if (nivelSeguranca <= 90f)
			this.fatorSeguranca = 1.2820f;
		
		else if (nivelSeguranca <= 95f)
			this.fatorSeguranca = 1.6450f;
		
		else if (nivelSeguranca <= 96f)
			this.fatorSeguranca = 1.7510f;
		
		else if (nivelSeguranca <= 97f)
			this.fatorSeguranca = 1.8800f;
		
		else if (nivelSeguranca <= 98f)
			this.fatorSeguranca = 2.0550f;
		
		else if (nivelSeguranca <= 99f)
			this.fatorSeguranca = 2.3250f;
		
		else if (nivelSeguranca <= 99.90f)
			this.fatorSeguranca = 3.1000f;
		
		else if (nivelSeguranca <= 99.99f)
			this.fatorSeguranca = 3.6200f;
	}

	/**
	 * Valor atribuído ao tempo de reabastecimento do Item
	 * @return Tempo de Reabastecimento
	 */
	public int getLeadTime() {
		return leadTime;
	}

	/**
	 * Atribui valor ao tempo de reabastecimento do Item
	 * @param leadTime Tempo de reabastecimento a ser
	 * atribuído
	 */
	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * Valor atribuído ao período que utiliza o estoque
	 * @return Período de uso do estoque
	 */
	public int getPeriodo() {
		return periodo;
	}
	
	/**
	 * Atribui valor ao período que utiliza o estoque
	 * @param periodo Período de uso do estoque a ser
	 * atribuído
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * Verificação se o item está habilitado para o
	 * cálculo do estoque mínimo
	 * @return True se estiver habilitado ou false,
	 * caso contrário
	 */

	public boolean isHabilitadoEstoqueMin() {
		return habilitadoEstoqueMin;
	}
	
	/**
	 * Configuração da habilitação ou não para o cálculo do Estoque
	 * mínimo
	 * @param habilitadoEstoqueMin Ativação ou não do cálculo
	 */

	public void setHabilitadoEstoqueMin(boolean habilitadoEstoqueMin) {
		this.habilitadoEstoqueMin = habilitadoEstoqueMin;
	}
	
	/**
	 * Verificação se o item está habilitado para o
	 * cálculo do estoque máximo
	 * @return True se estiver habilitado ou false,
	 * caso contrário
	 */
	public boolean isHabilitadoEstoqueMax() {
		return habilitadoEstoqueMax;
	}
	
	/**
	 * Configuração da habilitação ou não para o cálculo do Estoque
	 * máximo
	 * @param habilitadoEstoqueMin Ativação ou não do cálculo
	 */
	public void setHabilitadoEstoqueMax (boolean habilitadoEstoqueMax) {
		this.habilitadoEstoqueMax = habilitadoEstoqueMax;
	}




	/**
	 * Cálculo da médida da demanda para o histórico do Item
	 * @return Demanda média recém calculada
	 */
	private int calculaDemandaMedia (){
		if (!historico.isEmpty()){
			float soma = 0.0f;
				
			for (int ind = 0; ind < historico.size(); ind++)
				soma += (float) historico.get(ind);
				
			//demandaNova = false;
			
			return Math.round(soma / historico.size());
		}
		
		return 0;
	}
	
	/**
	 * Cálculo do desvio padrão para o histórico do Item
	 * @return Valor do desvio padrão
	 */
	private int calculaDemandaDesvPad (){
		if (!historico.isEmpty()){
			float desvPad = 0.0f;
			float media = getDemandaMedia();
			
			for (int ind = 0; ind < historico.size(); ind++)
				desvPad += Math.pow(historico.get(ind) - media, 2);
			
			desvPad = (float) Math.sqrt(desvPad / (historico.size() - 1));
			
			//demandaNova = false;
			
			return Math.round(desvPad);
		}
		return 0;
	}
	
	/**
	 * Cálculo do nível mínimo de estoque
	 * @return Nível mínimo calculado
	 */
	private int calculaEstoqueMin() {
		float termo1 = getFatorSeguranca() * getDemandaDesvPad();
		double termo2 = Math.sqrt(getLeadTime() / getPeriodo());
		
		demandaNova = false;
		
		return Math.round(termo1 * (float) termo2);
	}
	
	/**
	 * Cálculo do nível máximo de estoque
	 * @return Nível mínimo calculado
	 */
	private int calculaEstoqueMax() {
		return ((getEstoqueMin() + getDemandaMedia()) * getLeadTime());
	}
	
	
	
	/**
	 * Configurando as configurações de controle para o default
	 */
	public void restaurar (){
		this.setFatorSeguranca (95f);
		this.setLeadTime (2);
		this.setPeriodo (1);
		
		this.setHabilitadoEstoqueMin (true);
		this.setHabilitadoEstoqueMax (true);
	}
	
	/**
	 * Interpretação inteligível do Item
	 * @return Interpretação
	 */
	public String toString (){
		return "O código '" + getCodigo() +
				"' está atribuído ao produto: " + getDescricao() + ";"
				+ " tendo como histórico: \n" + getHistorico().toString() + "\n";
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
