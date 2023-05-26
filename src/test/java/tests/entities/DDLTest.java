package tests.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pro.entities.DDL;

import java.util.Calendar;

/**
 * Test for class DDL
 * @author Haotian Yu
 */
public class DDLTest {

    private DDL ddl;

    @Before
    public void setup() {
        ddl = new DDL();
    }

    @Test
    public void testSetName() {
        String name = "Task 1";
        ddl.setName(name);
        Assert.assertEquals(name, ddl.getName());
    }

    @Test
    public void testSetDescription() {
        String description = "This is a task";
        ddl.setDescription(description);
        Assert.assertEquals(description, ddl.getDescription());
    }

    @Test
    public void testSetDueTime() {
        Calendar dueTime = Calendar.getInstance();
        ddl.setDueTime(dueTime);
        Assert.assertEquals(dueTime, ddl.getDueTime());
    }
}