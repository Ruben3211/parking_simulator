package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import model.SimulatorModel;

/**
 * This class is responsible for creating and updating a pie chart. The pie
 * chart resembles the total amount of cars parked per type and the total amount
 * of empty parking spots left. This pie chart includes a legend to indicate
 * what each color in the pie chart represents.
 * 
 * @author Rick Zwaneveld
 * @version 28-01-2018
 */

@SuppressWarnings("serial")
public class PieChartView extends AbstractView {
	
	private JLabel titleLabel;
	private JLabel regularLabel;
	private JLabel subscriptionLabel;
	private JLabel reservationLabel;
	private JLabel emptySpots;
	
	/**
	 * The constructor for the class PieChartView.
	 * 
	 * @param simulator the model
	 */
	public PieChartView(SimulatorModel simulator) {
		super(simulator);
		
		setLayout(null);
		
		titleLabel = new JLabel("<html><b><h2><i>Occupancy Rate</i></h></b></html>");
		titleLabel.setBounds(103, 50, 144, 50);
		
		regularLabel = new JLabel("Regular Car");
		regularLabel.setBounds(385, 213, 100, 15);
		
		subscriptionLabel = new JLabel("Subscription Car");
		subscriptionLabel.setBounds(385, 233, 100, 15);
		
		reservationLabel = new JLabel("Reservation Car");
		reservationLabel.setBounds(385, 253, 100, 15);
		
		emptySpots = new JLabel("Empty Spots");
		emptySpots.setBounds(385, 273, 100, 15);
		
		add(titleLabel);
		
		add(regularLabel);
		
		add(subscriptionLabel);
		
		add(reservationLabel);
		
		add(emptySpots);
	}
	
	/**
	 * This method retrieves the information needed to create and update a pie 
	 * chart from the model. It will convert the data from the model to a 
	 * percentage. Once this is done it will begin drawing arcs to form a pie 
	 * chart. It also creates four rectangles, which are used as a legend.
	 * 
	 * @param g the specified Graphics context
	 */
	public void paintComponent(Graphics g) {
		int regular = getModel().getTotalParkedRegular();
		int subscription = getModel().getTotalParkedSubscription();
		int reservation = getModel().getTotalParkedReservation();
		int empty = getModel().getNumberOfOpenSpots();
		
		double radius = 360.0 / 540;
		
		regular = (int) (regular * radius);
		subscription = (int) (subscription * radius);
		reservation = (int) (reservation * radius);
		empty = (int) (empty * radius);
		
		Color background = new Color(214, 217, 223);
		
		g.setColor(background);
		g.fillRect(0, 0, 500, 500);
		
		g.setColor(Color.WHITE);
		g.fillArc(25, 100, 300, 300, 0, 360);
		
		int sum = 0;
	    g.setColor(Color.RED);
	    g.fillArc(25, 100, 300, 300, sum, regular);
	    
	    sum += regular;
	    g.setColor(Color.BLUE);
	    g.fillArc(25, 100, 300, 300, sum, subscription);
	    
	    sum += subscription;
	    g.setColor(Color.YELLOW);
	    g.fillArc(25, 100, 300, 300, sum, reservation);
	    
	    sum += reservation;
	    g.setColor(Color.WHITE);
	    g.fillArc(25, 100, 300, 300, sum, empty);
	    
	    g.setColor(Color.RED);
	    g.fillRect(350, 213, 30, 15);
	    
	    g.setColor(Color.BLUE);
	    g.fillRect(350, 233, 30, 15);
	    
	    g.setColor(Color.YELLOW);
	    g.fillRect(350, 253, 30, 15);
	    
	    g.setColor(Color.WHITE);
	    g.fillRect(350, 273, 30, 15);
	}
}
