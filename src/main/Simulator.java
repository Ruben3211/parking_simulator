package main;

import javax.swing.JFrame;

import controller.*;
import model.SimulatorModel;
import view.*;

/**
 * This class is responsible for assembling the application. It creates an
 * instance of the model and adds this, with all the controllers and views to
 * a screen. It also sets all the controllers and views at its designated
 * places. And makes sure everything is made visible. 
 * 
 * @author Rick Zwaneveld
 * @version 23-01-2018
 */

public class Simulator {
	
	private SimulatorModel simulatorLogic;
	
	private JFrame screen;
	
	private AbstractView barChartView;
	private AbstractView carParkView;
	private AbstractView carQueueView;
	private AbstractView financialView;
	private AbstractView pieChartView;
	private AbstractView timeView;
	
	private AbstractController dataController;
	private AbstractController runController;	
	private AbstractController sliderController;
	
	/**
	 * The constructor for this class.
	 */
	public Simulator() {
		simulatorLogic = new SimulatorModel(3, 6, 30);
		barChartView = new BarChartView(simulatorLogic);
		carParkView = new CarParkView(simulatorLogic);
		carQueueView = new CarQueueView(simulatorLogic);
		financialView = new FinancialView(simulatorLogic);
		pieChartView = new PieChartView(simulatorLogic);
		timeView = new TimeView(simulatorLogic);
		dataController = new DataController(simulatorLogic);
		runController = new RunController(simulatorLogic);
		sliderController = new SliderController(simulatorLogic);
		
		screen = new JFrame("Parking Garage Simulator");
		screen.setSize(1980, 1080);
		screen.setResizable(false);
		screen.setLayout(null);
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		screen.getContentPane().add(barChartView);
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(carQueueView);
		screen.getContentPane().add(financialView);
		screen.getContentPane().add(pieChartView);
		screen.getContentPane().add(timeView);
		screen.getContentPane().add(dataController);
		screen.getContentPane().add(runController);
		screen.getContentPane().add(sliderController);
		
		barChartView.setBounds(1400, 500, 600,600);
		carParkView.setBounds(10, 120, 800, 400);
		carQueueView.setBounds(700, 500, 235, 200);
		financialView.setBounds(1000, 60, 425, 100);
		pieChartView.setBounds(1000, 300, 200, 200);
		timeView.setBounds(10, 600, 100, 25);
		runController.setBounds(10, 60, 300, 25);
		dataController.setBounds(-250, 600, 800, 900);
		sliderController.setBounds(20, 60, 800, 400);
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);
	}
}
