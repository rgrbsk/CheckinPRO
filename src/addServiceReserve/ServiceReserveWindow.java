package addServiceReserve;

import objects.Client;
import objects.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ServiceReserveWindow extends JFrame {
    private Client cliente;
    public JFrame frameServiceReserve;
    public JButton btnConcluirCadastro;
    public static boolean isEditMode;
    public JScrollPane scrollPaneServicesReserve;
    private JTable tabelaServicos;
    private JTextField textQuantidadeServicos;
    private int reservaId;

 
    public ServiceReserveWindow(Client cliente) {
        this.cliente = cliente;
        initialize();
        carregarServicos(); 
    }
    
    public ServiceReserveWindow(int reservaId) {
        this.reservaId = reservaId;
        initialize();
        carregarServicos();
    }

    
    public ServiceReserveWindow() {
        initialize();
        carregarServicos();
    }

   
    private void initialize() {
        frameServiceReserve = new JFrame();
        frameServiceReserve.setTitle("CheckinPRO - 2025");
        frameServiceReserve.setBounds(100, 100, 617, 704);
        frameServiceReserve.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameServiceReserve.getContentPane().setLayout(null);
        
        

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(19, 19, 19));
        panel.setBounds(10, 11, 584, 661);
        frameServiceReserve.getContentPane().add(panel);

        JPanel customBar = new JPanel();
        customBar.setBackground(new Color(17, 193, 123));
        customBar.setBounds(0, 0, 598, 5);
        panel.add(customBar);

        JPanel panelCadastro = new JPanel();
        panelCadastro.setBackground(new Color(55, 55, 55));
        panelCadastro.setBounds(0, 0, 584, 661);
        panel.add(panelCadastro);
        panelCadastro.setLayout(null);

        JPanel panelCustomHeader = new JPanel();
        panelCustomHeader.setBounds(0, 0, 584, 32);
        panelCustomHeader.setLayout(null);
        panelCustomHeader.setBackground(Color.DARK_GRAY);
        panelCadastro.add(panelCustomHeader);

        JLabel lblCadastroDeClientes = new JLabel("Adicionar Serviços a Reserva");
        lblCadastroDeClientes.setForeground(new Color(17, 193, 120));
        lblCadastroDeClientes.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblCadastroDeClientes.setBounds(181, 0, 237, 35);
        panelCustomHeader.add(lblCadastroDeClientes);

        JPanel panelCustomFooter = new JPanel();
        panelCustomFooter.setBounds(0, 586, 584, 64);
        panelCustomFooter.setLayout(null);
        panelCustomFooter.setBackground(Color.DARK_GRAY);
        panelCadastro.add(panelCustomFooter);

        JButton btnVincularServico = new JButton("Vincular");
        btnVincularServico.setIcon(new ImageIcon(ServiceReserveWindow.class.getResource("/img/Ok.png")));
        btnVincularServico.setBounds(253, 11, 174, 42);
        panelCustomFooter.add(btnVincularServico);
        
        textQuantidadeServicos = new JTextField();
        textQuantidadeServicos.setHorizontalAlignment(SwingConstants.CENTER);
        textQuantidadeServicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textQuantidadeServicos.setBounds(146, 33, 86, 20);
        panelCustomFooter.add(textQuantidadeServicos);
        textQuantidadeServicos.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Quantidade");
        lblNewLabel.setForeground(Color.GREEN);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(152, 11, 80, 14);
        panelCustomFooter.add(lblNewLabel);

        // ScrollPane para os serviços
        scrollPaneServicesReserve = new JScrollPane();
        scrollPaneServicesReserve.setBounds(0, 35, 584, 556);
        panelCadastro.add(scrollPaneServicesReserve);

        // Tabela inicial vazia
        tabelaServicos = new JTable();
        scrollPaneServicesReserve.setViewportView(tabelaServicos);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        scrollPaneServicesReserve.setColumnHeaderView(lblNewLabel_1);
        
        btnVincularServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabelaServicos.getSelectedRow();

                if (selectedRow != -1) {
                    int idServico = (int) tabelaServicos.getValueAt(selectedRow, 0);
                    int quantidadeServicos = textQuantidadeServicos.getText().isEmpty()
                        ? 0
                        : Integer.parseInt(textQuantidadeServicos.getText());
                    if (textQuantidadeServicos.getText().isEmpty()) {
                    
						JOptionPane.showMessageDialog(frameServiceReserve,
								"Por favor, insira a quantidade de serviços!", "Erro", JOptionPane.WARNING_MESSAGE);
						return;
					}

                    try {
                        Service service = new Service();
                        service.AdicionarServicosReserva(idServico, quantidadeServicos, reservaId);
                        JOptionPane.showMessageDialog(frameServiceReserve, "Serviço vinculado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frameServiceReserve, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // ✅ Display error message
                    }
                } else {
                    JOptionPane.showMessageDialog(frameServiceReserve, "Selecione um serviço antes de vincular!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

       
	    	

 
    public void carregarServicos() {
        List<Service> servicos = Service.buscarTodosServicos();

        String[] colunas = {"ID Serviço", "Descrição", "Valor"};
        DefaultTableModel modelTable = new DefaultTableModel(null, colunas);

        servicos.forEach(service -> modelTable.addRow(new Object[]{
                service.getId(), service.getDescricao(), service.getValor()
        }));

        tabelaServicos.setModel(modelTable); 
        tabelaServicos.setShowGrid(true);
        tabelaServicos.setFillsViewportHeight(true);
    }
    

    public JFrame getFrameHistoricoCliente() {
        return frameServiceReserve;
    }

    public void setFrameHistoricoCliente(JFrame frameHistoricoCliente) {
        this.frameServiceReserve = frameHistoricoCliente;
    }

    public JFrame getFrame() {
        return frameServiceReserve;
    }

    // Método main para teste
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ServiceReserveWindow window = new ServiceReserveWindow();
                window.frameServiceReserve.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
       
    }
  
}
