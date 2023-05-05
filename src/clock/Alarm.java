
package clock;

/**
 *
 * @author Lee Devine
 */
public class Alarm {
    
    // Variables representing hour, minute and seconds of the alarm
    private int hour;
    private int minute;
    private int second;
    
    //Constructor that initialises the Alarm object
    public Alarm (int hour, int minute, int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * Getter method for the hour of the alarm
     * @return 
     */
    public int getHour() {
        return hour;
    }

    /**
     * Setter method for hour of the alarm
     * @param hour 
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Getter method for the minute of the alarm
     * @return 
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Setter method for the minute of the alarm
     * @param minute 
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Getter method for the second of the alarm
     * @return 
     */
    public int getSecond() {
        return second;
    }

    /**
     * Setter method for the second of the alarm
     * @param second 
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
     * @return 
     */
    public boolean isTriggered(int currentHour, int currentMinute, int currentSecond){
        return this.hour == currentHour && this.minute == currentMinute && this.second == currentSecond;
    }
    
}
