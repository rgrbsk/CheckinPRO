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
import systemReports.GerarRelatorios;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
//importar jasper
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.Map;


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
	private JTextField textParcelas;
	private JTextField textFrigobar;
	private JTextField textOutros;
	private JTextField textCpfCliente;
	private JTextField textIdCliente;
	private JTextField textValorInicial;
	private JTextField textValorComServicos;
	private JTextField textNumeroDiarias;
	private JTextField textDataCheckin;
	private JTextField textDataCheckout;
	private JTable tabelaConsumption;
	private JTextField textPercentageDiscount;

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
    	    scrollPaneConsumo.setBounds(0, 55, 650, 400);
    	    consumptionPanel.add(scrollPaneConsumo);

    	  
    	    JLabel lblTotalConsumo = new JLabel("Desconto (-)");
    	    lblTotalConsumo.setForeground(Color.GREEN);
    	    lblTotalConsumo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblTotalConsumo.setBounds(518, 460, 132, 22);
    	    consumptionPanel.add(lblTotalConsumo);

    	    JTextField textValorDesconto = new JTextField();
    	    textValorDesconto.setText("0");
    	    textValorDesconto.setBounds(500, 488, 150, 22);
    	    consumptionPanel.add(textValorDesconto);
    	    
    	    JLabel lblProdFrigobar = new JLabel("Adicional Frigobar ($)");
    	    lblProdFrigobar.setForeground(Color.GREEN);
    	    lblProdFrigobar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblProdFrigobar.setBounds(274, 460, 181, 22);
    	    consumptionPanel.add(lblProdFrigobar);
    	    
    	    textFrigobar = new JTextField();
    	    textFrigobar.setText("0");
    	    textFrigobar.setBounds(274, 489, 164, 22);
    	    consumptionPanel.add(textFrigobar);
    	    
    	    JLabel lblDanosAoQuarto = new JLabel("Valores Adicionais ($)");
    	    lblDanosAoQuarto.setForeground(new Color(128, 0, 0));
    	    lblDanosAoQuarto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblDanosAoQuarto.setBounds(47, 460, 150, 22);
    	    consumptionPanel.add(lblDanosAoQuarto);
    	    
    	    textOutros = new JTextField();
    	    textOutros.setText("0");
    	    textOutros.setBounds(47, 488, 150, 22);
    	    consumptionPanel.add(textOutros);
    	    
    	    textValorInicial = new JTextField();
    	    textValorInicial.setEditable(false);
    	    textValorInicial.setBounds(114, 22, 150, 22);
    	    consumptionPanel.add(textValorInicial);
    	    
    	    textValorComServicos = new JTextField();
    	    textValorComServicos.setEditable(false);
    	    textValorComServicos.setBounds(444, 22, 150, 22);
    	    consumptionPanel.add(textValorComServicos);
    	    
    	    JLabel lblNewLabel_2 = new JLabel("Valor Inicial");
    	    lblNewLabel_2.setBounds(47, 25, 119, 14);
    	    consumptionPanel.add(lblNewLabel_2);
    	    
    	    JLabel lblNewLabel_2_1 = new JLabel("Valor Final");
    	    lblNewLabel_2_1.setBounds(378, 26, 73, 14);
    	    consumptionPanel.add(lblNewLabel_2_1);
    	    
    	    JButton btnBuscarServicos = new JButton("Calcular");
    	    btnBuscarServicos.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Estimate.png")));
    	    

    	    
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
    	    textValorTotal.setText("0");
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
    	    btnPagCartao.setBounds(86, 128, 168, 44);
    	    panelCheckDate_1.add(btnPagCartao);
    	    
    	    JComboBox comboCartoes = new JComboBox();
    	    
    	    comboCartoes.setBounds(86, 168, 168, 22);
    	    panelCheckDate_1.add(comboCartoes);
    	    comboCartoes.addItem("Cartão de Débito");
    	    comboCartoes.addItem("Cartão de Crédito");
    	    
    	    
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
    	    btnPagPix.setBounds(86, 249, 168, 44);
    	    panelCheckDate_1.add(btnPagPix);
    	    
    	    textParcelas = new JTextField();
    	    textParcelas.setColumns(10);
    	    textParcelas.setBounds(264, 168, 45, 22);
    	    panelCheckDate_1.add(textParcelas);
    	    
    	    JLabel labelParcelas = new JLabel("Parcelas");
    	    labelParcelas.setForeground(Color.GREEN);
    	    labelParcelas.setBounds(264, 134, 45, 14);
    	    panelCheckDate_1.add(labelParcelas);
    	    labelParcelas.setVisible(false);
    	    textParcelas.setVisible(false);
    	    
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
    	    
    	    btnBuscarServicos.setBounds(258, 550, 205, 52);
    	    consumptionPanel.add(btnBuscarServicos);
    	    
    	    JButton btnPercentage = new JButton("");
    	    
    	    btnPercentage.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/DiscountSized.png")));
    	    btnPercentage.setBounds(519, 542, 53, 40);
    	    consumptionPanel.add(btnPercentage);
    	    btnPercentage.setVisible(false);
    	    
    	    textPercentageDiscount = new JTextField();
    	    textPercentageDiscount.setEditable(false);
    	    textPercentageDiscount.setBounds(569, 550, 53, 22);
    	    consumptionPanel.add(textPercentageDiscount);
    	    textPercentageDiscount.setVisible(false);
    	    
    	    JCheckBox checkDescontoPorcentagem = new JCheckBox("Desconto Por Porcentagem");
    	    checkDescontoPorcentagem.addActionListener(new ActionListener() {
    	    	public void actionPerformed(ActionEvent e) {
					if (checkDescontoPorcentagem.isSelected()) {
						btnPercentage.setVisible(true);
						textPercentageDiscount.setVisible(true);
						textPercentageDiscount.setEditable(true);
					} else {
						btnPercentage.setVisible(false);
						textPercentageDiscount.setVisible(false);
						textPercentageDiscount.setEditable(false);
						textPercentageDiscount.setText("0");
					}
    	    	}
    	    });
    	    checkDescontoPorcentagem.setBounds(496, 516, 181, 21);
    	    consumptionPanel.add(checkDescontoPorcentagem);
    	    
    	    JLabel infoInicial = new JLabel("");
    	    infoInicial.setToolTipText("(Valor do Quarto (Diária) x Número de Hóspedes) + Valor Total dos Serviços = Valor Inicial");
    	    infoInicial.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Info.png")));
    	    infoInicial.setBounds(270, 22, 25, 22);
    	    consumptionPanel.add(infoInicial);
    	    
    	    JLabel infoFinal = new JLabel("");
    	    infoFinal.setToolTipText("Valor Inicial + Frigobar + Outros - Desconto = Valor Final");
    	    infoFinal.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Info.png")));
    	    infoFinal.setBounds(600, 22, 25, 22);
    	    consumptionPanel.add(infoFinal);
    	    
    	    
    	    comboCartoes.addActionListener(new ActionListener() {
    	    	public void actionPerformed(ActionEvent e) {
					if (comboCartoes.getSelectedItem() != null) {
						String selectedItem = comboCartoes.getSelectedItem().toString();
						if (selectedItem.equals("Cartão de Crédito")) {
							textParcelas.setVisible(true);
							labelParcelas.setVisible(true);
						} else {
							textParcelas.setVisible(false);
							labelParcelas.setVisible(false);
						}
					}
    	    	}
    	    });
    	    
    	    tabbedPane.addChangeListener(e -> {
    	        int selectedIndex = tabbedPane.getSelectedIndex();
    	        String tabTitle = tabbedPane.getTitleAt(selectedIndex);

    	        if ("Consumo".equals(tabTitle)) { 
    	            btnBuscarServicos.doClick(); 
    	        }
    	    });
    	    btnPercentage.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) { 	            
    	            String porcentagemTexto = textPercentageDiscount.getText().replace(",", ".");
    	            double porcentagem = porcentagemTexto.isEmpty() ? 0.0 : Double.parseDouble(porcentagemTexto);

    	            if (porcentagem < 1 || porcentagem > 100) {
    	                JOptionPane.showMessageDialog(null, "Porcentagem inválida! Deve ser entre 1 e 100.", "Erro",
    	                        JOptionPane.ERROR_MESSAGE);
    	                return;
    	            }
    	            double totalBase = textValorInicial.getText().isEmpty() ? 0.0 : Double.parseDouble(textValorInicial.getText().replace(",", "."));
    	            double valorOutros = textOutros.getText().isEmpty() ? 0.0 : Double.parseDouble(textOutros.getText().replace(",", "."));
    	            double valorFrigobar = textFrigobar.getText().isEmpty() ? 0.0 : Double.parseDouble(textFrigobar.getText().replace(",", "."));

    	            double totalComExtras = totalBase + valorOutros + valorFrigobar;

    	            double descontoCalculado = totalComExtras * (porcentagem / 100);
    	            double valorFinal = totalComExtras - descontoCalculado;

    	            JOptionPane.showMessageDialog(null, "Desconto aplicado: " + porcentagem + "%", "Desconto",
    	                    JOptionPane.INFORMATION_MESSAGE);

    	            // Atualizar os campos corretamente
    	            textValorTotal.setText(String.format("%.2f", valorFinal));
    	            textValorComServicos.setText(String.format("%.2f", valorFinal));
    	           
    	            textValorDesconto.setText(String.format("%.2f", descontoCalculado)); // Exibir o valor do desconto aplicado
    	        }
    	    });

    	    
    	    btnBuscarServicos.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            String textoId = textIdReserva.getText().trim();
    	            if (textoId.isEmpty()) {
    	                JOptionPane.showMessageDialog(null, "Informe um ID de reserva válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
    	                return;
    	            }

    	            try {
    	                int reservaId = Integer.parseInt(textoId);

    	                double totalBase = ReserveService.calcularValorTotalReserva(reservaId);
    	                textValorInicial.setText(String.format("%.2f", totalBase));
    	                
    	                double valorOutros = textOutros.getText().isEmpty() ? 0.0 : Double.parseDouble(textOutros.getText());
    	                double valorFrigobar = textFrigobar.getText().isEmpty() ? 0.0 : Double.parseDouble(textFrigobar.getText());
    	                
    	                String valorDescTexto = textValorDesconto.getText().trim().replace(",", ".");
    	                double valorDesconto = valorDescTexto.isEmpty() ? 0.0 : Double.parseDouble(valorDescTexto);

    	                double valorFinal =  totalBase + valorOutros + valorFrigobar - valorDesconto;
    	                
    	                textValorComServicos.setText(String.format("%.2f", valorFinal));
    	                textValorTotal.setText(String.format("%.2f", valorFinal));
    	               
    	                List<ServicoConsumo> consumos = ReserveService.buscarServicosVinculados(reservaId);
    	                DefaultTableModel model = (DefaultTableModel) tabelaConsumption.getModel();
    	                model.setRowCount(0);

    	                for (ServicoConsumo item : consumos) {
    	                    model.addRow(new Object[]{
    	                        item.getNome(),
    	                        item.getQuantidade(),
    	                        String.format("R$ %.2f", item.getValorUnitario()),
    	                        String.format("R$ %.2f", item.getTotal())
    	                    });
    	                }
    	            
    	            } catch (Exception ex) {
    	                JOptionPane.showMessageDialog(null, "Erro ao buscar dados da reserva: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    	                ex.printStackTrace();
    	            }
    	        }
    	    });
    	    
    	    
    	    
    	    
    	    btnFinalizarPagamento.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            try {
    	                int idCliente = Integer.parseInt(textIdCliente.getText().trim());
    	                int reservaId = Integer.parseInt(textIdReserva.getText().trim());

    	                String textoValor = textValorTotal.getText()
    	                    .replace("R$", "")
    	                    .replace(".", "")
    	                    .replace(",", ".")
    	                    .trim();

    	                double valorTotal = Double.parseDouble(textoValor);

    	                String metodoPagamento = textMetodoPagamento.getText().trim();
    	                if (metodoPagamento.isEmpty()) {
    	                    JOptionPane.showMessageDialog(null, "Informe o método de pagamento.", "Aviso", JOptionPane.WARNING_MESSAGE);
    	                    return;
    	                }

    	                String dataPagamento = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    	                int resultado = Payment.registrarPagamento(idCliente, reservaId, valorTotal, metodoPagamento, "Concluído", dataPagamento);
    	                int statusAlterado = Payment.statusReserva(reservaId);
    	                System.out.println("Linhas atualizadas no statusReserva: " + statusAlterado);

    	                if (resultado > 0) {
    	                    JOptionPane.showMessageDialog(null, "Pagamento registrado com sucesso! \n A reserva foi concluída.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    	                    frameCheckoutPayment.dispose();

    	                    String[] options = {"Sim", "Não"};
    	                    int confirm = JOptionPane.showOptionDialog(
    	                        null,
    	                        "Deseja emitir o recibo de estadia?",
    	                        "Emissão de Recibo",
    	                        JOptionPane.YES_NO_OPTION,
    	                        JOptionPane.QUESTION_MESSAGE,
    	                        null,
    	                        options,
    	                        options[0]
    	                    );

    	                    if (confirm == JOptionPane.YES_OPTION) {
    	                    	if (confirm == JOptionPane.YES_OPTION) {
    	                    	    GerarRelatorios.gerarRecibo(reservaId);
    	                    	}
								//JasperCompileManager.compileReportToFile("src/systemReports/ReciboPdf.jrxml",
										//"src/systemReports/ReciboPdf.jasper");
								//JasperPrint jasperPrint = JasperFillManager.fillReport(
										//"src/systemReports/ReciboPdf.jasper", null, Conexao.getConnection());
								//JasperViewer.viewReport(jasperPrint, false);
    	                    }

    	                } else {
    	                    JOptionPane.showMessageDialog(null,
    	                        "Erro ao registrar pagamento.\nVerifique se já existe um pagamento para esta reserva.\nEstorne os pagamentos existentes antes de prosseguir.",
    	                        "Erro", JOptionPane.ERROR_MESSAGE);
    	                }

    	            } catch (NumberFormatException ex) {
    	                JOptionPane.showMessageDialog(null, "Erro de conversão numérica. Verifique os campos preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
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
					String selectedItem = comboCartoes.getSelectedItem().toString();
					if (selectedItem.equals("Cartão de Crédito")) {
						textMetodoPagamento.setText("Crédito");
					} else {
						textMetodoPagamento.setText("Débito");
					}
					
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
	


	public void preencherCamposCheckout(int reservaId) {
	    Reserve reserva = Reserve.buscarReservaPorId(reservaId);

	    if (reserva != null) {
	        textNomeCliente.setText(reserva.getCliente().getNome());
	        textCpfCliente.setText(reserva.getCliente().getCpf());
	        textIdCliente.setText(String.valueOf(reserva.getCliente().getId()));
	        textIdQuarto.setText(String.valueOf(reserva.getNumero_quarto()));
	        textIdReserva.setText(String.valueOf(reserva.getId()));

	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        textDataCheckin.setText(dateFormat.format(reserva.getDataCheckin()));
	        textDataCheckout.setText(dateFormat.format(reserva.getDataCheckout()));

	        if (textNumeroDiarias  != null) {
	            textNumeroDiarias.setText(String.valueOf(reserva.getNumeroDiarias()));
	            
	        }

	        try {
	            double total = ReserveService.calcularValorTotalReserva(reserva.getId());
	            textValorInicial.setText(String.format("%.2f", total));
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Erro ao calcular valor total: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }

	    } else {
	        JOptionPane.showMessageDialog(null, "Erro ao carregar reserva!", "Aviso", JOptionPane.WARNING_MESSAGE);
	    }
	}	
	}