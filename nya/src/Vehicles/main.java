package Vehicles;

public class main {
    public static void main(String[] args) {
        Garage<Volvo240> garage = new Garage<Volvo240>(5);
        Volvo240 testCar = new Volvo240(0,0);
        garage.insert(testCar);
        Volvo240 car2 = garage.pickUp(testCar);
        System.out.print(car2);

    }
}