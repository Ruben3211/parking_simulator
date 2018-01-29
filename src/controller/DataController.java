package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimulatorModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import view.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.scene.control.cell.TextFieldListCell;
import model.SimulatorModel;

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
	
	public JLabel multiplierText = new JLabel("Multiplier (%)");
	public JLabel reservationsText = new JLabel("Max Reservations");
	public JLabel subscribersText = new JLabel("Max Subscribers");
	public JLabel retardText = new JLabel("Disabled People");
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

			
	public JTextField multiplier = new JTextField(3);
	public JTextField reservations = new JTextField(Integer.toString(simulator.getMaxRes()), 3);
	public JTextField subscribers = new JTextField(Integer.toString(simulator.getMaxSub()),3);
	public JTextField disabledPeople = new JTextField(3);
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
		
		textFieldList = new ArrayList<>(Arrays.asList(multiplier,reservations,subscribers,disabledPeople,normalPrice,reservationPrice,subscriberPrice,enterSpeed,paymentSpeed,exitSpeed,workDay,weekend,special,subWorkDay,subWeekend,subSpecial,reservationWorkDay,reservationWeekend,reservationSpecial));
		
		sendDataButton = new JButton("Send");
		sendDataButton.addActionListener(this);
		resetDataButton = new JButton("Reset values");
		resetDataButton.addActionListener(this);
		
		buttonPanel.add(sendDataButton);
		buttonPanel.add(resetDataButton);
		
		
		multiplier.setName("multiplier");
		reservations.setName("reservations");
		subscribers.setName("subscribers");
		//retards
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
		System.out.println("multiplier's name is: " + reservations.getName());
		/*
		 * Divides all the labels and textfields across all the 4 box layouts
		 */
		boxLayout.add(multiplierText);	
		boxLayout.add(multiplier);
		boxLayout.add(reservationsText);	
		boxLayout.add(reservations);
		boxLayout.add(subscribersText);
		boxLayout.add(subscribers);
		boxLayout.add(retardText);
		boxLayout.add(disabledPeople);
		boxLayout.add(normalPriceText);
		boxLayout.add(normalPrice);
		boxLayout2.add(reservationPriceText);
		boxLayout2.add(reservationPrice);
		boxLayout2.add(subPriceText);
		boxLayout2.add(subscriberPrice);
		boxLayout2.add(enterSpeedText);
		boxLayout2.add(enterSpeed);
		boxLayout2.add(paymentSpeedText);
		boxLayout2.add(paymentSpeed);
		boxLayout2.add(exitSpeedText);
		boxLayout2.add(exitSpeed);
		boxLayout3.add(workDayText);
		boxLayout3.add(workDay);
		boxLayout3.add(weekendText);
		boxLayout3.add(weekend);
		boxLayout3.add(specialText);
		boxLayout3.add(special);
		boxLayout3.add(subWorkDayText);
		boxLayout3.add(subWorkDay);
		boxLayout3.add(subWeekendText);
		boxLayout3.add(subWeekend);
		boxLayout4.add(subSpecialText);
		boxLayout4.add(subSpecial);
		boxLayout4.add(reservationWorkDayText);
		boxLayout4.add(reservationWorkDay);
		boxLayout4.add(reservationWeekendText);
		boxLayout4.add(reservationWeekend);
		boxLayout4.add(reservationSpecialText);
		boxLayout4.add(reservationSpecial);
		
		
		/*
		 * Adds all the panels to the controller so they are added to the view
		 */
		add(jp);
		add(jp2);
		add(jp3);
		add(jp4);
		add(buttonPanel);
	}

	public void actionPerformed(ActionEvent e) {
		String objectName;
		if (e.getSource() == sendDataButton) {
			for (int i = 0; i < textFieldList.size(); i++) { // Loops through whole arraylist
				if(textFieldList.get(i).getText() != "") { //Checks if field is not empty 
					if (isInteger(textFieldList.get(i).getText()) == true) { //Checks if the text inside the field is an integer
						objectName = textFieldList.get(i).getName();
						//System.out.println("objectName is: " + this.getName());
					//	System.out.println(textFieldList.get(i).getName());
					//	System.out.println("size is " + textFieldList.size());
					simulator.setIntFromDataController(textFieldList.get(i).getName(),Integer.parseInt(textFieldList.get(i).getText())); //Calls the setdata method from model and passes the object name and value
					} else if (isInteger(textFieldList.get(i).getText()) == false) {
						//Do nothing
					}
				}
			}
		}
		
		if (e.getSource() == resetDataButton) {
			//multiplier;
			simulator.resetAllData();
			reservations.setText(Integer.toString(simulator.getMaxRes()));
			subscribers.setText(Integer.toString(simulator.getMaxSub()));
			//disabledPeople;
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
	
	public void resetData () {
		
	}
	
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
