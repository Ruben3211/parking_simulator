package logic;

import java.util.*;

public class SimulatorLogic extends AbstractModel implements Runnable {

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private Car[][][] cars;

	private static final String REGULAR = "1";
	private static final String SUBSCRIPTION = "2";
	private static final String RESERVATION = "3";

	private CarQueue entranceRegQueue;
    private CarQueue entranceSubResQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private int entranceRegQueueMax = 20;
    private int entranceSubResQueueMax = 20;

    // used for time, needed for certain views.
    private String[] weekDay = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int stepPause = 100;

    // Average number of cars arriving per hour.
    private int weekDayRegArrivals = 100;
    private int weekendRegArrivals = 200;
    private int eventRegArrivals = 0; // used on Thursday/Friday/Saturday night 18:00 - 24:00 and Sunday afternoon 12:00 - 18:00.
    private int weekDaySubArrivals = 50;
    private int weekendSubArrivals = 5;
    private int eventSubArrivals = 0; // used on Thursday/Friday/Saturday night 18:00 - 24:00 and Sunday afternoon 12:00 - 18:00.
    private int weekDayResArrivals = 50;
    private int weekendResArrivals = 5;
    private int eventResArrivals = 0; // used on Thursday/Friday/Saturday night 18:00 - 24:00 and Sunday afternoon 12:00 - 18:00.

    // Number of cars that can enter/leave per minute.
    private int enterSpeed = 3; // TODO: enterspeed says 3, but somethimes 4 enter.
    private int paymentSpeed = 7;
    private int exitSpeed = 5;
    
    // The prices the various cars have to pay.
    private int regPaymentAmount = 15;
    private int subPaymentAmount = 30;
    private int resPaymentAmount = 20;

    private int totalRegPaymentAmount;
    private int totalSubPaymentAmount;
    private int totalResPaymentAmount;

    // max number of cars allowed at once.
    private int maxSubAllowed = 60;
    private int maxResAllowed = 60;

    // lists the number of cars per type that left, because the queues were too long.
    private int numMissedReg;
    private int numMissedSub;
    private int numMissedRes;
    
    private int numberOfSteps;
    private boolean run;

    private int numParkedRegCars;
    private int numParkedResCars;
    private int numParkedSubCars;

    public SimulatorLogic(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        
        entranceRegQueue = new CarQueue();
        entranceSubResQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        
        run = false;
    }
     
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
                    if(car != null)
                    {
                    	if(car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                    		return car;
                    	}
                    }
                }
            }
        }
        return null;
    }
    
    public void tickCars() {
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
    
    public void step() {
    	advanceTime();
    	handleExit();
    	tickCars();
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
    		tickCars();
    		notifyViews();
    		try {
    			Thread.sleep(stepPause);
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
    	carsEntering(entranceSubResQueue);
    	carsEntering(entranceRegQueue);  	
    }
    
    private void handleExit() {
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    private int getMissedCars(CarQueue queue, int numCars, int maxCars)
    {
    	int spaceLeft = maxCars - queue.carsInQueue();
    	if(spaceLeft < numCars)
    		return numCars - spaceLeft;
    	return 0;
    }

    private void carsArriving()
    {
    	int numberOfCars, numberOfMissedCars;

    	numberOfCars = getNumberOfCarsArriving(weekDayRegArrivals, weekendRegArrivals, eventRegArrivals);
    	numberOfMissedCars = getMissedCars(entranceRegQueue, numberOfCars, entranceRegQueueMax);
    	for (int i = 0; i < numberOfCars - numberOfMissedCars; i++) {
        	entranceRegQueue.addCar(new RegularCar());
        }
    	numMissedReg += numberOfMissedCars;

        numberOfCars = getNumberOfCarsArriving(weekDaySubArrivals, weekendSubArrivals, eventSubArrivals);
    	numberOfMissedCars = getMissedCars(entranceSubResQueue, numberOfCars, entranceSubResQueueMax);
        for (int i = 0; i < numberOfCars - numberOfMissedCars; i++) {
    		entranceSubResQueue.addCar(new SubscriptionCar());
        }
    	numMissedSub += numberOfMissedCars;
 
        numberOfCars = getNumberOfCarsArriving(weekDayResArrivals, weekendResArrivals, eventResArrivals);
    	numberOfMissedCars = getMissedCars(entranceSubResQueue, numberOfCars, entranceSubResQueueMax);
   		for (int i = 0; i < numberOfCars - numberOfMissedCars; i++) {
   			entranceSubResQueue.addCar(new ReservationCar());
    	}
    	numMissedRes += numberOfMissedCars;
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

            String carType = car.getType();
            if(carType == "1") {
            	this.numParkedRegCars++;
            } else if(carType == "2") {
            	this.numParkedSubCars++;
            } else if(carType ==  "3") {
            	this.numParkedResCars++;
            }
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
            String carType = car.getType();
            // TODO Handle payment.
            if(car.getHasToPay())
            {
            	if(carType == "1") {
            	    this.totalRegPaymentAmount += this.regPaymentAmount;
            	} else if(carType == "3") { 
            	    this.totalResPaymentAmount += this.resPaymentAmount;
            	}
            }
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
    
    private int getNumberOfCarsArriving(int weekDay, int weekend, int event)
    {
        Random random = new Random();

        int averageNumberOfCarsPerHour;

        // Thursday/Friday/Saturday 18:00-24:00 and Sunday 12:00-18:00.
        if((day > 2 && day < 6 && hour >= 18) ||
           (day == 6 && hour >= 12 && hour < 18))
        {
        	averageNumberOfCarsPerHour = event;
        }
        else if(day < 5)
        	averageNumberOfCarsPerHour = weekDay;
        else
        	averageNumberOfCarsPerHour = weekend;

        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }    

    private void carLeavesSpot(Car car)
    {
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);

        String carType = car.getType();
        if(carType == "1") {
        	this.numParkedRegCars--;
        } else if(carType == "2") {
        	this.numParkedSubCars--;
        } else if(carType ==  "3") {
        	this.numParkedResCars--;
        }
    } 
}
