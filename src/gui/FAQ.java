package gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class FAQ {
	
	public JPanel faqPanel = new JPanel();

	public FAQ() {
		
		faqPanel.setLayout(new BorderLayout(0, 0));
		JTextArea textArea = new JTextArea
				( "  Question:\t What is my email going to be used for? \n  Answer:\t Your email....\n\n"
						+ "  Question:\t \n  Answer:\t \n\n"
						+ "  Question:\t \n  Answer:\t \n\n"
						+ "  Question:\t \n  Answer:\t \n\n"
						+ "  Question:\t \n  Answer:\t \n\n"
						+ "  Question:\t \n  Answer:\t \n\n"
						+ "  Question:\t \n  Answer:\t \n\n"
						+ "  Question:\t \n  Answer:\t \n\n"
						+ "  Question:\t \n  Answer:\t \n\n"
						+ "  Question:\t \n  Answer:\t \n\n"
						+ "  Question:\t \n  Answer:\t \n\n");
		textArea.setEditable(false);
		JScrollPane scrollbar = new JScrollPane(textArea);
		faqPanel.add(scrollbar);
		
	}
}
