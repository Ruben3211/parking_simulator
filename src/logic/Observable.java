package logic;

import view.Observer;

/**
 * This is an interface responsible for two things. It makes sure all the observable
 * are able to register an observer. It also makes sure they can notify the observers.
 * 
 * @author Rick Zwaneveld
 * @version 23-01-2018
 */

public interface Observable {

	/**
	 * This method registers a reference between the observable and the observer.
	 * 
	 * @param observer the observer
	 */
	public void registerObserver (Observer observer);
	
	/**
	 * This method is used to notify the observers whenever this is necessary.
	 */
	public void notifyObservers();
}
