package pro.frames;

import pro.entities.Grades;
import pro.utils.CSVProcessor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * A class to display a frame for users to add, modify or delete a course with mark, rank, name, gpa, credit and term
 * @author Yixuan Gou
 */
public class ModifyScoreFrame extends BaseFrame {

    /**
     * Constructs a frame for users to add, modify or delete a course with mark, rank, name, gpa, credit and term
     * 
     * @param title     title of the frame
     * @param index     index of the course waiting to be modified or deleted
     */
    public ModifyScoreFrame(String title, int index) {

        //creates the title label
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        //creates the box to seat the title label
        Box titleBox = new Box(BoxLayout.X_AXIS);
        titleBox.add(Box.createHorizontalGlue());
        titleBox.add(titleLabel);
        titleBox.add(Box.createHorizontalGlue());
        titleBox.setAlignmentX(CENTER_ALIGNMENT);


        //creates box to seat all components
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalStrut(20));
        box.add(titleBox);
        box.add(Box.createVerticalStrut(20));

        //creates labels for all fields
        JLabel labelName = new JLabel("Course Name");
        JLabel labelTerm = new JLabel("Term");
        JLabel labelCredit = new JLabel("Credit");
        JLabel labelScore = new JLabel("Score");
        JLabel labelRank = new JLabel("Rank");

        //creates boxes for components in a row
        Box box10 = new Box(BoxLayout.X_AXIS);
        Box box11 = new Box(BoxLayout.X_AXIS);
        Box box12 = new Box(BoxLayout.X_AXIS);

        //creates boxes for each field
        Box box1 = new Box(BoxLayout.Y_AXIS);
        Box box2 = new Box(BoxLayout.Y_AXIS);
        Box box3 = new Box(BoxLayout.Y_AXIS);
        Box box4 = new Box(BoxLayout.Y_AXIS);
        Box box5 = new Box(BoxLayout.Y_AXIS);

        //creates textField for each field
        JTextField nameField = new JTextField();
        JTextField termField = new JTextField();
        JTextField creditField = new JTextField();
        JTextField scoreField = new JTextField();
        JTextField rankField = new JTextField();

        //adds label and field to the vertical box
        box1.add(labelName);
        box1.add(nameField);

        //adds components to the horizontal box
        box10.add(Box.createHorizontalStrut(20));
        box10.add(box1);
        box10.add(Box.createHorizontalStrut(20));

        //add label and field to the vertical box
        box2.add(labelScore);
        box2.add(scoreField);

        //adds label and field to the vertical box
        box3.add(labelRank);
        box3.add(rankField);

        //adds components to the horizontal box
        box11.add(Box.createHorizontalStrut(20));
        box11.add(box2);
        box11.add(Box.createHorizontalStrut(20));
        box11.add(box3);
        box11.add(Box.createHorizontalStrut(20));

        //adds label and field to the vertical box
        box4.add(labelCredit);
        box4.add(creditField);

        //adds label and field to the vertical box
        box5.add(labelTerm);
        box5.add(termField);

        //adds components to the horizontal box
        box12.add(Box.createHorizontalStrut(20));
        box12.add(box4);
        box12.add(Box.createHorizontalStrut(20));
        box12.add(box5);
        box12.add(Box.createHorizontalStrut(20));

        //adds components to the main box
        box.add(Box.createVerticalStrut(20));
        box.add(box10);
        box.add(Box.createVerticalStrut(20));
        box.add(box11);
        box.add(Box.createVerticalStrut(20));
        box.add(box12);
        box.add(Box.createVerticalStrut(20));
        box.add(Box.createHorizontalStrut(400));

        //creates button box to seat buttons
        Box buttonBox = new Box(BoxLayout.X_AXIS);

        //extracts this
        ModifyScoreFrame that = this;

        //judges the title to distinguish different function
        if(title.equals("Add Course")) {

            //creates add button
            JButton addButton = new JButton("ADD");
            addButton.setBackground(Color.BLUE);
            addButton.setForeground(Color.WHITE);
            addButton.setOpaque(true); 
            addButton.setBorderPainted(false);
            buttonBox.add(addButton);

            //gives the add button a listener to handle the click event
            addButton.addActionListener(e -> {

                //if user doesn't input the name of the course
                if(nameField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(that, "Incompleted course name is not allowed~");
                    return;
                }

                //judges if current course has illegal fields
                Grades current;
                try {
                    current = new Grades(
                        nameField.getText() == null? "" : nameField.getText(),
                        termField.getText() == null? -1 : Integer.parseInt(termField.getText()),
                        scoreField.getText() == null? -1 : Double.parseDouble(scoreField.getText()),
                        creditField.getText() == null? -1 : Double.parseDouble(creditField.getText()),
                        rankField.getText() == null? -1 : Integer.parseInt(rankField.getText())
                    );
                }
                catch (Exception err) {
                    JOptionPane.showMessageDialog(that, "Illegal content occurred!");
                    return;
                }

                //reads data from file
                ArrayList<Grades> arrayList = new ArrayList<>();
                CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Grades.csv", Grades.class);
                Object[] gradesList = csvProcessor.readData();

                //pushes all courses into an arrayList
                for(Object obj: gradesList) arrayList.add((Grades) obj);

                //judges if current course is already exist
                for(int i = 0; i < arrayList.size(); i++) if(arrayList.get(i).getName().equals(current.getName())) {
                    JOptionPane.showMessageDialog(that, "Course already exist!");
                    return;
                }

                //adds current course into the arrayList
                arrayList.add(current);

                //writes back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates to ScoreFrame
                that.dispose();
                new ScoreFrame();
            });
        } else {

            //reads data from file
            CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Grades.csv", Grades.class);
            Object[] gradesList = csvProcessor.readData();

            //gets the target course and set the fields
            Grades currentGrade = (Grades) gradesList[index];
            nameField.setText(currentGrade.getName());
            creditField.setText(String.valueOf(currentGrade.getCredit()));
            rankField.setText(String.valueOf(currentGrade.getRank()));
            scoreField.setText(String.valueOf(currentGrade.getGrade()));
            termField.setText(String.valueOf(currentGrade.getTerm()));

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
            buttonBox.add(modifyButton);
            buttonBox.add(Box.createHorizontalStrut(30));
            buttonBox.add(deleteButton);

            //gives the modify button a listener to handle the click event
            modifyButton.addActionListener(e -> {

                //if user doesn't input the name of the course
                if(nameField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(that, "Incompleted course name is not allowed~");
                    return;
                }

                //judges if there exists illegal fileds
                Grades current;
                try {
                    current = new Grades(
                        nameField.getText() == null? "" : nameField.getText(),
                        termField.getText() == null? -1 : Integer.parseInt(termField.getText()),
                        scoreField.getText() == null? -1 : Double.parseDouble(scoreField.getText()),
                        creditField.getText() == null? -1 : Double.parseDouble(creditField.getText()),
                        rankField.getText() == null? -1 : Integer.parseInt(rankField.getText())
                    );
                }
                catch (Exception err) {
                    JOptionPane.showMessageDialog(that, "Illegal content occurred!");
                    return;
                }

                //term should in [1, 8]
                if(Integer.parseInt(termField.getText()) < 1 || Integer.parseInt(termField.getText()) > 8) {
                    JOptionPane.showMessageDialog(that, "Term should in [1, 8]!");
                    return;
                }

                //create an arrayList to save all grades
                ArrayList<Grades> arrayList = new ArrayList<>();

                //pushes all courses into an arrayList
                for(Object obj: gradesList) arrayList.add((Grades) obj);

                //judges if current course is already exist
                for(int i = 0; i < arrayList.size(); i++)
                    if(i != index && arrayList.get(i).getName().equals(current.getName())) {
                        JOptionPane.showMessageDialog(that, "Course already exist!");
                        return;
                }

                //changes the target course
                arrayList.set(index, current);

                //writes back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates to ScoreFrame
                that.dispose();
                new ScoreFrame();
            });

            //gives the delete button a listener to handle the click event
            deleteButton.addActionListener(e -> {

                //pushes all courses except the target to the arrayList
                ArrayList<Grades> arrayList = new ArrayList<>();
                for(int i = 0; i < gradesList.length; i++)
                    if(i != index) arrayList.add((Grades) gradesList[i]);

                //writes back to file
                csvProcessor.writeData(arrayList.toArray());

                //navigates to ScoreFrame
                that.dispose();
                new ScoreFrame();
            });
        }

        //adds the button box to the main box
        box.add(Box.createVerticalStrut(20));
        box.add(buttonBox);
        box.add(Box.createVerticalStrut(20));

        //adds the box to this frame
        this.add(box);

        //sets some properties
        setProperties(title);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new ScoreFrame();
            }
        });
    }
}
