package pro.frames;

import pro.entities.Course;
import pro.utils.CSVProcessor;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;

/**
 * A class to display a frame for user to add, modify or delete a course from class schedule
 * @author Haotian Yu
 */
public class ModifyScheduleFrame extends BaseFrame {

    /**
     * Constructs a frame for user to add, modify or delete a course from class schedule
     * 
     * @param title     title of the frame
     * @param index     index of the specific course waiting to be modified or deleted
     */
    public ModifyScheduleFrame(String title, int index) {

        //get the day in current week
        int day = LocalDate.now().getDayOfWeek().getValue();

        //read data from file
        CSVProcessor csvProcesser = new CSVProcessor("DataFiles/Schedule-" + day + ".csv", Course.class);
        Object[] objects = csvProcesser.readData();
        ArrayList<Course> arrayList = new ArrayList<>();

        //if there exists classes today, push them into the arrayList
        if(!(objects == null || objects.length == 0)) for(Object obj: objects) arrayList.add((Course) obj);
            
        //set the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //adds a plan label
        JLabel addPlanLabel = new JLabel("Modify Course");
        addPlanLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addPlanLabel.setForeground(Color.BLUE);
        addPlanLabel.setAlignmentX(CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(addPlanLabel, gbc);

        //adds plan description label
        JLabel planDescriptionLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(planDescriptionLabel, gbc);

        //adds plan description text area
        JTextField nameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameField, gbc);

        //adds time item label
        gbc.gridy = 3;
        gbc.gridx = 1;
        add(new JLabel("Hour"), gbc);
        gbc.gridx = 2;
        add(new JLabel("Minute"), gbc);

        //adds start time label
        JLabel startTimeLabel = new JLabel("Start Time:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(startTimeLabel, gbc);

        //hour comboBox for start time
        JComboBox<Integer> hourComboBox = new JComboBox<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24});
        gbc.gridx = 1;
        add(hourComboBox, gbc);

        //minute comboBox for start time
        JComboBox<Integer> minuteComboBox = new JComboBox<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
                40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59});
        gbc.gridx = 2;
        add(minuteComboBox, gbc);
        

        // end time label
        JLabel endTimeLabel = new JLabel("End Time:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        add(endTimeLabel, gbc);

        //hour comboBox for end time
        JComboBox<Integer> hourComboBox1 = new JComboBox<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22});
        gbc.gridx = 1;
        add(hourComboBox1, gbc);

        //minute comboBox for end time
        JComboBox<Integer> minuteComboBox1 = new JComboBox<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
                40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57});
        gbc.gridx = 2;
        add(minuteComboBox1, gbc);

        //extracts this
        ModifyScheduleFrame that = this;

        //a temporary comboBox
        JComboBox<Integer> ls = new JComboBox<>();
        ls.addItem(1);
        ls.setSelectedIndex(0);

        //judges the function of the window
        if(title.equals("Add Course")) {

            //creates and adds add button
            JButton addButton = new JButton("ADD");
            addButton.setBackground(Color.BLUE);
            addButton.setForeground(Color.WHITE);
            addButton.setOpaque(true);
            addButton.setBorderPainted(false);
            gbc.gridy = 6;
            gbc.gridx = 1;
            add(addButton, gbc); 
            
            //gives the add button a listener to handle the click event
            addButton.addActionListener(e -> {

                //if user doesn't input the name of the course
                if(nameField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(that, "Incompleted information is not allowed~");
                    return;
                }

                //if the start time of the course is later than the end time
                if(boxToCanlander(ls, ls, ls, hourComboBox, minuteComboBox).compareTo(
                    boxToCanlander(ls, ls, ls, hourComboBox1, minuteComboBox1)) >= 0) {
                    JOptionPane.showMessageDialog(that, "Start time should be earlier than end time~");
                    return;
                }

                //gets the new course, and adds it into the arrayList
                Course course = new Course(nameField.getText(),
                    that.boxToCanlander(ls, ls, ls, hourComboBox, minuteComboBox),
                    that.boxToCanlander(ls, ls, ls, hourComboBox1, minuteComboBox1)
                );
                arrayList.add(course);

                //sorts all courses by start time
                Comparator<Course> comparator = Comparator.comparing(Course::getStartTime);
                arrayList.sort(comparator);

                //judge if the courses are conflict
                boolean flag = true;
                for(int i = 0; i < arrayList.size() - 1; i++) {
                    if(arrayList.get(i).getEndTime().after(arrayList.get(i + 1).getStartTime())) {
                        flag = false;
                        break;
                    }
                }

                //if there exists conflict courses
                if(!flag) {
                    JOptionPane.showMessageDialog(that, "Conflict Course!");
                    arrayList.remove(course);
                    return;
                }

                //writes back to file
                csvProcesser.writeData(arrayList.toArray());

                //navigates to ScheduleFrame
                that.dispose();
                new ScheduleFrame();
            });
        } else {

            //sets the value of field according to target course
            nameField.setText(arrayList.get(index).getName());
            canlendarToBox(arrayList.get(index).getStartTime(), ls, ls, ls, hourComboBox, minuteComboBox);
            canlendarToBox(arrayList.get(index).getEndTime(), ls, ls, ls, hourComboBox1, minuteComboBox1);

            //creates modify and delete button
            JButton modifyButton = new JButton("MODIFY");
            modifyButton.setBackground(Color.BLUE);
            modifyButton.setForeground(Color.WHITE);
            modifyButton.setOpaque(true); 
            modifyButton.setBorderPainted(false);
            gbc.gridy = 6;
            gbc.gridx = 0;
            add(modifyButton, gbc);
            JButton deleteButton = new JButton("DELETE");
            deleteButton.setBackground(Color.BLUE);
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setOpaque(true); 
            deleteButton.setBorderPainted(false);
            gbc.gridy = 6;
            gbc.gridx = 2;
            add(deleteButton, gbc);

            //gives the modify button a listener to handle the click event
            modifyButton.addActionListener(e -> {

                //if user doesn't input the name of the course
                if(nameField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(that, "Incompleted information is not allowed~");
                    return;
                }

                //if the start time of the course is later than end time
                if(boxToCanlander(ls, ls, ls, hourComboBox, minuteComboBox).compareTo(
                    boxToCanlander(ls, ls, ls, hourComboBox1, minuteComboBox1)) >= 0) {
                    JOptionPane.showMessageDialog(that, "Start time should be earlier than end time~");
                    return;
                }

                //gets the current course
                Course course = new Course(nameField.getText(),
                    that.boxToCanlander(ls, ls, ls, hourComboBox, minuteComboBox),
                    that.boxToCanlander(ls, ls, ls, hourComboBox1, minuteComboBox1)
                );

                //gets the original course
                Course original = arrayList.get(index);

                //removes the original course and adds the new course
                arrayList.remove(index);
                arrayList.add(course);

                //sorts all courses according to their start time
                Comparator<Course> comparator = Comparator.comparing(Course::getStartTime);
                arrayList.sort(comparator);

                //judges if there exist conflict courses
                boolean flag = true;
                for(int i = 0; i < arrayList.size() - 1; i++) {
                    if(arrayList.get(i).getEndTime().after(arrayList.get(i + 1).getStartTime())) {
                        flag = false;
                        break;
                    }
                }

                //if there exists a conflict
                if(!flag) {
                    JOptionPane.showMessageDialog(that, "Conflict Course!");
                    arrayList.remove(course);
                    arrayList.add(original);
                    arrayList.sort(comparator);
                    return;
                }

                //writes back to file
                csvProcesser.writeData(arrayList.toArray());

                //navigate to ScheduleFrame
                that.dispose();
                new ScheduleFrame();
            });

            //gives the delete button a listener to handle the click event
            deleteButton.addActionListener(e -> {

                //removes the target course
                arrayList.remove(index);

                //writes back to file
                csvProcesser.writeData(arrayList.toArray());

                //navigates to ScheduleFrame
                that.dispose();
                new ScheduleFrame();
            });
        }

        //sets properties
        this.setPreferredSize(new Dimension(400, 400));
        setProperties(title);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new ScheduleFrame();
            }
        });
    }
}
