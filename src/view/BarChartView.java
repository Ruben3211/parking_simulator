package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import model.SimulatorModel;

/**
 * This class is responsible for creating and updating the bar chart. The bar 
 * chart displays the total amount of cars parked per type and the total amount 
 * of empty parking spots left.
 * 
 * @author Ruben Bonga
 * @version 29-01-2018
 */

@SuppressWarnings("serial")
public class BarChartView extends AbstractView {

	private JLabel regularLabel;
	private JLabel subscriptionLabel;
	private JLabel reservationLabel;
	private JLabel emptySpotsLabel;
	
	/**
	 * The constructor for the class BarChartView.
	 * 
	 * @param simulator the model
	 */
	public BarChartView(SimulatorModel simulator) {
		super(simulator);
		
		setLayout(null);
	    
		regularLabel = new JLabel("Regular Car");
		regularLabel.setBounds(385, 213, 100, 15);
	    add(regularLabel);
	    
		subscriptionLabel = new JLabel("Subscription Car");
		subscriptionLabel.setBounds(385, 233, 100, 15);
	    add(subscriptionLabel);

		reservationLabel = new JLabel("Reservation Car");
		reservationLabel.setBounds(385, 253, 100, 15);;
	    add(reservationLabel);
	    
		emptySpotsLabel = new JLabel("Empty Spots");
		emptySpotsLabel.setBounds(385, 273, 100, 15);
	    add(emptySpotsLabel);
	}

	/**
	 * This method is responsible for getting the data needed for the bar chart 
	 * and keeping this data up-to-date. Furthermore it is also responsible for
	 * creating the visuals needed to display the bar chart.
	 * 
	 * @param g the specified Graphics context
	 */
	public void paintComponent(Graphics g) {
		int regular = getModel().getTotalParkedRegular();
		int subscription = getModel().getTotalParkedSubscription();
		int reservation = getModel().getTotalParkedReservation();
		int empty = getModel().getNumberOfOpenSpots();
			
		// Creates a rectangular background.
		Color background = new Color(214, 217, 223);
		g.setColor(background);
		g.fillRect(0, 0, 500, 500);
		
		// Creates a blank line.
		g.setColor(Color.BLACK);
		g.fillRect(10, 385, 330, 2);
		
		// Creates the bars for the four categories.
		create3DBar(g, Color.RED, 25, 115, 50, 270);
		create2DBar(g, background, 25, 115, 50, 270 - regular / 2);
		create3DBar(g, Color.BLUE, 109, 115, 50, 270);
		create2DBar(g, background, 109, 115, 50, 270 - subscription / 2);
		create3DBar(g, Color.YELLOW, 191, 115, 50, 270);
		create2DBar(g, background, 191, 115, 50, 270 - reservation/ 2);
		create3DBar(g, Color.WHITE, 275, 115, 50, 270);
		create2DBar(g, background, 275, 115, 50, 270 - empty / 2);
   
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
	
	/**
	 * This method is used to create 3D bars. The bars can be customized with 
	 * the methods parameters.
	 * 
	 * @param g the specified Graphics context
	 * @param color the color given to the bar
	 * @param x the coordinates on the x-axis
	 * @param y the coordinates on the y-axis
	 * @param width the width of the bar
	 * @param height the height of the bar
	 */
	private void create3DBar(Graphics g, Color color, int x, int y, int width, int height) {
		g.setColor(color);
		g.fill3DRect(x, y, width, height, true);
	}
	
	/**
	 * This method is used to creates 2D bars. The bars can be customized with 
	 * the methods parameters.
	 * 
	 * @param g the specified Graphics context
	 * @param color the color given to the bar
	 * @param x the coordinates on the x-axis
	 * @param y the coordinates on the y-axis
	 * @param width the width of the bar
	 * @param height the height of the bar
	 */
	private void create2DBar(Graphics g, Color color, int x, int y, int width, int height) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
}
