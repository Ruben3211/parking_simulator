package view;

import java.awt.Color;
import java.awt.Graphics;

import model.SimulatorModel;

/**
 * This class is responsible for creating and updating a pie chart. The pie
 * chart resembles the total amount of cars parked per type and the total amount
 * of empty parking spots left.
 * 
 * @author Rick Zwaneveld
 * @version 27-01-2018
 */

@SuppressWarnings("serial")
public class PieChartView extends AbstractView {
	
	/**
	 * The constructor for the class PieChartView.
	 * 
	 * @param simulator the model
	 */
	public PieChartView(SimulatorModel simulator) {
		super(simulator);
	}
	
	/**
	 * This method retrieves the information needed to create and update a pie 
	 * chart from the model. It will convert the data from the model to a 
	 * percentage. Once this is done it will begin drawing arcs to form a pie 
	 * chart.
	 * 
	 * @param g the specified Graphics context
	 */
	public void paintComponent(Graphics g) {
		int regular = getModel().getTotalParkedRegular();
		int subscription = getModel().getTotalParkedSubscription();
		int reservation = getModel().getTotalParkedReservation();
		int empty = getModel().getNumberOfOpenSpots();
		
		double percentage = 360.0 / 540;
		
		regular = (int) (regular * percentage);
		subscription = (int) (subscription * percentage);
		reservation = (int) (reservation * percentage);
		empty = (int) (empty * percentage);
		
		Color background = new Color(214, 217, 223);
		
		g.setColor(background);
		g.fillRect(0, 0, 500, 500);
		
		int sum = 0;
	    g.setColor(Color.RED);
	    g.fillArc(100, 100, 300, 300, sum, regular);
	    
	    sum += regular;
	    g.setColor(Color.BLUE);
	    g.fillArc(100, 100, 300, 300, sum, subscription);
	    
	    sum += subscription;
	    g.setColor(Color.YELLOW);
	    g.fillArc(100, 100, 300, 300, sum, reservation);
	    
	    sum += reservation;
	    g.setColor(Color.WHITE);
	    g.fillArc(100, 100, 300, 300, sum, empty);
	}
}
