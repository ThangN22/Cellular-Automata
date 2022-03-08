
public abstract class Rule {
	private int ruleNum;
	// Add variables as needed

	protected Rule(int ruleNum) throws RuleNumException {
		this.ruleNum = ruleNum;
	}

	public int getRuleNum() {

		return ruleNum;
	}

	public abstract boolean[] getNeighborhood(int idx, Generation gen);
	

	public abstract boolean evolve(boolean[] neighborhood);

	public Generation evolve(Generation gen) {
		String evolvedGenString = "";
		for (int idx = 0; idx < gen.size(); ++idx) {
			if (evolve(getNeighborhood(idx, gen)) == true) { // Loop through the Generation gen to find the boolean results of each index's neighborhoods
				evolvedGenString += '1';
			} else
				evolvedGenString += '0';
		} // Append chars '1' and '0' to the evolvedGenString accordingly
		Generation evolved = new Generation(evolvedGenString, '1'); // Use the string in making a new generation to be returned

		return evolved; 
	}
	
	public abstract String ruleTableString(char falseSymbol, char trueSymbol);
	
	/**
	 * for getRadius, perhaps try absolute value of % instead of using floormod? 
	 * abstract classes do not have a method body, but rather has a semicolon
	 */
	public static boolean[] getNeighborhoodByRadius(int idx, int radius, Generation gen) {
		int booleanSize = 1 + (radius * 2);
		boolean[] cellStates = new boolean[booleanSize];
		int genIdx = Math.floorMod(idx - radius, gen.size());
		for (int index = 0; index < cellStates.length; ++index) {
			
			cellStates[index] = gen.getState(genIdx);
			++genIdx;
		}
		return cellStates;
	}
}

	
