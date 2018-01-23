package model;

import java.util.Random;

/**
 * This class uses the Car super class to set up a car of the type "regular". This
 * class will set a random amount of minutes the car will stay parked, and whether 
 * the car is obligated to pay or not.
 * 
 * @author Rick Zwaneveld
 * @version 23-01-2018
 */

public class RegularCar extends Car {
	
	private static final String TYPE="regular";
	
	/**
	 * The constructor for this class.
	 */
    public RegularCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
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