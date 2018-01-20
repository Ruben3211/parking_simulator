package controller;

import javax.swing.*;

import logic.*;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	
	protected SimulatorLogic simulator;
	
	public AbstractController(SimulatorLogic simulator) {
		this.simulator = simulator;
	}
}
