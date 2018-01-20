package logic;

public class Location {
	
    private int floor;
    private int row;
    private int place;

    /**
     * Constructor for objects of class Location.
     */
    public Location(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
    }

    /**
     * Method that checks if the object created matches the
     * instance of the Location class. This is used for to
     * implement content equality.
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
     * Method that returns a string consisting of the values 
     * in floor, row and place and returns that string.
     * 
     * @return A string representation of the location.
     */
    public String toString() {
        return floor + "," + row + "," + place;
    }

    /**
     * Using 10 bits for each of the floor, row and place
     * values. This should give a unique hash code for each
     * (floor, row, place) tuple.
     * 
     * @return A hash code for the location.
     */
    public int hashCode() {
        return (floor << 20) + (row << 10) + place;
    }

    /**
     * Method that returns the value of the instance variable floor.
     * 
     * @return floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Method that returns the value of the instance variable row.
     * 
     * @return row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Method that returns the value of the instance variable place.
     * 
     * @return place.
     */
    public int getPlace() {
        return place;
    }
}
