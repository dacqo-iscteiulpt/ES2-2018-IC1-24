package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import email.Mail;
import main.Main;

public class Help {

	public JPanel helpPanel = new JPanel();

	protected JTextField emailField = new JTextField();

	protected JTextArea description = new JTextArea();

	public Help() {
		helpPanel.setLayout(new BorderLayout());

		JPanel email_panel = new JPanel();
		helpPanel.add(email_panel, BorderLayout.NORTH);
		email_panel.setLayout(new FlowLayout());

		JLabel email_Label = new JLabel("Email:");
		email_panel.add(email_Label);


		emailField.setText(Main.getEmail() + "@gmail.com");
		emailField.setPreferredSize(new Dimension(200,25));
		email_panel.add(emailField);

		JButton send_button = new JButton("Send");
		send_button.setPreferredSize(new Dimension(100,50));
		send_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() { 
						Mail email = new Mail();
						email.sendEmail(emailField.getText(), description.getText());
						JOptionPane.showMessageDialog(helpPanel, "Email sent");
					}
				});
			}
		});


		helpPanel.add(send_button, BorderLayout.SOUTH);

		JPanel description_panel = new JPanel();
		helpPanel.add(description_panel, BorderLayout.CENTER);

		JLabel description_Label = new JLabel("Description");
		description_panel.add(description_Label);

		description.setPreferredSize(new Dimension(500,300));
		description.setLineWrap(true);
		description_panel.add(description);
	}
}
