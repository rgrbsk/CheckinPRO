import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CheckoutFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCheckout;

	/**
	 * Create the panel.
	 */
	public CheckoutFrame() {
		setBackground(new Color(55, 55, 55));

		
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(6, 6, 1292, 28);
		add(panel);
		
		txtCheckout = new JTextField();
		txtCheckout.setText("Check-out");
		txtCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheckout.setForeground(new Color(17, 193, 123));
		txtCheckout.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		txtCheckout.setEditable(false);
		txtCheckout.setColumns(10);
		txtCheckout.setBorder(null);
		txtCheckout.setBackground(Color.DARK_GRAY);
		txtCheckout.setAutoscrolls(false);
		panel.add(txtCheckout);
		
		
		
		
		
		
	}
	
	

}
