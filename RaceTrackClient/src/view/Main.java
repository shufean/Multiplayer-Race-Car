/*
 * Main class for CLient Project
 */

package view;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Car;
import model.Connection;

public class Main {

	
	public static void main(String[] args) throws IOException {
		//creates connection object
		Connection conn =new Connection();
		//establishes connection with the server
		conn.runClient();
		//initializes 1st car object which will be handled by keyboard
		Car carx = new Car(1200,450);
		//initializes object of second car which will be visible as the other client gets into the connection with the server
		Car cary = new Car(KeyEvent.VK_W,KeyEvent.VK_X,KeyEvent.VK_D,KeyEvent.VK_A,0,0);
		//gets car x and cary into the network
		cary.setConnection(conn);
		carx.setConnection(conn);
		//creates instance of view object in which both cars and tracks are visible in the frame
		CarView view = new CarView(carx, cary);
		
		
		
		createGui(view);
		createStartupScreen();
	}
	
	
	// play sound 
	public static void playSound(String soundFile){
		//soundFile = "crash4.wav";
		try {
			java.applet.AudioClip clip =
			java.applet.Applet.newAudioClip(new java.net.URL("file:sounds//"+soundFile));
			clip.play();
			} catch (java.net.MalformedURLException murle) {
			//System.out.println(murle);
			}
	}
	
	
	// thread for waiting
	public static void waitThread(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	
	// show startup screen like: 3 2 1 goooo
	public static void createStartupScreen(){
		playSound("countdown.wav");
		

		createStartupScreen("three.gif", 1000);
		createStartupScreen("two.gif", 1000);
		createStartupScreen("one.gif",1000);
		playSound("truckStartup.wav");
		playSound("engineStart.wav");
		createStartupScreen("go.gif", 5000);

	}
	
	
	// show image and wait until given time
public static void createStartupScreen(String imageFile, long waitTime){
	//imageFile = "one.gif";
		final JFrame startupFrame = new JFrame();
		//JButton button = new JButton();
		startupFrame.setSize(500, 340);
		startupFrame.setLocation(400, 200);
		//button.setText("Click me to show dialog!");
		//startupFrame.setVisible(true);
		
		//playSound("countdown.wav");
		
		JLabel labelOne=null;

		try {
			ImageIcon imageOne = new ImageIcon(ImageIO.read(new File("OwnImages//"+imageFile)));
			
			labelOne = new JLabel(imageOne);
			startupFrame.add(labelOne);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startupFrame.add(labelOne);
		startupFrame.setVisible(true);
		waitThread(waitTime);		
		startupFrame.setVisible(false);		
	}
	

	// startup main user interface
	public static void createGui(CarView view) {
		
		//creates frame
		JFrame f = new JFrame();

//	   	try {
//    		f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("OwnImages//background.jpg")))));
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
		f.setContentPane(view);
		f.setSize(1350, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
