package checkoutWindow;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import conexao.Conexao;
import objects.Client;
import objects.Reserve;
import objects.Room;
import paymentFunction.Payment;
import reserveService.ReserveService;
import servicoConsumo.ServicoConsumo;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;

//import PagamentoDialog;

public class CheckoutModuleWindow extends JPanel {
	private Reserve reserva;

	public JFrame frameCheckoutPayment;
	private DefaultTableModel model;
	private JFrame frame; 
	private JTextField textIdQuarto;
	private JTextField textIdReserva;
	private JTextField textNomeCliente;
	private JTextField textValorTotal;
	private JTextField textMetodoPagamento;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textCpfCliente;
	private JTextField textIdCliente;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textNumeroDiarias;
	private JTextField textDataCheckin;
	private JTextField textDataCheckout;
	private JTable tabelaConsumption;

	public JFrame getFrame() {
		return frame; // ✅ Certifique-se de que retorna a instância correta
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutModuleWindow window = new CheckoutModuleWindow();
					window.frameCheckoutPayment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CheckoutModuleWindow() {
		initialize();
		frame = new JFrame(); // ✅ Initialize JFrame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public CheckoutModuleWindow(Reserve reserva) {
		this.reserva = reserva;

	}

	public void initialize() {
		
			
    		
    	    frameCheckoutPayment = new JFrame();
    	    frameCheckoutPayment.setResizable(false);
    	    frameCheckoutPayment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	    frameCheckoutPayment.setBounds(100, 100, 750, 700);
    	    frameCheckoutPayment.setLocationRelativeTo(null);
    	    frameCheckoutPayment.getContentPane().setLayout(null);

    	    // ✅ Create a TabbedPane
    	    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	    tabbedPane.setBounds(10, 22, 714, 639);
    	    frameCheckoutPayment.getContentPane().add(tabbedPane);
    	    
    	    

    	  
    	    JPanel checkoutPanel = new JPanel();
    	    checkoutPanel.setLayout(null);
    	    tabbedPane.addTab("Checkout", checkoutPanel);
  
    	    JPanel consumptionPanel = new JPanel();
    	   
    	    consumptionPanel.setLayout(null);
    	    tabbedPane.addTab("Consumo", consumptionPanel);
    	    
    	    
    	    String[] colunasConsumo = {"Item", "Quantidade", "Valor Unitário", "Total"};
    	    DefaultTableModel modelConsumption = new DefaultTableModel(null, colunasConsumo);
    	    JTable tabelaConsumption = new JTable(modelConsumption);
    	    JScrollPane scrollPaneConsumo = new JScrollPane(tabelaConsumption);
    	    scrollPaneConsumo.setBounds(30, 50, 650, 400);
    	    consumptionPanel.add(scrollPaneConsumo);

    	  
    	    JLabel lblTotalConsumo = new JLabel("Serviços Extras");
    	    lblTotalConsumo.setForeground(Color.GREEN);
    	    lblTotalConsumo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblTotalConsumo.setBounds(58, 461, 132, 22);
    	    consumptionPanel.add(lblTotalConsumo);

    	    JTextField txtTotalConsumo = new JTextField();
    	    txtTotalConsumo.setBounds(40, 489, 150, 22);
    	    txtTotalConsumo.setEditable(false);
    	    consumptionPanel.add(txtTotalConsumo);
    	    
    	    JLabel lblProdFrigobar = new JLabel("Prod. Frigobar ($)");
    	    lblProdFrigobar.setForeground(Color.GREEN);
    	    lblProdFrigobar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblProdFrigobar.setBounds(298, 461, 138, 22);
    	    consumptionPanel.add(lblProdFrigobar);
    	    
    	    textField_7 = new JTextField();
    	    textField_7.setEditable(false);
    	    textField_7.setBounds(286, 489, 150, 22);
    	    consumptionPanel.add(textField_7);
    	    
    	    JLabel lblDanosAoQuarto = new JLabel("Outros ($)");
    	    lblDanosAoQuarto.setForeground(new Color(128, 0, 0));
    	    lblDanosAoQuarto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblDanosAoQuarto.setBounds(510, 461, 200, 22);
    	    consumptionPanel.add(lblDanosAoQuarto);
    	    
    	    textField_8 = new JTextField();
    	    textField_8.setEditable(false);
    	    textField_8.setBounds(510, 489, 150, 22);
    	    consumptionPanel.add(textField_8);
    	    
    	    JButton btnNewButton = new JButton("");
    	    btnNewButton.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/icons8-pesquisar-30.png")));
    	    btnNewButton.setBounds(187, 480, 41, 32);
    	    consumptionPanel.add(btnNewButton);
    	    
    	    textField = new JTextField();
    	    textField.setEditable(false);
    	    textField.setBounds(114, 22, 150, 22);
    	    consumptionPanel.add(textField);
    	    
    	    textField_1 = new JTextField();
    	    textField_1.setEditable(false);
    	    textField_1.setBounds(424, 22, 150, 22);
    	    consumptionPanel.add(textField_1);
    	    
    	    JLabel lblNewLabel_2 = new JLabel("Valor Inicial");
    	    lblNewLabel_2.setBounds(47, 25, 119, 14);
    	    consumptionPanel.add(lblNewLabel_2);
    	    
    	    JLabel lblNewLabel_2_1 = new JLabel("Valor c/ Consumo e Serv.");
    	    lblNewLabel_2_1.setBounds(286, 26, 150, 14);
    	    consumptionPanel.add(lblNewLabel_2_1);
    	    
    	    JButton btnFinalizarConsumo_1 = new JButton("");
    	    btnFinalizarConsumo_1.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/icons8-actualizar-30 (1).png")));
    	    btnFinalizarConsumo_1.setBounds(199, 561, 96, 39);
    	    consumptionPanel.add(btnFinalizarConsumo_1);
    	    
    	    JButton btnBuscarServicos = new JButton("Buscar Serviços");
    	    btnBuscarServicos.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/icons8-pesquisar-30.png")));
    	    

    	    
    	    JPanel paymentPanel = new JPanel();
    	    
    	    paymentPanel.setLayout(null);
    	    tabbedPane.addTab("Pagamento", paymentPanel);
    	

    	   

    	    
    	    JPanel panelCheckDate_2 = new JPanel();
    	    panelCheckDate_2.setLayout(null);
    	    panelCheckDate_2.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), 
    	                        new TitledBorder(null, "Cobrança", TitledBorder.CENTER, TitledBorder.TOP, null)));
    	   
    	    panelCheckDate_2.setBounds(203, 30, 333, 93);
    	    paymentPanel.add(panelCheckDate_2);
    	    
    	    textValorTotal = new JTextField();
    	    textValorTotal.setText("1");
    	    textValorTotal.setEditable(false);
    	    textValorTotal.setColumns(10);
    	    textValorTotal.setBounds(117, 23, 206, 22);
    	    panelCheckDate_2.add(textValorTotal);
    	    
    	    JLabel lblCpf_1 = new JLabel("Valor a Cobrar");
    	    lblCpf_1.setForeground(Color.LIGHT_GRAY);
    	    lblCpf_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	    lblCpf_1.setBounds(10, 23, 107, 16);
    	    panelCheckDate_2.add(lblCpf_1);
    	    
    	    JLabel lblCpf_1_1 = new JLabel("Pag. Selecionado");
    	    lblCpf_1_1.setForeground(Color.LIGHT_GRAY);
    	    lblCpf_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	    lblCpf_1_1.setBounds(10, 60, 107, 16);
    	    panelCheckDate_2.add(lblCpf_1_1);
    	    
    	    textMetodoPagamento = new JTextField();
    	    textMetodoPagamento.setForeground(Color.CYAN);
    	    textMetodoPagamento.setEditable(false);
     
    	    textMetodoPagamento.setColumns(10);
    	    textMetodoPagamento.setBounds(117, 60, 206, 22);
    	    panelCheckDate_2.add(textMetodoPagamento);
    	    
    	    JPanel panelCheckDate_1 = new JPanel();
    	    panelCheckDate_1.setLayout(null);
    	    panelCheckDate_1.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), 
    	                        new TitledBorder(null, "Especifique o meio de pagamento", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(187, 187, 187))));
    	    panelCheckDate_1.setBackground(Color.DARK_GRAY);
    	    panelCheckDate_1.setBounds(203, 135, 333, 410);
    	    paymentPanel.add(panelCheckDate_1);
    	    
    	    JButton btnPagDinheiro = new JButton("Dinheiro");
    	    btnPagDinheiro.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Banknotes.png")));
    	    btnPagDinheiro.setBounds(86, 32, 168, 44);
    	    panelCheckDate_1.add(btnPagDinheiro);
    	    
    	    JButton btnPagCartao = new JButton("Cartão");
    	    btnPagCartao.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/MasterCard.png")));
    	    btnPagCartao.setBounds(86, 108, 168, 44);
    	    panelCheckDate_1.add(btnPagCartao);
    	    
    	    JButton btnPagTransferencia = new JButton("Transf. Bancária");
    	    btnPagTransferencia.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Paycheque.png")));
    	    btnPagTransferencia.setBounds(86, 203, 168, 44);
    	    panelCheckDate_1.add(btnPagTransferencia);
    	    
    	    JComboBox comboCartoes = new JComboBox();
    	    comboCartoes.setBounds(86, 148, 168, 22);
    	    panelCheckDate_1.add(comboCartoes);
    	    comboCartoes.addItem("Cartão de Crédito");
    	    comboCartoes.addItem("Cartão de Débito");
    	    
    	    JButton btnPagOutro = new JButton("Outro");
    	    btnPagOutro.addActionListener(new ActionListener() {
    	    	public void actionPerformed(ActionEvent e) {
    	    	}
    	    });
    	    btnPagOutro.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/More.png")));
    	    btnPagOutro.setBounds(86, 355, 168, 44);
    	    panelCheckDate_1.add(btnPagOutro);
    	    
    	    JButton btnPagPix = new JButton("PIX");
    	    btnPagPix.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Pix.png")));
    	    btnPagPix.setBounds(86, 276, 168, 44);
    	    panelCheckDate_1.add(btnPagPix);
    	    
    	    textField_6 = new JTextField();
    	    textField_6.setColumns(10);
    	    textField_6.setBounds(264, 148, 45, 22);
    	    panelCheckDate_1.add(textField_6);
    	    
    	    JLabel lblNewLabel_1_1 = new JLabel("Parcelas");
    	    lblNewLabel_1_1.setForeground(Color.GREEN);
    	    lblNewLabel_1_1.setBounds(264, 134, 45, 14);
    	    panelCheckDate_1.add(lblNewLabel_1_1);
    	    
    	    JButton btnFinalizarPagamento = new JButton("Finalizar");
    	    
    	    btnFinalizarPagamento.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Ok.png")));
    	    btnFinalizarPagamento.setBounds(203, 561, 157, 39);
    	    paymentPanel.add(btnFinalizarPagamento);
    	    
    	    JButton btnClear = new JButton("Voltar");
    	    btnClear.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Cancelar.png")));
    	    btnClear.setBounds(389, 561, 147, 39);
    	    paymentPanel.add(btnClear);
    	    
    	    JLabel lblCpf = new JLabel("CPF do Cliente *");
    	    lblCpf.setForeground(new Color(17, 193, 120));
    	    lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblCpf.setBounds(261, 251, 183, 16);
    	    checkoutPanel.add(lblCpf);
    	    
    	    JLabel lblRoomNumber = new JLabel("Id do Quarto *");
    	    lblRoomNumber.setForeground(new Color(17, 193, 120));
    	    lblRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblRoomNumber.setBounds(261, 183, 183, 16);
    	    checkoutPanel.add(lblRoomNumber);
    	    
    	    textIdQuarto = new JTextField();
    	    textIdQuarto.setColumns(10);
    	    textIdQuarto.setBounds(261, 211, 206, 22);
    	    checkoutPanel.add(textIdQuarto);
    	    
    	    textIdReserva = new JTextField();
    	    textIdReserva.setColumns(10);
    	    textIdReserva.setBounds(261, 78, 206, 22);
    	    checkoutPanel.add(textIdReserva);
    	    
    	    JLabel lblNomeDoCliente = new JLabel("Nome do Cliente *");
    	    lblNomeDoCliente.setToolTipText("");
    	    lblNomeDoCliente.setForeground(new Color(17, 193, 120));
    	    lblNomeDoCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblNomeDoCliente.setBounds(261, 124, 139, 16);
    	    checkoutPanel.add(lblNomeDoCliente);
    	    
    	    textNomeCliente = new JTextField();
    	    textNomeCliente.setColumns(10);
    	    textNomeCliente.setBounds(261, 151, 206, 22);
    	    checkoutPanel.add(textNomeCliente);
    	    
    	    textCpfCliente = new JTextField();
    	    textCpfCliente.setColumns(10);
    	    textCpfCliente.setBounds(261, 278, 206, 22);
    	    checkoutPanel.add(textCpfCliente);
    	    
    	    JLabel lblReserva = new JLabel("Reserva *");
    	    lblReserva.setForeground(new Color(17, 193, 120));
    	    lblReserva.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblReserva.setBounds(261, 51, 183, 16);
    	    checkoutPanel.add(lblReserva);
    	    
    	    textIdCliente = new JTextField();
    	    textIdCliente.setColumns(10);
    	    textIdCliente.setBounds(399, 125, 68, 22);
    	    checkoutPanel.add(textIdCliente);
    	    
    	    JPanel panelCheckDate_2_1 = new JPanel();
    	    panelCheckDate_2_1.setLayout(null);
    	    panelCheckDate_2_1.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), 
    	        	                        new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null)));
    	    panelCheckDate_2_1.setBounds(261, 331, 206, 192);
    	    checkoutPanel.add(panelCheckDate_2_1);
    	    
    	    textDataCheckin = new JTextField();
    	    textDataCheckin.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	    textDataCheckin.setHorizontalAlignment(SwingConstants.CENTER);
    	    textDataCheckin.setColumns(10);
    	    textDataCheckin.setBounds(52, 27, 130, 22);
    	    panelCheckDate_2_1.add(textDataCheckin);
    	    
    	    textNumeroDiarias = new JTextField();
    	    textNumeroDiarias.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	    textNumeroDiarias.setHorizontalAlignment(SwingConstants.CENTER);
    	    textNumeroDiarias.setBounds(55, 159, 86, 22);
    	    panelCheckDate_2_1.add(textNumeroDiarias);
    	    textNumeroDiarias.setColumns(10);
    	    
    	    JLabel lblDirias = new JLabel("Diárias");
    	    lblDirias.setBounds(70, 142, 71, 16);
    	    panelCheckDate_2_1.add(lblDirias);
    	    lblDirias.setForeground(new Color(17, 193, 120));
    	    lblDirias.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    
    	    JLabel lblNewLabel = new JLabel("");
    	    lblNewLabel.setBounds(24, 23, 30, 30);
    	    panelCheckDate_2_1.add(lblNewLabel);
    	    lblNewLabel.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Calendar.png")));
    	    
    	    JLabel lblNewLabel_1 = new JLabel("");
    	    lblNewLabel_1.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Calendar.png")));
    	    lblNewLabel_1.setBounds(24, 81, 30, 30);
    	    panelCheckDate_2_1.add(lblNewLabel_1);
    	    
    	    textDataCheckout = new JTextField();
    	    textDataCheckout.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	    textDataCheckout.setHorizontalAlignment(SwingConstants.CENTER);
    	    textDataCheckout.setColumns(10);
    	    textDataCheckout.setBounds(52, 85, 130, 22);
    	    panelCheckDate_2_1.add(textDataCheckout);
    	    
    	    JLabel lblSada = new JLabel("Saída");
    	    lblSada.setForeground(new Color(17, 193, 120));
    	    lblSada.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblSada.setBounds(85, 70, 71, 16);
    	    panelCheckDate_2_1.add(lblSada);
    	    
    	    JLabel lblEntrada = new JLabel("Entrada");
    	    lblEntrada.setForeground(new Color(17, 193, 120));
    	    lblEntrada.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblEntrada.setBounds(85, 11, 71, 16);
    	    panelCheckDate_2_1.add(lblEntrada);
    	    textIdCliente.setVisible(false);
    	    
    	    btnBuscarServicos.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            try {
    	                int reservaId = textIdReserva.getText().isEmpty() ? 0 : Integer.parseInt(textIdReserva.getText());
    	                List<ServicoConsumo> consumos = ReserveService.buscarServicosVinculados(reservaId);

    	                DefaultTableModel model = (DefaultTableModel) tabelaConsumption.getModel();
    	                model.setRowCount(0); // Limpa a tabela

    	                for (ServicoConsumo item : consumos) {
    	                    model.addRow(new Object[] {
    	                        item.getNome(),
    	                        item.getQuantidade(),
    	                        String.format("R$ %.2f", item.getValorUnitario()),
    	                        String.format("R$ %.2f", item.getTotal())
    	                    });
    	                }
    	            } catch (NumberFormatException ex) {
    	                JOptionPane.showMessageDialog(null, "ID da reserva inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
    	            } catch (Exception ex) {
    	                JOptionPane.showMessageDialog(null, "Erro ao buscar serviços vinculados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	                ex.printStackTrace();
    	            }
    	        }
    	    });
    	    btnBuscarServicos.setBounds(298, 548, 164, 52);
    	    consumptionPanel.add(btnBuscarServicos);
    	    
    	    
    	    
    	    
    	    btnFinalizarPagamento.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	
    	            try {
    	               
    	                int id = Integer.parseInt(textIdCliente.getText());
    	                int reserveId = Integer.parseInt(textIdReserva.getText());
    	                double valorTotal = Double.parseDouble(textValorTotal.getText());
    	                String metodoPagamento = (String) textMetodoPagamento.getText();
    	              //  String statusPagamento = (String) extStatusPagamento.getText();
    	                long timestamp = System.currentTimeMillis(); // 
    	                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	                String dataPagamento = formato.format(new Date(timestamp)); 

    	              
    	                int resultado = Payment.registrarPagamento(1, reserveId, valorTotal, metodoPagamento, "Concluído", dataPagamento);

    	                if (resultado > 0) {
    	                	
    	                    JOptionPane.showMessageDialog(null, "Pagamento registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    	                    frameCheckoutPayment.dispose();
    	                    String[] options = {"Sim", "Não"};
    	    		        int confirm = JOptionPane.showOptionDialog(
    	    		            null, 
    	    		            "Deseja Emitir o Recibo de Estadia?", 
    	    		            "Emissão de Recibo", 
    	    		            JOptionPane.YES_NO_OPTION, 
    	    		            JOptionPane.QUESTION_MESSAGE, 
    	    		            null, // No cu
    	    		            options, 
    	    		            options[0]
    	    		            		
    	    		        );
    	                } else {
    	                    JOptionPane.showMessageDialog(null, "Erro ao registrar pagamento.", "Erro", JOptionPane.ERROR_MESSAGE);
    	                }

    	            } catch (Exception ex) {
    	                JOptionPane.showMessageDialog(null, "Erro ao processar pagamento: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	                ex.printStackTrace();
    	            }
    	        }
    	        
    	    
    	        
    	    });
    	    btnPagDinheiro.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            textMetodoPagamento.setText("Dinheiro");
    	        }
    	    });
			btnPagCartao.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					textMetodoPagamento.setText("Cartão");
				}
			});
			btnPagTransferencia.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					textMetodoPagamento.setText("Transferência Bancária");
				}
			});
			btnPagPix.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					textMetodoPagamento.setText("PIX");
				}
			});
			btnPagOutro.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					textMetodoPagamento.setText("Outro");
				}
			});
			btnClear.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frameCheckoutPayment.dispose();
				}
			});
	}
	public void preencherTabelaConsumo(int reservaId) {
	    DefaultTableModel model = (DefaultTableModel) tabelaConsumption.getModel();
	    model.setRowCount(0); // limpa a tabela

	    List<ServicoConsumo> consumos = ReserveService.buscarServicosVinculados(reservaId);

	    for (ServicoConsumo item : consumos) {
	        model.addRow(new Object[] {
	            item.getNome(),
	            item.getQuantidade(),
	            String.format("R$ %.2f", item.getValorUnitario()),
	            String.format("R$ %.2f", item.getTotal())
	        });
	    }
	}


	public void preencherCamposCheckout(int reservaId) {
	    Reserve reserva = Reserve.buscarReservaPorId(reservaId);

	    if (reserva != null) {
	        // Cliente
	        textNomeCliente.setText(reserva.getCliente().getNome());
	        textCpfCliente.setText(reserva.getCliente().getCpf());
	        textIdCliente.setText(String.valueOf(reserva.getCliente().getId()));

	     
	        textIdQuarto.setText(String.valueOf(reserva.getNumero_quarto()));
	        textIdReserva.setText(String.valueOf(reserva.getId()));

	       
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        textDataCheckin.setText(dateFormat.format(reserva.getDataCheckin()));
	        textDataCheckout.setText(dateFormat.format(reserva.getDataCheckout()));

	      
	        if (textNumeroDiarias != null) {
	            textNumeroDiarias.setText(String.valueOf(reserva.getNumeroDiarias()));
	        }

	    } else {
	        JOptionPane.showMessageDialog(null, "Erro ao carregar reserva!", "Aviso", JOptionPane.WARNING_MESSAGE);
	    }
	}}