package view;

import javax.swing.JPanel;

import logic.Observable;
import logic.SimulatorLogic;

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel implements Observer {
	
	private static Observable observable;
	
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

	public SimulatorLogic getModel() {
		return(SimulatorLogic) getObservable();
	}
	
	public void updateView() {
		repaint();
	}
	
	public void update() {
		updateView();
	}
}
