package clienteWindow;
import objects.Client;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import objects.Client;
import objects.Reserve;

import javax.swing.ImageIcon;
import java.awt.Window.Type;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JScrollBar;

public class ClientHistoricWindow extends JFrame {
    private Client cliente;

    public ClientHistoricWindow(Client cliente) { 
        this.cliente = cliente;
        initialize();
        preencherCampos(cliente); 
        preencherHistorico(cliente.getId());

    }

    public void preencherCamposHistoric(Client cliente) {
        if (cliente != null) { 
            textIdCliente.setText(Integer.toString(cliente.getId())); 
            textNomeCliente.setText(cliente.getNome() != null ? cliente.getNome() : "Nome não informado");
            textCpfCliente.setText(cliente.getCpf() != null ? cliente.getCpf() : "CPF não informado");
        }
    }
    
    
    
    private void preencherHistorico(int clienteId) {
    	List<Reserve> reservas = Client.pesquisarHistoricoCliente(clienteId); // ✅ Chamada correta se estiver na classe `Client`

        String[] colunas = {"ID Reserva", "Data Check-in", "Data Check-out", "Status"};
        DefaultTableModel modelReservas = new DefaultTableModel(null, colunas);

     
        for (Reserve reserva : reservas) {
            modelReservas.addRow(new Object[]{
                reserva.getId(),
                reserva.getCheckinDate(),
                reserva.getCheckoutDate(),
                reserva.getStatus()
            });
        }

        JTable tabelaReservas = new JTable(modelReservas); // 
        scrollPaneHistoric.setViewportView(tabelaReservas); 
        tabelaReservas.setShowGrid(true); 
    }
    
    
    


	public JFrame getFrameHistoricoCliente() {
		return frameHistoricoCliente;
	}

	public void setFrameHistoricoCliente(JFrame frameHistoricoCliente) {
		this.frameHistoricoCliente = frameHistoricoCliente;
	}
	
	



	
	private JFrame frameHistoricoCliente;
	public JButton btnConcluirCadastro;
	public static boolean isEditMode;
	private JTextField textNomeCliente;
	private JTextField textCpfCliente;
	private JTextField textIdCliente;
	public JScrollPane scrollPaneHistoric;
	


	/**

	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientHistoricWindow window = new ClientHistoricWindow();
					window.frameHistoricoCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @param clienteId 
	
	 */
	public ClientHistoricWindow() {
	    initialize(); // 
	}
	public void preencherCampos(Client cliente) {
	    if (cliente != null) { // ✅
	    	textIdCliente.setText(Integer.toString(cliente.getId())); //
	        textNomeCliente.setText(cliente.getNome() != null ? cliente.getNome() : "Nome não informado");
	        textCpfCliente.setText(cliente.getCpf() != null ? cliente.getCpf() : "CPF não informado");
	    }
	}
	



	/**

	 */
	private void initialize() {
		
		
		

		frameHistoricoCliente = new JFrame();
		frameHistoricoCliente.setTitle("CheckinPRO - 2025");
		frameHistoricoCliente.setBounds(100, 100, 617, 704);
		frameHistoricoCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameHistoricoCliente.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(19, 19, 19));
		panel.setBounds(10, 11, 584, 661);
		frameHistoricoCliente.getContentPane().add(panel);
		
		
		JPanel customBar = new JPanel();
		customBar.setBackground(new Color(17, 193, 123));
		customBar.setBounds(0, 0, 598, 5);
		panel.add(customBar);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setLayout(null);
		panelCadastro.setBackground(new Color(55, 55, 55));
		panelCadastro.setBounds(0, 0, 584, 661);
		panel.add(panelCadastro);
		
		JPanel panelCustomHeader = new JPanel();
		panelCustomHeader.setLayout(null);
		panelCustomHeader.setBackground(Color.DARK_GRAY);
		panelCustomHeader.setBounds(0, 0, 584, 32);
		panelCadastro.add(panelCustomHeader);
		
		JLabel lblCadastroDeClientes = new JLabel("Histórico do Cliente");
		lblCadastroDeClientes.setForeground(new Color(17, 193, 120));
		lblCadastroDeClientes.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblCadastroDeClientes.setBounds(202, 0, 158, 35);
		panelCustomHeader.add(lblCadastroDeClientes);
		
		JPanel panelCustomFooter = new JPanel();
		panelCustomFooter.setLayout(null);
		panelCustomFooter.setBackground(Color.DARK_GRAY);
		panelCustomFooter.setBounds(0, 586, 584, 64);
		panelCadastro.add(panelCustomFooter);
		
		JButton btnCancelarCadastro = new JButton("Voltar");
		btnCancelarCadastro.setIcon(new ImageIcon(ClientHistoricWindow.class.getResource("/img/Undo.png")));
		btnCancelarCadastro.setBounds(205, 16, 174, 37);
		panelCustomFooter.add(btnCancelarCadastro);
		
		scrollPaneHistoric = new JScrollPane();
		scrollPaneHistoric.setEnabled(false);
		
		scrollPaneHistoric.setBounds(0, 55, 574, 536);
		panelCadastro.add(scrollPaneHistoric);
		

		
		textNomeCliente = new JTextField();
		textNomeCliente.setEditable(false);
		textNomeCliente.setBounds(160, 35, 214, 20);
		panelCadastro.add(textNomeCliente);
		textNomeCliente.setColumns(10);
		
		textCpfCliente = new JTextField();
		textCpfCliente.setEditable(false);
		textCpfCliente.setHorizontalAlignment(SwingConstants.CENTER);
		textCpfCliente.setColumns(10);
		textCpfCliente.setBounds(415, 35, 159, 20);
		panelCadastro.add(textCpfCliente);
		
		JLabel lblNewLabel = new JLabel("Nome -");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(119, 38, 46, 14);
		panelCadastro.add(lblNewLabel);
		
		JLabel lblCpf = new JLabel("CPF -");
		lblCpf.setForeground(Color.LIGHT_GRAY);
		lblCpf.setBounds(384, 38, 35, 14);
		panelCadastro.add(lblCpf);
		
		JLabel lblId = new JLabel("ID -");
		lblId.setForeground(Color.LIGHT_GRAY);
		lblId.setBounds(10, 38, 35, 14);
		panelCadastro.add(lblId);
		
		textIdCliente = new JTextField();
		textIdCliente.setEnabled(false);
		textIdCliente.setEditable(false);
		textIdCliente.setColumns(10);
		textIdCliente.setBounds(31, 35, 78, 20);
		panelCadastro.add(textIdCliente);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(574, 35, 10, 546);
		panelCadastro.add(scrollBar);
		
		

		
		
		
		
		
		
		

		
		btnCancelarCadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
			
			frameHistoricoCliente.dispose();
			}
		});
		
		
				
		
			

		
		
	}

	public JFrame getFrame() {
	    return frameHistoricoCliente;
	}

		    


	{
	
	
		
		
	    

	}	

}


