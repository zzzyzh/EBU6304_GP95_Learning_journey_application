package pro.entities;

import java.util.Calendar;
import pro.utils.Convertor;

/**
 * A class to represent an activity with name, description, start time and end time.
 * @author Yuqing Cui
 */
public class Activity extends Convertor {

    private String name;
    private String description;
    private Calendar startTime;
    private Calendar endTime;

    /**
     * Constructs a specific activity with name, description, start time and end time
     * 
     * @param name          name of activity
     * @param description   description of activity
     * @param startTime     start time of activity
     * @param endTime       end time of activity
     */
    public Activity(String name, String description, Calendar startTime, Calendar endTime) {
        this.setName(name);
        this.setDescription(description);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
    }

    /**
     * Constructs a default activity
     */
    public Activity() {}

    /**
     * Gets the activity name
     * 
     * @return activity name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the activity name
     * 
     * @param name activity name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the description
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description
     * 
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the start time
     * 
     * @return start time
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time
     * 
     * @param startTime start time
     */
    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the end time
     * 
     * @return endtime
     */
    public Calendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time
     * 
     * @param endTime end time
     */
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
}
