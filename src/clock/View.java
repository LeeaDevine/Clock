package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

/**
 * View model part of MVC.
 * View represents the visual representation of the data from the 'Model'.
 * 'Controller' manages user interactions and updates between the Model and View
 * This is responsible for creating and managing the graphical user interface (GUI) for the application
 * 
 * @author Lee Devine
 */

public class View implements Observer {
    
    ClockPanel panel;
    
    /**
     *
     * @param model
     */
    public View(Model model) {
        //Create a new JFrame to display the clock.
        JFrame frame = new JFrame();
        
        //Create a ClockPanel and pass the Model to it.
        panel = new ClockPanel(model);
        
        //Configure the JFrame properties
        frame.setTitle("Alarm Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create MenuBar
        JMenuBar menuBar = new JMenuBar();
        
        //Create AlarmMenu
        JMenu alarmsMenu = new JMenu("Alarms");
        menuBar.add(alarmsMenu);
        
        //Creae menu items for adding, editing and deleting alarms
        JMenuItem addAlarm = new JMenuItem("Add Alarm");
        JMenuItem editAlarm = new JMenuItem("Edit Alarm");
        JMenuItem deleteAlarm = new JMenuItem("Delete Alarm");
        
        //Add menu items to the Alarms Menu
        alarmsMenu.add(addAlarm);
        alarmsMenu.add(editAlarm);
        alarmsMenu.add(deleteAlarm);
        
        //TODO: Add action listeners

        //Add menu bar to frame
        frame.setJMenuBar(menuBar);
        
        // Start of border layout code
        
        Container pane = frame.getContentPane();
        
        JButton button = new JButton("Menu Bar");
        pane.add(button, BorderLayout.PAGE_START);
        
        //Add the ClockPanel to the JFrame's content pane
        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);
         
        /**
         * Alarm Panel Contains:
         * 
         * Button:
         */
        JButton alarmButton = new JButton("Alarms");
        JPanel buttonAlarmPanel = new JPanel();
        buttonAlarmPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonAlarmPanel.add(alarmButton);
        pane.add(buttonAlarmPanel, BorderLayout.PAGE_END);

        
        // End of borderlayout code
        
        //Pack the components and set the JFrame visible
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Update the view with the new alarm information
     * 
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        //Repaint and show updated view
        panel.repaint();
    }
}
