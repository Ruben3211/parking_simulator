package logic;

import java.awt.Color;
import java.util.Random;

public class SubscriptionCar extends Car {
	
	private static final Color COLOR=Color.blue;
	private static final String TYPE="2";
	
    public SubscriptionCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public String getType() {
    	return TYPE;
    }
    public Color getColor() {
    	return COLOR;
    }
}
