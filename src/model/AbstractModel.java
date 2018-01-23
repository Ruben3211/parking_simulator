package model;

import java.util.ArrayList;
import java.util.List;

import view.Observer;

public abstract class AbstractModel implements Observable {
	
	private List<Observer> observers;
	
	public AbstractModel() {
		this.observers = new ArrayList<Observer>();
	}
	
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}
	
	public void notifyObservers() {
		for(Observer o: this.observers) {
			o.update();
		}
	}
}
