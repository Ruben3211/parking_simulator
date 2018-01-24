package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;

import model.SimulatorModel;

@SuppressWarnings("serial")
public class CarQueueDisplay extends AbstractView  {
	
	public static JLabel regCarQueue;
	public static JLabel subCarQueue;
	public static JLabel exitCarQueue;

	public CarQueueDisplay(SimulatorModel simulator) {
		super(simulator);
		setSize(600, 50);
		//setup labels
		regCarQueue = new JLabel("Regular cars in queue: 0");
		subCarQueue = new JLabel("Cars with subscription in queue: 0");
		exitCarQueue = new JLabel("Queue for leaving the garage: 0");
		
		//aanpassen labels:
	//	regCarQueue.setText("Regular cars in queue: " + SimulatorLogic.entranceRegQueue.carsInQueue());
	//	subCarQueue.setText("Cars with subscription in queue: " + SimulatorLogic.entranceRegQueue.carsInQueue());
	//	exitCarQueue.setText("Queue for leaving the garage: " + SimulatorLogic.entranceRegQueue.carsInQueue());
		
		this.setLayout(null);
		add(regCarQueue);
		add(subCarQueue);
		add(exitCarQueue);
		regCarQueue.setBounds(400,10, 300, 30);
		subCarQueue.setBounds(400,20, 300, 30);
		exitCarQueue.setBounds(400,40, 300, 10);
		regCarQueue.setVisible(true);
		subCarQueue.setVisible(true);
		exitCarQueue.setVisible(true);
		
	}
	
	public static void updateQueue () {
		regCarQueue.setText("Regular cars in queue: " + SimulatorModel.entranceRegQueue.carsInQueue());
		subCarQueue.setText("Cars with subscription in queue: " + SimulatorModel.entranceSubResQueue.carsInQueue());
		exitCarQueue.setText("Queue for leaving the garage: " + SimulatorModel.exitCarQueue.carsInQueue());
	}

}
