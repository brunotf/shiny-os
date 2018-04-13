package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import persistence.MateriasDao;
import model.Materias;

@SuppressWarnings("unused")
public class BotaoMateriasController implements ActionListener {

	private JTextField tfId, tfNome, tfCarga;
	private JComboBox<Materias> comboBox;
	private JRadioButton rdbtnExclui, rdbtnAtualiza, rdbtnCadastro;
	private JTable tblMaterias;
	
	public BotaoMateriasController(JTextField tfId, JTextField tfNome, 
			JTextField tfCarga, JComboBox<Materias> comboBox, 
			JRadioButton rdbtnExclui, JRadioButton rdbtnAtualiza, 
			JRadioButton rdbtnCadastro, JTable tblMaterias) {
		this.tfId = tfId;
		this.tfNome = tfNome;
		this.tfCarga = tfCarga;
		this.comboBox = comboBox;
		this.rdbtnExclui = rdbtnExclui;
		this.rdbtnAtualiza = rdbtnAtualiza;
		this.rdbtnCadastro = rdbtnCadastro;
		this.tblMaterias = tblMaterias;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Materias mat = new Materias();
		if (comboBox.getSelectedItem() != null){
			mat = (Materias) comboBox.getSelectedItem();
		}
		if (rdbtnExclui.isSelected()){
			exclui(mat);
		} else {
			mat.setId(Integer.parseInt(tfId.getText()));
			mat.setNome(tfNome.getText());
			mat.setCarga(Integer.parseInt(tfCarga.getText()));
			if (rdbtnCadastro.isSelected()){
				cadastro(mat);
			} else {
				atualiza(mat);
			}
		}
	}

	private void limpaCampos(){
		tfNome.setText("");
		tfCarga.setText("");
		if (!rdbtnCadastro.isSelected()){
			tfId.setText("");
		}
	}

	private void atualiza(Materias mat) {
		try {
			MateriasDao mDao = new MateriasDao();
			mDao.atualizaMaterias(mat);
			IMateriasController mController = new MateriasController(comboBox);
			mController.listaMaterias();
			mController = new MateriasController(tblMaterias);
			mController.tabelaMaterias();
			limpaCampos();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cadastro(Materias mat) {
		try {
			MateriasDao mDao = new MateriasDao();
			mDao.insereMaterias(mat);
			MateriasController mController = new MateriasController(tfId);
			mController.proximoId();
			mController = new MateriasController(tblMaterias);
			mController.tabelaMaterias();
			limpaCampos();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO", 
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void exclui(Materias mat) {
		try {
			MateriasDao mDao = new MateriasDao();
			mDao.excluiMaterias(mat);
			IMateriasController mController = new MateriasController(comboBox);
			mController.listaMaterias();
			mController = new MateriasController(tblMaterias);
			mController.tabelaMaterias();
			limpaCampos();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO", 
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
