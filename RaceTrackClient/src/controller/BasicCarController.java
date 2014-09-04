package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Car;
import view.CarView;

public class BasicCarController implements KeyListener, Runnable {

	private CarView view;
	private Car model;
	//to check the status of keyboard
	public boolean isUpPressed, isDownPressed, isSpacePressed, isRightPressed, isLeftPressed;
	
	public boolean[] pressedStatus = {false,false,false,false,false};
	
	public BasicCarController(Car model, CarView view) {
		this.view = view;
		this.model = model;

		(new Thread(this)).start();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	//set values to false when keyboard is pressed
	@Override
	public void keyPressed(KeyEvent ke) {
		
		if (ke.getKeyCode()==model.getUp()){
			pressedStatus[0] = true;
		}
		
		else if (ke.getKeyCode()==model.getDown()){
			pressedStatus[1] = true;
		}
		
		else if (ke.getKeyCode()==model.getRight()){
			pressedStatus[3] = true;
		}
		
		else if (ke.getKeyCode()==model.getLeft()){
			pressedStatus[4] = true;
		}
		
		}
	
	
	//set values to false when keyboard is released
	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode()==model.getUp()){
			pressedStatus[0] = false;
		}
		
		else if (e.getKeyCode()==model.getDown()){
			pressedStatus[1] = false;
		}
		
		else if (e.getKeyCode()==model.getRight()){
			pressedStatus[3] = false;
		}
		
		else if (e.getKeyCode()==model.getLeft()){
			pressedStatus[4] = false;
		}
			}

	public void run() {
		while (true) {
			try {
				//update the values of position of the car
					model.updateModel(pressedStatus);
				//update the position of the car in the screen
				view.updateView();
				Thread.sleep(200);
			} catch (Exception exc) {
				exc.printStackTrace();
				break;
			}
		}
	}

}
