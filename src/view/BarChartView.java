package view;

import model.SimulatorModel;
import java.awt.*;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class BarChartView extends AbstractView{

private JLabel Regcars, Subcars, Rescars, Empty;
	
public BarChartView(SimulatorModel simulator) {
		super(simulator);
	    Regcars = new JLabel();
	    Subcars = new JLabel();
	    Rescars = new JLabel();
	    Empty = new JLabel();
	    add(Regcars);
	    add(Rescars);
	    add(Subcars);
	    add(Empty);
	    Regcars.setBounds(60,270, 80, 100);
		Subcars.setBounds(400,20, 300, 30);
		Rescars.setBounds(400,40, 300, 10);
		Empty.setBounds(400,0, 300, 30);
		
	}
		public void paintComponent(Graphics g) {
			int reg = getModel().getTotalParkedRegular();
			int sub = getModel().getTotalParkedSubscription();
			int res = getModel().getTotalParkedReservation();
			int empty = getModel().getNumberOfOpenSpots();
			
			// het vlak naar achter de stafen instellen
			Color a = new Color(214, 217, 223);
			g.setColor(a);
			g.fillRect(0, 0, 600, 600);
			
			// Staaf voor de resevatie auto's
			creat3DBar(g, Color.YELLOW, 160, 40 , 60, 270);
			creat2DBar(g, a, 160, 40, 60, 270-res/2);
			
			//Staaf voor de pass houder auto's
			creat3DBar(g,Color.BLUE,360,40, 60, 270);
			 creat2DBar(g,a,360, 40, 60, 270-sub/2);
		
		    
		    // De staaf voor lege parkeer vakken
		   creat3DBar(g,Color.WHITE,260, 40, 60, 270);
		   creat2DBar(g,a,260, 40, 60, 270-empty/2);
		   
		   //De staaf voor algemene auto's
		   creat3DBar(g,Color.RED,60,40, 60, 270);
		   creat2DBar(g,a,60, 40, 60, 270-reg/2);
		   
		   // De waarden onder de stafen 
		   
		   Regcars.setText("Reguluar "+ reg);
		    Subcars.setText("Subscription "+ sub);
		     Rescars.setText("Resevation "+ res);
		    Empty.setText("Empty "+ empty);
		    
		    Regcars.setBounds(60,280, 80, 100);
			Subcars.setBounds(360,280, 100, 100);
			Rescars.setBounds(160,280, 100, 100);
			Empty.setBounds(260,280, 80, 100);
		 
		}
		
		// maak een 3Dbar aan
		public void creat3DBar(Graphics g, Color kleur, int x, int y, int with, int height ) {
			g.setColor(kleur);
			g.fill3DRect(x, y, with, height, true);
		
		
		}
		// maak een 2DBar
		public void creat2DBar(Graphics g, Color kleur, int x, int y, int with, int height ) {
			g.setColor(kleur);
			g.fillRect(x, y, with, height);
		}
}
