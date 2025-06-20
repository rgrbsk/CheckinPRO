package addServiceReserve;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Service;
import reserveService.ReserveService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class EditServicesReserve extends JFrame {

	private static final long serialVersionUID = 1L;
	public JFrame frameEditServicesReserve;
	private JPanel contentPane;
	private JTextField textQntAtual;
	private JTextField textQntNova;
	private JTextField textIdServico;
	private JTextField textIdReserva;

	
	public EditServicesReserve(int reservaId, int idServico) {
	    this(); 
	    textIdReserva.setText(String.valueOf(reservaId));
	    textIdServico.setText(String.valueOf(idServico));
	    
	    
	    int quantidadeAtual = ReserveService.buscarQuantidadeServicoReserva(reservaId, idServico); // método fictício
	    
	    textQntAtual.setText(String.valueOf(quantidadeAtual));
	    textQntNova.setText(String.valueOf("")); 
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditServicesReserve frame = new EditServicesReserve();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditServicesReserve() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 446, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Serviço");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(113, 11, 60, 14);
		contentPane.add(lblNewLabel);
		
		textQntAtual = new JTextField();
		textQntAtual.setEditable(false);
		textQntAtual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textQntAtual.setBounds(56, 93, 116, 29);
		contentPane.add(textQntAtual);
		textQntAtual.setColumns(10);
		
		textQntNova = new JTextField();
		textQntNova.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textQntNova.setColumns(10);
		textQntNova.setBounds(246, 93, 116, 29);
		contentPane.add(textQntNova);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade Atual");
		lblNewLabel_1.setBounds(71, 78, 116, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quantidade Nova");
		lblNewLabel_1_1.setBounds(261, 78, 116, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(EditServicesReserve.class.getResource("/img/Ok.png")));
		btnSalvar.setBounds(153, 147, 115, 40);
		contentPane.add(btnSalvar);
		
		textIdServico = new JTextField();
		textIdServico.setBounds(101, 36, 76, 20);
		contentPane.add(textIdServico);
		textIdServico.setColumns(10);
		
		JLabel lblReserva = new JLabel("Reserva");
		lblReserva.setForeground(Color.GREEN);
		lblReserva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReserva.setBounds(261, 11, 60, 14);
		contentPane.add(lblReserva);
		
		textIdReserva = new JTextField();
		textIdReserva.setColumns(10);
		textIdReserva.setBounds(246, 36, 76, 20);
		contentPane.add(textIdReserva);
		
		JLabel labelImagem = new JLabel("");
		labelImagem.setIcon(new ImageIcon(EditServicesReserve.class.getResource("/img/Arrow.png")));
		labelImagem.setBounds(170, 93, 76, 29);
		contentPane.add(labelImagem);
		labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnSalvar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	int reservaId = Integer.parseInt(textIdReserva.getText());
				int novaQuantidade = Integer.parseInt(textQntNova.getText());
				if (novaQuantidade == 0 || novaQuantidade <= 0) {
				    JOptionPane.showMessageDialog(null, "A quantidade deve ser maior que zero.", "Aviso", JOptionPane.WARNING_MESSAGE);				}
				int idServico = Integer.parseInt(textIdServico.getText());
				
					
				try {
					if (novaQuantidade == 0 || novaQuantidade <= 0) {
					    JOptionPane.showMessageDialog(null, "A quantidade deve ser maior que zero.", "Aviso", JOptionPane.WARNING_MESSAGE);				}
					
					ReserveService.editarQuantidade(novaQuantidade, reservaId, idServico );
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao editar quantidade do serviço: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} finally {
					JOptionPane.showMessageDialog(null, "Quantidade do serviço editada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					
					dispose();
				}
				

				
		    	
		    }
			
		});
		
	

}}
