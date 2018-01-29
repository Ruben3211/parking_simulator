package model;

import java.applet.AudioClip;
import java.awt.event.ActionListener;
import java.io.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	
	public static void main(String[] args) {
		
		}
	
	public static void playSound (String fileName) {
		String soundFileName = fileName;
		  try {
			  FileInputStream file = new FileInputStream(fileName);
			  Player playMP3 = new Player(file);
			  playMP3.play();
		}
		  catch(Exception exc){
			    exc.printStackTrace();
			    System.out.println("Failed to play the file.");
			}
	}
}
