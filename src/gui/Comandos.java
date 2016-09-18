package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

import controle.Item;

/**
 * Classe responsável pelo gerenciamento da comunicação entre
 * a interface gráfica e o núcleo da aplicação
 * @author Rodrigo Guimarães
 * @version	1.0
 * @since	13/09/2016
 */
public class Comandos {
	/**
	 * Seleção de arquivo .txt
	 * @return Arquivo selecionado
	 */
	public static File abrirArq (){
		JFileChooser seletorArquivo = new JFileChooser();
		
		seletorArquivo.setCurrentDirectory (new File("./ArquivosInicialização/"));
		
		seletorArquivo.addChoosableFileFilter(new FiltroTxt());
		seletorArquivo.setAcceptAllFileFilterUsed(true);
		seletorArquivo.setFileFilter(new FiltroTxt());
		
        int returnVal = seletorArquivo.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION){
        	return seletorArquivo.getSelectedFile();
        }
        
        return null;
	}
	
	/**
	 * Seleção de um arquivo .txt que sirva como catálogo
	 * de um estoque
	 */
	public static void abrirAqrListagem (){
		File arq = abrirArq();
		if(arq != null){
			String nomeArq = arq.getAbsolutePath();
			
			controle.ControladorEstoque.estoque.getItens().clear();
			controle.ControladorEstoque.estoque.setNomeAbsArqListagem(nomeArq);
			controle.ControladorEstoque.estoque.catalogar(nomeArq);
			
			atualizarTabelaItens ();
			
			nomeArq = arq.getName();
			InterfaceGrafica.nomeArqListagem.setText(nomeArq);
			
			System.out.println("Selecionou o arquivo" + nomeArq + "\n");
			
			inicializarBotoes ();
		
		}else
			System.out.println("Finalizada sem arquivo\n");
	}
	
	/**
	 * Seleção de um arquivo .txt que sirva como histórico
	 * dos itens de um estoque
	 */
	public static void abrirAqrHistorico (){
		File arq = abrirArq();
		if(arq != null){
			String nomeArq = arq.getAbsolutePath();
			
			controle.ControladorEstoque.estoque.setNomeAbsArqHistorico(nomeArq);
			controle.ControladorEstoque.estoque.constroiHistorico(nomeArq);
			
			nomeArq = arq.getName();
			
			InterfaceGrafica.nomeArqHistorico.setText(nomeArq);
			InterfaceGrafica.exibirHistorico.setEnabled(true);
			InterfaceGrafica.calcEstoque.setEnabled(true);
			
			System.out.println("Selecionou o arquivo" + nomeArq + "\n");
		
		}else
			System.out.println("Finalizada sem arquivo\n");
	}
	
	/**
	 * Restauração às configurações iniciais de todos os itens
	 * do estoque
	 */
	public static void restaurarItens (){
		controle.ControladorEstoque.estoque.restaurarTodos();
		
		atualizarTabelaItens ();
	}
	
	/**
	 * Atribuindo a todos o mesmo valor de LeadTime, o tempo de reabastecimento
	 * @param acao Registro da ação ocorrida, sendo 0 foi marcada positivamente
	 * @param LT Tempo de reabastecimento a ser atribuído
	 */
	public static void controleMarcarTodosLT (int acao, int LT){
		if (acao == 0)
			controle.ControladorEstoque.estoque.aplicarTodosLeadTime(LT);
	}
	
	/**
	 * Atribuindo a todos o mesmo valor do período a ser considerado o estoque
	 * @param acao Registro da ação ocorrida, sendo 0 foi marcada positivamente
	 * @param PP Tempo de uso do estoque
	 */
	public static void controleMarcarTodosPP (int acao, int PP){
		if (acao == 0){
			controle.ControladorEstoque.estoque.aplicarTodosPeriodo(PP);
			
			System.out.println("Aplicou a todos os mesmo período");
		}
	}
	
	/**
	 * Atribuindo a todos o mesmo valor do nível de segurança do controle
	 * de estoque
	 * @param acao Registro da ação ocorrida, sendo 0 foi marcada positivamente
	 * @param nivelSeguranca Nivel de segunrança a ser atribuído
	 */
	public static void controleMarcarTodosFS (int acao, float nivelSeguranca){
		if (acao == 0)
			controle.ControladorEstoque.estoque.aplicarTodosFatorSeguranca(nivelSeguranca);
	}
	
	/**
	 * Controle da ação de marcar ou desmarcar, sob a ótica do estoque mínimo
	 * @param escolha Decisão escolhida para o controle, onde:
	 * 0 equivale a marcar todos
	 * 1 equivale a desmarcar todos
	 */
	public static void controleMarcarTodosMin (int escolha){
		switch (escolha){
			case 0:
				controle.ControladorEstoque.estoque.marcarTodosEstoqueMin();
				
				for (int ind = 0; ind < InterfaceGrafica.tabelaDados.getRowCount(); ind++)
					InterfaceGrafica.tabelaDados.setValueAt(true, ind, 0);
				
				break;
			case 1:
				controle.ControladorEstoque.estoque.desmarcarTodosEstoqueMin();
				
				for (int ind = 0; ind < InterfaceGrafica.tabelaDados.getRowCount(); ind++)
					InterfaceGrafica.tabelaDados.setValueAt(false, ind, 0);
				
				break;
		}
		
		System.out.println("Operação finalizada.\n");
	}
	
	/**
	 * Controle da ação de marcar ou desmarcar, sob a ótica do estoque máximo
	 * @param escolha Decisão escolhida para o controle, onde:
	 * 0 equivale a marcar todos
	 * 1 equivale a desmarcar todos
	 */
	public static void controleMarcarTodosMax (int escolha){
		switch (escolha){
			case 0:
				controle.ControladorEstoque.estoque.marcarTodosEstoqueMax();
				
				for (int ind = 0; ind < InterfaceGrafica.tabelaDados.getRowCount(); ind++)
					InterfaceGrafica.tabelaDados.setValueAt(true, ind, 1);
				
				break;
			case 1:
				controle.ControladorEstoque.estoque.desmarcarTodosEstoqueMax();
				
				for (int ind = 0; ind < InterfaceGrafica.tabelaDados.getRowCount(); ind++)
					InterfaceGrafica.tabelaDados.setValueAt(false, ind, 1);
				
				break;
		}
		
		System.out.println("Operação finalizada.\n");
	}
	
	/**
	 * Controle da ação de calcular o valor de estoque, selecionando entre o 
	 * estoque mínimo e o máximo
	 */
	public static void calcularEstoque (){
		controle.ControladorEstoque.estoque.calcularEstoque();
		
		if (!InterfaceGrafica.nomeArqHistorico.getText().equals("<nome_arquivo_histórico>"))
			InterfaceGrafica.gerarRelatorio.setEnabled(true);
		
		System.out.println("Calculou os marcados");
	}
	
	/**
	 * Salvar a listagem dos itens do estoque modificaa
	 * @param caminho Arquivo que será atualizado
	 */
	public static void salvarListagem (String caminho){
		try{
			String caminhoTratado = tratarCaminho(caminho);
			BufferedWriter gravarArq = new BufferedWriter(new FileWriter (caminhoTratado));
			
			gravarArq.append("Codigo\tDescrição\tUnidades Existentes\n");
			
			for (int lin = 0; lin < InterfaceGrafica.tabelaDados.getRowCount(); lin++){
				for (int col = 2; col < InterfaceGrafica.tabelaDados.getColumnCount(); col++){
					gravarArq.append(InterfaceGrafica.tabelaDados.getValueAt(lin, col).toString());
					gravarArq.append("\t\t");
				}
				gravarArq.newLine();
			}
			
			gravarArq.close();
			
			System.out.println("Listagem salva\n");
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exibir janela extra, contendo o histórico
	 */
	public static void exibirHistorico() {
		InterfaceGrafica.getFrameHistorico();
		
		atualizarTabelaHistorico ();
	}

	/**
	 * Gerar o relatório do cálculo dos valores de estoque mínimo e máximo
	 */
	public static void gerarRelatorio (){
		controle.ControladorEstoque.estoque.escreverRelatorio();
		
		System.out.println("Relatório registrado no diretório padrão");
	}
	
	/**
	 * Atualização do conteúdo da tabela de itens, conforme a condição atual
	 * do estoque
	 */
	public static void atualizarTabelaItens (){
		int qntItens = controle.ControladorEstoque.estoque.itens.size();
		
		int lim = InterfaceGrafica.modeloTabelaDados.getRowCount();
		for (int i = 0; i < lim; i++)
			InterfaceGrafica.modeloTabelaDados.removeRow(0);
		
		for (int lin = 0; lin < qntItens; lin++){
			Item itemAtual = controle.ControladorEstoque.estoque.itens.get(lin);
			
			InterfaceGrafica.modeloTabelaDados.insertRow(lin, new Object[]{	itemAtual.isHabilitadoEstoqueMin(),
																			itemAtual.isHabilitadoEstoqueMax(), 
																			itemAtual.getCodigo(),
																			itemAtual.getDescricao(),
																			itemAtual.getHistorico().getFirst()}
														);
		}
	}
	
	/**
	 * Atualização do conteúdo da tabela de itens, conforme a condição atual
	 * do estoque
	 */
	public static void atualizarTabelaHistorico (){
		int qntItens = controle.ControladorEstoque.estoque.itens.size();
		
		InterfaceGrafica.tabelaDados2.removeAll();
		
		for (int lin = 0; lin < qntItens; lin++){
			Item itemAtual = controle.ControladorEstoque.estoque.itens.get(lin);
			
			for (int periodo = 0; periodo < itemAtual.getHistorico().size(); periodo++){
				int qnt = itemAtual.getHistorico().get(periodo);
				
				InterfaceGrafica.modeloTabelaDados2.addRow(new Object[]{itemAtual.getCodigo(),
																		itemAtual.getDescricao(),
																		periodo, qnt});
			}
			
			InterfaceGrafica.modeloTabelaDados2.addRow(new Object[]{null});
			
		}
	}

	/**
	 * Atualização do conteúdo do estoque, conforme o conteúdo da tabela
	 * @param lin Linha da tabela que foi modificada
	 * @param col Coluna da tabela que foi modificada
	 * @param alteracao Conteúdo que foi alterado
	 */
	public static void atualizarEstoque (int lin, int col, String alteracao){
		switch (col){
			case 0:
				controle.ControladorEstoque.estoque.getItens().get(lin).setHabilitadoEstoqueMin(Boolean.parseBoolean(alteracao));
				break;
				
			case 1:
				controle.ControladorEstoque.estoque.getItens().get(lin).setHabilitadoEstoqueMax(Boolean.parseBoolean(alteracao));
				break;
				
			case 2:
				alteracao = InterfaceGrafica.refinaInteiro(alteracao);
				
				controle.ControladorEstoque.estoque.getItens().get(lin).setCodigo(Integer.parseInt(alteracao));
				break;
				
			case 3:
				controle.ControladorEstoque.estoque.getItens().get(lin).setDescricao(alteracao);
				break;
				
			case 4:
				alteracao = InterfaceGrafica.refinaInteiro(alteracao);
				
				controle.ControladorEstoque.estoque.getItens().get(lin).setQntAtual(Integer.parseInt(alteracao));
				break;
		}
		
		InterfaceGrafica.salvarListagem.setEnabled(true);
	}
	
	/**
	 * Altera os status dos botões influenciados pelo arquivo base
	 * para a listagem dos itens em estoque
	 */
	private static void inicializarBotoes (){
		InterfaceGrafica.botaoAbrirHistorico.setEnabled(true);
		InterfaceGrafica.gerarRelatorio.setEnabled(false);
		InterfaceGrafica.markTodosMin.setEnabled(true);
		InterfaceGrafica.markTodosMax.setEnabled(true);
		InterfaceGrafica.desmarkTodosMin.setEnabled(true);
		InterfaceGrafica.desmarkTodosMax.setEnabled(true);
	}

	/**
	 * Tratamento do nome do arquivo a ser salvo em HD
	 * @param original Nome do arquivo original
	 * @return Nome do arquivo tratado
	 */
	public static String tratarCaminho (String original){
		String tratada = "";
		
		if(original.contains("_mod.txt")){
			System.out.println("primeiro caso");
			
			int primeiraPos = original.lastIndexOf("_mod") + 4;
			
			tratada = original.substring(0, primeiraPos) + "(1).txt";
			
		}else if(original.contains("_mod(")){
			System.out.println("segundo caso");
			int primeiraPos = original.lastIndexOf("_mod(") + 5;
			int ultimaPos = original.indexOf(").txt");
			int valor = Integer.parseInt(original.substring(primeiraPos, ultimaPos)) + 1;
			
			tratada = original.substring(0, primeiraPos);
			tratada += valor;
			tratada += ").txt";
			
		}else{
			System.out.println("terceiro caso");
			tratada = original.replace(".txt", "_mod.txt");
		}
		
		return tratada;
	}
}
