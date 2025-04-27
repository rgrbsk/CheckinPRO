import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class UserFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtClientes;

	/**
	 * Create the panel.
	 */
	public UserFrame() {
		setBackground(new Color(55, 55, 55));

		
		
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(6, 6, 1292, 28);
		add(panel);
		
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
		panel.add(txtClientes);
	}

}
