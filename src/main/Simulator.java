package main;

import javax.swing.*;
import java.awt.*;

import controller.*;
import logic.*;
import view.*;

public class Simulator {
	
	private SimulatorLogic simulatorLogic;
	
	private CarParkView carParkView;
	
	private JFrame screen;
	
	public Simulator() {
		simulatorLogic = new SimulatorLogic(3, 6, 30);
		
		screen = new JFrame("Parking garage simulator");
		screen.setSize(1980, 1080);
		screen.setResizable(false);
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screen.getContentPane().setLayout(null);
		
		carParkView = new CarParkView(simulatorLogic);
		carParkView.setBounds(10, 120, 800, 400);
		carParkView.setBackground(Color.WHITE);
		
		screen.getContentPane().add(carParkView);
		screen.setVisible(true);
	}
	
	// Needs to be deleted when buttons are added.
	public void run()
	{
		simulatorLogic.run();
	}

}
