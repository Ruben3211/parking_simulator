package model;

import java.util.ArrayList;
import java.util.List;

import view.Observer;

/**
 * This class is an abstract class used for the model. It will implement the
 * Observable interface, which means it's methods can be used. These methods include
 * the registration of an observer to a model and notifying all observers of changes.
 * 
 * @author Rick Zwaneveld
 * @version 24-01-2018
 */

public abstract class AbstractModel implements Observable {
	
	private List<Observer> observers;
	
	/**
	 * The constructor for this class.
	 */
	public AbstractModel() {
		this.observers = new ArrayList<Observer>();
	}
	
	/**
	 * This method registers an observer to the model. This needs to happen or else
	 * the observers can't be updated via the notifyObservers() method.
	 * 
	 * @param observer the Observer that is to be registered as observer
	 */
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}
	
	/**
	 * This method is responsible for updating all observers. This method is called
	 * on when ever changes are made in the model.
	 */
	public void notifyObservers() {
		for(Observer o: this.observers) {
			o.update();
		}
	}
}
