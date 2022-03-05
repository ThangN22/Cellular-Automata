import java.util.Arrays;

public class Generation {

	private boolean[] cellStates;

	// Written during tutor hours 02/18/2022 alongside Tommy Pham and Halyn Nguyen
	public Generation(boolean... states) {
		
// I OMITTED CELLSTATES == NULL AND EVERTHING WORKED FOR SOME REASON
		if (states == null || states.length <= 0) {
			cellStates = new boolean[1]; // initiation of the cellStates
			cellStates[0] = false;
		} 
		else {
			cellStates = new boolean[states.length]; // initiation of the cellStates

			for (int idx = 0; idx < cellStates.length; ++idx) {
				cellStates[idx] = states[idx];
			}
		}
	}

	public Generation(String states, char trueSymbol) {
		if (states == null || states.length() == 0) {
			cellStates = new boolean[1]; // initiation of the cellStates
			cellStates[0] = false;
		}
		
		// REMEMBER THIS ELSE!!!
		else {
			cellStates = new boolean[states.length()]; // initiation of the cellStates

			for (int idx = 0; idx < cellStates.length; ++idx) {
				if (states.charAt(idx) == trueSymbol) {
					cellStates[idx] = true;
				} else
					cellStates[idx] = false;
			}
		}
	}

	public boolean getState(int idx) { // Make a copy of the array for immutability
		return cellStates[idx];
	}

	public boolean[] getStates() {

		boolean[] copyOfCellStates = Arrays.copyOf(cellStates, cellStates.length);

		return copyOfCellStates;
	}

	public String getStates(char falseSymbol, char trueSymbol) {
		String stanTwice = ""; // This creates a string to be appended with the parameters falseSymbol and true Symbol
		for (int idx = 0; idx < this.cellStates.length; ++idx) {
			if (cellStates[idx] == true) {
				stanTwice += trueSymbol;
			} else if (cellStates[idx] == false) {
				stanTwice += falseSymbol;
			}

		}
		return stanTwice;
	}

	public int size() {
		return cellStates.length;
	}

}