import java.util.Arrays;
/** 
 * This class represents a row of cells at a fixed time. Each generation encapsulates a boolean array that represents the cell states.
 * The cells are indexed from left to right starting at zero, so the state of the first cell is cellStates[0], the state of the second 
 * cell is cellStates[1] and so on. 
 * @author Thang Nguyen
 *
 */
public class Generation {

	private boolean[] cellStates;

	/**
	 * Create a generation with one cell for each element in the given array. The state of each cell is specified by the value of the corresponding element.
	 * @param states boolean states, true or false, to be added into boolean[] cellStates
	 */
	public Generation(boolean... states) {
		
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

	/**
	 * Create a generation with one cell for each character in the given String. 
	 * @param states A string of two characters to be read into true and false states. These states will then be added into boolean[] cellStates.
	 * @param trueSymbol Used to compare with a corresponding cell. If equal, the cell is true. Otherwise, the state is false.
	 */
	public Generation(String states, char trueSymbol) {
		if (states == null || states.length() == 0) {
			cellStates = new boolean[1]; // initiation of the cellStates
			cellStates[0] = false;
		}
		
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

	/**
	 * Return the state of the cell with the given index.
	 * @param idx the given index
	 * @return the state of the cell
	 */
	public boolean getState(int idx) { // Make a copy of the array for immutability
		return cellStates[idx];
	}

	/**
	 * Return an array with all of the cell states
	 * @return
	 */
	public boolean[] getStates() {

		boolean[] copyOfCellStates = Arrays.copyOf(cellStates, cellStates.length);

		return copyOfCellStates;
	}

	/**
	 * Return a String representation of the cell states using falseSymbol and trueSymbol as the symbols false and true, respectively.
	 * @param falseSymbol symbol for false
	 * @param trueSymbol symbol for true
	 * @return String a string representation of the cell states using falseSymbol and trueSymbol
	 */
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