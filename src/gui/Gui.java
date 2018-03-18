package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Gui {

	public JPanel GuiPanel = new JPanel();

	protected JPanel topPanel = new JPanel();

	protected JPanel middlePanel = new JPanel();
	protected JPanel middleLeftPanel = new JPanel();
	protected JPanel middleRightPanel = new JPanel();

	protected JTable table = new JTable();
	protected DefaultTableModel dtm = new DefaultTableModel(0, 0);


	protected JPanel bottomPanel = new JPanel();
	protected JButton saveToXML = new JButton("Save to XML");
	protected JButton loadFromXML = new JButton("Load from XML");
	protected JButton run = new JButton("RUN");	

	public Gui() {
		GuiPanel.setLayout(new GridLayout(3, 1));

		buildTopPanel();
		buildMiddlePanel();
		buildBottomPanel();

	}
	private void buildTopPanel() {
		topPanel.setLayout(new BorderLayout());

		JLabel topName = new JLabel("Problem specification");
		topName.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(topName, BorderLayout.NORTH);

		JPanel topFields = new JPanel();
		topFields.setLayout(new GridLayout(2, 2));

		JLabel problemNameLabel = new JLabel("Problem name: ");
		JTextField problemNameField = new JTextField();
		problemNameField.setPreferredSize(new Dimension(200, 30));
		topFields.add(problemNameLabel);
		topFields.add(problemNameField);

		JLabel problemDescLabel = new JLabel("Problem Description: ");
		JTextArea problemDescArea = new JTextArea();
		problemDescArea.setLineWrap(true);
		problemDescArea.setPreferredSize(new Dimension(200, 50));
		topFields.add(problemDescLabel);
		topFields.add(problemDescArea);

		JLabel emailLabel = new JLabel("Email: ");
		JTextField emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(200, 30));
		topFields.add(emailLabel);
		topFields.add(emailField);

		JLabel nameLabel = new JLabel("Name: ");
		JTextField nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(200, 30));
		topFields.add(nameLabel);
		topFields.add(nameField);

		topPanel.add(topFields);

		GuiPanel.add(topPanel);
	}

	private void buildMiddlePanel() {

		middlePanel.setLayout(new BorderLayout(1,1));

		JLabel configLabel = new JLabel("Configuration");
		configLabel.setHorizontalAlignment(JLabel.CENTER);
		middlePanel.add(configLabel, BorderLayout.NORTH);

		//Right side
		
		middleRightPanel.setLayout(new BorderLayout());
		String header[] = new String[] { "Name", "Type", "BottomRange", "TopRange" };
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		
		for (int count = 1; count <= 30; count++) {
			dtm.addRow(new Object[] { "" + count, "type ", "low", "high" });
		}
		
		middleRightPanel.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

		//Left side
		middleLeftPanel.setLayout(new GridLayout(2, 2));
		
		JLabel maxWaitTimeLabel = new JLabel("Maximum wait time in minutes");
		JTextField maxWaitTimeField = new JTextField();
		JLabel numberOfVariablesLabel = new JLabel("Number of variables");
		JTextField numberOfVariablesField = new JTextField();
		
		middleLeftPanel.add(maxWaitTimeLabel);		
		middleLeftPanel.add(maxWaitTimeField);
		
		middleLeftPanel.add(numberOfVariablesLabel);		
		middleLeftPanel.add(numberOfVariablesField);
		
		
		//SplitPane
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				middleLeftPanel, middleRightPanel);
		splitPane.setDividerLocation(400);
		splitPane.setResizeWeight(0.5);
		
		middlePanel.add(splitPane, BorderLayout.CENTER);
		GuiPanel.add(middlePanel);
	}

	private void buildBottomPanel() {
		bottomPanel.setLayout(new FlowLayout());

		bottomPanel.add(saveToXML);
		saveToXML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveToXML();
			}
		});


		bottomPanel.add(loadFromXML);
		loadFromXML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoadFromXML();
			}
		});


		bottomPanel.add(run);
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(GuiPanel, "Do you agree that your email can be used\nand stored for"
						+ " status updates and warnings\nabout the execution? ", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				    // yes option
					System.out.println("Start to run the optimization...");
					/* Todo add unimplemented mehtods */
				} else {
				    // do nothing
					JOptionPane.showMessageDialog(GuiPanel, "Please agree to the terms if you want\n"
														  + "to run the optimization.");
				}
			}
		});

		GuiPanel.add(bottomPanel);
	}


	protected void SaveToXML() {
		// TODO Auto-generated method stub

	}
	protected void LoadFromXML() {
		// TODO Auto-generated method stub

	}



}