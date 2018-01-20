package view;

import javax.swing.*;

import logic.*;

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel {
	
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
