
package clock;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * AlarmSelectionDialog class creates a dialog box for the user to select
 * an alarm from a list.
 * 
 * @author Lee Devine
 */
public class AlarmSelectionDialog {
    
    private JDialog dialog;
    private JList<Alarm> alarmList;
    private Alarm selectedAlarms;
    
    /**
     * Constructor for AlarmSelectedDialog.
     * 
     * @param parentFrame The parent JFrame for the dialog
     * @param alarms A list of alarms to display in the dialog
     */
    public AlarmSelectionDialog(JFrame parentFrame, List<Alarm> alarms){
        dialog = new JDialog(parentFrame, "Select Alarm", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        //Iniialize alarmList with provided alarms
        alarmList = new JList<>(alarms.toArray(new Alarm[0]));
        JScrollPane scrollPane = new JScrollPane(alarmList);
        dialog.add(scrollPane, BorderLayout.CENTER);
        
        //Create a panel to hold the OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        //Set up event listeners for the OK and Cancel buttons
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //Retrieve the selected alarm form the list
                selectedAlarms = alarmList.getSelectedValue();
                //Dispose the dialog
                dialog.dispose();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //No alarms selected, set selectedAlarms null
                selectedAlarms = null;
                //dispose dialog
                dialog.dispose();
            }
        });
        
        //Set the size and location of the dialog
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(parentFrame);
    }
    
    /**
     * Displays the dialog and returns selected alarm
     * @return the selected Alarm object, or null if cancelled
     */
    public Alarm showDialog(){
        dialog.setVisible(true);
        return selectedAlarms;
    }
}
