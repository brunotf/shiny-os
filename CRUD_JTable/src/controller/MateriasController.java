package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import persistence.MateriasDao;
import model.Materias;

public class MateriasController implements IMateriasController {

	private JComboBox<Materias> comboBox;
	private JTextField tfId;
	private JTable tblMaterias;

	public MateriasController(JComboBox<Materias> comboBox) {
		this.comboBox = comboBox;
	}

	public MateriasController(JTextField tfId) {
		this.tfId = tfId;
	}

	public MateriasController(JComboBox<Materias> comboBox, JTextField tfId) {
		this.comboBox = comboBox;
		this.tfId = tfId;
	}

	public MateriasController(JTable tblMaterias) {
		this.tblMaterias = tblMaterias;
	}

	@Override
	public void proximoId() {
		try {
			MateriasDao mDao = new MateriasDao();
			tfId.setText(String.valueOf(mDao.proximoId()));
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void listaMaterias() {
		try {
			MateriasDao mDao = new MateriasDao();
			List<Materias> listaMaterias = mDao.consultaMaterias();
			if (comboBox.getItemCount() > 0) {
				comboBox.removeAllItems();
			}
			if (listaMaterias != null) {
				for (Materias m : listaMaterias) {
					comboBox.addItem(m);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void tabelaMaterias() {
		if (tblMaterias != null){
			DefaultTableModel modelo = (DefaultTableModel) tblMaterias.getModel();
			if (modelo.getRowCount() > 0){
				modelo.setRowCount(0);
			}
			try {
				MateriasDao mDao = new MateriasDao();
				List<Materias> listaMaterias = mDao.consultaMaterias();
				for (Materias m : listaMaterias){
					Object[] linha = new Object[3];
					linha[0] = m.getId();
					linha[1] = m.getNome();
					linha[2] = m.getCarga();
					modelo.addRow(linha);
				}
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

}
