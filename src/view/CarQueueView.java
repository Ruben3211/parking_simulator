package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;

import model.SimulatorModel;

/**
 * This class is used for keeping track of all the queues. It will display the
 * amount of cars in each queue, the amount of missed cars per type and in total
 * and the amount of income this has cost the garage.
 * 
 * @author Ruben Bonga & Rick Zwaneveld
 * @version 27-01-2018
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
	}
}
