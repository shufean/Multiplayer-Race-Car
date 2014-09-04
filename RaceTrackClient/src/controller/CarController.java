package controller;

import model.Car;
import view.CarView;

public class CarController implements Runnable {


	private CarView view;
	private Car model;
	public boolean isUpPressed, isDownPressed, isSpacePressed, isRightPressed, isLeftPressed;
	
	public boolean[] pressedStatus = {false,false,false,false,false};
	public CarController(Car model, CarView view) {
		this.view = view;
		this.model = model;

		(new Thread(this)).start();
	}

	public void run() {
		while (true) {
			try {
				//receives the postion from other client and updates the position
				model.updateModel1();
				//updates the position in the screen
				view.updateView();
				Thread.sleep(200);
			} catch (Exception exc) {
				exc.printStackTrace();
				break;
			}
		}
	}

}

