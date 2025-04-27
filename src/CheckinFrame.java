import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class CheckinFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCheckin;
	private JTextField textFieldSearchCheckin;
	private JTable table;
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public CheckinFrame() {
		setBorder(null);
		setBackground(new Color(55, 55, 55));

		
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(6, 6, 1292, 28);
		add(panel);
		
		txtCheckin = new JTextField();
		txtCheckin.setBorder(null);
		txtCheckin.setAutoscrolls(false);
		txtCheckin.setEditable(false);
		txtCheckin.setBackground(Color.DARK_GRAY);
		txtCheckin.setForeground(new Color(17, 193, 123));
		txtCheckin.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheckin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		txtCheckin.setText("Check-in");
		panel.add(txtCheckin);
		txtCheckin.setColumns(10);
		
		JButton btnNewButtonConfirmCheckin = new JButton("Confirmar Entrada");
		btnNewButtonConfirmCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButtonConfirmCheckin.setForeground(new Color(255, 255, 255));
		btnNewButtonConfirmCheckin.setBounds(6, 60, 172, 34);
		add(btnNewButtonConfirmCheckin);
		
		JButton btnNewButtonAddCheckin = new JButton("Cadastrar Entrada");
		btnNewButtonAddCheckin.setForeground(new Color(255, 255, 255));
		btnNewButtonAddCheckin.setBounds(198, 60, 172, 34);
		add(btnNewButtonAddCheckin);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(1288, 119, 10, 620);
		add(scrollBar);
		
		textFieldSearchCheckin = new JTextField();
		textFieldSearchCheckin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textFieldSearchCheckin.setForeground(new Color(255, 255, 255));
		textFieldSearchCheckin.setBounds(913, 73, 253, 34);
		add(textFieldSearchCheckin);
		textFieldSearchCheckin.setColumns(10);
		
		JButton btnNewButtonSearch = new JButton("");
		btnNewButtonSearch.setIcon(new ImageIcon(CheckinFrame.class.getResource("/img/icons8-pesquisar-30.png")));
		btnNewButtonSearch.setForeground(new Color(255, 255, 255));
		btnNewButtonSearch.setBounds(837, 73, 64, 34);
		add(btnNewButtonSearch);
		
		JComboBox comboBoxFilter = new JComboBox();
		comboBoxFilter.setForeground(new Color(255, 255, 255));
		comboBoxFilter.setModel(new DefaultComboBoxModel(new String[] {"CPF", "QUARTO", "CLIENTE"}));
		comboBoxFilter.setBounds(1178, 73, 108, 34);
		add(comboBoxFilter);
		
		JButton btnNewButtonDeleteReserve = new JButton("Cancelar Reserva");
		btnNewButtonDeleteReserve.setForeground(Color.WHITE);
		btnNewButtonDeleteReserve.setBounds(198, 770, 172, 34);
		add(btnNewButtonDeleteReserve);
		
		JButton btnNewButtonEditReserve = new JButton("Editar Reserva");
		btnNewButtonEditReserve.setForeground(Color.WHITE);
		btnNewButtonEditReserve.setBounds(6, 770, 172, 34);
		add(btnNewButtonEditReserve);
		
		JButton btnNewButtonRefresh = new JButton("");
		btnNewButtonRefresh.setIcon(new ImageIcon(CheckinFrame.class.getResource("/img/icons8-actualizar-30 (1).png")));
		btnNewButtonRefresh.setForeground(Color.WHITE);
		btnNewButtonRefresh.setBounds(1212, 770, 74, 34);
		add(btnNewButtonRefresh);
		
		table_1 = new JTable();
		table_1.setShowHorizontalLines(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Quarto", "Data Entrada", "Data Saida", "Nome", "CPF", "Status"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(45);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(45);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(6, 119, 1280, 620);
		add(scrollPane);

		
	}
}
