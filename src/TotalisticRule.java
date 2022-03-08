
public class TotalisticRule extends Rule {
	protected TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		int min = 0;
		int max = 63; 
		
		if (ruleNum < 0 || ruleNum > max) {
			throw new RuleNumException(min, max);
		}
	}
	
	public boolean evolve(int idx, Generation gen) {
		return false; // stub
	}
	
	public boolean[] getNeighborhood(int idx, int Generation) {
		boolean[] baka = {false};
		return baka;
	}
	
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		String baka = "";
		return baka;
	}
}
