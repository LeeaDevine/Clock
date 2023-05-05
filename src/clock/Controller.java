package clock;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * 
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