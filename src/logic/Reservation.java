package logic;

public class Reservation {

	private Location location;
	private int arrivalTime;    // in minutes
	private int expirationTime; // in minutes

	public Reservation(Location location, int arrivalTime, int expirationTime) {
		this.location = location;
		this.arrivalTime = arrivalTime;
		this.expirationTime = expirationTime;
	}

	public Location getLocation() {
		return location;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public int getExpirationTime() {
		return expirationTime;
	}
}
