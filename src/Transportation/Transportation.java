package Transportation;

public class Transportation {
    private final int duration;
    private final int charge;

    public Transportation(int duration, int charge) {
        this.duration = duration;
        this.charge = charge;
    }

    public String getDuration() {
        return "소요 시간(분): " + duration;
    }
    public String getCharge() {
        return "교통 요금(원): " + charge;
    }
}
