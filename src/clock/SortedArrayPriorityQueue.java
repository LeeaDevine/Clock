
package clock;

/**
 *
 * @author Leead
 */
public class SortedArrayPriorityQueue implements AlarmPriorityQueue{
    
    private final Object[] storage;
    private final int capacity;
    private int tailIndex;
    
    public SortedArrayPriorityQueue(int size){
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }

    @Override
    public void add(Alarm alarm, int priority) throws QueueOverflowException {
        tailIndex = tailIndex +1;
        if(tailIndex >= capacity){
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else{
            int i = tailIndex;
            while (i > 0 && ((AlarmPriorityItem) storage[i - 1]).getPriority() < priority) {                
                storage[i] = storage[i - 1];
                i = i - 1;
            }
            storage[i] = new AlarmPriorityItem(alarm, priority);
        }
    }

    @Override
    public Alarm head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            return ((AlarmPriorityItem) storage[0]).getAlarm();
        }
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if(isEmpty()){
            throw new QueueUnderflowException();
        } else {
            for (int i = 0; i < tailIndex; i++) {
                storage[i] = storage[i + 1];
            }
            tailIndex--;
        }
    }

    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }
    
    @Override
    public String toString(){
        String result = "[";
        for(int i = 0; i <= tailIndex; i++){
            if(i > 0){
                result = result + ", ";
            }
            result = result + storage[i];
        }
        result = result + "]";
        return result;
    }
    
}
