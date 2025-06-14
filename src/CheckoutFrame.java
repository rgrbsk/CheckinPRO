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
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import addServiceReserve.ServiceReserveWindow;
import clienteWindow.AddClientWindow;
import clienteWindow.ClientHistoricWindow;
import conexao.Conexao;
import objects.Client;
import objects.Reserve;
import objects.Service;
import paymentFunction.Payment;
import reserveService.ReserveService;
import servicoConsumo.ServicoConsumo;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class CheckoutFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCheckout;
	private JTextField textField;
	private JTable tabelaReservas;
	private DefaultTableModel modelTable;
	public int reservaId;

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
		btnConfirmarSaida.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/Share.png")));
		btnConfirmarSaida.setForeground(Color.WHITE);
		add(btnConfirmarSaida);
		
		String[] colunas = {"ID Reserva", "Nome Cliente", "Data Entrada", "Data Saída", "Quarto"};
		modelTable = new DefaultTableModel(null, colunas);
		tabelaReservas = new JTable(modelTable);
		JScrollPane scrollPaneCheckout = new JScrollPane(tabelaReservas);
		scrollPaneCheckout.setBounds(6, 120, 1280, 616);
		add(scrollPaneCheckout);
		tabelaReservas.setShowGrid(true); 
	
	    Reserve.pesquisarReservasCheckout(modelTable, tabelaReservas); // 🔥 Ago
	    
		JButton btnEstornarPagamento = new JButton("Estornar Pagamento");

		btnEstornarPagamento.setHorizontalAlignment(SwingConstants.LEFT);
		btnEstornarPagamento.setBounds(198, 62, 193, 34);
		btnEstornarPagamento.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/Banknotes.png")));
		btnEstornarPagamento.setForeground(Color.WHITE);
		add(btnEstornarPagamento);
		
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
		
		JLabel lblReservaSelecionada = new JLabel("-");
		lblReservaSelecionada.setForeground(Color.ORANGE);
		lblReservaSelecionada.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReservaSelecionada.setBounds(699, 758, 108, 26);
		add(lblReservaSelecionada);
		
		JLabel lblReservaSelecionada_1 = new JLabel("ID Reserva:");
        lblReservaSelecionada_1.setForeground(Color.ORANGE);
        lblReservaSelecionada_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblReservaSelecionada_1.setBounds(599, 758, 102, 26);
        add(lblReservaSelecionada_1);
		boolean pagamentoAtivo = false;
		
		
		tabelaReservas.getSelectionModel().addListSelectionListener(event -> {
		    if (!event.getValueIsAdjusting()) { 
		        int selectedRow = tabelaReservas.getSelectedRow();

		        if (selectedRow != -1) {
		            reservaId = (int) modelTable.getValueAt(selectedRow, 0);
		        }
		    }
		});
		
		Reserve reserva = new Reserve(reservaId);
		
	
		
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
		        ;
		        JButton btnAdicionarServico = new JButton("Adicionar Serviço");
		        btnAdicionarServico.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        	}
		        });
		       
		        

		    }
		});
		
        
        JButton btnAddServicoReserva = new JButton("Adicionar Serviço");
        btnAddServicoReserva.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  ServiceReserveWindow serviceWindow = new ServiceReserveWindow(reservaId); 
        	        serviceWindow.frameServiceReserve.setVisible(true);

        	
        	}
        });
        btnAddServicoReserva.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/Bell32.png")));
        btnAddServicoReserva.setHorizontalAlignment(SwingConstants.LEFT);
        btnAddServicoReserva.setForeground(Color.WHITE);
        btnAddServicoReserva.setBounds(416, 62, 172, 34);
        add(btnAddServicoReserva);
        
        JButton btnVerServicos = new JButton("Ver Serviços");
        
        btnVerServicos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabelaReservas.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null,
                        "Selecione corretamente uma reserva.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int reservaId = (int) modelTable.getValueAt(selectedRow, 0);
                List<ServicoConsumo> listaServicos = ReserveService.buscarServicosVinculados(reservaId);

                if (listaServicos.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                        "Nenhum serviço encontrado para esta reserva.",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder sb = new StringBuilder();
                    double totalGeral = 0.0;

                  
                    sb.append(String.format("%-30s | %-10s | %-15s | %-12s%n",
                        "Serviço", "Quantidade", "Valor Unitário", "Total"));
                    sb.append("-------------------------------------------------------------------------------\n"); //eu sei que e um pouco longo, mas é para manter o alinhamento

                
                    for (ServicoConsumo servico : listaServicos) {
                        double totalParcial = servico.getTotal();
                        totalGeral += totalParcial;

                        sb.append(String.format("%-30s | %-10d | R$ %-12.2f | R$ %-8.2f%n",
                            servico.getNome(),
                            servico.getQuantidade(),
                            servico.getValorUnitario(),
                            totalParcial));
                    }

                
                    sb.append("-------------------------------------------------------------------------------\n");
                    sb.append(String.format("%-59s R$ %.2f", "Total de serviços até o momento:", totalGeral));

                   
                    JTextArea areaTexto = new JTextArea(sb.toString());
                    areaTexto.setFont(new Font("Consolas", Font.PLAIN, 13));
                    areaTexto.setEditable(false);
                    JScrollPane scroll = new JScrollPane(areaTexto);

                    JOptionPane.showMessageDialog(null, scroll, "Serviços da Reserva", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnVerServicos.setIcon(new ImageIcon(CheckoutFrame.class.getResource("/img/Contacts40.png")));
        btnVerServicos.setHorizontalAlignment(SwingConstants.LEFT);
        btnVerServicos.setForeground(Color.WHITE);
        btnVerServicos.setBounds(606, 62, 172, 34);
        add(btnVerServicos);

		
		

		
	
		
	
		btnNewButtonSearch.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Reserve.pesquisarReservasCheckout(modelTable, tabelaReservas); 
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
		btnEstornarPagamento.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String[] options = {"Sim", "Não"};
		        int confirm = JOptionPane.showOptionDialog(
		            null, 
		            "Deseja estornar o pagamento da reserva selecionada?",
		            "Confirmação de Exclusão", 
		            JOptionPane.YES_NO_OPTION, 
		            JOptionPane.QUESTION_MESSAGE, 
		            null, // No cu
		            options, 
		            options[0]
		        );
		        if (confirm == 0) {
		            int deletedRows = Payment.estornarPagamento(reservaId);

		            if (deletedRows > 0) {
		                JOptionPane.showMessageDialog(null, "Pagamento estornado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "Erro ao estornar pagamento", "Erro", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Estorno cancelado pelo usuário.");
		        }
		    	
		        int selectedRow = tabelaReservas.getSelectedRow();

		        if (selectedRow == -1) { 
		            JOptionPane.showMessageDialog(null, "Selecione corretamente uma reserva.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int reservaId = (int) modelTable.getValueAt(selectedRow, 0); 
		        System.out.println("Reserva selecionada para alteração: ID " + reservaId);

		        
		        Payment.estornarPagamento(reservaId);

		        
		    }
		});
		
		

		
	}
}
