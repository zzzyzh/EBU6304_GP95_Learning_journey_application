package pro.frames;

import pro.entities.Activity;
import pro.utils.CSVProcessor;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * A class to generate a frame to show activities recorded
 * @author Yuqing Cui
 */
public class ActivityFrame extends BaseFrame {

    /**
     * Constructs a frame to show frame displaying activities
     */
    public ActivityFrame() {
        //whole box
        Box totalBox = new Box(BoxLayout.Y_AXIS);
        
        //the container of the add button
        Box addBox = new Box(BoxLayout.X_AXIS);

        //add button
        JButton addButton = new JButton("ADD+");
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true); 
        addButton.setBorderPainted(false);
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        addBox.add(addButton);

        //extracts this
        ActivityFrame that = this;

        //navigates to ModifyActivityFrame when clicking the button
        addButton.addActionListener(e -> {
            that.dispose();
            new ModifyActivityFrame("Add Activity", 0);
        });

        //adds the button container to the box
        totalBox.add(Box.createVerticalStrut(10));
        totalBox.add(addButton);
        totalBox.add(Box.createVerticalStrut(10));

        //a formatter to convert Calendar object to String object
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //reads data from csv
        CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Activity.csv", Activity.class);
        Object[] objects = csvProcessor.readData();

        //1 container for 1 activity
        Box[] itemBox = new Box[objects.length];

        //adds all activity to the frame
        for(int i = 0; i < objects.length; i++) {

            //current activity instance
            Activity activity = (Activity) objects[i];

            //activity title
            JLabel title = new JLabel(activity.getName());
            title.setAlignmentX(CENTER_ALIGNMENT);
            title.setFont(new Font("Arial", Font.BOLD, 20));

            //creates the box to seat the activity
            itemBox[i] = new Box(BoxLayout.Y_AXIS);
            
            
            //area for activity description
            JTextArea jTextArea = new JTextArea(3, 20);
            jTextArea.setText(activity.getDescription());
            jTextArea.setEditable(false);
            
            //modify button
            JButton modifyButton = new JButton("MODIFY");
            modifyButton.setBackground(Color.BLUE);
            modifyButton.setForeground(Color.WHITE);
            modifyButton.setOpaque(true); 
            modifyButton.setBorderPainted(false);
            
            //extracts i
            int t = i;
            
            //navigates to ModifyActivityFrame when clicking the button to modify current activity
            modifyButton.addActionListener(e -> {
                that.dispose();
                new ModifyActivityFrame("Modify Activity", t);
            });
            
            //box to seat the activity time and modify button
            Box boxTime = new Box(BoxLayout.X_AXIS);
            boxTime.add(new JLabel(simpleDateFormat.format(activity.getStartTime().getTime()) + " -- " + simpleDateFormat.format(activity.getEndTime().getTime())));
            boxTime.add(Box.createHorizontalGlue());
            boxTime.add(modifyButton);

            //adds the components in order to the itemBox
            itemBox[i].add(title);
            itemBox[i].add(jTextArea);
            itemBox[i].add(boxTime);
        }

        //Two activities in a row
        for(int i = 0; i < itemBox.length; i += 2) {


            //a horizontal box
            Box secBox = new Box(BoxLayout.X_AXIS);

            //adds two activities
            secBox.add(Box.createHorizontalStrut(20));
            secBox.add(itemBox[i]);
            secBox.add(Box.createHorizontalStrut(20));
            if(i + 1 < itemBox.length) {
                secBox.add(itemBox[i + 1]);
                secBox.add(Box.createHorizontalStrut(20));
            }

            //added to the main box
            totalBox.add(secBox);
            totalBox.add(Box.createVerticalStrut(50));
        }

        //scrollable panel to show all activities
        JScrollPane jScrollPane = new JScrollPane(totalBox); 
        jScrollPane.setPreferredSize(new Dimension(600, 400));
        SwingUtilities.invokeLater(() -> jScrollPane.getVerticalScrollBar().setValue(jScrollPane.getVerticalScrollBar().getMinimum()));
        this.add(jScrollPane);

        //configuration to the frame
        setBackToHome();
        setProperties("Activities");
    }
}
