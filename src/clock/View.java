package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    Model model;
    
    private JFrame frame;
    
    // Declare menu items as instance variables
    private JMenuItem saveAlarmsMenuItem;
    private JMenuItem loadAlarmsMenuItem;
    private JMenuItem addAlarmMenuItem;
    private JMenuItem editAlarmsMenuItem;
    private JMenuItem removeAlarmsMenuItem;
    
    private JMenuBar initMenuBar() {
        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create Menus
        JMenu fileMenu = new JMenu("File");
        JMenu alarmsMenu = new JMenu("Alarm");

        // Create submenus
        JMenu fileOperationsMenu = new JMenu("File Operations");

        // Create Menu items (remove type declaration to avoid shadowing)
        saveAlarmsMenuItem = new JMenuItem("Save");
        loadAlarmsMenuItem = new JMenuItem("Load");
        addAlarmMenuItem = new JMenuItem("Add");
        editAlarmsMenuItem = new JMenuItem("Edit");
        removeAlarmsMenuItem = new JMenuItem("Remove");

        // Add menu items to submenus
        fileOperationsMenu.add(saveAlarmsMenuItem);
        fileOperationsMenu.add(loadAlarmsMenuItem);

        // Add submenus and menu items to menus
        fileMenu.add(fileOperationsMenu);
        alarmsMenu.add(addAlarmMenuItem);
        alarmsMenu.add(editAlarmsMenuItem);
        alarmsMenu.add(removeAlarmsMenuItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(alarmsMenu);

        return menuBar;
        }

    
    public JMenuItem getSaveAlarmsMenuItem() {
        return saveAlarmsMenuItem;
    }

    public JMenuItem getLoadAlarmsMenuItem() {
        return loadAlarmsMenuItem;
    }

    public JMenuItem getAddAlarmMenuItem() {
        return addAlarmMenuItem;
    }

    public JMenuItem getEditAlarmsMenuItem() {
        return editAlarmsMenuItem;
    }

    public JMenuItem getRemoveAlarmsMenuItem() {
        return removeAlarmsMenuItem;
    }

    
    /**
     *
     * @param model
     */
    public View(final Model model) {
        this.model = model;
        
        //Create a new JFrame to display the clock.
        JFrame frame = new JFrame();
        
        //Create a ClockPanel and pass the Model to it.
        panel = new ClockPanel(model);
        
        //Configure the JFrame properties
        frame.setTitle("Alarm Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add the menu bar to the frame
        frame.setJMenuBar(initMenuBar());
         
        Container pane = frame.getContentPane();
        
        //Add the ClockPanel to the JFrame's content pane
        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);
        
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
    
    public JFrame getFrame(){
        return frame;
    }
   
}
