package controller;


import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SimulatorModel;

@SuppressWarnings("serial")
public class DataController extends AbstractController {
	
	JPanel jp = new JPanel();
	public JLabel multiplierText = new JLabel("Multiplier (%)");
	public JLabel reservationsText = new JLabel();
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
	public JTextField reservations = new JTextField();
	public JTextField subscribers = new JTextField();
	public JTextField retards = new JTextField();
	public JTextField normalPrice= new JTextField();
	public JTextField reservationPrice = new JTextField();
	public JTextField subscriberPrice = new JTextField();
	public JTextField enterSpeed = new JTextField();
	public JTextField paymentSpeed = new JTextField();
	public JTextField exitSpeed = new JTextField();
	public JTextField workDay = new JTextField();
	public JTextField weekend = new JTextField();
	public JTextField special = new JTextField();
	public JTextField subWorkDay = new JTextField();
	public JTextField subWeekend = new JTextField();
	public JTextField subSpecial = new JTextField();
	public JTextField reservationWorkDay = new JTextField();
	public JTextField reservationWeekend = new JTextField();
	public JTextField reservationSpecial = new JTextField();
	
	public DataController(SimulatorModel simulator) {
		super(simulator);
		setSize(400, 200);
		setVisible(true);
		
		jp.add(multiplierText);
		jp.add(multiplier);
		add(jp);
	}

	public void actionPerformed(ActionEvent e) {
		
	}
}
