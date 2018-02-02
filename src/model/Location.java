package model;

/**
 * This class keeps track of the locations of parking spots. It has floors, rows 
 * and places. It also provides getter methods to return these numbers.
 * 
 * @author Rick Zwaneveld
 * @version 03-02-2018
 */

public class Location {
	
    private int floor;
    private int row;
    private int place;

    /**
     * The constructor for the class Location.
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
