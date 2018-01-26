package controller;

import javax.swing.*;
import model.SimulatorModel;

import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
/**
 * This class is responsible for creating a slider controller that changes the speed of the simulation.
 * It implements a changelistener that calls a method when the value of the slider gets changed.
 * 
 * @author Detmer Struiksma
 * @version 26-01-2018
 *
 */

public class SliderController extends AbstractController implements ChangeListener{
	// Instance variables.
	private JSlider changeSpeed;
	/**
	 * Constructor for the class.
	 * 
	 * @param simulator Takes an object of type SimulatorModel to call methods the SimulatorModel class.
	 */
	public SliderController(SimulatorModel simulator) {
		super(simulator);
		
		this.setLayout(null);
		changeSpeed = new JSlider(10,200,100);
		changeSpeed.addChangeListener(this);
		
		changeSpeed.setMinorTickSpacing(4); 
		changeSpeed.setMajorTickSpacing(40);
		changeSpeed.setPaintTicks(true);
		changeSpeed.setInverted(true);
		add(changeSpeed);
		
		changeSpeed.setBounds(400, 10, 200, 40);
		
		
	}
    /**
     * This method is responsible for the speed of the simulation.
     * It takes the value of the slider and uses a setter for the variable setStepPause to change the speed of the simulation.
     * 
     * @param e Is used to notify when the state of the slider has changed.
     * 
     */
	public void stateChanged(ChangeEvent e) {
		int value = changeSpeed.getValue();
		simulator.setStepPause(value);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
	}
	
}

