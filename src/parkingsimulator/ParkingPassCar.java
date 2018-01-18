package parkingsimulator;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	
	private static final Color COLOR=Color.blue;
	
	/**
	 * Constructor for objects of class ParkingPassCar.
	 */
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    /**
     * Method that returns the color given to the ParkingPassCar objects.
     * 
     * @return color
     */
    public Color getColor(){
    	return COLOR;
    }
}
