package model;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

    private int totalRegPaymentAmount;
    private int totalSubPaymentAmount;
    private int totalResPaymentAmount;
	private int totalPaymentAmount;

	private int maxSubAllowed = 60;
    private int maxResAllowed = 60;

    // The number of cars per type that left, because the queues were too long.
    private int numMissedReg;
    private int numMissedSub;
    private int numMissedRes;

    private int numberOfSteps;
    private boolean run;

    private int numParkedRegCars;
    private int numParkedResCars;
    private int numParkedSubCars;

    private int moneyMissedReg;
    private int moneyMissedRes;
    private int moneyMissedTotal;

    private int moneyParkedReg;
    private int moneyParkedRes;
    private int moneyParkedTotal;

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

	    reservationList = new ArrayList<>();

        /* allocate subscription parking spaces */
        int floor = 0, row = 0, place = 0;
        for(int spaceIndex = 0; spaceIndex < maxSubAllowed; spaceIndex++) {
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
        maxEntranceQueue = 20;
        
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
        
        regularFee = 15;
        subscriptionFee = 30;
        reservationFee = 20;
        
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
    	tickCars();
    }
    
    private void secondAction() {
    	carsLeaving();
    	carsEntering(entranceOneQueue);
    	carsEntering(entranceTwoQueue);
    	makeReservations();
    }
    
    /**
    private void handleEntrances() {
    	moneyMissedTotal = moneyMissedReg + moneyMissedRes;
    }
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
        moneyParkedReg = numParkedRegCars * regularFee;
        moneyParkedRes = numParkedResCars * reservationFee;
        moneyParkedTotal = moneyParkedReg + moneyParkedRes;
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
    	numMissedReg += numberOfMissedCars;
        moneyMissedReg = numMissedReg * regularFee;

    	/* subscription cars */
        numberOfCars = getNumberOfCarsArriving(weekDaySubscriptionArrivals, weekendSubscriptionArrivals, eventSubscriptionArrivals);
    	numberOfMissedCars = getMissedCars(entranceTwoQueue, numberOfCars, maxEntranceQueue);
        for (int i = 0; i < numberOfCars - numberOfMissedCars; i++) {
    		entranceTwoQueue.addCar(new SubscriptionCar());
        }
    	numMissedSub += numberOfMissedCars;
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
    	int numberOfAdditionalReservationsAllowed = maxResAllowed - reservationList.size();

    	if(numberOfReservations > numberOfAdditionalReservationsAllowed) {
    		numberOfMissedReservations += numberOfReservations - numberOfAdditionalReservationsAllowed;
    		numberOfReservations = numberOfAdditionalReservationsAllowed;
    	}
    	if(numberOfReservations > numberOfFreeSpaces) {
    		numberOfMissedReservations += numberOfReservations - numberOfFreeSpaces;
    		numberOfReservations = numberOfFreeSpaces;
    	}

    	numMissedRes += numberOfMissedReservations;
    	moneyMissedRes = numMissedRes * reservationFee;

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
            	numParkedRegCars++;
            } else if(carType == "subscription") {
            	numParkedSubCars++;
            } else if(carType ==  "reservation") {
            	numParkedResCars++;
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
            	    this.totalRegPaymentAmount += this.regularFee;
            	} else if(carType == "reservation") { 
            	    this.totalResPaymentAmount += this.reservationFee;
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
    
    private void setSubIncome() {
    	if(day == 6 && hour == 23 && minute == 59) {
    		totalSubPaymentAmount = maxSubAllowed * subscriptionFee;
    	}
    }

    private void carLeavesSpot(Car car)
    {
    	Location location = car.getLocation();

    	removeCarAt(location);
        exitQueue.addCar(car);

        String carType = car.getType();
        if(carType == "regular") {
        	numParkedRegCars--;
        } else if(carType == "subscription") {
        	numParkedSubCars--;
        } else if(carType ==  "reservation") {
        	numParkedResCars--;
        	ParkingSpace space = getParkingSpaceAt(location);
        	space.setType("regular");
        }
    } 

    public CarQueue getRegCarQueue () {
	   return entranceOneQueue;
   	}
   
   	public CarQueue getSubResCarQueue () {
	   return entranceTwoQueue;
   	}
   
   	public CarQueue getExitCarQueue () {
	   return exitQueue;
   	}
   
   	public CarQueue getPaymentCarQueue () {
	   	return paymentQueue;
  	}
    
    /**
     * This method returns a string with the current weekday. Which day it is, is
     * calculated by using the day in numbers and a modulo, this number will
     * correspond with the position of the day within the string array.
     * 
     * @return String a string with the current weekday
     */
    public String getDay() {
    	return weekDay[day % 7];
    }
    
    /**
     * This method will create a string for the minutes and hours. If the minutes and
     * hours are under 10, a 0 will be appended to the number. This will make sure
     * that the time is displayed in a 24 hour (00:00) format.
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
    
    public int getTotalParkedIncome() {
    	return moneyParkedTotal;
    }
	
	public int getTotalRegIncome() {
		return totalRegPaymentAmount;
	}
	
	public int getTotalSubIncome() {
		return totalSubPaymentAmount;
	}
	
	public int getTotalResIncome() {
		return totalResPaymentAmount;
	}
	
	public void setTotalIncome() {
		totalPaymentAmount =
	    totalRegPaymentAmount +
	    totalSubPaymentAmount +
	    totalResPaymentAmount;
	}
	
	public int getTotalIncome() {
		return totalRegPaymentAmount;
	}
    
    public int getTotalRegCars() {
    	return numParkedRegCars;
    }
    
    public int getTotalSubCars() {
    	return numParkedSubCars;
    }
    
    public int getTotalResCars() {
    	return numParkedResCars;
    }
    
    public int getTotalEmptySpots() {
    	return numberOfOpenSpots;
    }
    
    public int getTotalParkedReg() {
    	return numParkedRegCars;
    }
    
    public int getTotalParkedRes() {
    	return numParkedResCars;
    }
    
    public int getTotalParkedSub() {
    	return numParkedSubCars;
    }
    
    public int getStepPause() {
    	return stepPause;
    }
    
    public void setStepPause(int stepPause) {
    	this.stepPause = stepPause;
    }
}
