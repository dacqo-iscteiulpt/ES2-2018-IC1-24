
package jmetal;

import java.io.File;

public class JMetalAlgorithmList {
	
	protected final String PATH = "/Users/Ltfx/.m2/repository/org/uma/jmetal/jmetal-algorithm/5.5/";

	public static void main(String[] args) {
		
	}
	
	public JMetalAlgorithmList() {
		File folder = new File(PATH);
		File[] listOfFiles = folder.listFiles();{
			
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName());
				}
			}
		}
		
	}

}