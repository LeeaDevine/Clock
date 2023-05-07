package clock;

import java.util.Calendar;

public class Alarm {
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
    public String toString() {
        int hour = alarmTime.get(Calendar.HOUR_OF_DAY);
        int minute = alarmTime.get(Calendar.MINUTE);
        int second = alarmTime.get(Calendar.SECOND);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
