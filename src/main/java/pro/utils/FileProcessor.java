package pro.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * A class to process file, offer data extracted from files to pages
 * @author Ruitang Lin
 */ 
public class FileProcessor {

    protected String fileName;

    /**
     * Constructs a processer handling specific file
     * 
     * @param fileName    path of the file
     */
    public FileProcessor(String fileName) {
        this.setFileName(fileName);
    }

    /**
     * Sets the name of the file
     * 
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads the string in the file, splits it by ` , and turns it into an array of string
     * 
     * @return String array
     */
    public Object[] readData() {

        //saves the read data
        String conStr = "";

        try {
            //gets the bufferedReader
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            //reads data
            conStr = bufferedReader.readLine();

            //close
            bufferedReader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        //nothing read
        if(conStr.length() == 0) return null;

        //saves the string splited
        ArrayList<String> stringList = new ArrayList<>();
        for(String str: conStr.split("`")) {
            if(str.length() == 1 && str.charAt(0) == ' ') stringList.add("");
            else stringList.add(str);
        }
        return stringList.toArray();
    }

    /**
     * Saves all string in a list into a file
     * 
     * @param strList string list to be converted
     */
    public void writeData(String []strList)  {

        //saves the string to be written into the file
        StringBuilder st = new StringBuilder();
        try {

            //get the bufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName), StandardCharsets.UTF_8));

            //fills the string
            for(int i = 0 ; i < strList.length; i++) {
                st.append(strList[i].length() == 0 ? " " : strList[i]).append(i == strList.length - 1 ? "" : "`");
            }

            //writes and closes
            bufferedWriter.write(st.toString());
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}