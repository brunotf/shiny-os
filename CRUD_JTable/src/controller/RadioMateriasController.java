package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Materias;

public class RadioMateriasController implements ActionListener {

	JTextField tfId, tfNome, tfCarga;
	JLabel lblId, lblNome, lblCarga, lblMatrias;
	JRadioButton rdbtnExclui, rdbtnAtualiza, rdbtnCadastro;
	JButton btnEnviar;
	JComboBox<Materias> comboBox;
	
	public RadioMateriasController(JTextField tfId, JTextField tfNome, JTextField tfCarga,
			JLabel lblId, JLabel lblNome, JLabel lblCarga, JLabel lblMatrias,
			JRadioButton rdbtnExclui, JRadioButton rdbtnAtualiza, JRadioButton rdbtnCadastro,
			JButton btnEnviar, JComboBox<Materias> comboBox) {
		this.tfId = tfId;
		this.tfCarga = tfCarga;
		this.tfNome = tfNome;
		this.lblCarga = lblCarga;
		this.lblId = lblId;
		this.lblMatrias = lblMatrias;
		this.lblNome = lblNome;
		this.rdbtnAtualiza = rdbtnAtualiza;
		this.rdbtnCadastro = rdbtnCadastro;
		this.rdbtnExclui = rdbtnExclui;
		this.btnEnviar = btnEnviar;
		this.comboBox = comboBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		limpaCampos();
		if (rdbtnCadastro.isSelected()){
			IMateriasController mController = new MateriasController(tfId);
			mController.proximoId();
			montaTelaCadastro();
		} else {
			IMateriasController mController = new MateriasController(comboBox);
			mController.listaMaterias();
			if (rdbtnAtualiza.isSelected()){
				montaTelaAtualiza();
			} else {
				montaTelaExclui();
			}
		}
	}

	private void limpaCampos() {
		tfId.setText("");
		tfNome.setText("");
		tfCarga.setText("");
	}

	private void montaTelaExclui() {
		lblCarga.setVisible(false);
		lblId.setVisible(false);
		lblNome.setVisible(false);
		lblMatrias.setVisible(true);
		tfCarga.setVisible(false);
		tfId.setVisible(false);
		tfNome.setVisible(false);
		comboBox.setVisible(true);
		btnEnviar.setVisible(true);		
	}

	private void montaTelaAtualiza() {
		lblCarga.setVisible(true);
		lblId.setVisible(true);
		lblNome.setVisible(true);
		lblMatrias.setVisible(true);
		tfCarga.setVisible(true);
		tfId.setVisible(true);
		tfNome.setVisible(true);
		comboBox.setVisible(true);
		btnEnviar.setVisible(true);		
	}

	private void montaTelaCadastro() {
		lblCarga.setVisible(true);
		lblId.setVisible(true);
		lblNome.setVisible(true);
		lblMatrias.setVisible(false);
		tfCarga.setVisible(true);
		tfId.setVisible(true);
		tfNome.setVisible(true);
		comboBox.setVisible(false);
		btnEnviar.setVisible(true);
	}

}
