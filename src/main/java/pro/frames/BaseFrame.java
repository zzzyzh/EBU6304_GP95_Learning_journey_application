package pro.frames;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * An abstract class to config all the frames
 * @author Ruitang Lin
 */
abstract public class BaseFrame extends JFrame{

    /**
     * A method for all frame to set some common properties
     * 
     * @param title window title
     */
    public void setProperties(String title) {
        pack();
        setTitle(title);
        setLocationRelativeTo(null);
        setMinimumSize(getSize());
        setVisible(true);
    }

    /**
     * Reads data from JComboBox<Integer> and turn them into a calendar
     * 
     * @param year        the year chose by user
     * @param month       the month chose by user
     * @param day         the day chose by user
     * @param hour        the hour chose by user
     * @param minute      the minute chose by user
     * @return            the date in type Calendar
     */
    public Calendar boxToCanlander(JComboBox<Integer> year, JComboBox<Integer> month, JComboBox<Integer> day, JComboBox<Integer> hour, JComboBox<Integer> minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
            Integer.parseInt(year.getSelectedItem().toString()),
            Integer.parseInt(month.getSelectedItem().toString()) - 1,
            Integer.parseInt(day.getSelectedItem().toString()),
            Integer.parseInt(hour.getSelectedItem().toString()),
            Integer.parseInt(minute.getSelectedItem().toString()),
            0
        );
        return calendar;
    }

    /**
     * Sets data from calendar into JComboBox<Integer>
     * 
     * @param calendar        the date data
     * @param year            the year chose by user
     * @param month           the month chose by user
     * @param day             the day chose by user
     * @param hour            the hour chose by user
     * @param minute          the minute chose by user
     */
    public void canlendarToBox(Calendar calendar, JComboBox<Integer> year, JComboBox<Integer> month, JComboBox<Integer> day, JComboBox<Integer> hour, JComboBox<Integer> minute) {
        year.setSelectedItem(calendar.get(Calendar.YEAR));
        month.setSelectedItem(calendar.get(Calendar.MONTH) + 1);
        day.setSelectedItem(calendar.get(Calendar.DAY_OF_MONTH));
        hour.setSelectedItem(calendar.get(Calendar.HOUR_OF_DAY));
        minute.setSelectedItem(calendar.get(Calendar.MINUTE));
    }
    
    /**
     * A method for frame to set that if the window is closed, the homepage will be shown.
     */
    public void setBackToHome() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new HomeFrame();
            }
        });
    }
}