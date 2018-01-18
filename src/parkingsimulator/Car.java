package parkingsimulator;

import java.awt.*;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;

    /**
     * Constructor for objects of class Car.
     */
    public Car() {

    }

    /**
     * This method returns the location of a car object.
     * 
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * This method sets the location of a car object.
     * 
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * This method returns the amount of minutes left until a car object is removed.
     * 
     * @return minutesLeft
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * This method sets the amount a minutes until a car object is removed.
     * 
     * @param minutesLeft
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    /**
     * This method decides if a leaving car is going to pay or not.
     * 
     * @return isPaying
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * This method sets whether a leaving car is going to pay or not.
     * 
     * @param isPaying
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * This method returns whether a car is obligated to pay or not.
     * 
     * @return hasToPay
     */
    public boolean getHasToPay() {
        return hasToPay;
    }

    /**
     * This method sets whether a car is obligated to pay or not.
     * 
     * @param hasToPay
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     * This method subtracts one minutes from the total amount of minutes that a car has left in the garage.
     */
    public void tick() {
        minutesLeft--;
    }
    
    /**
     * This method returns the color of a car object.
     * 
     * @return color
     */
    public abstract Color getColor();
}
