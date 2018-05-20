package xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.table.DefaultTableModel;

import org.xembly.*;


public class XmlWriter {
	public XmlWriter(String filename, String name, String description, String email,int maxWaitTime, int varNumber, DefaultTableModel dtm, DefaultTableModel dtm2) throws IOException {

		try (PrintStream out = new PrintStream(new FileOutputStream(filename + ".xml"))) {

			Directives directives = new Directives().add("data");
			directives.add("specifications");
			directives.add("problemName").set(name).up();
			directives.add("problemDesc").set(description).up();
			directives.add("problemEmail").set(email).up();
			directives.up();
			directives.add("configuration");
			directives.add("intMaxWaitTime").set(maxWaitTime).up();
			directives.add("variableNumber").set(varNumber).up();
			directives.up();
			directives.add("variables");
			for(int i = 0; i < dtm.getRowCount(); i++) {
				directives.add("variable"+ i);
				directives.add("variableName").set(dtm.getValueAt(i, 0)).up();
				directives.add("variableType").set(dtm.getValueAt(i, 1)).up();
				directives.add("bottomRange").set(dtm.getValueAt(i, 2)).up();
				directives.add("topRange").set(dtm.getValueAt(i, 3)).up();
				directives.up();
			}
			directives.up();
			directives.add("jarsTable");
			for(int i = 0; i < dtm2.getRowCount(); i++) {
				directives.add("jar" + i);
				directives.add("objName").set(dtm2.getValueAt(i, 0)).up();
				directives.add("objType").set(dtm2.getValueAt(i, 1)).up();
				directives.add("topRange").set(dtm2.getValueAt(i, 2)).up();
				directives.add("bottomRange").set(dtm2.getValueAt(i, 3)).up();
				directives.add("jarPath").set(dtm2.getValueAt(i, 4)).up();
				directives.up();
			}

			try {
				System.out.println(new Xembler(directives).xml());
				out.print(new Xembler(directives).xml());
			} catch (ImpossibleModificationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.flush();
			out.close();
		}
	}
}
