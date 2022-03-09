/**
 * The abstract rule represents any rule that governs the evolution of a 1D, two-state CA.
 * @author Thang Nguyen
 *
 */
public abstract class Rule {
	private int ruleNum;
	// Add variables as needed

	protected Rule(int ruleNum) {
		this.ruleNum = ruleNum;
	}

	public int getRuleNum() {

		return ruleNum;
	}
	/**
	 * Return the cell states in the neighborhood of the cell with the given index
	 * @param idx
	 * @param gen the generation to get the neighborhood from 
	 * @return neighborhood
	 */
	public abstract boolean[] getNeighborhood(int idx, Generation gen);
	
	/**
	 * Return the next state of a cell in a neighborhood with the given states.
	 * @param neighborhood
	 * @return boolean state of the cell
	 */
	public abstract boolean evolve(boolean[] neighborhood);

	/**
	 * Return the next generation with the given generation
	 * @param gen
	 * @return the evolved generation
	 */
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
	
	/*
	 * Return the table that depicts the rule using the given characters to represent false and true.
	 */
	public abstract String ruleTableString(char falseSymbol, char trueSymbol);
	
	/**
	 * Return the cell states in the neighborhood of the cell with the givien index and specified radius.
	 * The radius refers to the additional number of cells to the left/right of the given index.
	 * @param idx the location in which the radius is centered around
	 * @param radius the scope of coverage from the index to its left and right 
	 * @param gen the generation in which the index is drawn from
	 * @return the neighborhood
	 */
	public static boolean[] getNeighborhoodByRadius(int idx, int radius, Generation gen) {
		int booleanSize = 1 + (radius * 2); 
		boolean[] cellStates = new boolean[booleanSize];// Create a boolean array with the size of the radius
		
		for (int index = 0; index < cellStates.length; ++index) { // Fill in the array with the states of the param.
			int genIdx = index + (idx - radius);
			genIdx = Math.floorMod(genIdx, gen.size());
			cellStates[index] = gen.getState(genIdx);
			++genIdx;
		}
		return cellStates;
	}
}

	
