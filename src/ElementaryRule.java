import java.util.StringJoiner;

public class ElementaryRule extends Rule {
	
	protected ElementaryRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		
			int min = 0;
			int max = 255;
			if (ruleNum > max || ruleNum < min) {
				throw new RuleNumException(min, max);
			}
		
	}
	
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
	
	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		return super.getNeighborhoodByRadius(idx, 1, gen);
	}
	
	@Override
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		
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
				joiner2.add(String.valueOf(falseSymbol));
			}
			else if (comp.equals(String.valueOf(1))) {
				joiner2.add(String.valueOf(trueSymbol));
			}
		}
		String returnStub = joiner.toString() + System.lineSeparator() +joiner2.toString();
		return returnStub;
		
	}
}
