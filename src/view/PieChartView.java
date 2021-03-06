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
 * @version 03-02-2018
 */

@SuppressWarnings("serial")
public class PieChartView extends AbstractView {
	
	private JLabel regularLabel;
	private JLabel subscriptionLabel;
	private JLabel reservationLabel;
	private JLabel emptySpotsLabel;
	
	/**
	 * The constructor for the class PieChartView.
	 * 
	 * @param simulator the model
	 */
	public PieChartView(SimulatorModel simulator) {
		super(simulator);
		
		setLayout(null);

		regularLabel = new JLabel("Regular Car");
		regularLabel.setBounds(385, 213, 100, 15);
		add(regularLabel);

		subscriptionLabel = new JLabel("Subscription Car");
		subscriptionLabel.setBounds(385, 233, 100, 15);
		add(subscriptionLabel);

		reservationLabel = new JLabel("Reservation Car");
		reservationLabel.setBounds(385, 253, 100, 15);
		add(reservationLabel);

		emptySpotsLabel = new JLabel("Empty Spots");
		emptySpotsLabel.setBounds(385, 273, 100, 15);
		add(emptySpotsLabel);
	}
	
	/**
	 * This method retrieves the information needed to create and update a pie 
	 * chart from the model. It will convert the data from the model to a 
	 * radius. Once this is done it will begin drawing arcs to form a pie 
	 * chart. It also creates four rectangles, which are used as a legend.
	 * 
	 * @param g the specified Graphics context
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int regular = getModel().getTotalParkedRegular();
		int subscription = getModel().getTotalParkedSubscription();
		int reservation = getModel().getTotalParkedReservation();
		int empty = getModel().getNumberOfOpenSpots();
		
		double radius = 360.0 / 540;
		
		regular = (int) (regular * radius);
		subscription = (int) (subscription * radius);
		reservation = (int) (reservation * radius);
		empty = (int) (empty * radius);
		
		// Creates a rectangular and circular background.
		Color background = new Color(214, 217, 223);
		g.setColor(background);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.WHITE);
		g.fillArc(25, 100, 300, 300, 0, 360);
		
		// Creates the arcs for all four categories.
		int start = 0;
	    g.setColor(Color.RED);
	    g.fillArc(25, 100, 300, 300, start, regular);
	    start += regular;
	    g.setColor(Color.BLUE);
	    g.fillArc(25, 100, 300, 300, start, subscription);
	    start += subscription;
	    g.setColor(Color.YELLOW);
	    g.fillArc(25, 100, 300, 300, start, reservation);
	    start += reservation;
	    g.setColor(Color.WHITE);
	    g.fillArc(25, 100, 300, 300, start, empty);
	    
	    // Creates colored rectangles for the legend.
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
