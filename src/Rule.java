
public class Rule {
	private int ruleNum;
	private String binaryRuleNum;

	public Rule(int ruleNum) {
		if (ruleNum > 255) {
			this.ruleNum = 255;
		} else if (ruleNum < 0) {
			this.ruleNum = 0;
		} else if (ruleNum >= 0 && ruleNum <= 255) {
			this.ruleNum = ruleNum;
		}

		String binaryString = Integer.toBinaryString(ruleNum); // Receives integer and converts to String
		binaryRuleNum = String.format("%8s", binaryString).replace(' ', '0'); // adds in the zeros where they should be

	}

	public int getRuleNum() {

		return ruleNum;
	}

	public static boolean[] getNeighborhood(int idx, Generation gen) {

		boolean[] booleanGen = new boolean[3];
		if (gen.size() == 2) { // checks if the Generation parameter is less than 3
			if (idx == 0) {
				booleanGen[0] = gen.getState(idx);
				booleanGen[1] = gen.getState(idx);
				booleanGen[2] = gen.getState(idx + 1);
				return booleanGen;
			} 
			else if (idx == 1) {
				booleanGen[0] = gen.getState(idx - 1);
				booleanGen[1] = gen.getState(idx);
				booleanGen[2] = gen.getState(idx);
				return booleanGen;
			}
			
		}
		if (gen.size() == 1) { // checks if the Generation parameter is less than 2
			booleanGen[0] = gen.getState(idx);
			booleanGen[1] = gen.getState(idx);
			booleanGen[2] = gen.getState(idx);
			return booleanGen;
		}

		// Circular condition
		if (idx == 0) { // Accounts for the index being in the beginning of the gen array
			booleanGen[0] = gen.getState(gen.size() - 1);
			booleanGen[1] = gen.getState(idx);
			booleanGen[2] = gen.getState(idx + 1);
			return booleanGen;
		}

		else if (idx == gen.size() - 1) { // Accounts for the index being in the end of the gen array
			booleanGen[0] = gen.getState(idx - 1);
			booleanGen[1] = gen.getState(idx);
			booleanGen[2] = gen.getState(0);
			return booleanGen;
		}

		else { // Accounts for ever other situation where the index is somewhere in the center
				// of the gen array
			booleanGen[0] = gen.getState(idx - 1);
			booleanGen[1] = gen.getState(idx);
			booleanGen[2] = gen.getState(idx + 1);
			return booleanGen;
		}

	}

	public boolean evolve(boolean[] neighborhood) {
		/*
		 * Take the binary in String format and convert it to a boolean array of length
		 * 8. The use that to dictate the evolution
		 */

		boolean[] binaryArray1 = new boolean[8];

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
}
