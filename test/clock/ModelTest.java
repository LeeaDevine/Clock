
package clock;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit4 Testing -> Model
 * 
 * @author Lee Devine
 */
public class ModelTest {
    
    private Model model;
    private Calendar time1, time2;
    private Alarm alarm1, alarm2;
    
    public ModelTest() {
    }
    
    @Before
    public void setUp() {
        model = new Model();
    }

//------------------------------------------------------------------------------
    /**
     * Test of addAlarm method, of class Model.
     * Case 1.
     * Case 2.
     * 
     */
    @Test
    public void testAddAlarm_case1() {
        //Test case1: when queue is empty
        assertTrue(model.getAlarms().isEmpty());
        
        //Setup alarm instance
        time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 10);
        time1.set(Calendar.MINUTE, 30);
        time1.set(Calendar.SECOND, 30);
        alarm1 = new Alarm(time1);
        
        //Call in model
        model.addAlarm(alarm1);
        
        //Check results
        assertTrue(model.getAlarms().contains(alarm1));
        assertEquals(1, model.getAlarms().size());
    }
    
    @Test
    public void testAddAlarm_case2() {
        //Test case2: adding more alarms to queue
        
        //Setup alarm1 instance
        time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 10);
        time1.set(Calendar.MINUTE, 30);
        time1.set(Calendar.SECOND, 30);
        alarm1 = new Alarm(time1);
        model.addAlarm(alarm1);
        
        //Setup alarm2 instance
        time2 = new GregorianCalendar();
        time2.set(Calendar.HOUR_OF_DAY, 23);
        time2.set(Calendar.MINUTE, 59);
        time2.set(Calendar.SECOND, 59);
        alarm2 = new Alarm(time2);
        model.addAlarm(alarm2);
        
        //Check whether both alarms have been added.
        assertTrue(model.getAlarms().contains(alarm1));
        assertTrue(model.getAlarms().contains(alarm2));
        assertEquals(2, model.getAlarms().size());
    }

//------------------------------------------------------------------------------
    /**
     * Test of removeAlarm method, of class Model.
     * Case 1.
     * Case 2.
     */
    @Test
    public void testRemoveAlarm_case1() {
        //Test case 1: When the queue is empty
        assertTrue(model.getAlarms().isEmpty());
        
        //Create alarm1 instance
        time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 10);
        time1.set(Calendar.MINUTE, 33);
        time1.set(Calendar.SECOND, 33);
        alarm1 = new Alarm(time1);
        model.addAlarm(alarm1);
        
        //Check that alarm has been added.
        assertTrue(model.getAlarms().contains(alarm1));
        assertEquals(1, model.getAlarms().size());
        
        //Call model -> remove alarm1
        model.removeAlarm(alarm1);
        
        //Check results -> ensure queue is empty after removal
        assertTrue(model.getAlarms().isEmpty());
    }
    
    @Test
    public void testRemoveAlarm_case2() {
        //Test case 2: when the queue contains multiple alarms.
        
        //setup alarm1 instance
        time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 5);
        time1.set(Calendar.MINUTE, 33);
        time1.set(Calendar.SECOND, 33);
        alarm1 = new Alarm(time1);
        model.addAlarm(alarm1);
        
        //Check whether alarm1 has been added:
        assertTrue(model.getAlarms().contains(alarm1));
        
        //setup alarm2 instance
        time2 = new GregorianCalendar();
        time2.set(Calendar.HOUR_OF_DAY, 18);
        time2.set(Calendar.MINUTE, 59);
        time2.set(Calendar.SECOND, 59);
        alarm2 = new Alarm(time2);
        model.addAlarm(alarm2);
        
        //Check whether alarm2 has been added
        assertTrue(model.getAlarms().contains(alarm2));
        
        //Check that 2 alarms have been added
        assertEquals(2, model.getAlarms().size());
        
        //remove one of the alarms
        model.removeAlarm(alarm1);
        
        //ensure that the removed alarm is no longer in the queue
        assertFalse(model.getAlarms().contains(alarm1));
        //ensure that the other alarm is still in the queue
        assertTrue(model.getAlarms().contains(alarm2));
        //ensure that queue size is now 1
        assertEquals(1, model.getAlarms().size());
    }

//------------------------------------------------------------------------------
    /**
     * Test of getNextAlarm method, of class Model.
     */
    @Test
    public void testGetNextAlarm_case1() {
        //Test case 1: When is there are no alarms in queue
        
        //ensure the queue is empty
        assertTrue(model.getAlarms().isEmpty());
        
        //when there is no alarms, getNextAlarm() should be null
        assertNull(model.getNextAlarm());
    }
    
    @Test
    public void testGetNextAlarm_case2() {
        //Test case 2: When there is at least one alarm in queue
        
        //Create alarm1 instance
        time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 10);
        time1.set(Calendar.MINUTE, 30);
        time1.set(Calendar.SECOND, 0);
        alarm1 = new Alarm(time1);
        
        //Create alarm2 instance
        time2 = new GregorianCalendar();
        time2.set(Calendar.HOUR_OF_DAY, 11);
        time2.set(Calendar.MINUTE, 59);
        time2.set(Calendar.SECOND, 0);
        alarm2 = new Alarm(time2);
        
        //add alarms to the model in order: alarm2, alarm1
        model.addAlarm(alarm2);
        model.addAlarm(alarm1);
        
        //because alarm1 is set to go off before alarm1, getNextAlarm() should be return alarm1
        assertEquals(alarm1, model.getNextAlarm());
        
        //Note: within the program alarms are sorted depending on currentTime.
        //Whatever alarmTime is closest to currentTime will be NEXX ALARM
    }

//------------------------------------------------------------------------------    
    /**
     * Test of getAlarms method, of class Model.
     */
    @Test
    public void testGetAlarms_case1() {
        //Test case 1: When there are no alarms in the queue
        
        //ensure the queue is empty
        assertTrue(model.getAlarms().isEmpty());
        
        //getAlarms(), should return an empty list
        assertTrue(model.getAlarms().isEmpty());
    }
    
    @Test
    public void testGetAlarms_case2() {
        //Test case 2: When there is atleast one alarm in the queue
        
        //Create alarm1 instance
        time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 10);
        time1.set(Calendar.HOUR_OF_DAY, 30);
        time1.set(Calendar.HOUR_OF_DAY, 0);
        alarm1 = new Alarm(time1);
        
        //Create alarm2 instance
        time2 = new GregorianCalendar();
        time2.set(Calendar.HOUR_OF_DAY, 11);
        time2.set(Calendar.MINUTE, 30);
        time2.set(Calendar.SECOND, 0);
        alarm2 = new Alarm(time2);
        
        //add the alarms to the model in order: alarm2, alarm1
        model.addAlarm(alarm2);
        model.addAlarm(alarm1);
        
        //getAlarms() should return a list of alarms sorted by their alarmTime values:
        List<Alarm> expectedAlarms = Arrays.asList(alarm1, alarm2);
        assertEquals(expectedAlarms, model.getAlarms());
              
        //Note: alarms within program are sorted by closest to CurrentTime
        //If currentTime was 09:00:00 -> List(alarm1, alarm2)
        //If currentTime was 11:00:00 -> List(alarm2, alarm1)
    }
    
//------------------------------------------------------------------------------    
    /**
     * Test of clearAlarms method, of class Model.
     */
    @Test
    public void testClearAlarms() {
        //add alarms to queue.
        time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 10);
        time1.set(Calendar.MINUTE, 30);
        time1.set(Calendar.SECOND, 0);
        alarm1 = new Alarm(time1);

        time2 = new GregorianCalendar();
        time2.set(Calendar.HOUR_OF_DAY, 11);
        time2.set(Calendar.MINUTE, 30);
        time2.set(Calendar.SECOND, 0);
        alarm2 = new Alarm(time2);
        
        model.addAlarm(alarm1);
        model.addAlarm(alarm2);
        
        //confirm alarms were added:
        assertTrue(model.getAlarms().contains(alarm1));
        assertTrue(model.getAlarms().contains(alarm2));
        
        //Clear alarms
        model.clearAlarms();
        
        //excpected result = queue should now be empty
        assertTrue(model.getAlarms().isEmpty());
    }

//------------------------------------------------------------------------------
    /**
     * Test of isAlarmDuplicate method, of class Model.
     */
    @Test
    public void testIsAlarmDuplicate() {
        
        //Create alarms
        time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 10);
        time1.set(Calendar.MINUTE, 30);
        time1.set(Calendar.SECOND, 0);
        alarm1 = new Alarm(time1);

        time2 = new GregorianCalendar();
        time2.set(Calendar.HOUR_OF_DAY, 11);
        time2.set(Calendar.MINUTE, 30);
        time2.set(Calendar.SECOND, 0);
        alarm2 = new Alarm(time2);
        
        //add only alarm1
        model.addAlarm(alarm1);
        
        //Check whether the alarms in queue can be considered a duplicate
        //Check if alarm1 is considered a duplicate (YES)
        assertTrue(model.isAlarmDuplicate(alarm1));
        
        //Check if alarm2 is a considered a duplicate (NO)
        assertFalse(model.isAlarmDuplicate(alarm2));
    }
    
}
