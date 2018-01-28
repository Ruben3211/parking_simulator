package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import model.SimulatorModel;

/**
 * This class is responsible for setting up the buttons needed to control the
 * simulators operation. It adds three buttons and one text field to the
 * controller, with the appropriate names. It also sets the given layout. It
 * uses ActionEvent to know which button has been pressed and subsequently calls 
 * on the right method to execute.
 * 
 * @author Rick Zwaneveld
 * @version 27-01-2018
 */

@SuppressWarnings("serial")
public class RunController extends AbstractController implements ActionListener {

	private JButton start;
	private JFormattedTextField numberOfSteps;
	private JButton steps;
	private JButton stop;
	
	/**
	 * The constructor for the class RunController.
	 * 
	 * @param simulator the model
	 */
	public RunController(SimulatorModel simulator) {
		super(simulator);
		
		setLayout(new GridLayout(0, 4));
		
		start = new JButton("Start");
		start.addActionListener(this);
		add(start);
		
		numberOfSteps = new JFormattedTextField("0");
		numberOfSteps.addActionListener(this);
		add(numberOfSteps);
		
		steps = new JButton("Steps");
		steps.addActionListener(this);
		add(steps);
		
		stop = new JButton("Stop");
		stop.addActionListener(this);
		add(stop);
	}
	
	/**
	 * This method is responsible for registering the input made by the user. 
	 * It matches the input with the given method calls and subsequently
	 * executes these actions.
	 * 
	 * @param e the actionEvent given to the controller
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			simulator.start(40320);
		}
		
		if(e.getSource() == steps) {
			int numberOfSteps = parseSteps();
			simulator.start(numberOfSteps);
		}
		
		if(e.getSource() == stop) {
			simulator.stop();
		}
	}
	
	/**
	 * This method is needed to convert the string of text in the "steps" text
	 * field to and integer.
	 * 
	 * @return integer converted from the string input in the "steps" text field
	 */
	private int parseSteps() {
		return Integer.parseInt(numberOfSteps.getText());
	}
}
