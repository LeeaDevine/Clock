
package clock;

import java.io.IOException;
import java.util.List;

/**
 * Handles file management using iCalendar Format
 * 
 * @author Lee Devine
 */
public class ICalendarHandler {
    
    public ICalendarHandler(){
        //TODO constructor
    }
    
    public void saveAlarmsToFile(List<Alarm> alarms, String filePath) throws IOException {
        // Implement the logic to save the alarms list to a file in iCalendar format
    }

    public List<Alarm> loadAlarmsFromFile(String filePath) throws IOException {
        // Implement the logic to read the alarms from a file in iCalendar format
        // and return a list of Alarm objects
        return null;
    }
}
