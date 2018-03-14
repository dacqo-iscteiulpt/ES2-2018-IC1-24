package gui;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;

public class Main {

	private JFrame frame;
	private FAQ faq = new FAQ();
	private Help help = new Help();
	private Output output = new Output();
	private Gui gui = new Gui();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("GUI",gui.GuiPanel);
		
		tabbedPane.addTab("FAQ",faq.faqPanel);

		tabbedPane.addTab("HELP", help.helpPanel);
		
		tabbedPane.addTab("OUTPUT", output.outputPanel);
	}

}
