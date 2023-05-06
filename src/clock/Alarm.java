
package clock;

/**
 * The Alarm class represents an alarm object,
 * storing the time at which the alarm should be triggered.
 * 
 * @author Lee Devine
 */
public class Alarm {
    
    // Variables representing hour, minute and seconds of the alarm
    private int hour;
    private int minute;
    private int second;
    
    /**
     * Constructor that initializes the Alarm object with the given
     * hour, minute and second
     * @param hour The hour of the alarm
     * @param minute The minute of the alarm
     * @param second  The second of the alarm
     */
    public Alarm (int hour, int minute, int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * Getter method for the hour of the alarm
     * @return the hour of the alarm
     */
    public int getHour() {
        return hour;
    }

    /**
     * Setter method for hour of the alarm
     * @param hour the hour to set for the alarm
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Getter method for the minute of the alarm
     * @return the minute of the alarm
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Setter method for the minute of the alarm
     * @param minute the minute to set for the alarm
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Getter method for the second of the alarm
     * @return the second of the alarm
     */
    public int getSecond() {
        return second;
    }

    /**
     * Setter method for the second of the alarm
     * @param second the second to set for the alarm
     */
    public void setSecond(int second) {
        this.second = second;
    }
    
    /**
     * Method to check whether the alarm should be triggered, 
     * based on current time
     * @param currentHour 
     * @param currentMinute
     * @param currentSecond
     * @return true if the alarm should be triggered, false otherwise
     */
    public boolean isTriggered(int currentHour, int currentMinute, int currentSecond){
        return this.hour == currentHour && this.minute == currentMinute && this.second == currentSecond;
    }
    
}
