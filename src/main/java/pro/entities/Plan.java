package pro.entities;

import java.util.Calendar;
import pro.utils.Convertor;

/**
 * A class to represent a plan with description, start time and end time
 * @author Zhonghao Yan
 */
public class Plan extends Convertor{

    private String description;  
    private Calendar startTime;
    private Calendar endTime;

    /**
     * Constructs a plan with specific description, start time and end time
     * 
     * @param description       description of the plan
     * @param startTime         start time of the plan
     * @param endTime           end time of the plan
     */
    public Plan(String description, Calendar startTime, Calendar endTime) {
        this.setDescription(description);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
    }

    /**
     * Constructs a default plan
     */
    public Plan() {}

    /**
     * Gets the plan description
     * 
     * @return plan description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the plan description
     * 
     * @param description plan description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the plan startTime
     * 
     * @return plan startTime
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the plan startTime
     * 
     * @param startTime plan startTime
     */
    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the plan endTime
     * 
     * @return plan endTime
     */
    public Calendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the plan endTime
     * 
     * @param endTime plan endTime
     */
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
}
