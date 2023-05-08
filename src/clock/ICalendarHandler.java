package clock;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 * ICalendarHandler handles iCalendar (ICS) file formats for saving and loading alarms.
 * This class provides utility methods to save a list of alarms to an ICS file and load alarms
 * from an ICS file.
 * 
 * @author Lee Devine
 */
public class ICalendarHandler {

    /**
     * Constructor for ICalendarHandler
     */
    public ICalendarHandler() {
    }

    /**
     * Check if the given file is an iCalendar (.ics) file
     * 
     * @param file The file to check
     * @return True, if the file is an .ics file, false otherwise
     */
    public static boolean isIcsFile(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            String extension = fileName.substring(dotIndex + 1).toLowerCase();
            return extension.equals("ics");
        }
        return false;
    }
    
    /**
     * Saves the given list of alarms to the specified ICS file. The alarms are formatted
     * using the iCalendar VALARM component format with an AUDIO action and a TRIGGER value.
     * If the file is not an ICS file, an error message is displayed.
     * 
     * @param alarms The list of alarms to save
     * @param file The file to save the alarms in
     */
    public void saveAlarmsToFile(List<Alarm> alarms, File file) {
        if(!isIcsFile(file)){
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid file type. Please choose a .ics file.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            //Write the VCALENDAR header
            bw.write("BEGIN:VCALENDAR");
            bw.newLine();

            //Iterate through the alarms and write each VALARM component
            for (Alarm alarm : alarms) {
                bw.write("BEGIN:VALARM");
                bw.newLine();

                bw.write("ACTION:AUDIO");
                bw.newLine();

                String dateString = dateFormat.format(alarm.getAlarmTime().getTime());
                bw.write("TRIGGER;VALUE=DATE-TIME:" + dateString);
                bw.newLine();

                bw.write("END:VALARM");
                bw.newLine();
            }

            //Write VCALENDAR footer
            bw.write("END:VCALENDAR");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads alarms from the specified ICS file. This method reads the VALARM components
     * and extracts the TRIGGER value to create a list of Alarm objects.
     * If the file is not an ICS file, an error message is displayed.
     * 
     * @param file The file to load alarms from
     * @return A list of alarms loaded form the file
     */
    public List<Alarm> loadAlarmsFromFile(File file) {
        List<Alarm> alarmList = new ArrayList<>();
        if(!isIcsFile(file)){
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid file type. Please choose a .ics file.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return alarmList;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            //Read lines from the ICS file
            while ((line = br.readLine()) != null) {
                //Check if the line starts a VALARM component
                if (line.startsWith("BEGIN:VALARM")) {
                    Calendar alarmTime = null;

                    //Read lines with the VALARM component
                    while (!(line = br.readLine()).startsWith("END:VALARM")) {
                        //Check if the line starts with TRIGGER value
                        if (line.startsWith("TRIGGER;VALUE=DATE-TIME:")) {
                            String dateString = line.substring("TRIGGER;VALUE=DATE-TIME:".length());
                            alarmTime = Calendar.getInstance();
                            alarmTime.setTime(dateFormat.parse(dateString));
                        }
                    }

                    //If the alarm time is not null, create alarm object and add it to the list
                    if (alarmTime != null) {
                        alarmList.add(new Alarm(alarmTime));
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        //Return the list of loaded alarms
        return alarmList;
    }
}
