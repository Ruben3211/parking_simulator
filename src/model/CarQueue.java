package model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is responsible for the ability to create queues, these queues can 
 * hold car objects. It provides a method to add, retrieve and remove cars from 
 * the queue, as well as retrieving the total queue size.
 * 
 * @author Rick Zwaneveld
 * @version 30-01-2018
 */

public class CarQueue {
	
    private Queue<Car> queue = new LinkedList<>();

    /**
     * This method adds a car to the queue.
     * 
     * @param car a car object
     * @return true if a car is added successfully
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }
    
    /**
     * This method retrieves, but does not remove, the first car in the queue.
     * This method is used once in the SimulatorModel.
     * 
     * @return the first car if there is one, false if the queue is empty.
     */
    public Car peekCar() {
    	return queue.peek();
    }
    
    /**
     * This method retrieves, but does not remove, the first car in the queue.
     * This method is used once in the the CarQueueView.
     * 
     * @return the first car if there is one, false if the queue is empty.
     */
    public Car peekCar(int i) {
    	if(((LinkedList<Car>) queue).get(i) != null) {
    		return ((LinkedList<Car>) queue).get(i);
    	} 
    	return null;
    }
    
    /**
     * This method retrieves and removes the first car in the queue.
     * 
     * @return the first car if there is one, false if the queue is empty.
     */
    public Car removeCar() {
        return queue.poll();
    }

    /**
     * This method will return the total amount a cars in the queue.
     * 
     * @return a number corresponding with the total size of the queue
     */
    public int carsInQueue(){
    	return queue.size();
    }
}
