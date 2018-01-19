package car;

import java.awt.Color;
import java.util.Random;

public class RegularCar extends AbstractCar {
	
	private static final Color COLOR=Color.red;
	
    public RegularCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
