
package clock;

/**
 * Deals with an alarm based PriorityQueue
 * Alarm nearest to current time with be head of queue
 * 
 * @author Lee Devine
 */
public class AlarmPriorityItem {
    
    private final Alarm alarm;
    private final int priority;
    
    public AlarmPriorityItem(Alarm alarm, int priority){
        this.alarm = alarm;
        this.priority = priority;
    }
    
    public Alarm getAlarm(){
        return alarm;
    }
    
    public int getPriority(){
        return priority;
    }
    
    @Override
    public String toString(){
        return "(" + getAlarm() + ", " + getPriority() + ")";
    }
    
}
