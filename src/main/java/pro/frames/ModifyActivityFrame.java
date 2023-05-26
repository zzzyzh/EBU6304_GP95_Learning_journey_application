package pro.frames;

import pro.entities.Activity;
import pro.utils.CSVProcessor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * A class to display a frame of modifying an activity
 * @author Yuqing Cui
 */
public class ModifyActivityFrame extends BaseFrame{

    /**
     * Shows a frame to add, delete or modify a specific activity
     * 
     * @param title       title of the frame
     * @param idx         index of the activity waiting to be modified or deleted
     */
    public ModifyActivityFrame(String title, int idx) {

        //a box to seat all components
        Box box = new Box(BoxLayout.Y_AXIS);
        
        //day combobox for start time
        JComboBox<Integer> dayBox = new JComboBox<>();
        for (int j = 1; j <= 31; j++) {
            dayBox.addItem(j);
        }
        
        //year combobox for start time
        JComboBox<Integer> yearBox = new JComboBox<>();
        for (int j = 2000; j <= 2030; j++) {
            yearBox.addItem(j);
        }
        
        //month combobox for start time
        JComboBox<Integer> monthBox = new JComboBox<>();
        for (int j = 1; j <= 12; j++) {
            monthBox.addItem(j);
        }
        
        //listens to the change of year
        yearBox.addItemListener(e -> {
            if (ItemEvent.SELECTED == e.getStateChange()) {

                //gets the current month and day selected
                int month = (int) monthBox.getSelectedItem();
                int day = (int) dayBox.getSelectedItem();

                //saves the number of days in a month
                int dayCounts;

                //31 days for these months
                if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(month)) dayCounts = 31;

                //30 days for these months
                else if (Arrays.asList(4, 6, 9, 11).contains(month)) {
                    dayCounts = 30;

                    //modifies current day
                    if (day > 30) dayBox.setSelectedItem(30);
                } else {

                    //determines whether it is a leap year
                    int year = (int) yearBox.getSelectedItem();
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        dayCounts = 29;
                        if (day > 29) dayBox.setSelectedItem(29);
                    } else {
                        dayCounts = 28;
                        if (day > 28) dayBox.setSelectedItem(28);
                    }
                }

                //resets the content
                dayBox.removeAllItems();
                for (int j = 1; j <= dayCounts; j++) dayBox.addItem(j);
            }
        });
        
        //listens to the change of month
        monthBox.addItemListener(e -> {
            if (ItemEvent.SELECTED == e.getStateChange()) {

                //gets the current month and day
                int month = (int) monthBox.getSelectedItem();
                int day = (int) dayBox.getSelectedItem();

                //saves the number of days in a month
                int dayCounts;

                //31 days for these months
                if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(month)) dayCounts = 31;

                //30 days for these months
                else if (Arrays.asList(4, 6, 9, 11).contains(month)) {
                    dayCounts = 30;

                    //modifies current day
                    if (day > 30) dayBox.setSelectedItem(30);
                } else {

                    //determines whether it is a leap year
                    int year = (int) yearBox.getSelectedItem();
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        dayCounts = 29;
                        if (day > 29) dayBox.setSelectedItem(29);
                    } else {
                        dayCounts = 28;
                        if (day > 28) dayBox.setSelectedItem(28);
                    }
                }

                //resets the day comboBox
                dayBox.removeAllItems();
                for (int j = 1; j <= dayCounts; j++) dayBox.addItem(j);
            }
        });
        
        //day combobox for end time
        JComboBox<Integer> dayBox1 = new JComboBox<>();
        for (int j = 1; j <= 31; j++) {
            dayBox1.addItem(j);
        }
        
        //year combobox for end time
        JComboBox<Integer> yearBox1 = new JComboBox<>();
        for (int j = 2000; j <= 2030; j++) {
            yearBox1.addItem(j);
        }
        
        //month combobox for end time
        JComboBox<Integer> monthBox1 = new JComboBox<>();
        for (int j = 1; j <= 12; j++) {
            monthBox1.addItem(j);
        }
        
        //listens to the change of year
        yearBox1.addItemListener(e -> {
            if (ItemEvent.SELECTED == e.getStateChange()) {
                int month = (int) monthBox1.getSelectedItem();
                int day = (int) dayBox1.getSelectedItem();
                int dayCounts;
                if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(month)) dayCounts = 31;
                else if (Arrays.asList(4, 6, 9, 11).contains(month)) {
                    dayCounts = 30;
                    if (day > 30) dayBox1.setSelectedItem(30);
                } else {
                    int year = (int) yearBox1.getSelectedItem();
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        dayCounts = 29;
                        if (day > 29) dayBox1.setSelectedItem(29);
                    } else {
                        dayCounts = 28;
                        if (day > 28) dayBox1.setSelectedItem(28);
                    }
                }
                dayBox1.removeAllItems();
                for (int j = 1; j <= dayCounts; j++) dayBox1.addItem(j);
            }
        });

        //listen to the change of month
        monthBox1.addItemListener(e -> {
            if (ItemEvent.SELECTED == e.getStateChange()) {

                //gets the current month and day
                int month = (int) monthBox1.getSelectedItem();
                int day = (int) dayBox1.getSelectedItem();

                //saves the number of days in a month
                int dayCounts;

                //31 days for these months
                if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(month)) dayCounts = 31;

                //30 days for these months
                else if (Arrays.asList(4, 6, 9, 11).contains(month)) {
                    dayCounts = 30;

                    //modifies current day
                    if (day > 30) dayBox1.setSelectedItem(30);
                } else {

                    //determines whether it is a leap year
                    int year = (int) yearBox1.getSelectedItem();
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        dayCounts = 29;
                        if (day > 29) dayBox1.setSelectedItem(29);
                    } else {
                        dayCounts = 28;
                        if (day > 28) dayBox1.setSelectedItem(28);
                    }
                }

                //resets the day comboBox
                dayBox1.removeAllItems();
                for (int j = 1; j <= dayCounts; j++) dayBox1.addItem(j);
            }
        });

        //box to seat the label and combo boxes
        Box startBox = new Box(BoxLayout.X_AXIS);
        Box endBox = new Box(BoxLayout.X_AXIS);

        //receives user input of activity name
        JTextField jTextField = new JTextField();

        //receives user input of activity description
        JTextArea jTextArea = new JTextArea(5, 20);


        //adds label and jTextField to the box
        box.add(Box.createVerticalStrut(10));
        box.add(new Label("Activity name"));
        box.add(jTextField);
        
        box.add(Box.createVerticalStrut(20));
        
        //adds label and jTextArea to the box
        box.add(new Label("Activity description"));
        box.add(jTextArea);
        
        box.add(Box.createVerticalStrut(20));
        
        //adds labels and combo boxes into startBox, and add startBox into the box
        box.add(new Label("Start time"));
        startBox.add(Box.createHorizontalStrut(10));
        startBox.add(new JLabel("DAY"));
        startBox.add(Box.createHorizontalStrut(10));
        startBox.add(dayBox);
        startBox.add(Box.createHorizontalStrut(20));
        startBox.add(new JLabel("MONTH"));
        startBox.add(Box.createHorizontalStrut(10));
        startBox.add(monthBox);
        startBox.add(Box.createHorizontalStrut(20));
        startBox.add(new JLabel("YEAR"));
        startBox.add(Box.createHorizontalStrut(10));
        startBox.add(yearBox);
        startBox.add(Box.createHorizontalStrut(10));
        box.add(startBox);


        box.add(Box.createVerticalStrut(20));

        //adds labels and combo boxes into endBox, and add endBox into the box
        box.add(new Label("End time"));
        endBox.add(Box.createHorizontalStrut(10));
        endBox.add(new JLabel("DAY"));
        endBox.add(Box.createHorizontalStrut(10));
        endBox.add(dayBox1);
        endBox.add(Box.createHorizontalStrut(20));
        endBox.add(new JLabel("MONTH"));
        endBox.add(Box.createHorizontalStrut(10));
        endBox.add(monthBox1);
        endBox.add(Box.createHorizontalStrut(20));
        endBox.add(new JLabel("YEAR"));
        endBox.add(Box.createHorizontalStrut(10));
        endBox.add(yearBox1);
        endBox.add(Box.createHorizontalStrut(10));
        box.add(endBox);

        box.add(Box.createVerticalStrut(20));

        //button box to seat buttons
        Box buttonBox = new Box(BoxLayout.X_AXIS);

        //extracts this
        ModifyActivityFrame that = this;

        //temporary combobox with no sense
        JComboBox<Integer> ls = new JComboBox<>();
        ls.addItem(0);
        ls.setSelectedIndex(0);

        //displays different function according to different title
        if(title.equals("Add Activity")) {

            //creates add button and adds it to the box
            JButton addButton = new JButton("ADD");
            addButton.setBackground(Color.BLUE);
            addButton.setForeground(Color.WHITE);
            addButton.setOpaque(true); 
            addButton.setBorderPainted(false);
            buttonBox.add(addButton);

            //gives the add button a listener to handle the click event
            addButton.addActionListener(e -> {

                //user doesn't input the name or the description of the activity
                if(jTextArea.getText().length() == 0 || jTextField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(that, "Incompleted information is not allowed~");
                    return;
                }

                //if start time is later than end time
                if(boxToCanlander(yearBox, monthBox, dayBox, ls, ls).compareTo(
                    boxToCanlander(yearBox1, monthBox1, dayBox1, ls, ls)) >= 0) {
                    JOptionPane.showMessageDialog(that, "Start time should be earlier than end time~");
                    return;
                }

                //reads the original data from the file
                ArrayList<Activity> arrayList = new ArrayList<>();
                CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Activity.csv", Activity.class);
                Object[] activityList = csvProcessor.readData();

                //adds activity instances into the arrayList
                for(Object obj: activityList) arrayList.add((Activity) obj);

                //adds new activity instance
                arrayList.add(new Activity(
                    jTextField.getText(),
                    jTextArea.getText(),
                    that.boxToCanlander(yearBox, monthBox, dayBox, ls, ls),
                    that.boxToCanlander(yearBox1, monthBox1, dayBox1, ls, ls)
                ));

                //sorts all activity by start time in order
                Comparator<Activity> comparator = Comparator.comparing(Activity::getStartTime);
                arrayList.sort(comparator);

                //writes back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates to ActivityFrame
                that.dispose();
                new ActivityFrame();
            });
        } else {

            //creates two buttons and adds them to the box
            JButton modifyButton = new JButton("MODIFY");
            modifyButton.setBackground(Color.BLUE);
            modifyButton.setForeground(Color.WHITE);
            modifyButton.setOpaque(true); 
            modifyButton.setBorderPainted(false);
            buttonBox.add(modifyButton);
            buttonBox.add(Box.createHorizontalStrut(10));
            JButton deleteButton = new JButton("DELETE");
            deleteButton.setBackground(Color.BLUE);
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setOpaque(true); 
            deleteButton.setBorderPainted(false);
            buttonBox.add(deleteButton);

            //reads data from the file
            CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Activity.csv", Activity.class);
            Object[] activityList = csvProcessor.readData();

            //gets the current activity
            Activity currentActivity = (Activity) activityList[idx];

            //sets the content in jTextField and jTextArea according to the current activity
            jTextField.setText(currentActivity.getName());
            jTextArea.setText(currentActivity.getDescription());
            canlendarToBox(currentActivity.getStartTime(), yearBox, monthBox, dayBox, ls, ls);
            canlendarToBox(currentActivity.getEndTime(), yearBox1, monthBox1, dayBox1, ls, ls);

            //gives the modify button a listener to handle the click event
            modifyButton.addActionListener(e -> {

                //user doesn't input the name or the description of the activity
                if(jTextArea.getText().length() == 0 || jTextField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(that, "Incompleted information is not allowed~");
                    return;
                }

                //if start time is later than end time
                if(boxToCanlander(yearBox, monthBox, dayBox, ls, ls).compareTo(
                    boxToCanlander(yearBox1, monthBox1, dayBox1, ls, ls)) >= 0) {
                    JOptionPane.showMessageDialog(that, "Start time should be earlier than end time~");
                    return;
                }

                //pushes all activities into an arrayList
                ArrayList<Activity> arrayList = new ArrayList<>();
                for(Object obj: activityList) arrayList.add((Activity) obj);

                //modifies the specific activity
                arrayList.set(idx, new Activity(
                    jTextField.getText(),
                    jTextArea.getText(),
                    that.boxToCanlander(yearBox, monthBox, dayBox, ls, ls),
                    that.boxToCanlander(yearBox1, monthBox1, dayBox1, ls, ls)
                ));

                //sorts all activity by start time in order
                Comparator<Activity> comparator = Comparator.comparing(Activity::getStartTime);
                arrayList.sort(comparator);

                //writes back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates to ActivityFrame
                that.dispose();
                new ActivityFrame();
            });

            //gives the modify button a listener to handle the click event
            deleteButton.addActionListener(e -> {

                //pushes all activities except the specific activity into the arrayList
                ArrayList<Activity> arrayList = new ArrayList<>();
                for(int i = 0; i < activityList.length; i++)
                    if(i != idx) arrayList.add((Activity) activityList[i]);

                //writes back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates back to ActivityFrame
                that.dispose();
                new ActivityFrame();
            });
        }
        //adds buttonBox to the box
        box.add(buttonBox);

        //adds box to the frame
        this.add(box);

        //adds windowclose event and set some properties
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new ActivityFrame();
            }
        });
        setProperties(title);
    }
}
