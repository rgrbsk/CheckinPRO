import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class User extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public User() {
		setBackground(new Color(55, 55, 55));

		
		
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		JLabel lblTela = new JLabel("tela4");
		lblTela.setForeground(Color.WHITE);
		lblTela.setBounds(254, 204, 46, 14);
		add(lblTela);
	}

}
