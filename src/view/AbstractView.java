package view;

import javax.swing.JPanel;

import model.Observable;
import model.SimulatorModel;

/**
 * This class is and abstract class that is used by all the views. It extends
 * a JPanel and implements the Observer class. Multiple kinds of methods are
 * defined here.
 * 
 * @author Rick Zwaneveld
 * @version 30-01-2018
 */

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel implements Observer {
	
	private static Observable observable;
	
	/**
	 * The constructor for the class AbstractView.
	 * 
	 * @param observable register an object to be observed
	 */
	public AbstractView(Observable observable) {
		observe(observable);
	}
	
	/**
	 * This method returns a reference that this class is observing.
	 * 
	 * @return observable
	 */
	public static Observable getObservable() {
		return observable;
	}
	
	/**
	 * This method defines the object that this class needs to observe.
	 * 
	 * @param observable the model we want to observe
	 */
	public static void setObservable(Observable observable) {
		AbstractView.observable = observable;
	}
	
	/**
	 * This method does two things. First, it registers an observable object.
	 * Which in this case is the model. This is required to retrieve information
	 * from the model whenever a change is made. Second, it registers this 
	 * observer to the observable.
	 * 
	 * @param observable
	 */
	public void observe(Observable observable) {
		setObservable(observable);
		getObservable().registerObserver(this);
	}
	
	/**
	 * This method returns the model. It can be used to retrieve information
	 * from the model class.
	 * 
	 * @return model
	 */
	public SimulatorModel getModel() {
		return(SimulatorModel) getObservable();
	}
	
	/**
	 * This method is used to update the views.
	 */
	public void updateView() {
		repaint();
	}
	
	/**
	 * This method is called on by an observable whenever there is a change in
	 * an observed object.
	 */
	public void update() {
		updateView();
	}
}
