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

    // Keeps track of the income per hour/day/week.
    private List<Integer> incomeHourList;
    private List<Integer> incomeDayList;
    private List<Integer> incomeWeekList;
    private int incomeLastHour;
    private int incomeLastDay;
    private int incomeLastWeek;
    
	// The prices the various cars have to pay.
    private int regPaymentAmount = 15;
    private int subPaymentAmount = 30;
    private int resPaymentAmount = 20;

    private int totalRegPaymentAmount;
    private int totalSubPaymentAmount;
    private int totalResPaymentAmount;
	private int totalPaymentAmount;

    // Max number of subscription cars allowed at once.
    private int maxSubAllowed = 60;

    // Max number of reservations allowed at once.
    private int maxResAllowed = 60; // TODO: doenst work, fix it.

    // The number of cars per type that left, because the queues were too long.
    private int numMissedReg;
    private int numMissedSub;
    private int numMissedRes;

    private int numberOfSteps;
    private boolean run;

    private int numParkedRegCars;
    private int numParkedResCars;
    private int numParkedSubCars;

    // TODO: These are not used internally and can be derived from other variables. Maybe instead of keeping
    //       track of them we add a method to compute them whenever someone wants to know?
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
    
    public void step() {
    	advanceTime();
    	handleExit();
    	tickCars();
    	notifyObservers();
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
    		notifyObservers();
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

    private void updateMoneyInGarageCounts()
    {
        moneyParkedReg = numParkedRegCars * regPaymentAmount;
        moneyParkedRes = numParkedResCars * resPaymentAmount;
        moneyParkedTotal = moneyParkedReg + moneyParkedRes;
    }

    private void handleEntrance() {
    	carsArriving();
    	makeReservations();
    	checkReservations();
    	moneyMissedTotal = moneyMissedReg + moneyMissedRes;
    	carsEntering(entranceSubResQueue);
    	carsEntering(entranceRegQueue);
    	updateMoneyInGarageCounts();
    }
    
    private void handleExit() {
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    	updateMoneyInGarageCounts();
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
    	numberOfCars = getNumberOfCarsArriving(weekDayRegArrivals, weekendRegArrivals, eventRegArrivals);
    	numberOfMissedCars = getMissedCars(entranceRegQueue, numberOfCars, entranceRegQueueMax);
    	for (int i = 0; i < numberOfCars - numberOfMissedCars; i++) {
        	entranceRegQueue.addCar(new RegularCar());
        }
    	numMissedReg += numberOfMissedCars;
        moneyMissedReg = numMissedReg * regPaymentAmount;

    	/* subscription cars */
        numberOfCars = getNumberOfCarsArriving(weekDaySubArrivals, weekendSubArrivals, eventSubArrivals);
    	numberOfMissedCars = getMissedCars(entranceSubResQueue, numberOfCars, entranceSubResQueueMax);
        for (int i = 0; i < numberOfCars - numberOfMissedCars; i++) {
    		entranceSubResQueue.addCar(new SubscriptionCar());
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
    	int numberOfReservations = getNumberOfCarsArriving(weekDayResArrivals, weekendResArrivals, eventResArrivals);
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
    	moneyMissedRes = numMissedRes * resPaymentAmount;

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
	    		entranceSubResQueue.addCar(car);
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
    	while (queue.carsInQueue() > 0 && i < enterSpeed)
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
            if(car.getHasToPay())
            {
            	if(carType == "regular") {
            	    this.totalRegPaymentAmount += this.regPaymentAmount;
            	} else if(carType == "reservation") { 
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
        exitCarQueue.addCar(car);

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

    public int getRegCarQueue () {
	   return entranceRegQueue.carsInQueue();
   	}
   
   	public int getSubCarQueue () {
	   return entranceSubResQueue.carsInQueue();
   	}
   
   	public int getExitCarQueue () {
	   return exitCarQueue.carsInQueue();
   	}
   
   	public int getPaymentCarQueue () {
	   	return paymentCarQueue.carsInQueue();
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
    
    private void setIncomeHour(int incomeCounter) {
    	int incomeLastMinute = incomeCounter;
    	incomeLastHour = 0;
    	if (incomeHourList.size() >= 60) {
    		incomeHourList.remove(0);
    		incomeHourList.add(incomeLastMinute);
    	}
    	else {
    		incomeHourList.add(incomeLastMinute);
    	}
    	
    	for(Integer countHour : incomeHourList) {
    		incomeLastHour += countHour;
    	}
    	
    	if (minute == 0) {
    		setIncomeDay(incomeLastHour);
    	}
    }
    
    private void setIncomeDay(int incomeLastHourValue) {
    	int incomeLastHour = incomeLastHourValue;
    	incomeLastDay = 0;
    	if (incomeDayList.size() >= 24) {
    		incomeDayList.remove(0);
    		incomeDayList.add(incomeLastHour);
    	}
    	else {
    		incomeDayList.add(incomeLastHour);
    	}
    	
    	for(Integer countDay : incomeDayList) {
    		incomeLastDay += countDay;
    	}
    	
    	if (hour == 0) {
    		setIncomeWeek(incomeLastDay);
    	}
    }
    
    private void setIncomeWeek(int incomeLastDayValue) {
    	int incomeLastDay = incomeLastDayValue;
    	incomeLastWeek = 0;
    	if (incomeWeekList.size() >= 7) {
    		incomeWeekList.remove(0);
    		incomeWeekList.add(incomeLastDay);
    	}
    	else {
    		incomeWeekList.add(incomeLastDay);
    	}
    	
    	for(Integer countWeek : incomeWeekList) {
    		incomeLastWeek += countWeek;
    	}
    }
    
    public int getIncomeLastHour() {
    	return incomeLastHour;
    }
    
    public int getIncomeLastDay() {
    	return incomeLastDay;
    }
    
    public int getIncomeLastWeek() {
    	return incomeLastWeek;
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
    
    public int setStepPause(int mSeconds) {
    	stepPause = mSeconds;
    	return mSeconds;
    }

}
