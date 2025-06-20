package addServiceReserve;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import reserveService.ReserveService;
import servicoConsumo.ServicoConsumo;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewServicesWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelReservaId;
	private JTable table;
	private static JTextField textQuantidade;
	private static JTextField textValor;



	/**
	 * Create the frame.
	 * @param listaServicos 
	 */
	public ViewServicesWindow(List<ServicoConsumo> listaServicos, int reservaId) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 571, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelCustomHeader = new JPanel();
		panelCustomHeader.setBounds(0, 0, 557, 32);
		panelCustomHeader.setLayout(null);
		panelCustomHeader.setBackground(Color.DARK_GRAY);
		contentPane.add(panelCustomHeader);
		
		JLabel lblCadastroDeClientes = new JLabel("Serviços vinculados a Reserva: ");
		lblCadastroDeClientes.setForeground(new Color(17, 193, 120));
		lblCadastroDeClientes.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblCadastroDeClientes.setBounds(148, 0, 237, 35);
		panelCustomHeader.add(lblCadastroDeClientes);
		
		JLabel labelReservaId = new JLabel("-");
		labelReservaId.setForeground(new Color(17, 193, 120));
		labelReservaId.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		labelReservaId.setBounds(395, 0, 36, 35);
		panelCustomHeader.add(labelReservaId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 537, 497);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		textQuantidade = new JTextField();
		textQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		textQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textQuantidade.setBounds(60, 547, 134, 20);
		contentPane.add(textQuantidade);
		textQuantidade.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Qnt");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(31, 550, 30, 14);
		contentPane.add(lblNewLabel);
		
		textValor = new JTextField();
		textValor.setHorizontalAlignment(SwingConstants.CENTER);
		textValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textValor.setColumns(10);
		textValor.setBounds(331, 547, 134, 20);
		contentPane.add(textValor);
		labelReservaId.setText(String.valueOf(reservaId));
		
		JLabel lblValorTotal = new JLabel("Valor Total (R$)");
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValorTotal.setBounds(228, 550, 93, 14);
		contentPane.add(lblValorTotal);
		// chamar metods
		valorTotal(listaServicos);
		quantidadeTotal(listaServicos);
		JButton btnDeletarServicoReserva = new JButton("");
		btnDeletarServicoReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();	
				ReserveService reserveService = new ReserveService();
				String descricaoServico = (String) table.getValueAt(selectedRow, 0);
				int idReserva = reservaId; 
				System.out.println("Serviço selecionado para exclusão: " + descricaoServico);
				System.out.println("ID da reserva: " + idReserva);
				String resultado = ReserveService.deletarServicoReserva(descricaoServico, idReserva);
				if (resultado != null) {
					
					List<ServicoConsumo> listaAtualizada = ReserveService.buscarServicosVinculados(idReserva);
					atualizarTabela(listaAtualizada);
					valorTotal(listaAtualizada);
					quantidadeTotal(listaAtualizada);
					valorTotal(listaServicos);
					quantidadeTotal(listaServicos);
				} else {
					System.out.println("Erro ao deletar o serviço.");
				}
				
			}
		});
		btnDeletarServicoReserva.setIcon(new ImageIcon(ViewServicesWindow.class.getResource("/img/DeletarC.png")));
		btnDeletarServicoReserva.setBounds(475, 538, 72, 37);
		contentPane.add(btnDeletarServicoReserva);


		String[] colunas = {"Serviço", "Quantidade", "Valor Unitário", "Total"};
		DefaultTableModel model = new DefaultTableModel(colunas, 0);

		for (ServicoConsumo servico : listaServicos) {
		    model.addRow(new Object[]{
		       
		    	servico.getNome(),
		        servico.getQuantidade(),
		        String.format("R$ %.2f", servico.getValorUnitario()),
		        String.format("R$ %.2f", servico.getTotal())
		    });
		}

		table.setModel(model);
		scrollPane.setViewportView(table);
		
		
	}

	public static double valorTotal(List<ServicoConsumo> listaServicos) {
		double valorTotal = 0.0;
		for (ServicoConsumo servico : listaServicos) {
			valorTotal += servico.getTotal();
			textValor.setText(String.format("%.2f", valorTotal));
			
		}
		return valorTotal;
		
	}

	public static int quantidadeTotal(List<ServicoConsumo> listaServicos) {
        int quantidadeTotal = 0;
        for (ServicoConsumo servico : listaServicos) {
            quantidadeTotal += servico.getQuantidade();
            textQuantidade.setText(String.valueOf(quantidadeTotal));
            
        }
        return quantidadeTotal;
        
	}
	
	public void atualizarTabela(List<ServicoConsumo> listaServicos) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados

		for (ServicoConsumo servico : listaServicos) {
			model.addRow(new Object[] { servico.getNome(), servico.getQuantidade(),
					String.format("R$ %.2f", servico.getValorUnitario()),
					String.format("R$ %.2f", servico.getTotal()) });
		}

		valorTotal(listaServicos);
		quantidadeTotal(listaServicos);
	}
	
	
}
