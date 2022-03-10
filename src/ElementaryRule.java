import java.util.StringJoiner;
/**
 * ElementaryRule represents any one of the 256 rules that govern the evolution of elementary CAs
 * @author Thang Nguyen
 *
 */
public class ElementaryRule extends Rule {
	
	/**
	 * Ctor uses the parent ctor to create a rule.
	 * @param ruleNum
	 * @throws RuleNumException
	 */
	protected ElementaryRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		
			int min = 0;
			int max = 255;
			if (ruleNum > max || ruleNum < min) {
				throw new RuleNumException(min, max);
			}
		
	}
	
	/**
	 * evolve functions in the same way as it did in project one
	 */
	@Override
	public boolean evolve(boolean[] neighborhood) {
		boolean[] binaryArray1 = new boolean[8];

		String binaryString = Integer.toBinaryString(getRuleNum()); // Receives integer and converts to String
		String binaryRuleNum = String.format("%8s", binaryString).replace(' ', '0'); // adds in the zeros where they should be
		
		for (int idx = 0; idx < binaryArray1.length; ++idx) {
			if (binaryRuleNum.charAt(idx) == '1') {
				binaryArray1[idx] = true;
			} else if (binaryRuleNum.charAt(idx) == '0') {
				binaryArray1[idx] = false;
			}
		}

		// Account for  the 8 neighborhood configurations

		// Branch one
		if (neighborhood[0] == true) {
			if (neighborhood[1] == true) {
				if (neighborhood[2] == true) {
					return binaryArray1[0];
				} else if (neighborhood[2] == false) {
					return binaryArray1[1];
				}
			}

			else if (neighborhood[1] == false) {
				if (neighborhood[2] == true) {
					return binaryArray1[2];
				} else if (neighborhood[2] == false) {
					return binaryArray1[3];
				}
			}
		}

		// Branch Two
		else if (neighborhood[0] == false) {
			if (neighborhood[1] == true) {
				if (neighborhood[2] == true) {
					return binaryArray1[4];
				} else if (neighborhood[2] == false) {
					return binaryArray1[5];
				}
			}

			else if (neighborhood[1] == false) {
				if (neighborhood[2] == true) {
					return binaryArray1[6];
				} else if (neighborhood[2] == false) {
					return binaryArray1[7];
				}
			}

		}

		return false;
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
		return super.getNeighborhoodByRadius(idx, 1, gen);
	}
	
	/**
	 * Return a two-line representation of the elementary rule table. The first line shows the 8 possible neighborhoods separated by spaces;
	 * The second shows the states of the center cells in the next generation. Align each state character on the second line with the center 
	 * of the corresponding neighborhood. All returns will be in the format of the symbol parameters 
	 * 
	 * @param falseSymbol
	 * @param trueSymbol
	 * @return String a rule table in String format
	 */
	@Override
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		
		// Create a boolean array of the 8 possible neighborhood configurations.
		boolean[] genStates = {true, true, true, true, true, false, true, false, true, true, false, false, false, true, true, false, true, false, false, false, true, false, false, false};
		Generation gen = new Generation(genStates);
		StringJoiner joiner = new StringJoiner(" ", "", "");
		/*
		 * Create the first line
		 */
		for (int idx = 1; idx < genStates.length - 1; idx = idx + 3) {
			boolean[] temp = getNeighborhood(idx, gen); // helper method
			Generation tempGen = new Generation(temp); // generation class invoke
			String tempString = tempGen.getStates(falseSymbol, trueSymbol); // conform to the format of trueSymbol and falseSymbol
			joiner.add(tempString);
		}
		/*
		 * Create the second line
		 */
		String binaryString = Integer.toBinaryString(getRuleNum()); // Receives integer and converts to String
		String binaryRuleNum = String.format("%8s", binaryString).replace(' ', '0'); // adds in the zeros where they should be
		
		StringJoiner joiner2 = new StringJoiner("   ", " ", " ");
		for (int idx = 0; idx < binaryRuleNum.length(); ++idx)
		{
			String comp = String.valueOf(binaryRuleNum.charAt(idx));
			if (comp.equals(String.valueOf(0))) { // The following else and else if convert the String of the RuleNum in Binary Format to conform with the trueSymbol and falseSymbol
				joiner2.add(String.valueOf(falseSymbol)); //conform to the format of falseSymbol
			}
			else if (comp.equals(String.valueOf(1))) {
				joiner2.add(String.valueOf(trueSymbol)); //conform to the format of trueSymbol
			}
		}
		String returnStub = joiner.toString() + System.lineSeparator() +joiner2.toString();
		return returnStub;
		
	}
}
