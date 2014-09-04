package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;





import model.Car;
import model.Connection;
import controller.BasicCarController;
import controller.CarController;

public class CarView extends JPanel {
	
	
	private static JFrame f;
	private Car car1,car2;
	private BasicCarController controller1;
	private CarController controller2;
	Graphics offGraphics;
	Dimension offDimension;
	Image offImage;
	
	Connection conn=new Connection();
	
	String message;

	//initializes the car position and assigns the controller
	public CarView(Car carx,Car cary) {
		this.car1 = cary;
		this.car2 = carx;
		this.controller1 = new BasicCarController(carx, this);
		this.controller2 = new CarController(cary,this);
		init();
	}

	//assigns the keylisterner
	public void init() {
		setBackground(Color.WHITE);
		addKeyListener(this.controller1);
		setFocusable(true);
	}
	
	//update the view
	public void updateView() {
		repaint();
	}
	
	//update graphics
	public void update(Graphics g) {
		Dimension d = size();

		// Create the offscreen graphics context
		if ((offGraphics == null) || (d.width != offDimension.width) || (d.height != offDimension.height)) {
			offDimension = d;
		    offImage = createImage(d.width, d.height);
		    offGraphics = offImage.getGraphics();
		}

		// Erase the previous image
		offGraphics.setColor(getBackground());
		offGraphics.fillRect(0, 0, d.width, d.height);
		offGraphics.setColor(Color.black);

		// Paint the frame into the image
		//System.out.println("before paint graphics ");
		
		Image background = Toolkit.getDefaultToolkit().getImage("OwnImages//background.jpg");
		offGraphics.drawImage(background, 0,-18, this);
		
		if (car1.getBody_x()!=0 && car1.getBody_y()!=0)
			offGraphics.drawImage(car1.img[car1.getPosition()], car1.getBody_x(),car1.getBody_y(), this);
		offGraphics.drawImage(car2.img[car2.getPosition()], car2.getBody_x(),car2.getBody_y(), this);
		
		
		/*
		///these two lines make phasee 1
		offGraphics.drawLine(20, 20, 20, 660);//leftmost line
		offGraphics.drawLine(220, 200, 220, 480);//second leftmost line

		//these two lines make phase 2
		offGraphics.drawLine(20, 20, 1320, 20);//topmost line
		offGraphics.drawLine(220, 200, 1120, 200);//second top most line

		//these two lines make phase 3
		offGraphics.drawLine(220, 480, 1100, 480);//before bottom most
		offGraphics.drawLine(20, 660, 1100, 660);//bottom most

		//these two lines make phase 4
		offGraphics.drawLine(1120, 200, 1120, 660);//before most right
		offGraphics.drawLine(1320, 20, 1320, 660);//most right

		//ending line
		offGraphics.drawLine(1100, 480, 1100, 660);
		*/
		// Paint the image onto the screen
		g.drawImage(offImage, 0, 0, null);

	}

	//paint method to call update graphics again
	public void paint(Graphics g) {
		update(g);
	}

}