package clock;

import java.util.Calendar;
import java.util.Observable;
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
    
    private AlarmManager alarmManager;
    
    /**
     * Constructor for the Model Class
     * Initializes the Model by updating the current time
     */
    public Model() {
        // Initialize AlarmManager with a capacity of 5 alarms
        alarmManager = new AlarmManager(5);
        
        update();
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
            
            // Check if an alarm is triggered and handle it
            if (alarmManager.isAlarmTriggered(hour, minute, second)) {
                System.out.println("Alarm triggered!");
                // TODO:: logic to handle triggered alarms here, e.g., play a sound
            }
        }
    }
    
    public AlarmManager getAlarmManager(){
        return alarmManager;
    }
    
}