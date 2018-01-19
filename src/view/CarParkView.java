package view;

import java.awt.*;

import logic.*;
import car.*;
import parkingsimulator.Location;

public class CarParkView extends AbstractView {

	private Dimension carParkImageSize;
	private Image carParkImage;
	
	public CarParkView(SimulatorLogic simulator) {
		super(simulator);
		
		carParkImageSize = new Dimension(800, 500);
		carParkImage = createImage(carParkImageSize.width, carParkImageSize.height);
	}
	
	// This is all from the SimulatorView class under class CarParkView.
	
	public void paintComponent(Graphics g)
	{
		Dimension panelSize = getSize();

		if(!carParkImageSize.equals(panelSize))
		{
			carParkImageSize = panelSize; 
            carParkImage = createImage(panelSize.width, panelSize.height);
        }
        
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < simulator.getNumberOfFloors(); floor++)
        {
            for(int row = 0; row < simulator.getNumberOfRows(); row++)
            {
                for(int place = 0; place < simulator.getNumberOfPlaces(); place++)
                {
                    Location location = new Location(floor, row, place);
                    AbstractCar car = simulator.getCarAt(location);
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }

        g.drawImage(carParkImage, 0, 0, null);
        //g.drawImage(simulator.getCarParkImage(), 0, 0, currentSize.width, currentSize.height, null);
        g.dispose();
	}
	
    private void drawPlace(Graphics graphics, Location location, Color color)
    {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}
