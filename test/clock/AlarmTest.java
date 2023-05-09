
package clock;

import java.util.Calendar;
import java.util.GregorianCalendar;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * JUnit4 Testing -> Alarm Object
 * 
 * @author Lee Devine
 */
public class AlarmTest {
    
    //Setup instances for testing
    private Calendar time1, time2, time3, time4;
    private Alarm alarm1, alarm2, alarm3, alarm4;
    
    public AlarmTest() {
    }

    @Before
    public void setUp(){
        time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 10);
        time1.set(Calendar.MINUTE, 30);
        time1.set(Calendar.SECOND, 30);
        alarm1 = new Alarm(time1);

        time2 = new GregorianCalendar();
        time2.set(Calendar.HOUR_OF_DAY, 15);
        time2.set(Calendar.MINUTE,44);
        time2.set(Calendar.SECOND, 1);
        alarm2 = new Alarm(time2);

        //Edge Case2 -> Max
        time3 = new GregorianCalendar();
        time3.set(Calendar.HOUR_OF_DAY, 23);
        time3.set(Calendar.MINUTE, 59);
        time3.set(Calendar.SECOND, 59);
        alarm3 = new Alarm(time3);
        
        //Edge Case1 -> Min
        time4 = new GregorianCalendar();
        time4.set(Calendar.HOUR_OF_DAY, 0);
        time4.set(Calendar.MINUTE, 0);
        time4.set(Calendar.SECOND, 0);
        alarm4 = new Alarm(time4);
    }
    
    /**
     * Test of getAlarmTime method, of class Alarm.
     * Case 1. 
     * Case 2.
     * Case 3.
     * Case 4.
     */
    @Test
    public void testGetAlarmTime_case1() {assertEquals(time1, alarm1.getAlarmTime());}
    @Test
    public void testGetAlarmTime_case2() {assertEquals(time2, alarm2.getAlarmTime());}
    @Test
    public void testGetAlarmTime_case3() {assertEquals(time3, alarm3.getAlarmTime());}
    @Test
    public void testGetAlarmTime_case4() {assertEquals(time4, alarm4.getAlarmTime());}

    /**
     * Test of setAlarmTime method, of class Alarm.
     * Case 1.
     * Case 2.
     * Case 3.
     * Case 4.
     */
    @Test
    public void testSetAlarmTime_case1() {
        Calendar newTime = new GregorianCalendar();
        newTime.set(Calendar.HOUR_OF_DAY, 12);
        newTime.set(Calendar.HOUR_OF_DAY, 33);
        newTime.set(Calendar.HOUR_OF_DAY, 0);
        alarm1.setAlarmTime(newTime);
        assertEquals(newTime, alarm1.getAlarmTime());
    }
    @Test
    public void testSetAlarmTime_case2() {
        Calendar newTime = new GregorianCalendar();
        newTime.set(Calendar.HOUR_OF_DAY, 23);
        newTime.set(Calendar.HOUR_OF_DAY, 59);
        newTime.set(Calendar.HOUR_OF_DAY, 59);
        alarm2.setAlarmTime(newTime);
        assertEquals(newTime, alarm2.getAlarmTime());
    }
    @Test
    public void testSetAlarmTime_case3() {
        Calendar newTime = new GregorianCalendar();
        newTime.set(Calendar.HOUR_OF_DAY, 0);
        newTime.set(Calendar.HOUR_OF_DAY, 0);
        newTime.set(Calendar.HOUR_OF_DAY, 0);
        alarm3.setAlarmTime(newTime);
        assertEquals(newTime, alarm3.getAlarmTime());
    }
    @Test
    public void testSetAlarmTime_case4() {
        Calendar newTime = new GregorianCalendar();
        newTime.set(Calendar.HOUR_OF_DAY, 3);
        newTime.set(Calendar.HOUR_OF_DAY, 33);
        newTime.set(Calendar.HOUR_OF_DAY, 33);
        alarm4.setAlarmTime(newTime);
        assertEquals(newTime, alarm4.getAlarmTime());
    }

    /**
     * Test of getHour method, of class Alarm.
     */
    @Test
    public void testGetHour_case1() {assertEquals(10, alarm1.getHour());}
    @Test
    public void testGetHour_case2() {assertEquals(15, alarm2.getHour());}
    @Test
    public void testGetHour_case3() {assertEquals(23, alarm3.getHour());}
    @Test
    public void testGetHour_case4() {assertEquals(0, alarm4.getHour());}

    /**
     * Test of getMinute method, of class Alarm.
     */
    @Test
    public void testGetMinute_case1() {assertEquals(30, alarm1.getMinute());}
    @Test
    public void testGetMinute_case2() {assertEquals(44, alarm2.getMinute());}
    @Test
    public void testGetMinute_case3() {assertEquals(59, alarm3.getMinute());}
    @Test
    public void testGetMinute_case4() {assertEquals(0, alarm4.getMinute());}

    /**
     * Test of getSecond method, of class Alarm.
     */
    @Test
    public void testGetSecond_case1() {assertEquals(30, alarm1.getSecond());}
    @Test
    public void testGetSecond_case2() {assertEquals(1, alarm2.getSecond());}
    @Test
    public void testGetSecond_case3() {assertEquals(59, alarm3.getSecond());}
    @Test
    public void testGetSecond_case4() {assertEquals(0, alarm4.getSecond());}

    /**
     * Test of compareTo method, of class Alarm.
     */
    @Test
    public void testCompareTo_case1() {
        assertTrue(alarm1.compareTo(alarm2) < 0); // alarm1 is earlier than alarm2
    }
    @Test
    public void testCompareTo_case2() {
        assertTrue(alarm2.compareTo(alarm1) > 0); // alarm2 is later than alarm1
    }
    @Test
    public void testCompareTo_case3() {
        assertTrue(alarm1.compareTo(alarm1) == 0); // an alarm is equal to itself
    }


    /**
     * Test of toString method, of class Alarm.
     */
    @Test
    public void testToString_case1() {assertEquals("10:30:30", alarm1.toString());}
    @Test
    public void testToString_case2() {assertEquals("15:44:01", alarm2.toString());}
    @Test
    public void testToString_case3() {assertEquals("23:59:59", alarm3.toString());}
    @Test
    public void testToString_case4() {assertEquals("00:00:00", alarm4.toString());}
   
}
