package clock;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * Controller class for the clock application
 * @author Lee Devine
 */

public class Controller {
    
    ActionListener listener;
    Timer timer;
    
    Model model;
    View view;
    
    /**
     * 
     * @param m
     * @param v 
     */
    public Controller(Model m, View v) {
        model = m;
        view = v;
        
         // Attach action listeners to menu items using anonymous inner classes
        view.getSaveAlarmsMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save alarms logic
            }
        });

        view.getLoadAlarmsMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Load alarms logic
            }
        });

        view.getAddAlarmMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add alarm logic
            }
        });

        view.getEditAlarmsMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Edit alarms logic
            }
        });

        view.getRemoveAlarmsMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove alarms logic
            }
        });
        
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
    }
}
