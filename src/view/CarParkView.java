package view;

import java.awt.*;

import logic.*;

@SuppressWarnings("serial")
public class CarParkView extends AbstractView {

	private Dimension carParkImageSize;
	private Image carParkImage;
	
	public CarParkView(SimulatorLogic simulator) {
		super(simulator);
		
		carParkImageSize = new Dimension(800, 500);
		carParkImage = createImage(carParkImageSize.width, carParkImageSize.height);
	}

	public Color getParkingSpaceColor(String spaceType) {
		//if(spaceType == "regular")
			//return new Color(96, 0, 0); // dark red
		if(spaceType == "reservation")
			return new Color(255, 255, 153); // dark yellow
		else if(spaceType == "subscription")
			return new Color(153, 153, 255); // dark blue
		return new Color(255, 255, 255); // grey
	}

	public Color getCarColor(String carType) {
		if(carType == "regular")
			return new Color(255, 0, 0); // bright red
		else if(carType == "reservation")
			return new Color(255, 255, 0); // bright yellow
		else if(carType == "subscription")
			return new Color(0, 0, 255); // bright blue
		return new Color(255, 255, 255); // white
	}

	public void paintComponent(Graphics g) {
	
		Dimension panelSize = getSize();

		if(!carParkImageSize.equals(panelSize)) {
			carParkImageSize = panelSize; 
            carParkImage = createImage(panelSize.width, panelSize.height);
        }

        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < simulator.getNumberOfFloors(); floor++) {
            for(int row = 0; row < simulator.getNumberOfRows(); row++) {
                for(int place = 0; place < simulator.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                	ParkingSpace space = simulator.getParkingSpaceAt(location);
                	Car car = space.getCar();
                    Color color;
                    if(car == null)
                   		color = getParkingSpaceColor(space.getType());
                    else
                    	color = getCarColor(car.getType());
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
                10 - 1); // TODO use dynamic size or constants
    }
}
