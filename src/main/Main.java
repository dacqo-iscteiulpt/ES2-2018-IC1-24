package main;

import javax.swing.*;
import java.awt.*;

import gui.*;


public class Main {

	private static JFrame frame = new JFrame();
	protected static String email = null;

	protected FAQ faq = new FAQ();
	protected Help help = new Help();
	protected Output output = new Output();
	protected Gui gui = new Gui();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login(frame);
					email = login.getEmail();
					Main window = new Main();
					Main.frame.setVisible(true);
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
	
	public static String getEmail() {
		return email;
	}


}
