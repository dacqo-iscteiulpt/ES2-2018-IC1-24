package jmetal;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.JMetalException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Implementação de um problema do tipo Integer que executa o .jar externo
   NMMin.jar e pode ser usado como um dos problema de teste indicados 
   no encunciado do trabalho */

@SuppressWarnings("serial")
public class MyProblemIntegerExternalViaJAR extends AbstractIntegerProblem {

	ArrayList<String> jars;

	public MyProblemIntegerExternalViaJAR(String[][] variables, ArrayList<String> jars) throws JMetalException {
		setNumberOfVariables(variables.length);
		setNumberOfObjectives(2);
		setName("MyProblemIntegerExternalViaJAR");

		List<Integer> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Integer> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < variables.length; i++) {
			lowerLimit.add(Integer.parseInt(variables[i][1]));
			upperLimit.add(Integer.parseInt(variables[i][2]));
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);

		this.jars = jars;
	}

	public void evaluate(IntegerSolution solution) {
		for (int i = 0; i != jars.size(); i++) {
			System.out.println(jars.size());
			String solutionString = "";
			String evaluationResultString = "";
			for (int j = 0; j < solution.getNumberOfVariables(); j++) {
				solutionString = solutionString + " " + solution.getVariableValue(j);
			}
			try {
				String line;
				Process p = Runtime.getRuntime().exec("java -jar " + jars.get(i) + " " + solutionString);
				BufferedReader brinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = brinput.readLine()) != null) {
					evaluationResultString += line;
				}
				brinput.close();
				p.waitFor();
			} catch (Exception err) {
				err.printStackTrace();
			}

			String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
			// It is assumed that all evaluated criteria are returned in the
			// same result string
			for (int z = 0; z < solution.getNumberOfObjectives(); z++) {
				solution.setObjective(z, Integer.parseInt(individualEvaluationCriteria[z]));
			}
		}
	}
}
