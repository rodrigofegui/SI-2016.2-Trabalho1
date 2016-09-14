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
	static final int	comprimentoMaximo	= 760 + 250;
	static final int	alturaMaxima		= 493;
	
	private  JPanel painelPrincipal = null;
		private  JPanel painelEsquerda = null;
			private JPanel painelAbrir = null;
				public static JTextField nomeArqListagem = null;
				private JButton botaoAbrirListagem = null;
				private JTextField nomeArqHistorico = null;
				private JButton botaoAbrirHistorico = null;
			private JPanel painelConfiguracoes = null;
				private JTextField periodoLT = null;
				private JCheckBox marcarTodosLT = null;
				private JTextField periodoPP = null;
				private JCheckBox marcarTodosPP = null;
				private JTextField nivelSeguranca = null;
				private JCheckBox marcarTodosFS = null;
				private JButton restaurarTodos = null;
		private  JPanel painelDireita = null;
			private JPanel painelItensEstoque = null;
				private DefaultTableModel modeloTabelaDados = null;	
					private JTable tabelaDados = null;
			private JPanel painelManipulacao = null;
				private JButton salvarListagem = null;
				private JButton exibirHistorico = null;
				private JButton gerarRelatorio = null;
				private JButton markTodosMin = null;
				private JButton desmarkTodosMin = null;
				private JButton calcMin = null;
				private JButton markTodosMax = null;
				private JButton desmarkTodosMax = null;
				private JButton calcMax = null;
	/**
	 * Itens descartáveis, mas utilizados por muitos
	 */
	private JPanel painelAvulso;
	private JButton botaoAvulso;
	private Dimension dimensaoAvulsa;
	
	
	
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
	
	/**
	 * Estrutura construída no painel principal da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelPrincipal(){
		painelPrincipal = criarPainel(1, 10, comprimentoMaximo, alturaMaxima);
		
		painelPrincipal.add(getPainelEsquerda());
		
		painelPrincipal.add(getPainelDireita());
		
		return painelPrincipal;
	}
	
	/**
	 * Estrutura construía no painel Esquerda da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelEsquerda (){
		painelEsquerda = criarPainel(2, 0, 250, alturaMaxima);
		
		painelEsquerda.add(getPainelAbrir());
		
		painelEsquerda.add(getPainelConfiguracoesGerais());
		
		return painelEsquerda;
	}
	
	/**
	 * Estrutura construída no painel abrir da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelAbrir (){
		painelAbrir = criarPainel(2, 1, 1, 2, 250, 120,
									" Abrir Arquivos: ", "Arquivos necessários para a construção do estoque");
		
		construirPainelAbrir();
		
		return painelAbrir;
	}
	
	/**
	 * Estrutura construída no painel configurações da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelConfiguracoesGerais (){
		painelConfiguracoes = criarPainel(4, 1, 1, 2, 250, 180,
											" Configurações Globais: ", "Configurações habilitadas a atingir todos os itens em estoque");
		
		construirPainelConfiguracoes();
		
		return painelConfiguracoes;
	}
	
	/**
	 * Estrutura construída no painel diretira da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelDireita (){
		painelDireita = criarPainel (1, 740, alturaMaxima, " Estoque: ",
									"Informações e configurações que afetam o estoque");
		
		painelDireita.add(getPainelItensEstoque());
				
		painelDireita.add(getPainelManipulacao());
		
		return painelDireita;
	}
	
	/**
	 * Estrutura construída no painel itens no estoque da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelItensEstoque (){
		painelItensEstoque = criarPainel (1, 1, 480, alturaMaxima, " Itens em estoque: ", "Descritivo básico dos itens em estoque");
				
		construirTabelaItens();
		
		return painelItensEstoque;
	}
	
	/**
	 * Estrutura construída no painel manipulação da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelManipulacao (){
		painelManipulacao = criarPainel (2, 250, alturaMaxima, " Configurações: ", "Configurações pertinentes ao estoque");
		
		construirPainelManipulacao ();
		
		return painelManipulacao;
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
		painelAvulso = new JPanel ();
		
		dimensaoAvulsa = new Dimension(larg, alt);
		
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
		
		painelAvulso.setMaximumSize(dimensaoAvulsa);
		painelAvulso.setMinimumSize(dimensaoAvulsa);
		painelAvulso.setPreferredSize(dimensaoAvulsa);
		
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
		painelAvulso = new JPanel ();
		
		dimensaoAvulsa = new Dimension(larg, alt);
		
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
		
		painelAvulso.setMaximumSize(dimensaoAvulsa);
		painelAvulso.setMinimumSize(dimensaoAvulsa);
		painelAvulso.setPreferredSize(dimensaoAvulsa);
		
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
		painelAvulso = new JPanel ();
		
		dimensaoAvulsa = new Dimension(larg, alt);
		
		painelAvulso.setLayout (new GridLayout(lin, col));
				
		painelAvulso.setMaximumSize(dimensaoAvulsa);
		painelAvulso.setMinimumSize(dimensaoAvulsa);
		painelAvulso.setPreferredSize(dimensaoAvulsa);
		
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
		
		dimensaoAvulsa = new Dimension(larg, alt);
		
		painelAvulso.setLayout (new GridLayout(lin, col));
				
		painelAvulso.setMaximumSize(dimensaoAvulsa);
		painelAvulso.setMinimumSize(dimensaoAvulsa);
		painelAvulso.setPreferredSize(dimensaoAvulsa);
		
		painelAvulso.setOpaque (true);
        
		painelAvulso.setBorder (BorderFactory.createTitledBorder(titulo));
        painelAvulso.setToolTipText(mensagem);
        
        return painelAvulso;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	
	
	/**
	 * Construção do painel Abrir, com a inserção dos componentes
	 * necessários
	 */
	private void construirPainelAbrir (){
		construirAbrirItem("Listagem:", "Arquivo base para a catalogação dos itens em estoque",
							"nomeArqListagem", "<nome_arquivol_listagem>",
							"abrirListagem", "Abrir", "Busca do arquivo base para a catalogação dos itens em estoque",
							nomeArqListagem, botaoAbrirListagem);
		
		construirAbrirItem("Histórico:", "Arquivo base para o levantamento do histórico dos itens em estoque",
							"nomeArqHistorico", "<nome_arquivo_histórico>",
							"abrirHistorico", "Abrir", "Busca do arquivo base para o levantamento do histórico dos itens em estoque",
							nomeArqHistorico, botaoAbrirHistorico);
	}
	
	/**
	 * Construção do painel Configurações, com a inserção dos componentes
	 * necessários
	 */
	private void construirPainelConfiguracoes (){
		construirConfItem("Lead Time:", "Tempo gasto para o reabastecimento de um dado item",
							"periodoLT", "2",
							periodoLT, marcarTodosLT);
		
		construirConfItem("Período:", "Tempo a ser considerado no cálculo do estoque",
							"periodoPP", "1",
							periodoPP, marcarTodosPP);
		
		construirConfItem(	"Fator Segurança:", "Nível de segurança sobre o suprimento de estoque, em %",
							"nivelS", "95",
							nivelSeguranca, marcarTodosFS);
		
		
		
		construirBotaoPaineis ("restaurarTodos", "Restaurar itens",
								"Restaurar todos os itens às configurações default",
								true, restaurarTodos, painelConfiguracoes);
		
	}

	/**
	 * Construção padrão dos itens que compõem o painel Abrir
	 * @param descricaoID Descrição do item
	 * @param mensagemID Mensagem explicativa o item
	 * @param nomeTF Identificador do JTextField correspondente
	 * @param textoTF Texto inserido no JTextField correspondente
	 * @param nomeBT Identificador do JButton correspondente
	 * @param textoBT Texto inserido no JButton correspondente
	 * @param mensagemBT Mensagem explicativa do JButton
	 * @param tfAplicado JTextField correspondente
	 * @param btAplicado JButton correspondente
	 */
	private void construirAbrirItem (String descricaoID, String mensagemID,
									String nomeTF, String textoTF,
									String nomeBT, String textoBT, String mensagemBT,
									JTextField tfAplicado, JButton btAplicado){
		painelAvulso = new JPanel ();
		painelAvulso.setLayout(new GridLayout(2, 1));
					
		botaoAvulso = criarIdentificacao (descricaoID, false);
		botaoAvulso.setToolTipText(mensagemID);
		
		painelAvulso.add(botaoAvulso);		
		
		JPanel painelAvulso2 = new JPanel ();
		painelAvulso2.setLayout(new BoxLayout(painelAvulso2, BoxLayout.X_AXIS));
		
		tfAplicado = criarTextField (nomeTF, textoTF, false);
		tfAplicado.setEditable(false);
		
		btAplicado = criarBotao (nomeBT, textoBT, true);
		btAplicado.setToolTipText(mensagemBT);
				
		painelAvulso2.add(tfAplicado);
		painelAvulso2.add(btAplicado);		
		painelAvulso.add(painelAvulso2);
		
		painelAbrir.add(painelAvulso);
	}
	
	/**
	 * Construção padrão dos itens que compõem o painel Configurar
	 * @param descricaoID Descrição do item
	 * @param mensagemID Mensagem que explica o item
	 * @param nomeTF Identificador do JTextField correspondente
	 * @param textoTF Texto inserido no JTextField correspondente
	 * @param tfAplicado JTextField correspondente
	 * @param cbAplicado JCheckBox correspondentes
	 */
	private void construirConfItem (String descricaoID, String mensagemID,
									String nomeTF, String textoTF,
									JTextField tfAplicado, JCheckBox cbAplicado){
		painelAvulso = new JPanel ();
		painelAvulso.setLayout(new GridLayout(2, 1));
		
		JPanel painelAvulso2 = new JPanel ();
		painelAvulso2.setLayout(new BoxLayout(painelAvulso2, BoxLayout.X_AXIS));
					
		botaoAvulso = criarIdentificacao (descricaoID, false);
		botaoAvulso.setHorizontalAlignment(JButton.LEFT);
		botaoAvulso.setToolTipText (mensagemID);

		tfAplicado = criarTextField (nomeTF, textoTF, true);
		
		painelAvulso2.add(botaoAvulso);
		painelAvulso2.add(tfAplicado);
		painelAvulso.add(painelAvulso2);
		
		
		painelAvulso2 = new JPanel ();
		painelAvulso2.setLayout(new BoxLayout(painelAvulso2, BoxLayout.X_AXIS));
		
		cbAplicado = criarCheckBox();
		
		painelAvulso2.add(getEspacoVazio (115, 50));
		painelAvulso2.add(cbAplicado);
		painelAvulso2.add(getAplicarTodos());
		painelAvulso.add(painelAvulso2);
		
		painelConfiguracoes.add(painelAvulso);
	}
	
	/**
	 * Construção do botão de restaurar as configurações de todos os itens
	 * em estoque
	 */
	private void construirBotaoPaineis (String nomeBT, String textoBT,
										String mensagemBT, boolean habilitar,
										JButton btAplicado, JPanel painelAplicado){
		JPanel painelAvulso2 = new JPanel ();
		
		btAplicado = criarBotao(nomeBT, textoBT , habilitar, 120, 20);
		btAplicado.setToolTipText(mensagemBT);
		
		painelAvulso2.add(btAplicado);
		
		painelAplicado.add(painelAvulso2);
	}

	/**
	 * Construlão da tabela com os itens existentes em estoque
	 */
	private void construirTabelaItens (){
		painelAvulso = new JPanel ();
		
		String[][] dados = {null}; 
		String cabecalho[] = {"Estoque Min", "Estoque Máx",
							  "Código", "Descrição",
							  "Qnt."};
		int tamanho[] = {90, 90, 60, 150, 60};
		
		modeloTabelaDados = new DefaultTableModel (dados, cabecalho){
			private static final long serialVersionUID = 1L;

			@Override
		    public Class<?> getColumnClass(int column) {
				if (column < 2)
					return Boolean.class;
				else
					return super.getColumnClass(column);
			}
		};
		
		tabelaDados = new JTable(modeloTabelaDados);
		JScrollPane planoRolante = new JScrollPane(tabelaDados);
		
		confTamColTabela(tabelaDados, tamanho);
		
		tabelaDados.getTableHeader().setReorderingAllowed(false);
		
		tabelaDados.addPropertyChangeListener(this);
		
		for (int i = 0; i < 30; i++)
			modeloTabelaDados.insertRow (i, new Object[]{false, false, 0, 0, 0});
		
		painelAvulso.add(planoRolante);
		
		painelItensEstoque.add(painelAvulso);
	}

	/**
	 * Construção do painel Manipulação, com a inserção dos componentes
	 * necessários
	 */
	private void construirPainelManipulacao (){
		construirBotaoPaineis ("salvarListagem", "Salvar",
								"Salvar alteração do estoque",
								false, salvarListagem, painelManipulacao);
		
		construirBotaoPaineis ("exibirHistórico", "Histórico",
								"Exibir histórico completo dos itens",
								true, exibirHistorico, painelManipulacao);
		
		construirBotaoPaineis ("gerarRelatorio", "Gerar Relatorio",
								"Gerar relatório com os itens marcados",
								true, gerarRelatorio, painelManipulacao);
		
		painelManipulacao.add(getEspacoVazio(250, 20));
		
		construirManiEstoque ("Estoque Min:", "Operações básicas sobre o estoque mínimo",
								markTodosMin, "markTodosMin", desmarkTodosMin, "desmarkTodosMin",
								calcMin, "calcMin");
		
		painelManipulacao.add(getEspacoVazio(250, 20));
		
		construirManiEstoque ("Estoque Max:", "Operações básicas sobre o estoque máximo",
								markTodosMax, "markTodosMax", desmarkTodosMax, "desmarkTodosMax",
								calcMax, "calcMax");
		
		painelManipulacao.add(getEspacoVazio(250, 130));
	}
	
	/**
	 * Construção dos ambientes controladores das operações sobre o estoque
	 * @param descricaoID Descrição do ambiente
	 * @param mensagemID Mensagem auxiliar do ambiente
	 * @param marcar Ação de marcar
	 * @param markID Identificação da ação de marcar
	 * @param desmarcar Ação de desmarcar
	 * @param desmarkID Identificação da ação de desmarcar
	 * @param calcular Ação de calcular
	 * @param calcID Identificação da ação de calcular
	 */
	private void construirManiEstoque (String descricaoID, String mensagemID,
										JButton marcar, String markID, 
										JButton desmarcar, String desmarkID,
										JButton calcular, String calcID){
		painelAvulso = new JPanel ();
		painelAvulso.setLayout(new GridLayout(4, 1));
		
		dimensaoAvulsa = new Dimension (250, 100);
		
		painelAvulso.setMaximumSize (dimensaoAvulsa);
		painelAvulso.setMinimumSize (dimensaoAvulsa);
		painelAvulso.setPreferredSize(dimensaoAvulsa);
					
		botaoAvulso = criarIdentificacao (descricaoID, false);
		botaoAvulso.setToolTipText(mensagemID);
		painelAvulso.add(botaoAvulso);
		
		construirBotaoPaineis (markID, "Marcar todos",
								"Seleciona todos os itens para o cálculo",
								true, marcar, painelAvulso);
		
		construirBotaoPaineis (desmarkID, "Desmarcar todos",
								"Libera todos os itens do cálculo",
								true, desmarcar, painelAvulso);
		
		construirBotaoPaineis (calcID, "Calcular",
								"Cálculo com todos os itens selecionados",
								true, calcular, painelAvulso);
		
		painelManipulacao.add(painelAvulso);
	}
	
	
	

	/**
	 * Criação de identificação, textos, a serem inseridas na área
	 * gráfica
	 * @param descricao	Texto a ser posto como inicial 
	 * @param checkBox	Sinaliza se é um texto de identificação de checkBox
	 * @return 			O texto criado
	 */
	private JButton criarIdentificacao (String descricao, boolean checkBox){
		botaoAvulso = new JButton();
		
		if (!checkBox)
			dimensaoAvulsa = new Dimension(120, 50);
		else
			dimensaoAvulsa = new Dimension(120, 50);
		
		botaoAvulso.setText(descricao);
		botaoAvulso.setHorizontalAlignment(JButton.CENTER);
				
		botaoAvulso.setBorderPainted(false);
		botaoAvulso.setContentAreaFilled(false);
		
		botaoAvulso.setMaximumSize (dimensaoAvulsa);
		botaoAvulso.setMinimumSize (dimensaoAvulsa);
		botaoAvulso.setPreferredSize (dimensaoAvulsa);
		
		return botaoAvulso;
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
		JTextField novo = new JTextField();
		
		novo.setText (textoInicial);
		novo.setName (nome);

		if (executavel){
			novo.getDocument().addDocumentListener(this);
			dimensaoAvulsa = new Dimension (60, 20);
		}else{
			novo.addMouseListener(this);
			dimensaoAvulsa = new Dimension (179, 20);
		}
		
		novo.setMinimumSize (dimensaoAvulsa);
		novo.setMaximumSize (dimensaoAvulsa);
		novo.setPreferredSize (dimensaoAvulsa);
				
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
	private JButton criarBotao (String descricao, String texto, boolean habilitar){
		botaoAvulso = new JButton();
		dimensaoAvulsa = new Dimension (60, 20);
		
		botaoAvulso.setText(texto);
		
		botaoAvulso.setEnabled(habilitar);
		
		botaoAvulso.setMinimumSize(dimensaoAvulsa);
		botaoAvulso.setMaximumSize(dimensaoAvulsa);
		botaoAvulso.setPreferredSize(dimensaoAvulsa);
		
		botaoAvulso.setActionCommand(descricao);
		botaoAvulso.addActionListener(this);
		
		return botaoAvulso;
	}	
	
	/**
	 * Criação de um botão padrão, controlando a dimensão
	 * @param descricao Identificação do botao no tratamento de ações
	 * @param texto		Texto a ser posto como inicial
	 * @param habilitar Controle da utilização do botão, permitindo o click do
	 * mouse ou não
	 * @param larg	Largura do botão a ser criado
	 * @param alt Altura do botão a ser criado
	 * @return			Botao já criado
	 */
	private JButton criarBotao (String descricao, String texto, boolean habilitar, int larg, int alt){
		botaoAvulso = new JButton();
		dimensaoAvulsa = new Dimension (larg, alt);
		
		botaoAvulso.setText(texto);
		
		botaoAvulso.setEnabled(habilitar);
		
		botaoAvulso.setMinimumSize(dimensaoAvulsa);
		botaoAvulso.setMaximumSize(dimensaoAvulsa);
		botaoAvulso.setPreferredSize(dimensaoAvulsa);
		
		botaoAvulso.setActionCommand(descricao);
		botaoAvulso.addActionListener(this);
		
		return botaoAvulso;
	}
	
	/**
	 * Criando um espaço vazio nas dimensões desejadas
	 * @param largura Largura do espaço
	 * @param altura Altura do espaço
	 * @return Espaço vazio construído
	 */
	private Component getEspacoVazio (int largura, int altura) {
		Component caixa = Box.createRigidArea(new Dimension(largura, altura));
		
		return caixa;
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
	private JButton getAplicarTodos (){
		botaoAvulso = criarIdentificacao ("Aplicar a todos", true);
		botaoAvulso.setHorizontalAlignment(JButton.LEFT);
		botaoAvulso.setToolTipText("Aplicar o padrão a todos os itens de estoque");
		
		return botaoAvulso;
	}
	
	/**
	 * Configuração do largura das colunas da tabela
	 * @param tabela Tabela a ser configurada
	 * @param tamanho Larguras a serem atribuídas
	 */
	private void confTamColTabela (JTable tabela, int[] tamanho){
		for (int col = 0; col < tamanho.length; col++){
			tabela.getColumnModel().getColumn(col).setMaxWidth(tamanho[col]);
			tabela.getColumnModel().getColumn(col).setMinWidth(tamanho[col]);
			tabela.getColumnModel().getColumn(col).setPreferredWidth(tamanho[col]);
			tabela.getColumnModel().getColumn(col).setResizable(false);
		}
	}
	
	
	
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