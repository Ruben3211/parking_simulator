package view;

import java.awt.*;

import logic.*;
import car.*;
import parkingsimulator.Location;

public class CarParkView extends AbstractView {

	public CarParkView(SimulatorLogic simulator) {
		super(simulator);
	}
	
	// This is all from the SimulatorView class under class CarParkView.
	
	public void paintComponent(Graphics g) {
        
		Dimension size = simulator.getSize();
		
		if (simulator.getCarParkImage() == null) {
			Image carParkImage = createImage(size.width, size.height);
            simulator.setCarParkImage(carParkImage);
        }
        if (!simulator.getSize().equals(getSize())) {
            simulator.setSize(simulator.getSize());
            Image carParkImage = createImage(size.width, size.height);
            simulator.setCarParkImage(carParkImage);
        }
        
        Graphics graphics = simulator.getCarParkImage().getGraphics();
        for(int floor = 0; floor < simulator.getNumberOfFloors(); floor++) {
            for(int row = 0; row < simulator.getNumberOfRows(); row++) {
                for(int place = 0; place < simulator.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    AbstractCar car = simulator.getCarAt(location);
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        
        Dimension currentSize = getSize();
        if (simulator.getSize().equals(currentSize)) {
            g.drawImage(simulator.getCarParkImage(), 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(simulator.getCarParkImage(), 0, 0, currentSize.width, currentSize.height, null);
        }
        g.dispose();
	}
	
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}
