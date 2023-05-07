package clock;

import java.io.File;
import java.io.IOException;
import java.util.List;
import clock.Alarm;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class ICalendarHandler {

    public ICalendarHandler() {
    }

    public void saveAlarmsToFile(List<Alarm> alarms, File file) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("BEGIN:VCALENDAR");
            bw.newLine();

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

            bw.write("END:VCALENDAR");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Alarm> loadAlarmsFromFile(File file) {
        List<Alarm> alarmList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("BEGIN:VALARM")) {
                    Calendar alarmTime = null;

                    while (!(line = br.readLine()).startsWith("END:VALARM")) {
                        if (line.startsWith("TRIGGER;VALUE=DATE-TIME:")) {
                            String dateString = line.substring("TRIGGER;VALUE=DATE-TIME:".length());
                            alarmTime = Calendar.getInstance();
                            alarmTime.setTime(dateFormat.parse(dateString));
                        }
                    }

                    if (alarmTime != null) {
                        alarmList.add(new Alarm(alarmTime));
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return alarmList;
    }
}
