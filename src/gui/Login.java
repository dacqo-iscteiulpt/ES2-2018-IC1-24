package gui;

import java.awt.*;
import javax.swing.*;

public class Login {

	protected String email = null;

	public String getEmail() {
		return email;
	}

	public Login(JFrame frame) {

		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("User", SwingConstants.RIGHT));
		label.add(new JLabel("Password", SwingConstants.RIGHT));
		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField username = new JTextField("");
		username.setPreferredSize(new Dimension(200,50));
		controls.add(username);
		JPasswordField password = new JPasswordField();
		controls.add(password);

		panel.add(controls, BorderLayout.CENTER);
		int n = JOptionPane.showOptionDialog(frame, panel, "Login", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);

		if (n == JOptionPane.YES_OPTION) {
			email = username.getText();
		} else if (n == JOptionPane.NO_OPTION) {
			System.exit(1);;
		} else if (n == JOptionPane.CLOSED_OPTION) {
			System.exit(1);
		}
	}
}
