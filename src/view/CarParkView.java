package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import model.Car;
import model.Location;
import model.ParkingSpace;
import model.SimulatorModel;

/**
 * This class is responsible for creating the car park for the simulator. The
 * car park is the view of all the parking spaces. These parking spaces are
 * filled with the various different vehicles available.
 * 
 * @author Rick Zwaneveld
 * @version 30-01-2018
 */

@SuppressWarnings("serial")
public class CarParkView extends AbstractView {

	private Dimension carParkImageSize;
	private Image carParkImage;
	
	/**
	 * The constructor for the class CarParkView.
	 * 
	 * @param simulator the model
	 */
	public CarParkView(SimulatorModel simulator) {
		super(simulator);
		
		carParkImageSize = new Dimension(800, 500);
		carParkImage = createImage(carParkImageSize.width, carParkImageSize.height);
	}

	/**
	 * This method is used to set a specific color to a given space type. This
	 * will create colored parking spaces for certain cars to park on. These
	 * colors represent empty spaces.
	 * 
	 * @param spaceType the type associated with a parking space
	 * @return color the color that is used for the specified spaceType
	 */
	public Color getParkingSpaceColor(String spaceType) {
		if(spaceType == "reservation") {
			return new Color(255, 255, 204);
		}	
		else if(spaceType == "subscription") {
			return new Color(204, 204, 255);
		}
		return new Color(255, 255, 255);
	}

	/**
	 * This method is responsible for creating the car park image. This is the
	 * image that will contain all the individual parking spaces. It uses
	 * multiple loops to create the floors, rows and places.
	 * 
	 * @param g the specified Graphics context
	 */
	public void paintComponent(Graphics g) {
	
		Dimension panelSize = getSize();

		if(!carParkImageSize.equals(panelSize)) {
			carParkImageSize = panelSize; 
            carParkImage = createImage(panelSize.width, panelSize.height);
        }

        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < getModel().getNumberOfFloors(); floor++) {
            for(int row = 0; row < getModel().getNumberOfRows(); row++) {
                for(int place = 0; place < getModel().getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                	ParkingSpace space = getModel().getParkingSpaceAt(location);
                	Car car = space.getCar();
                    Color color;
                    if(car == null) {
                    	color = getParkingSpaceColor(space.getType());
                    }
                    else {
                    	color = car.getColor();
                    }
                    drawPlace(graphics, location, color);      
                }
            }
        }
        g.drawImage(carParkImage, 0, 0, null);
	}
	
	/**
	 * This method is responsible for drawing all the parking spaces to the
	 * screen. It creates a rectangle with a specific dimension and sets the
	 * needed color.
	 * 
	 * @param graphics the graphics needed to draw the parking spaces
	 * @param location the location for each parking space
	 * @param color the color for each parking space
	 */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1);
    }
}
