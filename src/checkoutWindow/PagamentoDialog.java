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

public class PagamentoDialog extends JPanel {

    public JFrame frameAddReserve;
    private DefaultTableModel model;
    private JFrame frame; // ✅ Declare a variável do JFrame
    private JTextField textField;
    private JTextField textField_1;
	public Object MainPanelOutros;

    public JFrame getFrame() {
        return frame; // ✅ Certifique-se de que retorna a instância correta
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PagamentoDialog window = new PagamentoDialog();
                    window.frameAddReserve.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PagamentoDialog() {
        initialize();
        frame = new JFrame(); // ✅ Initialize JFrame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



    }

    public void initialize() {
    	
    	    frameAddReserve = new JFrame();
    	    frameAddReserve.setResizable(false);
    	    frameAddReserve.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	    frameAddReserve.setBounds(100, 100, 628, 190);
    	    frameAddReserve.setLocationRelativeTo(null);
    	    frameAddReserve.getContentPane().setLayout(null);
    	    
    	    JPanel MainPanelOutros = new JPanel();
    	    MainPanelOutros.setBounds(-16, 0, 602, 140);
    	    frameAddReserve.getContentPane().add(MainPanelOutros);
    	    MainPanelOutros.setLayout(null);
    	    
    	    JLabel lblEspecifiqueOMtodo = new JLabel("Especifique o Método de Pagamento");
    	    lblEspecifiqueOMtodo.setForeground(Color.DARK_GRAY);
    	    lblEspecifiqueOMtodo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblEspecifiqueOMtodo.setBounds(30, 23, 253, 16);
    	    MainPanelOutros.add(lblEspecifiqueOMtodo);
    	    
    	    textField = new JTextField();
    	    textField.setColumns(10);
    	    textField.setBounds(30, 50, 253, 22);
    	    MainPanelOutros.add(textField);
    	    
    	    JLabel lblParcelas = new JLabel("Parcelas");
    	    lblParcelas.setForeground(Color.DARK_GRAY);
    	    lblParcelas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    	    lblParcelas.setBounds(345, 23, 183, 16);
    	    MainPanelOutros.add(lblParcelas);
    	    
    	    textField_1 = new JTextField();
    	    textField_1.setColumns(10);
    	    textField_1.setBounds(345, 50, 206, 22);
    	    MainPanelOutros.add(textField_1);
    	    
    	    JButton btnNewButton = new JButton("Ok");
    	    btnNewButton.setBounds(209, 106, 89, 23);
    	    MainPanelOutros.add(btnNewButton);
    	    
    	    JButton btnCancelr = new JButton("Cancelar");
    	    btnCancelr.setBounds(308, 106, 89, 23);
    	    MainPanelOutros.add(btnCancelr);
    	    // ✅ Add Table for Consumptions
    	    
    	    String[] colunasConsumo = {"Item", "Quantidade", "Valor Unitário", "Total"};
    	    DefaultTableModel modelConsumption = new DefaultTableModel(null, colunasConsumo);
    	    
    	}
}