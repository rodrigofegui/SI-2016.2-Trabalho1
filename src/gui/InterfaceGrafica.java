package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * Classe responsável pelo gerenciamento da interface gráfica
 * @author Rodrigo Guimarães
 * @version	1.0
 * @since	11/09/2016
 */
public class InterfaceGrafica implements DocumentListener, MouseListener, ActionListener, ItemListener {
	static JFrame janelaPrincipal = null;
	static final int	comprimentoMaximo	= 611;
	static final int	alturaMaxima		= 341;
	
	private  JPanel painelPrincipal = null;
		private  JPanel painelEsquerda = null;
			private JPanel painelAbrir = null;
				private JTextField nomeArqListagem = null;
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
		configurarPainelPrincipal();
		
		painelPrincipal.add(getPainelEsquerda());
		
		painelPrincipal.add(getPainelDireita());
		return painelPrincipal;
	}
	
	/**
	 * Estrutura construía no painel Esquerda da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelEsquerda (){
		configurarPainelEsquerdo ();
		
		painelEsquerda.add(getPainelAbrir());
		
		painelEsquerda.add(getPainelConfiguracoes());
		return painelEsquerda;
	}
	
	/**
	 * Estrutura construída no painel abrir da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelAbrir (){
		configurarPainelAbrir();
		
		construirPainelAbrir();
		
		return painelAbrir;
	}
	
	/**
	 * Estrutura construída no painel configurações da aplicação
	 * @return Painel construído
	 */
	private JPanel getPainelConfiguracoes (){
		configurarPainelConfiguracoes();
		
		construirPainelConfiguracoes();
		
		return painelConfiguracoes;
	}
	
	private JPanel getPainelDireita (){
		configurarPainelDireita();
		
		return painelDireita;
	}
	
	
	
	/**
	 * Configuração inicial do painel para receber os recursos
	 */
	private void configurarPainelPrincipal (){
		painelPrincipal = new JPanel ();
		
		dimensaoAvulsa = new Dimension(comprimentoMaximo, alturaMaxima);
		
		//painelPrincipal.setLayout (new GridLayout(1, 2));
		//painelPrincipal.setLayout (new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		painelPrincipal.setLayout (new BoxLayout(painelPrincipal, BoxLayout.X_AXIS));
		
		painelPrincipal.setMaximumSize(dimensaoAvulsa);
		painelPrincipal.setMinimumSize(dimensaoAvulsa);
		painelPrincipal.setPreferredSize(dimensaoAvulsa);
		
		painelPrincipal.setOpaque (true);
        
        Border caixaPrincipal = BorderFactory.createEmptyBorder (10, 10, 10, 10);
        painelPrincipal.setBorder (caixaPrincipal);
	}
	
	/**
	 * Configuração inicial do painel para receber os recursos
	 */
	private void configurarPainelEsquerdo (){
		painelEsquerda = new JPanel ();
		
		dimensaoAvulsa = new Dimension(270, alturaMaxima);
		
		painelEsquerda.setLayout (new GridLayout(2, 1));
		//painelEsquerda.setLayout (new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		//painelEsquerda.setLayout (new BoxLayout(painelEsquerda, BoxLayout.X_AXIS));
		
		painelEsquerda.setMaximumSize(dimensaoAvulsa);
		painelEsquerda.setMinimumSize(dimensaoAvulsa);
		painelEsquerda.setPreferredSize(dimensaoAvulsa);
		
		painelEsquerda.setOpaque (true);
        
        Border caixaPrincipal = BorderFactory.createEmptyBorder (10, 10, 10, 10);
        painelEsquerda.setBorder (caixaPrincipal);
	}
	
	/**
	 * Configuração inicial do painel Abrir, como:
	 * dimensão, layout e borda
	 */
	private void configurarPainelAbrir (){
		painelAbrir = new JPanel();
		dimensaoAvulsa = new Dimension(250, 115);
		
		//painelItens.setLayout(new FlowLayout());
		painelAbrir.setLayout(new GridLayout(2, 1, 1, 2));
		//painelItens.setLayout (new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		
		painelAbrir.setMaximumSize(dimensaoAvulsa);
		painelAbrir.setMinimumSize(dimensaoAvulsa);
		painelAbrir.setPreferredSize(dimensaoAvulsa);
		
		painelAbrir.setOpaque (true);
        
        //Border caixaPrincipal = BorderFactory.createEmptyBorder (5, 5, 5, 5);
        //painelItens.setBorder (caixaPrincipal);
		painelAbrir.setBorder (BorderFactory.createTitledBorder(" Abrir Arquivos: "));
		painelAbrir.setToolTipText("Arquivos necessários para a construção do estoque");
	}
	
	/**
	 * Configuração inicial do painel Configurações, como:
	 * dimensão, layout e borda
	 */
	private void configurarPainelConfiguracoes (){
		painelConfiguracoes = new JPanel();
		dimensaoAvulsa = new Dimension (250, 125);
		
		//painelConfiguracoes.setLayout(new FlowLayout());
		painelConfiguracoes.setLayout (new GridLayout(4, 1, 1, 2));
		//painelConfiguracoes.setLayout (new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		
		painelConfiguracoes.setMaximumSize (dimensaoAvulsa);
		painelConfiguracoes.setMinimumSize (dimensaoAvulsa);
		painelConfiguracoes.setPreferredSize(dimensaoAvulsa);

		painelConfiguracoes.setOpaque (true);
        
        //Border caixaPrincipal = BorderFactory.createEmptyBorder (5, 5, 5, 5);
        //painelConfiguracoes.setBorder (caixaPrincipal);
		painelConfiguracoes.setBorder (BorderFactory.createTitledBorder(" Configurações Globais: "));
		painelConfiguracoes.setToolTipText ("Configurações habilitadas a atingir todos os itens em estoque");
	}

	/**
	 * Configuração inicial do painel Direita, como:
	 * dimensão, layout e borda
	 */
	private void configurarPainelDireita (){
		painelDireita = new JPanel ();
		
		dimensaoAvulsa = new Dimension(300, alturaMaxima);
		
		//painelDireita.setLayout (new GridLayout(1, 2));
		//painelDireita.setLayout (new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		painelDireita.setLayout (new BoxLayout(painelDireita, BoxLayout.X_AXIS));
		
		painelDireita.setMaximumSize(dimensaoAvulsa);
		painelDireita.setMinimumSize(dimensaoAvulsa);
		painelDireita.setPreferredSize(dimensaoAvulsa);
		
		painelDireita.setOpaque (true);
        
        //Border caixaPrincipal = BorderFactory.createEmptyBorder (10, 10, 10, 10);
        //painelDireita.setBorder (caixaPrincipal);
        painelDireita.setBorder (BorderFactory.createTitledBorder(" Itens em estoque: "));
        painelDireita.setToolTipText("Descritivo básico dos itens em estoque");
	}


	/**
	 * Construção do painel Abrir, com a inserção dos componentes
	 * necessários
	 */
	private void construirPainelAbrir (){
		construirAbrirItem("Listagem:", "Arquivo base para a catalogação dos itens em estoque",
							"nomeArqListagem", "nome do arquivo da listagem",
							"abrirListagem", "Abrir", "Busca do arquivo base para a catalogação dos itens em estoque",
							nomeArqListagem, botaoAbrirListagem);
		
		construirAbrirItem("Histórico:", "Arquivo base para o levantamento do histórico dos itens em estoque",
							"nomeArqHistorico", "nome do arquivo do histórico",
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
		
		construirRestaurarItem ();
		
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
	private void construirRestaurarItem (){
		painelAvulso = new JPanel ();
		
		restaurarTodos = criarBotao("restaurarTodos", "Restaurar itens" , true, 120, 20);
		restaurarTodos.setToolTipText("Restaurar todos os itens às configurações default");
		
		painelAvulso.add(restaurarTodos);
		
		painelConfiguracoes.add(painelAvulso);		
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void itemStateChanged(ItemEvent evento) {
		// TODO Auto-generated method stub
		
	}
}