import java.util.StringJoiner;
/**
 * TotalisticRule represents ant of the 64 rules that govern the evolution of 1D, two-state totalistic CAs with a neighborhood radius of 2
 * @author Thang Nguyen
 *
 */
public class TotalisticRule extends Rule {
	protected TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		int min = 0;
		int max = 63; 
		
		if (ruleNum < 0 || ruleNum > max) {
			throw new RuleNumException(min, max);
		}
	}
	
	/**
	 * retrieve the neighborhood of the index in a Generation by using the helper method getNeighborhoodByRadius. 
	 * 
	 * @param int the index of the neighborhood's center 
	 * @param gen the generation of the neighborhood
	 * @return booleanArray a boolean array of the neighborhood 
	 */
	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		return super.getNeighborhoodByRadius(idx, 2, gen);  // check for 5 spaces, so use two as the radius param.
	}
	
	/**
	 * Return a two-line representation of the elementary rule table. The first line shows the 6 possible amounts of true states separated by spaces;
	 * The second shows the states of the center cells in the next generation. Align each state character on the second line with the center 
	 * of the corresponding neighborhood. All returns will be in the format of the symbol parameters 
	 * 
	 * @return String a rule table in String format
	 * @param falseSymbol 
	 * @param trueSymbol 
	 */
	@Override
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		int totalisticSet = 5;
		StringJoiner joiner = new StringJoiner(" ", "", "");
		for (int idx = totalisticSet; idx >= 0; --idx) {
			joiner.add(String.valueOf(idx));
		}
		
		
		String binaryString = Integer.toBinaryString(getRuleNum()); // Receives integer and converts to String
		binaryString = String.format("%6s", binaryString).replace(' ', '0'); // adds in the zeros where they should be
		
		String binaryStringSymbol = "";
		for(int idx  = 0; idx < binaryString.length(); ++idx) {
			String compareString = String.valueOf(binaryString.charAt(idx));
			if (compareString.equals(String.valueOf(1))) {
				binaryStringSymbol += trueSymbol;
			}
			else {
				binaryStringSymbol += falseSymbol;
			}
		}
		
		StringJoiner secondJoiner = new StringJoiner(" ", "", "");
		for (int idx = 0; idx < binaryStringSymbol.length(); ++idx) {
			secondJoiner.add(String.valueOf(binaryStringSymbol.charAt(idx)));
		}
		String baka = joiner.toString() + System.lineSeparator() + secondJoiner.toString();
		return baka;
	}

	@Override
	public boolean evolve(boolean[] neighborhood) {
		// TODO Auto-generated method stub
		boolean[] binaryArray1 = new boolean[6];
		//int[] numTrue = {5, 4, 3, 2, 1, 0};
		int trueCount = 0;
		String binaryString = Integer.toBinaryString(getRuleNum()); // Receives integer and converts to String
		binaryString = String.format("%6s", binaryString).replace(' ', '0'); // adds in the zeros where they should be
		
		for (int idx = 0; idx < binaryArray1.length; ++idx) {
			if (binaryString.charAt(idx) == '1') {
				binaryArray1[idx] = true;
			} else if (binaryString.charAt(idx) == '0') {
				binaryArray1[idx] = false;
			}
		}
		for (int idx = 0; idx < neighborhood.length; ++idx) {
			if (neighborhood[idx] == true) {
				++trueCount;
			}
		}
		if(trueCount == 5)
			return binaryArray1[0];
		if(trueCount == 4) 
			return binaryArray1[1];
		if(trueCount == 3)
			return binaryArray1[2];
		if(trueCount == 2) 
			return binaryArray1[3];
		if(trueCount == 1)
			return binaryArray1[4];
		if(trueCount == 0)
			return binaryArray1[5];
		
		return false;
	}
}
