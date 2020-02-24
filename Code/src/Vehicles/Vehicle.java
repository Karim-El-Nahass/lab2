package Vehicles;

import java.awt.*;

/**
 * General class describing vehicle
 */
public abstract class Vehicle implements Movable {

    /**
     * Constructor for vehicle.
     * @param nrDoors number of doors
     * @param enginePower engine power
     * @param color color
     * @param modelName model name
     * @param x x coordinate
     * @param y y coordinate
     * @param length length
     */
    public Vehicle(int nrDoors, double enginePower, Color color, String modelName, double x, double y, double length) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.x = x;
        this.y = y;
        this.length = length;
        stopEngine();
    }

    //-----------

    public enum direction {
        NORTH,
        EAST,
        SOUTH,
        WEST,
    }

    //-----------

    private direction currentDir = direction.NORTH;
    public void setCurrentDir(direction currentDir) {
        this.currentDir = currentDir;
    }
    public direction getCurrentDir() {
        return currentDir;
    }
    public boolean engineOn = false;

    //-----------

    private double x;
    public void setX(double x) {
        this.x = x;
    }
    public double getX() {
        return x;
    }

    //-----------

    private double y;
    public void setY(double y) {
        this.y = y;
    }
    public double getY() {
        return y;
    }

    //-----------

    private int nrDoors; // Number of doors on the car
    /** @return number of doors */
    public int getNrDoors(){
        return nrDoors;
    }

    //-----------

    public double enginePower; // Engine power of the car

    /** @return engine power */
    public double getEnginePower(){
        return enginePower;
    }

    //-----------

    public double currentSpeed; // The current speed of the car
    public void setCurrentSpeed(double currentSpeed) { this.currentSpeed = currentSpeed; }
    /** @return current speed */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    //-----------

    private Color color; // Color of the car

    /** @param clr color */
    public void setColor(Color clr){
        color = clr;
    }

    /** @return vehicle color */
    public Color getColor(){
        return color;
    }

    //-----------

    public String modelName; // The car model name
    public String getModelName() {
        return modelName;
    }

    //-----------

    private double length;
    public double getLength() { return length; }

    //-----------

    public abstract double speedFactor();
    public abstract void incrementSpeed(double amount);
    public abstract void decrementSpeed(double amount);

    //-----------

    /** starts engine */
    public void startEngine(){
        engineOn = true;
        currentSpeed = 0.1;
    }

    //-----------

    /** stops engine */
    public void stopEngine(){
        currentSpeed = 0;
        engineOn = false;
    }

    //-----------

    /** moves car forward */
    public void move() {
            switch (currentDir) {
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
    }

    /** changes direction to left */
    public void turnLeft() {
            switch (currentDir) {
                case NORTH:
                    setCurrentDir(direction.WEST);
                    break;

                case EAST:
                    setCurrentDir(direction.NORTH);
                    break;

                case SOUTH:
                    setCurrentDir(direction.EAST);
                    break;

                case WEST:
                    setCurrentDir(direction.SOUTH);
                    break;
        }
    }

    /**
     * changes direction to right
     */
    public void turnRight() {
        switch (currentDir) {
            case NORTH:
                setCurrentDir(direction.EAST);
                break;

            case EAST:
                setCurrentDir(direction.SOUTH);
                break;

            case SOUTH:
                setCurrentDir(direction.WEST);
                break;

            case WEST:
                setCurrentDir(direction.NORTH);
                break;

        }
    }

    /**
     * increases speed
     * @param amount 'amount'
     */
    // TODO fix this method according to lab pm
    public void gas(double amount){

        double temp = currentSpeed;

        if (amount >= 0 && amount <= 1) {
            incrementSpeed(amount);
        }

        if (currentSpeed < temp) {
            currentSpeed = temp;
        }
    }


    /** decreases speed
     * @param amount 'amount'
     */
    // TODO fix this method according to lab pm
    public void brake(double amount){

        double temp = currentSpeed;

        if (amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        }

        if (currentSpeed > temp) {
            currentSpeed = temp;
        }
    }

}
