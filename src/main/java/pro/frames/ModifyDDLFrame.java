package pro.frames;

import pro.entities.DDL;
import pro.utils.CSVProcessor;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.*;

/**
 * A class to display a frame to help student add tasks with deadline
 * @author Bingyao Li
 */
public class ModifyDDLFrame extends BaseFrame{

    /**
     * Constructs a frame for user to add, modify or delete a task with deadline
     * 
     * @param title     title of the frame
     * @param index     index of current task
     */
    public ModifyDDLFrame(String title, int index) {
        
        // creates a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // adds title to panel
        JLabel label1 = new JLabel("Add a deadline");
        if(!title.equals("Add DDL")) label1.setText("Modify deadline");
        label1.setForeground(Color.BLUE);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label1);

        // adds task title to panel
        JLabel label2 = new JLabel("Task Title");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setForeground(Color.BLACK);
        panel.add(label2);

        //adds task content to panel
        JTextField textField1 = new JTextField(20);
        panel.add(textField1);
        JLabel label3 = new JLabel("Task Content");
        label3.setForeground(Color.BLACK);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label3);
        JTextArea textArea = new JTextArea(5, 1);
        panel.add(textArea);

        // adds deadline time label to panel
        JLabel label4 = new JLabel("DEADLINE");
        label4.setForeground(Color.BLACK);
        label4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label4);

        // adds a panel to hold the date
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridLayout(2, 5, 5, 5));

        //day combobox
        JComboBox<Integer> dayBox = new JComboBox<>();
        for (int j = 1; j <= 31; j++) {
            dayBox.addItem(j);
        }
        datePanel.add(dayBox);

        //year combobox
        JComboBox<Integer> yearBox = new JComboBox<>();
        for (int j = 2000; j <= 2030; j++) {
            yearBox.addItem(j);
        }

        //month combobox
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
        //hour combobox
        JComboBox<Integer> hourBox = new JComboBox<>();
        for (int j = 0; j <= 23; j++) {
            hourBox.addItem(j);
        }
        
        //minute combobox
        JComboBox<Integer> minutesBox = new JComboBox<>();
        for (int j = 0; j <= 59; j++) {
            minutesBox.addItem(j);
        }

        //adds comboBoxes to the panel
        datePanel.add(monthBox);
        datePanel.add(yearBox);
        datePanel.add(hourBox);
        datePanel.add(minutesBox);
        
        
        //inserts head of table
        String[] labels = {"DAY", "MONTH", "YEAR", "HOUR", "MINUTE"};
        for (String label : labels) {
            JLabel dateLabel = new JLabel(label);
            dateLabel.setHorizontalAlignment(JLabel.LEFT);
            datePanel.add(dateLabel);
        }
        panel.add(datePanel);

        //judges the function of the window
        JPanel buttonPanel = new JPanel(new BorderLayout());
        ModifyDDLFrame that = this;
        if(title.equals("Add DDL")) {
            
            //creates add button
            JButton addButton = new JButton("ADD");
            addButton.setBackground(Color.BLUE);
            addButton.setForeground(Color.WHITE);
            addButton.setOpaque(true); 
            addButton.setBorderPainted(false);
            buttonPanel.add(addButton, BorderLayout.CENTER);

            //gives the add button a listener to handle the click event
            addButton.addActionListener(e -> {

                //if user doesn't input task title or task description
                if(textField1.getText().length() == 0 || textArea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(that, "Incompleted information is not allowed~");
                    return;
                }

                //reads data from file
                ArrayList<DDL> arrayList = new ArrayList<>();
                CSVProcessor csvProcessor = new CSVProcessor("DataFiles/DDL.csv", DDL.class);
                Object[] ddlList = csvProcessor.readData();

                //pushes all tasks into arrayList
                for(Object obj: ddlList)
                    arrayList.add((DDL) obj);

                //adds new task into the arrayList
                arrayList.add(new DDL(textField1.getText(), textArea.getText(), that.boxToCanlander(yearBox, monthBox, dayBox, hourBox, minutesBox)));

                //sorts all tasks by due time
                Comparator<DDL> comparator = Comparator.comparing(DDL::getDueTime);
                arrayList.sort(comparator);

                //writes back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates to DDLFrame
                that.dispose();
                new DDLFrame();
            });
        } else {

            //reads data from file
            CSVProcessor csvProcessor = new CSVProcessor("DataFiles/DDL.csv", DDL.class);
            Object[] ddlList = csvProcessor.readData();
            DDL currentDDL = (DDL) ddlList[index];
            ArrayList<DDL> arrayList = new ArrayList<>();

            //pushes all tasks into arrayList
            for (Object o : ddlList) arrayList.add((DDL) o);

            //sets textField and textArea according to current task
            textField1.setText(currentDDL.getName());
            textArea.setText(currentDDL.getDescription());
            canlendarToBox(currentDDL.getDueTime(), yearBox, monthBox, dayBox, hourBox, minutesBox);

            //creates modify and delete button
            JButton modifyButton = new JButton("MODIFY");
            modifyButton.setBackground(Color.BLUE);
            modifyButton.setForeground(Color.WHITE);
            modifyButton.setOpaque(true); 
            modifyButton.setBorderPainted(false);
            
            JButton deleteButton = new JButton("DELETE");
            deleteButton.setBackground(Color.BLUE);
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setOpaque(true); 
            deleteButton.setBorderPainted(false);

        
            //adds buttons to the frame
            buttonPanel.add(modifyButton, BorderLayout.LINE_START);
            buttonPanel.add(deleteButton, BorderLayout.LINE_END);
            
            //gives the modify button a listener to handle the click event
            modifyButton.addActionListener(e -> {

                //if user doesn't input task title or task description
                if(textField1.getText().length() == 0 || textArea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(that, "Incompleted information is not allowed~");
                    return;
                }

                //modifies the specific task
                arrayList.set(index, new DDL(textField1.getText(), textArea.getText(), that.boxToCanlander(yearBox, monthBox, dayBox, hourBox, minutesBox)));

                //writes back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates back to DDLFrame
                that.dispose();
                new DDLFrame();
            });

            //gives the delete button a listener to handle the click event
            deleteButton.addActionListener(e -> {

                //removes the specific task
                arrayList.remove(index);

                //writes back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates to DDLFrame
                that.dispose();
                new DDLFrame();
            });

        }

        //adds button panel to the main panel
        panel.add(buttonPanel);

        //adds the main panel to this frame
        add(panel);

        //set properties
        setProperties(title);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new DDLFrame();
            }
        });
    }
}
