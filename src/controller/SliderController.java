package controller;

import java.awt.event.ActionEvent;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.SimulatorModel;

/**
 * This class is responsible for creating a slider controller that changes the speed of the simulation.
 * It implements a changelistener that calls a method when the value of the slider gets changed.
 * 
 * @author Detmer Struiksma
 * @version 26-01-2018
 */

@SuppressWarnings("serial")
public class SliderController extends AbstractController implements ChangeListener{

	private JSlider changeSpeed;
	private Hashtable<Integer, JLabel> labels;

	/**
	 * The constructor for the class SliderController.
	 * 
	 * @param simulator the model
	 */
	public SliderController(SimulatorModel simulator) {
		super(simulator);
		
		changeSpeed = new JSlider(JSlider.HORIZONTAL, 1, 999, (1000 - simulator.getStepPause()));
		changeSpeed.addChangeListener(e -> {
			simulator.setStepPause(1000 - (changeSpeed.getValue()));
			revalidate();
		});
		
		changeSpeed.setMajorTickSpacing(998);
		changeSpeed.setPaintTicks(true);
		
		labels = new Hashtable<Integer, JLabel>();
		labels.put(1, new JLabel("Slow"));
		labels.put(999, new JLabel("Fast"));
		
		changeSpeed.setLabelTable(labels);
		changeSpeed.setPaintLabels(true);

		add(changeSpeed);
	}

	public void stateChanged(ChangeEvent e) {

	}
	
	public void actionPerformed(ActionEvent a) {
			
	}
}
