package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


/**
 * Classe responsável pelo gerenciamento da interface gráfica
 * @author Rodrigo Guimarães
 * @version	1.0
 * @since	11/09/2016
 */
public class InterfaceGrafica implements DocumentListener, MouseListener,
										ActionListener, ItemListener, PropertyChangeListener {
	static JFrame janelaPrincipal = null;
	static final int	comprimentoMaximo	= 1010;
	static final int	alturaMaxima		= 493;
	
	private  JPanel painelPrincipal = null;
		private  JPanel painelEsquerda = null;
			private JPanel painelAbrir = null;
				static JTextField nomeArqListagem = null;
				static JButton botaoAbrirListagem = null;
				static JTextField nomeArqHistorico = null;
				static JButton botaoAbrirHistorico = null;
			private JPanel painelConfiguracoes = null;
				static JTextField periodoLT = null;
				static JCheckBox marcarTodosLT = null;
				static JTextField periodoPP = null;
				static JCheckBox marcarTodosPP = null;
				static JTextField nivelSeguranca = null;
				static JCheckBox marcarTodosFS = null;
				static JButton restaurarTodos = null;
		private  JPanel painelDireita = null;
			private JPanel painelItensEstoque = null;
				static DefaultTableModel modeloTabelaDados = null;	
					private JTable tabelaDados = null;
			private JPanel painelManipulacao = null;
				static JButton salvarListagem = null;
				static JButton exibirHistorico = null;
				static JButton gerarRelatorio = null;
				static JButton markTodosMin = null;
				static JButton desmarkTodosMin = null;
				static JButton calcMin = null;
				static JButton markTodosMax = null;
				static JButton desmarkTodosMax = null;
				static JButton calcMax = null;
	/**
	 * Itens descartáveis, mas utilizados por muitos
	 */
	private LinkedList<Object> lista;
	private JPanel painelAvulso;
	private JButton botaoAvulso;
	private Dimension dimensaoAvulsa;
	
	
	/**********************************************************
	 * Construtores
	 **********************************************************/
	/**
	 * Construção de uma Interface Gráfica por maneira default
	 */
	public InterfaceGrafica(){
		FormatacaoPadrao.formatarGui();
		
		janelaPrincipal = new JFrame("Controlador de Estoque");
		
		janelaPrincipal.setContentPane (getPainelPrincipal());
		
		janelaPrincipal.setSize (new Dimension (comprimentoMaximo, alturaMaxima));
		
		janelaPrincipal.setMinimumSize (new Dimension (comprimentoMaximo, alturaMaxima));
		
		janelaPrincipal.setResizable (false);
		
		janelaPrincipal.setVisible (true);
		
		janelaPrincipal.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		/*
		janelaPrincipal.setResizable (true);
		for (int i = 0; ;i++)
			System.out.println("" + janelaPrincipal.getSize());
		//*/
	}
	
	
	/**********************************************************
	 * Aquisição de Painel
	 **********************************************************/
	/**
	 * Estrutura construída no painel principal da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelPrincipal(){
		painelPrincipal = criarPainel(1, 10, comprimentoMaximo, alturaMaxima);
		
		painelPrincipal = montarPainelPrincipal (painelPrincipal);
		
		return painelPrincipal;
	}
	
	/**
	 * Estrutura construía no painel Esquerda da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelEsquerda (){
		painelEsquerda = criarPainel(2, 0, 250, alturaMaxima);

		painelEsquerda = montarPainelEsquerdo (painelEsquerda);
		
		return painelEsquerda;
	}
	
	/**
	 * Estrutura construída no painel abrir da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelAbrir (){
		painelAbrir = criarPainel(2, 1, 1, 2, 250, 120,
									" Abrir Arquivos: ", "Arquivos necessários para a construção do estoque");

		painelAbrir = montarPainelAberturas (painelAbrir);

		return painelAbrir;
	}
	
	/**
	 * Estrutura construída no painel configurações da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelConfiguracoesGerais (){
		painelConfiguracoes = criarPainel(4, 1, 1, 2, 250, 180,
											" Configurações Globais: ", "Configurações habilitadas a atingir todos os itens em estoque");
		
		painelConfiguracoes = montarPainelConfiguracao (painelConfiguracoes);
			
		return painelConfiguracoes;
	}
	
	/**
	 * Estrutura construída no painel diretira da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelDireita (){
		painelDireita = criarPainel (1, 740, alturaMaxima, " Estoque: ",
									"Informações e configurações que afetam o estoque");
		
		painelDireita = montarPainelDireito (painelDireita);
		
		return painelDireita;
	}
	
	/**
	 * Estrutura construída no painel itens no estoque da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelItensEstoque (){
		painelItensEstoque = criarPainel (1, 1, 480, alturaMaxima, " Itens em estoque: ", "Descritivo básico dos itens em estoque");
				
		painelItensEstoque = montarPainelItens (painelItensEstoque);
		
		return painelItensEstoque;
	}
	
	/**
	 * Estrutura construída no painel manipulação da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelManipulacao (){
		painelManipulacao = criarPainel (2, 250, alturaMaxima, " Configurações: ", "Configurações pertinentes ao estoque");
		
		painelManipulacao = montarPainelManipulacao (painelManipulacao);
		
		return painelManipulacao;
	}

	
	/**********************************************************
	 * Montagem de painel
	 **********************************************************/
	/**
	 * Construção de um painel principal da interface
	 * @param painelBase Painel antes da inserção
	 * @return Painel construído
	 */
	private JPanel montarPainelPrincipal (JPanel painelBase){
		painelBase.add(getPainelEsquerda());
		
		painelBase.add(getPainelDireita());
		
		return painelBase;
	}
	
	/**
	 * Construção de um painel esquerdo da interface
	 * @param painelBase Painel antes da inserção
	 * @return Painel construído
	 */
	private JPanel montarPainelEsquerdo (JPanel painelBase){
		painelBase.add(getPainelAbrir());

		painelBase.add(getPainelConfiguracoesGerais());
		
		return painelBase;
	}
	
	/**
	 * Construção de um painel responsável pelas aberturas externas
	 * @param painelBase Painel antes da inserção
	 * @return Painel construído
	 */
	private JPanel montarPainelAberturas (JPanel painelBase){
		lista = criarPainelAberturaArq ("Listagem:", "Arquivo base para a catalogação dos itens em estoque",
								"nomeArqListagem", "<nome_arquivol_listagem>",
								"abrirListagem", "Abrir", "Busca do arquivo base para a catalogação dos itens em estoque");
		
		nomeArqListagem = (JTextField) lista.get(1);
		botaoAbrirListagem = (JButton) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();

		lista = criarPainelAberturaArq ("Histórico:", "Arquivo base para o levantamento do histórico dos itens em estoque",
								"nomeArqHistorico", "<nome_arquivo_histórico>",
								"abrirHistorico", "Abrir", "Busca do arquivo base para o levantamento do histórico dos itens em estoque");

		nomeArqHistorico = (JTextField) lista.get(1);
		botaoAbrirHistorico = (JButton) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		return painelBase;
	}
	
	/**
	 * Construção de um painel responsável pelas configurações
	 * que afetam a todos o estoque
	 * @param painelBase Painel antes da inserção
	 * @return Painel construído
	 */
	private JPanel montarPainelConfiguracao (JPanel painelBase){
		lista = criarPainelItensConf("Lead Time:", "Tempo gasto para o reabastecimento de um dado item",
								"periodoLT", "2");

		periodoLT = (JTextField) lista.get(1);
		marcarTodosLT = (JCheckBox) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelItensConf("Período:", "Tempo a ser considerado no cálculo do estoque",
								"periodoPP", "1");
		
		periodoPP = (JTextField) lista.get(1);
		marcarTodosPP = (JCheckBox) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelItensConf("Fator Segurança:", "Nível de segurança sobre o suprimento de estoque, em %",
								"nivelS", "95");
		
		nivelSeguranca = (JTextField) lista.get(1);
		marcarTodosFS = (JCheckBox) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelBotao("restaurarTodos", "Restaurar itens",
									"Restaurar todos os itens às configurações default", true);
		
		restaurarTodos = (JButton) lista.get(1);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		return painelBase;
	}
	
	/**
	 * Construção de um painel responsável pelas operações do 
	 * estoque
	 * @param painelBase Painel antes da inserção
	 * @return Painel construído
	 */
	private JPanel montarPainelDireito (JPanel painelBase){
		painelBase.add(getPainelItensEstoque());
		
		painelBase.add(getPainelManipulacao());
		
		return painelBase;
	}
	
	/**
	 * Construção de um painel responsável pela demonstração
	 * dos itens em estoque
	 * @param painelBase Painel antes da inserção
	 * @return Painel construído
	 */
	private JPanel montarPainelItens (JPanel painelBase){
		String[][] dados = {null}; 
		String cabecalho[] = {"Estoque Min", "Estoque Máx",
							  "Código", "Descrição",
							  "Qnt."};
		int larg[] = {90, 90, 60, 150, 60};
		
		lista = criarPainelTabela (dados, cabecalho, larg);
		
		modeloTabelaDados = (DefaultTableModel) lista.get(1);
		tabelaDados = (JTable) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		modeloTabelaDados.insertRow(0, new Object[]{false, false, 1,2,3});
		
		return painelBase;
	}
	
	/**
	 * Construção de um painel responsável pela manipulação
	 * do estoque
	 * @param painelBase Painel antes da inserção
	 * @return Painel construído
	 */
	private JPanel montarPainelManipulacao (JPanel painelBase){
		lista = criarPainelBotao("salvarListagem", "Salvar", "Salvar alteração do estoque", false);

		salvarListagem = (JButton) lista.get(1);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();

		lista = criarPainelBotao("exibirHistórico", "Histórico", "Exibir histórico completo dos itens", true);

		exibirHistorico = (JButton) lista.get(1);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelBotao("gerarRelatorio", "Gerar Relatorio", "Gerar relatório com os itens marcados", true);

		gerarRelatorio = (JButton) lista.get(1);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		painelBase.add(criarEspacoVazio (250, 50));
		
		lista = criarPainelEstoque ("Estoque Min:", "Operações básicas sobre o estoque mínimo",
									"markTodosMin", "desmarkTodosMin", "calcMin");
		
		markTodosMin = (JButton) lista.get(1);
		desmarkTodosMin = (JButton) lista.get(2);
		desmarkTodosMin = (JButton) lista.get(3);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelEstoque ("Estoque Max:", "Operações básicas sobre o estoque máximo",
									"markTodosMax", "desmarkTodosMax", "calcMax");

		markTodosMax = (JButton) lista.get(1);
		desmarkTodosMax = (JButton) lista.get(2);
		desmarkTodosMax = (JButton) lista.get(3);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		//painelBase.add(criarEspacoVazio (250, 120));
		
		return painelBase;
	}
	
	
	/**********************************************************
	 * Criação de componente ou conjunto de componentes
	 **********************************************************/
	/**
	 * Criação de painel para conexão de componentes
	 * @return Painel criado
	 */
	public JPanel criarPainel (){
		painelAvulso = new JPanel ();
		        
        return painelAvulso;
	}
	
	/**
	 * Criação de painel para conexão de componentes
	 * @param layout Escolha do layout aplicado ao painel, onde:
	 * 0 equivale a {@link FlowLayout}
	 * 1 equivale a {@link BoxLayout} em X
	 * 2 equivale a {@link BoxLayout} em Y
	 * @return Painel criado
	 */
	public JPanel criarPainel (int layout){
		painelAvulso = criarPainel();
				
		switch (layout){
			case 0:
				painelAvulso.setLayout (new FlowLayout());
				break;
			case 1:
				painelAvulso.setLayout (new BoxLayout(painelAvulso, BoxLayout.X_AXIS));
				break;
			case 2:
				painelAvulso.setLayout (new BoxLayout(painelAvulso, BoxLayout.Y_AXIS));
		}
		        
        return painelAvulso;
	}
	
	/**
	 * Criação de painel para conexão de componentes
	 * @param lin Quantidade de linhas no {@link GridLayout}
	 * @param col Quantidade de colunas no {@link GridLayout}
	 * @return Painel criado
	 */
	public JPanel criarPainel (int lin, int col){
		painelAvulso = criarPainel();
		
		painelAvulso.setLayout (new GridLayout(lin, col));
        
        return painelAvulso;
	}
	
	/**
	 * Criação de painel para conexão de componentes
	 * @param layout Escolha do layout aplicado ao painel, onde:
	 * 0 equivale a {@link FlowLayout}
	 * 1 equivale a {@link BoxLayout} em X
	 * 2 equivale a {@link BoxLayout} em Y
	 * @param borda Definição da dimensão da borda, considerando uma
	 * borda homogênea numa caixa vazia
	 * @param larg Largura do painel
	 * @param alt Altura do painel
	 * @return Painel criado
	 */
	public JPanel criarPainel (int layout, int borda, int larg, int alt){
		painelAvulso = criarPainel(layout);
		
		painelAvulso = (JPanel) setDimensao(painelAvulso, larg, alt);
		
		painelAvulso.setOpaque (true);
        
        Border caixaPrincipal = BorderFactory.createEmptyBorder (borda, borda, borda, borda);
        painelAvulso.setBorder (caixaPrincipal);
        
        return painelAvulso;
	}
	
	/**
	 * Criação de painel para conexão de componentes
	 * @param layout Escolha do layout aplicado ao painel, onde:
	 * 0 equivale a {@link FlowLayout}
	 * 1 equivale a {@link BoxLayout} em X
	 * 2 equivale a {@link BoxLayout} em Y
	 * @param larg Largura do painel
	 * @param alt Altura do painel
	 * @param titulo Título a ser posto na borda
	 * @param mensagem Mensagem com indicações das tarefas da região
	 * @return Painel criado
	 */
	public JPanel criarPainel (int layout, int larg, int alt, String titulo, String mensagem){
		painelAvulso = new JPanel ();
		
		switch (layout){
			case 0:
				painelAvulso.setLayout (new FlowLayout());
				break;
			case 1:
				painelAvulso.setLayout (new BoxLayout(painelAvulso, BoxLayout.X_AXIS));
				break;
			case 2:
				painelAvulso.setLayout (new BoxLayout(painelAvulso, BoxLayout.Y_AXIS));
		}
		
		painelAvulso = (JPanel) setDimensao(painelAvulso, larg, alt);
		
		painelAvulso.setOpaque (true);
        
		painelAvulso.setBorder (BorderFactory.createTitledBorder(titulo));
        painelAvulso.setToolTipText(mensagem);
        
        return painelAvulso;
	}
	
	/**
	 * Criação de painel para conexão de componentes
	 * @param lin Quantidade de linhas no {@link GridLayout}
	 * @param col Quantidade de colunas no {@link GridLayout}
	 * @param larg Largura do painel
	 * @param alt Altura do painel
	 * @param titulo Título a ser posto na borda
	 * @param mensagem Mensagem com indicações das tarefas da região
	 * @return Painel criado
	 */
	private JPanel criarPainel (int lin, int col, int larg, int alt, String titulo, String mensagem){
		painelAvulso = criarPainel(lin, col);
				
		painelAvulso = (JPanel) setDimensao(painelAvulso, larg, alt);
		
		painelAvulso.setOpaque (true);
        
		painelAvulso.setBorder (BorderFactory.createTitledBorder(titulo));
        painelAvulso.setToolTipText(mensagem);
        
        return painelAvulso;
	}
	
	/**
	 * Criação de painel para conexão de componentes
	 * @param lin Quantidade de linhas no {@link GridLayout}
	 * @param col Quantidade de colunas no {@link GridLayout}
	 * @param hgap Lacuna na horizontal, característica do {@link GridLayout}
	 * @param vgap Lacuna na vertical, característica do {@link GridLayout}
	 * @param larg Largura do painel
	 * @param alt Altura do painel
	 * @param titulo Título a ser posto na borda
	 * @param mensagem Mensagem com indicações das tarefas da região
	 * @return Painel criado
	 */
	private JPanel criarPainel (int lin, int col, int hgap, int vgap, int larg, int alt, String titulo, String mensagem){
		painelAvulso = new JPanel ();
		
		painelAvulso.setLayout (new GridLayout(lin, col));
				
		painelAvulso = (JPanel) setDimensao(painelAvulso, larg, alt);
		
		painelAvulso.setOpaque (true);
        
		painelAvulso.setBorder (BorderFactory.createTitledBorder(titulo));
        painelAvulso.setToolTipText(mensagem);
        
        return painelAvulso;
	}
		
	/**
	 * Construção padrão para o conjunto responsável pela abertura do arquivo
	 * @param descricaoID Descrição do item
	 * @param mensagemID Mensagem explicativa o item
	 * @param nomeTF Identificador do JTextField correspondente
	 * @param textoTF Texto inserido no JTextField correspondente
	 * @param nomeBT Identificador do JButton correspondente
	 * @param textoBT Texto inserido no JButton correspondente
	 * @param mensagemBT Mensagem explicativa do JButton
	 * @param tfAplicado JTextField correspondente
	 * @param btAplicado JButton correspondente
	 * @return Lista com os itens construídos
	 */
	private LinkedList<Object> criarPainelAberturaArq (String descricaoID, String mensagemID,
										String nomeTF, String textoTF,
										String nomeBT, String textoBT, String mensagemBT){
		JPanel painel = criarPainel (2, 1);
		
		JPanel painelAvulso2 = criarPainel(1);
		
		JTextField tfAplicado = criarTextField (nomeTF, textoTF, false);
		tfAplicado.setEditable(false);
		
		JButton btAplicado = criarBotao (nomeBT, textoBT, mensagemBT);
				
		painelAvulso2.add(tfAplicado);
		painelAvulso2.add(btAplicado);
		
		botaoAvulso = criarIdentificacao (descricaoID, mensagemID);
		
		painel.add(botaoAvulso);
		painel.add(painelAvulso2);
		
		LinkedList<Object> lista = new LinkedList<Object>();
		
		lista.addFirst(painel);
		lista.add(tfAplicado);
		lista.add(btAplicado);
		
		return lista;
	}
	
	/**
	 * Criação do padrão de itens, habilitando a edição e uma aplicação
	 * a todos
	 * @param descricaoID Identificação do item
	 * @param mensagemID Mensagem que auxilia no entendimento da
	 * semântica do componente
	 * @param nomeTF Nome identificador a ser atribuído ao campo de texto
	 * @param textoTF Texto iniciado de maneira default
	 * @return Lista com os itens construídos
	 */
	private LinkedList<Object> criarPainelItensConf (String descricaoID, String mensagemID,
														String nomeTF, String textoTF){
		JPanel painel = criarPainel(2, 1);
		JPanel painelAvulso2 = criarPainel(1);
		
		botaoAvulso = criarIdentificacao (descricaoID, mensagemID, 0);

		JTextField tfAplicado = criarTextField (nomeTF, textoTF, true);
		
		painelAvulso2.add(botaoAvulso);
		painelAvulso2.add(tfAplicado);
		painel.add(painelAvulso2);
		
		painelAvulso2 = criarPainel(1);
		
		JCheckBox cbAplicado = criarCheckBox();
		
		painelAvulso2.add(criarEspacoVazio (115, 50));
		painelAvulso2.add(cbAplicado);
		painelAvulso2.add(criarAplicarTodos());
		painel.add(painelAvulso2);
		
		LinkedList<Object> lista = new LinkedList<Object>();
		lista.add(painel);
		lista.add(tfAplicado);
		lista.add(cbAplicado);
		
		return lista;
	}
		
	/**
	 * Criação de um painel exclusivo para um botão
	 * @param nomeBT Identificação do botão no tratamento de ações
	 * @param textoBT Texto a ser posto como inicial
	 * @param mensagemBT Mensagem que auxilia no entendimento da
	 * semântica do componente
	 * @param habilitar Controle da utilização do botão, permitindo o click do
	 * mouse ou não
	 * @param btAplicado Botão a ser inserido no painel
	 * @return Painel criado
	 */
	private LinkedList<Object> criarPainelBotao (String nomeBT, String textoBT,
													String mensagemBT, boolean habilitar){
		LinkedList<Object> lista = new LinkedList<Object>();
		painelAvulso = criarPainel();
		
		painelAvulso = (JPanel) setDimensao (painelAvulso, 250, 30);
		
		JButton btAplicado = criarBotao(nomeBT, textoBT, mensagemBT, habilitar);
		
		painelAvulso.add(btAplicado);
		
		lista.add(painelAvulso);
		lista.add(btAplicado);
		
		return lista;
	}
	
	/**
	 * Criação de uma tabela padrao
	 * @param dadosTB Dados a serem inseridos, inicialmente, na tabela
	 * @param cabecalhoTB Identificador dos dados inseridos
	 * @param largCol Largura a ser atribuída às colunas da tabela
	 * @return Tabela construída
	 */
	private LinkedList<Object> criarPainelTabela (String[][] dadosTB, String []cabecalhoTB, int[] largCol){
		painelAvulso = criarPainel();
		
		DefaultTableModel modeloTB = criarModeloTabela (dadosTB, cabecalhoTB);
		JTable tabela = new JTable(modeloTB);
		JScrollPane planoRolante = new JScrollPane(tabela);
		
		tabela = setLargTabela(tabela, largCol);
		
		tabela.getTableHeader().setReorderingAllowed(false);
		
		tabela.addPropertyChangeListener(this);
		
		painelAvulso.add(planoRolante);
		
		LinkedList<Object> lista = new LinkedList<Object>();
		
		lista.add(painelAvulso);
		lista.add(modeloTB);
		lista.add(tabela);
		
		return lista;
	}
	
	private LinkedList<Object> criarPainelEstoque (String descricao, String mensagemAjuda,
													String markID, String desmarkID, String calcID){
		LinkedList<Object> listaInterna = new LinkedList<Object>();
		JPanel painelAvulsoInterno = criarPainel (4, 1);

		painelAvulsoInterno = (JPanel) setDimensao (painelAvulsoInterno, 250, 125);
		
		botaoAvulso = criarIdentificacao (descricao, mensagemAjuda);
		painelAvulsoInterno.add(botaoAvulso);
		
		lista = criarPainelBotao(markID, "Marcar todos", "Seleciona todos os itens para o cálculo", true);
		
		listaInterna.add(lista.get(1));
		painelAvulsoInterno.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelBotao(desmarkID, "Desmarcar todos", "Libera todos os itens do cálculo", true);
		
		listaInterna.add(lista.get(1));
		painelAvulsoInterno.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelBotao(calcID, "Calcular", "Cálculo com todos os itens selecionados", true);
		
		listaInterna.add(lista.get(1));
		painelAvulsoInterno.add((JPanel) lista.getFirst());
		lista.clear();
		
		listaInterna.addFirst(painelAvulsoInterno);		
		
		return listaInterna;
	}
	
	/**
	 * Criação de identificação, textos, a serem inseridas na área
	 * gráfica
	 * @param texto	Texto a ser posto como inicial 
	 * @return 			O texto criado
	 */
	private JButton criarIdentificacao (String texto){
		botaoAvulso = new JButton();
		
		botaoAvulso.setText(texto);
		
		botaoAvulso.setHorizontalAlignment(JButton.CENTER);
				
		botaoAvulso.setBorderPainted(false);
		botaoAvulso.setContentAreaFilled(false);
		
		botaoAvulso = (JButton) setDimensao(botaoAvulso, 120, 50);
			
		return botaoAvulso;
	}
	
	/**
	 * Criação de identificação, textos, a serem inseridas na área
	 * gráfica
	 * @param texto	Texto a ser posto como inicial
	 * @param mensagemAjuda Mensagem que auxilia no entendimento da
	 * semântica do componente
	 * @return 			O texto criado
	 */
	private JButton criarIdentificacao (String texto, String mensagemAjuda){
		botaoAvulso = criarIdentificacao(texto);
		
		botaoAvulso.setToolTipText(mensagemAjuda);
			
		return botaoAvulso;
	}
	
	/**
	 * Criação de identificação, textos, a serem inseridas na área
	 * gráfica
	 * @param texto	Texto a ser posto como inicial
	 * @param mensagemAjuda Mensagem que auxilia no entendimento da
	 * semântica do componente
	 * @param alinhamento Escolha do alinhamento do texto, onde:
	 * 0 equivale a Left
	 * 1 equivale a Center
	 * 2 equivale a Right
	 * @return 			O texto criado
	 */
	private JButton criarIdentificacao (String texto, String mensagemAjuda, int alinhamento){
		botaoAvulso = criarIdentificacao(texto, mensagemAjuda);
		
		switch (alinhamento){
			case 0:
				botaoAvulso.setHorizontalAlignment(JButton.LEFT);
				break;
			case 1:
				botaoAvulso.setHorizontalAlignment(JButton.CENTER);
				break;
			case 2:
				botaoAvulso.setHorizontalAlignment(JButton.RIGHT);
				break;
		}
			
		return botaoAvulso;
	}
	
	/**
	 * Criação de um botão padrão
	 * @return Botao já criado
	 */
	public JButton criarBotao (){
		JButton novo = new JButton();
		
		return novo;
	}
	
	/**
	 * Criação de um botão padrão
	 * @param descricao Identificação do botao no tratamento de ações
	 * @return			Botao já criado
	 */
	public JButton criarBotao (String descricao){
		JButton novo = criarBotao();
		
		novo.setActionCommand(descricao);
		novo.addActionListener(this);
		
		return novo;
	}
	
	/**
	 * Criação de um botão padrão
	 * @param descricao Identificação do botao no tratamento de ações
	 * @param texto		Texto a ser posto como inicial
	 * @return			Botao já criado
	 */
	public JButton criarBotao (String descricao, String texto){
		JButton novo = criarBotao(descricao);
		
		novo.setText(texto);
		
		return novo;
	}
	
	/**
	 * Criação de um botão padrão
	 * @param descricao Identificação do botao no tratamento de ações
	 * @param texto		Texto a ser posto como inicial
	 * @param mensagemAjuda Mensagem que auxilia no entendimento da
	 * semântica do componente
	 * @return			Botao já criado
	 */
	public JButton criarBotao (String descricao, String texto, String mensagemAjuda){
		JButton novo = criarBotao(descricao, texto);
		
		novo.setToolTipText(mensagemAjuda);
		
		return novo;
	}
	
	/**
	 * Criação de um botão padrão
	 * @param descricao Identificação do botao no tratamento de ações
	 * @param texto		Texto a ser posto como inicial
	 * @param habilitar Controle da utilização do botão, permitindo o click do
	 * mouse ou não
	 * @return			Botao já criado
	 */
	public JButton criarBotao (String descricao, String texto, boolean habilitar){
		JButton novo = criarBotao(descricao, texto);
		
		novo.setEnabled(habilitar);
		
		return novo;
	}
	
	/**
	 * Criação de um botão padrão
	 * @param descricao Identificação do botão no tratamento de ações
	 * @param texto		Texto a ser posto como inicial
	 * @param mensagemAjuda Mensagem que auxilia no entendimento da
	 * semântica do componente
	 *  @param habilitar Controle da utilização do botão, permitindo o click do
	 * mouse ou não
	 * @return			Botao já criado
	 */
	public JButton criarBotao (String descricao, String texto, String mensagemAjuda, boolean habilitar){
		JButton novo = criarBotao(descricao, texto, mensagemAjuda);
		
		novo.setEnabled(habilitar);
		
		return novo;
	}
	
	/**
	 * Criação de um espaço vazio nas dimensões desejadas
	 * @param largura Largura do espaço
	 * @param altura Altura do espaço
	 * @return Espaço vazio construído
	 */
	private Component criarEspacoVazio (int largura, int altura) {
		Component caixa = Box.createRigidArea(new Dimension(largura, altura));
		
		return caixa;
	}
	
	/**
	 * Criação de um modelo de tabela, configurando os dois primeiros
	 * campos como checkBox
	 * @param dados Dados a serem inicializados
	 * @param cabecalho Cabeçalho dos dados 
	 * @return Modelo de tabela criado
	 */
	private DefaultTableModel criarModeloTabela (String[][] dados, String []cabecalho){
		DefaultTableModel modelo = new DefaultTableModel (dados, cabecalho){
			private static final long serialVersionUID = 1L;

			@Override
		    public Class<?> getColumnClass(int column) {
				if (column < 2)
					return Boolean.class;
				else
					return super.getColumnClass(column);
			}
		};
		
		return modelo;
	}
		
	/**
	 * Criação de um campo para texto padrão 
	 * @return Campo de texto criado
	 */
	private JTextField criarTextField (){
		JTextField novo = new JTextField();
		
		return novo;
	}
	
	/**
	 * Criação de um campo para texto padrão
	 * @param nome Nome a ser atribuído ao campo
	 * @return Campo de texto criado
	 */
	private JTextField criarTextField (String nome){
		JTextField novo = criarTextField();
		
		novo.setName(nome);
		
		return novo;
	}
	
	/**
	 * Criação de um campo para texto padrão
	 * @param nome Nome a ser atribuído ao campo
	 * @param textoInicial Texto inicial a ser inserido no campo
	 * @return Campo de texto criado
	 */
	private JTextField criarTextField (String nome, String textoInicial){
		JTextField novo = criarTextField(nome);
		
		novo.setText(textoInicial);
		
		return novo;
	}
	
	/**
	 * Criação de uma área de texto padrão
	 * @param nome			Nome identificador a ser atribuído
	 * @param textoInicial	Texto iniciado de maneira defautl
	 * @param executavel	Sinaliza se será construído uma área de texto executável ou
	 * não
	 * @return				Área de texto construída
	 */
	private JTextField criarTextField (String nome, String textoInicial, boolean executavel){
		JTextField novo = criarTextField(nome, textoInicial);
		int larg, alt;
		
		if (executavel){
			novo.getDocument().addDocumentListener(this);
			larg = 60;
			alt = 20;
		}else{
			novo.addMouseListener(this);
			larg = 179;
			alt = 20;
		}
		
		novo = (JTextField) setDimensao(novo, larg, alt);
				
		return novo;
	}
	
	/**
	 * Criação de uma caixa de marcação padrão
	 * @return	Caixa construída
	 */
	private JCheckBox criarCheckBox (){
		JCheckBox novo = new JCheckBox ();
		
		novo.setSelected(false);
		
		novo.setEnabled(true);
		
		novo.addItemListener(this);
		
		return novo;
	}
	
	/**
	 * Criação da identificação do "Aplicar a todos" de maneira padrão
	 * @return Identificação padrão
	 */
	private JButton criarAplicarTodos (){
		botaoAvulso = criarIdentificacao ("Aplicar a todos");
		botaoAvulso.setHorizontalAlignment(JButton.LEFT);
		botaoAvulso.setToolTipText("Aplicar o padrão a todos os itens de estoque");
		
		return botaoAvulso;
	}
	
	
	/**********************************************************
	 * Configuração Geral
	 **********************************************************/
	/**
	 * Configuração da dimensão de um painel
	 * @param painel Painel a ser dimensionado
	 * @param larg Largura do painel
	 * @param alt Altura do painel
	 * @return Painel dimensionado
	 */
	private Component setDimensao (Component alvo, int larg, int alt){
		dimensaoAvulsa = new Dimension(larg, alt);
		
		alvo.setMaximumSize(dimensaoAvulsa);
		alvo.setMinimumSize(dimensaoAvulsa);
		alvo.setPreferredSize(dimensaoAvulsa);
		
		return alvo;
	}
	
	/**
	 * Configuração do largura das colunas da tabela
	 * @param tabela Tabela a ser configurada
	 * @param tamanho Larguras a serem atribuídas
	 * @return Tabela configurada
	 */
	private JTable setLargTabela (JTable tabela, int[] tamanho){
		for (int col = 0; col < tamanho.length; col++){
			tabela.getColumnModel().getColumn(col).setMaxWidth(tamanho[col]);
			tabela.getColumnModel().getColumn(col).setMinWidth(tamanho[col]);
			tabela.getColumnModel().getColumn(col).setPreferredWidth(tamanho[col]);
			tabela.getColumnModel().getColumn(col).setResizable(false);
		}
		
		return tabela;
	}
	

	/**********************************************************
	 * Controle de Ações na GUI
	 **********************************************************/
	/**
	 * Controle das ações dos ambientes gráficos, para
	 * a inserção de caracteres nos campos de texto
	 * @param evento Evento registrado
	 */
	public void insertUpdate(DocumentEvent evento) {
		atualizacaoTexto (evento);
	}

	/**
	 * Controle das ações dos ambientes gráficos, para
	 * a remoção de caracteres nos campos de texto
	 * @param evento Evento registrado
	 */
	public void removeUpdate(DocumentEvent evento) {
		atualizacaoTexto (evento);
	}

	/**
	 * Dispensável para este trabalho, mas obrigatório
	 * de declarar
	 */
	public void changedUpdate(DocumentEvent e) {
	}
	
	/**
	 * Controle das acoes dos ambientes graficos, para
	 * os campos de texto
	 * @param event Evento registrado
	 */
	private void atualizacaoTexto (DocumentEvent evento){
		/*
		Object fonte = evento.getDocument().getProperty("owner");
		
		if (fonte.equals(valorGanhoInst)){
			ControleComandos.configurarGanhoInstrumento ();
			
		}
		//*/
	}

	/**
	 * Controle das ações dos ambientes gráficos ao
	 * primeiro click do mouse sobre o mesmmo
	 * @param evento Evento registrado
	 */
	public void mouseClicked(MouseEvent evento) {
		//ControleComandos.configurarClickSalvar ();		
	}

	/**
	 * Dispensável para este trabalho, mas obrigatório
	 * de declarar
	 */
	public void mousePressed(MouseEvent evento) {
	}

	/**
	 * Dispensável para este trabalho, mas obrigatório
	 * de declarar
	 */
	public void mouseReleased(MouseEvent evento) {
	}

	/**
	 * Dispensável para este trabalho, mas obrigatório
	 * de declarar
	 */
	public void mouseEntered(MouseEvent evento) {
	}

	/**
	 * Dispensável para este trabalho, mas obrigatório
	 * de declarar
	 */
	public void mouseExited(MouseEvent evento) {
	}

	/**
	 * Dispensável para este trabalho, mas obrigatório
	 * de declarar
	 */
	public void focusGained(FocusEvent evento) {
	}

	/**
	 * Controle da configuracao do nome, apos clicar em 
	 * qualquer outro componente
	 * @param evento Evento registrado
	 */
	public void focusLost(FocusEvent evento) {
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if ("abrirListagem".equals(evento.getActionCommand())){
			Comandos.abrirAqrListagem();
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evento) {
		if (!tabelaDados.isEditing())
			System.out.println("Editou tabela\n");
		else{
			int lin = tabelaDados.getSelectedRow();
			int col = tabelaDados.getSelectedColumn();
			
			if ((lin != -1) && (col != -1)){
				Object resp = tabelaDados.getValueAt(lin, col);
				
				if (resp.toString().equals("true"))
					resp = "false";
				else
					resp = "true";
				
			System.out.println("Editando tabela");
			System.out.println("Valor em [" + lin + "][" + col + "]"
								+ " -> " + resp);
			}
		}
	}
}