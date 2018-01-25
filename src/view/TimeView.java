package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;

import model.SimulatorModel;

/**
 * This class has one simple task, displaying the current day, hour and minute of
 * the simulator. The constructor is responsible for setting up the labels. The
 * method called paintComponent is responsible for getting up to date information.
 * It does this by calling on two methods in the model.
 * 
 * @author Rick Zwaneveld
 * @version 24-01-2018
 */

@SuppressWarnings("serial")
public class TimeView extends AbstractView {
	
	private JLabel timeWeekDay, timeMinuteHour;
	
	/**
	 * The constructor for this class.
	 * 
	 * @param simulator the model
	 */
	public TimeView(SimulatorModel simulator) {
		super(simulator);
		
		setLayout(new GridLayout(0, 2));
		
		timeWeekDay = new JLabel("Monday");
		timeMinuteHour = new JLabel("00:00");
		
		add(timeWeekDay);
		add(timeMinuteHour);
	}
	
	/**
	 * Responsible for keeping the day, hour and minute up to date as the time
	 * within the simulator changes.
	 * 
	 * @param g the specified Graphics context
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		timeWeekDay.setText(getModel().getDay());
		timeMinuteHour.setText(getModel().getTime());
	}
}
