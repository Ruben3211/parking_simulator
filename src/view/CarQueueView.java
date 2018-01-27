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
	
	private JLabel queueLabel, blankOne, blankTwo, blankThree;
	private JLabel entranceOneLabel, entranceOneData, blankFour, blankFive;
	private JLabel entranceTwoLabel, entranceTwoData, blankSix, blankSeven;
	private JLabel paymentLabel, paymentData, blankEight, blankNine;
	private JLabel exitLabel, exitData, blankTen, blankEleven;
	
	private JLabel blankTwelve, blankThirteen, blankFourteen, blankFithteen;
	
	private JLabel missedCarLabel, blankSixteen, blankSeventeen, blankEighteen;
	private JLabel regularMissedLabel, regularMissedData, regularMissedMoneyData, blankNineteen;
	private JLabel subscriptionMissedLabel, subscriptionMissedData, blankTwenty, blankTwentyOne;
	private JLabel reservationMissedLabel, reservationMissedData, reservationMissedMoneyData, blankTwentyTwo;
	private JLabel totalMissedLabel, totalMissedData, totalMissedMoneyData, blankTwentyThree;

	/**
	 * The constructor for the class CarQueueView.
	 * 
	 * @param simulator the model
	 */
	public CarQueueView(SimulatorModel simulator) {
		super(simulator);
		
		setLayout(new GridLayout(11, 4));
		
		queueLabel = new JLabel("<html><b>Queues</b></html>");
		blankOne = new JLabel("");
		blankTwo = new JLabel("");
		blankThree = new JLabel("");
		
		entranceOneLabel = new JLabel("Entrance one:");
		entranceOneData = new JLabel("€ 0");
		blankFour = new JLabel("");
		blankFive = new JLabel("");
		
		entranceTwoLabel = new JLabel("Entrance two:");
		entranceTwoData = new JLabel("0");
		blankSix = new JLabel("");
		blankSeven = new JLabel("");
		
		paymentLabel = new JLabel("Payment:");
		paymentData = new JLabel("0");
		blankEight = new JLabel("");
		blankNine = new JLabel("");
		
		exitLabel = new JLabel("Exit:");
		exitData = new JLabel("0");
		blankTen = new JLabel("");
		blankEleven = new JLabel("");
		
		blankTwelve = new JLabel("");
		blankThirteen = new JLabel("");
		blankFourteen = new JLabel("");
		blankFithteen = new JLabel("");
		
		missedCarLabel = new JLabel("<html><b>Missed Cars</b></html>");
		blankSixteen = new JLabel("");
		blankSeventeen = new JLabel("");
		blankEighteen = new JLabel("");
			
		regularMissedLabel = new JLabel("Regular:");
		regularMissedData = new JLabel("0");
		regularMissedMoneyData = new JLabel("0");
		blankNineteen = new JLabel("");
		
		subscriptionMissedLabel = new JLabel("Subscription:");
		subscriptionMissedData = new JLabel("0");
		blankTwenty = new JLabel("");
		blankTwentyOne = new JLabel("");
		
		reservationMissedLabel = new JLabel("Reservation:");
		reservationMissedData = new JLabel("0");
		reservationMissedMoneyData = new JLabel("0");
		blankTwentyTwo = new JLabel("");
		
		totalMissedLabel = new JLabel("Total:");
		totalMissedData = new JLabel("0");
		totalMissedMoneyData = new JLabel("0");
		blankTwentyThree = new JLabel("");
		
		add(queueLabel);
		add(blankOne);
		add(blankTwo);
		add(blankThree);
		
		add(entranceOneLabel);
		add(entranceOneData);
		add(blankFour);
		add(blankFive);
		
		add(entranceTwoLabel);
		add(entranceTwoData);
		add(blankSix);
		add(blankSeven);
		
		add(paymentLabel);
		add(paymentData);
		add(blankEight);
		add(blankNine);
		
		add(exitLabel);
		add(exitData);
		add(blankTen);
		add(blankEleven);
		
		add(blankTwelve);
		add(blankThirteen);
		add(blankFourteen);
		add(blankFithteen);
		
		add(missedCarLabel);
		add(blankSixteen);
		add(blankSeventeen);
		add(blankEighteen);
			
		add(regularMissedLabel);
		add(regularMissedData);
		add(regularMissedMoneyData);
		add(blankNineteen);
		
		add(subscriptionMissedLabel);
		add(subscriptionMissedData);
		add(blankTwenty);
		add(blankTwentyOne);
		
		add(reservationMissedLabel);
		add(reservationMissedData);
		add(reservationMissedMoneyData);
		add(blankTwentyTwo);
		
		add(totalMissedLabel);
		add(totalMissedData);
		add(totalMissedMoneyData);
		add(blankTwentyThree);
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
		subscriptionMissedData.setText(String.valueOf(getModel().getTotalSubscriptionMissed()));
		reservationMissedData.setText(String.valueOf(getModel().getTotalReservationMissed()));
		totalMissedData.setText(String.valueOf(getModel().getTotalMissed()));
		regularMissedMoneyData.setText(String.valueOf(getModel().getMissedRegularIncome()));
		reservationMissedMoneyData.setText(String.valueOf(getModel().getMissedReservationIncome()));
		totalMissedMoneyData.setText(String.valueOf(getModel().getMissedTotalIncome()));
	}
}
