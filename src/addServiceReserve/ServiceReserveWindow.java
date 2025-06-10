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
    private JTable tabelaServicos; // ✅ Atributo da tabela corretamente usado
    private JTextField textField;

    // Construtor com cliente
    public ServiceReserveWindow(Client cliente) {
        this.cliente = cliente;
        initialize();
        carregarServicos(); // ✅ Carrega os serviços disponíveis na tabela
    }

    // Construtor padrão
    public ServiceReserveWindow() {
        initialize();
        carregarServicos(); // Também carrega para uso isolado
    }

    // Inicialização da interface
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

        JButton btnCancelarCadastro = new JButton("Vincular");
        btnCancelarCadastro.setIcon(new ImageIcon(ServiceReserveWindow.class.getResource("/img/Ok.png")));
        btnCancelarCadastro.setBounds(253, 11, 174, 42);
        panelCustomFooter.add(btnCancelarCadastro);
        
        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField.setBounds(146, 33, 86, 20);
        panelCustomFooter.add(textField);
        textField.setColumns(10);
        
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

        // Ação do botão "Vincular"
        btnCancelarCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                frameServiceReserve.dispose();
            }
        });
    }

    // Carrega os serviços do banco de dados na tabela
    public void carregarServicos() {
        List<Service> servicos = Service.buscarTodosServicos();

        String[] colunas = {"ID Serviço", "Descrição", "Valor"};
        DefaultTableModel modelTable = new DefaultTableModel(null, colunas);

        servicos.forEach(service -> modelTable.addRow(new Object[]{
                service.getId(), service.getDescricao(), service.getValor()
        }));

        tabelaServicos.setModel(modelTable); // ✅ Atualiza o modelo da tabela
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

    // Método main para teste independente
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
