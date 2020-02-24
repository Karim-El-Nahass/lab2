package Vehicles;

import java.awt.*;

/**
 * Saab95 class
 */
public class Saab95 extends Vehicle implements Movable, ICar {

    private boolean turboOn;

    /**
     * constructor for Saab95
     * @param x
     * @param y
     */

    public Saab95(double x, double y){
        super(2, 125, Color.red, "Saab95", x, y, 4.16);
        turboOn = false;
    }

    /**
     * sets turbo on
     */
    public void setTurboOn(){
	    turboOn = true;
    }

    /**
     * sets turbo off
     */
    public void setTurboOff(){
	    turboOn = false;
    }

    /**
     * @return returns speed factor
     */
    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    /**
     * **/
    public void incrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    public void decrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    // TODO fix this method according to lab pm
    public void gas(double amount){
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        decrementSpeed(amount);
    }
}
