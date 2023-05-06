/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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