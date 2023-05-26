package pro.entities;

import java.util.Calendar;
import pro.utils.Convertor;

/**
 * The Course class represents a course with a name and a start time and end time.
 * @author Haotian Yu
*/
public class Course extends Convertor {

    private String name;
    private Calendar startTime;
    private Calendar endTime;

    /**
     * Constructs a Course object with the specified name, start time, and end time.
     * 
     * @param name       the name of the course
     * @param startTime  the start time of the course
     * @param endTime    the end time of the course
     */
    public Course(String name, Calendar startTime, Calendar endTime) {
        this.setEndTime(endTime);
        this.setStartTime(startTime);
        this.setName(name);
    }

    /**
     * Constructs a default Course object.
     */
    public Course() {}


    /**
     * Sets the name of the course.
     * 
     * @param name  the name of the course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the start time of the course.
     *
     * @param startTime  the start time of the course
     */
    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the end time of the course.
     *
     * @param endTime  the end time of the course
     */
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns the name of the course.
     *
     * @return the name of the course
     */
    public String getName() { 
        return this.name;
    }

    /**
     * Returns the start time of the course.
     *
     * @return the start time of the course
     */
    public Calendar getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end time of the course.
     *
     * @return the end time of the course
     */
    public Calendar getEndTime() {
        return this.endTime;
    }
}