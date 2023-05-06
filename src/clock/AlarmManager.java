
package clock;

/**
 * This class will manage the alarms using a PriorityQueue
 * @author Lee Devine
*/
public class AlarmManager {
    
    private final AlarmPriorityQueue queue;
    
    public AlarmManager(int size){
        queue = new SortedArrayPriorityQueue(size);
    }
    
    public void addAlarm(Alarm alarm, int priority){
        try{
            queue.add(alarm, priority);
        } catch (QueueOverflowException e){
            System.out.println("Alarm queue is full. Unable to add new alarm");
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
    
    public AlarmPriorityQueue getAlarmPriorityQueue() {
        return queue;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
