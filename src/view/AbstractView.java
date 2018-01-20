package view;

import javax.swing.*;

import logic.*;

public abstract class AbstractView extends JPanel {
	
	private static final long serialVersionUID = 434279804548490239L;
	protected SimulatorLogic simulator;
	
	public AbstractView(SimulatorLogic simulator) {
		this.simulator = simulator;
		simulator.addView(this);
	}
	
	public SimulatorLogic getModel() {
		return simulator;
	}
	
	public void updateView() {
		repaint();
	}
}
