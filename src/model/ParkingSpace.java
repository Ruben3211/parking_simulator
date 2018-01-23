package model;

public class ParkingSpace {

	private String type;
	private Car car;

	public ParkingSpace() {
		type = "regular";
		car = null;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

    public Car getCar() {
    	return car;
    }
	public void setCar(Car car) {
		this.car = car;
	}
}
