package controller;

import javax.swing.JPanel;

import model.SimulatorModel;

/**
 * This class is responsible of adding a controller to the model. This means 
 * that this controller knows of the models existence.
 * 
 * @author Rick Zwaneveld
 * @version 03-02-2018
 */

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	
	protected SimulatorModel simulator;
	
	/**
	 * The constructor for the class AbstractController.
	 * 
	 * @param simulator the model
	 */
	public AbstractController(SimulatorModel simulator) {
		this.simulator = simulator;
	}
}
