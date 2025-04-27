import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import objects.Room.Filter;
import roomWindow.AddRoomWindow;
import javax.swing.ImageIcon;

public class RoomFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtQuartos;
	private JTextField textFieldSearch;
	private JTable tabela;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public RoomFrame() {
		
		setBackground(new Color(55, 55, 55));
		
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(6, 6, 1292, 28);
		add(panel);
		
		txtQuartos = new JTextField();
		txtQuartos.setText("Quartos");
		txtQuartos.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuartos.setForeground(new Color(17, 193, 123));
		txtQuartos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		txtQuartos.setEditable(false);
		txtQuartos.setColumns(10);
		txtQuartos.setBorder(null);
		txtQuartos.setBackground(Color.DARK_GRAY);
		txtQuartos.setAutoscrolls(false);
		panel.add(txtQuartos);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(1288, 119, 10, 620);
		add(scrollBar);
		
	
		JComboBox comboBoxFilter = new JComboBox();
		comboBoxFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			}
		});
		comboBoxFilter.setForeground(new Color(255, 255, 255));
		comboBoxFilter.setModel(new DefaultComboBoxModel(new String[] {"TODOS", "NÚMERO", "ANDAR"}));
		comboBoxFilter.setBounds(1178, 73, 108, 34);
		add(comboBoxFilter);
		
		
		
		JButton btnNewButtonSearch = new JButton("");
		btnNewButtonSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				String op = comboBoxFilter.getSelectedItem().toString();
				if (op != "TODOS") {
					
					filter(op);
				
			}else {
		      
				loadData();
				}
			}
			
			
		});
		btnNewButtonSearch.setIcon(new ImageIcon(RoomFrame.class.getResource("/img/icons8-pesquisar-30.png")));
		btnNewButtonSearch.setSelectedIcon(new ImageIcon(RoomFrame.class.getResource("/img/icons8-pesquisar-30.png")));
		btnNewButtonSearch.setForeground(new Color(255, 255, 255));
		btnNewButtonSearch.setBounds(837, 73, 64, 34);
		add(btnNewButtonSearch);
		
		
		
		JButton btnNewButtonDeleteRoom = new JButton("Apagar Quarto");
		btnNewButtonDeleteRoom.setForeground(Color.WHITE);
		btnNewButtonDeleteRoom.setBounds(190, 770, 172, 34);
		add(btnNewButtonDeleteRoom);
		
		JButton btnNewButtonAddRoom = new JButton("Adicionar Quarto");
		btnNewButtonAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddRoomWindow addRoom = new AddRoomWindow();
				addRoom.frameAddRoom.setVisible(true);
				
			}
		});
		btnNewButtonAddRoom.setForeground(Color.WHITE);
		btnNewButtonAddRoom.setBounds(6, 770, 172, 34);
		add(btnNewButtonAddRoom);
		
		JButton btnNewButtonRefresh = new JButton("");
		btnNewButtonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				loadData();
				
			}
		});
		btnNewButtonRefresh.setIcon(new ImageIcon(RoomFrame.class.getResource("/img/icons8-actualizar-30 (1).png")));
		btnNewButtonRefresh.setForeground(Color.WHITE);
		btnNewButtonRefresh.setBounds(1212, 770, 74, 34);
		add(btnNewButtonRefresh);
		
		
		
		
		tabela = new JTable();
		tabela.setShowHorizontalLines(true); 

		model = new DefaultTableModel(
		    new Object[][] {}, 
		    new String[] {"Número", "Andar", "Max Hóspedes", "Camas ","Valor"}
		);
		tabela.setModel(model);
		
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(6, 119, 1280, 620);
		add(scrollPane);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setForeground(Color.WHITE);
		textFieldSearch.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(913, 73, 253, 34);
		add(textFieldSearch);
		
		JButton btnNewButtonEditRoom = new JButton("Editar Quarto");
		btnNewButtonEditRoom.setForeground(Color.WHITE);
		btnNewButtonEditRoom.setBounds(374, 770, 172, 34);
		add(btnNewButtonEditRoom);
		
		loadData();
	}
	
	
	private void loadData() {
		model.setRowCount(0);
		List<Object[]> dados = Filter.searchRoom(null, "TODOS");
		for (Object[] linha : dados) {
			
			
			model.addRow(linha);
		}}
		
		
		private void filter(String op) {
			
			List<Object> parametersSearch = new ArrayList<>();
			String parameter = textFieldSearch.getText();
			parametersSearch.add(parameter);
			model.setRowCount(0);
			List<Object[]> data = Filter.searchRoom(parametersSearch, op);
			for (Object[] row : data) {
				
				
				model.addRow(row);
		
		
		}

	}
	
}