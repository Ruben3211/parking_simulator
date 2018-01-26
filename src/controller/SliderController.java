package controller;

import javax.swing.*;
import model.SimulatorModel;

import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")

public class SliderController extends AbstractController implements ChangeListener{
	
	private JSlider changeSpeed;
	
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

	public void stateChanged(ChangeEvent e) {
		int value = changeSpeed.getValue();
		simulator.setStepPause(value);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
	}
	
}

