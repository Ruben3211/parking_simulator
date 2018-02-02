package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.SimulatorModel;

/**
 * This class will be used to create a button for the user to press. When
 * pressing this button, the entire application will reset to their default
 * values. This way the user can restart the beginning of the simulation
 * without having the restart the entire application.
 * 
 * @author Rick Zwaneveld
 * @version 03-02-2018
 */

@SuppressWarnings("serial")
public class ResetController extends AbstractController implements ActionListener {
	
	private JButton reset;
	
	/**
	 * The constructor for the class ResetController.
	 * 
	 * @param simulator the model
	 */
	public ResetController(SimulatorModel simulator) {
		super(simulator);
		
		reset = new JButton("Reset Simulator");
		reset.addActionListener(this);
		add(reset);
	}

	/**
	 * This method will register the user pressing the reset button. When the
	 * button is clicked, the correct method is executed and the application
	 * will reset.
	 * 
	 * @param e the actionEvent given to the controller
	 */
	public void actionPerformed(ActionEvent e) {
		simulator.reset();
	}
}
