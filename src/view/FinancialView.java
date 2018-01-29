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
	
	private JLabel incomeLabel, blankOne;
	private JLabel regularLabel, regularData;
	private JLabel subscriptionLabel, subscriptionData;
	private JLabel reservationLabel, reservationData;
	private JLabel totalLabel, totalData;
	
	private JLabel blankTwo, blankThree;
	
	private JLabel parkedIncomeLabel, blankFour;
	private JLabel parkedRegularLabel, parkedRegularData;
	private JLabel parkedReservationLabel, parkedReservationData;
	private JLabel parkedTotalLabel, parkedTotalData;
	
	/**
	 * The constructor for the class FinancialView.
	 * 
	 * @param simulator the model
	 */
	public FinancialView(SimulatorModel simulator) {	
		super(simulator);
		
		setLayout(new GridLayout(10, 2));
		
		incomeLabel = new JLabel("<html><b>Income</b></html>");
		add(incomeLabel);
		blankOne = new JLabel();
		add(blankOne);
		
		regularLabel = new JLabel("Regular:");
		add(regularLabel);
		regularData = new JLabel();
		add(regularData);
		
		subscriptionLabel = new JLabel("Subscription:");
		add(subscriptionLabel);
		subscriptionData = new JLabel();
		add(subscriptionData);
		
		reservationLabel = new JLabel("Reservation:");
		add(reservationLabel);
		reservationData = new JLabel();
		add(reservationData);
		
		totalLabel = new JLabel("Total:");
		add(totalLabel);
		totalData = new JLabel();
		add(totalData);
		
		blankTwo = new JLabel();
		add(blankTwo);
		blankThree = new JLabel();
		add(blankThree);
		
		parkedIncomeLabel = new JLabel("<html><b>Parked Income</b></html>");
		add(parkedIncomeLabel);
		blankFour = new JLabel();
		add(blankFour);
		
		parkedRegularLabel = new JLabel("Regular:");
		add(parkedRegularLabel);
		parkedRegularData = new JLabel();
		add(parkedRegularData);
		
		parkedReservationLabel = new JLabel("Reservation:");
		add(parkedReservationLabel);
		parkedReservationData = new JLabel();
		add(parkedReservationData);
		
		parkedTotalLabel = new JLabel("Total:");
		add(parkedTotalLabel);
		parkedTotalData = new JLabel();
		add(parkedTotalData);
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
		
		regularData.setText("€" + String.valueOf(getModel().getTotalRegularIncome()));
		subscriptionData.setText("€" + String.valueOf(getModel().getTotalSubscriptionIncome()));
		reservationData.setText("€" + String.valueOf(getModel().getTotalReservationIncome()));
		totalData.setText("€" + String.valueOf(getModel().getTotalIncome()));
		parkedRegularData.setText("€" + String.valueOf(getModel().getParkedRegularIncome()));
		parkedReservationData.setText("€" + String.valueOf(getModel().getParkedReservationIncome()));
		parkedTotalData.setText("€" + String.valueOf(getModel().getParkedTotalIncome()));
	}
}
