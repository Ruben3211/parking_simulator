package controller;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.SimulatorModel;

/**
 * This class is responsible of adding a controller to the model. This means 
 * that this controller knows of the models existence.
 * 
 * @author Rick Zwaneveld
 * @version 23-01-2018
 */

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel implements ActionListener {
	
	protected SimulatorModel simulator;
	
	/**
	 * The constructor for this class.
	 * 
	 * @param simulator the model
	 */
	public AbstractController(SimulatorModel simulator) {
		this.simulator = simulator;
	}
}
