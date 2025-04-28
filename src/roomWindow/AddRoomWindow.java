package roomWindow;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import objects.Room;
import objects.Room.CadastrarQuarto;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.util.List;
import java.util.ArrayList;

public class AddRoomWindow {

    public JFrame frameAddRoom;
    private JTextField textFieldIdentificacaoQuarto;
    private JTextField textFieldAndarQuarto;
    private JSpinner spinnerValorQuarto;
    private JSpinner spinnerMaximoHospede;
    private JCheckBox CheckBoxFrigobar;
    private JCheckBox CheckBoxTv;
    private JTextArea textAreaDescricaoQuarto;
    private JSpinner spinnerNmrCamas;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddRoomWindow window = new AddRoomWindow();
                    window.frameAddRoom.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AddRoomWindow() {
        initialize();
    }

    public void initialize() {
    	
        frameAddRoom = new JFrame();
        frameAddRoom.setResizable(false);
        frameAddRoom.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAddRoom.setBounds(100, 100, 600, 700);
        frameAddRoom.setLocationRelativeTo(null);
        frameAddRoom.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(19, 19, 19));
        panel.setBounds(0, 0, 584, 661);
        frameAddRoom.getContentPane().add(panel);
        panel.setLayout(null);
        
        JPanel customBar = new JPanel();
        customBar.setBackground(new Color(17, 193, 123));
        customBar.setBounds(0, 0, 584, 5);
        panel.add(customBar);
        
        JPanel MainPanelAddRoom = new JPanel();
        MainPanelAddRoom.setBackground(new Color(55, 55, 55));
        MainPanelAddRoom.setBounds(6, 12, 572, 643);
        panel.add(MainPanelAddRoom);
        MainPanelAddRoom.setLayout(null);
        
        JPanel panelCustomHeader = new JPanel();
        panelCustomHeader.setBackground(Color.DARK_GRAY);
        panelCustomHeader.setBounds(0, 0, 572, 28);
        MainPanelAddRoom.add(panelCustomHeader);
        panelCustomHeader.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Cadastro de Quartos");
        lblNewLabel.setBounds(207, 0, 158, 24);
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblNewLabel.setForeground(new Color(17, 193, 120));
        panelCustomHeader.add(lblNewLabel);
        
        JLabel lblNewLabelIdentificacaoQuarto = new JLabel("Identificação do Quarto");
        lblNewLabelIdentificacaoQuarto.setForeground(new Color(17, 193, 120));
        lblNewLabelIdentificacaoQuarto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabelIdentificacaoQuarto.setBounds(64, 71, 183, 16);
        MainPanelAddRoom.add(lblNewLabelIdentificacaoQuarto);
        
        textFieldIdentificacaoQuarto = new JTextField();
        textFieldIdentificacaoQuarto.setBounds(64, 100, 206, 22);
        MainPanelAddRoom.add(textFieldIdentificacaoQuarto);
        textFieldIdentificacaoQuarto.setColumns(10);
        
        JLabel lblNewLabelAndar = new JLabel("Andar do Quarto");
        lblNewLabelAndar.setForeground(new Color(17, 193, 120));
        lblNewLabelAndar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabelAndar.setBounds(302, 71, 136, 16);
        MainPanelAddRoom.add(lblNewLabelAndar);
        
        textFieldAndarQuarto = new JTextField();
        textFieldAndarQuarto.setColumns(10);
        textFieldAndarQuarto.setBounds(302, 100, 211, 22);
        MainPanelAddRoom.add(textFieldAndarQuarto);
        
        JPanel panelCustomFooter = new JPanel();
        panelCustomFooter.setBackground(Color.DARK_GRAY);
        panelCustomFooter.setBounds(0, 579, 572, 64);
        MainPanelAddRoom.add(panelCustomFooter);
        panelCustomFooter.setLayout(null);
        
        JButton btnConcluir = new JButton("Concluir");
        btnConcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRoomDb();
            }
        });
        btnConcluir.setBounds(82, 14, 174, 37);
        panelCustomFooter.add(btnConcluir);
        
        JButton btnApagar = new JButton("Apagar");
        btnApagar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cleanFields();
        	}
        });
        btnApagar.setBounds(317, 14, 174, 37);
        panelCustomFooter.add(btnApagar);
        
        JLabel lblNewLabelValor = new JLabel("Valor do Quarto");
        lblNewLabelValor.setForeground(new Color(17, 193, 120));
        lblNewLabelValor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabelValor.setBounds(64, 156, 183, 16);
        MainPanelAddRoom.add(lblNewLabelValor);
        
        spinnerValorQuarto = new JSpinner();
        spinnerValorQuarto.setName("");
        spinnerValorQuarto.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(10)));
        spinnerValorQuarto.setBounds(64, 184, 206, 22);
        MainPanelAddRoom.add(spinnerValorQuarto);
        
        JLabel lblNewLabelDescricao = new JLabel("Descrição");
        lblNewLabelDescricao.setForeground(new Color(17, 193, 120));
        lblNewLabelDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabelDescricao.setBounds(64, 411, 183, 16);
        MainPanelAddRoom.add(lblNewLabelDescricao);
        
        textAreaDescricaoQuarto = new JTextArea();
        textAreaDescricaoQuarto.setLineWrap(true);
        textAreaDescricaoQuarto.setBounds(64, 439, 449, 76);
        MainPanelAddRoom.add(textAreaDescricaoQuarto);
        
        JLabel lblNewLabelMaxHospedes = new JLabel("Maximo de hóspedes");
        lblNewLabelMaxHospedes.setForeground(new Color(17, 193, 120));
        lblNewLabelMaxHospedes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabelMaxHospedes.setBounds(64, 239, 183, 16);
        MainPanelAddRoom.add(lblNewLabelMaxHospedes);
        
        spinnerMaximoHospede = new JSpinner();
        spinnerMaximoHospede.setName("");
        spinnerMaximoHospede.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerMaximoHospede.setBounds(64, 267, 206, 22);
        MainPanelAddRoom.add(spinnerMaximoHospede);
        
        JPanel panelAdicionais = new JPanel();
        panelAdicionais.setBackground(new Color(70, 73, 75));
        panelAdicionais.setBounds(302, 156, 211, 133);
        MainPanelAddRoom.add(panelAdicionais);
        panelAdicionais.setLayout(null);
        
        CheckBoxFrigobar = new JCheckBox(" Possui Frigobar");
        CheckBoxFrigobar.setBounds(6, 45, 186, 20);
        panelAdicionais.add(CheckBoxFrigobar);
        
        CheckBoxTv = new JCheckBox("Possui televisão");
        CheckBoxTv.setBounds(6, 77, 186, 20);
        panelAdicionais.add(CheckBoxTv);
        
        JLabel lblNewLabelAdicionais = new JLabel("Adicionais");
        lblNewLabelAdicionais.setForeground(new Color(17, 193, 120));
        lblNewLabelAdicionais.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabelAdicionais.setBounds(47, 6, 104, 16);
        panelAdicionais.add(lblNewLabelAdicionais);
        
        spinnerNmrCamas = new JSpinner();
        spinnerNmrCamas.setName("");
        spinnerNmrCamas.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerNmrCamas.setBounds(64, 351, 206, 22);
        MainPanelAddRoom.add(spinnerNmrCamas);
        
        JLabel lblNmeroDeCamas = new JLabel("Número de Camas");
        lblNmeroDeCamas.setForeground(new Color(17, 193, 120));
        lblNmeroDeCamas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNmeroDeCamas.setBounds(64, 323, 183, 16);
        MainPanelAddRoom.add(lblNmeroDeCamas);
    }

    public void setVisible(boolean b) {
        frameAddRoom.setVisible(b);
    }
    
 
        	public void addRoomDb() { // Get dos campos para criar lista e adiconar aos parametros da executeUpdate
        	    try {
        	        
        	        int nmrQuarto = Integer.parseInt(textFieldIdentificacaoQuarto.getText());
        	        int andar = Integer.parseInt(textFieldAndarQuarto.getText());
        	        double valorDiaria = (Double) spinnerValorQuarto.getValue();
        	        int maxHospedes = (Integer) spinnerMaximoHospede.getValue();
        	        int nmrCamas = (Integer) spinnerNmrCamas.getValue();
        	        boolean frigobar = CheckBoxFrigobar.isSelected();
        	        boolean tv = CheckBoxTv.isSelected();
        	        String descricao = textAreaDescricaoQuarto.getText();
        	        int nmrSuites = 1;  // não faz sentido esse campo mas tem que passar  
        	        
        	        
        	        List<Object> parameters = new ArrayList<>();
        	        parameters.add(nmrQuarto);
        	        parameters.add(andar);
        	        parameters.add(maxHospedes);
        	        parameters.add(nmrCamas);
        	        parameters.add(frigobar);
        	        parameters.add(tv);
        	        parameters.add(nmrSuites);
        	        parameters.add(valorDiaria);
        	        parameters.add(descricao);
        	        
        	       
        	        int result = CadastrarQuarto.ListAddRoom(parameters);
        	        
        	        if (result > 0) {
        	            JOptionPane.showMessageDialog(frameAddRoom, "Quarto cadastrado com sucesso!");
        	            frameAddRoom.dispose();
        	            
        	        } else {
        	            JOptionPane.showMessageDialog(frameAddRoom, "Falha ao cadastrar quarto.", "Erro", JOptionPane.ERROR_MESSAGE);
        	        }
        	        
        	    } catch (NumberFormatException e) {
        	        JOptionPane.showMessageDialog(frameAddRoom, "Por favor, insira valores numéricos válidos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        	    } catch (Exception e) {
        	        JOptionPane.showMessageDialog(frameAddRoom, "Erro ao cadastrar quarto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        	        e.printStackTrace();
        	    }
        	}
        	public void cleanFields() {
        		
        		textFieldIdentificacaoQuarto.setText(null);;
    	        textFieldAndarQuarto.setText(null);
    	        spinnerValorQuarto.setValue(0);
    	        spinnerMaximoHospede.setValue(1);
    	        spinnerNmrCamas.setValue(1);
    	        CheckBoxFrigobar.setSelected(false);;
    	        CheckBoxTv.setSelected(false);;
    	        textAreaDescricaoQuarto.setText(null);
    	         
        		
        		
        	
        }
}
