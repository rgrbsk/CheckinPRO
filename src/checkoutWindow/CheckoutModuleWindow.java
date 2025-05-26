package checkoutWindow;

import java.awt.Color;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import conexao.Conexao;
import objects.Reserve;
import objects.Room;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;

//import PagamentoDialog;

public class CheckoutModuleWindow extends JPanel {

    public JFrame frameAddReserve;
    private DefaultTableModel model;
    private JFrame frame; // ✅ Declare a variável do JFrame
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;

    public JFrame getFrame() {
        return frame; // ✅ Certifique-se de que retorna a instância correta
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CheckoutModuleWindow window = new CheckoutModuleWindow();
                    window.frameAddReserve.setVisible(true);
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

    public void initialize() {
    	
    	    frameAddReserve = new JFrame();
    	    frameAddReserve.setResizable(false);
    	    frameAddReserve.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	    frameAddReserve.setBounds(100, 100, 750, 700);
    	    frameAddReserve.setLocationRelativeTo(null);
    	    frameAddReserve.getContentPane().setLayout(null);

    	    // ✅ Create a TabbedPane
    	    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	    tabbedPane.setBounds(10, 22, 714, 639);
    	    frameAddReserve.getContentPane().add(tabbedPane);
    	    
    	    

    	    // ✅ Checkout Panel
    	    JPanel checkoutPanel = new JPanel();
    	    checkoutPanel.setLayout(null);
    	    tabbedPane.addTab("Checkout", checkoutPanel);
    	 // ✅ Create Consumption Panel
    	    JPanel consumptionPanel = new JPanel();
    	   
    	    consumptionPanel.setLayout(null);
    	    tabbedPane.addTab("Consumo", consumptionPanel);
    	    // ✅ Add Table for Consumptions
    	    
    	    String[] colunasConsumo = {"Item", "Quantidade", "Valor Unitário", "Total"};
    	    DefaultTableModel modelConsumption = new DefaultTableModel(null, colunasConsumo);
    	    JTable tabelaConsumption = new JTable(modelConsumption);
    	    JScrollPane scrollPaneConsumption = new JScrollPane(tabelaConsumption);
    	    scrollPaneConsumption.setBounds(30, 50, 650, 400);
    	    consumptionPanel.add(scrollPaneConsumption);

    	    // ✅ Add Labels and Input Fields
    	    JLabel lblTotalConsumo = new JLabel("Serviços Extras");
    	    lblTotalConsumo.setForeground(Color.GREEN);
    	    lblTotalConsumo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblTotalConsumo.setBounds(30, 470, 200, 22);
    	    consumptionPanel.add(lblTotalConsumo);

    	    JTextField txtTotalConsumo = new JTextField();
    	    txtTotalConsumo.setBounds(200, 470, 150, 22);
    	    txtTotalConsumo.setEditable(false);
    	    consumptionPanel.add(txtTotalConsumo);

    	    // ✅ Add Button to Process Consumptions
    	    JButton btnFinalizarConsumo = new JButton("Confirmar Consumo");
    	    btnFinalizarConsumo.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Ok.png")));
    	    btnFinalizarConsumo.setBounds(259, 572, 200, 39);
    	    consumptionPanel.add(btnFinalizarConsumo);
    	    
    	    JLabel lblProdFrigobar = new JLabel("Prod. Frigobar");
    	    lblProdFrigobar.setForeground(Color.GREEN);
    	    lblProdFrigobar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblProdFrigobar.setBounds(417, 470, 119, 22);
    	    consumptionPanel.add(lblProdFrigobar);
    	    
    	    textField_7 = new JTextField();
    	    textField_7.setEditable(false);
    	    textField_7.setBounds(549, 470, 150, 22);
    	    consumptionPanel.add(textField_7);
    	    
    	    JLabel lblDanosAoQuarto = new JLabel("Danos ao Quarto");
    	    lblDanosAoQuarto.setForeground(new Color(128, 0, 0));
    	    lblDanosAoQuarto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblDanosAoQuarto.setBounds(30, 518, 200, 22);
    	    consumptionPanel.add(lblDanosAoQuarto);
    	    
    	    textField_8 = new JTextField();
    	    textField_8.setEditable(false);
    	    textField_8.setBounds(200, 518, 150, 22);
    	    consumptionPanel.add(textField_8);
    	    
    	    JButton btnNewButton = new JButton("");
    	    btnNewButton.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/icons8-pesquisar-30.png")));
    	    btnNewButton.setBounds(347, 461, 41, 32);
    	    consumptionPanel.add(btnNewButton);

    	    // ✅ Payment Panel
    	    JPanel paymentPanel = new JPanel();
    	    
    	    paymentPanel.setLayout(null);
    	    tabbedPane.addTab("Pagamento", paymentPanel);
    	

    	   

    	    
    	    JPanel panelCheckDate_2 = new JPanel();
    	    panelCheckDate_2.setLayout(null);
    	    panelCheckDate_2.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), 
    	                        new TitledBorder(null, "Conferir Datas", TitledBorder.CENTER, TitledBorder.TOP, null)));
    	   
    	    panelCheckDate_2.setBounds(203, 30, 333, 93);
    	    paymentPanel.add(panelCheckDate_2);
    	    
    	    textField_4 = new JTextField();
    	    textField_4.setColumns(10);
    	    textField_4.setBounds(117, 23, 206, 22);
    	    panelCheckDate_2.add(textField_4);
    	    
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
    	    
    	    textField_5 = new JTextField();
    	    textField_5.setColumns(10);
    	    textField_5.setBounds(117, 60, 206, 22);
    	    panelCheckDate_2.add(textField_5);
    	    
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
    	    
    	    JButton btnCarto_1 = new JButton("Cartão");
    	    btnCarto_1.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/MasterCard.png")));
    	    btnCarto_1.setBounds(86, 108, 168, 44);
    	    panelCheckDate_1.add(btnCarto_1);
    	    
    	    JButton btnPagCartao = new JButton("Transf. Bancária");
    	    btnPagCartao.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Paycheque.png")));
    	    btnPagCartao.setBounds(86, 203, 168, 44);
    	    panelCheckDate_1.add(btnPagCartao);
    	    
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
    	    
    	    JButton btnPagPix = new JButton("PIX QR Code");
    	    btnPagPix.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Qr Code.png")));
    	    btnPagPix.setBounds(86, 276, 168, 44);
    	    panelCheckDate_1.add(btnPagPix);
    	    
    	    textField_6 = new JTextField();
    	    textField_6.setColumns(10);
    	    textField_6.setBounds(259, 148, 64, 22);
    	    panelCheckDate_1.add(textField_6);
    	    
    	    JLabel lblNewLabel_1_1 = new JLabel("Parcelas");
    	    lblNewLabel_1_1.setForeground(Color.GREEN);
    	    lblNewLabel_1_1.setBounds(266, 123, 45, 14);
    	    panelCheckDate_1.add(lblNewLabel_1_1);
    	    
    	    JButton btnConfirm = new JButton("Finalizar");
    	    btnConfirm.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Ok.png")));
    	    btnConfirm.setBounds(203, 561, 157, 39);
    	    paymentPanel.add(btnConfirm);
    	    
    	    JButton btnClear = new JButton("Voltar");
    	    btnClear.setIcon(new ImageIcon(CheckoutModuleWindow.class.getResource("/img/Cancelar.png")));
    	    btnClear.setBounds(389, 561, 147, 39);
    	    paymentPanel.add(btnClear);
    	    
    	    JLabel lblCpf = new JLabel("CPF do Cliente *");
    	    lblCpf.setForeground(new Color(17, 193, 120));
    	    lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblCpf.setBounds(270, 240, 183, 16);
    	    checkoutPanel.add(lblCpf);
    	    
    	    JLabel lblRoomNumber = new JLabel("Id do Quarto *");
    	    lblRoomNumber.setForeground(new Color(17, 193, 120));
    	    lblRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblRoomNumber.setBounds(270, 172, 183, 16);
    	    checkoutPanel.add(lblRoomNumber);
    	    
    	    textField = new JTextField();
    	    textField.setColumns(10);
    	    textField.setBounds(270, 200, 206, 22);
    	    checkoutPanel.add(textField);
    	    
    	    JPanel panelCheckDate = new JPanel();
    	    panelCheckDate.setLayout(null);
    	    panelCheckDate.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), 
    	                        new TitledBorder(null, "Conferir Datas", TitledBorder.CENTER, TitledBorder.TOP, null)));
    	    panelCheckDate.setBackground(Color.DARK_GRAY);
    	    panelCheckDate.setBounds(270, 330, 206, 224);
    	    checkoutPanel.add(panelCheckDate);
    	    
    	    JDateChooser dateChooserArrive = new JDateChooser();
    	    dateChooserArrive.setBounds(27, 57, 152, 28);
    	    panelCheckDate.add(dateChooserArrive);
    	    
    	    JLabel lblArrivalDate = new JLabel("Data de entrada*");
    	    lblArrivalDate.setHorizontalAlignment(SwingConstants.CENTER);
    	    lblArrivalDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	    lblArrivalDate.setBounds(6, 36, 194, 16);
    	    panelCheckDate.add(lblArrivalDate);
    	    
    	    JLabel lblDepartureDate = new JLabel("Data de Saída");
    	    lblDepartureDate.setHorizontalAlignment(SwingConstants.CENTER);
    	    lblDepartureDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	    lblDepartureDate.setBounds(6, 103, 194, 16);
    	    panelCheckDate.add(lblDepartureDate);
    	    
    	    JDateChooser dateChooserDeparture = new JDateChooser();
    	    dateChooserDeparture.setBounds(27, 123, 152, 28);
    	    panelCheckDate.add(dateChooserDeparture);
    	    
    	    textField_1 = new JTextField();
    	    textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
    	    textField_1.setText("08:00");
    	    textField_1.setHorizontalAlignment(SwingConstants.CENTER);
    	    textField_1.setColumns(10);
    	    textField_1.setBounds(27, 189, 152, 20);
    	    panelCheckDate.add(textField_1);
    	    
    	    JLabel lblHorrio = new JLabel("Horário");
    	    lblHorrio.setHorizontalAlignment(SwingConstants.CENTER);
    	    lblHorrio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	    lblHorrio.setBounds(6, 172, 194, 16);
    	    panelCheckDate.add(lblHorrio);
    	    
    	    JLabel lblNewLabel_1 = new JLabel("Legenda: Campos seguidos de \" * \" são imutáveis.");
    	    lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 11));
    	    lblNewLabel_1.setForeground(Color.ORANGE);
    	    lblNewLabel_1.setBounds(227, 586, 292, 14);
    	    checkoutPanel.add(lblNewLabel_1);
    	    
    	    textField_2 = new JTextField();
    	    textField_2.setColumns(10);
    	    textField_2.setBounds(270, 67, 206, 22);
    	    checkoutPanel.add(textField_2);
    	    
    	    JLabel lblNomeDoCliente = new JLabel("Nome do Cliente *");
    	    lblNomeDoCliente.setForeground(new Color(17, 193, 120));
    	    lblNomeDoCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblNomeDoCliente.setBounds(270, 113, 183, 16);
    	    checkoutPanel.add(lblNomeDoCliente);
    	    
    	    textField_3 = new JTextField();
    	    textField_3.setColumns(10);
    	    textField_3.setBounds(270, 140, 206, 22);
    	    checkoutPanel.add(textField_3);
    	    
    	    textField_9 = new JTextField();
    	    textField_9.setColumns(10);
    	    textField_9.setBounds(270, 267, 206, 22);
    	    checkoutPanel.add(textField_9);
    	    
    	    JLabel lblReserva = new JLabel("Reserva *");
    	    lblReserva.setForeground(new Color(17, 193, 120));
    	    lblReserva.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblReserva.setBounds(270, 40, 183, 16);
    	    checkoutPanel.add(lblReserva);
    	    
    	    btnPagOutro.addActionListener(new ActionListener() {
    		    @Override
    		    public void actionPerformed(ActionEvent e) {
    		    	//PagamentoDialog pagamento = new PagamentoDialog();
                    //pagamento.MainPanelOutros.setVisible(true);
    		        
    		    }
    		});
    	    
    	}
}