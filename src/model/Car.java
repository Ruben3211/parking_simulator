package model;

import java.awt.Color;

/**
 * This class is the super class that is required for all the different kinds
 * of cars that are created for the simulation. This class will provide a 
 * multitude of methods that will set up the cars correctly. As well as keep 
 * track of their location and payment status.
 * 
 * @author Rick Zwaneveld
 * @version 03-02-2018
 */

public abstract class Car {

	private Location location;
	private int minutesLeft;
	private boolean isPaying;
	private boolean hasToPay;

	/**
	 * The constructor for the class Car.
	 */
	public Car() {
		
	}
	
	/**
	 * This method will return the location of a parked car.
	 * 
	 * @return location the location of the car
	 */
	public Location getLocation() {
        return location;
    }
	
	/**
	 * This method will set the location of where a car is going to park.
	 * 
	 * @param location the location of the car
	 */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * This method will return the amount of minutes left, that a parked car 
     * still has left in the garage.
     * 
     * @return minutesLeft the amount of minutes the car has left in the garage
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }
    
    /**
     * This method will set the amount of minutes for how long a car will
     * remain parked in the garage.
     * 
     * @param minutesLeft the amount of minutes the car will stay in the garage
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    /**
     * This method will determine if a car is about to pay or not.
     * 
     * @return isPaying true if the vehicle is going to pay, false if not
     */
    public boolean getIsPaying() {
        return isPaying;
    }
    
    /**
     * This method will set whether a car is about to pay or not.
     * 
     * @param isPaying true if the vehicle is going to pay, false if not
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * This method will determine if the vehicle is obligated to pay before 
     * leaving the garage.
     * 
     * @return hasToPay true if the vehicle must pay before exiting, false if not 
     */
    public boolean getHasToPay() {
        return hasToPay;
    }
    
    /**
     * This method will set whether a car is obligated to pay on exit, or not
     * 
     * @param hasToPay true if the vehicle must pay before exiting, false if not
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     * This method will decrease the time a car has left in a parking spot by a 
     * minute each time it is called.
     */
    public void tick() {
        minutesLeft--;
    }
    
    /**
     * This method will return the color that is given to a car object.
     * 
     * @return color the car assigned to the car object
     */
    public abstract Color getColor();

    /**
     * This method will return a String consisting of the type that belongs to a 
     * car object.
     * 
     * @return String the type that is set to a specific car object
     */
    public abstract String getType();
}
