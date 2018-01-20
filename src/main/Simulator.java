package main;

import javax.swing.*;
import java.awt.*;

import controller.*;
import logic.*;
import view.*;

public class Simulator {
	
	private JFrame screen;
	private CarParkView carParkView;
	private SimulatorLogic simulatorLogic;
	
	public Simulator() {
		simulatorLogic = new SimulatorLogic(3, 6, 30);
		carParkView = new CarParkView(simulatorLogic);
		
		screen = new JFrame("Parking Garage Simulator");
		screen.setSize(1980, 1080);
		screen.setResizable(false);
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screen.setLayout(null);
		screen.getContentPane().add(carParkView);
		
		carParkView.setBounds(10, 120, 800, 400);
		carParkView.setBackground(Color.WHITE);
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);
	}
	
	// Needs to be deleted when buttons are added.
	public void run()
	{
		simulatorLogic.run();
	}

}
