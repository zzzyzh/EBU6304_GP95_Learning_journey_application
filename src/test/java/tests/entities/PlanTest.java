package tests.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pro.entities.Plan;
import java.util.Calendar;

/**
 * Test for class Plan
 * @author Bingyao Li
 */
public class PlanTest {

    private Plan plan;

    @Before
    public void setup() {
        plan = new Plan();
    }

    @Test
    public void testSetDescription() {
        String description = "Meeting";
        plan.setDescription(description);
        Assert.assertEquals(description, plan.getDescription());
    }

    @Test
    public void testSetStartTime() {
        Calendar startTime = Calendar.getInstance();
        plan.setStartTime(startTime);
        Assert.assertEquals(startTime, plan.getStartTime());
    }

    @Test
    public void testSetEndTime() {
        Calendar endTime = Calendar.getInstance();
        plan.setEndTime(endTime);
        Assert.assertEquals(endTime, plan.getEndTime());
    }
}