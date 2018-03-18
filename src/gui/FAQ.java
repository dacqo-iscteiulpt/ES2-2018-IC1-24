package gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class FAQ {
	
	public JPanel faqPanel = new JPanel();

	public FAQ() {
		
		faqPanel.setLayout(new BorderLayout(0, 0));
		JTextArea textArea = new JTextArea
				( "Question:\t blabla\nAnswer:\t blabla\n\n"
						+ "Question:\t blabla\nAnswer:\t blabla\n\n"
						+ "Question:\t blabla\nAnswer:\t blabla\n\n"
						+ "Question:\t blabla\nAnswer:\t blabla\n\n"
						+ "Question:\t blabla\nAnswer:\t blabla\n\n"
						+ "Question:\t blabla\nAnswer:\t blabla\n\n"
						+ "Question:\t blabla\nAnswer:\t blabla\n\n"
						+ "Question:\t blabla\nAnswer:\t blabla\n\n"
						+ "Question:\t blabla\nAnswer:\t blabla\n\n"
						+ "Question:\t blabla\nAnswer:\t blabla\n\n"
						+ "Question:\t blabla\nAnswer:\t blabla\n\n");
		textArea.setEditable(false);
		JScrollPane scrollbar = new JScrollPane(textArea);
		faqPanel.add(scrollbar);
		
	}
}
