package runner;

import main.Simulator;

/**
 * This class is needed for running the application. It uses the main method
 * that will create a new Simulator() object, which will immediately start up
 * the application.
 * 
 * @author Rick Zwaneveld
 * @version 23-01-2018
 */

public class SimulatorRunner {
	
	/**
	 * The main method that is used to start the entire application.
	 * 
	 * @param args no arguments are needed
	 */
	public static void main(String[] args) {
		new Simulator();
	}
}
