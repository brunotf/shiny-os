package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PingControl;

public class PingVisao implements ActionListener {

	private JButton btnAdaptador = new JButton("Mostrar adaptadores Ethernet");
	private JButton btnPing = new JButton("Mostrar média do ping com uma URL");
	private JTextField txtURL;
	

	private PingControl pc = new PingControl();

	public PingVisao() {
		JFrame janela = new JFrame("Testa Ping");
		JPanel painelPrincipal = new JPanel(new BorderLayout());
		JPanel painelPing = new JPanel(new BorderLayout());
				
		janela.setContentPane(painelPrincipal);
		
		JPanel painelBotoes = new JPanel();
				
		janela.setContentPane( painelPrincipal ); 

		
		painelBotoes.add(btnAdaptador);
		painelBotoes.add(btnPing);
		
		painelPrincipal.add(painelBotoes, BorderLayout.CENTER);
		
		btnAdaptador.addActionListener( this );
		btnPing.addActionListener( this );
		
		janela.setContentPane(painelPrincipal);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		janela.setSize(640, 480);
		
	}
	
	public static void main(String[] args) {
		new PingVisao();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("Mostrar adaptadores Ethernet".contains( cmd )) {
			pc.getAdaptador();
		} else if ("Mostrar média do ping com uma URL".contains(cmd)) {
		}
	}

}
