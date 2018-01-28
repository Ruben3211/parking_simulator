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
		blankOne = new JLabel();
		
		regularLabel = new JLabel("Regular:");
		regularData = new JLabel();
		
		subscriptionLabel = new JLabel("Subscription:");
		subscriptionData = new JLabel();
		
		reservationLabel = new JLabel("Reservation:");
		reservationData = new JLabel();
		
		totalLabel = new JLabel("Total:");
		totalData = new JLabel();
		
		blankTwo = new JLabel();
		blankThree = new JLabel();
		
		parkedIncomeLabel = new JLabel("<html><b>Parked Income</b></html>");
		blankFour = new JLabel();
		
		parkedRegularLabel = new JLabel("Regular:");
		parkedRegularData = new JLabel();
		
		parkedReservationLabel = new JLabel("Reservation:");
		parkedReservationData = new JLabel();
		
		parkedTotalLabel = new JLabel("Total:");
		parkedTotalData = new JLabel();

		add(incomeLabel);
		add(blankOne);
		
		add(regularLabel);
		add(regularData);
		
		add(subscriptionLabel);
		add(subscriptionData);
		
		add(reservationLabel);
		add(reservationData);
		
		add(totalLabel);
		add(totalData);
		
		add(blankTwo);
		add(blankThree);
		
		add(parkedIncomeLabel);
		add(blankFour);
		
		add(parkedRegularLabel);
		add(parkedRegularData);
		
		add(parkedReservationLabel);
		add(parkedReservationData);
		
		add(parkedTotalLabel);
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
