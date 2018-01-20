package logic;

import java.util.*;

public class SimulatorLogic extends AbstractModel implements Runnable {

	// -----------------------------------------------------------
	// These instance variables are from the SimulatorView class.
	// -----------------------------------------------------------
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private Car[][][] cars;
	
	// -----------------------------------------------------------
	// These instance variables are from the Simulator class.
	// -----------------------------------------------------------
	private static final String REGULAR = "1";
	private static final String SUBSCRIPTION = "2";

	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int stepPause = 100;

    // Average number of cars arriving per hour.
    int weekDayArrivals = 100;
    int weekendArrivals = 200;
    int weekDayPassArrivals = 50;
    int weekendPassArrivals = 5;

    // Number of cars that can enter/leave per minute.
    int enterSpeed = 3;
    int paymentSpeed = 7;
    int exitSpeed = 5;
    
    private int numberOfSteps;
    private boolean run;
    
	// -----------------------------------------------------------
	// This is the constructor from the SimulatorView class.
	// -----------------------------------------------------------
    public SimulatorLogic(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        
        // These are from the constructor in the Simulator class.
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        
        run = false;
    }
    
	// -----------------------------------------------------------
	// These methods are from the SimulatorView class.
	// -----------------------------------------------------------  
    public int getNumberOfFloors() {
        return numberOfFloors;
    }
	
    public int getNumberOfRows() {
        return numberOfRows;
    }
    
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }
    
    public int getNumberOfOpenSpots() {
    	return numberOfOpenSpots;
    }
    
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }
    
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
    
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    
	// -----------------------------------------------------------
	// These methods are from the Simulator class.
	// -----------------------------------------------------------
    public void step() {
    	advanceTime();
    	handleExit();
    	notifyViews();
    	handleEntrance();
    }
    
    public void start(int numberOfSteps) {
    	this.numberOfSteps = numberOfSteps;
    	run = true;
    	new Thread(this).start();
    }
    
    public void stop() {
    	run = false;
    }
    
    public void run() {
    	for(int i = 0; i < numberOfSteps && run; i++) {
    		advanceTime();
    		handleExit();
    		notifyViews();
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		handleEntrance();
    	}
    	run = false;
    }
    
    private void advanceTime() {
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }
    }
    
    private void handleEntrance() {
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    private void handleExit() {
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void carsArriving() {
    	int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, REGULAR);    	
    	numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, SUBSCRIPTION);    	
    }
    
    private void carsEntering(CarQueue queue) {
        int i = 0;
    	while (queue.carsInQueue() > 0 && 
    			getNumberOfOpenSpots() > 0 && 
    			i < enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = getFirstFreeLocation();
            setCarAt(freeLocation, car);
            i++;
        }
    }
    
    private void carsReadyToLeave() {
        Car car = getFirstLeavingCar();
        while (car != null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
        }
    }
    
    private void carsPaying() {
    	int i = 0;
    	while (paymentCarQueue.carsInQueue() > 0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    private void carsLeaving() {
    	int i = 0;
    	while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend) {
        Random random = new Random();
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, String type) {
    	switch(type) {
    	case REGULAR: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new RegularCar());
            }
            break;
    	case SUBSCRIPTION:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new SubscriptionCar());
            }
            break;	            
    	}
    }
    
    private void carLeavesSpot(Car car) {
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    } 
}
