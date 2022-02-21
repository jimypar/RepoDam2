package borderlayout;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Component;

public class BLayout extends JPanel {

	/**
	 * Create the panel.
	 */
	public BLayout() {
		setAlignmentY(Component.TOP_ALIGNMENT);
		setLayout(new BorderLayout(0, 0));
		
		JButton btnWest = new JButton("New button");
		add(btnWest, BorderLayout.WEST);

	}

}
