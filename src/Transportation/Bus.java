package Transportation;

public class Bus extends Vehicle {
    private final String rideStop;
    private final String dropStop;

    public Bus(int duration, int charge, String vehicleNumber, String rideStop, String dropStop) {
        super(duration, charge, vehicleNumber);
        this.rideStop = rideStop;
        this.dropStop = dropStop;
    }

    public String getInfo() { return "\t" + vehicleNumber + "번 버스"; }
    public String getRideStop() {
        return "승차: " + rideStop;
    }
    public String getDropStop() {
        return "하차: " + dropStop;
    }
}
