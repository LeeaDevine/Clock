/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package clock;

/**
 *
 * @author Leead
 */
public interface AlarmPriorityQueue {
    
    // Update method signatures to work with Alarm objects
    public void add(Alarm alarm, int priority) throws QueueOverflowException;
    public Alarm head() throws QueueUnderflowException;
    public void remove() throws QueueUnderflowException;
    public boolean isEmpty();
    @Override
    public String toString();
}
