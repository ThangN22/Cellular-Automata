/**
 * The Application class includes a main method accepting command line arguments
 * to create and simulate an appropriate CA.
 * 
 * @author Rafal Jabrzemski, Thang Nguyen
 *
 */
public class Application {

	private static final int NUM_EXPECTED_ARGS = 6;

	private static final int IDX_CA = 0;
	private static final int IDX_RULE_NUM = 1;
	private static final int IDX_FALSE_SYMBOL = 2;
	private static final int IDX_TRUE_SYMBOL = 3;
	private static final int IDX_INITIAL_GENERATION = 4;
	private static final int IDX_NUM_EVOLVE = 5;

	private static final String ARG_NAMES = "ca rule-num false-symbol true-symbol initial-generation num-evolutions";

	// Source and class file names must match, so Application can be hard-coded
	private static final String USAGE_FMT_STRING_CLASS = "Usage: java Application " + ARG_NAMES;

	// The jar file may be renamed, so this will be varied
	private static final String USAGE_FMT_STRING_JAR = "Usage: java -jar %s " + ARG_NAMES;

	private String[] appArgs;

	/**
	 * Application constructor
	 * 
	 * @param args arguments
	 */
	public Application(String[] args) {
		// TODO: Validate the number of arguments passed
		// and set the appArgs variable.

		validateNumArgs(args);
		this.appArgs = args;

	}

	/**
	 * Validate the number of arguments. Throw exception of not 6.
	 * 
	 * @param args arguments
	 */
	private void validateNumArgs(String[] args) {
		// TODO: Validate the number of arguments and throw an exception
		// if they do not match the expected amount.

		if (args.length != NUM_EXPECTED_ARGS) {
			throwRuntimeExceptionWithUsageMessage();
		}

	}

	/**
	 * Exception method.
	 */
	private void throwRuntimeExceptionWithUsageMessage() {
		// Implementation provided
		if (runningAsJar()) {
			// Get the path to the executing file
			String path = Application.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			// Only take path after last slash (to get file name).
			// A hard-coded slash is fine since Java URLs use /
			String file = path.substring(path.lastIndexOf("/") + 1);
			throw new RuntimeException(String.format(USAGE_FMT_STRING_JAR, file));
		} else {
			throw new RuntimeException(String.format(USAGE_FMT_STRING_CLASS));
		}
	}

	/**
	 * Returns true if the Application is running as a JAR and false otherwise.
	 * 
	 * @return boolean true or false
	 */
	private boolean runningAsJar() {
		// Implementation provided
		return Application.class.getResource("Application.class").toString().startsWith("jar");
	}

	/**
	 * Parses each of the six arguments, constructs the appropriate Automaton, and
	 * prints out the full evolution.
	 * 
	 * @param args arguments
	 */
	private void parseArgs(String[] args) {
		// TODO: Parse each of the six arguments, construct the appropriate
		// Automaton, and print out the full evolution to System.out.
		// Refer to the README for details on exception handling.

		try {
			// Create variables to assign elements of args to
			String ca;
			int ruleNum;
			char thisFalseSymbol;
			char thisTrueSymbol;
			String states;
			int numEvolve;

			// assign elements of args to variables
			ca = appArgs[IDX_CA];
			ruleNum = Integer.parseInt(args[IDX_RULE_NUM]);
			thisFalseSymbol = appArgs[IDX_FALSE_SYMBOL].charAt(0);
			thisTrueSymbol = appArgs[IDX_TRUE_SYMBOL].charAt(0);
			states = appArgs[IDX_INITIAL_GENERATION];
			numEvolve = Integer.parseInt(args[IDX_NUM_EVOLVE]);

			if (args[IDX_CA].equalsIgnoreCase("TCA")) {
				int min = 0;
				int max = 63;
				if (ruleNum > max || ruleNum < min) {
					throw new RuleNumException(min, max);
				}
			} else if (args[IDX_CA].equalsIgnoreCase("ECA")) {
				int min = 0;
				int max = 255;
				if (ruleNum > max || ruleNum < min) {
					throw new RuleNumException(min, max);
				}
			}

			// invoke methods of various classes (Automaton, Generation) to run an automaton
			Generation initial = new Generation(states, thisTrueSymbol);
			Automaton auto = Automaton.createAutomaton(CellularAutomaton.parse(ca), ruleNum, initial); // Double static
																										// calls
			auto.falseSymbol = thisFalseSymbol;
			auto.trueSymbol = thisTrueSymbol;
			auto.evolve(numEvolve);
			System.out.println(auto.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * Calls the parseArgs(String[] args) method using the previously given arguments.
	 */
	public void run() {
		parseArgs(appArgs);
	}

	/**
	 * Constructs and runs an Application using the supplied main method arguments.
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		// TODO: Construct and run an Application using the
		// supplied main method arguments. Refer to the README
		// for details on RuntimeException handling.

		Application app = new Application(args);
		try {

			app.run();
		}

		catch (RuntimeException e) {
			System.err.println(e.getMessage());
		}

	}
}
