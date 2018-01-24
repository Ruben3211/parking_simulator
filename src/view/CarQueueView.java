package view;

import java.awt.*;

import java.awt.event.*;

import javax.swing.JLabel;

import model.SimulatorModel;

/*
 * This class makes Jlabel and assigns a text to it that keeps track of the number of cars in the queue.
 * When the update method is called it takes the queue and calls the carInQueue method that returns the size of the array. 
 * 
 */

@SuppressWarnings("serial")
public class CarQueueView extends AbstractView  {
	
	public static JLabel regCarQueue;
	public static JLabel subCarQueue;
	public static JLabel exitCarQueue;
	public static JLabel paymentCarQueue;

	public CarQueueView(SimulatorModel simulator) {
		super(simulator);
		setSize(600, 50);
		
		//setup labels
		regCarQueue = new JLabel("Regular cars in queue: 0");
		subCarQueue = new JLabel("Cars with subscription in queue: 0");
		exitCarQueue = new JLabel("Queue for leaving the garage: 0");
		paymentCarQueue = new JLabel("Queue for paying: 0");
		
		
		this.setLayout(null);
		add(regCarQueue);
		add(subCarQueue);
		add(exitCarQueue);
		add(paymentCarQueue);
		regCarQueue.setBounds(400,10, 300, 30);
		subCarQueue.setBounds(400,20, 300, 30);
		exitCarQueue.setBounds(400,40, 300, 10);
		paymentCarQueue.setBounds(400,0, 300, 30);
		regCarQueue.setVisible(true);
		subCarQueue.setVisible(true);
		exitCarQueue.setVisible(true);
		paymentCarQueue.setVisible(true);
		
	}
	/*
	 * Updates the queue in the view when this method is called. 
	 * This update is static so it can be called by SimulatorModel.
	 */
	public static void updateQueue () {
		regCarQueue.setText("Regular cars in queue: " + SimulatorModel.entranceRegQueue.carsInQueue());
		subCarQueue.setText("Cars with subscription in queue: " + SimulatorModel.entranceSubResQueue.carsInQueue());
		exitCarQueue.setText("Queue for leaving the garage: " + SimulatorModel.exitCarQueue.carsInQueue());
		paymentCarQueue.setText("Queue for paying: " + SimulatorModel.paymentCarQueue.carsInQueue());
	}

}
