
package clock;

/**
 * Queue is empty.
 *
 * Cannot access or remove an item from an empty queue.
 * @author Lee Devine
 */
public class QueueUnderflowException extends Exception {

    public QueueUnderflowException() {
        super("Queue is empty");
    }
}

