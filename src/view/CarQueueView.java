package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;

import model.Car;
import model.CarQueue;
import model.SimulatorModel;

/**
 * This class is used for keeping track of all the queues. It will display the
 * amount of cars in each queue, the amount of missed cars per type and in total
 * the amount of income this has cost the garage.
 * 
 * @author Ruben Bonga & Rick Zwaneveld
 * @version 30-01-2018
 */

@SuppressWarnings("serial")
public class CarQueueView extends AbstractView  {
	
	private JLabel queueLabel, blankOne, blankTwo;
	private JLabel entranceOneLabel, entranceOneData, blankThree;
	private JLabel entranceTwoLabel, entranceTwoData, blankFour;
	private JLabel paymentLabel, paymentData, blankFive;
	private JLabel exitLabel, exitData, blankSix;
	
	private JLabel blankSeven, blankEight, blankNine;
	
	private JLabel missedCarLabel, blankTen, blankEleven;
	private JLabel regularMissedLabel, regularMissedData, regularMissedMoneyData;
	private JLabel reservationMissedLabel, reservationMissedData, reservationMissedMoneyData;
	private JLabel totalMissedLabel, totalMissedData, totalMissedMoneyData;

	/**
	 * The constructor for the class CarQueueView.
	 * 
	 * @param simulator the model
	 */
	public CarQueueView(SimulatorModel simulator) {
		super(simulator);
		
		setLayout(new GridLayout(10, 3));
		
		queueLabel = new JLabel("<html><b>Queues</b></html>");
		add(queueLabel);
		blankOne = new JLabel();
		add(blankOne);
		blankTwo = new JLabel();
		add(blankTwo);
		
		entranceOneLabel = new JLabel("Entrance one:");
		add(entranceOneLabel);
		entranceOneData = new JLabel();
		add(entranceOneData);
		blankThree = new JLabel();
		add(blankThree);
		
		entranceTwoLabel = new JLabel("Entrance two:");
		add(entranceTwoLabel);
		entranceTwoData = new JLabel();
		add(entranceTwoData);
		blankFour = new JLabel();
		add(blankFour);
		
		paymentLabel = new JLabel("Payment:");
		add(paymentLabel);
		paymentData = new JLabel();
		add(paymentData);
		blankFive = new JLabel();
		add(blankFive);
		
		exitLabel = new JLabel("Exit:");
		add(exitLabel);
		exitData = new JLabel();
		add(exitData);
		blankSix = new JLabel();
		add(blankSix);
		
		blankSeven = new JLabel();
		add(blankSeven);
		blankEight = new JLabel();
		add(blankEight);
		blankNine = new JLabel();
		add(blankNine);
		
		missedCarLabel = new JLabel("<html><b>Missed Cars</b></html>");
		add(missedCarLabel);
		blankTen = new JLabel();
		add(blankTen);
		blankEleven = new JLabel();
		add(blankEleven);
			
		regularMissedLabel = new JLabel("Regular:");
		add(regularMissedLabel);
		regularMissedData = new JLabel();
		add(regularMissedData);
		regularMissedMoneyData = new JLabel();
		add(regularMissedMoneyData);
		
		reservationMissedLabel = new JLabel("Reservation:");
		add(reservationMissedLabel);
		reservationMissedData = new JLabel();
		add(reservationMissedData);
		reservationMissedMoneyData = new JLabel();
		add(reservationMissedMoneyData);
		
		totalMissedLabel = new JLabel("Total:");
		add(totalMissedLabel);
		totalMissedData = new JLabel();
		add(totalMissedData);
		totalMissedMoneyData = new JLabel();
		add(totalMissedMoneyData);
	}
	
	/**
	 * This method retrieves the information needed for the queues and keeps 
	 * this data up-to-date. 
	 * 
	 * @param g the specified Graphics context
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		entranceOneData.setText(String.valueOf(getModel().getEntranceOneQueue().carsInQueue()));
		entranceTwoData.setText(String.valueOf(getModel().getEntranceTwoQueue().carsInQueue()));
		paymentData.setText(String.valueOf(getModel().getPaymentQueue().carsInQueue()));
		exitData.setText(String.valueOf(getModel().getExitQueue().carsInQueue()));
		regularMissedData.setText(String.valueOf(getModel().getTotalRegularMissed()));
		reservationMissedData.setText(String.valueOf(getModel().getTotalReservationMissed()));
		totalMissedData.setText(String.valueOf(getModel().getTotalMissed()));
		regularMissedMoneyData.setText("€" + String.valueOf(getModel().getMissedRegularIncome()));
		reservationMissedMoneyData.setText("€" + String.valueOf(getModel().getMissedReservationIncome()));
		totalMissedMoneyData.setText("€" + String.valueOf(getModel().getMissedTotalIncome()));
		
		// Uses the createQueueLine() method to show the car color in the queue.
		createQueueLine(g, getModel().getEntranceOneQueue());
		createQueueLine(g, getModel().getEntranceTwoQueue());
		createQueueLine(g, getModel().getPaymentQueue());
		createQueueLine(g, getModel().getExitQueue());
	}
	
	/**
	 * This method will get data from one of the four queues. Depending on the
	 * queue it will create x-coordinates for that queue display. It uses the
	 * peekCar() method in the CarQueue class to view which type of car is in
	 * the queue. It will eventually use the getColor() and the x-coordinate to
	 * draw a visual representation of the queues. 
	 * 
	 * @param g the specified Graphics context
	 * @param queueCar the queue used for comparison to get the proper data
	 */
	private void createQueueLine(Graphics g, CarQueue queueCar) {
		int xCoordinate = 0;
		
		if(queueCar == getModel().getEntranceOneQueue()) {
			xCoordinate = 21;
		}
		if(queueCar == getModel().getEntranceTwoQueue()) {
			xCoordinate = 37;
		}
		if(queueCar == getModel().getPaymentQueue()) {
			xCoordinate = 53;
		}
		if(queueCar == getModel().getExitQueue()) {
			xCoordinate = 69;
		}
		for(int i = 0; i < queueCar.carsInQueue(); i++) {
			if(queueCar.peekCar(i) != null) {
				Car car = queueCar.peekCar(i);
				g.setColor(car.getColor());
				g.fillRect(125 + (i * 13), xCoordinate, 10, 10);
			}
		}
	}
}
