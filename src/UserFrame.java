import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.Client;
import objects.Reserve;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import clienteWindow.AddClientWindow;
import clienteWindow.ClientHistoricWindow;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JCheckBox;

public class UserFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtClientes;
	private JTextField textConteudoPesquisa;
	private JTable tabelaClientes;
	private JLabel lblTotalClientes; // ✅ Declaração correta

	private DefaultTableModel modelTable;
	private JTextField txtClientes_1;
	private JTextField textField;
	private JTable tabelaCliente; // Declare at class level
	
	/**
	 
	 */
	public UserFrame() {
		
		setBackground(new Color(55, 55, 55));
		// Initialize table model correctly
	    modelTable = new DefaultTableModel(
	        new Object[][] {}, 
	        new String[] {"ID", "Nome", "CPF", "Email", "Telefone"}
	        
	    );
	    
	    tabelaClientes = new JTable(); // Ensure JTable is initialized properly
	    
	 
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 1292, 28);
		panel.setBackground(Color.DARK_GRAY);
		add(panel);
		panel.setLayout(null);
		
		
		txtClientes_1 = new JTextField();
		txtClientes_1.setBounds(576, 5, 140, 24);
		txtClientes_1.setText("Clientes");
		txtClientes_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtClientes_1.setForeground(new Color(17, 193, 123));
		txtClientes_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		txtClientes_1.setEditable(false);
		txtClientes_1.setColumns(10);
		txtClientes_1.setBorder(null);
		txtClientes_1.setBackground(Color.DARK_GRAY);
		panel.add(txtClientes_1);
		
	
		
		txtClientes = new JTextField();
		txtClientes.setText("Clientes");
		txtClientes.setHorizontalAlignment(SwingConstants.CENTER);
		txtClientes.setForeground(new Color(17, 193, 123));
		txtClientes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		txtClientes.setEditable(false);
		txtClientes.setColumns(10);
		txtClientes.setBorder(null);
		txtClientes.setBackground(Color.DARK_GRAY);
		txtClientes.setAutoscrolls(false);
		
		JButton btnSearchResults = new JButton("");
		btnSearchResults.setBounds(848, 76, 64, 34);
		btnSearchResults.setIcon(new ImageIcon(UserFrame.class.getResource("/img/icons8-pesquisar-30.png")));
		btnSearchResults.setForeground(Color.WHITE);
		add(btnSearchResults);
		
		textConteudoPesquisa = new JTextField();
		textConteudoPesquisa.setBounds(926, 76, 253, 34);
		textConteudoPesquisa.setForeground(Color.WHITE);
		textConteudoPesquisa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textConteudoPesquisa.setColumns(10);
		add(textConteudoPesquisa);
		modelTable = new DefaultTableModel(
			    new Object[][] {}, 
			    new String[] {"ID", "Nome", "CPF", "Email", "Telefone"} // Update column names if needed
			);

	
		JScrollPane scrollPane = new JScrollPane(tabelaClientes);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(6, 121, 1280, 620);
		add(scrollPane);
		tabelaClientes.setShowGrid(true);
		

		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.setIcon(new ImageIcon(UserFrame.class.getResource("/img/Adicionar.png")));
		btnCadastrarCliente.setBounds(6, 76, 172, 34);
		add(btnCadastrarCliente);
		
		JPanel panelCustomFooter = new JPanel();
		panelCustomFooter.setLayout(null);
		panelCustomFooter.setBackground(Color.DARK_GRAY);
		panelCustomFooter.setBounds(348, 770, 592, 64);
		add(panelCustomFooter);
		
		JLabel lblNewLabel = new JLabel("Clientes Cadastrados:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(89, 23, 140, 14);
		panelCustomFooter.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Exibindo no Grid:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(359, 23, 108, 14);
		panelCustomFooter.add(lblNewLabel_1);
		
		lblTotalClientes = new JLabel();
		lblTotalClientes.setForeground(Color.GREEN);
		lblTotalClientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalClientes.setBounds(239, 23, 66, 14);
		panelCustomFooter.add(lblTotalClientes);
		atualizarTotalClientes(); // ✅ Agora pode ser chamado!

		
		JLabel lblTotalGrid = new JLabel("-");
		lblTotalGrid.setForeground(Color.GREEN);
		lblTotalGrid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalGrid.setBounds(477, 23, 66, 14);
		panelCustomFooter.add(lblTotalGrid);
		
		JComboBox comboLimitarReg = new JComboBox();
		comboLimitarReg.setEditable(true);
		comboLimitarReg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboLimitarReg.setToolTipText("");
		comboLimitarReg.setBounds(974, 800, 83, 34);
		add(comboLimitarReg);
		comboLimitarReg.addItem("1000");
		comboLimitarReg.addItem("20");
		comboLimitarReg.addItem("50");
		comboLimitarReg.addItem("100");
		comboLimitarReg.addItem("120");
		comboLimitarReg.setVisible(false);
		
		JComboBox comboFiltro = new JComboBox();
		comboFiltro.setForeground(Color.WHITE);
		comboFiltro.setBounds(1189, 76, 108, 34);
		add(comboFiltro);
		
		comboFiltro.addItem("Nome");
		comboFiltro.addItem("CPF");
		comboFiltro.addItem("E-mail");
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(1288, 121, 10, 621);
		add(scrollBar);
		
		JCheckBox checkPerformance = new JCheckBox("Qnt Clientes Grid");
		checkPerformance.setHorizontalAlignment(SwingConstants.CENTER);
		checkPerformance.setBounds(964, 770, 123, 19);
		add(checkPerformance);
		
		JButton btnEditarCadastro = new JButton("Visualizar / Editar Cadastro");
		btnEditarCadastro.setIcon(new ImageIcon(UserFrame.class.getResource("/img/EditarC.png")));
		btnEditarCadastro.setBounds(392, 76, 218, 34);
		add(btnEditarCadastro);
		JButton btnExcluirCadastro = new JButton("Excluir Cadastro");
		btnExcluirCadastro.setIcon(new ImageIcon(UserFrame.class.getResource("/img/DeletarC.png")));
		btnExcluirCadastro.setBounds(631, 76, 172, 34);
		add(btnExcluirCadastro);
		
		JLabel lblMaxRegistros = new JLabel("Máximo de registros a serem exibidos: 1000");
		lblMaxRegistros.setForeground(Color.GREEN);
		lblMaxRegistros.setVisible(false);
		lblMaxRegistros.setBounds(536, 745, 276, 14);
		add(lblMaxRegistros);
		
		JLabel label = new JLabel("New label");
		label.setBounds(667, 6, 52, 16);
		add(label);
		
		JButton btnVerHistorico = new JButton("Histórico");
		btnVerHistorico.setIcon(new ImageIcon(UserFrame.class.getResource("/img/Bookmark.png")));
		btnVerHistorico.setBounds(200, 76, 172, 34);
		add(btnVerHistorico);
		
		tabelaClientes.setModel(modelTable); 
		
		
		
		
		
		checkPerformance.addActionListener(e -> {
		    if (checkPerformance.isSelected()) {
		        System.out.println("Clicado");
		        comboLimitarReg.setVisible(true);
		    } else {
		        System.out.println("Desclicado");
		        comboLimitarReg.setVisible(false);
		    }
		});
		
		btnCadastrarCliente.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AddClientWindow.isEditMode = false;
		        AddClientWindow addClient = new AddClientWindow();
		        addClient.getFrame().setVisible(true);
		        addClient.lblModoEdicao.setVisible(false);

			}
		    
		});
		btnSearchResults.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		        String filtroSelecionado = comboFiltro.getSelectedItem().toString();
		        String conteudoPesquisa = textConteudoPesquisa.getText().trim();

		        Object valorSelecionado = comboLimitarReg.getSelectedItem();
		        String limitarRegString = (valorSelecionado != null) ? valorSelecionado.toString().trim() : "1000";
		        int limitarRegSql;
		        try {
		            limitarRegSql = Integer.parseInt(limitarRegString);
		        } catch (Exception ex) {
		            limitarRegSql = 1000;
		        }
		        

		        lblMaxRegistros.setVisible(limitarRegSql >= 1000);

		        Map<String, String> columnMap = new HashMap<>();
		        columnMap.put("Nome", "nome");
		        columnMap.put("CPF", "cpf");
		        columnMap.put("E-mail", "email");

		        String filtroReal = columnMap.getOrDefault(filtroSelecionado, "nome");

		        List<Client> clientesEncontrados = Client.buscarClientes(filtroReal, conteudoPesquisa, limitarRegString);

		        System.out.println(clientesEncontrados);
		        System.out.println("Filter used: " + filtroReal);
		        System.out.println("Search term: " + conteudoPesquisa);
		        System.out.println("Total clients found: " + clientesEncontrados.size());

		        lblTotalGrid.setText(String.valueOf(clientesEncontrados.size()));
		        modelTable.setRowCount(0); // Limpa resultados anteriores

		        for (Client cliente : clientesEncontrados) {
		            modelTable.addRow(new Object[]{
		                cliente.getId(),
		                cliente.getNome(),
		                cliente.getCpf(),
		                cliente.getEmail(),
		                cliente.getTelefone()
		            });
		        }

		        tabelaClientes.setModel(modelTable);
		        atualizarTotalClientes();
		        
		    }
		   
		});

		btnExcluirCadastro.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tabelaClientes.getSelectedRow();

		
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Selecione um cliente para excluir!", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return; // Stop execution if no row is selected
		        }

		        int clienteId = (int) modelTable.getValueAt(selectedRow, 0);

		   
		        String[] options = {"Sim", "Não"};
		        int confirm = JOptionPane.showOptionDialog(
		            null, 
		            "Deseja excluir o cliente de ID " + clienteId + "?", 
		            "Confirmação de Exclusão", 
		            JOptionPane.YES_NO_OPTION, 
		            JOptionPane.QUESTION_MESSAGE, 
		            null, // No cu
		            options, 
		            options[0]
		        );

		
		        if (confirm == 0) {
		            int deletedRows = Client.excluirCliente(clienteId);

		            if (deletedRows > 0) {
		                JOptionPane.showMessageDialog(null, "Exclusão efetuada com sucesso ✅.");
		            } else {
		                JOptionPane.showMessageDialog(null, "Erro ao excluir cliente.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Exclusão cancelada.");
		        }
		    }
		});
		
		btnVerHistorico.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	int selectedRow = tabelaClientes.getSelectedRow(); 

		        if (selectedRow == -1) { 
		            JOptionPane.showMessageDialog(null, "Nenhum cliente disponível!", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		    
		        int clienteId = Integer.parseInt(modelTable.getValueAt(selectedRow, 0).toString()); 
		        String nome = modelTable.getValueAt(selectedRow, 1).toString(); 
		        String cpf = modelTable.getValueAt(selectedRow, 2).toString();
		        String email = modelTable.getValueAt(selectedRow, 3).toString(); 
		        String telefone = modelTable.getValueAt(selectedRow, 4).toString();

		        Client cliente = new Client(clienteId, nome, "", cpf, email, telefone); 

		       
		        ClientHistoricWindow historic = new ClientHistoricWindow(cliente);
		        historic.getFrame().setVisible(true);
		    }
		});
		
		
		

		
		
		btnEditarCadastro.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	AddClientWindow.isEditMode = true;
		        int selectedRow = tabelaClientes.getSelectedRow();

		        if (selectedRow != -1) {
		            int clienteId = (int) modelTable.getValueAt(selectedRow, 0);

		           //r
		            System.out.println("Cliente selecionado: " + clienteId);

		            Client cliente = Client.buscarClientePorId(clienteId);

		            if (cliente != null) {
		                
		                AddClientWindow addClient = new AddClientWindow();
		                addClient.preencherCampos(cliente); 
		                addClient.getFrame().setVisible(true);
		                addClient.lblModoEdicao.setText("Edição de Cadastro");
		            } else {
		                JOptionPane.showMessageDialog(null, "Erro ao buscar cliente!");
		            }
		        } else {
		        	JOptionPane.showMessageDialog(null, "Selecione um cliente para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		btnSearchResults.doClick(); // roger: nao mexer
	}
		
	public void atualizarTotalClientes() {
	    if (lblTotalClientes == null) { 
	        System.err.println("Erro: lblTotalClientes não foi inicializado!");
	        return;
	    }

	    int totalClientes = Client.contarTotalClientes(); 
	    lblTotalClientes.setText(String.valueOf(totalClientes));
	}
	
	public void carregarClientesNoGrid() {
	    List<Client> clientes = Client.loadGridClientes(); 

	    modelTable.setRowCount(0); //

	    for (Client cliente : clientes) {
	        modelTable.addRow(new Object[]{
	            cliente.getId(),
	            cliente.getNome(),
	            cliente.getCpf(),
	            cliente.getEmail(),
	            cliente.getTelefone()
	        });
	    }
	}


		public static Integer tryParseInt(String valor) {
		    try {
		        return Integer.parseInt(valor);
		    } catch (NumberFormatException e) {
		        return null;
		    }
		}
		
		
}
