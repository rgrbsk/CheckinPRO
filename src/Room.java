import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class Room extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Room() {
		
		setBackground(new Color(55, 55, 55));
		
		setBounds(0, 0, 1304, 845);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("tela3");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(146, 133, 46, 14);
		add(lblNewLabel);

	}

}
