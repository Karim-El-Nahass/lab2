package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Vehicles.*;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240(0, 100));
        cc.cars.add(new Saab95(0, 250));
        cc.cars.add(new Scania(0,400));

        for (Vehicle car : cc.cars) {
            car.setCurrentDir(Vehicle.direction.WEST);
        }

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (Vehicle car : cars) {
                car.move();
                if (car.getX() > 685) {
                    car.setX(685);
                    car.stopEngine();
                    car.setCurrentDir(Vehicle.direction.WEST);
                    car.startEngine();

                } else if (car.getX() < 0) {
                    car.setX(0);
                    car.stopEngine();
                    car.setCurrentDir(Vehicle.direction.EAST);
                    car.startEngine();
                }
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveit(car, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            if (car.engineOn) {
                car.gas(gas);

            }
        }
    }

    // Calls the gas method for each car once
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            if (car.engineOn && car.getCurrentSpeed() > 0) {
                car.brake(brake);
                if (car.getCurrentSpeed() < 0) {
                    car.setCurrentSpeed(0);
                }
            }
        }
    }

    void startEngine() {
        for (Vehicle car : cars) {
            if (!car.engineOn) {
                car.startEngine();
            }
        }
    }
    void stopEngine() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    void turboOn() {
        for (Vehicle car : cars) {
            if (car.getModelName().equals("Saab95")) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Vehicle car : cars) {
            if (car.getModelName().equals("Saab95")) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed() {
        for (Vehicle car : cars) {
            if (car.getModelName().equals("Scania")) {
                ((Scania) car).raise(30);
                System.out.println(((Scania) car).getFlatBedAngle());
            }
        }
    }

    void lowerBed() {
        for (Vehicle car : cars) {
            if (car.getModelName().equals("Scania")) {
                ((Scania) car).lower(30);
                System.out.println(((Scania) car).getFlatBedAngle());

            }
        }

    }

}