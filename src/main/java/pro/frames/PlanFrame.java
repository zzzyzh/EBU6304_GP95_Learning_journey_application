package pro.frames;

import pro.entities.Plan;
import pro.utils.CSVProcessor;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A class for displaying the plans set by the use
 * @author Zhonghao Yan
 */
public class PlanFrame extends BaseFrame{
    
    /**
     * Read the time in two calender
     * Return a String in the format of 'day month year - day month year'
     * For example: 08 Sep. 2023 - 09 Sep. 2023
     * 
     * @param calendar1      the start time
     * @param calendar2      the end time
     * @return               the formatted string
     */
    private String toDateString(Calendar calendar1, Calendar calendar2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM. yyyy", new Locale("en", "US"));
        return simpleDateFormat.format(calendar1.getTime()) + " - " + simpleDateFormat.format(calendar2.getTime());
    }

    /**
     * Constructs a frame for user to show plans
     */
    public PlanFrame() {

        //sets the layout
        setLayout(new BorderLayout());
        
        //creates a panel for the title and button
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(getWidth(), 50));
        
        //creates a label for the title
        Font titleFont = new Font("Arial", Font.BOLD, 20);
        JLabel titleLabel = new JLabel("Learning Plan           ");
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        //creates add button
        JButton addButton = new JButton("ADD+");
        addButton.setFont(new Font("Arial", Font.PLAIN, 12)); 
        addButton.setPreferredSize(new Dimension(80, 30));
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        titlePanel.add(addButton);
        
        //adds the title panel to the frame
        add(titlePanel, BorderLayout.NORTH);
        
        //creates a table with 3 columns and n rows
        String[] columnNames = {"Plan", "Time"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(40);
        
        //center align table cell contents
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centerRenderer);
        
        //adds the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        add(scrollPane, BorderLayout.CENTER);

        //adds data to the table
        CSVProcessor csvProcessor = new CSVProcessor("DataFiles/Plan.csv", Plan.class);
        Object[] planList = csvProcessor.readData();
        for (Object obj: planList) {
            Plan plan = (Plan) obj;
            model.addRow(new String[] {plan.getDescription(), toDateString(plan.getStartTime(), plan.getEndTime())});
        }


        //sets event listener
        PlanFrame that = this;
        addButton.addActionListener(e -> {
            that.dispose();
            new ModifyPlanFrame("Add Plan", 0);
        });

        //adds a listener to handle the double click event
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow();
                    that.dispose();
                    new ModifyPlanFrame("Modify Plan", row);
                }
            }
        });

        //sets properties
        setBackToHome();
        setProperties("My Plan");
    }
}