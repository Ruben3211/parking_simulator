package model;

/**
 * This class keeps track of the locations of parking spots. It has floors, rows and
 * places. It also provides getter methods to return these numbers. These numbers
 * can also be returned as a hash code. Lastly this class also provides a quality
 * check to see if an obj matches Location.
 * 
 * @author Rick Zwaneveld
 * @version 23-01-2018
 */

public class Location {
	
    private int floor;
    private int row;
    private int place;

    /**
     * The constructor of this class.
     * 
     * @param floor a number for the floor
     * @param row a number for the row
     * @param place a number for the place
     */
    public Location(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
    }

    /**
     * This method will check if the object created matches the instance of the 
     * Location class. This is used to implement content equality.
     * 
     * @return true if obj matches Location, false if otherwise
     */
    public boolean equals(Object obj) {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return floor == other.getFloor() && row == other.getRow() && place == other.getPlace();
        }
        else {
            return false;
        }
    }
    
    /**
     * This method will create a string out of the values of floor, row and place
     * and concatenates them together via a comma.
     * 
     * @return a String consisting of the floor, row and place
     */
    public String toString() {
        return floor + "," + row + "," + place;
    }

    /**
     * This method uses 10 bits for each of the floor, row and place values. This 
     * should give a unique hash code for each (floor, row, place) tuple.
     * 
     * @return a hash code for the location
     */
    public int hashCode() {
        return (floor << 20) + (row << 10) + place;
    }

    /**
     * This method will return the number of the floor.
     * 
     * @return floor a floor number
     */
    public int getFloor() {
        return floor;
    }

    /**
     * This method will return the number of a row.
     * 
     * @return row a row number
     */
    public int getRow() {
        return row;
    }

    /**
     * This method will return the number of a place.
     * 
     * @return place a place number
     */
    public int getPlace() {
        return place;
    }
}
