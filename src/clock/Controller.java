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
        
        view.setController(this);
        
         // Attach action listeners to menu items using anonymous inner classes
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


        view.getAddAlarmMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add alarm logic
                showAddAlarmDialog();
            }
        });

        view.getEditAlarmsMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEditAlarmDialog();
            }
        });

        view.getRemoveAlarmsMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRemoveAlarmDialog();
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
        
        // Show the load alarms dialog at the start of the program
        showLoadAlarmsDialog();
        
        //Call the startAlarmCheckingThread() method
        startAlarmCheckingThread();
    }
    
    private void showAddAlarmDialog() {
        JFrame parentFrame = view.getFrame();
        AlarmDialog alarmDialog = new AlarmDialog(parentFrame);
        Alarm newAlarm = alarmDialog.showDialog();

        if (newAlarm != null) {
            model.addAlarm(newAlarm);
            view.updateNumberOfAlarmsLabel(model.getAlarms().size());
        }
    }
    
    private void showEditAlarmDialog() {
        JFrame parentFrame = view.getFrame();
        List<Alarm> alarms = model.getAlarms();
        if (!alarms.isEmpty()) {
            AlarmSelectionDialog alarmSelectionDialog = new AlarmSelectionDialog(parentFrame, alarms);
            Alarm selectedAlarm = alarmSelectionDialog.showDialog();

            if (selectedAlarm != null) {
                model.removeAlarm(selectedAlarm);
                AlarmDialog alarmDialog = new AlarmDialog(parentFrame);
                Alarm updatedAlarm = alarmDialog.showDialog();

                if (updatedAlarm != null) {
                    model.addAlarm(updatedAlarm);
                } else {
                    model.addAlarm(selectedAlarm);
                }

                view.updateNumberOfAlarmsLabel(model.getAlarms().size());
            }
        } else {
            JOptionPane.showMessageDialog(parentFrame, "No alarms available to edit.", "Edit Alarm", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showRemoveAlarmDialog() {
        JFrame parentFrame = view.getFrame();
        List<Alarm> alarms = model.getAlarms();
        if (!alarms.isEmpty()) {
            AlarmSelectionDialog alarmSelectionDialog = new AlarmSelectionDialog(parentFrame, alarms);
            Alarm selectedAlarm = alarmSelectionDialog.showDialog();

            if (selectedAlarm != null) {
                model.removeAlarm(selectedAlarm);
                view.updateNumberOfAlarmsLabel(model.getAlarms().size());
            }
        } else {
            JOptionPane.showMessageDialog(parentFrame, "No alarms available to remove.", "Remove Alarm", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
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
    
    public void saveAlarmsOnExit(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(view.getFrame());
        
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            ICalendarHandler icsHandler = new ICalendarHandler();
            icsHandler.saveAlarmsToFile(model.getAlarms(), file);
        }
    }

    
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
                        //Wait for 1 second before checking again
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
