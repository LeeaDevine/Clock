
package clock;

import java.util.List;

/**
 * This class will manage the alarms using a PriorityQueue
 * @author Lee Devine
*/
public class AlarmManager {
    
    private static final int MAX_PRIORITY = 10;
    
    // Declare and initialize the AlarmPriorityQueue instance variable
    private final AlarmPriorityQueue queue;
    
    public AlarmManager(int capacity){
        queue = new SortedArrayPriorityQueue(capacity);
    }
    
    public void addAlarm(Alarm alarm) {
    // Find the correct position to insert the alarm in the sorted list
        int position = 0;
        for (AlarmPriorityItem item : queue.getItems()) {
            if (item.getAlarm().compareTo(alarm) > 0) {
                break;
            }
            position++;
        }

        // Assign priority based on the position
        int priority = CalculatePriority(alarm);

        // Insert the alarm with the assigned priority
        try{
            queue.add(alarm, priority);
        } catch (QueueOverflowException e){
            System.out.println("Alarm queue is full. Could not add the alarm");
        }
    }

    
    public void removeAlarm(){
        try{
            queue.remove();
        } catch (QueueUnderflowException e){
            System.out.println("Alarm queue is empty. Unable to remove an alarm");
        }
    }
    
    public Alarm getNextAlarm(){
        try{
            return queue.head();
        } catch (QueueUnderflowException e){
            System.out.println("Alarm queue is empty. No next alarm.");
            return null;
        }
    }
    
    public boolean isAlarmTriggered(int currentHour, int currentMinute, int CurrentSecond){
        Alarm nextAlarm = getNextAlarm();
        if(nextAlarm != null && nextAlarm.isTriggered(currentHour, currentMinute, CurrentSecond)){
            removeAlarm();
            return true;
        }
        return false;
    }
    
    public int CalculatePriority(Alarm alarm){
        int priority = 0;
        AlarmPriorityItem[] items = queue.getItems();
        for(int i = 0; i <= items.length - 1; i++){
            Alarm currentAlarm = items[i].getAlarm();
            if (alarm.compareTo(currentAlarm) >= 0) {
                priority++;
            } else {
                break;
            }
        }
        return priority;
    }
    
    public AlarmPriorityQueue getAlarmPriorityQueue() {
        return queue;
    }

    @Override
    public String toString() {
        return queue.toString();
    }

    List<Alarm> getAllAlarms() {
        //TODO::
        return null;
    }
}
