package Vehicles;

import java.util.ArrayList;

/**
 * Garage class.
 * @param <T>
 */
public class Garage<T extends ICar> {
    int max;
    T type;
    public ArrayList<T> spaces = new ArrayList<T>();

    /**
     * constructor for Garage.
     * @param max
     */
   public Garage(int max) {
        this.max = max;
//        this.type = type;
    }

    /**
     * Inserts vehicle if there is space.
     * @param vehicle
     */
    public void insert(T vehicle) {
        if (spaces.size() != max) {
            spaces.add(vehicle);
        }
    }

    /**
     * picks up vehicle if it exists, if not returns null.
     * @param vehicle
     * @return
     */
    public T pickUp(T vehicle) {
        T x = null;

        for (T v : spaces) {
             if (v.equals(vehicle)) {

                 x = vehicle;
                 spaces.remove(vehicle);
                 break;
             }
        }
        return x;
    }
}