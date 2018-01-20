package controller;

import java.awt.event.*;
import javax.swing.*;
import logic.*;

// TODO: Add a way to disable the buttons once the simulator has been started.

@SuppressWarnings("serial")
public class RunController extends AbstractController implements ActionListener {

	private JButton step;
	private JTextField steps;
	private JButton start;
	private JButton stop;
	
	public RunController(SimulatorLogic simulator) {
		super(simulator);
		setSize(450, 50);
		step = new JButton("Step");
		step.addActionListener(this);
		steps = new JTextField();
		steps.addActionListener(this);
		start = new JButton("Start");
		start.addActionListener(this);
		stop = new JButton("Stop");
		stop.addActionListener(this);
		
		this.setLayout(null);
		add(step);
		add(steps);
		add(start);
		add(stop);
		step.setBounds(50, 10, 70, 30);
		steps.setBounds(140, 10, 70, 30);
		start.setBounds(230, 10, 70, 30);
		stop.setBounds(320, 10, 70 ,30);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == step) {
			simulator.step();
		}
		
		if (e.getSource() == start) {
			int steps = parseSteps();
			simulator.start(steps);
		}
		
		if (e.getSource() == stop) {
			simulator.stop();
		}
	}
	
	private int parseSteps() {
		return Integer.parseInt(steps.getText());
	}
}
