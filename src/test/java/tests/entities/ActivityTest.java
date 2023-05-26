package tests.entities;

import org.junit.Assert;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import pro.entities.Activity;

/**
 * Test for class Activity
 * @author Zhonghao Yan
 */
public class ActivityTest {

    private Activity activity;

    @Before
    public void setUp() {
        activity = new Activity();
    }

    @Test
    public void testSetName() {
        activity.setName("test activity");
        Assert.assertEquals("test activity", activity.getName());
    }

    @Test
    public void testSetDescription() {
        activity.setDescription("test description");
        Assert.assertEquals("test description", activity.getDescription());
    }

    @Test
    public void testSetStartTime() {
        Calendar startTime= Calendar.getInstance();
        startTime.set(2023, Calendar.MAY, 25, 8, 0, 0);
        activity.setStartTime(startTime);
        Assert.assertEquals(startTime, activity.getStartTime());
    }

    @Test
    public void testSetEndTime() {
        Calendar endTime = Calendar.getInstance();
        endTime.set(2023, Calendar.MAY, 25, 9, 0, 0);
        activity.setEndTime(endTime);
        Assert.assertEquals(endTime, activity.getEndTime());
    }
}