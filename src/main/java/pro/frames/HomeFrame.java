package pro.frames;

import javax.swing.*;
import java.awt.*;

/**
 * A class to display homepage of the project
 * @author Ruitang Lin
 */
public class HomeFrame extends BaseFrame{

    /**
     * Constructs a homepage frame
     */
    public HomeFrame() {
        
        //a box to seat all components
        Box box = new Box(BoxLayout.Y_AXIS);

        //sets a title of the page and adds it into the box
        JLabel title = new JLabel("My Journey");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(20));
        box.add(title);

        //adds grades and plan buttons for navigation
        JButton gradesButton = new JButton("  Grades  ");
        gradesButton.setBackground(Color.BLUE);
        gradesButton.setForeground(Color.WHITE);
        gradesButton.setOpaque(true); 
        gradesButton.setBorderPainted(false);
        JButton planButton = new JButton("    Plan     ");
        planButton.setBackground(Color.BLUE); 
        planButton.setForeground(Color.WHITE);
        planButton.setOpaque(true); 
        planButton.setBorderPainted(false);

        //creates a box to seat the two buttons
        Box box1 = new Box(BoxLayout.X_AXIS);
        box1.add(Box.createHorizontalStrut(60));
        box1.add(gradesButton);
        box1.add(Box.createHorizontalStrut(60));
        box1.add(planButton);
        box1.add(Box.createHorizontalStrut(60));

        //add the box1 containing the two buttons above to the box
        box.add(Box.createVerticalStrut(30));
        box.add(box1);

        //adds ddl and activity button for navigation
        JButton ddlButton = new JButton("     DDL     ");
        ddlButton.setBackground(Color.BLUE);  
        ddlButton.setForeground(Color.WHITE);
        ddlButton.setOpaque(true); 
        ddlButton.setBorderPainted(false);
        JButton activityButton = new JButton("  Activity  ");
        activityButton.setBackground(Color.BLUE);
        activityButton.setForeground(Color.WHITE);
        activityButton.setOpaque(true); 
        activityButton.setBorderPainted(false);

        //creates a box to seat the two buttons
        Box box2 = new Box(BoxLayout.X_AXIS);
        box2.add(Box.createHorizontalStrut(60));
        box2.add(ddlButton);
        box2.add(Box.createHorizontalStrut(60));
        box2.add(activityButton);
        box2.add(Box.createHorizontalStrut(60));

        //adds the box2 containing the two button above to the box
        box.add(Box.createVerticalStrut(30));
        box.add(box2);

        //adds term and course buttons for navigation
        JButton termButton = new JButton("TermScore");
        termButton.setBackground(Color.BLUE);
        termButton.setForeground(Color.WHITE);
        termButton.setOpaque(true); 
        termButton.setBorderPainted(false);
        JButton courseButton = new JButton("Schedule");
        courseButton.setBackground(Color.BLUE);
        courseButton.setForeground(Color.WHITE);
        courseButton.setOpaque(true); 
        courseButton.setBorderPainted(false);

        //creates a box to seat the two buttons
        Box box3 = new Box(BoxLayout.X_AXIS);
        box3.add(Box.createHorizontalStrut(60));
        box3.add(termButton);
        box3.add(Box.createHorizontalStrut(60));
        box3.add(courseButton);
        box3.add(Box.createHorizontalStrut(60));

        //adds the box3 containing the two button above to the box
        box.add(Box.createVerticalStrut(30));
        box.add(box3);

        //adds box to this frame
        box.add(Box.createVerticalStrut(30));
        this.add(box);

        //extracts this
        HomeFrame that = this;

        //configures the navigation of the grades button
        gradesButton.addActionListener(e -> {
            that.dispose();
            new ScoreFrame();
        });

        //configures the navigation of the grades button
        ddlButton.addActionListener(e -> {
            that.dispose();
            new DDLFrame();
        });

        //configures the navigation of the grades button
        activityButton.addActionListener(e -> {
            that.dispose();
            new ActivityFrame();
        });
        
        //configures the navigation of the grades button
        planButton.addActionListener(e -> {
            that.dispose();
            new PlanFrame();
        });

        //configures the navigation of the grades button
        termButton.addActionListener(e -> {
            that.dispose();
            new TermAverageFrame();
        });

        //configures the navigation of the grades button
        courseButton.addActionListener(e -> {
            that.dispose();
            new ScheduleFrame();
        });

        //configures some properties
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setProperties("homepage");
    }
}
