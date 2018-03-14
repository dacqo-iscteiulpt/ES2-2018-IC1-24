package gui;

import javax.swing.*;

public class Gui {

	protected JPanel GuiPanel = new JPanel();
	
	public Gui() {
		
		JLabel todo = new JLabel("todo");
		GuiPanel.add(todo);
	}
}