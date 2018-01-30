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
 * @version 30-01-2018
 */

@SuppressWarnings("serial")
public class DataController extends AbstractController implements ActionListener {
	
	private JLabel maxSubscribersLabel, maxReservationsLabel;
	private JLabel regularFeeLabel, subscriberFeeLabel, reservationFeeLabel;
	private JLabel enterSpeedLabel, paymentSpeedLabel, exitSpeedLabel;
	private JLabel regularWeekDayLabel, regularWeekendLabel, regularEventLabel;
	private JLabel subscriptionWeekDayLabel, subscriptionWeekendLabel, subscriptionEventLabel;
	private JLabel reservationWeekDayLabel, reservationWeekendLabel, reservationEventLabel;
	
	private JTextField maxSubscribersData, maxReservationsData;
	private JTextField regularFeeData, subscriberFeeData, reservationFeeData;
	private JTextField enterSpeedData, paymentSpeedData, exitSpeedData;
	private JTextField regularWeekDayData, regularWeekendData, regularEventData;
	private JTextField subscriptionWeekDayData, subscriptionWeekendData, subscriptionEventData;
	private JTextField reservationWeekDayData, reservationWeekendData, reservationEventData;
	
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
		maxSubscribersLabel = new JLabel("Max subscriptions");
		maxReservationsLabel = new JLabel("Max reservations");
		regularFeeLabel = new JLabel("Regular fee");
		subscriberFeeLabel = new JLabel("Subscriber fee");
		reservationFeeLabel = new JLabel("Reservation fee");
		enterSpeedLabel = new JLabel("Enter speed");
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
		
		// Creating all the text fields and retrieving their display data.
		maxSubscribersData = new JTextField(Integer.toString(simulator.getMaxSub()));
		maxReservationsData = new JTextField(Integer.toString(simulator.getMaxRes()));
		regularFeeData = new JTextField(Integer.toString(simulator.getRegPayAmount()));
		subscriberFeeData = new JTextField(Integer.toString(simulator.getSubPayAmount()));
		reservationFeeData = new JTextField(Integer.toString(simulator.getResPayAmount()));
		enterSpeedData = new JTextField(Integer.toString(simulator.getEnterSpeed()));
		paymentSpeedData = new JTextField(Integer.toString(simulator.getPaymentSpeed()));
		exitSpeedData = new JTextField(Integer.toString(simulator.getExitSpeed()));
		regularWeekDayData = new JTextField(Integer.toString(simulator.getWeekDayReg()));
		regularWeekendData = new JTextField(Integer.toString(simulator.getWeekendReg()));
		regularEventData = new JTextField(Integer.toString(simulator.getEventReg()));
		subscriptionWeekDayData = new JTextField(Integer.toString(simulator.getWeekDaySub()));
		subscriptionWeekendData = new JTextField(Integer.toString(simulator.getWeekendSub()));
		subscriptionEventData = new JTextField(Integer.toString(simulator.getEventSub()));
		reservationWeekDayData = new JTextField(Integer.toString(simulator.getWeekDayRes()));
		reservationWeekendData = new JTextField(Integer.toString(simulator.getWeekendRes()));
		reservationEventData = new JTextField(Integer.toString(simulator.getEventRes()));
		
		// Setting a name to all the text fields.
		maxSubscribersData.setName("maxSubscribersData");
		maxReservationsData.setName("maxReservationsData");
		regularFeeData.setName("regularFeeData");
		subscriberFeeData.setName("subscriberFeeData");
		reservationFeeData.setName("reservationFeeData");
		enterSpeedData.setName("enterSpeedData");
		paymentSpeedData.setName("paymentSpeedData");
		exitSpeedData.setName("exitSpeedData");
		regularWeekDayData.setName("regularWeekDayData");
		regularWeekendData.setName("regularWeekendData");
		regularEventData.setName("regularEventData");
		subscriptionWeekDayData.setName("subscriberWeekDayData");
		subscriptionWeekendData.setName("subscriberWeekendData");
		subscriptionEventData.setName("subscriberEventData");
		reservationWeekDayData.setName("reservationWeekDayData");
		reservationWeekendData.setName("reservationWeekendData");
		reservationEventData.setName("reservationEventData");

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
		boxOne.add(subscriberFeeLabel);
		boxOne.add(subscriberFeeData);
		boxOne.add(reservationFeeLabel);
		boxOne.add(reservationFeeData);
		boxTwo.add(enterSpeedLabel);
		boxTwo.add(enterSpeedData);
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
		boxSix.add(maxSubscribersLabel);
		boxSix.add(maxSubscribersData);
		boxSix.add(maxReservationsLabel);
		boxSix.add(maxReservationsData);
		
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
		textFieldList = new ArrayList<>(Arrays.asList(maxSubscribersData, maxReservationsData,
													  regularFeeData, subscriberFeeData, reservationFeeData,
													  enterSpeedData, paymentSpeedData, exitSpeedData,
													  regularWeekDayData, regularWeekendData, regularEventData,
													  subscriptionWeekDayData, subscriptionWeekendData, subscriptionEventData,
													  reservationWeekDayData, reservationWeekendData, reservationEventData));
	}

	/**
	 * This method is responsible for checking which button is pushed. If a
	 * button is pushed, the corresponding actions are taken.
	 * 
	 * @param e the actionEvent given to the controller
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendDataButton) {
			for (int i = 0; i < textFieldList.size(); i++) {
				if(textFieldList.get(i).getText() != "") {
					if (isInteger(textFieldList.get(i).getText()) == true) {
						simulator.setIntFromDataController(textFieldList.get(i).getName(),Integer.parseInt(textFieldList.get(i).getText()));
					}
				}
			}
		}
		if (e.getSource() == resetDataButton) {
			simulator.resetAllData();
			maxSubscribersData.setText(Integer.toString(simulator.getMaxSub()));
			maxReservationsData.setText(Integer.toString(simulator.getMaxRes()));
			regularFeeData.setText(Integer.toString(simulator.getRegPayAmount()));
			subscriberFeeData.setText(Integer.toString(simulator.getSubPayAmount()));
			reservationFeeData.setText(Integer.toString(simulator.getResPayAmount()));
			enterSpeedData.setText(Integer.toString(simulator.getEnterSpeed()));
			paymentSpeedData.setText(Integer.toString(simulator.getPaymentSpeed()));
			exitSpeedData.setText(Integer.toString(simulator.getExitSpeed()));
			regularWeekDayData.setText(Integer.toString(simulator.getWeekDayReg()));
			regularWeekendData.setText(Integer.toString(simulator.getWeekendReg()));
			regularEventData.setText(Integer.toString(simulator.getEventReg()));
			subscriptionWeekDayData.setText(Integer.toString(simulator.getWeekDaySub()));
			subscriptionWeekendData.setText(Integer.toString(simulator.getWeekendSub()));
			subscriptionEventData.setText(Integer.toString(simulator.getEventSub()));
			reservationWeekDayData.setText(Integer.toString(simulator.getWeekDayRes()));
			reservationWeekendData.setText(Integer.toString(simulator.getWeekendRes()));
			reservationEventData.setText(Integer.toString(simulator.getEventRes()));
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
