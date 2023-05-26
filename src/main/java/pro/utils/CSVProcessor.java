package pro.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * This class is used to optionally export existing information about the user and to add external data of csv type
 * @author Ruitang Lin
 */
public class CSVProcessor extends FileProcessor{

    private Class<?> targetClass;

    /**
     * Constructs a CSVProcessor to handle data of specific type in a specific file
     * 
     * @param fileName        target file path
     * @param targetClass     target class to be processed
     */
    public CSVProcessor(String fileName, Class<?> targetClass) {
        super(fileName);
        this.setTargetClass(targetClass);
    }

    /**
     * Gets the target class
     * 
     * @return the target class
     */
    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    /**
     * Sets the targetClass
     * 
     * @param targetClass the targetClass
     */
    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * Reads the entity string in the csv, into an array of objects.
     * 
     * @return Object array
     */
    public Object[] readData() {
        //saves the read string
        String conStr;

        //saves the data
        ArrayList<Object> arrayList = new ArrayList<>();
        try {
            //gets the specific bufferedReader
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            //gets the method setFromCSVString() of the target class
            Method method = this.targetClass.getMethod("setFromCSVString", String.class);

            //finds the constructor with no arguments
            Constructor<?>[] constructors = this.targetClass.getDeclaredConstructors();
            Constructor<?> constructor = null;
            for(Constructor<?> ele: constructors) 
                if(ele.getParameterCount() == 0) {
                    constructor = ele;
                    break;
                }
                
            //reads data from file
            while ((conStr = bufferedReader.readLine()) != null) {

                //gets a default instance of target class
                Object obj = constructor.newInstance();

                //sets all fields from CSV string to the instance, and adds it to the arrayList
                arrayList.add(method.invoke(this.targetClass.cast(obj), conStr));
            }

            //closes
            bufferedReader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return arrayList.toArray();
    }

    /**
     * Converts all properties in an array of objects into a format that can be stored in csv
     * 
     * @param objects objects to be converted
     */
    public void writeData(Object[] objects) {
        try {
            //gets the bufferedwriter
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName), StandardCharsets.UTF_8));

            //gets the method toCSVString() of the target class
            Method method = this.targetClass.getMethod("toCSVString");

            //uses the method toCSVString() to get the CSV string of objects and write it into the CSV file, while preserving '\n'
            for(Object object: objects) {
                bufferedWriter.write(method.invoke(this.targetClass.cast(object)).toString().replaceAll("\n", "\\n"));

                //gets a new line
                bufferedWriter.newLine();
            }

            //closes
            bufferedWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}