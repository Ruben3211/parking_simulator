package model;

/**
 * This class is responsible for keeping track of reservations made, for when
 * these reservations will arrive within the garage and for when the made
 * reservations will expire.
 * 
 * @author Rick Zwaneveld
 * @version 30-01-2018
 */
public class Reservation {

	private Location location;
	private int arrivalTime;
	private int expirationTime;

	/**
	 * The constructor for the class Reservation.
	 * 
	 * @param location the location of a reservation
	 * @param arrivalTime the time of arrival of a reservation
	 * @param expirationTime the time of expiration of a reservation
	 */
	public Reservation(Location location, int arrivalTime, int expirationTime) {
		this.location = location;
		this.arrivalTime = arrivalTime;
		this.expirationTime = expirationTime;
	}

	/**
	 * This method returns the location for a made reservation.
	 * 
	 * @return location the location of where a reservation is set
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * This method returns the time for when a reservation is going to arrive.
	 * 
	 * @return arrivalTime the time of arrival for a reservation
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	/**
	 * This method returns the time, for when a reservation will leave the
	 * garage.
	 * 
	 * @return expirationTime the time of expiration for a reservation
	 */
	public int getExpirationTime() {
		return expirationTime;
	}
}
