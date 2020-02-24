package Vehicles;

import java.awt.*;

/**
 * Volvo240 class
 */
public class Volvo240 extends Vehicle implements ICar {

    private final static double trimFactor = 1.25;

    public Volvo240(double x, double y){
        super(4, 100, Color.black, "Volvo240",x ,y, 4.9);
    }

    /**
     * @return returns speed factor
     */
    @Override
    public double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }

    public void incrementSpeed(double amount){
	    currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    // TODO fix this method according to lab pm
    public void gas(double amount) {
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount) {
        decrementSpeed(amount);
    }
}