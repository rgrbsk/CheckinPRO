import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class Checkin extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Checkin() {
		setBackground(new Color(55, 55, 55));

		
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("tela1");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(124, 52, 46, 14);
		add(lblNewLabel);
		
	}
}
