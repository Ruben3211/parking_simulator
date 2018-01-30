package model;

import java.awt.Color;
import java.util.Random;

/**
 * This class extends the Car class and will be used to create "reservation"
 * cars. Every car will stay parked a random amount of minutes. Reservation 
 * cars do have to pay when they leave the garage.
 * 
 * @author Rick Zwaneveld
 * @version 30-01-2018
 */

public class ReservationCar extends Car {
	
	private static final Color COLOR=Color.YELLOW;
	private static final String TYPE="reservation";
	
	/**
	 * The constructor for the class ReservationCar.
	 */
	public ReservationCar() {
		Random random = new Random();
		int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
		this.setMinutesLeft(stayMinutes);
		this.setHasToPay(true);
	}
	
    /**
     * This method returns the color set for reservation cars, which is set to
     * the color yellow.
     * 
     * @return color returns the color for reservation car objects
     */
	public Color getColor() {
		return COLOR;
	}

    /**
     * This method will return the type set to the car, which is set to
     * "reservation".
     * 
     * @return type a String with a type attached to it.
     */
	public String getType() {
		return TYPE;
	}
}
