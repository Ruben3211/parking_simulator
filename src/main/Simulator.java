package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import controller.*;
import model.SimulatorModel;
import view.*;

/**
 * This class is responsible for assembling the application. It creates an
 * instance of the model and adds this, with all the controllers and views to
 * a screen. It also sets all the controllers and views at their designated
 * places and makes sure everything is made visible. 
 * 
 * @author Rick Zwaneveld
 * @version 27-01-2018
 */

public class Simulator {
	
	private SimulatorModel simulatorLogic;
	
	private JFrame screen;
	private JTabbedPane graphTabbedPane;
	
	//private AbstractView barChartView;
	private AbstractView carParkView;
	private AbstractView carQueueView;
	private AbstractView financialView;
	//private AbstractView lineGraphView;
	private AbstractView pieChartView;
	private AbstractView timeView;
	
	//private AbstractController dataController;
	private AbstractController resetController;
	private AbstractController runController;	
	private AbstractController sliderController;
	private AbstractController dataController;
	
	
	/**
	 * The constructor for the class Simulator.
	 */
	public Simulator() {
		simulatorLogic = new SimulatorModel(3, 6, 30);
		
		screen = new JFrame("Parking Garage Simulator");
		graphTabbedPane = new JTabbedPane();
		
		//barChartView = new BarChartView(simulatorLogic);
		carParkView = new CarParkView(simulatorLogic);
		carQueueView = new CarQueueView(simulatorLogic);
		financialView = new FinancialView(simulatorLogic);
		//lineGraphView = new LineGraphView(simulatorLogic);
		pieChartView = new PieChartView(simulatorLogic);
		timeView = new TimeView(simulatorLogic);
		dataController = new DataController(simulatorLogic);
		
		//dataController = new DataController(simulatorLogic);
		resetController = new ResetController(simulatorLogic);
		runController = new RunController(simulatorLogic);
		sliderController = new SliderController(simulatorLogic);
		
		ImageIcon parkingSignIcon = new ImageIcon("res/images/parking_sign_icon.png");
		ImageIcon pieChartIcon = new ImageIcon("res/images/pie_chart_icon.png");
		ImageIcon barChartIcon = new ImageIcon("res/images/bar_chart_icon.png");
		ImageIcon lineChartIcon = new ImageIcon("res/images/line_graph_icon.png");
		
		screen.setSize(1980, 1080);
		screen.setResizable(false);
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setIconImage(parkingSignIcon.getImage());
		
		screen.getContentPane().setLayout(null);
		screen.getContentPane().add(graphTabbedPane);
		//screen.getContentPane().add(barChartView);
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(carQueueView);
		screen.getContentPane().add(financialView);
		screen.getContentPane().add(timeView);
		//screen.getContentPane().add(dataController);
		screen.getContentPane().add(resetController);
		screen.getContentPane().add(runController);
		screen.getContentPane().add(sliderController);
		screen.getContentPane().add(dataController);
				
		graphTabbedPane.addTab("Pie Chart", pieChartIcon, pieChartView, null);
		graphTabbedPane.addTab("Bar Chart", barChartIcon, null, null);
		graphTabbedPane.addTab("Line Graph", lineChartIcon, null, null);
		
		graphTabbedPane.setBounds(1000, 40, 500, 500);
		//barChartView.setBounds(1400, 500, 600, 600);
		carParkView.setBounds(6, 6, 800, 400);
		carQueueView.setBounds(1200, 610, 275, 176);
		financialView.setBounds(1000, 610, 190, 160);
		timeView.setBounds(1000, 575, 200, 25);
		//dataController.setBounds(-250, 600, 800, 900);
		runController.setBounds(80, 425, 300, 25);
		resetController.setBounds(450, 419, 60, 35);
		sliderController.setBounds(595, 410, 200, 55);
		dataController.setBounds(0, 600, 800, 900);
		
		screen.setVisible(true);
	}
}
