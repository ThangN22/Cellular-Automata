import java.io.IOException;

public class ElementaryAutomaton extends Automaton {

	protected ElementaryAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
		
	}
	
	protected ElementaryAutomaton(String filename) throws IOException, RuleNumException {
		super(filename);
	}

	protected Rule createRule(int ruleNum) {
		Rule rule = null;
		try {
		rule = new ElementaryRule(ruleNum);
		return rule;
		}
		catch (RuleNumException e) {
			e.printStackTrace();
		}
		return null;
	}

}
