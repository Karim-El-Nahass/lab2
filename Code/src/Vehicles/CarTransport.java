package Vehicles;

import java.awt.*;
import java.util.Stack;

/** Truck class */
public class CarTransport extends Vehicle implements ITruck {

    /**
     * constructor for truck
     * @param color color
     */
    public CarTransport(Color color) {
        super(2, 500, color, "Man", 0, 0, 30);
        flatBedUp = false;
    }

    //-----------

    public Stack<Vehicle> stack = new Stack<>();

    //-----------

    boolean flatBedUp;

    /*
     * Makes the flatbed go up.
     * @param flatBedUp flat bed up
     */
    // public void setFlatBedUp(boolean flatBedUp) { this.flatBedUp = flatBedUp; }

    /**
     * Checks if flatbed is up.
     * @return flatBedUp
     */
    public boolean getFlatBedUp() {
        return flatBedUp;
    }

    //-----------

    /**
     * Loads the truck with vehicles with given conditions.
     * @param vehicle vehicle
     * @throws Exception exception
     */
    public void load(Vehicle vehicle) throws Exception {

        boolean conditionX = (vehicle.getX() - this.getX()) <= 10;
        boolean conditionY = (vehicle.getY() - this.getY()) <= 10;
        boolean vehicleIsClose = conditionX && conditionY;

        if ((vehicle.getLength() <= 5.5) && !(flatBedUp) && vehicleIsClose && (stack.size() < 8)) {
            stack.push(vehicle);
        } else if (stack.size() == 8) {
            throw new Exception("Truck Full!");
        } else if (vehicle.getLength() > 5.5) {
            throw new Exception("Vehicle too large!");
        } else if (flatBedUp) {
            throw new Exception("Flatbed is up!");
        } else if (!vehicleIsClose) {
            throw new Exception("Vehicle is too far away!");
        }
    }

    //-----------

    /**
     * Unloads vehicles from the flatbed.
     */
    public void unload() {

        if (!flatBedUp) {
            Vehicle vehicle = stack.pop();
            vehicle.setX(this.getX() + 10);
            vehicle.setY(this.getY() + 10);
        }
    }

    //-----------

    /**
     * raises the flatbed
     */
    public void raise() {
        if (getCurrentSpeed() == 0) {
            flatBedUp = true;
        }
    }

    //-----------

    /**
     * lowers the flatbed
     */
    public void lower() {

        if (getCurrentSpeed() == 0) {
            flatBedUp = false;
        }
    }

    //-----------

    /**
     * Decides speed.
     * @return returns speed factor
     */
    public double speedFactor() {
        return enginePower * 0.01;
    }

    //-----------

    /**
     * Increases speed.
     * @param amount amount
     */
    public void incrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    //-----------

    /**
     * Decreases speed.
     * @param amount amount
     */
    public void decrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    //-----------

    /**
     * Describes how both truck and vehicles on the truck moves.
     */
    @Override
    public void move() {
        switch (this.getCurrentDir()) {
            case NORTH:
                setY(getY() - getCurrentSpeed());
                break;

            case EAST:
                setX(getX() + getCurrentSpeed());
                break;

            case SOUTH:
                setY(getY() + getCurrentSpeed());
                break;

            case WEST:
                setX(getX() - getCurrentSpeed());
                break;
        }

        for (Vehicle car: stack) {
            car.setX(this.getX());
            car.setY(this.getY());
        }
    }
}