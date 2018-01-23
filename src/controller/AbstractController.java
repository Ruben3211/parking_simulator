package controller;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import logic.SimulatorLogic;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel implements ActionListener {
	
	protected SimulatorLogic simulator;
	
	public AbstractController(SimulatorLogic simulator) {
		this.simulator = simulator;
	}
}
