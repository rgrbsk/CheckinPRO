package serviceWindow;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import objects.Service;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class ServicesWindow extends JPanel {

    public JFrame frameAddReserve;
    private JFrame frame;
    public JTextField textField;
    public JTextArea areatexto;
    public JTable table;
    private DefaultTableModel model;

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServicesWindow window = new ServicesWindow();
                    window.frameAddReserve.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ServicesWindow() {
        initialize();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void initialize() {
        frameAddReserve = new JFrame();
        frameAddReserve.setBackground(new Color(0, 0, 0));
        frameAddReserve.setResizable(false);
        frameAddReserve.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAddReserve.setBounds(100, 100, 750, 700);
        frameAddReserve.setLocationRelativeTo(null);
        frameAddReserve.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setBackground(Color.DARK_GRAY);
        tabbedPane.setBounds(10, 11, 714, 639);
        frameAddReserve.getContentPane().add(tabbedPane);

        // Aba Serviço
        JPanel servicoPanel = new JPanel();
        servicoPanel.setLayout(null);
        tabbedPane.addTab("Serviço", servicoPanel);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setHorizontalAlignment(SwingConstants.LEFT);
        lblDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDescricao.setBounds(29, 36, 114, 30);
        servicoPanel.add(lblDescricao);

        areatexto = new JTextArea();
        areatexto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        areatexto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areatexto.setRows(6);
        areatexto.setLineWrap(true);
        areatexto.setBounds(29, 77, 256, 89);
        servicoPanel.add(areatexto);

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setHorizontalAlignment(SwingConstants.LEFT);
        lblValor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblValor.setBounds(312, 36, 96, 30);
        servicoPanel.add(lblValor);

        textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.setBounds(312, 75, 193, 27);
        servicoPanel.add(textField);
        textField.setColumns(10);

        JButton btnExcluir = new JButton("Limpar");
        btnExcluir.setIcon(new ImageIcon(ServicesWindow.class.getResource("/img/icons8-pencil-eraser-30.png")));
        btnExcluir.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnExcluir.setBounds(532, 141, 148, 39);
        servicoPanel.add(btnExcluir);

        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                areatexto.setText("");
                textField.setText("");
            }
        });

        JButton btnConfirmar = new JButton("Adicionar");
        btnConfirmar.setIcon(new ImageIcon(ServicesWindow.class.getResource("/img/Adicionar.png")));
        btnConfirmar.setSelectedIcon(new ImageIcon(ServicesWindow.class.getResource("/img/Adicionar.png")));
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Add();
                loadData(); // Atualiza a tabela após adicionar
            }
        });
        btnConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnConfirmar.setBounds(532, 77, 148, 39);
        servicoPanel.add(btnConfirmar);
        
        JButton Adicionar = new JButton("Atualizar");
        Adicionar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        Adicionar.setIcon(new ImageIcon(ServicesWindow.class.getResource("/img/Refresh.png")));
        Adicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData(); // Atualiza os dados da tabela
            }
        });
        Adicionar.setBounds(510, 526, 170, 39);
        servicoPanel.add(Adicionar);
        
        JButton btnNewButton_1 = new JButton("Excluir");
        btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnNewButton_1.setIcon(new ImageIcon(ServicesWindow.class.getResource("/img/Cancelar.png")));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelectedService();
                loadData();
            }
        });
        btnNewButton_1.setBounds(29, 526, 170, 39);
        servicoPanel.add(btnNewButton_1);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(29, 238, 650, 268);
        servicoPanel.add(scrollPane);
        
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Descrição", "Valor"}
        );
        
        table = new JTable(model);
        scrollPane.setViewportView(table);
        
        // Carrega os dados iniciais
        loadData();
    }

    public void Add() {
        try {
            String descricao = areatexto.getText().trim();
            String valorTexto = textField.getText().trim();

            if (descricao.isEmpty() || valorTexto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }

            float valor = Float.parseFloat(valorTexto);

            int resultado = Service.addService(valor, descricao);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Serviço adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                areatexto.setText("");
                textField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar serviço.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O campo 'Valor' deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void loadData() {
        model.setRowCount(0); 
        
        List<Service> services = Service.filter(); 
      
        for (Service service : services) {
            model.addRow(new Object[]{
            	service.getId(),
                service.getDescricao(),
                service.getValor()
            });
        }
    }
    
    private void deleteSelectedService() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um serviço para excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        int id_servico = (int) model.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir este serviço?", "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            int resultado = Service.deleteData(id_servico);
        }
        
        
    }
}