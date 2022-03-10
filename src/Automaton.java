import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public abstract class Automaton {

	private Rule rule;
	private ArrayList<Generation> generations = new ArrayList<>();
	public char falseSymbol = '0';
	public char trueSymbol = '1';

	protected Automaton(int ruleNum, Generation initial) throws RuleNumException {
		rule = createRule(ruleNum);
		generations.add(initial);
	}

	protected Automaton(String filename) throws IOException, RuleNumException {
		Scanner scanner = new Scanner(new File(filename));
		
		this.rule = createRule(scanner.nextInt());
		scanner.nextLine();
		this.falseSymbol = scanner.next().charAt(0);
		this.trueSymbol = scanner.next().charAt(0);
		scanner.nextLine(); 
		Generation temp = new Generation(scanner.nextLine(), this.trueSymbol);
		generations.add(temp);
		
		scanner.close();
		}
	

	public int evolve(int numSteps) {
		if (numSteps <= 0) {
			return 0;
		}
		else if(numSteps > 0) {
			for (int idx = 0; idx < numSteps; ++idx) {
				generations.add(generations.size(), rule.evolve(generations.get(generations.size() - 1)));
			}
		}
		return numSteps;
	}

	public Generation getGeneration(int stepNum) {
		/*
		 * If stepNum does not match with the evolutions that have already occurred, 
		 * evolve it an X amount of time so that the number of evolutions match with stepNum.
		 */
		if(stepNum > generations.size()) 
		{
		evolve((stepNum + 1) - generations.size());
		}
		return generations.get(stepNum); // stub
	}
	
	public Generation getCurrentGeneration( ) {
		return (generations.get(generations.size() - 1));
	}
	
	public int getRuleNum() {
		return rule.getRuleNum(); // stub
	}
	
	public int getTotalSteps() {
		return generations.size() - 1;
	}
	
	public void saveEvolution(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(toString()); // calls the toString method
		bw.close();
	}
	
	public String toString() {
		StringJoiner automatonJoiner = new StringJoiner(System.lineSeparator());
		for(int idx = 0; idx < generations.size(); ++idx) {
			automatonJoiner.add((generations.get(idx).getStates(falseSymbol, trueSymbol))); 
		}
		String genToString = automatonJoiner.toString();
		return genToString;
	}
	
	public String ruleTableString() {
		return rule.ruleTableString(falseSymbol, trueSymbol);
	}
	
	protected abstract Rule createRule(int ruleNum) ;
	
	public static Automaton createAutomaton(CellularAutomaton ca, int ruleNum, Generation initial) throws RuleNumException {
		if (ca == null) {
			return null;
		}
		if(ca == CellularAutomaton.ECA) {
			Automaton AutoElementary = new ElementaryAutomaton(ruleNum, initial);
			return AutoElementary;
		}
		
		else if(ca == CellularAutomaton.TCA) {
			Automaton AutoTotalistic = new TotalisticAutomaton(ruleNum, initial);
			return AutoTotalistic;
		}
		
		
		return null;
		
	}

}