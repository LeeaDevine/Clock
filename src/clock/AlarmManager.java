
package clock;

/**
 * This class will manage the alarms using a PriorityQueue
 * @author Lee Devine
*/
public class AlarmManager {
    
    private AlarmPriorityQueue queue;
    
    public AlarmManager(int size){
        queue = new SortedArrayPriorityQueue(size);
    }
}
