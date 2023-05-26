package tests.utils;

import org.junit.Assert;
import org.junit.Test;
import pro.entities.*;
import java.util.Calendar;

/**
 * Test for class Convertor
 * @author Yixuan Gou
 */
public class ConvertorTest {

    @Test
    public void testSetFromCSVStringWithNumber() {
        Grades grades = new Grades("Math", 1, 85.5, 3.0, 1);
        String csvString = grades.toCSVString();
        String expectedCsvString = "Math,1,85.5,3.0,1";
        Assert.assertEquals(expectedCsvString, csvString);
    }

    @Test
    public void testToCSVStringWithNumber() {
        Grades grades = new Grades();
        String csvString = "Math,1,85.5,3.0,1";
        grades.setFromCSVString(csvString);
        Assert.assertEquals(1, grades.getTerm());
        Assert.assertEquals(85.5, grades.getGrade(), 0.001);
        Assert.assertEquals(3.0, grades.getCredit(), 0.001);
        Assert.assertEquals(1, grades.getRank());
    }

    @Test
    public void testToCSVStringWithCalendar() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(2022, Calendar.JANUARY, 1, 10, 2, 3);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2023, Calendar.MAY, 4, 0, 1, 0);
        Plan plan = new Plan("Meeting", startTime, endTime);
        String csvString = plan.toCSVString();
        String expectedCsvString = "Meeting,2022-01-01 10:02:03,2023-05-04 00:01:00";
        Assert.assertEquals(expectedCsvString, csvString);
    }

    @Test
    public void testSetFromCSVStringWithCalendar() {
        Plan plan = new Plan();
        String csvString = "Meeting,2022-01-01 10:02:03,2023-05-04 00:01:00";
        plan.setFromCSVString(csvString);
        Calendar expectedStartTime = Calendar.getInstance();
        expectedStartTime.set(2022, Calendar.JANUARY, 1, 10, 2, 3);
        Assert.assertEquals(expectedStartTime.get(Calendar.YEAR), plan.getStartTime().get(Calendar.YEAR));
        Assert.assertEquals(expectedStartTime.get(Calendar.MONTH), plan.getStartTime().get(Calendar.MONTH));
        Assert.assertEquals(expectedStartTime.get(Calendar.DAY_OF_MONTH), plan.getStartTime().get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(expectedStartTime.get(Calendar.HOUR_OF_DAY), plan.getStartTime().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(expectedStartTime.get(Calendar.MINUTE), plan.getStartTime().get(Calendar.MINUTE));
        Assert.assertEquals(expectedStartTime.get(Calendar.SECOND), plan.getStartTime().get(Calendar.SECOND));
        Calendar expectedEndTime = Calendar.getInstance();
        expectedEndTime.set(2023, Calendar.MAY, 4, 0, 1, 0);
        Assert.assertEquals(expectedEndTime.get(Calendar.YEAR), plan.getEndTime().get(Calendar.YEAR));
        Assert.assertEquals(expectedEndTime.get(Calendar.MONTH), plan.getEndTime().get(Calendar.MONTH));
        Assert.assertEquals(expectedEndTime.get(Calendar.DAY_OF_MONTH), plan.getEndTime().get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(expectedEndTime.get(Calendar.HOUR_OF_DAY), plan.getEndTime().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(expectedEndTime.get(Calendar.MINUTE), plan.getEndTime().get(Calendar.MINUTE));
        Assert.assertEquals(expectedEndTime.get(Calendar.SECOND), plan.getEndTime().get(Calendar.SECOND));
    }

    @Test
    public void testToCSVStringWithNewLine() {
        String description = "Meeting with\nTeam";
        Calendar startTime = Calendar.getInstance();
        startTime.set(2022, Calendar.JANUARY, 1, 10, 2, 3);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2023, Calendar.MAY, 4, 0, 1, 0);
        Plan plan = new Plan(description, startTime, endTime);
        String csvString = plan.toCSVString();
        String expectedCsvString = "Meeting with\\nTeam,2022-01-01 10:02:03,2023-05-04 00:01:00";
        Assert.assertEquals(expectedCsvString, csvString);
    }

    @Test
    public void testSetFromCSVStringWithNewLine() {
        String description = "Meeting with\nTeam";
        Calendar startTime = Calendar.getInstance();
        startTime.set(2022, Calendar.JANUARY, 1, 10, 2, 3);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2023, Calendar.MAY, 4, 0, 1, 0);
        String csvString = "Meeting with\\nTeam,2022-01-01 10:02:03,2023-05-04 00:01:00";
        Plan newPlan = new Plan();
        newPlan.setFromCSVString(csvString);
        Assert.assertEquals(description, newPlan.getDescription());
    }

    @Test
    public void testToCSVStringWithDelimiter() {
        String description = "Meeting with,Team";
        Calendar startTime = Calendar.getInstance();
        startTime.set(2022, Calendar.JANUARY, 1, 10, 2, 3);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2023, Calendar.MAY, 4, 0, 1, 0);
        Plan plan = new Plan(description, startTime, endTime);
        String csvString = plan.toCSVString();
        String expectedCsvString = "`Meeting with,Team`,2022-01-01 10:02:03,2023-05-04 00:01:00";
        Assert.assertEquals(expectedCsvString, csvString);
    }

    @Test
    public void testSetFromCSVStringWithDelimiter() {
        String description = "Meeting wi,thTeam";
        Calendar startTime = Calendar.getInstance();
        startTime.set(2022, Calendar.JANUARY, 1, 10, 2, 3);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2023, Calendar.MAY, 4, 0, 1, 0);
        String csvString = "`Meeting wi,thTeam`,2022-01-01 10:02:03,2023-05-04 00:01:00";
        Plan newPlan = new Plan();
        newPlan.setFromCSVString(csvString);
        Assert.assertEquals(description, newPlan.getDescription());
    }
}