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
import clienteWindow.ClientHistoricWindow;
import conexao.Conexao;
import objects.Client;
import objects.Reserve;
import paymentFunction.Payment;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

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
		panel.setBounds(6, 6, 1292, 28);
		panel.setBackground(Color.DARK_GRAY);
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
		btnConfirmarSaida.setBounds(6, 62, 172, 34);
		btnConfirmarSaida.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/Saida.png")));
		btnConfirmarSaida.setForeground(Color.WHITE);
		add(btnConfirmarSaida);
		
		String[] colunas = {"ID Reserva", "Nome Cliente", "Data Entrada", "Data Saída", "Quarto"};
		modelTable = new DefaultTableModel(null, colunas);
		tabelaReservas = new JTable(modelTable);
		JScrollPane scrollPaneCheckout = new JScrollPane(tabelaReservas);
		scrollPaneCheckout.setBounds(6, 120, 1280, 616);
		add(scrollPaneCheckout);
	
	    Reserve.pesquisarReservasCheckout(modelTable, tabelaReservas); // 🔥 Ago
	    
		JButton btnNewButtonEditReserve = new JButton("Estornar Pagamento");

		btnNewButtonEditReserve.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButtonEditReserve.setBounds(198, 62, 172, 34);
		btnNewButtonEditReserve.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/Banknotes.png")));
		btnNewButtonEditReserve.setForeground(Color.WHITE);
		add(btnNewButtonEditReserve);
		
		JButton btnNewButtonSearch = new JButton("");
		btnNewButtonSearch.setBounds(837, 75, 64, 34);
		btnNewButtonSearch.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/icons8-pesquisar-30.png")));
		add(btnNewButtonSearch);
		
		textField = new JTextField();
		textField.setBounds(913, 75, 253, 34);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField.setColumns(10);
		add(textField);
		
		JComboBox<String> comboBoxFilter = new JComboBox<String>();
		comboBoxFilter.setBounds(1178, 75, 108, 34);
		comboBoxFilter.setForeground(Color.WHITE);
		add(comboBoxFilter);
		
		JPanel panelCustomFooter_1 = new JPanel();
		panelCustomFooter_1.setLayout(null);
		panelCustomFooter_1.setBackground(Color.DARK_GRAY);
		panelCustomFooter_1.setBounds(6, 770, 1057, 64);
		add(panelCustomFooter_1);
		
		JLabel lblReservaSelecionada = new JLabel("-");
		lblReservaSelecionada.setForeground(Color.ORANGE);
		lblReservaSelecionada.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReservaSelecionada.setBounds(1209, 787, 108, 26);
		add(lblReservaSelecionada);
		
		JLabel lblReservaSelecionada_1 = new JLabel("ID Reserva:");
        lblReservaSelecionada_1.setForeground(Color.ORANGE);
        lblReservaSelecionada_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblReservaSelecionada_1.setBounds(1109, 787, 102, 26);
        add(lblReservaSelecionada_1);
		boolean pagamentoAtivo = false;
		
		tabelaReservas.getSelectionModel().addListSelectionListener(event -> {
		    if (!event.getValueIsAdjusting()) { 
		        int selectedRow = tabelaReservas.getSelectedRow();

		        if (selectedRow == -1) { 
		            selectedRow = 0; 
		            System.out.println("Inicializando selectedRow = 0");
		            return; 
		        }

		        int reservaId = (int) modelTable.getValueAt(selectedRow, 0); 
		        lblReservaSelecionada.setText(String.valueOf(reservaId)); 
		        
		        

		       
		       
		        
		        

		       //preencherCamposCheckout(reservaId); // ✅ Automatically fills checkout form
		    }
		});

		
	
		
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
		    	
		        int selectedRow = tabelaReservas.getSelectedRow();

		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Selecione corretamente uma reserva.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int reservaId = (int) modelTable.getValueAt(selectedRow, 0);

		        System.out.println("Reserva selecionada: ID " + reservaId);

		        CheckoutModuleWindow window = new CheckoutModuleWindow();
		        window.preencherCamposCheckout(reservaId); 
		        window.frameCheckoutPayment.setVisible(true);
		        
		        
		    }
		});
		btnNewButtonEditReserve.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tabelaReservas.getSelectedRow();

		        if (selectedRow == -1) { 
		            JOptionPane.showMessageDialog(null, "Selecione corretamente uma reserva.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int reservaId = (int) modelTable.getValueAt(selectedRow, 0); 
		        System.out.println("Reserva selecionada para alteração: ID " + reservaId);

		        
		        Reserve.pesquisarReservasCheckout(modelTable, tabelaReservas);

		        JOptionPane.showMessageDialog(null, "Status do pagamento foi alterado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		    }
		});
		
		

		
	}
}
