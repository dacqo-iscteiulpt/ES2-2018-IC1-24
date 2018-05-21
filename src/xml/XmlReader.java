package xml;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReader {

	private String PATH;
	protected ArrayList<Regra> regras= new ArrayList<Regra>();
	protected ArrayList<Jar> jarsList = new ArrayList<Jar>();
	protected String problemName;
	protected String problemDesc;
	protected String problemEmail;
	protected String maxWaitTime;
	protected String numberofVariables;
	
	public XmlReader(String PATH) {
		this.PATH = PATH;
		buildFile();
//		System.out.println(getProblemName());
//		System.out.println(getProblemDesc());
//		System.out.println(getProblemEmail());
//		System.out.println(getMaxWaitTime());
//		System.out.println(getNumberofVariables());
	}

	public String getMaxWaitTime() {
		return maxWaitTime;
	}

	public String getNumberofVariables() {
		return numberofVariables;
	}

	private void buildFile() {
		try {
			File file = new File(PATH);

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(file);
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			if (doc.hasChildNodes()) {
				printNote(doc.getChildNodes());

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void printNote(NodeList nodeList) {
		String aux = "";
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				aux+=tempNode.getTextContent();
				if (tempNode.hasAttributes()) {
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
					}
				}
			}
		}
		//		System.out.println(aux);
		String text = aux.replace("\n", ";");
		text = text.replaceAll(";;;;", "§");
		text = text.replaceAll(";;;", "%");
		text = text.replaceAll(";;", "#");
		text = text.replaceAll(";", "!");
//		System.out.println(text);
		loadIntoGUI(text);
	}

	private void loadIntoGUI(String text) {
		String[] sections = text.split("§");
		
		//Section 0
		
		String[] description = sections[0].split("%");
		
//		System.out.println(description[1]);
		String[] nameDescEmail = description[0].split("!");
		problemName = nameDescEmail[0].substring(1);
		problemDesc = nameDescEmail[1];
		problemEmail = nameDescEmail[2];

		String[] vars = description[1].split("!");
		maxWaitTime = vars[0];
		numberofVariables = vars[1];
		
		//Section 1
		
		String[] rules = sections[1].split("%");
		for(int i= 0; i < rules.length; i++) {
//			System.out.println(rules[i]);
			String[] rule = rules[i].split("!");
			Regra x = new Regra(rule[0],rule[1],rule[2],rule[3]);
//			System.out.println(x.toString());
			regras.add(x);
		}
		
		//Section 2
		String[] jars = sections[2].substring(1).split("%");
		for(int i= 0; i < jars.length; i++) {
//			System.out.println(jars[i]);
			String[] jar = jars[i].split("!");
			Jar j = new Jar(jar[0], jar[1]);
//			System.out.println(j.toString());
			jarsList.add(j);
		}
	}

	public ArrayList<Regra> getRegras() {
		return regras;
	}

	public ArrayList<Jar> getJarsList() {
		return jarsList;
	}

	public String getProblemName() {
		return problemName;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public String getProblemEmail() {
		return problemEmail;
	}
}