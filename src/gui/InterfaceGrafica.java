package gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Classe responsável pelo gerenciamento da interface gráfica
 * @author Rodrigo Guimarães
 * @version	1.0
 * @since	11/09/2016
 */
public class InterfaceGrafica {
	static JFrame janelaPrincipal = null;
	static final int	comprimentoMaximo	= 670;
	static final int	alturaMaxima		= 341;
	
	private JPanel painelPrincipal = null;
	private JPanel painelItens = null;
	private JPanel painelOperacoes = null;
	
	private Dimension dimensaoAvulsa;

	public InterfaceGrafica(){
		FormatacaoPadrao.formatarGui();
		
		janelaPrincipal = new JFrame("Controlador de Estoque");
		
		janelaPrincipal.setContentPane (this.getPainelPrincipal());
		
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

	private JPanel getPainelPrincipal() {
		prepararPainelPrincipal ();
		
		constroiPainelItens ();
		
		return painelPrincipal;
	}
	

	/**
	 * Configuração inicial do painel para receber os recursos
	 */
	private void prepararPainelPrincipal (){
		painelPrincipal = new JPanel ();
		
		//painelPrincipal.setLayout (new GridLayout(1, 2));
		//painelPrincipal.setLayout (new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		painelPrincipal.setLayout (new BoxLayout(painelPrincipal, BoxLayout.X_AXIS));
		
		painelPrincipal.setOpaque (true);
        
        Border caixaPrincipal = BorderFactory.createEmptyBorder (10, 10, 10, 10);
        painelPrincipal.setBorder (caixaPrincipal);
	}

	private void prepararPainelItens (){
		painelItens = new JPanel();
		
		dimensaoAvulsa = new Dimension(250, alturaMaxima);
		
		painelItens.setLayout (new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		
		painelItens.setMaximumSize(dimensaoAvulsa);
		painelItens.setMinimumSize(dimensaoAvulsa);
		painelItens.setPreferredSize(dimensaoAvulsa);
		
		painelItens.setOpaque (true);
        
        Border caixaPrincipal = BorderFactory.createEmptyBorder (5, 5, 5, 5);
        painelItens.setBorder (caixaPrincipal);
	}
	
	/**
	 * Construção do painel controlador dos itens
	 */
	private void constroiPainelItens() {
		prepararPainelItens ();
		
	}

}
