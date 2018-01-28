package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import model.Car;
import model.Location;
import model.ParkingSpace;
import model.SimulatorModel;

@SuppressWarnings("serial")
public class CarParkView extends AbstractView {

	private Dimension carParkImageSize;
	private Image carParkImage;
	
	public CarParkView(SimulatorModel simulator) {
		super(simulator);
		
		carParkImageSize = new Dimension(800, 500);
		carParkImage = createImage(carParkImageSize.width, carParkImageSize.height);
	}

	public Color getParkingSpaceColor(String spaceType) {
		if(spaceType == "reservation") {
			return new Color(255, 255, 204);
		}	
		else if(spaceType == "subscription") {
			return new Color(204, 204, 255);
		}
		return new Color(255, 255, 255);
	}

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
                    if(car == null)
                   		color = getParkingSpaceColor(space.getType());
                    else
                    	color = car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        g.drawImage(carParkImage, 0, 0, null);
        g.dispose();
	}
	
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1);
    }
}
