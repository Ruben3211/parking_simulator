package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;

import model.SimulatorModel;

@SuppressWarnings("serial")
public class FinancialView extends AbstractView{

	private JLabel regPaidLabel, regPaidData;
	private JLabel subPaidLabel, subPaidData;
	private JLabel resPaidLabel, resPaidData;
	private JLabel totalPaidLabel, totalPaidData;
	
	public FinancialView(SimulatorModel simulator) {	
		super(simulator);
		
		setLayout(new GridLayout(4,2));
		
		regPaidLabel = new JLabel("Regular cars: ");
		regPaidData = new JLabel("0");
		subPaidLabel = new JLabel("Subscription cars: ");
		subPaidData = new JLabel("0");
		resPaidLabel = new JLabel("Reservered cars: ");
		resPaidData = new JLabel("0");
		totalPaidLabel = new JLabel("Total: ");
		totalPaidData = new JLabel("0");
	
		add(regPaidLabel);
		add(regPaidData);
		add(subPaidLabel);
		add(subPaidData);
		add(resPaidLabel);
		add(resPaidData);
		add(totalPaidLabel);
		add(totalPaidData);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		regPaidData.setText(String.valueOf(getModel().getRegPaidAmount()));
		subPaidData.setText(String.valueOf(getModel().getSubPaidAmount()));
		resPaidData.setText(String.valueOf(getModel().getResPaidAmount()));
		totalPaidData.setText(String.valueOf(getModel().getTotalPaidAmount()));
	}
}
