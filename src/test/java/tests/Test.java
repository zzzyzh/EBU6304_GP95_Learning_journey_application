package tests;

import tests.entities.*;
import tests.utils.ConvertorTest;


/**
 * Test entrance
 * @author Ruitang Lin
 */
public class Test {

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(
            PlanTest.class.getName(),
            DDLTest.class.getName(),
            CourseTest.class.getName(),
            ActivityTest.class.getName(),
            GradesTest.class.getName(),
            ConvertorTest.class.getName()
        );
    }
}