package pro.frames;

import pro.entities.Grades;
import pro.utils.CSVProcessor;
import pro.utils.FileProcessor;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A class to display a frame to show user's term average score
 * @author Yixuan Gou
 */
public class TermAverageFrame extends BaseFrame{

    /**
     * Constructs a frame to show user's term average score
     */
    public TermAverageFrame() {

        //reads data from file
        FileProcessor fileProcessor = new FileProcessor("DataFiles/TermRank.txt");
        Object[] objects = fileProcessor.readData();

        //creates title label
        JLabel titleLabel = new JLabel("Term Average Score");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        //creates box to seat title label
        Box titleBox = new Box(BoxLayout.X_AXIS);
        titleBox.add(Box.createHorizontalGlue());
        titleBox.add(titleLabel);
        titleBox.add(Box.createHorizontalGlue());
        titleBox.setAlignmentX(CENTER_ALIGNMENT);
        
        //creates the main box
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalStrut(20));
        box.add(titleBox);
        box.add(Box.createVerticalStrut(20));

        //creates vertical and horizontal boxes to seat components
        Box[] vBox = new Box[8];
        Box[] hBox = new Box[4];

        //creates 8 fields to show the value
        JTextField[] field = new JTextField[8];

        for(int i = 0; i < 8; i++) {

            //adds label and field to each box
            vBox[i] = new Box(BoxLayout.Y_AXIS);
            JLabel label = new JLabel("Term" + (i + 1));
            field[i] = new JTextField();
            if(objects[i].toString().equals("0.0")) field[i].setText("0");
            else field[i].setText(new DecimalFormat("#.00").format(Double.parseDouble((String) objects[i])));
            field[i].setEditable(false);
            vBox[i].add(label);
            vBox[i].add(field[i]);
        }
        for(int i = 0; i < 4; i++) {

            //two vertical boxes in one horizontal box
            hBox[i] = new Box(BoxLayout.X_AXIS);
            hBox[i].add(Box.createHorizontalStrut(20));
            hBox[i].add(vBox[i * 2]);
            hBox[i].add(Box.createHorizontalStrut(20));
            hBox[i].add(vBox[i * 2 + 1]);
            hBox[i].add(Box.createHorizontalStrut(20));

            //adds hBox to main box
            box.add(Box.createVerticalStrut(20));
            box.add(hBox[i]);
            box.add(Box.createVerticalStrut(20));
        }
        box.add(Box.createVerticalStrut(20));


        //creates horizontal box to seat buttons
        Box buttonBox = new Box(BoxLayout.X_AXIS);

        //creates modify button and autofill button
        JButton modifyButton = new JButton("MODIFY");
        JButton autoFillButton = new JButton("AUTO-FILL");
        modifyButton.setBackground(Color.BLUE);
        modifyButton.setForeground(Color.WHITE);
        modifyButton.setOpaque(true); 
        modifyButton.setBorderPainted(false);
        autoFillButton.setBackground(Color.BLUE);
        autoFillButton.setForeground(Color.WHITE);
        autoFillButton.setOpaque(true);
        autoFillButton.setBorderPainted(false);
        buttonBox.add(modifyButton);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(autoFillButton);
        box.add(buttonBox);
        box.add(Box.createVerticalStrut(20));

        //sets the width of box
        box.add(Box.createHorizontalStrut(400));

        //adds the main box to the frame
        this.add(box);

        //extracts this
        TermAverageFrame that = this;

        //gives the modify button a listener to handle the click event
        modifyButton.addActionListener(e -> {

            //creates textField
            JTextField[] modifyField = new JTextField[8];

            //creates a panel to display for user to modify data
            JPanel panel = new JPanel(new GridLayout(0, 1));
            for(int i = 0; i < 8; i++) {
                modifyField[i] = new JTextField(field[i].getText());
                panel.add(new JLabel("Term " + (i + 1)));
                panel.add(modifyField[i]);
            }

            //sets the content of the button in the dialog
            UIManager.put("OptionPane.okButtonText", "confirm");
            UIManager.put("OptionPane.cancelButtonText", "cancel");

            //creates new frame and gets the results
            int result = JOptionPane.showConfirmDialog(null, panel, "Modify", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {

                //judges if the input value is legal
                try {
                    for(int i = 0; i < 8; i++) {

                        //0 as a default value
                        if(modifyField[i].getText().length() == 0) modifyField[i].setText("0");
                        Double.parseDouble(modifyField[i].getText());
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(that, "Only number is allowed");
                    return;
                }

                //change the text
                String[] data = new String[8];
                for(int i = 0; i < 8; i++) {
                    field[i].setText(modifyField[i].getText());
                    data[i] = field[i].getText();
                }

                //write back to file
                fileProcessor.writeData(data);
            }
        });

        //gives the autofill button a listener to handle the click event
        autoFillButton.addActionListener(e -> {

            //reads data from grades.csv
            CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Grades.csv", Grades.class);
            Object[] object = csvProcessor.readData();
            ArrayList<Grades> arrayList = new ArrayList<>();
            for(Object obj: object) arrayList.add((Grades) obj);

            //saves weighted average score for each term
            double[] data = new double[8];

            //saves total credits for each terms
            double[] credit = new double[8];

            //0 for default value
            for(int i = 0; i < 8; i++) data[i] = credit[i] = 0;

            //calculates weighted average score
            for (Grades grades : arrayList) {
                data[grades.getTerm() - 1] += grades.getCredit() * grades.getGrade();
                credit[grades.getTerm() - 1] += grades.getCredit();
            }
            for(int i = 0; i < 8; i++) {
                if (credit[i] != 0) {
                    data[i] /= credit[i];
                    field[i].setText(new DecimalFormat("#.00").format(data[i]));
                } else field[i].setText("0");
            }

            //saves the data waiting to be written back to file
            String[] fileData = new String[8];

            //2 decimal places for each value
            for(int i = 0; i < 8; i++) fileData[i] = String.valueOf(data[i]);

            //write back to file
            fileProcessor.writeData(fileData);
        });

        //set properties
        setBackToHome();
        setProperties(titleLabel.getText());
    }
}
