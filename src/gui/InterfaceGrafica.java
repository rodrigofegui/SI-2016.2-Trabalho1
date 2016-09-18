package gui;

import java.awt.Component;
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
	static JFrame janelaHistorico = null;
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
				static JTable tabelaDados = null;
			private JPanel painelManipulacao = null;
				static JButton salvarListagem = null;
				static JButton exibirHistorico = null;
				static JButton calcEstoque = null;
				static JButton gerarRelatorio = null;
				static JButton markTodosMin = null;
				static JButton desmarkTodosMin = null;
				static JButton markTodosMax = null;
				static JButton desmarkTodosMax = null;
	private static  JPanel painelHistorico = null;
		static DefaultTableModel modeloTabelaDados2 = null;	
		static JTable tabelaDados2 = null;
	/**
	 * Itens descartáveis, por ser utilizado por muitos
	 */
	private LinkedList<Object> lista;
	private static LinkedList<Object> listaS;
	private JPanel painelAvulso;
	private static JPanel painelAvulsoS;
	private JButton botaoAvulso;
	private static Dimension dimensaoAvulsa;
	
	
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
	
	/**
	 * Estrutura contruída da janela pertinente ao histórico
	 * @return Janela construída
	 */
	public static JFrame getFrameHistorico (){
		janelaHistorico = criarJanela("Histórico dos Itens", 500, 460);
		
		janelaHistorico = montarJanelaHistórico (janelaHistorico);
		
		return janelaHistorico;
	}

	/**
	 * Estrutura construida do painel histórico dos itens em estoque
	 * da aplicação
	 * @return Painel construído
	 */
	private static JPanel getPainelHistorico (){
		painelHistorico =  criarPainelS (1, 300, alturaMaxima, " Histórico: ",
										"Histórico de demanda para os itens em estoque");
		
		painelHistorico = montarPainelHistorico (painelHistorico);
		
		return painelHistorico;
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
								"abrirListagem", "Abrir", "Busca do arquivo base para a catalogação dos itens em estoque", true);
		
		nomeArqListagem = (JTextField) lista.get(1);
		botaoAbrirListagem = (JButton) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();

		lista = criarPainelAberturaArq ("Histórico:", "Arquivo base para o levantamento do histórico dos itens em estoque",
								"nomeArqHistorico", "<nome_arquivo_histórico>",
								"abrirHistorico", "Abrir", "Busca do arquivo base para o levantamento do histórico dos itens em estoque", false);

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
		lista = criarPainelItensConf("Lead Time:", "Tempo gasto para o reabastecimento de um dado item " +
									"(deve ser inteiro)", "periodoLT", "2");

		periodoLT = (JTextField) lista.get(1);
		marcarTodosLT = (JCheckBox) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelItensConf("Período:", "Tempo a ser considerado no cálculo do estoque " +
									"(deve ser inteiro)", "periodoPP", "1");
		
		periodoPP = (JTextField) lista.get(1);
		marcarTodosPP = (JCheckBox) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelItensConf("Nível de Segurança:", "Nível de segurança sobre o suprimento de estoque, em % "+
									"(deve ser menor que 100)", "nivelS", "95");
		
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
		String[][] dados = {}; 
		String cabecalho[] = {"Estoque Min", "Estoque Máx",
							  "Código", "Descrição",
							  "Qnt."};
		int larg[] = {90, 90, 60, 150, 60};
		
		lista = criarPainelTabela (dados, cabecalho, larg);
		
		modeloTabelaDados = (DefaultTableModel) lista.get(1);
		tabelaDados = (JTable) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
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

		lista = criarPainelBotao("exibirHistórico", "Histórico", "Exibir histórico completo dos itens", false);

		exibirHistorico = (JButton) lista.get(1);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelBotao("calcular", "Calcular", "Cálculo dos itens marcados, nos respectivos estoques", false);

		calcEstoque = (JButton) lista.get(1);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelBotao("gerarRelatório", "Gerar Relatorio", "Gerar relatório com os itens marcados", false);

		gerarRelatorio = (JButton) lista.get(1);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		painelBase.add(criarEspacoVazio (250, 20));
		
		lista = criarPainelEstoque ("Estoque Min:", "Operações básicas sobre o estoque mínimo",
									"markTodosMin", "desmarkTodosMin");
		
		markTodosMin = (JButton) lista.get(1);
		desmarkTodosMin = (JButton) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		painelBase.add(criarEspacoVazio (250, 15));
		
		lista = criarPainelEstoque ("Estoque Max:", "Operações básicas sobre o estoque máximo",
									"markTodosMax", "desmarkTodosMax");

		markTodosMax = (JButton) lista.get(1);
		desmarkTodosMax = (JButton) lista.get(2);
		painelBase.add((JPanel) lista.getFirst());
		lista.clear();
		
		return painelBase;
	}

	/**
	 * Construção de uma janela responsável pela exposição do histórico
	 * dos itens em estoque
	 * @param janelaBase Janela a ser construída
	 * @return Janela construída
	 */
	private static JFrame montarJanelaHistórico (JFrame janelaBase){
		janelaBase.add(getPainelHistorico());
		
		return janelaBase;
	}

	/**
	 * Construção de um painel responsábel pela demonstração
	 * do histórico dos itens em estoque
	 * @param painelBase Painel antes da inserção
	 * @return Painel construído
	 */
	private static JPanel montarPainelHistorico (JPanel painelBase){
		String[][] dados = {}; 
		String cabecalho[] = {"Código", "Descrição", "Período", "Qnt."};
		int larg[] = {60, 150, 60, 60};
		
		listaS = criarPainelTabelaS (dados, cabecalho, larg);
		
		modeloTabelaDados2 = (DefaultTableModel) listaS.get(1);
		tabelaDados2 = (JTable) listaS.get(2);
		painelBase.add((JPanel) listaS.getFirst());
		listaS.clear();
		
		return painelBase;
	}
	
	/**********************************************************
	 * Criação de componente ou conjunto de componentes
	 **********************************************************/	
	/**
	 * Criação de janela padrão
	 * @return Janela criada
	 */
	private JFrame criarJanela (){
		JFrame nova = new JFrame();
		
		nova.setVisible (true);
		nova.setResizable(false);
		nova.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		return nova;
	}
	
	/**
	 * Criação de janela padrão
	 * @param larg Largura da janela
	 * @param alt Altura da Janela
	 * @return Janela criada
	 */
	private JFrame criarJanela (int larg, int alt){
		JFrame nova = criarJanela();
		
		nova = (JFrame) setDimensao(nova, larg, alt);
		
		return nova;
	}
	
	/**
	 * Criação de janela padrão
	 * @param larg Largura da janela
	 * @param alt Altura da Janela
	 * @param redimensionavel Condição de poder redimensionar a janela
	 * @return Janela criada
	 */
	private JFrame criarJanela (int larg, int alt, boolean redimensionavel){
		JFrame nova = criarJanela (larg, alt);
		
		nova.setResizable(redimensionavel);
		
		return nova;
	}
	
	
	/**
	 * Criação de janela padrão
	 * @return Janela criada
	 */
	private static JFrame criarJanela (String nome){
		JFrame nova = new JFrame(nome);
		
		nova.setVisible (true);
		nova.setResizable(false);
		nova.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		return nova;
	}
	
	/**
	 * Criação de janela padrão
	 * @param larg Largura da janela
	 * @param alt Altura da Janela
	 * @return Janela criada
	 */
	private static JFrame criarJanela (String nome, int larg, int alt){
		JFrame nova = criarJanela(nome);
		
		nova = (JFrame) setDimensao(nova, larg, alt);
		
		return nova;
	}
	
	/**
	 * Criação de janela padrão
	 * @param larg Largura da janela
	 * @param alt Altura da Janela
	 * @param redimensionavel Condição de poder redimensionar a janela
	 * @return Janela criada
	 */
	private static JFrame criarJanela (String nome, int larg, int alt, boolean redimensionavel){
		JFrame nova = criarJanela (nome, larg, alt);
		
		nova.setResizable(redimensionavel);
		
		return nova;
	}	
	
	/**
	 * Criação de painel para conexão de componentes
	 * @return Painel criado
	 */
	private JPanel criarPainel (){
		JPanel novo = new JPanel ();
		        
        return novo;
	}
	
	/**
	 * Criação de painel para conexão de componentes
	 * @return Painel criado
	 */
	private static JPanel criarPainelS (){
		JPanel novo = new JPanel ();
		        
        return novo;
	}
	
	/**
	 * Criação de painel para conexão de componentes
	 * @param layout Escolha do layout aplicado ao painel, onde:
	 * 0 equivale a {@link FlowLayout}
	 * 1 equivale a {@link BoxLayout} em X
	 * 2 equivale a {@link BoxLayout} em Y
	 * @return Painel criado
	 */
	private JPanel criarPainel (int layout){
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
	 * @param layout Escolha do layout aplicado ao painel, onde:
	 * 0 equivale a {@link FlowLayout}
	 * 1 equivale a {@link BoxLayout} em X
	 * 2 equivale a {@link BoxLayout} em Y
	 * @return Painel criado
	 */
	private static JPanel criarPainelS (int layout){
		painelAvulsoS = criarPainelS();
				
		switch (layout){
			case 0:
				painelAvulsoS.setLayout (new FlowLayout());
				break;
			case 1:
				painelAvulsoS.setLayout (new BoxLayout(painelAvulsoS, BoxLayout.X_AXIS));
				break;
			case 2:
				painelAvulsoS.setLayout (new BoxLayout(painelAvulsoS, BoxLayout.Y_AXIS));
		}
		        
        return painelAvulsoS;
	}
	
	/**
	 * Criação de painel para conexão de componentes
	 * @param lin Quantidade de linhas no {@link GridLayout}
	 * @param col Quantidade de colunas no {@link GridLayout}
	 * @return Painel criado
	 */
	private JPanel criarPainel (int lin, int col){
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
	private JPanel criarPainel (int layout, int borda, int larg, int alt){
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
	private JPanel criarPainel (int layout, int larg, int alt, String titulo, String mensagem){
		painelAvulso = criarPainel(layout);
		
		painelAvulso = (JPanel) setDimensao(painelAvulso, larg, alt);
		
		painelAvulso.setOpaque (true);
        
		painelAvulso.setBorder (BorderFactory.createTitledBorder(titulo));
        painelAvulso.setToolTipText(mensagem);
        
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
	private static JPanel criarPainelS (int layout, int larg, int alt, String titulo, String mensagem){
		painelAvulsoS = criarPainelS(layout);
		
		painelAvulsoS = (JPanel) setDimensao(painelAvulsoS, larg, alt);
		
		painelAvulsoS.setOpaque (true);
        
		painelAvulsoS.setBorder (BorderFactory.createTitledBorder(titulo));
		painelAvulsoS.setToolTipText(mensagem);
        
        return painelAvulsoS;
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
	 * @param habilitar Controle do enabled do botão
	 * @return Lista com os itens construídos
	 */
	private LinkedList<Object> criarPainelAberturaArq (String descricaoID, String mensagemID,
										String nomeTF, String textoTF,
										String nomeBT, String textoBT, String mensagemBT, boolean habilitar){
		JPanel painel = criarPainel (2, 1);
		
		JPanel painelAvulso2 = criarPainel(1);
		
		JTextField tfAplicado = criarTextField (nomeTF, textoTF, false);
		tfAplicado.setEditable(false);
		
		JButton btAplicado = criarBotao (nomeBT, textoBT, mensagemBT, habilitar);
				
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
		
		//System.out.println("Tamanho -> " + tabela.getColumnCount());
		
		painelAvulso.add(planoRolante);
		
		LinkedList<Object> lista = new LinkedList<Object>();
		
		lista.add(painelAvulso);
		lista.add(modeloTB);
		lista.add(tabela);
		
		return lista;
	}
	
	/**
	 * Criação de uma tabela padrao
	 * @param dadosTB Dados a serem inseridos, inicialmente, na tabela
	 * @param cabecalhoTB Identificador dos dados inseridos
	 * @param largCol Largura a ser atribuída às colunas da tabela
	 * @return Tabela construída
	 */
	private static LinkedList<Object> criarPainelTabelaS (String[][] dadosTB, String []cabecalhoTB, int[] largCol){
		painelAvulsoS = criarPainelS();
		
		DefaultTableModel modeloTB = new DefaultTableModel (dadosTB, cabecalhoTB);
		JTable tabela = new JTable(modeloTB);
		JScrollPane planoRolante = new JScrollPane(tabela);
		
		tabela = setLargTabelaS (tabela, largCol);
		
		tabela.getTableHeader().setReorderingAllowed(false);
		
		tabela.setEnabled(false);
		
		//tabela.addPropertyChangeListener(this);
		
		//System.out.println("Tamanho -> " + tabela.getColumnCount());
		
		//for (int ind = 0; ind < 30; ind++)
		//	modeloTB.insertRow(ind, new Object[]{null});
		
		painelAvulsoS.add(planoRolante);
		
		LinkedList<Object> lista = new LinkedList<Object>();
		
		lista.add(painelAvulsoS);
		lista.add(modeloTB);
		lista.add(tabela);
		
		return lista;
	}
	
	/**
	 * Criação de um painel padrão para os controles de estoque
	 * @param descricao Identificação do painel
	 * @param mensagemAjuda Mensagem que auxilia no entendimento da
	 * semântica do componente
	 * @param markID Identificador do botão que marca todos os itens
	 * @param desmarkID Itenficador do botão que desmarca todos os
	 * itens
	 * @param calcID Identificador do botão que calcula o estoque
	 * @return Painel criado
	 */
	private LinkedList<Object> criarPainelEstoque (String descricao, String mensagemAjuda,
													String markID, String desmarkID){
		LinkedList<Object> listaInterna = new LinkedList<Object>();
		JPanel painelAvulsoInterno = criarPainel (2, 250, 90, descricao, mensagemAjuda);
		
		lista = criarPainelBotao(markID, "Marcar todos", "Seleciona todos os itens para o cálculo", false);
		
		listaInterna.add(lista.get(1));
		painelAvulsoInterno.add((JPanel) lista.getFirst());
		lista.clear();
		
		lista = criarPainelBotao(desmarkID, "Desmarcar todos", "Libera todos os itens do cálculo", false);
		
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
	public JButton criarIdentificacao (String texto){
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
	public JButton criarIdentificacao (String texto, String mensagemAjuda){
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
	public JButton criarIdentificacao (String texto, String mensagemAjuda, int alinhamento){
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
	public Component criarEspacoVazio (int largura, int altura) {
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
	public JTextField criarTextField (){
		JTextField novo = new JTextField();
		
		return novo;
	}
	
	/**
	 * Criação de um campo para texto padrão
	 * @param nome Nome a ser atribuído ao campo
	 * @return Campo de texto criado
	 */
	public JTextField criarTextField (String nome){
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
	public JTextField criarTextField (String nome, String textoInicial){
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
	public JTextField criarTextField (String nome, String textoInicial, boolean executavel){
		JTextField novo = criarTextField(nome, textoInicial);
		int larg, alt;
		
		if (executavel){
			novo.getDocument().addDocumentListener(this);
			novo.getDocument().putProperty("owner", novo);
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
	public JCheckBox criarCheckBox (){
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
		botaoAvulso.setToolTipText("Aplicar o padrão a todos os itens de estoque (será aplicado assim que marcar)");
		
		return botaoAvulso;
	}
	
	
	/**********************************************************
	 * Configuração Geral
	 **********************************************************/
	/**
	 * Configuração da dimensão de um painel
	 * @param alvo Componente a ser dimensionado
	 * @param larg Largura do painel
	 * @param alt Altura do painel
	 * @return Painel dimensionado
	 */
	public static Component setDimensao (Component alvo, int larg, int alt){
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
	
	/**
	 * Configuração do largura das colunas da tabela
	 * @param tabela Tabela a ser configurada
	 * @param tamanho Larguras a serem atribuídas
	 * @return Tabela configurada
	 */
	private static JTable setLargTabelaS (JTable tabela, int[] tamanho){
		for (int col = 0; col < tamanho.length; col++){
			tabela.getColumnModel().getColumn(col).setMaxWidth(tamanho[col]);
			tabela.getColumnModel().getColumn(col).setMinWidth(tamanho[col]);
			tabela.getColumnModel().getColumn(col).setPreferredWidth(tamanho[col]);
			tabela.getColumnModel().getColumn(col).setResizable(false);
		}
		
		return tabela;
	}
	
	/**
	 * Comparação entre 3 inteiros, descobrir se o do meio pertence ao intervalo
	 * @param menor Limite inferior do intervalo
	 * @param valor Valor de interesse
	 * @param maior Limite superior do intervalo
	 * @return true, se o valor estiver no intervalo;
	 * false caso contrário
	 */
	public boolean cmpIntervalo (int menor, int valor, int maior){
		if ((menor <= valor) && (valor <= maior))
			return true;
		
		return false;
	}

	/**
	 * Filtro de caracteres alfabéticos, objetivando um inteiro
	 * @param origem String a ser refinada
	 * @return String filtrada
	 */
	public static String refinaInteiro (String origem){
		String resultado = origem.replaceAll("[a-zA-Z]", "");
		
		return resultado;
	}
	
	/**
	 * Filtro de caracteres alfabéticos, objetivando um float
	 * ou double
	 * @param origem String a ser refinada
	 * @return String filtrada
	 */
	public static String refinaFloat (String origem){
		String resultado = refinaInteiro(origem);
		
		resultado = resultado.replaceAll(",", ".");
		
		return resultado;
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
		Object fonte = evento.getDocument().getProperty("owner");
		String texto = "";
		
		if (fonte.equals(periodoLT) && periodoLT.getText().length() >= 1){
			texto = refinaInteiro (periodoLT.getText());
				
			System.out.println("Está assim no LT: " + Integer.parseInt(texto) + "\n");
			
		}else if (fonte.equals(periodoPP) && periodoPP.getText().length() >= 1){
			texto = refinaInteiro (periodoPP.getText());
				
			System.out.println("Está assim no PP: " + Integer.parseInt(texto) + "\n");
						
		}else if (fonte.equals(nivelSeguranca) && nivelSeguranca.getText().length() >= 1){
			texto = refinaFloat(nivelSeguranca.getText());
				
			System.out.println("Está assim no FS: " + Float.parseFloat(texto) + "\n");
		}
		
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

	
	/**
	 * Controle das ações dos ambientes gráficos ao click
	 * de botão
	 * @param evento Evento registrado
	 */
	public void actionPerformed(ActionEvent evento) {
		if ("abrirListagem".equals(evento.getActionCommand())){
			System.out.println("A selecionar arquivo listagem...");
			
			Comandos.abrirAqrListagem();
		
		}else if ("abrirHistorico".equals(evento.getActionCommand())){
			System.out.println("A selecionar arquivo histórico");
			
			Comandos.abrirAqrHistorico();
			
		}else if ("restaurarTodos".equals(evento.getActionCommand())){
			System.out.println("Restando todos os itens...");
			
			Comandos.restaurarItens();
			
		}else if ("salvarListagem".equals(evento.getActionCommand())){
			System.out.println("Salvando listagem modificada...");
			
			Comandos.salvarListagem(controle.ControladorEstoque.estoque.getNomeAbsArqListagem());
		
		}else if ("exibirHistórico".equals(evento.getActionCommand())){
			System.out.println("Exibindo o histórico...\n");
			
			Comandos.exibirHistorico();
		
		}else if ("calcular".equals(evento.getActionCommand())){
			System.out.println("Calculando estoques marcados...");
			
			Comandos.calcularEstoque();
		
		}else if ("gerarRelatório".equals(evento.getActionCommand())){
			System.out.println("Gerando relatório...\n");
			
			Comandos.gerarRelatorio ();
		
		}else if ("markTodosMin".equals(evento.getActionCommand())){
			System.out.println("Marcando todos para o cálculo do estoque mínimo...\n");
			
			Comandos.controleMarcarTodosMin (0);
			
		
		}else if ("desmarkTodosMin".equals(evento.getActionCommand())){
			System.out.println("Desmarcando todos para o cálculo do estoque mínimo...\n");
			
			Comandos.controleMarcarTodosMin (1);
		
		}else if ("markTodosMax".equals(evento.getActionCommand())){
			System.out.println("Marcando todos para o cálculo do estoque máximo...\n");
			
			Comandos.controleMarcarTodosMax (0);
		
		}else if ("desmarkTodosMax".equals(evento.getActionCommand())){
			System.out.println("Desmarcando todos para o cálculo do estoque máximo...\n");
			
			Comandos.controleMarcarTodosMax (1);
		
		}
		
	}

	
	/**
	 * Controle das ações dos ambientes gráficos, para
	 * os checkBox
	 * @param evento Evento registrado
	 */
	public void itemStateChanged(ItemEvent evento) {
		Object origem = evento.getItemSelectable();
		int acao = (evento.getStateChange() - 1);
		
		if (origem.equals(marcarTodosLT)){
			System.out.println("Marcar Todos LT -> " + acao + "\n");
			
			Comandos.controleMarcarTodosLT(acao, Integer.parseInt(refinaInteiro (periodoLT.getText())));
			
		}else if (origem.equals(marcarTodosPP)){
			System.out.println("Marcar Todos PP -> " + acao + "\n");
			
			Comandos.controleMarcarTodosPP(acao, Integer.parseInt(refinaInteiro (periodoPP.getText())));
			
		}else if (origem.equals(marcarTodosFS)){
			System.out.println("Marcar Todos FS -> " + acao + "\n");
			
			Comandos.controleMarcarTodosFS(acao, Float.parseFloat(refinaFloat(nivelSeguranca.getText())));
			
		}
	}

	
	/**
	 * Controle das ações dos ambientes gráficos, para
	 * as tabelas
	 * @param evento Evento registrado
	 */
	public void propertyChange(PropertyChangeEvent evento) {
		if(evento.getSource().equals(tabelaDados)){
			if (!tabelaDados.isEditing()){
				System.out.println("Editou tabela\n");
				int lin = tabelaDados.getSelectedRow();
				int col = tabelaDados.getSelectedColumn();
				
				if ((lin >= 0) && (col >= 0)){
					Object resp = tabelaDados.getValueAt(lin, col);
					
					System.out.println("Editando tabela");
					System.out.println("Valor em [" + lin + "][" + col + "]"
										+ " -> " + resp);
					
					if (col > 1)
						Comandos.atualizarEstoque(lin, col, resp.toString());
				}
			}
		}
	}
}
