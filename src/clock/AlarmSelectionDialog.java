
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
 *
 * @author Lee Devine
 */
public class AlarmSelectionDialog {
    
    private JDialog dialog;
    private JList<Alarm> alarmList;
    private Alarm selectedAlarms;
    
    public AlarmSelectionDialog(JFrame parentFrame, List<Alarm> alarms){
        dialog = new JDialog(parentFrame, "Select Alarm", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        alarmList = new JList<>(alarms.toArray(new Alarm[0]));
        JScrollPane scrollPane = new JScrollPane(alarmList);
        dialog.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                selectedAlarms = alarmList.getSelectedValue();
                dialog.dispose();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                selectedAlarms = null;
                dialog.dispose();
            }
        });
        
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(parentFrame);
    }
    
    public Alarm showDialog(){
        dialog.setVisible(true);
        return selectedAlarms;
    }
}
