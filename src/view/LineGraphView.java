package view;

import model.SimulatorModel;
import java.awt.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("serial")
public class LineGraphView extends AbstractView {
	
	private static final int PAD = 20;
	private int valuesY;
	ArrayList<Integer> data;
	
	public LineGraphView(SimulatorModel simulator) {
		super(simulator);
		
		int valuesY = simulator.getTotalIncome();
		data = simulator.data;
	}
	
	public void paintComponent(Graphics g ) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();
		g2.drawLine(PAD, PAD, PAD, height-PAD);
		g2.drawLine(PAD, height-PAD, width-PAD, height-PAD);
        double xScale = (width- 2*PAD) / (data.size() + 1);
        double maxValue = 100.0;
        double yScale = (height -  2 * PAD)/ maxValue;
        
        int x0 = PAD;
        int y0 = height - PAD;
        g2.setPaint(Color.red);
        for(int i = 0; i < data.size(); i++) {
        	int x = x0 + (int)(xScale * i);
        	int y = y0 - (int)(data.get(i));
        	g2.fillOval(x - 2, y - 2, 4, 4);
        }
		
	}
}
