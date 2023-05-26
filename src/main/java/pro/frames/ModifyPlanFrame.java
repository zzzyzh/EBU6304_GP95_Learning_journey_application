package pro.frames;

import pro.entities.Plan;
import pro.utils.CSVProcessor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * A class to display frame for user adding, modifying or deleting a plan
 * @author Zhonghao Yan
 */
public class ModifyPlanFrame extends BaseFrame{

    private final ArrayList<JComboBox<Integer>> yearComboBox;
    private final ArrayList<JComboBox<Integer>> monthComboBox;
    private final ArrayList<JComboBox<Integer>> dayComboBox;

    /**
     * Determines the range of data in the day drop-down box based on the user's choice of year and month.
     * 
     * @param idx distinguishes the start time and end time
     */
    private void updateDayComboBox(int idx) {

        //gets the current year and month selected
        Integer year = (Integer) yearComboBox.get(idx).getSelectedItem();
        Integer month = (Integer) monthComboBox.get(idx).getSelectedItem();

        //maximum days of a month
        int maxDays = 31;

        //30 days for these month
        if (month == 4 || month == 6 || month == 9 || month == 11) maxDays = 30;

        //leap year case
        else if (month == 2) {
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) maxDays = 29;
            else maxDays = 28;
        }

        //resets dayComboBox
        Integer[] dayOptions = new Integer[maxDays];
        for (int i = 0; i < maxDays; i++) dayOptions[i] = i + 1;
        dayComboBox.get(idx).setModel(new DefaultComboBoxModel<>(dayOptions));
    }

    /**
     * Constructs a frame for user to add, modify or delete a plan
     * 
     * @param title the title of the window
     * @param index the index of plan waiting to be changed
     */
    public ModifyPlanFrame(String title, int index) {

        //sets the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //adds a plan label to the frame
        JLabel addPlanLabel = new JLabel(title);
        addPlanLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addPlanLabel.setForeground(Color.BLUE);
        addPlanLabel.setAlignmentX(CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(addPlanLabel, gbc);

        //adds plan description label
        JLabel planDescriptionLabel = new JLabel("Plan Description:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(planDescriptionLabel, gbc);

        //adds plan description text area
        JTextArea planDescriptionTextArea = new JTextArea(5, 20);
        JScrollPane planDescriptionScrollPane = new JScrollPane(planDescriptionTextArea);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(planDescriptionScrollPane, gbc);

        //adds time item labels
        gbc.gridy = 3;
        gbc.gridx = 3;
        add(new JLabel("Year"), gbc);

        gbc.gridx = 2;
        add(new JLabel("Month"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Day"), gbc);

        gbc.gridx = 4;
        add(new JLabel("Hour"), gbc);

        gbc.gridx = 5;
        add(new JLabel("Minute"), gbc);
    

        //adds start time label
        JLabel startTimeLabel = new JLabel("Start Time:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(startTimeLabel, gbc);

        //year comboBox of start time
        yearComboBox = new ArrayList<>();
        yearComboBox.add(new JComboBox<>(new Integer[] { 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029 }));
        yearComboBox.get(0).addItemListener(e -> updateDayComboBox(0));
        gbc.gridx = 3;
        add(yearComboBox.get(0), gbc);

        //month comboBox of start time
        monthComboBox = new ArrayList<>();
        monthComboBox.add(new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }));
        monthComboBox.get(0).addItemListener(e -> updateDayComboBox(0));
        gbc.gridx = 2;
        add(monthComboBox.get(0), gbc);

        //day comboBox of start time
        dayComboBox = new ArrayList<>();
        dayComboBox.add(new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 }));
        gbc.gridx = 1;
        add(dayComboBox.get(0), gbc);

        //hour comboBox of start time
        JComboBox<Integer> hourComboBox = new JComboBox<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24});
        gbc.gridx = 4;
        add(hourComboBox, gbc);

        //minute comboBox of start time
        JComboBox<Integer> minuteComboBox = new JComboBox<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
                40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59});
        gbc.gridx = 5;
        add(minuteComboBox, gbc);

        //adds end time label
        JLabel endTimeLabel = new JLabel("End Time:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        add(endTimeLabel, gbc);

        //year comboBox of end time
        yearComboBox.add(new JComboBox<>(new Integer[] { 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029 }));
        yearComboBox.get(1).addItemListener(e -> updateDayComboBox(1));
        gbc.gridx = 3;
        add(yearComboBox.get(1), gbc);

        //month comboBox of end time
        monthComboBox.add(new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }));
        monthComboBox.get(1).addItemListener(e -> updateDayComboBox(1));
        gbc.gridx = 2;
        add(monthComboBox.get(1), gbc);

        //day comboBox of end time
        dayComboBox.add(new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 }));
        gbc.gridx = 1;
        add(dayComboBox.get(1), gbc);

        //hour comboBox of end time
        JComboBox<Integer> hourComboBox1 = new JComboBox<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22});
        gbc.gridx = 4;
        add(hourComboBox1, gbc);

        //minute comboBox of end time
        JComboBox<Integer> minuteComboBox1 = new JComboBox<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
                40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57});
        gbc.gridx = 5;
        add(minuteComboBox1, gbc);

        //extracts this
        ModifyPlanFrame that = this;

        //judges the function of the window
        if(title.equals("Add Plan")) {

            //creates add button and adds it to the frame
            JButton addButton = new JButton("ADD");
            addButton.setBackground(Color.BLUE);
            addButton.setForeground(Color.WHITE);
            addButton.setOpaque(true); 
            addButton.setBorderPainted(false);
            gbc.gridy = 6;
            gbc.gridx = 2;
            add(addButton, gbc); 

            //gives the add button a listener to handle the click event
            addButton.addActionListener(e -> {

                //if user doesn't input the description of the plan
                if(planDescriptionTextArea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(that, "Incompleted information is not allowed~");
                    return;
                }

                //if start time is later than end time
                if(boxToCanlander(yearComboBox.get(0), monthComboBox.get(0), dayComboBox.get(0), hourComboBox, minuteComboBox).compareTo(
                    boxToCanlander(yearComboBox.get(1), monthComboBox.get(1), dayComboBox.get(1), hourComboBox1, minuteComboBox1)) >= 0) {
                    JOptionPane.showMessageDialog(that, "Start time should be earlier than end time~");
                    return;
                }

                //reads data from file
                ArrayList<Plan> arrayList = new ArrayList<>();
                CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Plan.csv", Plan.class);
                Object[] planList = csvProcessor.readData();

                //pushes all plans into an arrayList
                for(Object obj: planList)
                    arrayList.add((Plan) obj);

                //adds new plan to the list
                arrayList.add(new Plan(planDescriptionTextArea.getText(),
                    that.boxToCanlander(yearComboBox.get(0), monthComboBox.get(0), dayComboBox.get(0), hourComboBox, minuteComboBox),
                    that.boxToCanlander(yearComboBox.get(1), monthComboBox.get(1), dayComboBox.get(1), hourComboBox1, minuteComboBox1)
                ));

                //writes back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates to PlanFrame
                that.dispose();
                new PlanFrame();
            });
        } else {

            //reads data from file
            CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Plan.csv", Plan.class);
            Object[] planList = csvProcessor.readData();

            //gets the current plan and set the value of fields and areas
            Plan currentPlan = (Plan) planList[index];
            planDescriptionTextArea.setText(currentPlan.getDescription());
            canlendarToBox(currentPlan.getStartTime(), yearComboBox.get(0), monthComboBox.get(0), dayComboBox.get(0), hourComboBox, minuteComboBox);
            canlendarToBox(currentPlan.getEndTime(), yearComboBox.get(1), monthComboBox.get(1), dayComboBox.get(1), hourComboBox1, minuteComboBox1);

            //creates modify and delete button, and adds them to frame
            JButton modifyButton = new JButton("MODIFY");
            modifyButton.setBackground(Color.BLUE);
            modifyButton.setForeground(Color.WHITE);
            modifyButton.setOpaque(true); 
            modifyButton.setBorderPainted(false);
            gbc.gridy = 6;
            gbc.gridx = 1;
            add(modifyButton, gbc);
            JButton deleteButton = new JButton("DELETE");
            deleteButton.setBackground(Color.BLUE);
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setOpaque(true);
            deleteButton.setBorderPainted(false);
            gbc.gridy = 6;
            gbc.gridx = 3;
            add(deleteButton, gbc);


            //gives the modify button a listener to handle the click event
            modifyButton.addActionListener(e -> {

                //if user doesn't input the description of the plan
                if(planDescriptionTextArea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(that, "Incompleted information is not allowed~");
                    return;
                }
                //if start time is later than end time
                if(boxToCanlander(yearComboBox.get(0), monthComboBox.get(0), dayComboBox.get(0), hourComboBox, minuteComboBox).compareTo(
                    boxToCanlander(yearComboBox.get(1), monthComboBox.get(1), dayComboBox.get(1), hourComboBox1, minuteComboBox1)) >= 0) {
                    JOptionPane.showMessageDialog(that, "Start time should be earlier than end time~");
                    return;

                }

                //directly changes the specific plan
                planList[index] = new Plan(planDescriptionTextArea.getText(),
                    that.boxToCanlander(yearComboBox.get(0), monthComboBox.get(0), dayComboBox.get(0), hourComboBox, minuteComboBox),
                    that.boxToCanlander(yearComboBox.get(1), monthComboBox.get(1), dayComboBox.get(1), hourComboBox1, minuteComboBox1)
                );

                //writes back to file
                csvProcessor.writeData(planList);

                //navigates to PlanFrame
                that.dispose();
                new PlanFrame();
            });

            //gives the delete button a listener to handle the click event
            deleteButton.addActionListener(e -> {

                //pushed all plans into an arrayList except current plan
                ArrayList<Plan> arrayList = new ArrayList<>();
                for(int i = 0; i < planList.length; i++)
                    if(i != index) arrayList.add((Plan) planList[i]);

                //write back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates to PlanFrame
                that.dispose();
                new PlanFrame();
            });
        }

        //sets properties
        setProperties(title);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new PlanFrame();
            }
        });
    }
}
