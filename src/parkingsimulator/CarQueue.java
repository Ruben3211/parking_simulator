package parkingsimulator;

import java.util.LinkedList;
import java.util.Queue;

import car.*;

public class CarQueue {
	
    private Queue<AbstractCar> queue = new LinkedList<>();

    /**
     * Method that adds a car to the queue list.
     * 
     * @param car A car object.
     * @return true If a car has been added.
     */
    public boolean addCar(AbstractCar car) {
        return queue.add(car);
    }

    /**
     * Method that retrieves and removes the first car in the queue list.
     * 
     * @return The first car in the list, or null if the list is empty.
     */
    public AbstractCar removeCar() {
        return queue.poll();
    }

    /**
     * Method that returns how many car objects are in the queue list.
     * 
     * @return The total size of the queue.
     */
    public int carsInQueue(){
    	return queue.size();
    }
}
