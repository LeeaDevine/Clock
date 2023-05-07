package clock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.PriorityQueue;
//import java.util.GregorianCalendar;

/**
 * Model class for the clock application
 * @author Lee Devine
 */

public class Model extends Observable {
    
    int hour = 0;
    int minute = 0;
    int second = 0;
    int oldSecond = 0;
    
    private PriorityQueue<Alarm> alarms;
    
    
    /**
     * Constructor for the Model Class
     * Initializes the Model by updating the current time
     */
    public Model() {
        update();
        
        alarms = new PriorityQueue<Alarm>(new Comparator<Alarm>() {
            @Override
            public int compare(Alarm a1, Alarm a2) {
                return a1.getAlarmTime().compareTo(a2.getAlarmTime());
            }
        });
    }
    
    /**
     * Updates the current time using the Calendar API
     * Notifies observers if the second value has changed
     */
    public void update() {
        //Get the current time using Calendar API
        Calendar date = Calendar.getInstance();
        hour = date.get(Calendar.HOUR);
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        
        //Check if the second value has changed
        if (oldSecond != second) {
            //If the second value has changed, notify the observers
            setChanged();
            notifyObservers();
        }
    }
    
    public void addAlarm(Alarm alarm) {
    alarms.add(alarm);
    setChanged();
    notifyObservers();
}

    public void removeAlarm(Alarm alarm) {
        alarms.remove(alarm);
        setChanged();
        notifyObservers();
    }

    public Alarm getNextAlarm() {
        return alarms.peek();
    }
    
    public List<Alarm> getAlarms() {
        return new ArrayList<>(alarms);
    }

    public void clearAlarms() {
        alarms.clear();
        setChanged();
        notifyObservers();
    }

}