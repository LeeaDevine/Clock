
package clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * AlarmDialog class creates a dialog box for the user to interact with 
 * when setting an alarm
 * 
 * @author Lee Devine
 */
public class AlarmDialog extends JDialog {
    
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JSpinner secondSpinner;
    private JButton setButton;
    private JButton cancelButton;

    private Alarm alarm;

    /**
     * Constructor for AlarmDialog
     * @param owner The frame instance that owns this dialog
     * @param selectedAlarm The selectedAlarm from edit option, if available.
     */
    public AlarmDialog(Frame owner, Alarm selectedAlarm) {
        super(owner, "Set Alarm", true);
        initComponents(selectedAlarm);
        
        setLocationRelativeTo(owner);
    }
    
    /**
     * Initialize components of the Dialog Box
     * @param selectedAlarm The selectedAlarm from edit option
     */
    private void initComponents(Alarm selectedAlarm) {
        // Initialize components
        int selectedHour = selectedAlarm != null ? selectedAlarm.getHour() : 0;
        int selectedMinute = selectedAlarm != null ? selectedAlarm.getMinute() : 0;
        int selectedSecond = selectedAlarm != null ? selectedAlarm.getSecond() : 0;
        
        hourSpinner = new JSpinner(new SpinnerNumberModel(selectedHour, 0, 23, 1));
        minuteSpinner = new JSpinner(new SpinnerNumberModel(selectedMinute, 0, 59, 1));
        secondSpinner = new JSpinner(new SpinnerNumberModel(selectedSecond, 0, 59, 1));
        setButton = new JButton("Set");
        cancelButton = new JButton("Cancel");

        //Disable editing in the spinners' text fields
        ((JSpinner.DefaultEditor) hourSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) minuteSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) secondSpinner.getEditor()).getTextField().setEditable(false);
        
        //Get hold of values for when user edits selected alarm
        if(selectedAlarm != null){
            hourSpinner.setValue(selectedAlarm.getHour());
            minuteSpinner.setValue(selectedAlarm.getMinute());
            secondSpinner.setValue(selectedAlarm.getSecond());
        }
        
        // Layout components
        setLayout(new FlowLayout());

        add(hourSpinner);
        add(new JLabel(":"));
        add(minuteSpinner);
        add(new JLabel(":"));
        add(secondSpinner);

        add(setButton);
        add(cancelButton);

        // Set up event handlers
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hour = (Integer)(hourSpinner.getValue());
                int minute = (Integer)(minuteSpinner.getValue());
                int second = (Integer)(secondSpinner.getValue());

                Calendar alarmTime = Calendar.getInstance();
                alarmTime.set(Calendar.HOUR_OF_DAY, hour);
                alarmTime.set(Calendar.MINUTE, minute);
                alarmTime.set(Calendar.SECOND, second);
                
                Calendar currentTime = Calendar.getInstance();
                
                //If alarmTime is set before currentTime -> move to next day
                if(alarmTime.before(currentTime)){
                    alarmTime.add(Calendar.DATE, 1);
                    JOptionPane.showMessageDialog(AlarmDialog.this,
                            "The alarm time has already passed today. The Alarm has been set for tomorrow",
                            "Alarm Set",
                            JOptionPane.INFORMATION_MESSAGE
                            );
                }

                alarm = new Alarm(alarmTime);
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarm = null;
                setVisible(false);
            }
        });

        pack();
    }

    /**
     * Displays the dialog and returns the created alarm
     * @return The created alarm object, or null if cancelled.
     */
    public Alarm showDialog() {
        setVisible(true);
        return alarm;
    }
}
