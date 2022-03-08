
public class ElementaryRule extends Rule {

	protected ElementaryRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		
			int min = 0;
			int max = 255;
			if (ruleNum > max || ruleNum < min) {
				throw new RuleNumException(min, max);
			}
		
	}
	
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
	
	public boolean[] getNeighborhood(int idx, Generation gen) {
		return super.getNeighborhoodByRadius(idx, 1, gen);
	}
	
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		String returnStub = "";
		return returnStub;
		
	}
}
