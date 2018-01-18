package parkingsimulator;

import java.util.Random;

public class Simulator {

	private static final String AD_HOC = "1";
	private static final String PASS = "2";

	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private SimulatorView simulatorView;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    // Average number of cars arriving per hour.
    int weekDayArrivals = 100;
    int weekendArrivals = 200;
    int weekDayPassArrivals = 50;
    int weekendPassArrivals = 5;

    // Number of cars that can enter/leave per minute.
    int enterSpeed = 3;
    int paymentSpeed = 7;
    int exitSpeed = 5;

    /**
     * Constructor for objects of class Simulator.
     */
    public Simulator() {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        simulatorView = new SimulatorView(3, 6, 30);
    }

    /**
     * Method that makes the simulator is run 10000 times, each 
     * single time it will call upon the method named tick().
     */
    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    /**
     * Method that handles all the needed action during a single tick in the simulator.
     */
    private void tick() {
    	advanceTime();
    	handleExit();
    	updateViews();
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }

    /**
     * Method that advances the time with one minute each time the method is 
     * called. It will go through 60 minutes, 24 hours and 7 days.
     */
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

    /**
     * Method that handles all the entrances.
     */
    private void handleEntrance() {
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    /**
     * Method that handles all the exits.
     */
    private void handleExit() {
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    /**
     * Method that updates the car park view.
     */
    private void updateViews() {
    	simulatorView.tick();
        simulatorView.updateView();	
    }
    
    /**
     * Method that sums of the number of cars arriving.
     */
    private void carsArriving() {
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);    	
    }

    /**
     * Method that removes a car from the front of the queue 
     * and assigns it to parking space.
     * 
     * @param queue
     */
    private void carsEntering(CarQueue queue) {
        int i = 0;
    	while (queue.carsInQueue() > 0 && 
    			simulatorView.getNumberOfOpenSpots() > 0 && 
    			i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = simulatorView.getFirstFreeLocation();
            simulatorView.setCarAt(freeLocation, car);
            i++;
        }
    }
    
    /**
     * Method that adds the leaving cars to the payment queue.
     */
    private void carsReadyToLeave() {
        Car car = simulatorView.getFirstLeavingCar();
        while (car != null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = simulatorView.getFirstLeavingCar();
        }
    }

    /**
     * Method that lets the exiting cars perform their payment.
     */
    private void carsPaying() {
    	int i = 0;
    	while (paymentCarQueue.carsInQueue() > 0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    /**
     * Method that lets the parked cars leave the garage.
     */
    private void carsLeaving() {
    	int i = 0;
    	while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    /**
     * Method that calculates the average number of cars per minute.
     * 
     * @param weekDay
     * @param weekend
     * @return average number of cars per minute
     */
    private int getNumberOfCars(int weekDay, int weekend) {
        Random random = new Random();
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    /**
     * Method that adds a the various types of cars to
     * the back of the queue.
     * 
     * @param numberOfCars
     * @param type
     */
    private void addArrivingCars(int numberOfCars, String type) {
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }
            break;	            
    	}
    }
    
    /**
     * Method that removes the car from its location
     * and adds it to the queue for exiting cars.
     * 
     * @param car
     */
    private void carLeavesSpot(Car car) {
    	simulatorView.removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
}
