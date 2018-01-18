package parkingsimulator;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
	
	private static final Color COLOR=Color.red;
	
	/**
	 * Constructor for objects of class AdHocCar.
	 */
    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    /**
     * This method returns the color given to the AdHocCar objects.
     * 
     * @return color
     */
    public Color getColor(){
    	return COLOR;
    }
}
