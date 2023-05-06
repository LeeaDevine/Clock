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
     * Constructor for the Controller class
     * Sets up the timer and listener for updating the clock model
     * 
     * @param m The Model instance of the clock application
     * @param v The View instance of the clock application
     */
    public Controller(Model m, View v) {
        model = m;
        view = v;
        
        //Create an ActionListener to update the model everytime the timer fires
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
            }
        };
        
        /**
         * Create a timer that fires every 100 millisecond and
         * attach the listener to it.
        */
        timer = new Timer(100, listener);
        timer.start();
    }
}