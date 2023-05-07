
package clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * Dialog box dealing with user alarm interaction
 * 
 * @author Lee Devine
 */
public class AlarmDialog extends JDialog {
    
    private JTextField hourField;
    private JTextField minuteField;
    private JTextField secondField;
    private JButton setButton;
    private JButton cancelButton;

    private Alarm alarm;

    public AlarmDialog(Frame owner) {
        super(owner, "Set Alarm", true);
        initComponents();
    }
    
    /**
     * Initialize components of the Dialog Box
     */
    private void initComponents() {
        // Initialize components
        hourField = new JTextField(2);
        minuteField = new JTextField(2);
        secondField = new JTextField(2);
        setButton = new JButton("Set");
        cancelButton = new JButton("Cancel");

        // Layout components
        setLayout(new FlowLayout());

        add(hourField);
        add(new JLabel(":"));
        add(minuteField);
        add(new JLabel(":"));
        add(secondField);

        add(setButton);
        add(cancelButton);

        // Set up event handlers
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hour = Integer.parseInt(hourField.getText());
                int minute = Integer.parseInt(minuteField.getText());
                int second = Integer.parseInt(secondField.getText());

                Calendar alarmTime = Calendar.getInstance();
                alarmTime.set(Calendar.HOUR_OF_DAY, hour);
                alarmTime.set(Calendar.MINUTE, minute);
                alarmTime.set(Calendar.SECOND, second);

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
     * Show Dialog
     * @return 
     */
    public Alarm showDialog() {
        setVisible(true);
        return alarm;
    }
    
}
