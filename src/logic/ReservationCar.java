package logic;

import java.util.Random;

public class ReservationCar extends Car {
	
	private static final String TYPE="reservation";
	
	public ReservationCar() {
		Random random = new Random();
		int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
		this.setMinutesLeft(stayMinutes);
		this.setHasToPay(true);
	}

	public String getType() {
		return TYPE;
	}
}
