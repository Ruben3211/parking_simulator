package logic;

import java.util.Random;

/**
 * This class uses the Car super class to set up a car of the type "subscription". 
 * This class will set a random amount of minutes the car will stay parked, and whether 
 * the car is obligated to pay or not.
 * 
 * @author Rick Zwaneveld
 * @version 23-01-2018
 */
public class SubscriptionCar extends Car {
	
	private static final String TYPE="subscription";
	
	/**
	 * The constructor for this class.
	 */
    public SubscriptionCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    /**
     * This method will return the type set to the car.
     * 
     * @return TYPE a String with a type attached to it.
     */
    public String getType() {
    	return TYPE;
    }
}
