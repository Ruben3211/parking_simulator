package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SimulatorModel;

/**
 * This class is responsible for creating and assembling everything to-do with
 * the data controller. This controller lets the user manipulate data, to change
 * the simulators behavior. This way the simulator can be used under different
 * circumstances and their results can be compared.
 * 
 * @author Joey Kroes
 * @version 03-02-2018
 */

@SuppressWarnings("serial")
public class DataController extends AbstractController implements ActionListener {
	
	private JLabel regularFeeLabel, subscriptionFeeLabel, reservationFeeLabel;
	private JLabel entranceSpeedLabel, paymentSpeedLabel, exitSpeedLabel;
	private JLabel regularWeekDayLabel, regularWeekendLabel, regularEventLabel;
	private JLabel subscriptionWeekDayLabel, subscriptionWeekendLabel, subscriptionEventLabel;
	private JLabel reservationWeekDayLabel, reservationWeekendLabel, reservationEventLabel;
	private JLabel maxSubscriptionsLabel, maxReservationsLabel, maxEntranceQueueLabel;
	
	private JTextField regularFeeData, subscriptionFeeData, reservationFeeData;
	private JTextField entranceSpeedData, paymentSpeedData, exitSpeedData;
	private JTextField regularWeekDayData, regularWeekendData, regularEventData;
	private JTextField subscriptionWeekDayData, subscriptionWeekendData, subscriptionEventData;
	private JTextField reservationWeekDayData, reservationWeekendData, reservationEventData;
	private JTextField maxSubscriptionsData, maxReservationsData, maxEntranceQueueData;
	
	private JButton sendDataButton, resetDataButton;
	
	private Box boxOne, boxTwo, boxThree, boxFour, boxFive, boxSix;
	
	private JPanel panelOne, panelTwo, panelThree, panelFour, panelFive, panelSix, panelSeven;
	
	private List<JTextField> textFieldList;
	
	/**
	 * The constructor for the DataController class.
	 * 
	 * @param simulator the model
	 */
	public DataController(SimulatorModel simulator) {
		super(simulator);

		// Creating all the labels and adding text.
		regularFeeLabel = new JLabel("Regular fee");
		subscriptionFeeLabel = new JLabel("Subscription fee");
		reservationFeeLabel = new JLabel("Reservation fee");
		entranceSpeedLabel = new JLabel("Entrance speed");
		paymentSpeedLabel = new JLabel("Payment speed");
		exitSpeedLabel = new JLabel("Exit speed");
		regularWeekDayLabel = new JLabel("Regular (weekday)");
		regularWeekendLabel = new JLabel("Regular (weekend)");
		regularEventLabel = new JLabel("Regular (event)");
		subscriptionWeekDayLabel = new JLabel("Subscription (weekday)");
		subscriptionWeekendLabel = new JLabel("Subscription (weekend)");
		subscriptionEventLabel = new JLabel("Subscription (event)");
		reservationWeekDayLabel = new JLabel("Reservation (weekday)");
		reservationWeekendLabel = new JLabel("Reservation (weekend)");
		reservationEventLabel = new JLabel("Reservation (event)");
		maxSubscriptionsLabel = new JLabel("Max subscriptions");
		maxReservationsLabel = new JLabel("Max reservations");
		maxEntranceQueueLabel = new JLabel("Max entrance queue");
		
		// Creating all the text fields and retrieving their display data.
		regularFeeData = new JTextField(Integer.toString(simulator.getRegularFee()));
		subscriptionFeeData = new JTextField(Integer.toString(simulator.getSubscriptionFee()));
		reservationFeeData = new JTextField(Integer.toString(simulator.getReservationFee()));
		entranceSpeedData = new JTextField(Integer.toString(simulator.getEntranceSpeed()));
		paymentSpeedData = new JTextField(Integer.toString(simulator.getPaymentSpeed()));
		exitSpeedData = new JTextField(Integer.toString(simulator.getExitSpeed()));
		regularWeekDayData = new JTextField(Integer.toString(simulator.getWeekDayRegularArrivals()));
		regularWeekendData = new JTextField(Integer.toString(simulator.getWeekendRegularArrivals()));
		regularEventData = new JTextField(Integer.toString(simulator.getEventRegularArrivals()));
		subscriptionWeekDayData = new JTextField(Integer.toString(simulator.getWeekDaySubscriptionArrivals()));
		subscriptionWeekendData = new JTextField(Integer.toString(simulator.getWeekendSubscriptionArrivals()));
		subscriptionEventData = new JTextField(Integer.toString(simulator.getEventSubscriptionArrivals()));
		reservationWeekDayData = new JTextField(Integer.toString(simulator.getWeekDayReservationArrivals()));
		reservationWeekendData = new JTextField(Integer.toString(simulator.getWeekendReservationArrivals()));
		reservationEventData = new JTextField(Integer.toString(simulator.getEventReservationArrivals()));
		maxSubscriptionsData = new JTextField(Integer.toString(simulator.getMaxSubscriptions()));
		maxReservationsData = new JTextField(Integer.toString(simulator.getMaxReservations()));
		maxEntranceQueueData = new JTextField(Integer.toString(simulator.getMaxEntranceQueue()));
		
		// Setting a name to all the text fields.
		regularFeeData.setName("regularFeeData");
		subscriptionFeeData.setName("subscriptionFeeData");
		reservationFeeData.setName("reservationFeeData");
		entranceSpeedData.setName("entranceSpeedData");
		paymentSpeedData.setName("paymentSpeedData");
		exitSpeedData.setName("exitSpeedData");
		regularWeekDayData.setName("regularWeekDayData");
		regularWeekendData.setName("regularWeekendData");
		regularEventData.setName("regularEventData");
		subscriptionWeekDayData.setName("subscriptionWeekDayData");
		subscriptionWeekendData.setName("subscriptionWeekendData");
		subscriptionEventData.setName("subscriptionEventData");
		reservationWeekDayData.setName("reservationWeekDayData");
		reservationWeekendData.setName("reservationWeekendData");
		reservationEventData.setName("reservationEventData");
		maxSubscriptionsData.setName("maxSubscriptionsData");
		maxReservationsData.setName("maxReservationsData");
		maxEntranceQueueData.setName("maxEntranceQueueData");

		// Creating the buttons and adding ActionListeners.
		sendDataButton = new JButton("Send");
		sendDataButton.addActionListener(this);
		resetDataButton = new JButton("Reset Values");
		resetDataButton.addActionListener(this);
		
		// Creating the boxes and setting their layout.
		boxOne = Box.createVerticalBox();
		boxTwo = Box.createVerticalBox();
		boxThree = Box.createVerticalBox();
		boxFour = Box.createVerticalBox();
		boxFive = Box.createVerticalBox();
		boxSix = Box.createVerticalBox();
		
		// Added all the labels and text fields to one of the boxes.
		boxOne.add(regularFeeLabel);
		boxOne.add(regularFeeData);
		boxOne.add(subscriptionFeeLabel);
		boxOne.add(subscriptionFeeData);
		boxOne.add(reservationFeeLabel);
		boxOne.add(reservationFeeData);
		boxTwo.add(entranceSpeedLabel);
		boxTwo.add(entranceSpeedData);
		boxTwo.add(paymentSpeedLabel);
		boxTwo.add(paymentSpeedData);
		boxTwo.add(exitSpeedLabel);
		boxTwo.add(exitSpeedData);
		boxThree.add(regularWeekDayLabel);
		boxThree.add(regularWeekDayData);
		boxThree.add(regularWeekendLabel);
		boxThree.add(regularWeekendData);
		boxThree.add(regularEventLabel);
		boxThree.add(regularEventData);
		boxFour.add(subscriptionWeekDayLabel);
		boxFour.add(subscriptionWeekDayData);
		boxFour.add(subscriptionWeekendLabel);
		boxFour.add(subscriptionWeekendData);
		boxFour.add(subscriptionEventLabel);
		boxFour.add(subscriptionEventData);
		boxFive.add(reservationWeekDayLabel);
		boxFive.add(reservationWeekDayData);
		boxFive.add(reservationWeekendLabel);
		boxFive.add(reservationWeekendData);
		boxFive.add(reservationEventLabel);
		boxFive.add(reservationEventData);
		boxSix.add(maxSubscriptionsLabel);
		boxSix.add(maxSubscriptionsData);
		boxSix.add(maxReservationsLabel);
		boxSix.add(maxReservationsData);
		boxSix.add(maxEntranceQueueLabel);
		boxSix.add(maxEntranceQueueData);
		
		// Creating panels, include a box in them and add them to the content pane.
		panelOne = new JPanel();
		panelOne.add(boxOne);
		add(panelOne);
		panelTwo = new JPanel();
		panelTwo.add(boxTwo);
		add(panelTwo);
		panelThree = new JPanel();
		panelThree.add(boxThree);
		add(panelThree);
		panelFour = new JPanel();
		panelFour.add(boxFour);
		add(panelFour);
		panelFive = new JPanel();
		panelFive.add(boxFive);
		add(panelFive);
		panelSix = new JPanel();
		panelSix.add(boxSix);
		add(panelSix);
		panelSeven = new JPanel();
		panelSeven.add(sendDataButton);
		panelSeven.add(resetDataButton);
		add(panelSeven);

		// Adding all text fields to an array.
		textFieldList = new ArrayList<>(Arrays.asList(regularFeeData, subscriptionFeeData, reservationFeeData,
													  entranceSpeedData, paymentSpeedData, exitSpeedData,
													  regularWeekDayData, regularWeekendData, regularEventData,
													  subscriptionWeekDayData, subscriptionWeekendData, subscriptionEventData,
													  reservationWeekDayData, reservationWeekendData, reservationEventData,
													  maxSubscriptionsData, maxReservationsData, maxEntranceQueueData));
	}

	/**
	 * This method is responsible for checking which button is pushed. If a
	 * button is pushed, the corresponding actions are taken.
	 * 
	 * @param e the actionEvent given to the controller
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendDataButton) {
			simulator.playSound();
			for (int i = 0; i < textFieldList.size(); i++) {
				if(textFieldList.get(i).getText() != "") {
					if (isInteger(textFieldList.get(i).getText()) == true) {
						simulator.setIntFromDataController(textFieldList.get(i).getName(),Integer.parseInt(textFieldList.get(i).getText()));
					}
				}
			}
			simulator.reset();
		}
		if (e.getSource() == resetDataButton) {
			simulator.playSound();
			simulator.initialization();
			regularFeeData.setText(Integer.toString(simulator.getRegularFee()));
			subscriptionFeeData.setText(Integer.toString(simulator.getSubscriptionFee()));
			reservationFeeData.setText(Integer.toString(simulator.getReservationFee()));
			entranceSpeedData.setText(Integer.toString(simulator.getEntranceSpeed()));
			paymentSpeedData.setText(Integer.toString(simulator.getPaymentSpeed()));
			exitSpeedData.setText(Integer.toString(simulator.getExitSpeed()));
			regularWeekDayData.setText(Integer.toString(simulator.getWeekDayRegularArrivals()));
			regularWeekendData.setText(Integer.toString(simulator.getWeekendRegularArrivals()));
			regularEventData.setText(Integer.toString(simulator.getEventRegularArrivals()));
			subscriptionWeekDayData.setText(Integer.toString(simulator.getWeekDaySubscriptionArrivals()));
			subscriptionWeekendData.setText(Integer.toString(simulator.getWeekendSubscriptionArrivals()));
			subscriptionEventData.setText(Integer.toString(simulator.getEventSubscriptionArrivals()));
			reservationWeekDayData.setText(Integer.toString(simulator.getWeekDayReservationArrivals()));
			reservationWeekendData.setText(Integer.toString(simulator.getWeekendReservationArrivals()));
			reservationEventData.setText(Integer.toString(simulator.getEventReservationArrivals()));
			maxSubscriptionsData.setText(Integer.toString(simulator.getMaxSubscriptions()));
			maxReservationsData.setText(Integer.toString(simulator.getMaxReservations()));
			maxEntranceQueueData.setText(Integer.toString(simulator.getMaxEntranceQueue()));
		}
	}

	/**
	 * This method converts a String to an Integer. If the String can be
	 * converted to an integer, the boolean true is returned. If the String can't
	 * be converted to an integer, the boolean false is returned.
	 * 
	 * @param s a String that is check for integers
	 * @return true if it is an integer, false if it is not an integer
	 */
	private boolean isInteger(String s) {
		try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}	
}
