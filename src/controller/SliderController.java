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
		changeSpeed = new JSlider(0,60,30);
		changeSpeed.addChangeListener(this);
		
		changeSpeed.setMinorTickSpacing(4);  
		changeSpeed.setMajorTickSpacing(20);  
		changeSpeed.setPaintTicks(true);  
		changeSpeed.setPaintLabels(true);
		add(changeSpeed);
		
		changeSpeed.setBounds(400, 10, 200, 40);
		
		
	}

	public void stateChanged(ChangeEvent e) {
		changeSpeed.setValue(changeSpeed.getValue());
		changeTime();
		}
	
	public void changeTime() {
		int value = changeSpeed.getValue();
		if(value > 0 && value < 10) {
			
			try {
				   Thread.sleep(400);
				}
			catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			    }
			    
		System.out.println(value);
		}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
	}
	
}

