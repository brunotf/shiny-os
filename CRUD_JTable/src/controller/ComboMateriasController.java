package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Materias;

public class ComboMateriasController implements ActionListener {

	private JTextField tfId, tfNome, tfCarga;
	private JComboBox<Materias> comboBox;
	private JRadioButton rdbtnExclui;
	
	public ComboMateriasController(JTextField tfId, JTextField tfNome, 
			JTextField tfCarga, JComboBox<Materias> comboBox, 
			JRadioButton rdbtnExclui) {
		this.tfId = tfId;
		this.tfNome = tfNome;
		this.tfCarga = tfCarga;
		this.comboBox = comboBox;
		this.rdbtnExclui = rdbtnExclui;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (comboBox.getItemCount() > 0){
			if (!rdbtnExclui.isSelected()){
				Materias mat = (Materias) comboBox.getSelectedItem();
				preencheCampos(mat);
			}
		}
	}

	private void preencheCampos(Materias mat) {
		tfId.setText(String.valueOf(mat.getId()));
		tfNome.setText(mat.getNome());
		tfCarga.setText(String.valueOf(mat.getCarga()));
	}

}
