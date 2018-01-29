package model;

import java.awt.Color;
import java.util.Random;

/**
 * This class extends the Car class and will be used to create "regular" cars. 
 * Every car will stay parked a random amount of minutes. Regular cars d have 
 * to pay when they leave the garage.
 * 
 * @author Rick Zwaneveld
 * @version 27-01-2018
 */

public class RegularCar extends Car {
	
	private static final Color COLOR=Color.RED;
	private static final String TYPE="regular";
	
	/**
	 * The constructor for the class RegularCar.
	 */
    public RegularCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    /**
     * This method returns the color set for regular, which is set to
     * the color red.
     * 
     * @return color returns the color for regular car objects
     */
    public Color getColor() {
    	return COLOR;
    }
    
    /**
     * This method will return the type set to the car, which is set to 
     * "regular".
     * 
     * @return type a String with a type attached to it.
     */
    public String getType() {
    	return TYPE;
    }
}
