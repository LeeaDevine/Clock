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
    JLabel nextAlarmLabel;
    
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
        
        /**
         *      //Create MenuBar
         */
        JMenuBar menuBar = new JMenuBar();
        
        //Create AlarmMenu
        JMenu alarmsMenu = new JMenu("Alarms");
        menuBar.add(alarmsMenu);
        
        //Creae menu items for adding, editing and deleting alarms
        JMenuItem addAlarm = new JMenuItem("Add Alarm");
        JMenuItem editAlarm = new JMenuItem("Edit Alarm");
        JMenuItem deleteAlarm = new JMenuItem("Delete Alarm");
        
        //TODO: Add action listeners for menu items
        addAlarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Display a dialog box to input the alarm time
                String alarmTime = JOptionPane.showInputDialog("Enter alarm time(HH:mm)");
                
                if(alarmTime == null){
                    return;
                }
                
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                Date parseDate;
                try{
                    parseDate = timeFormat.parse(alarmTime);
                } catch (ParseException ex){
                    JOptionPane.showMessageDialog(null, "Invalid time format. Please enter the time as HH:mm");
                    return;
                }
                
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(parseDate);
                int alarmHour = calendar.get(Calendar.HOUR_OF_DAY);
                int alarmMinute = calendar.get(Calendar.MINUTE);
                
                Alarm newAlarm = new Alarm(alarmHour, alarmMinute, 0);
                
                // Calculate the priority based on the alarm's proximity to the current time
                Calendar currentTime = Calendar.getInstance();
                long currentTimeMillis = currentTime.getTimeInMillis();
                long alarmTimeMillis = calendar.getTimeInMillis();
                
                int priority = (int) (alarmTimeMillis - currentTimeMillis);
                
                model.getAlarmManager().addAlarm(newAlarm);
                JOptionPane.showMessageDialog(null, "Alarm added successfully");
                updateNextAlarmLabel();
                //testing queue content
                System.out.println("Current Alarm queue: " + model.getAlarmManager().toString());
            }
        });
        
        editAlarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add logic for editing an alarm
                System.out.println("edit alarm");
            }
        });
        
        deleteAlarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add logic for deleting an alarm
                System.out.println("delete alarm");
            }
        });
        
        
        //Add menu items to the Alarms Menu
        alarmsMenu.add(addAlarm);
        alarmsMenu.add(editAlarm);
        alarmsMenu.add(deleteAlarm);

        //Add menu bar to frame
        frame.setJMenuBar(menuBar);
        
        
//-------- Start of border layout code -----------------------------------------
         
        
        Container pane = frame.getContentPane();
        
        JButton button = new JButton("Menu Bar");
        pane.add(button, BorderLayout.PAGE_START);
        
        //Add the ClockPanel to the JFrame's content pane
        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);
         
//----------- Alarm Panel -----------------------------------------
        //Create new JLabel for displaying the next alarm in queue
        nextAlarmLabel = new JLabel("Next Alarm -");
        
        // Initialize the nextAlarmLabel with the next alarm information
        updateNextAlarmLabel();
        
        // Call updateNextAlarmLabel() after adding, removing or modifying alarms in the queue
        // For example, when you create or initialize the AlarmManager, or when you add or remove alarms
        // ...
        JButton alarmButton = new JButton("Alarms");
        JPanel buttonAlarmPanel = new JPanel();
        buttonAlarmPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonAlarmPanel.add(alarmButton);
        buttonAlarmPanel.add(nextAlarmLabel);
        pane.add(buttonAlarmPanel, BorderLayout.PAGE_END);

        
//--------- End of borderlayout code -------------------------------------------
        
        //Pack the components and set the JFrame visible
        frame.pack();
        frame.setVisible(true);
    }
    
    public void updateNextAlarmLabel() {
        Alarm nextAlarm = model.getAlarmManager().getNextAlarm();
        if (nextAlarm != null) {
            nextAlarmLabel.setText("Next Alarm: " + nextAlarm.toString());
        } else {
            nextAlarmLabel.setText("Next Alarm -");
        }
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
