import java.io.IOException;

public class TotalisticAutomaton extends Automaton {

	protected TotalisticAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
		// TODO Auto-generated constructor stub
	}
	
	protected TotalisticAutomaton(String filename) throws IOException, RuleNumException {
		super(filename);
	}

	@Override
	protected Rule createRule(int ruleNum) {
		Rule rule = null;
		try {
		rule = new TotalisticRule(ruleNum);
		return rule;
		}
		catch (RuleNumException e) {
			e.printStackTrace();
		}
		return null;
	}

}
