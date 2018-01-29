package view;

import model.SimulatorModel;
import java.awt.*;
import javax.swing.JLabel;
import java.awt.geom.*;

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
			Color a = new Color(238, 238, 238);
			g.setColor(a);
			g.fillRect(0, 0, 600, 600);
			//g.setColor(Color.RED);
			//g.fill3DRect(60, 250, 80, reg, false);
	
			creatBar(g, Color.YELLOW, 160,250 , 80, res);
			
			
			//g.setColor(Color.BLUE);
		   // g.fill3DRect(360, 250, 80, -sub, true);
		
		  // g.setColor(Color.YELLOW);
		  //  g.fill3DRect(160, 250, 80, res, true);
		 //  /g.fillRect(160, 250, 80 - 3, res);
		
		    
		    
		   // g.setColor(Color.WHITE);
		   // g.fill3DRect(260, 250, 80, empty, true);
		    
		    Regcars.setText("Reguluar "+ reg);
		    Subcars.setText("Subscription "+ sub);
		     Rescars.setText("Resevation "+ res);
		    Empty.setText("Empty "+ empty);
		    
		    
		    Regcars.setBounds(60,260, 80, 100);
			Subcars.setBounds(360,260, 100, 100);
			Rescars.setBounds(160,260, 80, 100);
			Empty.setBounds(260,260, 80, 100);
		 
		}
		public void creatBar(Graphics g, Color kleur, int x, int y, int with, int height ) {

		g.setColor(kleur);
		g.fill3DRect(x, y, with, height, true);
		
		
		}
}
