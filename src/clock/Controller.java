package clock;

import java.awt.event.*;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Controller class for the clock application.
 * Handles user interactions and communication between the Model and View.
 * 
 * @author Lee Devine
 */

public class Controller {
    
    ActionListener listener;
    Timer timer;
    
    Model model;
    View view;
    
    /**
     * Constructor for the Controller class.
     * Initializes the Controller with a Model and a View.
     * 
     * @param m Model object to be managed by the Controller
     * @param v View object to be managed by the Controller
     */
    public Controller(Model m, View v) {
        model = m;
        view = v;
        
        view.setController(this);
        
        // Attach action listeners to menu items using anonymous inner classes
        // Save alarms menu interaction 
        view.getSaveAlarmsMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(view.getFrame());

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    ICalendarHandler icsHandler = new ICalendarHandler();
                    icsHandler.saveAlarmsToFile(model.getAlarms(), file);
                }
            }
           
        });

        //Load alarms menu interaction
        view.getLoadAlarmsMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(view.getFrame());

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    ICalendarHandler icsHandler = new ICalendarHandler();
                    List<Alarm> loadedAlarms = icsHandler.loadAlarmsFromFile(file);
                    model.clearAlarms();
                    for (Alarm alarm : loadedAlarms) {
                        model.addAlarm(alarm);
                    }
                }
            }
        });

        //Add alarms menu interaction
        view.getAddAlarmMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add alarm logic
                showAddAlarmDialog();
            }
        });

        //Edit alarms menu interaction
        view.getEditAlarmsMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEditAlarmDialog();
            }
        });

        //Remove alarms menu interaction
        view.getRemoveAlarmsMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRemoveAlarmDialog();
            }
        });
        
        // ActionListener for the Timer that updates the Model.
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
            }
        };
        
        //Initalize and start the Timer.
        timer = new Timer(100, listener);
        timer.start();
        
        // Show the load alarms dialog at the start of the program
        showLoadAlarmsDialog();
        
        //Call the startAlarmCheckingThread() method
        startAlarmCheckingThread();
    }
    
    /**
     * Displays the Add Alarm dialog and handles addition of a new alarm.
     * Creates a new Alarm object based on user input.
     * If the new Alarm object is not a duplicate, adds it to the Model and updates the View.
     * Shows an information message for duplicate alarms.
     */
    private void showAddAlarmDialog() {
        JFrame parentFrame = view.getFrame();
        AlarmDialog alarmDialog = new AlarmDialog(parentFrame, null);
        Alarm newAlarm = alarmDialog.showDialog();

        if (newAlarm != null) {
            if(model.isAlarmDuplicate(newAlarm)){
                JOptionPane.showMessageDialog(parentFrame,
                        "This alarm has already been set.",
                        "Duplicate Alarm",
                        JOptionPane.INFORMATION_MESSAGE
                        );
            }else{
                model.addAlarm(newAlarm);
                view.updateNumberOfAlarmsLabel(model.getAlarms().size());
                JOptionPane.showMessageDialog(parentFrame,
                                "Alarm has been Added",
                                "Add Alarm",
                                JOptionPane.INFORMATION_MESSAGE
                        );
            }
        }
    }
    
    /**
     * Displays the Edit Alarm dialog and handles the editing of an existing alarm.
     * Presents a list of alarms to the user for selection.
     * If an alarm is selected, opens the Edit Alarm dialog with the selected alarm's details.
     * If the user confirms the edit, updates the alarm in the Model and refreshes the View.
     * If there are no alarms to edit, shows an information message.
     */
    private void showEditAlarmDialog() {
        JFrame parentFrame = view.getFrame();
        List<Alarm> alarms = model.getAlarms();
        if (!alarms.isEmpty()) {
            AlarmSelectionDialog alarmSelectionDialog = new AlarmSelectionDialog(parentFrame, alarms);
            Alarm selectedAlarm = alarmSelectionDialog.showDialog();

            if (selectedAlarm != null) {
//                model.removeAlarm(selectedAlarm);
                AlarmDialog alarmDialog = new AlarmDialog(parentFrame, selectedAlarm);
                Alarm updatedAlarm = alarmDialog.showDialog();

                if (updatedAlarm != null) {
                    model.removeAlarm(selectedAlarm);
                    model.addAlarm(updatedAlarm); 
                    JOptionPane.showMessageDialog(parentFrame,
                            "Alarm has been updated",
                            "Edit Alarm",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

                view.updateNumberOfAlarmsLabel(model.getAlarms().size());
            }
        } else {
            JOptionPane.showMessageDialog(parentFrame,
                    "No alarms available to edit.",
                    "Edit Alarm",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Displays the Remove Alarm dialog and handles the removal of an alarm.
     * Presents a list of alarms to the user for selection.
     * If an alarm is selected, removes it from the Model and updates the View.
     * If there are no alarms to remove, shows an information message.
     */
    private void showRemoveAlarmDialog() {
        JFrame parentFrame = view.getFrame();
        List<Alarm> alarms = model.getAlarms();
        if (!alarms.isEmpty()) {
            AlarmSelectionDialog alarmSelectionDialog = new AlarmSelectionDialog(parentFrame, alarms);
            Alarm selectedAlarm = alarmSelectionDialog.showDialog();

            if (selectedAlarm != null) {
                model.removeAlarm(selectedAlarm);
                view.updateNumberOfAlarmsLabel(model.getAlarms().size());
                JOptionPane.showMessageDialog(parentFrame,
                            "Alarm has been removed",
                            "Remove Alarm",
                            JOptionPane.INFORMATION_MESSAGE
                    );
            }
        } else {
            JOptionPane.showMessageDialog(parentFrame,
                    "No alarms available to remove.",
                    "Remove Alarm",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Displays the Load Alarms dialog and handles the loading of alarms from a file.
     * Opens a file chooser for the user to select a file containing alarm data.
     * If a file is selected, reads the alarms from the file and adds them to the Model.
     * Updates the View with the loaded alarms.
     */
    private void showLoadAlarmsDialog() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(view.getFrame());

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ICalendarHandler icsHandler = new ICalendarHandler();
            List<Alarm> loadedAlarms = icsHandler.loadAlarmsFromFile(file);
            model.clearAlarms();
            for (Alarm alarm : loadedAlarms) {
                model.addAlarm(alarm);
            }
            view.updateNumberOfAlarmsLabel(model.getAlarms().size());
        }
    }
    
    /**
     * Prompts the user to save alarms to a file when exiting the application.
     * Opens a file chooser for the user to select a destination file for saving alarm data.
     * If a file is selected, writes the alarms from the Model to the file.
     */
    public void saveAlarmsOnExit(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(view.getFrame());
        
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            ICalendarHandler icsHandler = new ICalendarHandler();
            icsHandler.saveAlarmsToFile(model.getAlarms(), file);
        }
    }
    
    /**
     * Starts a new thread that continuously checks for triggered alarms.
     * Compares the current time with the alarm time of the next alarm in the Model.
     * If the current time is equal to or greater than the alarm time, shows an information message for the triggered alarm.
     * Removes the triggered alarm from the Model and updates the View.
     * Sleeps for 0.1 seconds before checking again.
     */
    private void startAlarmCheckingThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {                    
                    Alarm nextAlarm = model.getNextAlarm();
                    if(nextAlarm != null){
                        Calendar currentTime = Calendar.getInstance();
                        Calendar alarmTime = nextAlarm.getAlarmTime();
                        
                        if(currentTime.compareTo(alarmTime) >= 0){
                            //Show dialog box
                            JOptionPane.showMessageDialog(
                                    view.getFrame(),
                                    "Alarm Triggered: " + nextAlarm.toString(),
                                    "Alarm",
                                    JOptionPane.INFORMATION_MESSAGE);
                            
                            //Remove the triggered alarm and update the next alarm label
                            model.removeAlarm(nextAlarm);
                            view.updateNextAlarmLabel(nextAlarm);
                            view.updateNumberOfAlarmsLabel(model.getAlarms().size());
                        }
                    }
                    
                    try{
                        //Wait for 0.1 seconds before checking again
                        Thread.sleep(100);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
