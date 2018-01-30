package runner;

import javax.swing.UIManager;

import main.Simulator;

/**
 * This class is needed for running the application. It uses the main method
 * that will create a new Simulator() object, which will immediately start up
 * the application. The main method includes a UIManager to change to look and
 * feel of the application.
 * 
 * @author Rick Zwaneveld
 * @version 30-01-2018
 */

public class SimulatorRunner {
	
	/**
	 * The main method that is used to start the entire application. It first
	 * uses the UIManager to set the look of the UI to the "Nimbus" UI, after
	 * which it creates the simulator.
	 * 
	 * @param args no arguments are needed
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new Simulator();
	}
}
