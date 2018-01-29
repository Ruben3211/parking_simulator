package controller;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;

import model.SimulatorModel;

/**
 * This class is responsible for creating a slider that can be used to change 
 * the speed of the simulation. It implements a changeListener that calls on the 
 * appropriate methods when the value of the slider gets changed.
 * 
 * @author Detmer Struiksma & Rick Zwaneveld
 * @version 27-01-2018
 */

@SuppressWarnings("serial")
public class SliderController extends AbstractController {

	private JSlider changeSpeed;
	private Hashtable<Integer, JLabel> speedLabels;

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
		
		speedLabels = new Hashtable<Integer, JLabel>();
		speedLabels.put(1, new JLabel("Slow"));
		speedLabels.put(999, new JLabel("Fast"));
		
		changeSpeed.setLabelTable(speedLabels);
		changeSpeed.setPaintLabels(true);

		add(changeSpeed);
	}
}
