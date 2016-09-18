package controle;

/**
 * Classes necessárias
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

/**
 * Classe responsável pela implementação lógica de um estoque
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	07/09/2016
 */
public class Estoque {
	/**
	 * Itens registrados
	 */
	public LinkedList<Item> itens;
	/**
	 * nomeAbsArqListagem Diretório completo do arquivo base para a catalogação
	 * do estoque
	 * nomeAbsArqHistorico Diretório completo do arquivo base para o levantamento
	 * dos históricos dos itens em estoque 
	 */
	String nomeAbsArqListagem = "";
	String nomeAbsArqHistorico = "";
	
	
	
	/**
	 * Construção de um Estoque por maneira default
	 */
	public Estoque(){
		this.itens = new LinkedList<Item>();
	}
	
	/**
	 * Construção de um Estoque, considerando um arquivo 
	 * com os dados do catálogo
	 * @param nomeArq Nome do arquivo do catálogo
	 */
	public Estoque (String nomeArq){
		this();
		
		this.nomeAbsArqListagem = nomeArq;
		
		catalogar(nomeArq);
	}
	
	/**
	 * Construção de um Estoque, considerando um arquivo 
	 * com os dados do catálogo e um do histórico
	 * @param nomeArqCat Nome do arquivo do catálogo
	 * @param nomeArqHist Nome do arquivo do histórico
	 */
	public Estoque (String nomeArqCat, String nomeArqHist){
		this(nomeArqCat);
		
		this.nomeAbsArqHistorico = nomeArqHist;
		
		constroiHistorico (nomeArqHist);
	}

	/**
	 * Valor atribuído ao diretório do arquivo base para a catalogação
	 * @return Diretório completo do arquivo
	 */
	public String getNomeAbsArqListagem (){
		return this.nomeAbsArqListagem;
	}
	
	/**
	 * Atribuição do diretório completo do arquivo de catalogação
	 * @param nome Diretório completo do arquivo
	 */
	public void setNomeAbsArqListagem (String nome){
		this.nomeAbsArqListagem = nome;
	}
	
	/**
	 * Valor atribuído ao diretório do arquivo base para a construção
	 * dos históricos
	 * @return Diretório completo do arquivo
	 */
	public String getNomeAbsArqHistorico (){
		return this.nomeAbsArqHistorico;
	}
	
	/**
	 * Atribuição do diretório completo do arquivo de construção dos
	 * históricos
	 * @param nome Diretório completo do arquivo
	 */
	public void setNomeAbsArqHistorico (String nome){
		this.nomeAbsArqHistorico = nome;
	}
	
	/**
	 * Itens registrados em estoque
	 * @return Itens registrados, caso exista; ou "null" caso contrário
	 */
	public LinkedList<Item> getItens (){
		if (!itens.equals(null))
			return this.itens;
		
		return null;			
	}
	
	
	
	/**
	 * Catalogação dos itens que constam no estoque
	 * @param nomeArq Nome do arquivo que armazena os dados
	 */
	public void catalogar(String nomeArq) {
		try{
			BufferedReader leitura = new BufferedReader(new FileReader(nomeArq));
			String linha = leitura.readLine();
		
			while(leitura.ready()){
				linha = leitura.readLine();
				String[] partes = linha.split("\\s+");
				
				if (partes.length > 1){
					int codigo = Integer.parseInt(partes[0]);
					String descricao = partes[1];
					int unidade = Integer.parseInt(partes[2]);
					 
					addItem(codigo, descricao, unidade);
				}
			}
		     
			leitura.close();
			
			ordena();
			
			System.out.println("Catalogou e ordenou");
		}catch(IOException excessao){
			excessao.printStackTrace();
		}
	}
	
	/**
	 * Construção do histórico dos itens do estoque, conforme um
	 * arquivo
	 * @param nomeArq Nome do arquivo do Histórico
	 */
	public void constroiHistorico (String nomeArq){
		try{
			BufferedReader leitura = new BufferedReader(new FileReader(nomeArq));
			String linha = leitura.readLine();
	        Item item = new Item();
	         
	        while(leitura.ready()){
	        	linha = leitura.readLine();
	            String[] partes = linha.split("\\s+");
	            
	            if (partes.length > 1){
            		int codigo = Integer.parseInt(partes[0]);
		            int demanda = Integer.parseInt(partes[2]);

		            
		            if ((codigo - 1) < itens.size())
						item = itens.get(codigo - 1);
					else
						item = itens.getLast();
		            
		            if (item.getCodigo() == codigo)
		            	item.addHistorico(demanda);

            	}
        	}
	         
	        leitura.close();
      	}catch(IOException excessao){
      		excessao.printStackTrace();
	    }
	}
	
	/**
	 * Construção do histórico dos itens do estoque, conforme um
	 * arquivo e um período máximo de análise
	 * @param nomeArq Nome do arquivo do Histórico
	 * @param max_periodo Período máximo para a análise
	 */
	public void constroiHistorico (String nomeArq, int max_periodo){
		try{
			BufferedReader leitura = new BufferedReader(new FileReader(nomeArq));
			String linha = leitura.readLine();
	        Item item = new Item();
	        int periodo = 0;
	         
	        while(leitura.ready()){
	        	linha = leitura.readLine();
	            String[] partes = linha.split("\\s+");
	            
	            if (partes.length > 1){
            		int codigo = Integer.parseInt(partes[0]);
		            int demanda = Integer.parseInt(partes[2]);

		            if (periodo <= max_periodo){
			            if ((codigo - 1) < itens.size())
							item = itens.get(codigo - 1);
						else
							item = itens.getLast();
			            
			            if (item.getCodigo() == codigo){
			            	item.addHistorico(demanda);
			            	periodo++;
			            }
	            	}
            	}
        	}
	         
	        leitura.close();
      	}catch(IOException excessao){
      		excessao.printStackTrace();
	    }
	}

	/**
	 * Marcar todos os itens em estoque como hábeis para o cálculo
	 * do estoque mínimo
	 */
	public void marcarTodosEstoqueMin (){
		for (int ind = 0; ind < itens.size(); ind++){
			Item itemAvulso = itens.get(ind);
			
			itemAvulso.setHabilitadoEstoqueMin(true);
		}			
	}
	
	/**
	 * Marcar todos os itens em estoque como hábeis para o cálculo
	 * do estoque máximo
	 */
	public void marcarTodosEstoqueMax (){
		for (int ind = 0; ind < itens.size(); ind++){
			Item itemAvulso = itens.get(ind);
			
			itemAvulso.setHabilitadoEstoqueMax(true);
		}			
	}
	
	/**
	 * Desmarcar todos os itens em estoque como hábeis para o cálculo
	 * do estoque mínimo
	 */
	public void desmarcarTodosEstoqueMin (){
		for (int ind = 0; ind < itens.size(); ind++){
			Item itemAvulso = itens.get(ind);
			
			itemAvulso.setHabilitadoEstoqueMin(false);
		}			
	}
	
	/**
	 * Desmarcar todos os itens em estoque como hábeis para o cálculo
	 * do estoque máximo
	 */
	public void desmarcarTodosEstoqueMax (){
		for (int ind = 0; ind < itens.size(); ind++){
			Item itemAvulso = itens.get(ind);
			
			itemAvulso.setHabilitadoEstoqueMax(false);
		}			
	}
	
	/**
	 * Atribuição a todos os itens do estoque o mesmo período
	 * para o reabastecimento
	 * @param leadTime Tempo de reabastecimento a ser aplicado
	 */
	public void aplicarTodosLeadTime (int leadTime){
		for (int ind = 0; ind < itens.size(); ind++){
			Item itemAvulso = itens.get(ind);
			
			itemAvulso.setLeadTime(leadTime);
		}
	}
	
	/**
	 * Atribuição a todos os itens do estoque o mesmo período
	 * de consideração para o estoque
	 * @param periodo Tempo para consideração a ser aplicado
	 */
	public void aplicarTodosPeriodo (int periodo){
		for (int ind = 0; ind < itens.size(); ind++){
			Item itemAvulso = itens.get(ind);
			
			itemAvulso.setPeriodo(periodo);
		}
	}
	
	/**
	 * Atribuição a todos os itens do estoque o mesmo fator de
	 * segurança do estoque
	 * @param nivelSeguranca Nível de segurança a ser aplicado
	 */
	public void aplicarTodosFatorSeguranca (float nivelSeguranca){
		for (int ind = 0; ind < itens.size(); ind++){
			Item itemAvulso = itens.get(ind);
			
			itemAvulso.setFatorSeguranca(nivelSeguranca);
		}
	}
	
	/**
	 * Cálculo do estoque mínimo e máximo para todos os itens
	 */
	public void calcularEstoque (){
		for (Item item : itens){
			item.getEstoqueMin();
			item.getEstoqueMax();
		}
	}
	
	/**
	 * Restauração dos itens do Estoque às configurações iniciais
	 */
	public void restaurarTodos (){
		for (int ind = 0; ind < itens.size(); ind++){
			itens.get(ind).restaurar();
		}
		
		System.out.println("Restaurou todos\n");
	}
	
	/**
	 * Inserção de um novo elemento no Estoque
	 * @param codigo Código do Item
	 * @param descricao Descrição do Item
	 * @param unidade Quantidade atual do Item
	 */
	public void addItem (int codigo, String descricao, int unidade){
		Item item = new Item(codigo, descricao, unidade);
		   
		if ((codigo - 1) < itens.size())
			itens.add(item.getCodigo() - 1, item);
		else
			itens.add(item);
	}
	
	/**
	 * Ordenação do Estoque
	 */
	public void ordena (){
		Collections.sort(itens, new Comparator<Item>() {
		    @Override
		    public int compare (Item objetoUm, Item objetoDois) {
		        // Sua implementação de comparador aqui
		        return objetoUm.compareTo(objetoDois);
		    }
		});
	}
	
	/**
	 * Construção do relatório do constrole,
	 * disponibilizando para exibição da maneira escolhida
	 * @param data Data a ser incorporada ao relatório
	 * @return Relatório atualizado
	 */
	public String gerarRelatorio (String data){
		String relatorio = "";
		
		relatorio += "Relatório de situação do estoque\n\n";
		relatorio += "Data de expedição: " + data + "\n";
		relatorio += "Período de referência: " + itens.getFirst().getPeriodo() + "\n\n";
		relatorio += "--------------------------------------------------------------------------------\n";
		relatorio += "Código\tDescrição\tQnt Existente\tDemanda Tot.\tEstoque Min\t\tEstoque Max\n";
		relatorio += "--------------------------------------------------------------------------------\n";
		
		for (int ind = 0; ind < itens.size(); ind++){
			Item item = itens.get(ind);
			relatorio +=   item.getCodigo() + "\t\t"
						 + item.getDescricao() + "\t\t"
						 + item.getQntExistente() + "\t\t\t\t"
						 + item.getDemanda() + "\t\t\t\t"
						 + item.getEstoqueMin() + "\t\t\t\t"
						 + item.getEstoqueMax() + "\n";
		}
		
		relatorio += "--------------------------------------------------------------------------------\n";
		
		return relatorio;
	}
	
	/**
	 * Escrita do relatório num arquivo .txt
	 */
	public void escreverRelatorio (){
		try{			
			DateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd_HH:mm:ss");
			Date date = new Date();
			String data = dateFormat.format(date);
			
			String relatorio = gerarRelatorio (data);
			String caminho = "Relatórios/relatorio_" + data + ".txt";

			BufferedWriter gravarArq = new BufferedWriter(new FileWriter (caminho));
			
			gravarArq.append(relatorio + "\n");
			
			gravarArq.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Descritivo do estoque, com enfoque nos itens registrados
	 * @return Estoque convertido em string
	 */
	public String toString (){
		String retorno = "";
		
		if (itens.isEmpty())
			retorno += "Não há itens registrados!\n";
		else{
			if (itens.size() == 1)
				retorno += "Temos o seguinte item:\n";
			else
				retorno += "Temos os seguintes itens:\n";
			
			retorno += itens.toString();
		}
		
		
		
		return retorno;
	}
}
