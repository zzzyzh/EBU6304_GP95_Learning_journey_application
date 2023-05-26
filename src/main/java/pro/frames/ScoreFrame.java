package pro.frames;

import pro.entities.Grades;
import pro.utils.CSVProcessor;
import pro.utils.FileProcessor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.*;

/**
 * A class to display score information of the user
 * @author Yixuan Gou
 */
public class ScoreFrame extends BaseFrame {

    /**
     * constructs a frame to show detailed information of user's grades
     */
    public ScoreFrame() {

        //defines some components
        JLabel titleLabel, gpaLabel, weightedAverageScoreLabel, rankLabel;
        JButton modifyButton, addButton;
        JTable table;
        JScrollPane scrollPane;

        //reads data
        CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Grades.csv", Grades.class);
        FileProcessor fileProcesser = new FileProcessor("DataFiles/Grades.txt");
        Object[] gradesList = csvProcessor.readData();
        Object[] gradeInfo = fileProcesser.readData();
        
        //creates table
        String[] columnNames = {"Course Name", "Term", "Grade", "Credit", "GPA", "Rank"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        //calculates and sets average weighted score and GPA
        double totWeightScore = 0;
        double totCredit = 0;
        double totGPAScore = 0;
        for(Object obj: gradesList) {
            Grades grade = (Grades) obj;
            model.addRow(new Object[] {grade.getName(), grade.getTerm(), grade.getGrade(), grade.getCredit(), grade.getGpa(), grade.getRank()});
            double gpa = grade.getGpa();
            totCredit += grade.getCredit();
            totWeightScore += grade.getCredit() * grade.getGrade();
            totGPAScore += grade.getCredit() * gpa;
        }

        // creates labels
        titleLabel = new JLabel("My grades");
        titleLabel.setForeground(Color.BLUE);
        gpaLabel = new JLabel("GPA: " + new DecimalFormat("#.00").format(totGPAScore / totCredit));
        weightedAverageScoreLabel = new JLabel("Weighted average score: " + new DecimalFormat("#.00").format(totWeightScore / totCredit));
        rankLabel = new JLabel("Rank: " + gradeInfo[0].toString() + " / " + gradeInfo[1].toString());

        // creates buttons
        modifyButton = new JButton("Modify");
        modifyButton.setBackground(Color.BLUE);
        modifyButton.setForeground(Color.WHITE);
        modifyButton.setOpaque(true); 
        modifyButton.setBorderPainted(false);
        addButton = new JButton("ADD+");
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true); 
        addButton.setBorderPainted(false);

        //extracts this
        ScoreFrame that = this;

        //navigates to ModifyScoreFrame
        addButton.addActionListener(e -> {
            that.dispose();
            new ModifyScoreFrame("Add Course", 0);
        });
        
        //adds listener for modifyButton
        modifyButton.addActionListener(e -> {

            //creates textField
            JTextField rankTextField = new JTextField(gradeInfo[0].toString(), 5);
            JTextField totRankTextField = new JTextField(gradeInfo[1].toString(), 5);

            //creates the dialog
            JPanel panel = new JPanel();
            GridBagConstraints gbc = new GridBagConstraints();
            panel.add(new JLabel("Rank:   "), gbc);
            panel.add(rankTextField);
            panel.add(new JLabel("  /  "));
            panel.add(totRankTextField);

            //sets the content of the button in the dialog
            UIManager.put("OptionPane.okButtonText", "confirm");
            UIManager.put("OptionPane.cancelButtonText", "cancel");

            //gets the result from the dialog
            int result = JOptionPane.showConfirmDialog(null, panel, "Modify", JOptionPane.OK_CANCEL_OPTION);

            //if user clicks OK
            if (result == JOptionPane.OK_OPTION) {

                //judges if input is legal or not
                try {
                    Integer.parseInt(rankTextField.getText());
                    Integer.parseInt(totRankTextField.getText());
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(that, "Rank must be an Integer~");
                    return;
                }

                //if current rank is more than the total number of students
                if(Integer.parseInt(rankTextField.getText()) > Integer.parseInt(totRankTextField.getText())) {
                    JOptionPane.showMessageDialog(that, "Rank can't exceed total number~");
                    return;
                }

                //changes the label
                rankLabel.setText("Rank: " + rankTextField.getText() + " / " + totRankTextField.getText());

                //writes back to file
                fileProcesser.writeData(new String[] {rankTextField.getText(), totRankTextField.getText()});

                //changes the data in array
                gradeInfo[0] = rankTextField.getText();
                gradeInfo[1] = totRankTextField.getText();
            }
        });

        //creates table 
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        scrollPane = new JScrollPane(table);

        //makes table sortable by score or credit
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.addMouseListener(new MouseAdapter() {
            public void mouseReleased (MouseEvent e) { 
                if (e.getClickCount() == 1) {

                    //get the clicked column
                    int pick = jTableHeader.columnAtPoint(e.getPoint()); 
                    if (pick != 2 && pick != 3) return;

                    //get the other sortable column
                    int theOther = pick == 2? 3 : 2;

                    //reads data from file
                    Object[] objectList = csvProcessor.readData();
                    ArrayList<Grades> grades = new ArrayList<>();
                    for(Object obj: objectList) grades.add((Grades) obj);

                    //saves the sorted data
                    Object[][] dataList = new Object[grades.size()][6];

                    //replace the content of arrow
                    if (columnNames[pick].charAt(columnNames[pick].length() - 1) == '↑')
                        columnNames[pick] = columnNames[pick].replace('↑', '↓');
                    else if (columnNames[pick].charAt(columnNames[pick].length() - 1) == '↓')
                        columnNames[pick] = columnNames[pick].substring(0, columnNames[pick].length() - 1);
                    else columnNames[pick] += "↑";
                    if(columnNames[theOther].endsWith("↑") || columnNames[theOther].endsWith("↓"))
                        columnNames[theOther] = columnNames[theOther].substring(0, columnNames[theOther].length() - 1);
                    if(columnNames[pick].endsWith("↑") || columnNames[pick].endsWith("↓")) {

                        //sets the rule of sort according to pick and arrow
                        grades.sort((a, b) -> {
                            if (columnNames[pick].endsWith("↑")) {
                                if (pick == 2) return a.getGrade() < b.getGrade() ? 1 : -1;
                                else return a.getCredit() < b.getCredit() ? 1 : -1;
                            } else {
                                if (pick == 2) return a.getGrade() > b.getGrade() ? 1 : -1;
                                else return a.getCredit() > b.getCredit() ? 1 : -1;
                            }
                        });
                    }
                    //resets the data
                    for(int i = 0; i < grades.size(); i++) {
                        dataList[i][0] = grades.get(i).getName();
                        dataList[i][1] = grades.get(i).getTerm();
                        dataList[i][2] = grades.get(i).getGrade();
                        dataList[i][3] = grades.get(i).getCredit();
                        dataList[i][4] = grades.get(i).getGpa();
                        dataList[i][5] = grades.get(i).getRank();
                    }

                    //applies the change
                    model.setDataVector(dataList, columnNames);
                }
            }
        });

        //adds double click event listener
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    String name = table.getValueAt(row, 0).toString();

                    //gets the correct course to modify
                    for(int i = 0; i < gradesList.length; i++) {
                        Grades grade = (Grades) gradesList[i];
                        if(grade.equals(new Grades(name, 1, 1, 1, 1))) {
                            that.dispose();
                            new ModifyScoreFrame("Modify Course", i);
                            break;
                        }
                    }
                }
            }
        });
        //sets layout and add components
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);

        //adds components to frame
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.WEST;
        add(titleLabel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(gpaLabel, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(weightedAverageScoreLabel, gc);


        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.WEST;
        add(rankLabel, gc);
        gc.gridx = 1;
        add(modifyButton, gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.EAST;
        add(addButton, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 3;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1;
        gc.weighty = 1;
        add(scrollPane, gc);

        //sets properties
        setBackToHome();
        setProperties("My Grades");
    }
}
