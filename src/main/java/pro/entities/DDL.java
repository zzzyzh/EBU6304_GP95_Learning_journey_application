package pro.entities;

import java.util.Calendar;
import pro.utils.Convertor;

/**
 * The DDL class represents a task with name, description and due time
 * @author Bingyao Li
 */
public class DDL extends Convertor{

    private String name;
    private String description;
    private Calendar dueTime;

    /**
     * Constructs a task with specific name, description and due time
     * 
     * @param name         name of the task
     * @param description  description of the task
     * @param dueTime      due time of the task
     */
    public DDL(String name, String description, Calendar dueTime) {
        this.setName(name);
        this.setDescription(description);
        this.setDueTime(dueTime);
    }

    /**
     * Constructs a default task
     */
    public DDL() {};

    /**
     * Gets the DDL name
     * 
     * @return DDL name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the DDL name
     * 
     * @param name DDL name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the DDL description
     * 
     * @return DDL description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the DDL description
     * 
     * @param description DDL description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the DDL dueTime
     * 
     * @return DDL dueTime
     */
    public Calendar getDueTime() {
        return dueTime;
    }

    /**
     * Sets the DDL dueTime
     * 
     * @param dueTime DDL dueTime
     */
    public void setDueTime(Calendar dueTime) {
        this.dueTime = dueTime;
    }
}
