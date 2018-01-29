package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimulatorModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * This class imports a few boxlayouts containing textfields and labels.
 * These can be used to alter data within the simulation for testing purposes.
 */

@SuppressWarnings("serial")
public class DataController extends AbstractController implements ActionListener {
	
	private List<JTextField> textFieldList;
	
	Box boxLayout = Box.createVerticalBox();
	Box boxLayout2 = Box.createVerticalBox();
	Box boxLayout3 = Box.createVerticalBox();
	Box boxLayout4 = Box.createVerticalBox();
	
	JPanel jp = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	JPanel jp4 = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	private JButton sendDataButton;
	private JButton resetDataButton;
	
	
	/**
	 * Jlabels
	 */
	public JLabel reservationsText = new JLabel("Max Reservations");
	public JLabel subscribersText = new JLabel("Max Subscribers");
	public JLabel normalPriceText = new JLabel("Normal price");
	public JLabel reservationPriceText = new JLabel("Reservation price");
	public JLabel subPriceText = new JLabel("Subscriber price");
	public JLabel enterSpeedText = new JLabel("Enter speed");
	public JLabel paymentSpeedText = new JLabel("Payment speed");
	public JLabel exitSpeedText = new JLabel("Exit speed");
	public JLabel workDayText = new JLabel("Normal work day");
	public JLabel weekendText = new JLabel("Normal weekend day");
	public JLabel specialText = new JLabel("Normal special day");
	public JLabel subWorkDayText = new JLabel("Subscriber work day");
	public JLabel subWeekendText = new JLabel("Subscriber weekend day");
	public JLabel subSpecialText = new JLabel("Subscriber special day");
	public JLabel reservationWorkDayText = new JLabel(" Reservation work day");
	public JLabel reservationWeekendText = new JLabel("Reservation weekend day");
	public JLabel reservationSpecialText = new JLabel("Reservation special day");

	/**
	 * JTextFields		
	 */
	public JTextField reservations = new JTextField(Integer.toString(simulator.getMaxRes()), 3);
	public JTextField subscribers = new JTextField(Integer.toString(simulator.getMaxSub()),3);
	public JTextField normalPrice= new JTextField(Integer.toString(simulator.getRegPayAmount()),3);
	public JTextField reservationPrice = new JTextField(Integer.toString(simulator.getResPayAmount()),3);
	public JTextField subscriberPrice = new JTextField(Integer.toString(simulator.getSubPayAmount()),3);
	public JTextField enterSpeed = new JTextField(Integer.toString(simulator.getEnterSpeed()),3);
	public JTextField paymentSpeed = new JTextField(Integer.toString(simulator.getPaymentSpeed()),3);
	public JTextField exitSpeed = new JTextField(Integer.toString(simulator.getExitSpeed()),3);
	public JTextField workDay = new JTextField(Integer.toString(simulator.getWeekDayReg()),3);
	public JTextField weekend = new JTextField(Integer.toString(simulator.getWeekendReg()),3);
	public JTextField special = new JTextField(Integer.toString(simulator.getEventReg()),3);
	public JTextField subWorkDay = new JTextField(Integer.toString(simulator.getWeekDaySub()),3);
	public JTextField subWeekend = new JTextField(Integer.toString(simulator.getWeekendSub()),3);
	public JTextField subSpecial = new JTextField(Integer.toString(simulator.getEventSub()),3);
	public JTextField reservationWorkDay = new JTextField(Integer.toString(simulator.getWeekDayRes()),3);
	public JTextField reservationWeekend = new JTextField(Integer.toString(simulator.getWeekendRes()),3);
	public JTextField reservationSpecial = new JTextField(Integer.toString(simulator.getEventRes()),3);
	
	public DataController(SimulatorModel simulator) {
		super(simulator);
		setSize(400, 800);
		setVisible(true);

		jp.add(boxLayout);
		jp2.add(boxLayout2);
		jp3.add(boxLayout3);
		jp4.add(boxLayout4);
		
		/**
		 * Adds all the textfields to this arraylist
		 */
		textFieldList = new ArrayList<>(Arrays.asList(reservations,subscribers,normalPrice,reservationPrice,subscriberPrice,enterSpeed,paymentSpeed,exitSpeed,workDay,weekend,special,subWorkDay,subWeekend,subSpecial,reservationWorkDay,reservationWeekend,reservationSpecial));
		
		sendDataButton = new JButton("Send");
		sendDataButton.addActionListener(this);
		resetDataButton = new JButton("Reset values");
		resetDataButton.addActionListener(this);
		
		buttonPanel.add(sendDataButton);
		buttonPanel.add(resetDataButton);
		
		/**
		 * Gives all the fields a name so they can be retrieved by getName in the loop for the senDataButton
		 */
		reservations.setName("reservations");
		subscribers.setName("subscribers");
		normalPrice.setName("normalPrice");
		reservationPrice.setName("reservationPrice");
		subscriberPrice.setName("subscriberPrice");
		enterSpeed.setName("enterSpeed");
		paymentSpeed.setName("paymentSpeed");
		exitSpeed.setName("exitSpeed");
		workDay.setName("workDay");
		weekend.setName("weekend");
		special.setName("special");
		subWorkDay.setName("subWorkDay");
		subWeekend.setName("subWeekend");
		subSpecial.setName("subSpecial");
		reservationWorkDay.setName("reservationWorkDay");
		reservationWeekend.setName("reservationWeekend");
		reservationSpecial.setName("reservationSpecial");
		
		/**
		 * Divides all the labels and textfields across all the 4 box layouts
		 */
		boxLayout.add(reservationsText);	
		boxLayout.add(reservations);
		boxLayout.add(subscribersText);
		boxLayout.add(subscribers);
		boxLayout.add(normalPriceText);
		boxLayout.add(normalPrice);
		boxLayout.add(reservationPriceText);
		boxLayout.add(reservationPrice);
		boxLayout.add(subPriceText);
		boxLayout.add(subscriberPrice);
		boxLayout2.add(enterSpeedText);
		boxLayout2.add(enterSpeed);
		boxLayout2.add(paymentSpeedText);
		boxLayout2.add(paymentSpeed);
		boxLayout2.add(exitSpeedText);
		boxLayout2.add(exitSpeed);
		boxLayout2.add(workDayText);
		boxLayout2.add(workDay);
		boxLayout2.add(weekendText);
		boxLayout2.add(weekend);
		boxLayout3.add(specialText);
		boxLayout3.add(special);
		boxLayout3.add(subWorkDayText);
		boxLayout3.add(subWorkDay);
		boxLayout3.add(subWeekendText);
		boxLayout3.add(subWeekend);
		boxLayout3.add(subSpecialText);
		boxLayout3.add(subSpecial);
		boxLayout3.add(reservationWorkDayText);
		boxLayout3.add(reservationWorkDay);
		boxLayout4.add(reservationWeekendText);
		boxLayout4.add(reservationWeekend);
		boxLayout4.add(reservationSpecialText);
		boxLayout4.add(reservationSpecial);
		
		
		/**
		 * Adds all the panels to the controller so they are added to the view
		 */
		add(jp);
		add(jp2);
		add(jp3);
		add(jp4);
		add(buttonPanel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendDataButton) {
			for (int i = 0; i < textFieldList.size(); i++) { // Loops through whole arraylist
				if(textFieldList.get(i).getText() != "") { //Checks if field is not empty 
					if (isInteger(textFieldList.get(i).getText()) == true) { //Checks if the text inside the field is an integer				
					simulator.setIntFromDataController(textFieldList.get(i).getName(),Integer.parseInt(textFieldList.get(i).getText())); //Calls the setdata method from model and passes the object name and value
					} else if (isInteger(textFieldList.get(i).getText()) == false) {
						//Do nothing
					}
				}
			}
		}
		
		/**
		 * Calls the resetAllData method from simulatorModel and then loads the variables again.
		 */
		if (e.getSource() == resetDataButton) {
			simulator.resetAllData();
			reservations.setText(Integer.toString(simulator.getMaxRes()));
			subscribers.setText(Integer.toString(simulator.getMaxSub()));
			normalPrice.setText(Integer.toString(simulator.getRegPayAmount()));
			reservationPrice.setText(Integer.toString(simulator.getResPayAmount()));
			subscriberPrice.setText(Integer.toString(simulator.getSubPayAmount()));
			enterSpeed.setText(Integer.toString(simulator.getEnterSpeed()));
			paymentSpeed.setText(Integer.toString(simulator.getPaymentSpeed()));
			exitSpeed.setText(Integer.toString(simulator.getExitSpeed()));
			workDay.setText(Integer.toString(simulator.getWeekDayReg()));
			weekend.setText(Integer.toString(simulator.getWeekendReg()));
			special.setText(Integer.toString(simulator.getEventReg()));
			subWorkDay.setText(Integer.toString(simulator.getWeekDaySub()));
			subWeekend.setText(Integer.toString(simulator.getWeekendSub()));
			subSpecial.setText(Integer.toString(simulator.getEventSub()));
			reservationWorkDay.setText(Integer.toString(simulator.getWeekDayRes()));
			reservationWeekend.setText(Integer.toString(simulator.getWeekendRes()));
			reservationSpecial.setText(Integer.toString(simulator.getEventRes()));
		}
	}
	/**
	 * Checks if the given text in the textfield is an int. If not this method returns false and the data from the textfield wont be send
	 * @param s String given when calling this method this string is taken from the textfield
	 * @returns true or false 
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // if exception isn't thrown, then it is an integer
	    return true;
	}
}
