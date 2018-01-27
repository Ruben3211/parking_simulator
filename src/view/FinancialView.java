package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;

import model.SimulatorModel;

/**
 * This class is used to display the financial data a user needs for the 
 * simulation. It keeps track of the income per car type, the total income and
 * the amount of money currently still parked in the garage.
 * 
 * @author Ruben Bonga & Rick Zwaneveld
 * @version 27-01-2018
 */

@SuppressWarnings("serial")
public class FinancialView extends AbstractView{

	private JLabel regularLabel, regularData;
	private JLabel subscriptionLabel, subscriptionData;
	private JLabel reservationLabel, reservationData;
	private JLabel totalLabel, totalData;
	private JLabel parkedLabel, parkedData;
	
	/**
	 * The constructor for the class FinancialView.
	 * 
	 * @param simulator the model
	 */
	public FinancialView(SimulatorModel simulator) {	
		super(simulator);
		
		setLayout(new GridLayout(6, 2));
		
		regularLabel = new JLabel("Regular:");
		regularData = new JLabel("0");
		
		subscriptionLabel = new JLabel("Subscription:");
		subscriptionData = new JLabel("0");
		
		reservationLabel = new JLabel("Reservation:");
		reservationData = new JLabel("0");
		
		totalLabel = new JLabel("Total:");
		totalData = new JLabel("0");
		
		parkedLabel = new JLabel("Parked income:");
		parkedData = new JLabel("0");

		add(regularLabel);
		add(regularData);
		
		add(subscriptionLabel);
		add(subscriptionData);
		
		add(reservationLabel);
		add(reservationData);
		
		add(totalLabel);
		add(totalData);
		
		add(parkedLabel);
		add(parkedData);
	}
	
	/**
	 * This method is responsible for retrieving and updating all the data that
	 * needs to be displayed. It does this by calling on getter methods in the
	 * model.
	 * 
	 * @param g the specified Graphics context
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		regularData.setText(String.valueOf(getModel().getTotalRegIncome()));
		subscriptionData.setText(String.valueOf(getModel().getTotalSubIncome()));
		reservationData.setText(String.valueOf(getModel().getTotalResIncome()));
		totalData.setText(String.valueOf(getModel().getTotalIncome()));
		parkedData.setText(String.valueOf(getModel().getTotalParkedIncome()));
	}
}
