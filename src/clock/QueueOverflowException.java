
package clock;

/**
 * Queue is full.
 *
 * Cannot accept a new alarm.
 * @author Lee Devine
 */
public class QueueOverflowException extends Exception {

    public QueueOverflowException() {
        super("Queue is full");
    }
}