package pro.frames;

import pro.entities.Course;
import pro.utils.CSVProcessor;
import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A class to display a frame showing today's course schedule
 * @author Haotian Yu
 */
public class ScheduleFrame extends BaseFrame{

    /**
     * constucts a frame to show today's course schedule
     */
    public ScheduleFrame() {

        //creates a box to seat all components
        Box box = new Box(BoxLayout.Y_AXIS);

        //creates the title of the frame
        JLabel titleLabel = new JLabel("TimeTable");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        //creates a box to seat the title label
        Box titleBox = new Box(BoxLayout.X_AXIS);
        titleBox.add(Box.createHorizontalGlue());
        titleBox.add(titleLabel);
        titleBox.add(Box.createHorizontalGlue());
        titleBox.setAlignmentX(CENTER_ALIGNMENT);
        
        //creates the date today
        JLabel dateLabel = new JLabel(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("en", "US")));
        dateLabel.setAlignmentX(CENTER_ALIGNMENT);

        //creates dateBox to seat date today
        Box dateBox = new Box(BoxLayout.X_AXIS);
        dateBox.add(Box.createHorizontalGlue());
        dateBox.add(dateLabel);
        dateBox.add(Box.createHorizontalGlue());
        dateBox.setAlignmentX(CENTER_ALIGNMENT);

        //adds components to the main box
        box.add(Box.createVerticalStrut(20));
        box.add(titleBox);
        box.add(dateBox);
        box.add(Box.createVerticalStrut(20));

        //gets the current day in a week
        int day = LocalDate.now().getDayOfWeek().getValue();
        
        //extracts this
        ScheduleFrame that = this;

        //reads data from file
        CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Schedule-" + day + ".csv", Course.class);
        Object[] objects = csvProcessor.readData();
        ArrayList<Course> arrayList = new ArrayList<>();

        //if there are no courses today
        if(objects == null || objects.length == 0) {
            //creates the label
            JLabel emptyLabel = new JLabel("NO CLASS TODAY!");
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 35));
            emptyLabel.setAlignmentX(CENTER_ALIGNMENT);
            Box emptyBox = new Box(BoxLayout.X_AXIS);
            emptyBox.add(Box.createHorizontalGlue());
            emptyBox.add(emptyLabel);
            emptyBox.add(Box.createHorizontalGlue());
            emptyBox.setAlignmentX(CENTER_ALIGNMENT);
            box.add(Box.createVerticalStrut(100));
            box.add(emptyBox);
            box.add(Box.createVerticalStrut(100));
        } else {
            //pushes all courses today to the arrayList
            for(Object obj: objects) arrayList.add((Course) obj);

            //date formater to format date
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

            //create a panel to seat all courses
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout(arrayList.size(), 5, 10, 20));

            //traverses all courses
            for(int i = 0; i < arrayList.size(); i++) {

                //creates name label
                JLabel nameLabel = new JLabel(arrayList.get(i).getName());
                nameLabel.setAlignmentX(LEFT_ALIGNMENT);

                //creates name label
                JLabel timeLabel = new JLabel(dateFormat.format(arrayList.get(i).getStartTime().getTime()) + "--" + dateFormat.format(arrayList.get(i).getEndTime().getTime()));

                //creates modify button
                JButton modifyButton = new JButton("MODIFY");
                modifyButton.setBackground(Color.BLUE);
                modifyButton.setForeground(Color.WHITE);
                modifyButton.setOpaque(true); 
                modifyButton.setBorderPainted(false);

                //adds components to the panel
                jPanel.add(new JLabel("     "));
                jPanel.add(nameLabel);
                jPanel.add(timeLabel);
                jPanel.add(modifyButton);
                jPanel.add(new JLabel("     "));

                //extracts i
                int t = i;

                //gives the modify button a listener to handle the click event
                modifyButton.addActionListener(e -> {
                    that.dispose();
                    new ModifyScheduleFrame("Modify Course", t);
                });
            }
            box.add(jPanel);
        }
        //sets the box width
        box.add(Box.createHorizontalStrut(400));

        //creates add button
        JButton addButton = new JButton("ADD");
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true); 
        addButton.setBorderPainted(false);
        addButton.addActionListener(e -> {
            that.dispose();
            new ModifyScheduleFrame("Add Course", 0);
        });

        //adds 'add button' to the main box
        box.add(Box.createVerticalStrut(20));
        box.add(addButton);
        box.add(Box.createVerticalStrut(20));

        //adds the main box to the frame
        this.add(box);

        //set properties
        setBackToHome();
        setProperties("Schedule");
    }
}
