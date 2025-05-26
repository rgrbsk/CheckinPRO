import javax.swing.JPanel;
import checkoutWindow.CheckoutModuleWindow;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import clienteWindow.AddClientWindow;
import conexao.Conexao;
import objects.Reserve;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JComboBox;

public class CheckoutFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCheckout;
	private JTextField textField;
	private JTable tabelaReservas;
	private DefaultTableModel modelTable;

	/**
	 * Create the panel.
	 */
	public CheckoutFrame() {
		setBackground(new Color(55, 55, 55));

		
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(6, 6, 1292, 28);
		add(panel);
		panel.setLayout(null);
		
		txtCheckout = new JTextField();
		txtCheckout.setBounds(576, 5, 140, 24);
		txtCheckout.setText("Check-out");
		txtCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheckout.setForeground(new Color(17, 193, 123));
		txtCheckout.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		txtCheckout.setEditable(false);
		txtCheckout.setColumns(10);
		txtCheckout.setBorder(null);
		txtCheckout.setBackground(Color.DARK_GRAY);
		txtCheckout.setAutoscrolls(false);
		panel.add(txtCheckout);
		
		JButton btnConfirmarSaida = new JButton("Confirmar Saída");
		btnConfirmarSaida.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/Saida.png")));
		btnConfirmarSaida.setForeground(Color.WHITE);
		btnConfirmarSaida.setBounds(6, 62, 172, 34);
		add(btnConfirmarSaida);
		
		String[] colunas = {"ID Reserva", "Nome Cliente", "Data Entrada", "Data Saída", "Quarto"};
		modelTable = new DefaultTableModel(null, colunas);
		tabelaReservas = new JTable(modelTable);
		JScrollPane scrollPaneCheckout = new JScrollPane(tabelaReservas);
		scrollPaneCheckout.setBounds(6, 120, 1280, 616);
		add(scrollPaneCheckout);
		// ✅ Chamar pesquisa automaticamente ao abrir a tela
	    Reserve.pesquisarReservasCheckout(modelTable, tabelaReservas); // 🔥 Ago
	    
		JButton btnNewButtonEditReserve = new JButton("Editar Reserva");
		btnNewButtonEditReserve.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/EditarC.png")));
		btnNewButtonEditReserve.setForeground(Color.WHITE);
		btnNewButtonEditReserve.setBounds(6, 772, 172, 34);
		add(btnNewButtonEditReserve);
		
		JButton btnNewButtonDeleteReserve = new JButton("Cancelar Reserva");
		btnNewButtonDeleteReserve.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/Cancelar.png")));
		btnNewButtonDeleteReserve.setForeground(Color.WHITE);
		btnNewButtonDeleteReserve.setBounds(198, 772, 172, 34);
		add(btnNewButtonDeleteReserve);
		
		JButton btnNewButtonRefresh = new JButton("");
		btnNewButtonRefresh.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/icons8-actualizar-30 (1).png")));
		btnNewButtonRefresh.setForeground(Color.WHITE);
		btnNewButtonRefresh.setBounds(1212, 772, 74, 34);
		add(btnNewButtonRefresh);
		
		JButton btnNewButtonSearch = new JButton("");
		btnNewButtonSearch.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/icons8-pesquisar-30.png")));
		btnNewButtonSearch.setBounds(837, 75, 64, 34);
		add(btnNewButtonSearch);
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(913, 75, 253, 34);
		add(textField);
		
		JComboBox<String> comboBoxFilter = new JComboBox<String>();
		comboBoxFilter.setForeground(Color.WHITE);
		comboBoxFilter.setBounds(1178, 75, 108, 34);
		add(comboBoxFilter);
		
		boolean pagamentoAtivo = false; // ✅ Variável para controlar instância de pagament
		
		
	
		
		//gatilhos e Listeners para acionar botões, aq - add commit.
		btnNewButtonSearch.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Reserve.pesquisarReservasCheckout(modelTable, tabelaReservas); // chmar metodo dentro do objeto Re
		    }
		});

		btnConfirmarSaida.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tabelaReservas.getSelectedRow(); // pega id da bosta do grid, nao mexer senao da pau
		        		        
		        if (selectedRow == -1) { // se nao tiver nenhuma reserva selecionada, o usuario é burro
		            JOptionPane.showMessageDialog(null, "Selecione corretamente uma reserva.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int reservaId = (int) modelTable.getValueAt(selectedRow, 0); // id da coluna 0

		        System.out.println("Reserva selecionada: ID " + reservaId);
		        CheckoutModuleWindow window = new CheckoutModuleWindow();
                window.frameAddReserve.setVisible(true);
                btnConfirmarSaida.setEnabled(false);
		    }
		});


		

				 
		
		
		
		
		
		
		
	}
}
