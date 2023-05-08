package clock;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    Controller controller;
    
    private final JFrame frame;
    
    // Declare menu items as instance variables
    private JMenuItem saveAlarmsMenuItem;
    private JMenuItem loadAlarmsMenuItem;
    private JMenuItem addAlarmMenuItem;
    private JMenuItem editAlarmsMenuItem;
    private JMenuItem removeAlarmsMenuItem;
    
    //JLabel variables for nextAlarm and NumberOfAlarms
    private final JLabel nextAlarmLabel;
    private final JLabel numberOfAlarmsLabel;
    
    /**
     * Initializes the menu bar for the application
     * @return The initialized menu bar.
     */
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

        // Return menu bar
        return menuBar;
        }

    //Getter methods for menu items
    public JMenuItem getSaveAlarmsMenuItem() {return saveAlarmsMenuItem;}
    public JMenuItem getLoadAlarmsMenuItem() {return loadAlarmsMenuItem;}
    public JMenuItem getAddAlarmMenuItem() {return addAlarmMenuItem;}
    public JMenuItem getEditAlarmsMenuItem() {return editAlarmsMenuItem;}
    public JMenuItem getRemoveAlarmsMenuItem() {return removeAlarmsMenuItem;}
    
    /**
     * Updates the next alarm label with the provided Alarm object
     * @param nextAlarm The next Alarm object to display
     */
    public void updateNextAlarmLabel(Alarm nextAlarm) {
        if (nextAlarm != null) {
            nextAlarmLabel.setText("Next Alarm: " + nextAlarm.toString());
        } else {
            nextAlarmLabel.setText("Next Alarm: -");
        }
    }
    
    /**
     * Updates the number of alarms label with the provided number.
     * @param numberOfAlarms The number of alarms to display
     */
    public void updateNumberOfAlarmsLabel(int numberOfAlarms){
        numberOfAlarmsLabel.setText("Number of Alarms: " + numberOfAlarms);
    }

    /**
     * Constructor for the View class
     * Initializes the components and sets up the UI.
     * 
     * @param model The Model to be associated with this View.
     */
    public View(final Model model) {
        
        this.model = model;
        
        //Create a new JFrame to display the clock.
        frame = new JFrame();
        
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
        
        //Create the new JLabels with empty strings
        nextAlarmLabel = new JLabel("");
        numberOfAlarmsLabel = new JLabel("");
        
        //Add the southPanel JPanel to the JFrame
        pane.add(createSouthPanel(), BorderLayout.SOUTH);
        
        //Pack the components and set the JFrame visible
        frame.pack();
        frame.setVisible(true);
        
        //Add window listener
        attachWindowListener();
    }
    
    //Getter methods for labels
    public JLabel getNumberOfAlarmsLabel(){return numberOfAlarmsLabel;}
    public JLabel getNextAlarmLabel(){return nextAlarmLabel;}
    
    /**
     * Creates the southPanel containing next alarm and number of alarms labels.
     * @return The initialized south panel.
     */
    private JPanel createSouthPanel(){
        JPanel southPanel = new JPanel(new BorderLayout());
        
        //Set the border for the southPanel
        southPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        //Add the JLabels to the southPanel with BorderLayout constraints
        southPanel.add(numberOfAlarmsLabel, BorderLayout.EAST);
        southPanel.add(nextAlarmLabel, BorderLayout.WEST);
        
        return southPanel;
    }
    
    /**
     * Update the view with the new alarm information.
     * @param o The Observable object that triggered the update.
     * @param arg The argument passed to the update method.
     */
    @Override
    public void update(Observable o, Object arg) {
        //Repaint and show updated view
        panel.repaint();
        
        // Update the nextAlarmLabel
        updateNextAlarmLabel(model.getNextAlarm());
        
        //Update the numberOfAlarmsLabel
        updateNumberOfAlarmsLabel(model.getAlarms().size());
    }
    
    //Getter for the JFrame
    public JFrame getFrame(){return frame;}
    
    /**
     * Attaches a window listener to handle saving alarms on exit.
     */
    private void attachWindowListener(){
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                int result = JOptionPane.showConfirmDialog(
                        frame,
                        "Would you like to save the file before closing program?",
                        "Save Alarm List",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );
                
                if(result == JOptionPane.YES_OPTION){
                    //Call the save method from the controller
                    controller.saveAlarmsOnExit();
                }
            }
        });
    }

    /**
     * Sets the Controller associated with this View.
     * @param controller The Controller to be associated with this View.
     */
    void setController(Controller controller) {
        this.controller = controller;
    }
}
