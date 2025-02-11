package Transportation;

public class Vehicle extends Transportation {
    protected String vehicleNumber;

    public Vehicle(int duration, int charge, String vehicleNumber) {
        super(duration, charge);
        this.vehicleNumber = vehicleNumber;
    }
}
