package controller;

import javax.swing.*;

import logic.*;

public abstract class AbstractController extends JPanel {
	
	private static final long serialVersionUID = -9122011915600484014L;
	protected SimulatorLogic simulator;
	
	public AbstractController(SimulatorLogic simulator) {
		this.simulator = simulator;
	}
}
