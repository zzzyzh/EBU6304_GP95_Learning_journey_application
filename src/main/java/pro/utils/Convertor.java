package pro.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract class used for all entity class to convert the type of data
 * @author Ruitang Lin
 */
public abstract class Convertor {

    /**
     * Converts the custom object to CSV string
     * 
     * @return         string of type CSV
     */
    public String toCSVString() {

        //gets the fields of the class
        Field[] field = this.getClass().getDeclaredFields();

        //the CSV string
        StringBuilder ans = new StringBuilder();
        
        try {
            for(int i = 0; i < field.length; i++) {

                //adds the delimiter
                if(i != 0) ans.append(',');

                //makes the field accessible
                field[i].setAccessible(true);

                //uses toString() method to convert each field to string, and preserve '\n'
                String currentValue = field[i].get(this).toString().replace("\n", "\\n");

                //uses "yyyy-MM-dd HH:mm:ss" string to replace the return value of toString() method of Calendar class
                if(field[i].getType().equals(java.util.Calendar.class)) {

                    //gets the date by using the method of Calendar "getTime()"
                    Object date = Class.forName("java.util.Calendar").getDeclaredMethod("getTime").invoke(field[i].get(this));

                    //adds the string to the answer
                    ans.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                }
                //uses `` to quote the string containing the delimiter
                else if(currentValue.contains(",")) ans.append("`").append(currentValue).append("`");
                else ans.append(currentValue);

                //recovers the accessibility
                field[i].setAccessible(false);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ans.toString();
    }

    /**
     *  Extracts inputted CSV string，and sets the information to the object
     * 
     *  @param s       CSV string
     *  @return        the object
     */
    public Object setFromCSVString(String s) {

        //gets the fields of the class
        Field[] field = this.getClass().getDeclaredFields();

        //uses regular expression to extract each field in the CSV string
        Pattern pattern = Pattern.compile("(?<=^|,)(\\`(?:[^\\`]|\\`\\`)*\\`|[^,]*)");
        Matcher matcher = pattern.matcher(s);

        //array to save each field string
        String[] itemList = new String[field.length];

        //current index of the itemList
        int idx = 0;

        //traverses all field string found in s
        while (matcher.find()) {

            // gets the string
            String element = matcher.group(1);

            //unquotes ``
            if(element.startsWith("`") && element.endsWith("`") && element.contains(",")) 
                element = element.substring(1, element.length() - 1);

            //pushes into the array
            itemList[idx++] = element.replace("\\n", "\n");
        }
        try {

            //sets the value to each field
            for(int i = 0; i < idx; i++) {

                //sets the accessibility
                field[i].setAccessible(true);

                //enumerates each possible type 
                if(field[i].getType() == int.class)
                    field[i].set(this, Integer.parseInt(itemList[i]));
                else if(field[i].getType() == double.class)
                    field[i].set(this, Double.parseDouble(itemList[i]));
                else if(field[i].getType() == String.class) 
                    field[i].set(this, itemList[i]);
                else if(field[i].getType() == java.util.Calendar.class) {
                    Calendar calendar = Calendar.getInstance();
                    int year = Integer.parseInt(itemList[i].substring(0, 4));
                    int month = Integer.parseInt(itemList[i].substring(5, 7));
                    int day = Integer.parseInt(itemList[i].substring(8, 10));
                    int hour = Integer.parseInt(itemList[i].substring(11, 13));
                    int minute = Integer.parseInt(itemList[i].substring(14, 16));
                    int second = Integer.parseInt(itemList[i].substring(17, 19));
                    calendar.set(year, month - 1, day, hour, minute, second);
                    field[i].set(this, calendar);
                }

                //recovers the accessibility
                field[i].setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}