package view;
import java.awt.GridLayout;


import javax.swing.*;

import main.Simulator;
import model.SimulatorModel;
@SuppressWarnings("serial")
public class FinancialView extends AbstractView{

public static JLabel total,regularpayed, subpayed, resvpayed;
public FinancialView(SimulatorModel simulator) {	
	super(simulator);
	setLayout(new GridLayout(5,1));
		
	//maak het normaal label
	regularpayed = new JLabel("Payed by regular cars is: " + SimulatorModel.getPayedByReg());

	
	
	// maak het subscription label
	subpayed = new JLabel("Payed by subscription cars is: " + SimulatorModel.getPayedBySub());
	
	
	// maak het reseverings label
	resvpayed = new JLabel("Payed by reserverd cars is: " + SimulatorModel.getPayedByRes());
	
	// maak het totaal label aan
	total = new JLabel("Payed in total is: " + SimulatorModel.getTotalPayed());
	
	
	
	
	// voeg de labels toe
	add(regularpayed);
	add(subpayed);
	add(resvpayed);
	add(total);
	
}
	public static void updateFinancialView() {
		regularpayed.setText("Payed by regular cars is: €" + SimulatorModel.getPayedByReg());
		resvpayed.setText("Payed by resevation cars is: €" + SimulatorModel.getPayedByRes());
		subpayed.setText("Payed by Subscription cars is: €" + SimulatorModel.getPayedBySub());
		total.setText("Payed in total is: €" + SimulatorModel.getTotalPayed() );

	}
	

}
