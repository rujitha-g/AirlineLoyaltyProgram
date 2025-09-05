package airline.loyalty;

import java.util.Date;

public class FlightActivity {
    public String activityId;
    public String flightNo;
    public Date date;
    private int distance;
    private String fareClass;
    private int milesEarned;

    public FlightActivity(String activityId, String flightNo, Date date, int distance, String fareClass) {
        this.activityId = activityId;
        this.flightNo = flightNo;
        this.date = date;
        this.distance = distance;
        this.fareClass = fareClass;
    }

    public int getDistance() { return distance; }
    public String getFareClass() { return fareClass; }
    public void setMilesEarned(int miles) { this.milesEarned = miles; }
    public int getMilesEarned() { return milesEarned; }
}

