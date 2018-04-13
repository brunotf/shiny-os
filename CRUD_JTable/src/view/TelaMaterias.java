package view;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.BotaoMateriasController;
import controller.ComboMateriasController;
import controller.MateriasController;
import controller.RadioMateriasController;
import model.Materias;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class TelaMaterias extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNome;
	private JTextField tfCarga;
	private JTable tblMaterias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMaterias frame = new TelaMaterias();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaMaterias() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton rdbtnCadastro = new JRadioButton("Cadastro");
		rdbtnCadastro.setBounds(6, 25, 109, 23);
		contentPane.add(rdbtnCadastro);
		rdbtnCadastro.setSelected(true);

		JRadioButton rdbtnAtualiza = new JRadioButton("Atualiza");
		rdbtnAtualiza.setBounds(258, 25, 109, 23);
		contentPane.add(rdbtnAtualiza);

		JRadioButton rdbtnExclui = new JRadioButton("Exclui");
		rdbtnExclui.setBounds(489, 25, 109, 23);
		contentPane.add(rdbtnExclui);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnExclui);
		bg.add(rdbtnAtualiza);
		bg.add(rdbtnCadastro);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 136, 46, 14);
		contentPane.add(lblId);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(88, 133, 86, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 177, 46, 14);
		contentPane.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(88, 174, 366, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		JLabel lblCarga = new JLabel("Carga");
		lblCarga.setBounds(10, 214, 46, 14);
		contentPane.add(lblCarga);

		tfCarga = new JTextField();
		tfCarga.setBounds(88, 211, 86, 20);
		contentPane.add(tfCarga);
		tfCarga.setColumns(10);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(245, 269, 89, 23);
		contentPane.add(btnEnviar);

		JLabel lblMatrias = new JLabel("Mat\u00E9rias");
		lblMatrias.setBounds(10, 80, 67, 14);
		contentPane.add(lblMatrias);
		lblMatrias.setVisible(false);

		JComboBox<Materias> comboBox = new JComboBox<Materias>();
		comboBox.setBounds(87, 77, 489, 20);
		contentPane.add(comboBox);
		comboBox.setVisible(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 322, 588, 158);
		contentPane.add(scrollPane);

		tblMaterias = new JTable();
		scrollPane.setViewportView(tblMaterias);
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Carga Hor\u00E1ria" });
		tblMaterias.setModel(modelo);
		tblMaterias.getColumnModel().getColumn(0).setPreferredWidth(98);
		tblMaterias.getColumnModel().getColumn(1).setPreferredWidth(399);
		tblMaterias.getColumnModel().getColumn(2).setPreferredWidth(122);

		MateriasController mController = new MateriasController(tfId);
		mController.proximoId();
		
		mController = new MateriasController(tblMaterias);
		mController.tabelaMaterias();

		RadioMateriasController rmController = new RadioMateriasController(
				tfId, tfNome, tfCarga, lblId, lblNome, lblCarga, lblMatrias,
				rdbtnExclui, rdbtnAtualiza, rdbtnCadastro, btnEnviar, comboBox);
		rdbtnAtualiza.addActionListener(rmController);
		rdbtnCadastro.addActionListener(rmController);
		rdbtnExclui.addActionListener(rmController);

		ComboMateriasController cmController = new ComboMateriasController(
				tfId, tfNome, tfCarga, comboBox, rdbtnExclui);
		comboBox.addActionListener(cmController);

		BotaoMateriasController bmController = new BotaoMateriasController(
				tfId, tfNome, tfCarga, comboBox, rdbtnExclui, rdbtnAtualiza,
				rdbtnCadastro, tblMaterias);
		btnEnviar.addActionListener(bmController);

	}
}
