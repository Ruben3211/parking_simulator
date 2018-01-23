package controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import logic.SimulatorLogic;

/**
 * This class is responsible for setting up the buttons needed to control the
 * simulators operation. It adds three buttons and one text field to the
 * controller, with the appropriate names. It also sets the given layout It then 
 * uses a Listener to know which put has been called and subsequently calls on 
 * the right method to execute.
 * 
 * @author Rick Zwaneveld
 * @version 23-01-2018
 */

@SuppressWarnings("serial")
public class RunController extends AbstractController {

	private JButton start;
	private JFormattedTextField numberOfSteps;
	private JButton steps;
	private JButton stop;
	
	/**
	 * The constructor for this class.
	 * 
	 * @param simulator the model
	 */
	public RunController(SimulatorLogic simulator) {
		super(simulator);
		
		setSize(450, 50);
		this.setLayout(null);
		
		start = new JButton("Start");
		start.addActionListener(this);
		add(start);
		start.setBounds(50, 10, 70, 30);
		
		numberOfSteps = new JFormattedTextField("0");
		numberOfSteps.addActionListener(this);
		add(numberOfSteps);
		numberOfSteps.setBounds(140, 10, 70, 30);
		
		steps = new JButton("Steps");
		steps.addActionListener(this);
		add(steps);
		steps.setBounds(230, 10, 70, 30);
		
		stop = new JButton("Stop");
		stop.addActionListener(this);
		add(stop);
		stop.setBounds(320, 10, 70 ,30);	
	}
	
	/**
	 * This method is responsible for registering the input made by the user. 
	 * It matches the input with the given method calls on subsequently
	 * executes these actions.
	 * 
	 * @param e the actionEvent given to the controller
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			simulator.step();
		}
		if (e.getSource() == steps) {
			int numberOfSteps = parseSteps();
			simulator.start(numberOfSteps);
		}
		if (e.getSource() == stop) {
			simulator.stop();
		}
	}
	
	/**
	 * This method is needed to convert the string of text in the "steps" text
	 * field to and integer.
	 * 
	 * @return integer made from the string input in the "steps" text field
	 */
	
	private int parseSteps() {
		return Integer.parseInt(numberOfSteps.getText());
	}
}
