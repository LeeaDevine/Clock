
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
    
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JSpinner secondSpinner;
    private JButton setButton;
    private JButton cancelButton;

    private Alarm alarm;

    public AlarmDialog(Frame owner) {
        super(owner, "Set Alarm", true);
        initComponents();
        
        setLocationRelativeTo(owner);
    }
    
    /**
     * Initialize components of the Dialog Box
     */
    private void initComponents() {
        // Initialize components
        hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        secondSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        setButton = new JButton("Set");
        cancelButton = new JButton("Cancel");

        ((JSpinner.DefaultEditor) hourSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) minuteSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) secondSpinner.getEditor()).getTextField().setEditable(false);
        
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
    
    //Make sure user stay within constraints of JSpinner max and min values
    private static class ConstrainedSpinnerNumberModel extends SpinnerNumberModel{
        
        public ConstrainedSpinnerNumberModel(int value, int minimum, int maximum, int stepSize){
            super(value, minimum, maximum, stepSize);
        }
        
        @Override
        public void setValue(Object value){
            if(value instanceof Integer){
                int intValue = (Integer) value;
                int minValue = (Integer) getMinimum();
                int maxValue = (Integer) getMaximum();
                
                if(intValue < minValue){
                    intValue = minValue;
                } else if(intValue > maxValue){
                    intValue = maxValue;
                }
                super.setValue(intValue);
            }else{
                super.setValue(value);
            }
        }
    }
}
