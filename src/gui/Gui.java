package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import jcombocheckbox.JComboCheckBox;
import jmetal.ExperimentsIntegerExternalViaJAR;
import xml.XmlReader;
import xml.XmlWriter;

public class Gui {

	public JPanel GuiPanel = new JPanel();

	protected JPanel topPanel = new JPanel();
	protected JLabel problemNameLabel = new JLabel("Problem name: ");
	protected JTextField problemNameField = new JTextField("Problem1");
	protected JLabel emailLabel = new JLabel("Email: ");
	protected JTextField emailField = new JTextField(main.Main.getEmail() + "@gmail.com");
	protected JLabel nameLabel = new JLabel("Name: ");
	protected JTextField nameField = new JTextField(main.Main.getEmail());
	protected JLabel problemDescLabel = new JLabel("Small Problem Description: ");
	protected JTextArea problemDescArea = new JTextArea("This is a problem description");

	protected JPanel middlePanel = new JPanel();
	protected JPanel middleLeftPanel = new JPanel();
	protected JPanel comboBoxPanel = new JPanel();

	protected JPanel middleRightPanel = new JPanel();

	protected JLabel numberOfVariablesLabel = new JLabel("Number of variables");
	protected JTextField numberOfVariablesField = new JTextField("30");
	protected JLabel maxWaitTimeLabel = new JLabel("Maximum wait time");
	protected JTextField maxWaitTimeField = new JTextField("100000");

	protected JTable table1 = new JTable();
	protected DefaultTableModel dtm1 = new DefaultTableModel();
	protected Object[][] tabledata1 = new Object[30][4];

	protected JPanel bottomPanel = new JPanel();
	protected JPanel bottomRightPanel = new JPanel();
	protected JPanel bottomLeftPanel = new JPanel();

	protected JTable table2 = new JTable();
	protected DefaultTableModel dtm2 = new DefaultTableModel();
	protected Object[][] tabledata2 = new Object[5][5];

	protected JButton saveToXML = new JButton("Save to XML");
	protected JButton loadFromXML = new JButton("Load from XML");
	protected JButton run = new JButton("RUN");

	protected Vector<JCheckBox> v;

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

		problemNameField.setPreferredSize(new Dimension(200, 30));
		topFields.add(problemNameLabel);
		topFields.add(problemNameField);

		topFields.add(problemDescLabel);
		problemDescArea.setLineWrap(true);
		problemDescArea.setWrapStyleWord(true);
		problemDescArea.setPreferredSize(new Dimension(200, 50));
		topFields.add(problemDescArea);

		emailField.setPreferredSize(new Dimension(200, 30));
		topFields.add(emailLabel);
		topFields.add(emailField);

		nameField.setPreferredSize(new Dimension(200, 30));
		nameField.setEnabled(false);
		topFields.add(nameLabel);
		topFields.add(nameField);

		topPanel.add(topFields);

		GuiPanel.add(topPanel);
	}

	private void buildMiddlePanel() {

		middlePanel.setLayout(new BorderLayout(1, 1));

		JLabel configLabel = new JLabel("Configuration");
		configLabel.setHorizontalAlignment(JLabel.CENTER);
		middlePanel.add(configLabel, BorderLayout.NORTH);

		// Right side

		middleRightPanel.setLayout(new BorderLayout());
		middleRightPanel.setPreferredSize(new Dimension(300, 100));
		String header[] = new String[] { "Name", "Type", "BottomRange", "TopRange" };
		dtm1.setColumnIdentifiers(header);
		table1.setModel(dtm1);

		for (int count = 1; count <= 30; count++) {
			tabledata1[count - 1][0] = "Regra" + count;
			tabledata1[count - 1][1] = "Tipo";
			tabledata1[count - 1][2] = "0";
			tabledata1[count - 1][3] = "5";
			dtm1.addRow(tabledata1[count - 1]);
		}

		middleRightPanel.add(new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

		dtm1.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
			}
		});

		// Left side
		middleLeftPanel.setLayout(new GridLayout(2, 2));

		maxWaitTimeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Max wait time: " + maxWaitTimeField.getText());
			}
		});

		numberOfVariablesField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("Number of variables: " +
				// numberOfVariablesField.getText());
				resizeTable(Integer.parseInt(numberOfVariablesField.getText()));
			}
		});

		middleLeftPanel.add(maxWaitTimeLabel);
		middleLeftPanel.add(maxWaitTimeField);

		middleLeftPanel.add(numberOfVariablesLabel);
		middleLeftPanel.add(numberOfVariablesField);

		// SplitPane

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel);
		splitPane.setDividerLocation(400);
		splitPane.setResizeWeight(0.5);

		middlePanel.add(splitPane, BorderLayout.CENTER);
		GuiPanel.add(middlePanel);
	}

	private void buildBottomPanel() {
		bottomPanel.setLayout(new GridLayout(1, 2));

		// Bottom left Panel
		dtm2.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
			}
		});

		bottomLeftPanel.setPreferredSize(new Dimension(300, 100));
		String header[] = new String[] { "Name", "Jar Path" };
		dtm2.setColumnIdentifiers(header);
		table2.setModel(dtm2);

		for (int count = 1; count <= 3; count++) {
			tabledata2[count - 1][0] = "Name";
			tabledata2[count - 1][1] = "Path";
			dtm2.addRow(tabledata2[count - 1]);
		}
		table2.setPreferredSize(null);
		bottomLeftPanel.add(
				new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS),
				BorderLayout.CENTER);

		bottomRightPanel.setPreferredSize(new Dimension(200, 100));
		bottomRightPanel.setLayout(new GridLayout(2, 2));
		bottomRightPanel.add(saveToXML);
		saveToXML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveToXML();
			}
		});

		bottomRightPanel.add(loadFromXML);
		loadFromXML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoadFromXML();
			}
		});

		v = new Vector<JCheckBox>();
		v.add(new JCheckBox("NSGAII", true));
		v.add(new JCheckBox("SMSESOA", false));
		v.add(new JCheckBox("GDE3", false));
		v.add(new JCheckBox("IBEA", false));
		v.add(new JCheckBox("MOCell", false));
		v.add(new JCheckBox("MOEAD", false));
		v.add(new JCheckBox("PAES", false));
		v.add(new JCheckBox("RandomSearch", false));
		v.add(new JCheckBox("MOCH", false));
		v.add(new JCheckBox("SPEA2", false));

		bottomRightPanel.add(new JComboCheckBox(v));

		bottomRightPanel.add(run);
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(GuiPanel,
						"Do you agree that your email can be used\nand stored for"
								+ " status updates and warnings\nabout the execution? ",
						"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					// yes option
					System.out.println("Start to run the optimization...");
					/* Todo add unimplemented mehtods */
					try {
						if (countNumberOf("integer") != 0) {
							new ExperimentsIntegerExternalViaJAR(variablesDefinedFor("integer"), algorithmsChosen(),
									jarsChosen());
						}
					} catch (IOException e1) {

					}
				} else {
					// do nothing
					JOptionPane.showMessageDialog(GuiPanel,
							"Please agree to the terms if you want\n" + "to run the optimization.");
				}
			}
		});

		// Split pane

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bottomLeftPanel, bottomRightPanel);
		splitPane.setDividerLocation(500);
		splitPane.setResizeWeight(0.5);

		bottomPanel.add(splitPane, BorderLayout.CENTER);
		GuiPanel.add(bottomPanel);
	}

	protected void SaveToXML() {
		String timeString = LocalDateTime.now().getYear() + "_" + LocalDateTime.now().getMonthValue() + "_"
				+ LocalDateTime.now().getDayOfMonth() + "_" + LocalDateTime.now().getHour() + "_"
				+ LocalDateTime.now().getMinute() + "_" + LocalDateTime.now().getSecond();

		System.out.println(timeString);
		try {
			XmlWriter x = new XmlWriter(problemNameField.getText() + timeString, problemNameField.getText(),
					problemDescArea.getText(), emailField.getText(), Integer.parseInt(maxWaitTimeField.getText()),
					Integer.parseInt(numberOfVariablesField.getText()), dtm1, dtm2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void LoadFromXML() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			System.out.println(chooser.getSelectedFile().getPath());
			XmlReader xml = new XmlReader(chooser.getSelectedFile().getPath());
		}
	}

	protected void resizeTable(int rows) {

		for (int i = dtm1.getRowCount() - 1; i > -1; i--) {
			dtm1.removeRow(i);
		}

		Object[][] tableDataTemp = new Object[rows][4];
		for (int count = 1; count <= rows; count++) {
			tableDataTemp[count - 1][0] = "Regra" + count;
			tableDataTemp[count - 1][1] = "Tipo";
			tableDataTemp[count - 1][2] = "0";
			tableDataTemp[count - 1][3] = "5";
		}
		for (int count = 0; count < tableDataTemp.length; count++) {
			dtm1.addRow(tableDataTemp[count]);
		}

		dtm1.fireTableDataChanged();
	}

	protected int countNumberOf(String type) {
		int total = 0;
		for (int i = 0; i < dtm1.getRowCount(); i++) {
			if (dtm1.getValueAt(i, 1).equals(type)) {
				total++;
			}
		}
		return total;
	}

	protected String[][] variablesDefinedFor(String type) {
		String[][] variables = new String[countNumberOf(type)][3];
		for (int i = 0; i < variables.length; i++) {
			for (int x = 0; x < dtm1.getRowCount(); x++) {
				if (dtm1.getValueAt(x, 1).equals(type)) {
					variables[i][0] = (String) dtm1.getValueAt(x, 1);
					variables[i][1] = (String) dtm1.getValueAt(x, 2);
					variables[i][2] = (String) dtm1.getValueAt(x, 3);
				}
			}
		}
		return variables;
	}

	protected ArrayList<String> algorithmsChosen() {
		ArrayList<String> algorithms = new ArrayList<String>();
		for (int i = 0; i != v.capacity(); i++) {
			if (v.get(i).isSelected()) {
				algorithms.add(v.get(i).getText());
			}
		}
		return algorithms;
	}

	protected ArrayList<String> jarsChosen() {
		ArrayList<String> objectiveFunctions = new ArrayList<String>();
		for (int i = 0; i < dtm2.getRowCount(); i++) {
			if (!dtm2.getValueAt(i, 1).equals("Path")) {
				objectiveFunctions.add((String) dtm2.getValueAt(i, 1));
			}
		}
		return objectiveFunctions;
	}
}