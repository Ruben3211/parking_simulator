package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;

import model.Car;
import model.CarQueue;
import model.SimulatorModel;

/*
 * This class makes Jlabel and assigns a text to it that keeps track of the number of cars in the queue.
 * When the update method is called it takes the queue and calls the carInQueue method that returns the size of the array. 
 * 
 */

@SuppressWarnings("serial")
public class CarQueueView extends AbstractView  {
	
	private JLabel entranceOne, entranceOneQueue;
	private JLabel entranceTwo, entranceTwoQueue;
	private JLabel payment, paymentQueue;
	private JLabel exit, exitQueue;

	public CarQueueView(SimulatorModel simulator) {
		super(simulator);
		
		setSize(200, 200);
		setLayout(new GridLayout(4, 2));
		
		entranceOne = new JLabel("Entrance one: ");
		entranceOneQueue = new JLabel("0");
		
		entranceTwo = new JLabel("Entrance two: ");
		entranceTwoQueue = new JLabel("0");
		
		payment = new JLabel("Payment: ");
		paymentQueue = new JLabel("0");
		
		exit = new JLabel("Exit");
		exitQueue = new JLabel("0");
		
		add(entranceOne);
		add(entranceOneQueue);
		
		add(entranceTwo);
		add(entranceTwoQueue);
		
		add(payment);
		add(paymentQueue);
		
		add(exit);
		add(exitQueue);
	}
	
	/*
	 * Updates the queue in the view when this method is called. 
	 * This update is static so it can be called by SimulatorModel.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/**
		setQueueColor(g, getModel().getRegCarQueue());
		setQueueColor(g, getModel().getSubResCarQueue());
		setQueueColor(g, getModel().getPaymentCarQueue());
		setQueueColor(g, getModel().getExitCarQueue());
		*/
		entranceOneQueue.setText(String.valueOf(getModel().getRegCarQueue().carsInQueue()));
		entranceTwoQueue.setText(String.valueOf(getModel().getSubResCarQueue().carsInQueue()));
		paymentQueue.setText(String.valueOf(getModel().getPaymentCarQueue().carsInQueue()));
		exitQueue.setText(String.valueOf(getModel().getExitCarQueue().carsInQueue()));
	}
	/**
	private void setQueueColor(Graphics g, CarQueue queueData) {
		int top = 0;
		if(queueData == getModel().getRegCarQueue()); top = 0;
		if(queueData == getModel().getSubResCarQueue()); top = 33;
		if(queueData == getModel().getPaymentCarQueue()); top = 66;
		if(queueData == getModel().getExitCarQueue()); top = 99;
		for(int i = 0; i < queueData.carsInQueue(); i++) {
			if(queueData.peekCar(i) != null) {
				Car car = queueData.peekCar(1);
				g.setColor(car.getColor());
				g.fillRect(0 + (i * 21), top, 20, 10);
			}
		}
	}*/
}
