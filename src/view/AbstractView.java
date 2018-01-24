package view;

import javax.swing.JPanel;

import model.Observable;
import model.SimulatorModel;

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel implements Observer {
	
	private static Observable observable;
	
	/**
	 * The constructor for this class.
	 * 
	 * @param observable register an object to be observed
	 */
	public AbstractView(Observable observable) {
		observe(observable);
	}
	
	public static Observable getObservable() {
		return observable;
	}
	
	public static void setObservable(Observable observable) {
		AbstractView.observable = observable;
	}
	
	public void observe(Observable observable) {
		setObservable(observable);
		getObservable().registerObserver(this);
	}

	public SimulatorModel getModel() {
		return(SimulatorModel) getObservable();
	}
	
	public void updateView() {
		repaint();
	}
	
	public void update() {
		updateView();
	}
}
