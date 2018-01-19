package car;

import java.awt.Color;
import java.util.Random;

public class SubscriptionCar extends AbstractCar {
	
	private static final Color COLOR=Color.blue;
	
    public SubscriptionCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
