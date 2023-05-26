package pro.frames;

import pro.entities.DDL;
import pro.utils.CSVProcessor;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.*;

/**
 * A class to display the tasks set by the user
 * @author Bingyao Li
 */
public class DDLFrame extends BaseFrame{
    
    /**
     * Constructs a frame to show tasks
     */
    public DDLFrame() {

        //creates a panel to seat the title and add button
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(getWidth(), 50));

        //creates a title label and adds it to the panel
        JLabel titleLabel = new JLabel("My DDL                    ");
        Font titleFont = new Font("Arial", Font.BOLD, 20);
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);

        //creates a button and adds it to the panel
        JButton addButton = new JButton("ADD+");
        addButton.setFont(new Font("Arial", Font.PLAIN, 12));
        addButton.setPreferredSize(new Dimension(80, 30));
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true); 
        addButton.setBorderPainted(false);
        titlePanel.add(addButton);

        //gives the button a click event
        DDLFrame that = this;
        addButton.addActionListener(e -> {
            that.dispose();
            new ModifyDDLFrame("Add DDL", 0);
        });
        
        
        //Initializes the table
        DefaultTableModel tableModel = new DefaultTableModel(new String[] {"Task Description", "Deadline"}, 0);
        JTable taskTable = new JTable(tableModel);
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskTable.setDefaultEditor(Object.class, null);

        //gives the table an event that double clicking the a row to modify it.
        taskTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {

                //double clicked
                if (me.getClickCount() == 2) {    

                    //gets the seleted row
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow();

                    //navigates to modifying the task
                    that.dispose();
                    new ModifyDDLFrame("Modify DDL", row);
                }
            }
        });


        //adds the title panel and table to the frame
        JScrollPane scrollPane = new JScrollPane(taskTable);
        getContentPane().add(titlePanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        //adds data to the table
        CSVProcessor csvProcessor = new CSVProcessor("DataFiles/DDL.csv", DDL.class);
        Object[] DDLList = csvProcessor.readData();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM. yyyy", new Locale("en", "US"));
        for (Object obj: DDLList) {
            DDL ddl = (DDL) obj;
            tableModel.addRow(new String[] {ddl.getName(), simpleDateFormat.format(ddl.getDueTime().getTime())});
        }
        
        //configuration to the frame
        setBackToHome();
        setProperties("My DDL");
    }
}