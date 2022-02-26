
public class CellularAutomatonNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CellularAutomatonNotFoundException(String s) {
		super("Unknown cellular automaton type " + s);
	}
}
