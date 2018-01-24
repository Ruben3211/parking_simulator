package controller;


import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import javafx.scene.shape.Box;
import model.SimulatorModel;

@SuppressWarnings("serial")
public class DataController extends AbstractController {
	
	Box boxLayout = Box.createVerticalBox();
	
	JPanel jp = new JPanel();
	public JLabel multiplierText = new JLabel("Multiplier (%)");
	public JLabel reservationsText = new JLabel("Reservations");
	public JLabel subscribersText = new JLabel();
	public JLabel retardText = new JLabel();
	public JLabel normalPriceText = new JLabel();
	public JLabel reservationPriceText = new JLabel();
	public JLabel subPriceText = new JLabel();
	public JLabel enterSpeedText = new JLabel();
	public JLabel paymentSpeedText = new JLabel();
	public JLabel exitSpeedText = new JLabel();
	public JLabel workDayText = new JLabel();
	public JLabel weekendText = new JLabel();
	public JLabel specialText = new JLabel();
	public JLabel subWorkDayText = new JLabel();
	public JLabel subWeekendText = new JLabel();
	public JLabel subSpecialText = new JLabel();
	public JLabel reservationWorkDayText = new JLabel();
	public JLabel reservationWeekendText = new JLabel();
	public JLabel reservationSpecialText = new JLabel();

			
	public JTextField multiplier = new JTextField(5);
	public JTextField reservations = new JTextField(5);
	public JTextField subscribers = new JTextField(5);
	public JTextField retards = new JTextField(5);
	public JTextField normalPrice= new JTextField(5);
	public JTextField reservationPrice = new JTextField(5);
	public JTextField subscriberPrice = new JTextField(5);
	public JTextField enterSpeed = new JTextField(5);
	public JTextField paymentSpeed = new JTextField(5);
	public JTextField exitSpeed = new JTextField(5);
	public JTextField workDay = new JTextField(5);
	public JTextField weekend = new JTextField(5);
	public JTextField special = new JTextField(5);
	public JTextField subWorkDay = new JTextField(5);
	public JTextField subWeekend = new JTextField(5);
	public JTextField subSpecial = new JTextField(5);
	public JTextField reservationWorkDay = new JTextField(5);
	public JTextField reservationWeekend = new JTextField(5);
	public JTextField reservationSpecial = new JTextField(5);
	
	public DataController(SimulatorModel simulator) {
		super(simulator);
		setSize(400, 200);
		setVisible(true);
	
		add(boxLayout);
		boxLayout.add(multiplierText);	
		boxLayout.add(multiplier);
		boxLayout.add(reservationsText);	
		boxLayout.add(reservations);
		boxLayout.add(subscribersText);
		boxLayout.add(subscribers);
		boxLayout.add(retardText);
		boxLayout.add(retards);
		boxLayout.add(normalPriceText);
		boxLayout.add(normalPrice);
		boxLayout.add(reservationPriceText);
		boxLayout.add(reservationPrice);
		boxLayout.add(subPriceText);
		boxLayout.add(subscriberPrice);
		boxLayout.add(enterSpeedText);
		boxLayout.add(enterSpeed);
		boxLayout.add(paymentSpeedText);
		boxLayout.add(paymentSpeed);
		boxLayout.add(exitSpeedText);
		boxLayout.add(exitSpeed);
		boxLayout.add(workDayText);
		boxLayout.add(workDay);
		boxLayout.add(weekendText);
		boxLayout.add(weekend);
		boxLayout.add(specialText);
		boxLayout.add(special);
		boxLayout.add(subWorkDayText);
		boxLayout.add(subWorkDay);
		boxLayout.add(subWeekendText);
		boxLayout.add(subWeekend);
		boxLayout.add(subSpecialText);
		boxLayout.add(subSpecial);
		boxLayout.add(reservationWorkDayText);
		boxLayout.add(reservationWorkDay);
		boxLayout.add(reservationWeekendText);
		boxLayout.add(reservationWeekend);
		boxLayout.add(reservationSpecialText);
		boxLayout.add(reservationSpecial);
		
		
	
		//add(jp);
	}

	public void actionPerformed(ActionEvent e) {
		
	}
}
