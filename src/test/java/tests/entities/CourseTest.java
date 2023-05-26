package tests.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pro.entities.Course;
import java.util.Calendar;

/**
 * Test for class Course
 * @author Ruitang Lin
 */
public class CourseTest {

    private Course course;

    @Before
    public void setup() {
        course = new Course();
    }

    @Test
    public void testSetName() {
        String name = "Math";
        course.setName(name);
        Assert.assertEquals(name, course.getName());
    }

    @Test
    public void testSetStartTime() {
        Calendar startTime = Calendar.getInstance();
        course.setStartTime(startTime);
        Assert.assertEquals(startTime, course.getStartTime());
    }

    @Test
    public void testSetEndTime() {
        Calendar endTime = Calendar.getInstance();
        course.setEndTime(endTime);
        Assert.assertEquals(endTime, course.getEndTime());
    }
}