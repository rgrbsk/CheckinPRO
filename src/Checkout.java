import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class Checkout extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Checkout() {
		setBackground(new Color(55, 55, 55));

		
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("tela2");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(58, 126, 46, 14);
		add(lblNewLabel);
		
	}

}
