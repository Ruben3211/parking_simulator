package controller;

import javax.swing.*;

import logic.*;

public abstract class AbstractController extends JPanel {
	
	protected SimulatorLogic simulator;
	
	public AbstractController(SimulatorLogic simulator) {
		this.simulator = simulator;
	}
}
