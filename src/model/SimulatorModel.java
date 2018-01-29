package model;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 
 * 
 * @author Ruben Bonga, Joey Kroes, Detmer Struiksma & Rick Zwaneveld
 * @version 28-01-2018
 */

public class SimulatorModel extends AbstractModel implements Runnable {

    private Random random;

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private ParkingSpace[][][] spaces;

    private List<Reservation> reservationList;

	private CarQueue entranceOneQueue;
    private CarQueue entranceTwoQueue;
    private CarQueue paymentQueue;
    private CarQueue exitQueue;

    private int	maxEntranceQueue;

    private String[] weekDay = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private int day;
    private int hour;
    private int minute;

    private int stepPause;

    // Average number of cars arriving per hour.
    private int weekDayRegularArrivals;
    private int weekendRegularArrivals;
    private int eventRegularArrivals; // used on Thursday/Friday/Saturday night 18:00 - 24:00 and Sunday afternoon 12:00 - 18:00.
    private int weekDaySubscriptionArrivals;
    private int weekendSubscriptionArrivals;
    private int eventSubscriptionArrivals; // used on Thursday/Friday/Saturday night 18:00 - 24:00 and Sunday afternoon 12:00 - 18:00.
    private int weekDayReservationArrivals;
    private int weekendReservationArrivals;
    private int eventReservationArrivals; // used on Thursday/Friday/Saturday night 18:00 - 24:00 and Sunday afternoon 12:00 - 18:00.

    // Number of cars that can enter/leave per minute.
    private int entranceSpeed;
    private int paymentSpeed;
    private int exitSpeed;
    
	// The prices the various cars have to pay.
    private int regularFee;
    private int subscriptionFee;
    private int reservationFee;

    private int totalRegularIncome;
    private int totalSubscriptionIncome;
    private int totalReservationIncome;
	private int totalIncome;

	private int maxSubscriptions;
    private int maxReservations;

    // The number of cars per type that left, because the queues were too long.
    private int totalRegularMissed;
    private int totalReservationMissed;
    private int totalCarsMissed;

    private int numberOfSteps;
    private boolean run;

    private int totalParkedRegular;
    private int totalParkedSubscription;
    private int totalParkedReservation;
    
    private int missedRegularIncome;
    private int missedReservationIncome;
    private int missedTotalIncome;

    private int parkedRegularIncome;
    private int parkedReservationIncome;
    private int parkedTotalIncome;

    /**
     * The constructor for the class SimulatorModel.
     * 
     * @param numberOfFloors the number of floors
     * @param numberOfRows the number of rows
     * @param numberOfPlaces the number of places
     */
    public SimulatorModel(int numberOfFloors, int numberOfRows, int numberOfPlaces)
    {
        this.random = new Random();

        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;

        spaces = new ParkingSpace[numberOfFloors][numberOfRows][numberOfPlaces];

        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                	spaces[floor][row][place] = new ParkingSpace();
                }
            }
        }

        
    	maxSubscriptions = 60;
        maxReservations = 60;
        
	    reservationList = new ArrayList<>();

        /* allocate subscription parking spaces */
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

        
        stepPause = 100;
        maxEntranceQueue = 6;
        
        minute = 0;
        hour = 0;
        day = 0;
        
        entranceOneQueue = new CarQueue();
        entranceTwoQueue = new CarQueue();
        paymentQueue = new CarQueue();
        exitQueue = new CarQueue();
        
        weekDayRegularArrivals = 100;
        weekendRegularArrivals = 200;
        eventRegularArrivals = 300;
        weekDaySubscriptionArrivals = 50;
        weekendSubscriptionArrivals = 5;
        eventSubscriptionArrivals = 300;
        weekDayReservationArrivals = 50;
        weekendReservationArrivals = 5;
        eventReservationArrivals = 300;
        
        entranceSpeed = 3;
        paymentSpeed = 7;
        exitSpeed = 5;
        
        regularFee = 1;
        subscriptionFee = 15;
        reservationFee = 9;
        
        run = false;
    }
    
	public ParkingSpace getParkingSpaceAt(Location location) {
		if(!locationIsValid(location)) {
			return null;
		}
		return spaces[location.getFloor()][location.getRow()][location.getPlace()];
	}

	public Car getCarAt(Location location) {
    	ParkingSpace space = getParkingSpaceAt(location);
    	if(space == null) {
    		return null;
    	}
   		return space.getCar();
    }

    public boolean setCarAt(Location location, Car car) {
    	ParkingSpace space = getParkingSpaceAt(location);
    	if(space == null) {
    		return false;
    	}
        Car oldCar = space.getCar();
        if (oldCar == null) {
        	space.setCar(car);
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
    	ParkingSpace space = getParkingSpaceAt(location);
    	if(space == null) {
    		return null;
    	}
        Car car = space.getCar();
        if (car == null) {
            return null;	
        }
        space.setCar(null);
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    public int countFreeParkingSpaces(String type)
    {
    	int count = 0;
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    ParkingSpace space = getParkingSpaceAt(location);
                    if(space != null && space.getType() == type) {
                    	if (space.getCar() == null) {
                    		count++;
                    	}
                    }
                }
            }
        }
        return count;	
    }

    public Location getFirstFreeParkingSpace(String type)
    {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    ParkingSpace space = getParkingSpaceAt(location);
                    if(space != null && space.getType() == type) {
                    	if (space.getCar() == null) {
                    		return location;
                    	}
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
    
    public void start(int numberOfSteps) {
    	this.numberOfSteps = numberOfSteps;
    	run = true;
    	new Thread(this).start();
    }
    
    public void stop() {
    	run = false;
    }
 
    private void firstAction() {
    	advanceTime();
    	checkReservations();
    	carsArriving();
    	carsReadyToLeave();
    	carsPaying();
    	setSubIncome();
    	updateMoneyInGarageCounts();
    	setTotalIncome();
    	setTotalCarsMissed();
    	setMissedIncome();
    	tickCars();
    }
    
    private void secondAction() {
    	carsLeaving();
    	carsEntering(entranceOneQueue);
    	carsEntering(entranceTwoQueue);
    	makeReservations();
    }
    
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

    private void updateMoneyInGarageCounts()
    {
        parkedRegularIncome = totalParkedRegular * regularFee;
        parkedReservationIncome = totalParkedReservation * reservationFee;
        parkedTotalIncome = parkedRegularIncome + parkedReservationIncome;
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

    	/* regular cars */
    	numberOfCars = getNumberOfCarsArriving(weekDayRegularArrivals, weekendRegularArrivals, eventRegularArrivals);
    	numberOfMissedCars = getMissedCars(entranceOneQueue, numberOfCars, maxEntranceQueue);
    	for (int i = 0; i < numberOfCars - numberOfMissedCars; i++) {
        	entranceOneQueue.addCar(new RegularCar());
        }
    	totalRegularMissed += numberOfMissedCars;
        missedRegularIncome = totalRegularMissed * regularFee;

    	/* subscription cars */
        numberOfCars = getNumberOfCarsArriving(weekDaySubscriptionArrivals, weekendSubscriptionArrivals, eventSubscriptionArrivals);
    	numberOfMissedCars = getMissedCars(entranceTwoQueue, numberOfCars, maxEntranceQueue);
        for (int i = 0; i < numberOfCars - numberOfMissedCars; i++) {
    		entranceTwoQueue.addCar(new SubscriptionCar());
        }
    }

    private int computeReservationArrivalTime() {
    	double value;
    	double min = 2.0;
    	double average = 15.0;
    	double standardDeviation = 50.0;
    	do {
    	  value = random.nextGaussian() * standardDeviation + average;
    	} while(value <= min);
    	return (int)Math.round(value);
    }

    private void makeReservations()
    {
    	int numberOfReservations = getNumberOfCarsArriving(weekDayReservationArrivals, weekendReservationArrivals, eventReservationArrivals);
    	int numberOfMissedReservations = 0;
    	int numberOfFreeSpaces = countFreeParkingSpaces("regular");
    	int numberOfAdditionalReservationsAllowed = maxReservations - reservationList.size();

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

    	for(int i = 0; i < numberOfReservations; i++)
    	{
            Location freeLocation = getFirstFreeParkingSpace("regular");

            int timeOfArrival = computeReservationArrivalTime();
            int timeOfExpiration = curMinute + 30;
            Reservation reservation = new Reservation(freeLocation, timeOfArrival, timeOfExpiration);

            reservationList.add(reservation);

            ParkingSpace space = getParkingSpaceAt(freeLocation);
            space.setType("reservation");
    	}
    }

    private void checkReservations()
    {
    	int curMinute = 24*60*day + 60*hour + minute;

	    Iterator<Reservation> iterator = reservationList.iterator();
	    while(iterator.hasNext())
	    {
	    	Reservation reservation = iterator.next();

	    	if(reservation.getArrivalTime() <= curMinute)
	    	{
	    		ReservationCar car = new ReservationCar();
	    		entranceTwoQueue.addCar(car);
	    		car.setLocation(reservation.getLocation());
				iterator.remove();
	    	}
	    	else if(reservation.getExpirationTime() <= curMinute)
	    	{
	    		ParkingSpace space = getParkingSpaceAt(reservation.getLocation());
	    		space.setType("regular");
	    		iterator.remove();
	    	}
	    }
    }

    private void carsEntering(CarQueue queue)
    {
        int i = 0;
    	while (queue.carsInQueue() > 0 && i < entranceSpeed)
    	{
    		Car car = queue.peekCar();

            Location freeLocation;
            if(car.getType() == "subscription")
            	freeLocation = getFirstFreeParkingSpace("subscription");
            else if(car.getType() == "reservation")
            	freeLocation = car.getLocation();
            else
            	freeLocation = getFirstFreeParkingSpace("regular");

            if(freeLocation == null)
            	break;

            car = queue.removeCar();
            setCarAt(freeLocation, car);
            i++;

            String carType = car.getType();
            if(carType == "regular") {
            	totalParkedRegular++;
            } else if(carType == "subscription") {
            	totalParkedSubscription++;
            } else if(carType ==  "reservation") {
            	totalParkedReservation++;
            }
        }
    }

    private void carsReadyToLeave() {
        Car car = getFirstLeavingCar();
        while (car != null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
        }
    }
    
    private void carsPaying() {
    	int i = 0;
    	while (paymentQueue.carsInQueue() > 0 && i < paymentSpeed){
            Car car = paymentQueue.removeCar();
            String carType = car.getType();
            if(car.getHasToPay())
            {
            	if(carType == "regular") {
            	    this.totalRegularIncome += this.regularFee;
            	} else if(carType == "reservation") { 
            	    this.totalReservationIncome += this.reservationFee;
            	}
            }
            carLeavesSpot(car);
            i++;
    	}
    }

    private void carsLeaving() {
    	int i = 0;
    	while (exitQueue.carsInQueue() > 0 && i < exitSpeed){
            exitQueue.removeCar();
            i++;
    	}	
    }

    private int getNumberOfCarsArriving(int weekDay, int weekend, int event)
    {
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
    	Location location = car.getLocation();

    	removeCarAt(location);
        exitQueue.addCar(car);

        String carType = car.getType();
        if(carType == "regular") {
        	totalParkedRegular--;
        } else if(carType == "subscription") {
        	totalParkedSubscription--;
        } else if(carType ==  "reservation") {
        	totalParkedReservation--;
        	ParkingSpace space = getParkingSpaceAt(location);
        	space.setType("regular");
        }
    } 

    //----------------------------------------------------------------------------------------------------------------
    // Place all the getters and setters, that are to be used in the controllers	
    // and views, here. If a getter and setter use the same variable place the
    // setter first and then the getter. Comment them appropriately.
    //----------------------------------------------------------------------------------------------------------------
    
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
     * This method will retrieve data from the entranceOneQueue. With the methods
     * defined in the CarQueue class, this will allow access to the objects
     * within this particular queue.
     * 
     * @return entranceOneQueue a LinkedList with objects within
     */
    public CarQueue getEntranceOneQueue () {
	   return entranceOneQueue;
   	}
    
    /**
     * This method will retrieve data from the entranceTwoQueue. With the methods
     * defined in the CarQueue class, this will allow access to the objects
     * within this particular queue.
     * 
     * @return entranceTwoQueue a LinkedList with objects within
     */
   	public CarQueue getEntranceTwoQueue () {
	   return entranceTwoQueue;
   	}
   	
    /**
     * This method will retrieve data from the paymentQueue. With the methods
     * defined in the CarQueue class, this will allow access to the objects
     * within this particular queue.
     * 
     * @return paymentQueue a LinkedList with objects within
     */
   	public CarQueue getPaymentQueue () {
	   	return paymentQueue;
  	}
   	
    /**
     * This method will retrieve data from the exitQueue. With the methods
     * defined in the CarQueue class, this will allow access to the objects
     * within this particular queue.
     * 
     * @return exitQueue a LinkedList with objects within
     */
   	public CarQueue getExitQueue () {
	   return exitQueue;
   	}
   	
   	/**
   	 * This method will return the total amount of income earned from the
   	 * regular car objects.
   	 * 
   	 * @return totalRegularIncome a number with the total regular income
   	 */
   	public int getTotalRegularIncome() {
		return totalRegularIncome;
	}
	
    /**
     * This method will set the total income from subscription cars. These cars
     * pay a weekly fee. This fee is multiplied with the total amount of total 
     * subscriptions allowed. These fees are paid on Sunday at 23:59 each week.
     */
    private void setSubIncome() {
    	if(day == 6 && hour == 23 && minute == 59) {
    		totalSubscriptionIncome = maxSubscriptions * subscriptionFee;
    	}
    }
   	
   	/**
   	 * This method will return the total amount of income earned from the
   	 * subscription car objects.
   	 * 
   	 * @return totalSubscriptionIncome a number with the total subscription income
   	 */
	public int getTotalSubscriptionIncome() {
		return totalSubscriptionIncome;
	}
	
   	/**
   	 * This method will return the total amount of income earned from the
   	 * reservation car objects.
   	 * 
   	 * @return totalReservationIncome a number with the total reservation income
   	 */
	public int getTotalReservationIncome() {
		return totalReservationIncome;
	}
	
	/**
	 * This method will set the total income from all the cars that left the
	 * garage and paid the fee required.
	 */
	private void setTotalIncome() {
		totalIncome = totalRegularIncome + totalSubscriptionIncome + totalReservationIncome;
	}
	
   	/**
   	 * This method will return the total amount of income earned from the
   	 * regular car objects.
   	 * 
   	 * @return totalIncome a number with the total income from all cars
   	 */
	public int getTotalIncome() {
		return totalIncome;
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
	 * parked within the garage for all parked cars.
   	 * 
   	 * @return parkedTotalIncome the amount of money still parked in total
   	 */
    public int getParkedTotalIncome() {
    	return parkedTotalIncome;
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
    
    public int getTotalParkedRegular() {
    	return totalParkedRegular;
    }
    
    public int getTotalParkedSubscription() {
    	return totalParkedSubscription;
    }
    
    public int getTotalParkedReservation() {
    	return totalParkedReservation;
    }
    
    public int getTotalEmptySpots() {
    	return numberOfOpenSpots;
    }
    
    public int getStepPause() {
    	return stepPause;
    }
    
    public void setStepPause(int stepPause) {
    	this.stepPause = stepPause;
    }
    
    public int getMissedRegularIncome() {
    	return missedRegularIncome;
    }
    
    public int getMissedReservationIncome() {
    	return missedReservationIncome;
    }
    
    public void setMissedIncome() {
    	missedTotalIncome = missedRegularIncome + missedReservationIncome;
    }
    
    public int getMissedTotalIncome() {
    	return missedTotalIncome;
    }
    
    public void setTotalCarsMissed() {
    	totalCarsMissed =
    	totalRegularMissed +
    	totalReservationMissed;
    }
    
    public int getTotalRegularMissed() {
    	return totalRegularMissed;
    }
    
    public int getTotalReservationMissed() {
    	return totalReservationMissed;
    }
    
    public int getTotalMissed() {
    	return totalCarsMissed;
    }
}
