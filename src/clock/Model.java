package clock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.PriorityQueue;
//import java.util.GregorianCalendar;

/**
 * Model class for the clock application
 * Represents the clock's state, including the current time and alarm settings.
 *
 * @author Lee Devine
 */

public class Model extends Observable {
    
    int hour = 0;
    int minute = 0;
    int second = 0;
    int oldSecond = 0;
    
    int nextAlarmHour;
    int nextAlarmMinute;
    
    private final PriorityQueue<Alarm> alarms;
    
    
    /**
     * Constructor for the Model Class
     * Initializes the Model by updating the current time and creating a priority queue for alarms.
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
     
    /**
     * Adds an alarm to the priority queue of alarms.
     * Notifies observers after the alarm has been added.
     * 
     * @param alarm Alarm to be added
     */
    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
        setChanged();
        notifyObservers();
    }

    /**
     * Removes an alarm form the priority queue of alarms.
     * Notifies observers after the alarm has been removed.
     * 
     * @param alarm Alarm to be removed
     */
    public void removeAlarm(Alarm alarm) {
        alarms.remove(alarm);
        setChanged();
        notifyObservers();
    }

    /**
     * Retrieves the next alarm from the priority queue
     * 
     * @return The next Alarm object in the queue, or null if there are no alarms.
     */
    public Alarm getNextAlarm() {
        return alarms.peek();
    }
    
    /**
     * Retrieves a list of alarms in the priority queue.
     * 
     * @return A list of Alarm objects, sorted by their alarm time. 
     */
    public List<Alarm> getAlarms() {
        List<Alarm> alarmList = new ArrayList<>(alarms);
        Collections.sort(alarmList);
        return alarmList;
    }

    /**
     * Clears all alarms from the priority queue.
     * Notifies observers after the alarms have been cleared.
     */
    public void clearAlarms() {
        alarms.clear();
        setChanged();
        notifyObservers();
    }
    
    /**
     * Checks if a new alarm is a duplicate of an existing alarm
     *
     * @param newAlarm The Alarm object to be checked for duplicates
     * @return true if the new alarm is a duplicate, false otherwise
     */
    public boolean isAlarmDuplicate(Alarm newAlarm){
        for(Alarm alarm : alarms){
            if(alarm.toString().equals(newAlarm.toString())){
                return true;
            }
        }
        return false;
    }

}
    