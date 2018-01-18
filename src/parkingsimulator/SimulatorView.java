package parkingsimulator;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends JFrame {
	
    private CarParkView carParkView;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private Car[][][] cars;

    /**
     * Constructor for the objects of class SimulatorView.
     * 
     * @param numberOfFloors
     * @param numberOfRows
     * @param numberOfPlaces
     */
    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        
        carParkView = new CarParkView();

        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        pack();
        setVisible(true);

        updateView();
    }

    /**
     * Method that updates the carParkView.
     */
    public void updateView() {
        carParkView.updateView();
    }
    
    /**
     * Method that returns the number of floors.
     * 
     * @return numberOfFloors
     */
	public int getNumberOfFloors() {
        return numberOfFloors;
    }

	/**
	 * Method that returns the number of rows.
	 * 
	 * @return numberOfRows
	 */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Method that returns the number of places.
     * 
     * @return numberOfPlaces
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Method that returns the number of open spots.
     * 
     * @return numberOfOpenSpots
     */
    public int getNumberOfOpenSpots() {
    	return numberOfOpenSpots;
    }
    
    /**
     * Method that gets the location of a car.
     * 
     * @param location
     * @return null if the location is invalid and the floor, row and place if it is valid
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Method that sets a car at a location.
     * 
     * @param location
     * @param car
     * @return false if the location is invalid or true if the location is valid
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }
    
    /**
     * Method that removes a car from a location.
     * 
     * @param location
     * @return null if the location is invalid and car equals null or returns car if a car is found
     */

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    /**
     * Method that returns the first free location it can find.
     * 
     * @return location if a free location is found, otherwise it returns null
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that gets the first car that needs to leave.
     * 
     * @return car if a car is found, otherwise return null
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that checks all the parking spaces and gets the cars at each location.
     * If the car equals null it calls upon the tick() method in the Car class.
     */
    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    /**
     * Method that checks if a location is valid.
     * 
     * @param location
     * @return false if location is invalid, otherwise return true
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    
    private class CarParkView extends JPanel {
        
        private Dimension size;
        private Image carParkImage;    

        /**
         * Constructor for objects of class CarPark.
         */
        public CarParkView() {
            size = new Dimension(0, 0);
        }
    
        /**
         * Method that sets the dimensions of car park view.
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }
    
        /**
         * Method that draws the image of a car park view.
         * Overridden. The car park view component needs to be redisplayed. 
         * Copy the internal image to screen.
         */
        public void paintComponent(Graphics g) {
            if (carParkImage == null) {
                return;
            }
            Dimension currentSize = getSize();
            if (size.equals(currentSize)) {
                g.drawImage(carParkImage, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }
    
        /**
         * Method that updates the view.
         */
        public void updateView() {
            // Create a new car park image if the size has changed.
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            Graphics graphics = carParkImage.getGraphics();
            for(int floor = 0; floor < getNumberOfFloors(); floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        Color color = car == null ? Color.white : car.getColor();
                        drawPlace(graphics, location, color);
                    }
                }
            }
            repaint();
        }
    
        /**
         * Method that paints a certain place in the car park view in a given color.
         */
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }
}
