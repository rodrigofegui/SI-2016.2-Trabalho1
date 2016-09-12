/**
 * Classes necessárias
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Classe responsável pela implementação física de um estoque
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	07/09/2016
 */
public class Estoque {
	/**
	 * Itens registrados
	 * Itens que deverão ter o estoque mínimo calculado
	 * Itens que deverão ter o estoque máximo calculado
	 */
	private LinkedList<Item> itens;
	private LinkedList<Boolean> itensCalcEstoqueMin;
	private LinkedList<Boolean> itensCalcEstoqueMax;
	
	/**
	 * Construção de um Estoque por maneira default
	 */
	public Estoque(){
		this.itens = new LinkedList<Item>();
		this.itensCalcEstoqueMin = new LinkedList<Boolean>();
		this.itensCalcEstoqueMax = new LinkedList<Boolean>();
		
		System.out.println("Antes");
		System.out.println (toString());
		System.out.println("\n");
	}
	
	/**
	 * Construção de um Estoque, considerando um arquivo 
	 * com os dados do catálogo
	 * @param nomeArq Nome do arquivo do catálogo
	 */
	public Estoque (String nomeArq){
		this();
		
		catalogar(nomeArq);
		
		ordena();
	}
	
	/**
	 * Construção de um Estoque, considerando um arquivo 
	 * com os dados do catálogo e um do histórico
	 * @param nomeArqCat Nome do arquivo do catálogo
	 * @param nomeArqHist Nome do arquivo do histórico
	 */
	public Estoque (String nomeArqCat, String nomeArqHist){
		this(nomeArqCat);
		
		constroiHistorico (nomeArqHist);
		System.out.println (toString());
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
					Item item = new Item();
					
					int codigo = Integer.parseInt(partes[0]);
					String descricao = partes[1];
					int uni = Integer.parseInt(partes[2]);
					 
					item.setCodigo(codigo);
					item.setDescricao(descricao);
					item.addQntAtual(uni);
					   
					if ((codigo - 1) < itens.size())
						itens.add(item.getCodigo() - 1, item);
					else
						itens.add(item);
					
					itensCalcEstoqueMin.add(false);
					itensCalcEstoqueMax.add(false);
				}
			}
		     
			leitura.close();
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
