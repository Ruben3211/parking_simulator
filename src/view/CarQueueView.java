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
		
		setLayout(new GridLayout(11, 3));
		
		queueLabel = new JLabel("<html><b>Queues</b></html>");
		blankOne = new JLabel("");
		blankTwo = new JLabel("");
		
		entranceOneLabel = new JLabel("Entrance one:");
		entranceOneData = new JLabel("€ 0");
		blankThree = new JLabel("");
		
		entranceTwoLabel = new JLabel("Entrance two:");
		entranceTwoData = new JLabel("0");
		blankFour = new JLabel("");
		
		paymentLabel = new JLabel("Payment:");
		paymentData = new JLabel("0");
		blankFive = new JLabel("");
		
		exitLabel = new JLabel("Exit:");
		exitData = new JLabel("0");
		blankSix = new JLabel("");
		
		blankSeven = new JLabel("");
		blankEight = new JLabel("");
		blankNine = new JLabel("");
		
		missedCarLabel = new JLabel("<html><b>Missed Cars</b></html>");
		blankTen = new JLabel("");
		blankEleven = new JLabel("");
			
		regularMissedLabel = new JLabel("Regular:");
		regularMissedData = new JLabel("0");
		regularMissedMoneyData = new JLabel("0");
		
		reservationMissedLabel = new JLabel("Reservation:");
		reservationMissedData = new JLabel("0");
		reservationMissedMoneyData = new JLabel("0");
		
		totalMissedLabel = new JLabel("Total:");
		totalMissedData = new JLabel("0");
		totalMissedMoneyData = new JLabel("0");
		
		add(queueLabel);
		add(blankOne);
		add(blankTwo);
		
		add(entranceOneLabel);
		add(entranceOneData);
		add(blankThree);
		
		add(entranceTwoLabel);
		add(entranceTwoData);
		add(blankFour);
		
		add(paymentLabel);
		add(paymentData);
		add(blankFive);
		
		add(exitLabel);
		add(exitData);
		add(blankSix);
		
		add(blankSeven);
		add(blankEight);
		add(blankNine);
		
		add(missedCarLabel);
		add(blankTen);
		add(blankEleven);
			
		add(regularMissedLabel);
		add(regularMissedData);
		add(regularMissedMoneyData);
		
		add(reservationMissedLabel);
		add(reservationMissedData);
		add(reservationMissedMoneyData);
		
		add(totalMissedLabel);
		add(totalMissedData);
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
		regularMissedMoneyData.setText(String.valueOf(getModel().getMissedRegularIncome()));
		reservationMissedMoneyData.setText(String.valueOf(getModel().getMissedReservationIncome()));
		totalMissedMoneyData.setText(String.valueOf(getModel().getMissedTotalIncome()));
	}
}
