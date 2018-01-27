package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

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
	private JTabbedPane graphPane;
	
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
		graphPane = new JTabbedPane();
		
		try {
			screen.setIconImage(ImageIO.read(new File("res/images/parking_sign_icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ImageIcon pieChartIcon = new ImageIcon("res/images/pie_chart_icon.png");
		ImageIcon barChartIcon = new ImageIcon("res/images/bar_chart_icon.png");
		ImageIcon lineChartIcon = new ImageIcon("res/images/line_graph_icon.png");

		screen.setSize(1980, 1080);
		screen.setResizable(false);
		screen.getContentPane().setLayout(null);
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		screen.getContentPane().add(graphPane);
		screen.getContentPane().add(barChartView);
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(carQueueView);
		screen.getContentPane().add(financialView);
		screen.getContentPane().add(timeView);
		screen.getContentPane().add(dataController);
		screen.getContentPane().add(runController);
		screen.getContentPane().add(sliderController);
				
		graphPane.addTab("Pie Chart", pieChartIcon, null, null);
		graphPane.addTab("Bar Chart", barChartIcon, null, null);
		graphPane.addTab("Line Graph", lineChartIcon, null, null);
		
		graphPane.setBounds(600, 500, 500, 500);
		barChartView.setBounds(1400, 500, 600, 600);
		carParkView.setBounds(10, 120, 800, 400);
		carQueueView.setBounds(700, 500, 235, 200);
		financialView.setBounds(1000, 60, 425, 100);
		pieChartView.setBounds(1000, 300, 200, 200);
		timeView.setBounds(635, 60, 150, 25);
		runController.setBounds(23, 60, 300, 25);
		dataController.setBounds(-250, 600, 800, 900);
		sliderController.setBounds(381, 26, 200, 80);
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);
	}
}
