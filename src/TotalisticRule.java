
public class TotalisticRule extends Rule {
	protected TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		int min = 0;
		int max = 63; 
		
		if (ruleNum < 0 || ruleNum > max) {
			throw new RuleNumException(min, max);
		}
	}
	
	
	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		return super.getNeighborhoodByRadius(idx, 2, gen);  // check for 5 spaces, so use two as the radius param.
	}
	
	@Override
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		String baka = "";
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
		if (trueCount == 5)
			return binaryArray1[0];
		if(trueCount ==4) 
			return binaryArray1[1];
		if(trueCount == 3)
			return binaryArray1[2];
		if(trueCount == 2) 
			return binaryArray1[3];
		if(trueCount ==1)
			return binaryArray1[4];
		if(trueCount ==0)
			return binaryArray1[5];
		
		return false;
	}
}
