/*
 * Design of the car, get car positions make movement
 * 
 */

package model;

import java.awt.Image;
import view.Main;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Car {
	Connection conn=new Connection();
	public String message;
		
	private int UP = KeyEvent.VK_UP;
	private int DOWN = KeyEvent.VK_DOWN;
	private int RIGHT = KeyEvent.VK_RIGHT;
	private int LEFT= KeyEvent.VK_LEFT;
	private int length;
	private int width;
	private int position=0;
	private boolean Collision = false;
	
	public Image img[];
	
	 String values[]= new String[4];

	private int body_x;
	private int body_y;

	private static int POSITIVE_STEP = 3;
	private static int NEGATIVE_STEP = -3;
	
	public Car(int x, int y) {
		initCar(x,y);
	}
	
	public Car(int u, int d, int r, int l,int x, int y) {
		UP = u;
		DOWN = d;
		RIGHT = r;
		LEFT = l;
		initCar(x,y);
	}

	//initializes the position of the car
	public void initCar(int x, int y) {
	    
		System.out.println("yes");
		img = new Image[16];
		for (int i = 1 ; i <= 16 ; i++) {
		   			if (x==0) // i.e othercar always start with x=0 as initialized at Car.java Cary;
				img[i-1] = Toolkit.getDefaultToolkit().getImage("OwnImages//"+(i-1)+".gif"); 
			else
				img[i-1] = Toolkit.getDefaultToolkit().getImage("OtherImages//r"+(i-1)+".gif");
		}
		
		length = 15;
		width = 20;

		body_x = x;
		body_y = y;
	}
	
	//checks the collision with the track
	public boolean isCollision(){
		
		if(body_x>=175 && body_x<=1115 && body_y>=155 && body_y<=475){ 
			Collision = true;
			//System.out.println("Collition 1----------------------");
			
		}
		
		else if (body_x>=1055 && body_x<=1115 &&body_y>=475 && body_y<=660){
			Collision = true;
			//System.out.println("Collition 2----------------------");
			//return true;
			Main.createStartupScreen("end.gif", 2000);
			
			
		}
		else if (body_x>=20 && body_x<=1275 && body_y>=20 && body_y<=615){
			Collision = false;
			//System.out.println("OUTER RECTANGLE and body_x:"+body_x+"and body_y"+body_y);
			//return false;
		}
		else {
			Collision = true;
		}
		
		
		return Collision;
		
	}
	
	//updates the position of the car and sends position parameter to the server
	public void updateModel(boolean[] pressedStatus) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		if(pressedStatus[0]==true) //for up arrow
    	{
    			if (position == 0) {
		    			
		    			body_x +=0;
		    			body_y -=5;
		    			if(isCollision()){
		    				body_x -=0;
			    			body_y +=10;
			    			crashSound();//to produce crash sound
		    			}
		    			
		    		}
		    		
		    		else if (position == 1){
		    			body_x +=2; 
		    			body_y -=5; 
		    			if(isCollision()){
		    				body_x -=4;
			    			body_y +=10;
			    			crashSound();
		    			}
		    			}
		    		else if (position == 2){
		    			body_x +=3; 
		    			body_y -=3; 
		    			if(isCollision()){
		    				body_x -=6;
			    			body_y +=6;
			    			crashSound();
		    			}
		    		}
		    		else if (position == 3) {
		    			body_x +=5; 
		    			body_y -=2; 
		    			if(isCollision()){
		    				body_x -=10;
			    			body_y +=4;
			    			crashSound();
		    			}
		    		}
		    		
		    		else if (position == 4){
		    			body_x +=5;
		    			body_y -=0;
		    			if(isCollision()){
		    				body_x -=10;
			    			body_y +=0;
			    			crashSound();
		    			}
		    		}
		    		
		    		else if (position == 5) {
		    			body_x +=5;
		    			body_y +=2;  
		    			if(isCollision()){
		    				body_x -=10;
			    			body_y -=4;
			    			crashSound();
		    			}
		    		}
		    		
		    		else if (position == 6) {
		    			body_x +=3;
		    			body_y +=3; 
		    			if(isCollision()){
		    				body_x -=6;
			    			body_y -=6;
			    			crashSound();
		    			}
		    		}
		    		else if (position == 7) {
		    			body_x +=2;
		    			body_y +=5;  
		    			if(isCollision()){
		    				body_x -=4;
			    			body_y -=10;
			    			crashSound();
		    			}
		    		}
		    		else if (position == 8) {
		    			body_x +=0;
		    			body_y +=5; 
		    			if(isCollision()){
		    				body_x -=0;
			    			body_y -=10;
			    			crashSound();
		    			}
		    		}
		    		
		    		else if (position == 9 ) {
		    			body_x -=2;
		    			body_y +=5;  
		    			if(isCollision()){
		    				body_x +=4;
			    			body_y -=10;
			    			crashSound();
		    			}
		    		}
		    		
		    		else if (position == 10 ) {
		    			body_x -=3;
		    			body_y +=3;   
		    			if(isCollision()){
		    				body_x +=6;
			    			body_y -=6;
			    			crashSound();
		    			}
		    		}	else if (position == 11 ) {
		    			body_x -=5;
		    			body_y +=2; 
		    			if(isCollision()){
		    				body_x +=10;
			    			body_y -=4;
			    			crashSound();
		    			}
		    		}	else if (position == 12 ) {
		    			body_x -=5;
		    			body_y +=0; 
		    			if(isCollision()){
		    				body_x +=10;
			    			body_y -=0;
			    			crashSound();
		    			}
		    		}
		    		
		    		else if (position == 13 ) {
		    			body_x -=5;
		    			body_y -=2; 
		    			if(isCollision()){
		    				body_x +=10;
			    			body_y +=4;
			    			crashSound();
		    			}
		    		}
		    		else if (position == 14 ) {
		    			body_x -=3;
		    			body_y -=3;  
		    			if(isCollision()){
		    				body_x +=6;
			    			body_y +=6;
			    			crashSound();
		    			}
		    		}
		    		
		    		else if (position == 15 ) {
		    			body_x -=2;
		    			body_y -=5; 
		    			if(isCollision()){
		    				body_x +=4;
			    			body_y +=10;
			    			crashSound();
		    			}
		    		}
    	 		
    	}	
    	
    	if(pressedStatus[1]==true) // down arrow
    	{
    		if (position == 0) {
    			body_x -=0;
    			body_y +=5;
    			if(isCollision()){
    				body_x +=0;
	    			body_y -=10;
	    			crashSound();
    			}
    		}
    		
    		else if (position == 1){
    			body_x -=2; //1.54
    			body_y +=5; //4.75
    			if(isCollision()){
    				body_x +=4;
	    			body_y -=10;
	    			crashSound();
    			}
    			}
    		else if (position == 2){
    			body_x -=3; 
    			body_y +=3; 
    			if(isCollision()){
    				body_x +=6;
	    			body_y -=6;
	    			crashSound();
    			}
    		}
    		else if (position == 3) {
    			body_x -=5; 
    			body_y +=2; 
    			if(isCollision()){
    				body_x +=10;
	    			body_y -=4;
	    			crashSound();
    			}
    		}
    		
    		else if (position == 4){
    			body_x -=5;
    			body_y +=0;
    			if(isCollision()){
    				body_x +=10;
	    			body_y -=0;
	    			crashSound();
    			}
    		}
    		
    		else if (position == 5) {
    			body_x -=5;
    			body_y -=2; 
    			if(isCollision()){
    				body_x +=10;
	    			body_y +=4;
	    			crashSound();
    			}
    		}
    		
    		else if (position == 6) {
    			body_x -=3;
    			body_y -=3; 
    			if(isCollision()){
    				body_x +=6;
	    			body_y +=6;
	    			crashSound();
    			}
    		}
    		else if (position == 7) {
    			body_x -=2;
    			body_y -=5;
    			if(isCollision()){
    				body_x +=4;
	    			body_y +=10;
	    			crashSound();
    			}
    		}
    		else if (position == 8) {
    			body_x -=0;
    			body_y -=5; 
    			if(isCollision()){
    				body_x +=0;
	    			body_y +=10;
	    			crashSound();
    			}
    		}
    		
    		else if (position == 9 ) {
    			body_x +=2;
    			body_y -=5;  
    			if(isCollision()){
    				body_x -=4;
	    			body_y +=10;
	    			crashSound();
    			}
    		}
    		
    		else if (position == 10 ) {
    			body_x +=3;
    			body_y -=3;  
    			if(isCollision()){
    				body_x -=6;
	    			body_y +=6;
	    			crashSound();
    			}
    		}	else if (position == 11 ) {
    			body_x +=5;
    			body_y -=2; 
    			if(isCollision()){
    				body_x -=10;
	    			body_y +=4;
	    			crashSound();
    			}
    		}	else if (position == 12 ) {
    			body_x +=5;
    			body_y -=0; 
    			if(isCollision()){
    				body_x -=10;
	    			body_y +=0;
	    			crashSound();
    			}
    		}
    		
    		else if (position == 13 ) {
    			body_x +=5;
    			body_y +=2; 
    			if(isCollision()){
    				body_x -=10;
	    			body_y -=4;
	    			crashSound();
    			}
    		}
    		else if (position == 14 ) {
    			body_x +=3;
    			body_y +=3;  
    			if(isCollision()){
    				body_x -=6;
	    			body_y -=6;
	    			crashSound();
    			}
    		}
    		else if (position == 15 ) {
    			body_x +=2;
    			body_y +=5; 
    			if(isCollision()){
    				body_x -=4;
	    			body_y -=10;
	    			crashSound();
    			}
    		}
    	   		
    	}	
    	
    	if(pressedStatus[4]==true) // for left arrow
    	{
    		if(position==0)
    			position+=16;
    		position-=1;
    		position=position%16;
    	    	   		
    	}	
    	
    	if(pressedStatus[3]==true) // for right arrow
    	{
    		position+=1;
    		position=position%16;
    	}	
	
		
    	try {
    		conn.send_to_Server(11,body_x,body_y,position);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
	}
	
	//receives the message from the server and updates the positions
	public void updateModel1(){
		try {
    		message=conn.receive_from_Server();
			parseMessageAndSetValues(message);
			int id=Integer.parseInt(values[0]);
			
				body_x=Integer.parseInt(values[1]);
				body_y=Integer.parseInt(values[2]);
				position=Integer.parseInt(values[3]);
			
		} catch (IOException e) {
				e.printStackTrace();
		}
		
	}
	
	public void setConnection(Connection conn1){
		this.conn=conn1;
	}

	public int getBody_x() {
		return body_x;
	}

	public void setBody_x(int body_x) {
		this.body_x = body_x;
	}

	public int getBody_y() {
		return body_y;
	}

	public void setBody_y(int body_y) {
		this.body_y = body_y;
	}
	
	public int getPosition(){
		return position;
	}
	
	public int getUp(){
		return UP;
	}
	public int getDown(){
		return DOWN;
	}
	public int getLeft(){
		return LEFT;
	}
	public int getRight(){
		return RIGHT;
	}
	
	public void parseMessageAndSetValues(String message1){
			StringTokenizer st = new StringTokenizer(message, ":"); 
			int n=0;
			int i=0;
			while(st.hasMoreTokens()) { 
					
						values[i]=st.nextToken();
						// System.out.println(values[i]);
				i++;
					
				} 
		
	}
/*
	public void crashSound() throws IOException, LineUnavailableException, UnsupportedAudioFileException
	{
		Clip clip = AudioSystem.getClip(); 
		InputStream s=Car.class.getResourceAsStream("explosion.wav");
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(s); 
        clip.open(inputStream); 
        clip.start();

	}
*/	
	
	public void crashSound(){
		try {
			java.applet.AudioClip clip =
			java.applet.Applet.newAudioClip(new java.net.URL("file:sounds//explosion.wav"));
			clip.play();
			} catch (java.net.MalformedURLException murle) {
			//System.out.println(murle);
			}
	}
	/*
	public static synchronized void crashSound() {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		        		Car.class.getResourceAsStream("audio//crash4.wav"));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
		*/
	
}
