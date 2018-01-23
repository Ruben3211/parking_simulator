package view;
import java.awt.GridLayout;

import javax.swing.*;

import model.SimulatorModel;
@SuppressWarnings("serial")
public class FinancialView extends AbstractView{

	JLabel total,regularpayd, subpayd, resvpayd;
public FinancialView(SimulatorModel simulator) {	
	super(simulator);
	setLayout(new GridLayout(5,1));
		
	//maak het normaal label
	regularpayd = new JLabel("Payd by regular cars is:......" );
	
	
	
	// maak het subscription label
	subpayd = new JLabel("Payd by subscription cars is: .........");
	
	
	// maak het reseverings label
	resvpayd = new JLabel("Payd by reserverd cars is: ...........");
	
	// maak het totaal label aan
	total = new JLabel("Payd in total is: " + simulator.getTotalPayd());
	
	
	
	
	// voeg de labels toe
	add(regularpayd);
	add(subpayd);
	add(resvpayd);
	add(total);
	
}
	

}
