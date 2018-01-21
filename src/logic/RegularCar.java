package logic;

import java.awt.Color;
import java.util.Random;

public class RegularCar extends Car {
	
	private static final String TYPE="1";
	private static final Color COLOR=Color.red;
	
    public RegularCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    public String getType() {
    	return TYPE;
    }
    public Color getColor() {
    	return COLOR;
    }
}
