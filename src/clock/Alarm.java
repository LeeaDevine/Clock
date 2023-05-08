package clock;

import java.util.Calendar;

/**
 * The Alarm class represents an alarm object with a specific alarm time.
 * It provides methods to get and set the alarm time and its components
 * (hour, minute, second), and to compare alarms based on their times.
 *
 * @author Lee Devine
 */
public class Alarm implements Comparable<Alarm>{
    
    private Calendar alarmTime;

    /**
     * Constructor for Alarm class.
     * 
     * @param alarmTime - Calendar object representing the alarm time
     */
    public Alarm(Calendar alarmTime) {
        this.alarmTime = alarmTime;
    }

    /**
     * Getter for alarm time
     * 
     * @return - Calendar object representing the alarm time
     */
    public Calendar getAlarmTime() {
        return alarmTime;
    }

    /**
     * Setter for alarm time
     * 
     * @param alarmTime - Calendar object representing the alarm time
     */
    public void setAlarmTime(Calendar alarmTime) {
        this.alarmTime = alarmTime;
    }
    
    /**
     * Getter for alarm hour 
     * 
     * @return - int representing the hour of the alarm 
     */
    public int getHour() {
        return alarmTime.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * getter for alarm minute
     * 
     * @return - int representing the minute of the alarm
     */
    public int getMinute() {
        return alarmTime.get(Calendar.MINUTE);
    }
    
    /**
     * getter for alarm second
     * 
     * @return - int representing the second of the alarm
     */
    public int getSecond() {
        return alarmTime.get(Calendar.SECOND);
    }
    
    /**
     * Compare method for alarm based on their alarm times
     * 
     * @param other Alarm objects to compare to
     * @return int value indicating the comparison result
     */
    @Override
    public int compareTo(Alarm other){
        return this.getAlarmTime().compareTo(other.getAlarmTime());
    }

    /**
     * String representation of the alarm object
     * 
     * @return String representing the alarm time in the format HH:mm:ss
     */
    @Override
    public String toString() {
        int hour = alarmTime.get(Calendar.HOUR_OF_DAY);
        int minute = alarmTime.get(Calendar.MINUTE);
        int second = alarmTime.get(Calendar.SECOND);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
