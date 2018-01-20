package logic;

import java.awt.Color;
import java.util.Random;

public class ReservationCar extends Car {
	
	private static final Color COLOR=Color.yellow;
	
	public ReservationCar() {
		Random random = new Random();
		int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
		this.setMinutesLeft(stayMinutes);
		this.setHasToPay(true);
	}

	public Color getColor() {
		return COLOR;
	}
}
