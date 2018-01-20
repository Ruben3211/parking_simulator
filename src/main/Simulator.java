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
		
		screen = new JFrame("SIm");
		screen.setSize(1350, 925);
		screen.setResizable(false);
		screen.getContentPane().setLayout(null);
		carParkView = new CarParkView(simulatorLogic);
		carParkView.setBounds(10, 120, 800, 400);
		screen.getContentPane().add(carParkView);
		carParkView.setBackground(Color.WHITE);
		screen.setVisible(true);

	}
	
	// Needs to be deleted when buttons are added.
	public void run()
	{
		simulatorLogic.run();
	}

}
