package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;

import model.SimulatorModel;

@SuppressWarnings("serial")
public class FinancialView extends AbstractView{

	private JLabel blank, lastHour, lastDay, lastWeek, totalAll;
	private JLabel regLabel, regHourlyData, regDailyData, regWeeklyData ,regTotalData;
	private JLabel subLabel, subHourlyData, subDailyData, subWeeklyData ,subTotalData;
	private JLabel resLabel, resHourlyData, resDailyData, resWeeklyData ,resTotalData;
	private JLabel totalLabel, totalHourlyData, totalDailyData, totalWeeklyData ,totalTotalData;
	
	public FinancialView(SimulatorModel simulator) {	
		super(simulator);
		
		setLayout(new GridLayout(5, 5));
		
		blank = new JLabel("");
		lastHour = new JLabel("Last hour");
		lastDay = new JLabel("Last Day");
		lastWeek = new JLabel("Last Week");
		totalAll = new JLabel("Total");
		
		regLabel = new JLabel("Regular:");
		regHourlyData = new JLabel("0");
		regDailyData = new JLabel("0");
		regWeeklyData = new JLabel("0");
		regTotalData = new JLabel("0");
		
		subLabel = new JLabel("Subscription:");
		subHourlyData = new JLabel("");
		subDailyData = new JLabel("");
		subWeeklyData = new JLabel("0");
		subTotalData = new JLabel("0");
		
		resLabel = new JLabel("Reservation:");
		resHourlyData = new JLabel("0");
		resDailyData = new JLabel("0");
		resWeeklyData = new JLabel("0");
		resTotalData = new JLabel("0");
		
		totalLabel = new JLabel("Total:");
		totalHourlyData = new JLabel("0");
		totalDailyData = new JLabel("0");
		totalWeeklyData = new JLabel("0");
		totalTotalData = new JLabel("0");
	
		add(blank);
		add(lastHour);
		add(lastDay);
		add(lastWeek);
		add(totalAll);
		
		add(regLabel);
		add(regHourlyData);
		add(regDailyData);
		add(regWeeklyData);
		add(regTotalData);
		
		add(subLabel);
		add(subHourlyData);
		add(subDailyData);
		add(subWeeklyData);
		add(subTotalData);
		
		add(resLabel);
		add(resHourlyData);
		add(resDailyData);
		add(resWeeklyData);
		add(resTotalData);
		
		add(totalLabel);
		add(totalHourlyData);
		add(totalDailyData);
		add(totalWeeklyData);
		add(totalTotalData);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
