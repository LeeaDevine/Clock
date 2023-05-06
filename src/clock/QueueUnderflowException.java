/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

