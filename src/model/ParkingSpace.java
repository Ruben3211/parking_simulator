package model;

/**
 * This class is responsible for handling the parking spaces. These spaces are
 * used to assign a specific car to a specific place. This way, cars can be
 * given certain spots within the garage where the other cars can't park.
 * 
 * @author Rick Zwaneveld
 * @version 03-02-2018
 */
public class ParkingSpace {

	private String type;
	private Car car;

	/**
	 * The constructor for the class ParkingSpace.
	 */
	public ParkingSpace() {
		type = "regular";
		car = null;
	}

	/**
	 * The method returns the given type of a parking space.
	 * 
	 * @return type the type set to a parking space
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * The method sets a type of a parking space.
	 * 
	 * @param type the type to be set to a parking space
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * This method returns the car for a specific parking space.
	 * 
	 * @return car the car for a specific parking space
	 */
    public Car getCar() {
    	return car;
    }
    
    /**
     * This method sets a car object to a specific parking space.
     * 
     * @param car the car for a specific parking space
     */
	public void setCar(Car car) {
		this.car = car;
	}
}
