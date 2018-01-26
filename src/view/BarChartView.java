package view;

import model.SimulatorModel;
import java.awt.*;




@SuppressWarnings("serial")
public class BarChartView extends AbstractView{


	public BarChartView(SimulatorModel simulator) {
		super(simulator);
	}
		public void paintComponent(Graphics g) {
			int reg = getModel().getTotalParkedReg();
			int sub = getModel().getTotalParkedSub();
			int res = getModel().getTotalParkedRes();
			int empty = getModel().getNumberOfOpenSpots();
			Color a = new Color(238, 238, 238);
			g.setColor(a);
			g.fillRect(0, 0, 600, 600);
			g.setColor(Color.RED);
			g.fill3DRect(60, 250, 80, reg, false);
	
			g.setColor(Color.BLUE);
		    g.fill3DRect(360, 250, 80, -sub, true);
		
		   g.setColor(Color.YELLOW);
		   // g.fill3DRect(160, 250, 80, res, true);
		   g.fillRect(160, 250, 80, -res);
		    
		    g.setColor(Color.WHITE);
		    g.fill3DRect(260, 250, 80, empty, true);
		}
	
}
