package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;

import model.Car;
import model.CarQueue;
import model.SimulatorModel;

/**
 * This class is used for keeping track of all the queues. It will display the
 * amount of cars in each queue and will show a colored rectangle to display
 * which type of cars these are.
 * 
 * @author Ruben Bonga & Rick Zwaneveld
 * @version 27-01-2018
 */

@SuppressWarnings("serial")
public class CarQueueView extends AbstractView  {
	
	private JLabel entranceOneLabel, entranceOneData;
	private JLabel entranceTwoLabel, entranceTwoData;
	private JLabel paymentLabel, paymentData;
	private JLabel exitLabel, exitData;

	/**
	 * The constructor for the class CarQueueView.
	 * 
	 * @param simulator the model
	 */
	public CarQueueView(SimulatorModel simulator) {
		super(simulator);
		
		setSize(200, 200);
		setLayout(new GridLayout(4, 2));
		
		entranceOneLabel = new JLabel("Entrance one:");
		entranceOneData = new JLabel("0");
		
		entranceTwoLabel = new JLabel("Entrance two:");
		entranceTwoData = new JLabel("0");
		
		paymentLabel = new JLabel("Payment:");
		paymentData = new JLabel("0");
		
		exitLabel = new JLabel("Exit:");
		exitData = new JLabel("0");
		
		add(entranceOneLabel);
		add(entranceOneData);
		
		add(entranceTwoLabel);
		add(entranceTwoData);
		
		add(paymentLabel);
		add(paymentData);
		
		add(exitLabel);
		add(exitData);
	}
	
	/**
	 * This method retrieves the information needed for the queues and keeps 
	 * this data up-to-date. 
	 * 
	 * @param g the specified Graphics context
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/**
		setQueueColor(g, getModel().getRegCarQueue());
		setQueueColor(g, getModel().getSubResCarQueue());
		setQueueColor(g, getModel().getPaymentCarQueue());
		setQueueColor(g, getModel().getExitCarQueue());
		*/
		entranceOneData.setText(String.valueOf(getModel().getEntranceOneQueue().carsInQueue()));
		entranceTwoData.setText(String.valueOf(getModel().getEntranceTwoQueue().carsInQueue()));
		paymentData.setText(String.valueOf(getModel().getPaymentQueue().carsInQueue()));
		exitData.setText(String.valueOf(getModel().getExitQueue().carsInQueue()));
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
