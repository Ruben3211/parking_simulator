package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import model.SimulatorModel;

@SuppressWarnings("serial")
public class FinancialView extends AbstractView{

	private JLabel regLabel, regTotalData;
	private JLabel subLabel, subTotalData;
	private JLabel resLabel, resTotalData;
	private JLabel totalLabel, totalTotalData;
	private JLabel parkedLabel, parkedData;
	
	public FinancialView(SimulatorModel simulator) {	
		super(simulator);
		
		setLayout(new GridLayout(6, 2));
		
		regLabel = new JLabel("Regular:");
		regTotalData = new JLabel("0");
		
		subLabel = new JLabel("Subscription:");
		subTotalData = new JLabel("0");
		
		resLabel = new JLabel("Reservation:");
		resTotalData = new JLabel("0");
		
		totalLabel = new JLabel("Total:");
		totalTotalData = new JLabel("0");
		
		parkedLabel = new JLabel("Parked income:");
		parkedData = new JLabel("0");

		add(regLabel);
		add(regTotalData);
		
		add(subLabel);
		add(subTotalData);
		
		add(resLabel);
		add(resTotalData);
		
		add(totalLabel);
		add(totalTotalData);
		
		add(parkedLabel);
		add(parkedData);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		regTotalData.setText(String.valueOf(getModel().getTotalRegIncome()));
		subTotalData.setText(String.valueOf(getModel().getTotalSubIncome()));
		resTotalData.setText(String.valueOf(getModel().getTotalResIncome()));
		totalTotalData.setText(String.valueOf(getModel().getTotalIncome()));
		parkedData.setText(String.valueOf(getModel().getTotalParkedIncome()));
	}
}
