package view;
import logic.SimulatorLogic;

import java.awt.GridLayout;

import javax.swing.*;
@SuppressWarnings("serial")
public class ManagerView extends AbstractView{

	JLabel time, total,regularpayd, subpayd, resvpayd;
public ManagerView(SimulatorLogic simulator) {	
	super(simulator);
	setLayout(new GridLayout(5,1));
	
	//Maak het tijd label
	time = new JLabel("Its ......" +  "the time is......");
	
	//maak het normaal label
	regularpayd = new JLabel("Payd by regular cars is:......" );
	
	
	
	// maak het subscription label
	subpayd = new JLabel("Payd by subscription cars is: .........");
	
	
	// maak het reseverings label
	resvpayd = new JLabel("Payd by reserverd cars is: ...........");
	
	// maak het totaal label aan
	total = new JLabel("Payd in total is: ........");
	
	
	
	
	// voeg de labels toe
	add(time);
	add(regularpayd);
	add(subpayd);
	add(resvpayd);
	add(total);
	
}
	

}
