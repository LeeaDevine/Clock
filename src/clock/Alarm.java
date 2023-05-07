package clock;

import java.util.Calendar;

/**
 * Alarm class deals with object of an alarm
 * 
 * @author Lee Devine
 */
public class Alarm implements Comparable<Alarm>{
    private Calendar alarmTime;

    public Alarm(Calendar alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Calendar getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Calendar alarmTime) {
        this.alarmTime = alarmTime;
    }
    
    @Override
    public int compareTo(Alarm other){
        return this.getAlarmTime().compareTo(other.getAlarmTime());
    }

    @Override
    public String toString() {
        int hour = alarmTime.get(Calendar.HOUR_OF_DAY);
        int minute = alarmTime.get(Calendar.MINUTE);
        int second = alarmTime.get(Calendar.SECOND);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
