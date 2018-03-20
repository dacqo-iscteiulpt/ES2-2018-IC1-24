package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import jcombocheckbox.JComboCheckBox;
import xml.XmlReader;

public class Gui {

	public JPanel GuiPanel = new JPanel();

	protected JPanel topPanel = new JPanel();

	protected JPanel middlePanel = new JPanel();
	protected JPanel middleLeftPanel = new JPanel();
	protected JPanel comboBoxPanel = new JPanel();

	protected JPanel middleRightPanel = new JPanel();

	protected JTable table = new JTable();
	protected DefaultTableModel dtm = new DefaultTableModel();
	protected Object[][] tabledata = new Object[30][4];

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

		JLabel problemDescLabel = new JLabel("Small Problem Description: ");
		topFields.add(problemDescLabel);
		JTextArea problemDescArea = new JTextArea();
		problemDescArea.setLineWrap(true);
		problemDescArea.setWrapStyleWord(true);
		problemDescArea.setPreferredSize(new Dimension(200, 50));
		topFields.add(problemDescArea);

		JLabel emailLabel = new JLabel("Email: ");
		JTextField emailField = new JTextField(main.Main.getEmail() + "@gmail.com");
		emailField.setPreferredSize(new Dimension(200, 30));
		topFields.add(emailLabel);
		topFields.add(emailField);

		JLabel nameLabel = new JLabel("Name: ");
		JTextField nameField = new JTextField(main.Main.getEmail());
		nameField.setPreferredSize(new Dimension(200, 30));
		nameField.setEnabled(false);
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
		middleRightPanel.setPreferredSize(new Dimension(300,100));
		String header[] = new String[] { "Name", "Type", "BottomRange", "TopRange" };
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);

		for (int count = 1; count <= 30; count++) {
			tabledata[count-1][0] = "Regra"+count;
			tabledata[count-1][1] = "Tipo";
			tabledata[count-1][2] = "low";
			tabledata[count-1][3] = "high";
			dtm.addRow(tabledata[count-1]);
		}

		middleRightPanel.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

		//Left side
		middleLeftPanel.setLayout(new GridLayout(2, 2));

		JLabel maxWaitTimeLabel = new JLabel("Maximum wait time");
		JTextField maxWaitTimeField = new JTextField();
		maxWaitTimeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Max wait time: " + maxWaitTimeField.getText());
			}
		});

		JLabel numberOfVariablesLabel = new JLabel("Number of variables");
		JTextField numberOfVariablesField = new JTextField();
		numberOfVariablesField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("Number of variables: " + numberOfVariablesField.getText());
				resizeTable(Integer.parseInt(numberOfVariablesField.getText()));
			}
		});

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

		Vector<JCheckBox> v = new Vector<JCheckBox>();
		v.add(new JCheckBox("NSGA-II", true));
		v.add(new JCheckBox("ssNSGA-II", false));
		v.add(new JCheckBox("NSGAIIr", false));
		v.add(new JCheckBox("NSGAIIa", false));
		v.add(new JCheckBox("pNSGA-II", false));
		v.add(new JCheckBox("AbYSS", false));
		v.add(new JCheckBox("CellDE", false));
		v.add(new JCheckBox("dMPOSO", false));
		v.add(new JCheckBox("GDE3", false));
		v.add(new JCheckBox("FastPGA", false));
		v.add(new JCheckBox("IBEA", false));
		v.add(new JCheckBox("MOCHC", false));
		v.add(new JCheckBox("MOCell", false));
		v.add(new JCheckBox("MOEA/D-DE", false));
		v.add(new JCheckBox("pMOEA/D-DE", false));
		v.add(new JCheckBox("MOEA/D-DRA", false));
		v.add(new JCheckBox("OMOPSO", false));
		v.add(new JCheckBox("PAES", false));
		v.add(new JCheckBox("SMPSO", false));
		v.add(new JCheckBox("pSMPSO", false));
		v.add(new JCheckBox("SMPSOhv", false));
		v.add(new JCheckBox("SPEA2", false));

		bottomPanel.add(new JComboCheckBox(v));

		GuiPanel.add(bottomPanel);
	}


	protected void SaveToXML() {

	}
	protected void LoadFromXML() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"XML Files", "xml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +
					chooser.getSelectedFile().getName());
			System.out.println(chooser.getSelectedFile().getPath());
			XmlReader xml = new XmlReader(chooser.getSelectedFile().getPath());
		}
	}

	protected void resizeTable(int rows) {

		for (int i = dtm.getRowCount() - 1; i > -1; i--) {
			dtm.removeRow(i);
		}
		
		Object[][] tableDataTemp = new Object[rows][6];
		for (int count = 1; count <= rows; count++) {
			tableDataTemp[count-1][0] = "Regra"+count;
			tableDataTemp[count-1][1] = "Tipo";
			tableDataTemp[count-1][2] = "low";
			tableDataTemp[count-1][3] = "high";
		}
		for(int count = 0; count < tableDataTemp.length; count++) {
			dtm.addRow(tableDataTemp[count]);
		}
		
//		for(int count = 0; count < tabledata.length; count++) {
//			tableDataTemp[count][0] = tabledata[count][0];
//			tableDataTemp[count][1] = tabledata[count][1];
//			tableDataTemp[count][2] = tabledata[count][2];
//			tableDataTemp[count][3] = tabledata[count][3];
//		}

//		for (int count = 1; count <= rows; count++) {
//			dtm.addRow(tableDataTemp[count]);
//		}
	
		dtm.fireTableDataChanged();
	}
}