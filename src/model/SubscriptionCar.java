package model;

import java.awt.Color;
import java.util.Random;

/**
 * This class extends the Car class and will be used to create "subscription"
 * cars. Every car will stay parked a random amount of minutes. Subscription 
 * cars do not have to pay when they leave the garage.
 * 
 * @author Rick Zwaneveld
 * @version 27-01-2018
 */

public class SubscriptionCar extends Car {
	
	private static final Color COLOR=Color.BLUE;
	private static final String TYPE="subscription";
	
	/**
	 * The constructor for the class SubscriptionCar.
	 */
    public SubscriptionCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    /**
     * This method returns the color set for subscription cars, which is set to
     * the color blue.
     * 
     * @return color returns the color for subscription car objects
     */
    public Color getColor() {
    	return COLOR;
    }

    /**
     * This method will return the type set to the car, which is set to
     * "subscriptions".
     * 
     * @return type a String with a type attached to it.
     */
    public String getType() {
    	return TYPE;
    }
}
