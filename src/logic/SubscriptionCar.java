package logic;

import java.util.Random;

public class SubscriptionCar extends Car {
	
	private static final String TYPE="subscription";
	
    public SubscriptionCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public String getType() {
    	return TYPE;
    }
}
