
package clock;

/**
 * The AlarmPriorityQueue interface represents a priority queue structure for
 * managing Alarm objects. Implementing classes should provide the functionality
 * for adding, removing, and accessing alarms based on their priorities.
 * 
 * @author Lee Devine
 */
public interface AlarmPriorityQueue {
    
    /**
     * Adds an alarm object to the priority queue with the given priority
     * 
     * @param alarm The alarm object to be added
     * @param priority The priority of the alarm
     * @throws QueueOverflowException if the queue is full, cannot add new alarm
     */
    public void add(Alarm alarm, int priority) throws QueueOverflowException;
    
    
    /**
     * Retrieves the Alarm object with the highest priority (closest to current time)
     * Does not remove the alarm from queue
     * 
     * @return The Alarm object with highest priority
     * @throws QueueUnderflowException if the queue is empty, no alarm to return
     */
    public Alarm head() throws QueueUnderflowException;
    
    
    /**
     * Removes the Alarm object with the highest priority from the queue
     * 
     * @throws QueueUnderflowException 
     */
    public void remove() throws QueueUnderflowException;
    
    
    /**
     * Checks if queue is empty
     * 
     * @return true if queue is empty, false otherwise 
     */
    public boolean isEmpty();
    
    /**
     * Returns a string representation of the queue
     * 
     * @return A string
     */
    @Override
    public String toString();
}
