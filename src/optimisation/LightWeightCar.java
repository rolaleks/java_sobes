package optimisation;

class LightWeightCar extends Car{

    @Override
    void open() {
        System.out.println("optimisation.Car is open");
    }

    @Override
    public void move() {
        System.out.println("optimisation.Car is moving");
    }

    @Override
    public void stop() {
        System.out.println("optimisation.Car is stopped");
    }
}