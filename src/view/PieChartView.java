package view;

import java.awt.Color;
import java.awt.Graphics;

import model.SimulatorModel;

@SuppressWarnings("serial")
public class PieChartView extends AbstractView {
	
	public PieChartView(SimulatorModel simulator) {
		super(simulator);
	}
	
	public void paintComponent(Graphics g) {
		int reg = getModel().getTotalParkedReg();
		int sub = getModel().getTotalParkedSub();
		int res = getModel().getTotalParkedRes();
		int empty = getModel().getNumberOfOpenSpots();
		
		
		int sum = 0;
		
		double percentage = 360.0 / 540;
		
		reg = (int) (reg * percentage);
		sub = (int) (sub * percentage);
		res = (int) (res * percentage);
		empty = (int) (empty * percentage);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 200);
	    g.setColor(Color.RED);
	    g.fillArc(0, 0, 200, 200, 0, reg);
	    sum += reg;
	    g.setColor(Color.BLUE);
	    g.fillArc(0, 0, 200, 200, sum, sub);
	    sum += sub;
	    g.setColor(Color.YELLOW);
	    g.fillArc(0, 0, 200, 200, sum, res);
	    sum += res;
	    g.setColor(Color.WHITE);
	    g.fillArc(0, 0, 200, 200, sum, empty);

	}
}
