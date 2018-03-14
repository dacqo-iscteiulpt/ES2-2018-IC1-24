package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Help {

	protected JPanel helpPanel = new JPanel();

	public Help() {
		helpPanel.setLayout(new BorderLayout());

		JPanel email_panel = new JPanel();
		helpPanel.add(email_panel, BorderLayout.NORTH);
		email_panel.setLayout(new FlowLayout());

		JLabel email_Label = new JLabel("Email:");
		email_panel.add(email_Label);

		JTextField email = new JTextField();
		email.setPreferredSize(new Dimension(200,25));
		email_panel.add(email);

		JButton send_button = new JButton("Send");
		send_button.setPreferredSize(new Dimension(100,50));
		send_button.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
			       //
				   JOptionPane.showMessageDialog(helpPanel, "Email sent");
			   }
			});
		
		
		helpPanel.add(send_button, BorderLayout.SOUTH);

		JPanel description_panel = new JPanel();
		helpPanel.add(description_panel, BorderLayout.CENTER);

		JLabel description_Label = new JLabel("Description");
		description_panel.add(description_Label);

		JTextArea description = new JTextArea();
		description.setPreferredSize(new Dimension(500,300));
		description.setLineWrap(true);
		description_panel.add(description);
		

	}
	
}
