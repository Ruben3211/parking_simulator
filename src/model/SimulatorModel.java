package model;

import java.io.File;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class is the "database" of the application. It holds all the variables
 * that keep track of various data points needed for the controllers and views.
 * These variables or changed and retrieved via getters and setters, listed in
 * this class. It also contains all the method needed to handle all the processes
 * needed to run the simulator like adding cars, removed cars and calculating 
 * time for various cars. It also contains method for running, stopping and
 * resetting the application.
 * 
 * @author Ruben Bonga, Joey Kroes, Detmer Struiksma & Rick Zwaneveld
 * @version 03-02-2018
 */

public class SimulatorModel extends AbstractModel implements Runnable {

    private Random random;

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private ParkingSpace[][][] spaces;

    private ArrayList<Reservation> reservationList;

	private CarQueue entranceOneQueue;
    private CarQueue entranceTwoQueue;
    private CarQueue paymentQueue;
    private CarQueue exitQueue;

    private int	maxEntranceQueue;
    
    private int entranceSpeed;
    private int paymentSpeed;
    private int exitSpeed;

    private String[] weekDay = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private int day;
    private int hour;
    private int minute;

    private int weekDayRegularArrivals;
    private int weekendRegularArrivals;
    private int eventRegularArrivals;
    private int weekDaySubscriptionArrivals;
    private int weekendSubscriptionArrivals;
    private int eventSubscriptionArrivals;
    private int weekDayReservationArrivals;
    private int weekendReservationArrivals;
    private int eventReservationArrivals;
    
	private int maxSubscriptions;
    private int maxReservations;

    private int regularFee;
    private int subscriptionFee;
    private int reservationFee;

    private int totalRegularIncome;
    private int totalSubscriptionIncome;
    private int totalReservationIncome;
	private int totalIncome;
	
    private ArrayList<Integer> totalIncomeData;

    private int totalParkedRegular;
    private int totalParkedSubscription;
    private int totalParkedReservation;
    
    private int parkedRegularIncome;
    private int parkedReservationIncome;
    private int parkedTotalIncome;
    
    private int totalRegularMissed;
    private int totalReservationMissed;
    private int totalCarsMissed;
    
    private int missedRegularIncome;
    private int missedReservationIncome;
    private int missedTotalIncome;

    private int stepPause;
    private int numberOfSteps;
    private boolean run;

    /**
     * The constructor for the class SimulatorModel.
     * 
     * @param numberOfFloors the number of floors
     * @param numberOfRows the number of rows
     * @param numberOfPlaces the number of places
     */
    public SimulatorModel(int numberOfFloors, int numberOfRows, int numberOfPlaces)
    {
        random = new Random();

        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        
        spaces = new ParkingSpace[numberOfFloors][numberOfRows][numberOfPlaces];

        for(int floor = 0; floor < numberOfFloors; floor++) {
            for(int row = 0; row < numberOfRows; row++) {
                for(int place = 0; place < numberOfPlaces; place++) {
                	spaces[floor][row][place] = new ParkingSpace();
                }
            }
        }
        initialization();
        reset();
    }
    
    /**
     * This method is responsible for initializing all the data the simulator
     * needs to setup in order to run correctly. This is done within the
     * SimulatorModel constructor. This method is also used to reset the data in
     * the DataController, back to the initial values.
     */
   	public void initialization() {
   		regularFee = 1;
   		subscriptionFee = 50;
   		reservationFee = 6;
   		entranceSpeed = 3; 
   		paymentSpeed = 7;
   		exitSpeed = 5;
   		weekDayRegularArrivals = 100;
   		weekendRegularArrivals = 70;
   		eventRegularArrivals = 300; 
   		weekDaySubscriptionArrivals = 50;
   		weekendSubscriptionArrivals = 5;
   		eventSubscriptionArrivals = 20; 
   		weekDayReservationArrivals = 20;
   		weekendReservationArrivals = 50;
   		eventReservationArrivals = 300;
   		maxReservations = 250;
   		maxSubscriptions = 56;
   		maxEntranceQueue = 6;
   	}
    
    /**
     * This method will start running the simulator. The length of the runtime
     * is determined by the amount of steps given. When the start signal is
     * given, a new thread will be created.
     * 
     * @param numberOfSteps the amount of steps the simulator will go through
     */
    public void start(int numberOfSteps) {
    	this.numberOfSteps = numberOfSteps;
    	run = true;
    	new Thread(this).start();
    }
    
    /**
     * This method is responsible for running the simulator when the start() 
     * method is called upon. It will execute the numberOfSteps given. With each
     * step it will go through the firstAction() and secondAction() methods and
     * updates the observers.
     */
    public void run() {
    	for(int i = 0; i < numberOfSteps && run; i++) {
    		firstAction();
    		try {
    			Thread.sleep(stepPause);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		secondAction();
    		notifyObservers();
    	}
    	run = false;
    }
    
    /**
     * This method is used to stop the simulator from running.
     */
    public void stop() {
    	run = false;
    }
    
    /**
     * This method is used to reset the entire simulator to its default state.
     */
    public void reset() {
    	numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
    	
    	reservationList = new ArrayList<Reservation>();

    	entranceOneQueue = new CarQueue();
        entranceTwoQueue = new CarQueue();
        paymentQueue = new CarQueue();
        exitQueue = new CarQueue();

        day = 0;
        hour = 0;
        minute = 0;

        totalRegularIncome = 0;
        totalSubscriptionIncome = 0;
        totalReservationIncome = 0;
    	totalIncome = 0;

        totalIncomeData = new ArrayList<Integer>();
    	
        totalParkedRegular = 0;
        totalParkedSubscription = 0;
        totalParkedReservation = 0;

        parkedRegularIncome = 0;
        parkedReservationIncome = 0;
        parkedTotalIncome = 0;

        totalRegularMissed = 0;
        totalReservationMissed = 0;
        totalCarsMissed = 0;

        missedRegularIncome = 0;
        missedReservationIncome = 0;
        missedTotalIncome = 0;

        totalParkedRegular = 0;
        totalParkedSubscription = 0;
        totalParkedReservation = 0;
        
        stepPause = 100;
        numberOfSteps = 0;
        run = false;
        
        resetSpaces();
		notifyObservers();
    }

    /**
     * This method is responsible for resetting all the parking spaces. It first
     * removes all spaces and any parked cars. After which it will allocate the
     * subscription parking spaces.
     */
    private void resetSpaces() {
        // Sets all places to regular places and removes parked cars.
    	for(int floor = 0; floor < numberOfFloors; floor++) {
            for(int row = 0; row < numberOfRows; row++) {
                for(int place = 0; place < numberOfPlaces; place++) {
                	ParkingSpace space = spaces[floor][row][place];
                	space.setType("regular");
                	space.setCar(null);
                }
            }
        }
    	// Allocates the subscription parking spaces.
        int floor = 0, row = 0, place = 0;
        for(int spaceIndex = 0; spaceIndex < maxSubscriptions; spaceIndex++) {
        	spaces[floor][row][place].setType("subscription");
        	place++;
        	if(place >= numberOfPlaces) {
        		place = 0;
        		row++;
        		if(row >= numberOfRows) {
        			row = 0;
        			floor++;
        		}
        	}
        }
    }
    
    /**
     * This method consists of multiple sets of methods that need to be executed
     * during the run of the simulation. These methods keep track of various
     * different values. This method is called upon in the run() method.
     */
    private void firstAction() {
    	advanceTime();
    	checkReservations();
    	carsArriving();
    	carsReadyToLeave();
    	carsPaying();
    	setSubscriptionIncome();
    	setParkedIncome();
    	setTotalIncome();
    	setTotalCarsMissed();
    	setMissedIncome();
    	tickCars();
    }
    
    /**
     * This method is has the same function as the firstAction() method. These
     * actions need to be taken separately for the simulator to function
     * properly. This method is also called upon in the run() method.
     */
    private void secondAction() {
    	carsLeaving();
    	carsEntering(entranceOneQueue);
    	carsEntering(entranceTwoQueue);
    	makeReservations();
    }
    
    /**
     * This method returns a parking space location. It first checks if the
     * location is valid or not. If it is valid it return the floor, row and
     * place of the space.
     * 
     * @param location the location that needs to be checked
     * @return null if the location is invalid, the parking space if it is valid
     */
	public ParkingSpace getParkingSpaceAt(Location location) {
		if(!locationIsValid(location)) {
			return null;
		}
		return spaces[location.getFloor()][location.getRow()][location.getPlace()];
	}

	/**
	 * This method retrieves the car located at a location. If the space is
	 * empty null is returned, otherwise the car is returned.
	 * 
	 * @param location the location that needs to be checked
	 * @return the car parked at the checked location, null if the space is empty
	 */
	private Car getCarAt(Location location) {
    	ParkingSpace space = getParkingSpaceAt(location);
    	if(space == null) {
    		return null;
    	}
   		return space.getCar();
    }

	/**
	 * This method is responsible for setting a car at a location. If a car is
	 * successfully placed, the numberOfOpenSpots is decreased.
	 * 
	 * @param location the location a car will be placed at
	 * @param car the car that needs to be placed
	 * @return true if a car is placed, false if their is no space
	 */
    private boolean setCarAt(Location location, Car car) {
    	ParkingSpace space = getParkingSpaceAt(location);
    	if(space == null) {
    		return false;
    	}
        Car oldCar = space.getCar();
        if(oldCar == null) {
        	space.setCar(car);
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    /**
     * This method removes a car from a specific location. If the location has
     * no space or no car, null is returned. If the location has a car, the car
     * is removed and the numberOfOpenSpots is increased.
     * 
     * @param location the location of the removed car
     * @return null if their is no car or no space, the car is one is found
     */
    private Car removeCarAt(Location location) {
    	ParkingSpace space = getParkingSpaceAt(location);
    	if(space == null) {
    		return null;
    	}
        Car car = space.getCar();
        if(car == null) {
            return null;	
        }
        space.setCar(null);
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    /**
     * This method returns the number of free parking spaces available. It will
     * loop through all the location to do so.
     * 
     * @param type the space type
     * @return count the number of free parking spaces
     */
    private int countFreeParkingSpaces(String type) {
    	int count = 0;
        for(int floor = 0; floor < getNumberOfFloors(); floor++) {
            for(int row = 0; row < getNumberOfRows(); row++) {
                for(int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    ParkingSpace space = getParkingSpaceAt(location);
                    if(space != null && space.getType() == type) {
                    	if(space.getCar() == null) {
                    		count++;
                    	}
                    }
                }
            }
        }
        return count;	
    }

    /**
     * This method looks for the first free parking space. It uses a nested
     * for-loop to go through all the locations in the garage.
     * 
     * @param type the space type
     * @return location if one is free, null otherwise
     */
    private Location getFirstFreeParkingSpace(String type) {
        for(int floor = 0; floor < getNumberOfFloors(); floor++) {
            for(int row = 0; row < getNumberOfRows(); row++) {
                for(int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    ParkingSpace space = getParkingSpaceAt(location);
                    if(space != null && space.getType() == type) {
                    	if(space.getCar() == null) {
                    		return location;
                    	}
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * This method uses a nested for-loop to go through all the location to find
     * the first car that is leaving the garage.
     * 
     * @return car the first car found, null if no car is found
     */
    private Car getFirstLeavingCar() {
        for(int floor = 0; floor < getNumberOfFloors(); floor++) {
            for(int row = 0; row < getNumberOfRows(); row++) {
                for(int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if(car != null) {
                    	if(car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                    		return car;
                    	}
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * This method loops through all the location and gets the car parked at
     * each one provided a car is parked on that location. If a car is parked
     * the method will call upon the car.tick() method to decrease the parking 
     * time of the car.
     */
    private void tickCars() {
        for(int floor = 0; floor < getNumberOfFloors(); floor++) {
            for(int row = 0; row < getNumberOfRows(); row++) {
                for(int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if(car != null) {
                        car.tick();
                    }
                }
            }
        }
    }
    
    /**
     * This method will check if a location is valid or not.
     * 
     * @param location the location to be checked
     * @return false if the location is invalid, true if the location is valid
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if(floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    
    /**
     * This method advances the time by one minute each time the method is
     * called upon. It will loop through 60 minutes, 24 hours and 7 days.
     */
    private void advanceTime() {
        minute++;
        while(minute > 59) {
            minute -= 60;
    		totalIncomeData.add(totalIncome);
            hour++;
        }
        while(hour > 23) {
            hour -= 24;
            day++;
        }
        while(day > 6) {
            day -= 7;
        }
    }

    /**
     * This method is used to update all the total parked income for regular
     * cars, reservation cars and the income of these two combined.
     */
    private void setParkedIncome() {
        parkedRegularIncome = totalParkedRegular * regularFee;
        parkedReservationIncome = totalParkedReservation * reservationFee;
        parkedTotalIncome = parkedRegularIncome + parkedReservationIncome;
    }

    /**
     * This method calculates the number of missed cars. When the queue length
     * gets to big, cars will leave.
     * 
     * @param queue the queue that is going to be checked
     * @param numCars the cars within the queue
     * @param maxCars the max number of cars allowed within a queue
     * @return number of cars missed, if no cars are missed it returns 0
     */
    private int getMissedCars(CarQueue queue, int numCars, int maxCars) {
    	int spaceLeft = maxCars - queue.carsInQueue();
    	if(spaceLeft < numCars) {
    		return numCars - spaceLeft;
    	}
    	return 0;
    }

    /**
     * This method is responsible for the arriving regular cars and subscription
     * cars. It also keeps track of the missed regular cars and the missed regular
     * income.
     */
    private void carsArriving() {
    	int numberOfCars, numberOfMissedCars;

    	// Regular cars.
    	numberOfCars = getNumberOfCarsArriving(weekDayRegularArrivals, weekendRegularArrivals, eventRegularArrivals);
    	numberOfMissedCars = getMissedCars(entranceOneQueue, numberOfCars, maxEntranceQueue);
    	for(int i = 0; i < numberOfCars - numberOfMissedCars; i++) {
        	entranceOneQueue.addCar(new RegularCar());
        }
    	totalRegularMissed += numberOfMissedCars;
        missedRegularIncome = totalRegularMissed * regularFee;

    	// Subscription cars.
        numberOfCars = getNumberOfCarsArriving(weekDaySubscriptionArrivals, weekendSubscriptionArrivals, eventSubscriptionArrivals);
        int spaceLeft = maxSubscriptions - totalParkedSubscription;
        if(spaceLeft > 0) {
        	if(numberOfCars > spaceLeft) {
        		numberOfCars = spaceLeft;
        	}
        	numberOfMissedCars = getMissedCars(entranceTwoQueue, numberOfCars, maxEntranceQueue);
        	for(int i = 0; i < numberOfCars - numberOfMissedCars; i++) {
        		entranceTwoQueue.addCar(new SubscriptionCar());
        	}
        }
    }

    /**
     * This method is used to calculate the arrival time for the reservation
     * cars. It sets multiple variables and uses a do-while loop to calculate
     * the value needed.
     * 
     * @return value the calculated time for a reservation to arrive
     */
    private int computeReservationArrivalTime() {
    	double value;
    	double min = 2.0;
    	double average = 15.0;
    	double standardDeviation = 50.0;
    	do {
    	  value = random.nextGaussian() * standardDeviation + average;
    	} 
    	while(value <= min);
    	return (int)Math.round(value);
    }

    /**
     * This method is responsible for making reservations. It checks how many cars
     * should arrive, and the amount of reservations allowed. It also keeps track
     * of the amount of missed reservations and the missed income. When a
     * reservation is made, it is added to the reservation list.
     */
    private void makeReservations() {
    	int numberOfReservations = getNumberOfCarsArriving(weekDayReservationArrivals, weekendReservationArrivals, eventReservationArrivals);
    	int numberOfMissedReservations = 0;
    	int numberOfFreeSpaces = countFreeParkingSpaces("regular");

    	int numberOfAdditionalReservationsAllowed = maxReservations - reservationList.size() - totalParkedReservation;

    	if(numberOfReservations > numberOfAdditionalReservationsAllowed) {
    		numberOfMissedReservations += numberOfReservations - numberOfAdditionalReservationsAllowed;
    		numberOfReservations = numberOfAdditionalReservationsAllowed;
    	}
    	if(numberOfReservations > numberOfFreeSpaces) {
    		numberOfMissedReservations += numberOfReservations - numberOfFreeSpaces;
    		numberOfReservations = numberOfFreeSpaces;
    	}

    	totalReservationMissed += numberOfMissedReservations;
    	missedReservationIncome = totalReservationMissed * reservationFee;

    	int curMinute = 24*60*day + 60*hour + minute;

    	for(int i = 0; i < numberOfReservations; i++) {
            Location freeLocation = getFirstFreeParkingSpace("regular");

            int timeOfArrival = computeReservationArrivalTime();
            int timeOfExpiration = curMinute + 30;
            Reservation reservation = new Reservation(freeLocation, timeOfArrival, timeOfExpiration);

            reservationList.add(reservation);

            ParkingSpace space = getParkingSpaceAt(freeLocation);
            space.setType("reservation");
    	}
    }

    /**
     * This method checks the reservations. It checks the reservation list and
     * decides what action to take depending on the situation. If the arrival time
     * is under or equal to the curMinute, the car will be added to the queue and
     * added to the garage. If the expiration time is under or equal to the curMinute
     * the reservation will be removed from the parking garage.
     */
    private void checkReservations() {
    	int curMinute = 24 * 60 * day + 60 * hour + minute;

	    Iterator<Reservation> iterator = reservationList.iterator();
	    while(iterator.hasNext()) {
	    	Reservation reservation = iterator.next();

	    	if(reservation.getArrivalTime() <= curMinute) {
	    		ReservationCar car = new ReservationCar();
	    		entranceTwoQueue.addCar(car);
	    		car.setLocation(reservation.getLocation());
				iterator.remove();
	    	}
	    	else if(reservation.getExpirationTime() <= curMinute) {
	    		ParkingSpace space = getParkingSpaceAt(reservation.getLocation());
	    		space.setType("regular");
	    		iterator.remove();
	    	}
	    }
    }

    /**
     * This method is responsible for removing cars from a queue and adding them
     * to a parking location. It is also responsible for keeping track of how 
     * many cars are currently parked for each car type.
     * 
     * @param queue the queue that needs to be checked
     */
    private void carsEntering(CarQueue queue) {
        int i = 0;
    	while(queue.carsInQueue() > 0 && i < entranceSpeed) {
    		Car car = queue.peekCar();

            Location freeLocation;
            if(car.getType() == "subscription") {
            	freeLocation = getFirstFreeParkingSpace("subscription");
            }
            else if(car.getType() == "reservation") {
            	freeLocation = car.getLocation();
            }
            else {
            	freeLocation = getFirstFreeParkingSpace("regular");
            }
            if(freeLocation == null) {
            	break;
            }

            car = queue.removeCar();
            setCarAt(freeLocation, car);
            i++;

            String carType = car.getType();
            if(carType == "regular") {
            	totalParkedRegular++;
            } 
            else if(carType == "subscription") {
            	totalParkedSubscription++;
            } 
            else if(carType ==  "reservation") {
            	totalParkedReservation++;
            }
        }
    }

    /**
     * This method checks whether a car is ready to leave the garage. It checks
     * whether a car has to pay or not. If a car has to pay it is added to the
     * payment queue. If a car does not have to pay, it is added to the exit
     * queue via the carLeaveSpot() method. 
     */
    private void carsReadyToLeave() {
        Car car = getFirstLeavingCar();
        while(car != null) {
        	if(car.getHasToPay()) {
	            car.setIsPaying(true);
	            paymentQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
        }
    }
    
    /**
     * This method uses a while loop to handle the cars within the paymentQueue.
     * It check whether the car is a regular or reservation car and takes the
     * appropriate addition depending on the type. It calls on the carLeavesSpot()
     * to add the cars to the exit queue.
     */
    private void carsPaying() {
    	int i = 0;
    	while(paymentQueue.carsInQueue() > 0 && i < paymentSpeed) {
            Car car = paymentQueue.removeCar();
            String carType = car.getType();
            if(car.getHasToPay()) {
            	if(carType == "regular") {
            	    this.totalRegularIncome += this.regularFee;
            	} 
            	else if(carType == "reservation") { 
            	    this.totalReservationIncome += this.reservationFee;
            	}
            }
            carLeavesSpot(car);
            i++;
    	}
    }

    /**
     * This method is responsible for going through the exit queue and removing
     * cars within it, one at a time.
     */
    private void carsLeaving() {
    	int i = 0;
    	while(exitQueue.carsInQueue() > 0 && i < exitSpeed){
            exitQueue.removeCar();
            i++;
    	}	
    }

    /**
     * This method changes the value of the averageNumberOfCarsPerHour to arrive
     * in the garage. It does this based on certain times of the day and week.
     * At night less cars come in. During events more cars will come in.
     * 
     * @param weekDay the average amount of cars arriving on a weekday
     * @param weekend the average amount of cars arriving during the weekend
     * @param event the average amount of cars arriving during an event
     * @return the average amount of cars arriving
     */
    private int getNumberOfCarsArriving(int weekDay, int weekend, int event) {
        int averageNumberOfCarsPerHour = 0;
        if(hour < 7) {
        	if(weekDay > 0 && weekend > 0 && event > 0) {
            	averageNumberOfCarsPerHour = 20;
        	}
        }
        else if((day > 2 && day < 6 && hour >= 18) || (day == 6 && hour >= 12 && hour < 18)) {
        	averageNumberOfCarsPerHour = event;
        }
        else if(day < 5) {
        	averageNumberOfCarsPerHour = weekDay;
        }
        else {
        	averageNumberOfCarsPerHour = weekend;
        }
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    /**
     * This method makes sure a car leaves their parking spot. If a car is to
     * be removed, it is added to the exit queue. If a car is removed the car
     * counter for that type of car will be decreased.
     * 
     * @param car the car that is to leave a spot
     */
    private void carLeavesSpot(Car car) {
    	Location location = car.getLocation();

    	removeCarAt(location);
        exitQueue.addCar(car);

        String carType = car.getType();
        if(carType == "regular") {
        	totalParkedRegular--;
        } 
        else if(carType == "subscription") {
        	totalParkedSubscription--;
        } 
        else if(carType ==  "reservation") {
        	totalParkedReservation--;
        	ParkingSpace space = getParkingSpaceAt(location);
        	space.setType("regular");
        }
    }
    
    /**
     * This method is responsible for playing sounds. It can be called upon
     * where ever the application needs to play a sound, by simple adding
     * playSound() to that place.
     */
    public void playSound() {
    	try {
    		Clip clip = AudioSystem.getClip();
    		clip.open(AudioSystem.getAudioInputStream(new File("res/audio/knob.wav")));
    		clip.start();
    	} catch (Exception e) {
			e.printStackTrace(System.out);
		}
    }
   	
    /**
     * This method is used for the DataController. It will check the name and
     * pass the given value to the right variable within the model. This way
     * the DataController can update the data. 
     * 
     * @param objectName the name of the variable that needs to be changed.
     * @param value the value that needs to be given to the variable
     */
   	public void setIntFromDataController(String objectName, int value) {
   		switch(objectName) {
	   		case "regularFeeData":			regularFee = value;						break;
	   		case "subscriptionFeeData":		subscriptionFee = value;				break;
	   		case "reservationFeeData":		reservationFee = value;					break;
	   		case "entranceSpeedData":		entranceSpeed = value;					break;
	   		case "paymentSpeedData":		paymentSpeed = value;					break;
	   		case "exitSpeedData":			exitSpeed = value;						break;
	   		case "regularWeekDayData":		weekDayRegularArrivals = value;			break;
	   		case "regularWeekendData":		weekendRegularArrivals = value;			break;
	   		case "regularEventData":		eventRegularArrivals = value;			break;
	   		case "subscriptionWeekDayData":	weekDaySubscriptionArrivals = value;	break;
	   		case "subscriptionWeekendData":	weekendSubscriptionArrivals = value;	break;
	   		case "subscriptionEventData":	eventSubscriptionArrivals = value;		break;
	   		case "reservationWeekDayData":	weekDayReservationArrivals = value;		break;
	   		case "reservationWeekendData":	weekendReservationArrivals = value;		break;
	   		case "reservationEventData":	eventReservationArrivals = value;		break;
	   		case "maxSubscriptionsData":	maxSubscriptions = value;				break;
	   		case "maxReservationsData":		maxReservations = value;				break;
	   		case "maxEntranceQueueData":	maxEntranceQueue = value;				break;
   		}
   	}

    /**
     * This method returns the total number of floors that exists in the
     * parking garage.
     * 
     * @return numberOfFloors the total number of floors in the garage
     */
    public int getNumberOfFloors() {
    	return numberOfFloors;
   	}
	
    /**
     * This method returns the total number of rows that exists in the
     * parking garage.
     * 
     * @return numberOfRows the total number of rows in the garage
     */
    public int getNumberOfRows() {
    	return numberOfRows;
   	}
    
    /**
     * This method returns the total number of places that exists in the
     * parking garage.
     * 
     * @return numberOfPlaces the total number of places in the garage
     */
   	public int getNumberOfPlaces() {
   		return numberOfPlaces;
   	}
   	
    /**
     * This method returns the total amount of open spots within the garage.
     * These spots have no cars parked within them.
     * 
     * @return numberOfOpenSpots the amount of spots with no cars parked in it
     */
    public int getNumberOfOpenSpots() {
    	return numberOfOpenSpots;
    }
    
    /**
     * This method will retrieve data from the entranceOneQueue. With the methods
     * defined in the CarQueue class, this will allow access to the objects
     * within this particular queue.
     * 
     * @return entranceOneQueue a LinkedList with objects within it
     */
    public CarQueue getEntranceOneQueue() {
    	return entranceOneQueue;
   	}
    
    /**
     * This method will retrieve data from the entranceTwoQueue. With the methods
     * defined in the CarQueue class, this will allow access to the objects
     * within this particular queue.
     * 
     * @return entranceTwoQueue a LinkedList with objects within it
     */
   	public CarQueue getEntranceTwoQueue() {
   		return entranceTwoQueue;
   	}
   	
    /**
     * This method will retrieve data from the paymentQueue. With the methods
     * defined in the CarQueue class, this will allow access to the objects
     * within this particular queue.
     * 
     * @return paymentQueue a LinkedList with objects within it
     */
   	public CarQueue getPaymentQueue() {
   		return paymentQueue;
  	}
   	
    /**
     * This method will retrieve data from the exitQueue. With the methods
     * defined in the CarQueue class, this will allow access to the objects
     * within this particular queue.
     * 
     * @return exitQueue a LinkedList with objects within it
     */
   	public CarQueue getExitQueue() {
   		return exitQueue;
   	}
   	
   	/**
   	 * This method will return the maximum allowed number with both entrance
   	 * queues. When this number is reached, the people above this number will
   	 * leave the queue.
   	 * 
   	 * @return maxEntranceQueue the allowed length of the entrance queue
   	 */
   	public int getMaxEntranceQueue() {
   		return maxEntranceQueue;
   	}
    
   	/**
   	 * This method will return the amount of cars going through the entrance
   	 * queues every minute.
   	 * 
   	 * @return entranceSpeed the amount of cars entering per minute
   	 */
    public int getEntranceSpeed() {
    	return entranceSpeed;
    }
    
   	/**
   	 * This method will return the amount of cars going through the payment
   	 * queue every minute.
   	 * 
   	 * @return paymentSpeed the amount of cars paying per minute
   	 */
    public int getPaymentSpeed() {
    	return paymentSpeed;
    }
    
   	/**
   	 * This method will return the amount of cars going through the exit
   	 * queue every minute.
   	 * 
   	 * @return exitSpeed the amount of cars exiting per minute
   	 */
    public int getExitSpeed() {
    	return exitSpeed;
    }
   
    /**
     * This method returns a string with the current weekday. Which day it is, 
     * is calculated by using the day in numbers and a modulo, this number will
     * correspond with the position of the day within the string array.
     * 
     * @return String a string with the current weekday
     */
    public String getDay() {
    	return weekDay[day % 7];
    }
    
    /**
     * This method will create a string for the minutes and hours. If the minutes 
     * and hours are under 10, a 0 will be appended to the number. This will make 
     * sure that the time is displayed in a 24 hour (00:00) format.
     * 
     * @return String a string with the hours and minutes in a 24 hour format
     */
    public String getTime() {
    	String stringMinute = ("" + minute);
    	String stringHour = ("" + hour);
    	if(minute < 10) {
    		stringMinute = ("0" + stringMinute);
    	}
    	if(hour < 10) {
    		stringHour = ("0" + stringHour);
    	}
    	return (stringHour + ":" + stringMinute);
    }
    
    /**
     * This method will return the average amount of regular cars that will
     * arrive in the garage during the weekdays.
     * 
     * @return weekDayRegularArrivals the amount of regular cars arriving
     */
    public int getWeekDayRegularArrivals() {
    	return weekDayRegularArrivals;
    }
    
    /**
     * This method will return the average amount of regular cars that will
     * arrive in the garage during the weekends.
     * 
     * @return weekendRegularArrivals the amount of regular cars arriving
     */
    public int getWeekendRegularArrivals() {
    	return weekendRegularArrivals;
    }
    
    /**
     * This method will return the average amount of regular cars that will
     * arrive in the garage during events. These events are at Thursday/Friday
     * and Saturday between 18:00 and 24:00 and on Sunday afternoon between
     * 12:00 and 18:00.
     * 
     * @return eventRegularArrivals the amount of regular cars arriving
     */
    public int getEventRegularArrivals() {
    	return eventRegularArrivals;
    }
    
    /**
     * This method will return the average amount of subscription cars that will
     * arrive in the garage during the weekdays.
     * 
     * @return weekDaySubscriptionArrivals the amount of subscription cars arriving
     */
    public int getWeekDaySubscriptionArrivals() {
    	return weekDaySubscriptionArrivals;
    }
    
    /**
     * This method will return the average amount of subscription cars that will
     * arrive in the garage during the weekends.
     * 
     * @return weekendSubscriptionArrivals the amount of subscription cars arriving
     */
    public int getWeekendSubscriptionArrivals() {
    	return weekendSubscriptionArrivals;
    }
    
    /**
     * This method will return the average amount of reservation cars that will
     * arrive in the garage during events. These events are at Thursday/Friday
     * and Saturday between 18:00 and 24:00 and on Sunday afternoon between
     * 12:00 and 18:00.
     * 
     * @return eventReservationArrivals the amount of reservation cars arriving
     */
    public int getEventSubscriptionArrivals() {
    	return eventSubscriptionArrivals;
    }
    
    /**
     * This method will return the average amount of reservation cars that will
     * arrive in the garage during the weekdays.
     * 
     * @return weekDayReservationArrivals the amount of reservation cars arriving
     */
    public int getWeekDayReservationArrivals() {
    	return weekDayReservationArrivals;
    }
    
    /**
     * This method will return the average amount of reservation cars that will
     * arrive in the garage during the weekends.
     * 
     * @return weekendReservationArrivals the amount of reservation cars arriving
     */
    public int getWeekendReservationArrivals() {
    	return weekendReservationArrivals;
    }
    
    /**
     * This method will return the average amount of reservation cars that will
     * arrive in the garage during events. These events are at Thursday/Friday
     * and Saturday between 18:00 and 24:00 and on Sunday afternoon between
     * 12:00 and 18:00.
     * 
     * @return eventReservationArrivals the amount of reservation cars arriving
     */
    public int getEventReservationArrivals() {
    	return eventReservationArrivals;
    }
    
    /**
     * This method returns the total amount of subscription allowed to exist
     * during a simulation.
     * 
     * @return maxSubscriptions the amount of subscription allowed to exist
     */
    public int getMaxSubscriptions() {
    	return maxSubscriptions;
    }
    
    /**
     * This method returns the total amount of reservation allowed to exist
     * during a simulation.
     * 
     * @return maxReservations the amount of reservation allowed to exist
     */
    public int getMaxReservations() {
    	return maxReservations;
    }
    
    /**
     * This method returns the fee regular cars have to pay when leaving the
     * garage.
     * 
     * @return regularFee the fee that regular cars have to pay
     */
    public int getRegularFee() {
    	return regularFee;
    }
    
    /**
     * This method returns the fee subscription cars have to pay once every
     * week.
     * 
     * @return subscriptionFee the fee that subscription cars have to pay
     */
    public int getSubscriptionFee() {
    	return subscriptionFee;
    }
    
    /**
     * This method returns the fee reservation cars have to paying, on top of
     * the regular fee, when leaving the garage.
     * 
     * @return reservationFee the fee that reservation cars have to pay
     */
    public int getReservationFee() {
    	return reservationFee;
    }
    
   	/**
   	 * This method will return the total amount of income earned from the
   	 * regular cars.
   	 * 
   	 * @return totalRegularIncome a number with the total regular income
   	 */
   	public int getTotalRegularIncome() {
		return totalRegularIncome;
	}
    
   	/**
   	 * This method will return the total amount of income earned from the
   	 * subscription cars.
   	 * 
   	 * @return totalSubscriptionIncome a number with the total subscription income
   	 */
	public int getTotalSubscriptionIncome() {
		return totalSubscriptionIncome;
	}
	
    /**
     * This method will set the total income from subscription cars. These cars
     * pay a weekly fee. This fee is multiplied with the total amount of total 
     * subscriptions allowed. These fees are paid on Sunday at 23:59 each week.
     */
    private void setSubscriptionIncome() {
    	if(day == 6 && hour == 23 && minute == 59) {
    		totalSubscriptionIncome += maxSubscriptions * subscriptionFee;
    	}
    }
	
   	/**
   	 * This method will return the total amount of income earned from the
   	 * reservation cars.
   	 * 
   	 * @return totalReservationIncome a number with the total reservation income
   	 */
	public int getTotalReservationIncome() {
		return totalReservationIncome;
	}
    
   	/**
   	 * This method will return the total amount of income earned from all cars
   	 * combined.
   	 * 
   	 * @return totalIncome a number with the total income from all cars
   	 */
	public int getTotalIncome() {
		return totalIncome;
	}
	
	/**
	 * This method will set the total income from all the cars that left the
	 * garage and paid the fee required.
	 */
	private void setTotalIncome() {
		totalIncome = totalRegularIncome + totalSubscriptionIncome + totalReservationIncome;
	}
	
    /**
     * This method returns the totalIncome that is added to the ArrayList
     * called totalIncomeData.
     * 
     * @return totalIncomeData the total income added to the ArrayList
     */
    public ArrayList<Integer> getTotalIncomeData() {
    	return totalIncomeData;
    }
    
    /**
     * This method returns the total amount regular cars that are currently
     * parked in the garage.
     * 
     * @return totalParkedRegular total amount of regular cars currently parked
     */
    public int getTotalParkedRegular() {
    	return totalParkedRegular;
    }
    
    /**
     * This method returns the total amount subscription cars that are currently
     * parked in the garage.
     * 
     * @return totalParkedSubscription total amount of subscription cars currently parked
     */
    public int getTotalParkedSubscription() {
    	return totalParkedSubscription;
    }
    
    /**
     * This method returns the total amount reservation cars that are currently
     * parked in the garage. 
     * 
     * @return totalParkedReservation total amount of reservation cars currently parked
     */
    public int getTotalParkedReservation() {
    	return totalParkedReservation;
    }
   
	/**
	 * This method will retrieve the total amount of money that is currently
	 * parked within the garage for all parked regular cars.
	 * 
	 * @return parkedRegularIncome the amount of money still parked for regulars
	 */
   	public int getParkedRegularIncome() {
   		return parkedRegularIncome;
   	}
   	
   	/**
	 * This method will retrieve the total amount of money that is currently
	 * parked within the garage for all parked reservation cars.
   	 * 
   	 * @return parkedReservationIncome the amount of money still parked for reservations
   	 */
   	public int getParkedReservationIncome() {
   		return parkedReservationIncome;
   	}
    
   	/**
   	 * This method will retrieve the total amount of money that is currently
	 * parked within the garage for all parked paying cars.
   	 * 
   	 * @return parkedTotalIncome the amount of money still parked in total
   	 */
    public int getParkedTotalIncome() {
    	return parkedTotalIncome;
    }
    
    /**
     * This method returns the total amount of regular cars that were missed.
     * These cars are missed because the queues were to long and the cars drove
     * away.
     * 
     * @return totalRegularMissed the amount of regular cars missed
     */
    public int getTotalRegularMissed() {
    	return totalRegularMissed;
    }
    
    /**
     * This method returns the total amount of reservation cars that were missed.
     * These cars are missed because the queues were to long and the cars drove
     * away.
     * 
     * @return totalReservationMissed the amount of reservation cars missed
     */
    public int getTotalReservationMissed() {
    	return totalReservationMissed;
    }
    
    /**
     * This method returns the total amount of all cars that were missed. These 
     * cars are missed because the queues were to long and the cars drove away.
     * 
     * @return totalCarsMissed the amount of total cars missed
     */
    public int getTotalMissed() {
    	return totalCarsMissed;
    }
    
    /**
     * This method sets the total amount of cars missed. It does this by adding
     * the regular cars missed and the reservation cars missed to the total cars
     * missed variable
     */
    private void setTotalCarsMissed() {
    	totalCarsMissed = totalRegularMissed + totalReservationMissed;
    }
    
    /**
     * This method return the total amount of income missed for regular cars, 
     * due to regular cars leaving the queues.
     * 
     * @return missedRegularIncome the income missed from regular cars
     */
    public int getMissedRegularIncome() {
    	return missedRegularIncome;
    }
    
    /**
     * This method return the total amount of income missed for reservations
     * cars, due to reservation cars leaving the queues.
     * 
     * @return missedReservationIncome the income missed from reservation cars
     */
    public int getMissedReservationIncome() {
    	return missedReservationIncome;
    }
    
    /**
     * This method returns the total amount of income missed from all cars
     * combined. This income is missed because cars leave their queues.
     * 
     * @return missedTotalIncome the income missed from cars combined
     */
    public int getMissedTotalIncome() {
    	return missedTotalIncome;
    }
    
    /**
     * This method calculates the total income missed from missed cars. This is
     * calculated by adding all missed income, from the regular and reservation
     * cars to the missedTotalIncome variable.
     */
    private void setMissedIncome() {
    	missedTotalIncome = missedRegularIncome + missedReservationIncome;
    }
    
    /**
     * This method returns the current value of the stepPause variable.
     * 
     * @return stepPause the step pause which influences the speed of simulation
     */
    public int getStepPause() {
    	return stepPause;
    }
    
    /**
     * This method sets the stepPause variable to a new number. This is done via
     * the speed slider in the SlideController class, to speed up the simulation.
     * 
     * @param stepPause the step pause which influences the speed of simulation
     */
    public void setStepPause(int stepPause) {
    	this.stepPause = stepPause;
    }
    
    /**
     * This method will return the amount of steps the simulator will run for.
     * This getter is needed in the SliderController.
     * 
     * @return numberOfSteps the number of steps the simulator will go through
     */
   	public int getNumberOfSteps() {
   		return numberOfSteps;
   	}
   	
   	/**
   	 * This method will return a boolean on whether the simulator is running or 
   	 * not.
   	 * 
   	 * @return run true if the simulator is running, false if not
   	 */
   	public boolean getRun() {
   		return run;
   	}
}
