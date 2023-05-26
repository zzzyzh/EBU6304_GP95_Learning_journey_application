package tests.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pro.entities.Grades;

/**
 * Test for class Grades
 * @author Yuqing cui
 */
public class GradesTest {

    private Grades grades;

    @Before
    public void setup() {
        grades = new Grades();
    }

    @Test
    public void testSetName() {
        String name = "Math";
        grades.setName(name);
        Assert.assertEquals(name, grades.getName());
    }

    @Test
    public void testSetTerm() {
        int term = 2;
        grades.setTerm(term);
        Assert.assertEquals(term, grades.getTerm());
    }

    @Test
    public void testSetGradePass() {
        double grade = 85.5;
        grades.setGrade(grade);
        Assert.assertEquals(grade, grades.getGrade(), 0.01);
        Assert.assertEquals(3.60, grades.getGpa(), 0.01);
    }
    @Test
    public void testSetGradeUnpass() {
        double grade = 50.0;
        grades.setGrade(grade);
        Assert.assertEquals(grade, grades.getGrade(), 0.01);
        Assert.assertEquals(0.0, grades.getGpa(), 0.01);
    }

    @Test
    public void testSetCredit() {
        double credit = 3.0;
        grades.setCredit(credit);
        Assert.assertEquals(credit, grades.getCredit(), 0.01);
    }

    @Test
    public void testSetRank() {
        int rank = 1;
        grades.setRank(rank);
        Assert.assertEquals(rank, grades.getRank());
    }

    @Test
    public void testEquals() {
        Grades grades1 = new Grades("Math", 1, 85.5, 3.0, 1);
        Grades grades2 = new Grades("Math", 2, 90.0, 4.0, 2);
        Grades grades3 = new Grades("English", 1, 92.0, 3.0, 1);

        Assert.assertTrue(grades1.equals(grades2));
        Assert.assertFalse(grades1.equals(grades3));
    }
}